<?php

App::uses('AppController', 'Controller');
App::uses('RefManRefereeFormat', 'Utility');

/**
 * Assignments Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.4
 * @since 0.1
 */
class AssignmentsController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeFormat', 'RefereeForm');

	/** Models. */
	public $uses = array('Assignment', 'League', 'LeagueGame', 'Referee', 'RefereeAssignment', 'Season');

	/**
	 * Defines actions to perform before the action method is executed.
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		$this->League->recursive = -1;
		$this->Referee->recursive = -1;
	}

	/**
	 * Import method.
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public function import() {

		$this->set('title_for_layout', __('Import von Schiedsrichtereinsätzen'));

		// create messages
		if (!empty($this->request->data)) {

			$this->set('importdata', $this->request->data);

			$importresult = array();

			// settings
			$delimiter = empty($this->request->data['Assignments']['delimiter']) ? ';' : $this->request->data['Assignments']['delimiter'];
			$enclosure = empty($this->request->data['Assignments']['enclosure']) ? '"' : $this->request->data['Assignments']['enclosure'];
			$escape = empty($this->request->data['Assignments']['escape']) ? '\\' : $this->request->data['Assignments']['escape'];

			// read and parse csv
			$matrix = array(
											'Datum/Uhrzeit' => 'datetime',
											'Lokal' => 'venue',
											'Spielnr.' => 'game_number',
											'Liga' => 'league',
											'Heimmannschaft' => 'home',
											'Gastmannschaft' => 'off',
											'Oberschiedsrichter' => 'referee',
											'stellv. OSR' => 'standbyref',
											'Schiedsrichter' => 'umpire',
											);

			$lookup = array();

			// open file
			$tmpFile = sprintf('%s%s', TMP, $this->request->data['Assignments']['csvfile']);
			if (!file_exists($tmpFile)) {
				$this->Session->setFlash(
																 __('CSV-Datei "%s" existiert nicht.', $tmpFile),
																 'flash',
																 array('class' => 'danger')
																 );
			} else {
				$handle = fopen($tmpFile, "r");

				// headings
				$csvline = fgetcsv($handle, 0, $delimiter, $enclosure, $escape);
				foreach ($csvline as $key => $value) {
					if (array_key_exists($value, $matrix)) {
						$lookup[$matrix[$value]] = $key;
					}
				}

				// read data
				$rowCount = 0;
				$csv = array();
				while (($csvline = fgetcsv($handle, 0, $delimiter, $enclosure, $escape)) !== FALSE) {
					$csv[$rowCount] = array();
					foreach ($lookup as $key => $value) {
						$csv[$rowCount][$key] = $csvline[$value];
					}
					$rowCount++;
				}
				fclose($handle);
				$importresult[] = __('%d Datenzeilen eingelesen', $rowCount);

				// preparation
				$arrTemp = $this->League->find('all');
				$arrLeagues = array();
				foreach ($arrTemp as $league) {
					$arrLeagues[$league['League']['abbreviation']] = $league['League']['id'];
				}

				// import data
				$assignCount = 0;
				$errors = array();
				foreach ($csv as $csvassignment) {

					if (empty($csvassignment['game_number'])) {
						// no league game
					} else {
						// league game

						$theSeason = $this->Season->getSeason(null, $this->viewVars['isEditor']);

						$tmpData = array();

						$tmpData['LeagueGame']['game_number'] = $csvassignment['game_number'];
						$tmpData['LeagueGame']['season_id'] = $theSeason['Season']['id'];

						if (array_key_exists($csvassignment['league'], $arrLeagues)) {
							$tmpData['LeagueGame']['league_id'] = $arrLeagues[$csvassignment['league']];
						} else {
							debug($csvassignment['league']);
						}

						$tmpDateTime = strtotime($csvassignment['datetime']);
						$tmpData['Assignment']['start'] = RefManRefereeFormat::formatDate($tmpDateTime, 'sqldatetime');

						//debug($csvassignment);
						//debug($tmpData);
						$this->LeagueGame->create();
						if ($this->LeagueGame->saveAssociated($tmpData, array('deep' => true))) {
							$assignCount++;
						} else {
							$errors[] = $this->LeagueGame->validationErrors;
						}
					}

				}

				$importresult[] = __('%d Einsätze importiert', $assignCount);
				$importresult[] = __('%d Einsätze nicht importiert', $rowCount - $assignCount);

				// success
				$this->Session->setFlash(
																 RefManRefereeFormat::formatMultiline($importresult, '</p><p>'),
																 'flash',
																 array(
																			 'class' => (empty($errors)) ? 'success' : 'danger',
																			 'nohtml' => true,
																			 )
																 );
				if (!empty($errors)) {
					debug($errors);
				}
			}

		}

	}





	/**
	 * Index method.
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function index($season = null) {

		$this->setAndGetStandardIndex($season);

		$this->set('title_for_layout', __('Übersicht der Schiedsrichtereinsätze'));
	}

	/**
	 * View method: show the assignment with the given id.
	 *
	 * @param $id id of assignment
	 * @return void
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function view($id = null) {

		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Schiedsrichtereinsatz mit der ID \'%s\' existiert nicht.', $id));
		}
		$assignment = $this->Assignment->read(null, $id);

		$this->setAndGetStandardNewAddView(null, $assignment);
		$this->set('title_for_layout', __('Detailanzeige Schiedsrichtereinsatz'));
		$this->render('/Generic/view');
	}

	/**
	 * Add method: add new assignment.
	 *
	 * @return void
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function add() {

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Assignment', $this->request->data)) {

			$tmpData = $this->request->data;
			$tmpData['Assignment']['start'] = RefManRefereeFormat::sqlFromDateTime($this->request->data['Assignment']['start']['date'], $this->request->data['Assignment']['start']['time']);

			$this->Assignment->create();
			if ($this->Assignment->saveAssociated($tmpData, array('deep' => true))) {
				$this->Session->setFlash(__('Der Schiedsrichtereinsatz wurde gespeichert.'));
				$this->redirect(array('action' => 'edit', $this->Assignment->id));
			} else {
				$this->Session->setFlash(__('Der Schiedsrichtereinsatz konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));

				debug($this->Assignment->validationErrors);
				debug($tmpData);
			}

		}

		$this->setAndGetStandardNewAddView();
		$this->set('title_for_layout', __('Schiedsrichtereinsatz anlegen'));
		$this->render('/Generic/add');
	}

	/**
	 * Edit method: edit the referee assignment with the given id.
	 *
	 * @param $id id of assignment
	 * @return void
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function edit($id = null) {

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Assignment', $this->request->data)) {

			$tmpData = $this->request->data;
			$tmpData['Assignment']['start'] = RefManRefereeFormat::sqlFromDateTime($this->request->data['Assignment']['start']['date'], $this->request->data['Assignment']['start']['time']);

			if ($this->Assignment->saveAssociated($tmpData, array('deep' => true))) {
				$this->Session->setFlash(__('Der geänderte Schiedsrichtereinsatz wurde gespeichert.'));
			} else {
				$this->Session->setFlash(__('Der geänderte Schiedsrichtereinsatz konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));

				debug($this->Assignment->validationErrors);
				debug($tmpData);
			}

			$this->redirect(array('action' => 'edit', $this->Assignment->id));

		}

		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Schiedsrichtereinsatz mit der ID \'%s\' existiert nicht.', $id));
		}

		$assignment = $this->Assignment->read(null, $id);

		$this->setAndGetStandardNewAddView(null, $assignment);
		$this->set('title_for_layout', __('Schiedsrichtereinsatz editieren'));
		$this->render('/Generic/edit');
	}

	/**
	 * Set and get standard values for index.
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandardIndex($season = null) {

		$theSeason = $this->getSeason($season);
		$this->setAndGetStandard();

		$assignments = $this->getAssignments($theSeason);
		$this->set('assignments', $assignments);

	}

	/**
	 * Set and get standard values for new, add, view.
	 *
	 * @param season season (default: null == current season)
	 * @param $assignment assignment
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function setAndGetStandardNewAddView($season = null, &$assignment = null) {

		$theSeason = $this->getSeason($season);
		$this->setAndGetStandard();

		if ($assignment === null) {
			$this->set('assignment', array());
		} else {
			$this->set('assignment', $assignment);
			$this->set('id', $assignment['Assignment']['id']);
		}

		$leaguearray = $this->League->find('list');
		asort($leaguearray, SORT_LOCALE_STRING);
		$this->set('leaguearray', $leaguearray);

	}

	/**
	 * Set and get standard values.
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function setAndGetStandard() {

		$this->set('seasonarray', $this->Season->getSeasonList($this->viewVars['isEditor']));

		$this->set('controller', 'Assignments');
	}

	/**
	 * Returns the assignments.
	 *
	 * @param $season season
	 * @return array of assignments
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function getAssignments($season) {

		$this->Assignment->recursive = 2;

		$assignments = $this->Assignment->find('all', array('conditions' => array('LeagueGame.season_id' => $season['Season']['id'])));

		foreach ($assignments as &$assignment) {
			// status (todo)
			$assignment['status'] = 'normal'; // or changed
		}

		return $assignments;
	}

}

/* EOF */


<?php

App::uses('AppController', 'Controller');
App::uses('RefManRefereeFormat', 'Utility');

/**
 * Assignments Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class AssignmentsController extends AppController {

	/** Helper classes. */
	public $helpers = array('PHPExcel', 'RefereeFormat', 'RefereeForm');

	/** Models. */
	public $uses = array('Assignment', 'Club', 'League', 'LeagueGame', 'Referee', 'RefereeAssignment', 'Season');

	/**
	 * Defines actions to perform before the action method is executed.
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		$this->Club->recursive = -1;
		$this->League->recursive = -1;
		$this->Referee->recursive = -1;
	}

	/**
	 * Index method.
	 *
	 * @param season season to export (default: null == current season)
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
			}

		}

		$this->setAndGetStandardNewAddView();
		$this->set('title_for_layout', __('Schiedsrichtereinsatz anlegen'));

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
			}

			//debug($this->Assignment->validationErrors);
			//debug($tmpData);
			$this->redirect(array('action' => 'edit', $this->Assignment->id));

		}

		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Schiedsrichtereinsatz mit der ID \'%s\' existiert nicht.', $id));
		}

		$assignment = $this->Assignment->read(null, $id);

		$this->setAndGetStandardNewAddView(null, $assignment);
		$this->set('title_for_layout', __('Schiedsrichtereinsatz editieren'));
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

		$seasonarray = $this->Season->find('list');
		asort($seasonarray, SORT_LOCALE_STRING);

		$this->set('seasonarray', $seasonarray);

	}

	/**
	 * Returns season (default, stated, or from filter).
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function getSeason($season = null) {

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Filter', $this->request->data)) {
			$theSeason = $this->Season->findById($this->request->data['Filter']['season']);
			$theSeason = $theSeason['Season'];
		} else {
			$theSeason = $this->Season->getSeason($season);
		}
		$this->set('season', $theSeason);

		return $theSeason;
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

		$leaguegames = $this->LeagueGame->findAllBySeasonId($season['id']);

		$arrReturn = array();
		foreach ($leaguegames as $leaguegame) {
			$arrReturn[] = $this->fillAssignment($leaguegame);
		}

		return $arrReturn;
	}

	/**
	 * Fills the assignment with the needed data.
	 *
	 * @param $leaguegame league game to use
	 * @param $season season (null if season should be ignored)
	 * @return referee assignment
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function fillAssignment($leaguegame, $season = null) {

		$arrReturn = array();

		// id
		$arrReturn['id'] = $leaguegame['Assignment']['id'];

		// start
		$arrReturn['start'] = $leaguegame['Assignment']['start'];

		// game number
		$arrReturn['game_number'] = $leaguegame['LeagueGame']['game_number'];

		// league
		$arrReturn['league'] = $leaguegame['League']['abbreviation'];

		// remark
		$arrReturn['remark'] = $leaguegame['Assignment']['remark'];

		// status
		$arrReturn['status'] = 'normal'; // or changed

		//$arrReturn['orig'] = $leaguegame;
		return $arrReturn;
	}

	/**
	 * Returns the status types used by the referees.
	 *
	 * @param $referees referees
	 * @param $season season
	 * @return array of status types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getStatusTypes($referees, $season) {
		$statustypes = array();

		foreach ($referees as $referee) {
			if (!array_key_exists($referee['RefereeStatus']['sid'], $statustypes)) {
				$statustypes[$referee['RefereeStatus']['sid']] = $referee['RefereeStatus'];
			}
			if (($referee['RefereeStatus']['sid'] == StatusType::SID_MANY) ||
					($referee['RefereeStatus']['sid'] == StatusType::SID_INACTIVESEASON) ||
					($referee['RefereeStatus']['sid'] == StatusType::SID_OTHER)) {
				$statustypes[$referee['RefereeStatus']['sid']]['referees'][] = $referee['Person'];
			}
		}

		ksort($statustypes);

		return $statustypes;
	}

	/**
	 * Returns the contact types.
	 *
	 * @return array of contact types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getContactTypes() {
		$contacttypes = array();
		foreach ($this->ContactType->find('all') as $contacttype) {
			$contacttypes[$contacttype['ContactType']['id']] = $contacttype['ContactType'];
		}
		return $contacttypes;
	}

	/**
	 * Returns the club array for use in select fields.
	 *
	 * @return array of club
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getClubArray() {
		$clubarray = $this->Club->find('list');
		asort($clubarray, SORT_LOCALE_STRING);

		return $clubarray;
	}

}

/* EOF */


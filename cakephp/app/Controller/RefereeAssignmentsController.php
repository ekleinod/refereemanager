<?php

App::uses('AppController', 'Controller');
App::uses('RefManRefereeFormat', 'Utility');

/**
 * RefereeAssignments Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class RefereeAssignmentsController extends AppController {

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
	 * @version 0.1
	 * @since 0.1
	 */
	public function index($season = null) {

		$this->setAndGetStandard();

		$this->set('title_for_layout', __('Übersicht der Schiedsrichtereinsätze'));
	}

	/**
	 * View method: show the refereeassignment with the given id.
	 *
	 * @param $id id of refereeassignment
	 * @return void
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function view($id = null) {

		$this->RefereeAssignment->id = $id;
		if (!$this->RefereeAssignment->exists()) {
			throw new NotFoundException(__('Schiedsrichtereinsatz mit der ID \'%s\' existiert nicht.', $id));
		}
		$refereeassignment = $this->RefereeAssignment->read(null, $id);

		$this->setAndGetStandardNewAddView($refereeassignment);
		$this->set('title_for_layout', __('Detailanzeige Schiedsrichtereinsatz'));
	}

	/**
	 * Add method: add new referee assignment.
	 *
	 * @return void
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function add() {

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('RefereeAssignment', $this->request->data)) {

			$tmpData = array();
			$tmpData['Assignment'] = array();
			$tmpData['Assignment']['start'] = RefManRefereeFormat::sqlFromDateTime($this->request->data['RefereeAssignment']['start']['date'], $this->request->data['RefereeAssignment']['start']['time']);

			$this->Assignment->create();
			if ($this->Assignment->save($tmpData)) {

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
	 * @param $id id of refereeassignment
	 * @return void
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function edit($id = null) {

		$this->RefereeAssignment->id = $id;
		if (!$this->RefereeAssignment->exists()) {
			throw new NotFoundException(__('Schiedsrichtereinsatz mit der ID \'%s\' existiert nicht.', $id));
		}
		$refereeassignment = $this->RefereeAssignment->read(null, $id);

		$this->setAndGetStandardNewAddView($refereeassignment);
		$this->set('title_for_layout', __('Schiedsrichtereinsatz editieren'));
	}

	/**
	 * Set and get standard values.
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function setAndGetStandard($season = null) {

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Filter', $this->request->data)) {
			$theSeason = $this->Season->findById($this->request->data['Filter']['season']);
			$theSeason = $theSeason['Season'];
		} else {
			$theSeason = $this->Season->getSeason($season);
		}

		$seasonarray = $this->Season->find('list');
		asort($seasonarray, SORT_LOCALE_STRING);

		$refereeassignments = $this->getRefereeAssignments($theSeason);

		$this->set('refereeassignments', $refereeassignments);
		$this->set('season', $theSeason);
		$this->set('seasonarray', $seasonarray);
	}

	/**
	 * Set and get standard values for new, add, view.
	 *
	 * @param $refereeassignment refereeassignment
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function setAndGetStandardNewAddView(&$refereeassignment = null) {

		if ($refereeassignment === null) {
			$this->set('refereeassignment', array());
		} else {
			$this->set('refereeassignment', $refereeassignment);
			$this->set('id', $refereeassignment['RefereeAssignment']['id']);
		}
	}

	/**
	 * Returns the referee assignments.
	 *
	 * @param $season season
	 * @return array of referee assignments
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getRefereeAssignments($season) {
		$leaguegames = $this->LeagueGame->find('all');//todo where season clause
		usort($leaguegames, array('RefereeAssignmentsController', 'compareTo'));

		$arrReturn = array();
		foreach ($leaguegames as $leaguegame) {
			$arrReturn[] = $this->fillRefereeAssignment($leaguegame);
		}

		return $arrReturn;
	}

	/**
	 * Fills the referee assignment with the needed data.
	 *
	 * @param $leaguegame league game to use
	 * @param $season season (null if season should be ignored)
	 * @return referee assignment
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function fillRefereeAssignment($leaguegame, $season = null) {

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

	/**
	 * Compare two objects.
	 *
	 * @param a first object
	 * @param b second object
	 * @return comparison result
	 *  @retval -1 a<b
	 *  @retval 0 a==b
	 *  @retval 1 a>b
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public static function compareTo($a, $b) {

		// first criterion: name
		if ($a['Person']['name'] < $b['Person']['name']) {
			return -1;
		}
		if ($a['Person']['name'] > $b['Person']['name']) {
			return 1;
		}

		// second criterion: first name
		if ($a['Person']['first_name'] < $b['Person']['first_name']) {
			return -1;
		}
		if ($a['Person']['first_name'] > $b['Person']['first_name']) {
			return 1;
		}

		// equal
		return 0;
	}

}

/* EOF */


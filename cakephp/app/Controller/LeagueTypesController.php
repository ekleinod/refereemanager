<?php

App::uses('AppController', 'Controller');

/**
 * LeagueTypes Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.3
 */
class LeagueTypesController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeForm');

	/** Models. */
	public $uses = array('LeagueType', 'SexType');

	/**
	 * Defines actions to perform before the action method is executed.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		$this->SexType->recursive = -1;
	}

	/**
	 * Index method.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function index() {

		$this->setAndGetStandardIndex();

		$this->set('title_for_layout', __('Übersicht der Liga-Typen'));
	}

	/**
	 * View method.
	 *
	 * @param $id id of data
	 * @return void
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function view($id = null) {

		$this->LeagueType->id = $id;
		if (!$this->LeagueType->exists()) {
			throw new NotFoundException(__('Liga-Typ mit der ID \'%s\' existiert nicht.', $id));
		}
		$leaguetype = $this->LeagueType->read(null, $id);

		$this->setAndGetStandardNewAddView($leaguetype);
		$this->set('title_for_layout', __('Detailanzeige Liga-Typ'));
		$this->render('/Generic/view');
	}

	/**
	 * Add method.
	 *
	 * @return void
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function add() {

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('LeagueType', $this->request->data)) {

			$tmpData = $this->request->data;

			$this->LeagueType->create();
			if ($this->LeagueType->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Der Liga-Typ wurde gespeichert.'));
				$this->redirect(array('action' => 'edit', $this->LeagueType->id));
			} else {
				$this->Session->setFlash(__('Der Liga-Typ konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));

				debug($this->LeagueType->validationErrors);
				debug($tmpData);
			}

		}

		$this->setAndGetStandardNewAddView();
		$this->set('title_for_layout', __('Liga-Typ anlegen'));
		$this->render('/Generic/add');
	}

	/**
	 * Edit method.
	 *
	 * @param $id id of data
	 * @return void
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function edit($id = null) {

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('LeagueType', $this->request->data)) {

			$tmpData = $this->request->data;

			if ($this->LeagueType->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Der geänderte Liga-Typ wurde gespeichert.'));
			} else {
				$this->Session->setFlash(__('Der geänderte Liga-Typ konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));

				debug($this->LeagueType->validationErrors);
				debug($tmpData);
			}

		}

		$this->LeagueType->id = $id;
		if (!$this->LeagueType->exists()) {
			throw new NotFoundException(__('Der Liga-Typ mit der ID \'%s\' existiert nicht.', $id));
		}

		$leaguetype = $this->LeagueType->read(null, $id);

		$this->setAndGetStandardNewAddView($leaguetype);
		$this->set('title_for_layout', __('Liga-Typ editieren'));
		$this->render('/Generic/edit');
	}

	/**
	 * Set and get standard values for index.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandardIndex() {

		$this->setAndGetStandard();

		$leaguetypes = $this->getLeagueTypes();
		$this->set('leaguetypes', $leaguetypes);

	}

	/**
	 * Set and get standard values for new, add, view.
	 *
	 * @param $leaguetype LeagueType
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandardNewAddView(&$leaguetype = null) {

		$this->setAndGetStandard();

		if ($leaguetype === null) {
			$this->set('leaguetype', array());
		} else {
			$this->set('leaguetype', $leaguetype);
			$this->set('id', $leaguetype['LeagueType']['id']);
		}

		$sextypearray = $this->SexType->find('list');
		$this->set('sextypearray', $sextypearray);

	}

	/**
	 * Set and get standard values.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandard() {
		$this->set('controller', 'LeagueTypes');
	}

	/**
	 * Returns the leaguetypes.
	 *
	 * @return array of leaguetypes
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function getLeagueTypes() {
		$leaguetypes = $this->LeagueType->getLeagueTypes();
		return $leaguetypes;
	}

}

/* EOF */


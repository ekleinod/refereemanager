<?php

App::uses('AppController', 'Controller');

/**
 * Leagues Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.3
 */
class LeaguesController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeForm');

	/** Models. */
	public $uses = array('League', 'LeagueType');

	/**
	 * Defines actions to perform before the action method is executed.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		$this->LeagueType->recursive = -1;
	}

	/**
	 * Index method.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function index() {

		$this->setAndGetStandardIndex();

		$this->set('title_for_layout', __('Übersicht der Ligen'));
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

		$this->League->id = $id;
		if (!$this->League->exists()) {
			throw new NotFoundException(__('Liga mit der ID \'%s\' existiert nicht.', $id));
		}
		$league = $this->League->read(null, $id);

		$this->setAndGetStandardNewAddView($league);
		$this->set('title_for_layout', __('Detailanzeige Liga'));
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

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('League', $this->request->data)) {

			$tmpData = $this->request->data;

			$this->League->create();
			if ($this->League->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Die Liga wurde gespeichert.'));
				$this->redirect(array('action' => 'edit', $this->League->id));
			} else {
				$this->Session->setFlash(__('Die Liga konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}

		}

		$this->setAndGetStandardNewAddView();
		$this->set('title_for_layout', __('Liga anlegen'));

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

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('League', $this->request->data)) {

			$tmpData = $this->request->data;

			if ($this->League->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Die geänderte Liga wurde gespeichert.'));
			} else {
				$this->Session->setFlash(__('Die geänderte Liga konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}

			$this->redirect(array('action' => 'edit', $this->League->id));

		}

		$this->League->id = $id;
		if (!$this->League->exists()) {
			throw new NotFoundException(__('Die Liga mit der ID \'%s\' existiert nicht.', $id));
		}

		$league = $this->League->read(null, $id);

		$this->setAndGetStandardNewAddView($league);
		$this->set('title_for_layout', __('Liga editieren'));
	}

	/**
	 * Set and get standard values for index.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandardIndex() {

		$this->setAndGetStandard();

		$leagues = $this->getLeagues();
		$this->set('leagues', $leagues);

	}

	/**
	 * Set and get standard values for new, add, view.
	 *
	 * @param $league league
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandardNewAddView(&$league = null) {

		$this->setAndGetStandard();

		if ($league === null) {
			$this->set('league', array());
		} else {
			$this->set('league', $league);
			$this->set('id', $league['League']['id']);
		}

		$leaguetypearray = $this->LeagueType->find('list');
		asort($leaguetypearray, SORT_LOCALE_STRING);
		$this->set('leaguetypearray', $leaguetypearray);

	}

	/**
	 * Set and get standard values.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandard() {
		// empty for now
	}

	/**
	 * Returns the leagues.
	 *
	 * @return array of leagues
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function getLeagues() {
		$leagues = $this->League->getLeagues();
		return $leagues;
	}

}

/* EOF */


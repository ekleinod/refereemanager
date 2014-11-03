<?php

App::uses('AppController', 'Controller');

/**
 * Clubs Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.3
 */
class ClubsController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeForm');

	/** Models. */
	public $uses = array('Club');

	/**
	 * Defines actions to perform before the action method is executed.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		//$this->ClubType->recursive = -1;
	}

	/**
	 * Index method.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function index() {

		$this->setAndGetStandardIndex();

		$this->set('title_for_layout', __('Übersicht der Clubs'));
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

		$this->Club->id = $id;
		if (!$this->Club->exists()) {
			throw new NotFoundException(__('Club mit der ID \'%s\' existiert nicht.', $id));
		}
		$club = $this->Club->read(null, $id);

		$this->setAndGetStandardNewAddView($club);
		$this->set('title_for_layout', __('Detailanzeige Club'));
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

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Club', $this->request->data)) {

			$tmpData = $this->request->data;

			$this->Club->create();
			if ($this->Club->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Der Club wurde gespeichert.'));
				$this->redirect(array('action' => 'edit', $this->Club->id));
			} else {
				$this->Session->setFlash(__('Der Club konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}

		}

		$this->setAndGetStandardNewAddView();
		$this->set('title_for_layout', __('Club anlegen'));
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

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Club', $this->request->data)) {

			$tmpData = $this->request->data;

			if ($this->Club->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Der geänderte Club wurde gespeichert.'));
			} else {
				$this->Session->setFlash(__('Der geänderte Club konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}

			$this->redirect(array('action' => 'edit', $this->Club->id));

		}

		$this->Club->id = $id;
		if (!$this->Club->exists()) {
			throw new NotFoundException(__('Der Club mit der ID \'%s\' existiert nicht.', $id));
		}

		$club = $this->Club->read(null, $id);

		$this->setAndGetStandardNewAddView($club);
		$this->set('title_for_layout', __('Club editieren'));
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

		$clubs = $this->getClubs();
		$this->set('clubs', $clubs);

	}

	/**
	 * Set and get standard values for new, add, view.
	 *
	 * @param $club Club
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandardNewAddView(&$club = null) {

		$this->setAndGetStandard();

		if ($club === null) {
			$this->set('club', array());
		} else {
			$this->set('club', $club);
			$this->set('id', $club['Club']['id']);
		}

	}

	/**
	 * Set and get standard values.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandard() {
		$this->set('controller', 'Clubs');
	}

	/**
	 * Returns the Clubs.
	 *
	 * @return array of Clubs
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function getClubs() {
		$clubs = $this->Club->getClubs();
		return $clubs;
	}

}

/* EOF */


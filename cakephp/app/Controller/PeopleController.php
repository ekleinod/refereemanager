<?php

App::uses('AppController', 'Controller');

/**
 * People Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.3
 */
class PeopleController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeForm');

	/** Models. */
	public $uses = array('Person', 'SexType');

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

		$this->set('title_for_layout', __('Übersicht der Personen'));
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

		$this->Person->id = $id;
		if (!$this->Person->exists()) {
			throw new NotFoundException(__('Die Person mit der ID \'%s\' existiert nicht.', $id));
		}
		$person = $this->Person->read(null, $id);

		$this->setAndGetStandardNewAddView($person);
		$this->set('title_for_layout', __('Detailanzeige "%s"', $person['Person']['title_person']));
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

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Person', $this->request->data)) {

			$tmpData = $this->request->data;

			$this->Person->create();
			if ($this->Person->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('"%s" wurde gespeichert.', $this->Person->getDisplayField()));
				$this->redirect(array('action' => 'edit', $this->Person->id));
			} else {
				$this->Session->setFlash(__('Die Person konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}

		}

		$this->setAndGetStandardNewAddView();
		$this->set('title_for_layout', __('Person anlegen'));
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

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Person', $this->request->data)) {

			$tmpData = $this->request->data;

			if ($this->Person->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Die Änderungen für "%s" wurden gespeichert.', $this->Person->getDisplayField()));
			} else {
				$this->Session->setFlash(__('Die Änderungen konnten nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}

			$this->redirect(array('action' => 'edit', $this->Person->id));

		}

		$this->Person->id = $id;
		if (!$this->Person->exists()) {
			throw new NotFoundException(__('Die Person mit der ID \'%s\' existiert nicht.', $id));
		}

		$person = $this->Person->read(null, $id);

		$this->setAndGetStandardNewAddView($person);
		$this->set('title_for_layout', __('Daten von "%s" editieren', $person['Person']['title_person']));
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

		$people = $this->getPeople();
		$this->set('people', $people);

	}

	/**
	 * Set and get standard values for new, add, view.
	 *
	 * @param $person Person
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandardNewAddView(&$person = null) {

		$this->setAndGetStandard();

		if ($person === null) {
			$this->set('person', array());
		} else {
			$this->set('person', $person);
			$this->set('id', $person['Person']['id']);
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
		$this->set('controller', 'People');
	}

	/**
	 * Returns the People.
	 *
	 * @return array of People
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function getPeople() {
		$people = $this->Person->getPeople();
		return $people;
	}

}

/* EOF */


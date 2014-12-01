<?php

App::uses('AppController', 'Controller');
App::uses('RefManRefereeFormat', 'Utility');

/**
 * People Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.3
 */
class PeopleController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeFormat', 'RefereeForm');

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

		if (!$this->viewVars['isReferee']) {
			$this->Session->setFlash(__('Der Zugriff ist nur für Schiedsrichter erlaubt. Bitte loggen Sie sich ein.'));
			$this->redirect(array('controller' => 'users', 'action' => 'login'));
		}

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
		$this->render('/Referees/index');
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

			$tmpData = $this->cleanRequest($this->request->data);

			$this->Person->create();
			if ($this->Person->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Die Person wurde gespeichert.'));
				$this->redirect(array('action' => 'edit', $this->Person->id));
			} else {
				$this->Session->setFlash(__('Die Person konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));

				debug($this->Person->validationErrors);
				debug($tmpData);
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

			$tmpData = $this->cleanRequest($this->request->data);

			if ($this->Person->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Die Änderungen wurden gespeichert.'));
			} else {
				$this->Session->setFlash(__('Die Änderungen konnten nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));

				debug($this->Person->validationErrors);
				debug($tmpData);
			}

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
	 * Clean request data.
	 *
	 * @param $request request data
	 * @return cleaned request data
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function cleanRequest($request) {

		$dataReturn = $request;
		$dataReturn['Person']['birthday'] = RefManRefereeFormat::sqlFromDateTime($request['Person']['birthday']['date']);
		$dataReturn['Person']['dayofdeath'] = RefManRefereeFormat::sqlFromDateTime($request['Person']['dayofdeath']['date']);

		$dataReturn = $this->cleanEmpty($dataReturn);

		return $dataReturn;
	}

	/**
	 * Set and get standard values for index.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandardIndex() {

		$this->setAndGetStandard();

		$people = $this->Person->getPeople();
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

}

/* EOF */


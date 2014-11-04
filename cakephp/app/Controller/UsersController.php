<?php

App::uses('AppController', 'Controller');

/**
 * Users Controller
 *
 * @property User $User
 */
class UsersController extends AppController {

	/**
	 * Defines actions to perform before the action method is executed.
	 *
	 */
	public function beforeFilter() {
		parent::beforeFilter();
		//$this->Auth->allow('add'); // this would allow users to register themselves, this is not used for now
	}

	/**
	 * Login to the application.
	 *
	 */
	public function login() {
		if ($this->request->is('post')) {
			if ($this->Auth->login()) {
				$this->redirect($this->Auth->redirect());
			} else {
				$this->Session->setFlash(__('Ungültiger Nutzername oder Passwort, bitte versuchen Sie es erneut.'));
			}
		}
	}

	/**
	 * Logout of the application.
	 *
	 */
	public function logout() {
		$this->redirect($this->Auth->logout());
	}

	/**
	 * index method
	 *
	 * @return void
	 */
	public function index() {
		$this->User->recursive = 0;
		$this->set('users', $this->paginate());
	}

	/**
	 * view method
	 *
	 * @throws NotFoundException
	 * @param string $id
	 * @return void
	 */
	public function view($id = null) {
		$this->User->id = $id;
		if (!$this->User->exists()) {
			throw new NotFoundException(__('Unbekannter Nutzer.'));
		}
		$this->set('user', $this->User->read(null, $id));
	}

	/**
	 * add method
	 *
	 * @return void
	 */
	public function add() {
		if ($this->request->is('post')) {
			$this->User->create();
			$this->request->data['User']['salt'] = Security::generateAuthKey();
			if ($this->User->save($this->request->data)) {
				$this->Session->setFlash(__('Der Nutzer wurde gespeichert.'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Der Nutzer konnte nicht gespeichert werden, versuchen Sie es erneut.'));
			}
		}
		$userRoles = $this->User->UserRole->find('list');
		asort($userRoles, SORT_LOCALE_STRING);
		$people = $this->User->Person->find('list');
		asort($people, SORT_LOCALE_STRING);
		$this->set(compact('userRoles', 'people'));
	}

	/**
	 * edit method
	 *
	 * @throws NotFoundException
	 * @param string $id
	 * @return void
	 */
	public function edit($id = null) {
		$this->User->id = $id;
		if (!$this->User->exists()) {
			throw new NotFoundException(__('Invalid user'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->User->save($this->request->data)) {
				$this->Session->setFlash(__('Die Änderungen des Nutzers wurden gespeichert.'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Die Änderungen des Nutzers konnten nicht gespeichert werden, versuchen Sie es erneut.'));
			}
		} else {
			$this->request->data = $this->User->read(null, $id);
		}
		$userRoles = $this->User->UserRole->find('list');
		$people = $this->User->Person->find('list');
		$this->set(compact('userRoles', 'people'));
	}

	/**
	 * delete method
	 *
	 * @throws MethodNotAllowedException
	 * @throws NotFoundException
	 * @param string $id
	 * @return void
	 */
	public function delete($id = null) {
		if (!$this->request->is('post')) {
			throw new MethodNotAllowedException();
		}
		$this->User->id = $id;
		if (!$this->User->exists()) {
			throw new NotFoundException(__('Unbekannter Nutzer.'));
		}
		if ($this->User->delete()) {
			$this->Session->setFlash(__('Der Nutzer wurde gelöscht.'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Der Nutzer konnte nicht gelöscht werden.'));
		$this->redirect(array('action' => 'index'));
	}
}

/* EOF */


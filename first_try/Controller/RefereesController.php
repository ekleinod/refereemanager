<?php

App::uses('AppController', 'Controller');

/**
 * Referees Controller
 *
 * @property Referee $Referee
 */
class RefereesController extends AppController {

	/**
	 * Index method: show all referees.
	 *
	 * @return void
	 */
	public function index() {

		// find referees
		$referees = $this->Referee->find('all');
		//asort($referees, SORT_LOCALE_STRING);

		// pass information to view
		$this->set('referees', $referees);

		// set title
		$this->set('title_for_layout', __('Übersicht der Schiedsrichter'));
	}

/**
 * view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function view($id = null) {
		$this->Referee->id = $id;
		if (!$this->Referee->exists()) {
			throw new NotFoundException(__('Invalid referee'));
		}
		$this->set('referee', $this->Referee->read(null, $id));
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->Referee->create();
			if ($this->Referee->save($this->request->data)) {
				$this->Session->setFlash(__('The referee has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The referee could not be saved. Please, try again.'));
			}
		}
		$clubs = $this->Referee->Club->find('list');
		$people = $this->Referee->Person->find('list');
		$statuses = $this->Referee->Status->find('list');
		$assignmentQuantities = $this->Referee->AssignmentQuantity->find('list');
		$refereeKinds = $this->Referee->RefereeKind->find('list');
		$this->set(compact('clubs', 'people', 'statuses', 'assignmentQuantities', 'refereeKinds'));
	}

/**
 * edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function edit($id = null) {
		$this->Referee->id = $id;
		if (!$this->Referee->exists()) {
			throw new NotFoundException(__('Invalid referee'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->Referee->save($this->request->data)) {
				$this->Session->setFlash(__('The referee has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The referee could not be saved. Please, try again.'));
			}
		} else {
			$this->request->data = $this->Referee->read(null, $id);
		}
		$clubs = $this->Referee->Club->find('list');
		$people = $this->Referee->Person->find('list');
		$statuses = $this->Referee->Status->find('list');
		$assignmentQuantities = $this->Referee->AssignmentQuantity->find('list');
		$refereeKinds = $this->Referee->RefereeKind->find('list');
		$this->set(compact('clubs', 'people', 'statuses', 'assignmentQuantities', 'refereeKinds'));
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
		$this->Referee->id = $id;
		if (!$this->Referee->exists()) {
			throw new NotFoundException(__('Invalid referee'));
		}
		if ($this->Referee->delete()) {
			$this->Session->setFlash(__('Referee deleted'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Referee was not deleted'));
		$this->redirect(array('action' => 'index'));
	}
}

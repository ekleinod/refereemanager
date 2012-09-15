<?php
App::uses('AppController', 'Controller');
/**
 * Assignments Controller
 *
 * @property Assignment $Assignment
 */
class AssignmentsController extends AppController {

/**
 * index method
 *
 * @param string $season
 * @return void
 */
	public function index($season = null) {
		$this->Assignment->recursive = 0;

		if ($season == null) {
			$season = sprintf('%d', ((idate('m') < 8) ? (idate('Y') - 1) : idate('Y')));
		}

		$this->set('assignments', $this->paginate());
		$this->set('season', $season);
	}

/**
 * view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function view($id = null) {
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Invalid assignment'));
		}
		$this->set('assignment', $this->Assignment->read(null, $id));
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->Assignment->create();
			if ($this->Assignment->save($this->request->data)) {
				$this->Session->setFlash(__('The assignment has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The assignment could not be saved. Please, try again.'));
			}
		}
		$seasons = $this->Assignment->Season->find('list');
		$this->set(compact('seasons'));
	}

/**
 * edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function edit($id = null) {
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Invalid assignment'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->Assignment->save($this->request->data)) {
				$this->Session->setFlash(__('The assignment has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The assignment could not be saved. Please, try again.'));
			}
		} else {
			$this->request->data = $this->Assignment->read(null, $id);
		}
		$seasons = $this->Assignment->Season->find('list');
		$this->set(compact('seasons'));
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
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Invalid assignment'));
		}
		if ($this->Assignment->delete()) {
			$this->Session->setFlash(__('Assignment deleted'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Assignment was not deleted'));
		$this->redirect(array('action' => 'index'));
	}

/**
 * assignments_index method
 *
 * @return void
 */
	public function assignments_index() {
		$this->Assignment->recursive = 0;
		$this->set('assignments', $this->paginate());
	}

/**
 * assignments_view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function assignments_view($id = null) {
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Invalid assignment'));
		}
		$this->set('assignment', $this->Assignment->read(null, $id));
	}

/**
 * assignments_add method
 *
 * @return void
 */
	public function assignments_add() {
		if ($this->request->is('post')) {
			$this->Assignment->create();
			if ($this->Assignment->save($this->request->data)) {
				$this->Session->setFlash(__('The assignment has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The assignment could not be saved. Please, try again.'));
			}
		}
		$seasons = $this->Assignment->Season->find('list');
		$this->set(compact('seasons'));
	}

/**
 * assignments_edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function assignments_edit($id = null) {
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Invalid assignment'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->Assignment->save($this->request->data)) {
				$this->Session->setFlash(__('The assignment has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The assignment could not be saved. Please, try again.'));
			}
		} else {
			$this->request->data = $this->Assignment->read(null, $id);
		}
		$seasons = $this->Assignment->Season->find('list');
		$this->set(compact('seasons'));
	}

/**
 * assignments_delete method
 *
 * @throws MethodNotAllowedException
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function assignments_delete($id = null) {
		if (!$this->request->is('post')) {
			throw new MethodNotAllowedException();
		}
		$this->Assignment->id = $id;
		if (!$this->Assignment->exists()) {
			throw new NotFoundException(__('Invalid assignment'));
		}
		if ($this->Assignment->delete()) {
			$this->Session->setFlash(__('Assignment deleted'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Assignment was not deleted'));
		$this->redirect(array('action' => 'index'));
	}
}

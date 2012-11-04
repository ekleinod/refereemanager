<?php
App::uses('AppController', 'Controller');
/**
 * Teams Controller
 *
 * @property Team $Team
 */
class TeamsController extends AppController {

	/**
	 * Index method: show all teams of a specific season, current season if none given.
	 *
	 * @param string $season
	 * @return void
	 */
	public function index() {
		$this->Team->recursive = 0;
		$this->set('teams', $this->paginate());
	}

/**
 * view method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function view($id = null) {
		$this->Team->id = $id;
		if (!$this->Team->exists()) {
			throw new NotFoundException(__('Invalid team'));
		}
		$this->set('team', $this->Team->read(null, $id));
	}

/**
 * add method
 *
 * @return void
 */
	public function add() {
		if ($this->request->is('post')) {
			$this->Team->create();
			if ($this->Team->save($this->request->data)) {
				$this->Session->setFlash(__('The team has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The team could not be saved. Please, try again.'));
			}
		}
		$addresses = $this->Team->Address->find('list');
		$clubs = $this->Team->Club->find('list');
		$leagues = $this->Team->League->find('list');
		$this->set(compact('addresses', 'clubs', 'leagues'));
	}

/**
 * edit method
 *
 * @throws NotFoundException
 * @param string $id
 * @return void
 */
	public function edit($id = null) {
		$this->Team->id = $id;
		if (!$this->Team->exists()) {
			throw new NotFoundException(__('Invalid team'));
		}
		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->Team->save($this->request->data)) {
				$this->Session->setFlash(__('The team has been saved'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('The team could not be saved. Please, try again.'));
			}
		} else {
			$this->request->data = $this->Team->read(null, $id);
		}
		$addresses = $this->Team->Address->find('list');
		$clubs = $this->Team->Club->find('list');
		$leagues = $this->Team->League->find('list');
		$this->set(compact('addresses', 'clubs', 'leagues'));
	}

}

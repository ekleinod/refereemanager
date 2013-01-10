<?php
App::uses('AppController', 'Controller');
/**
 * Teams Controller
 *
 * @property Team $Team
 */
class TeamsController extends AppController {

	/**
	 * Index method: show all assignments of a specific season, current season if none given.
	 *
	 * @param string $season
	 * @return void
	 */
	public function index($season = null) {
		// select season
		if ($season == null) {
			$season = sprintf('%d', ((idate('m') < 8) ? (idate('Y') - 1) : idate('Y')));
		}
		$options['conditions'] = array(
//			'Season.year_start' => $season
		);

		// find matching teams
		$teams = $this->Team->find('all', $options);
		foreach ($teams as &$team) {
			if (!$team['Team']['name']) {
				$team['Team']['name'] = $this->Team->getTitle($team);
			}
		}

		$this->set('teams', $teams);
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
		$this->Team->id = $id;
		if (!$this->Team->exists()) {
			throw new NotFoundException(__('Invalid team'));
		}

		$team = $this->Team->read(null, $id);
		$team['Team']['title_team'] = $this->Team->getTitle($team);
		$this->set('team', $team);
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
		$assignments = $this->Team->Assignment->find('list');
		$seasons = $this->Team->Season->find('list');
		$leagues = $this->Team->League->find('list');
		$people = $this->Team->Person->find('list');
		$this->set(compact('addresses', 'clubs', 'assignments', 'seasons', 'leagues', 'people'));
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
		$assignments = $this->Team->Assignment->find('list');
		$seasons = $this->Team->Season->find('list');
		$leagues = $this->Team->League->find('list');
		$people = $this->Team->Person->find('list');
		$this->set(compact('addresses', 'clubs', 'assignments', 'seasons', 'leagues', 'people'));
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
		$this->Team->id = $id;
		if (!$this->Team->exists()) {
			throw new NotFoundException(__('Invalid team'));
		}
		if ($this->Team->delete()) {
			$this->Session->setFlash(__('Team deleted'));
			$this->redirect(array('action' => 'index'));
		}
		$this->Session->setFlash(__('Team was not deleted'));
		$this->redirect(array('action' => 'index'));
	}
}

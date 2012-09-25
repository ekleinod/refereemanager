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

		// select season
		if ($season == null) {
			$season = sprintf('%d', ((idate('m') < 8) ? (idate('Y') - 1) : idate('Y')));
		}
		$options['conditions'] = array(
			'Season.year_start' => $season
		);

		// find matching assignments
		$assignments = $this->Assignment->find('all', $options);

		// reconfigure array for easy access of values
		foreach ($assignments as &$assignment):
			foreach ($assignment['Team'] as $team):
				if ($team['TeamAssignment']['home']) {
					$assignment['HomeTeam'] = $team;
				} else {
					$assignment['RoadTeam'] = $team;
				}
			endforeach;
			foreach ($assignment['Referee'] as $referee):
				if ($referee['RefereeAssignment']['referee_assignment_role_id'] == 1) {
					$assignment['Umpire'] = $referee;
				}
				if ($referee['RefereeAssignment']['referee_assignment_role_id'] == 2) {
					$assignment['Standby'] = $referee;
				}
				if ($referee['RefereeAssignment']['referee_assignment_role_id'] == 3) {
					if (!array_key_exists('SimpleReferee', $assignment)) {
						$assignment['SimpleReferee'] = array();
					}
					$assignment['SimpleReferee'][] = $referee;
				}
			endforeach;
		endforeach;

		// pass selected items to view
		$this->set('assignments', $assignments);
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

}

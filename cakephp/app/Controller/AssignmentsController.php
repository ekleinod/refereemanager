<?php
App::uses('AppController', 'Controller');
/**
 * Assignments Controller
 *
 * @property Assignment $Assignment
 */
class AssignmentsController extends AppController {

	private $refereeroles = NULL;

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
			'Season.year_start' => $season
		);

		// find matching assignments
		$assignments = $this->Assignment->find('all', $options);

		// load Person model
		$this->loadModel('Person');

		// reconfigure array for easy access of values
		foreach ($assignments as &$assignment):

			// home and road team
			foreach ($assignment['Team'] as $team):
				if ($team['TeamAssignment']['home']) {
					$assignment['HomeTeam'] = $team;
				} else {
					$assignment['RoadTeam'] = $team;
				}
			endforeach;

			// referees
			foreach ($assignment['Referee'] as &$referee):
				// person data
				$referee['Person'] = $this->Person->findById($referee['person_id']);

				// assignment roles
				foreach ($this->getRefereeRoles() as $refereerole):
					if ($referee['RefereeAssignment']['referee_assignment_role_id'] == $refereerole['id']) {
						if (!array_key_exists($refereerole['code'], $assignment)) {
							$assignment[$refereerole['code']] = array();
						}
						$assignment[$refereerole['code']][] = $referee;
					}
				endforeach;
			endforeach;
		endforeach;

		// pass selected items to view
		$this->set('assignments', $assignments);
		$this->set('season', $season);
		$this->set('refereeroles', $this->getRefereeRoles());
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
		$this->set('refereeroles', $this->getRefereeRoles());
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
	 * Returns available referee roles.
	 *
	 */
	private function getRefereeRoles() {
		if ($this->refereeroles == NULL) {
			$this->loadModel('RefereeAssignmentRole');
			$wholerefereeroles = $this->RefereeAssignmentRole->find('all');
			$this->refereeroles = array();
			foreach ($wholerefereeroles as $refereerole):
				$this->refereeroles[] = $refereerole['RefereeAssignmentRole'];
			endforeach;
		}
		return $this->refereeroles;
	}

}

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
	 * @param $season season to show teams for
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
	 * View method: show the team with the given id.
	 *
	 * @param $id id of team
	 * @return void
	 */
	public function view($id = null) {
		$this->Team->id = $id;
		if (!$this->Team->exists()) {
			throw new NotFoundException(__('Team nicht vorhanden'));
		}

		$team = $this->Team->read(null, $id);
		$team['Team']['title_team'] = $this->Team->getTitle($team);

		// get changes
		$changes = $this->getChanges($team);;

		// pass information to view
		$this->set('team', $team);
		$this->set('changes', $changes);
	}

	/**
	 * Add method: add new team.
	 *
	 * @return void
	 */
	public function add() {
		if ($this->request->is('post')) {
			$this->Team->create();
			if ($this->Team->save($this->request->data)) {
				$this->Session->setFlash(__('Das Team wurde gespeichert.'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Das Team konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}
		}

		$this->prepareAndSetAddEdit();
	}

	/**
	 * Edit method: edit the assignment with the given id.
	 *
	 * @param $id id of assignment
	 * @return void
	 */
	public function edit($id = null) {
		$this->Team->id = $id;

		if (!$this->Team->exists()) {
			throw new NotFoundException(__('Team nicht vorhanden'));
		}

		if ($this->request->is('post') || $this->request->is('put')) {
			if ($this->Team->save($this->request->data)) {
				$this->Session->setFlash(__('Das Team wurde gespeichert.'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Das Team konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}
		} else {
			$this->request->data = $this->Team->read(null, $id);
		}

		$this->prepareAndSetAddEdit();
	}

	/**
	 * Delete method: show the team with the given id and ask for deletion or delete team.
	 *
	 * @param $id id of team
	 * @return void
	 */
	public function delete($id = null) {
		$this->Team->id = $id;
		if (!$this->Team->exists()) {
			throw new NotFoundException(__('Team nicht vorhanden'));
		}

		if ($this->request->is('post') || $this->request->is('put')) {
//		if ($this->Team->delete()) {
			if (false) {
				$this->Session->setFlash(__('Das Team wurde gelöscht.'));
				$this->redirect(array('action' => 'index'));
			} else {
				$this->Session->setFlash(__('Das Team konnte nicht gelöscht werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}
		}

		$team = $this->Team->read(null, $id);
		$team['Team']['title_team'] = $this->Team->getTitle($team);

		// get changes
		$changes = $this->getChanges($team);;

		// pass information to view
		$this->set('team', $team);
		$this->set('changes', $changes);
	}

	/**
	 * Prepares data for add/edit view and sets it.
	 *
	 */
	private function prepareAndSetAddEdit() {

		$clubs = $this->Team->Club->find('list');
		asort($clubs, SORT_LOCALE_STRING);
		$leagues = $this->Team->League->find('list');
		asort($leagues, SORT_LOCALE_STRING);
		$seasons = $this->Team->Season->find('list');
		asort($seasons, SORT_LOCALE_STRING);
		$people = $this->Team->Person->find('list');
		asort($people, SORT_LOCALE_STRING);
		$addresses = $this->Team->Address->find('list');
		asort($addresses, SORT_LOCALE_STRING);

		// pass information to view
		$this->set(compact('clubs'));
		$this->set(compact('leagues'));
		$this->set(compact('seasons'));
		$this->set(compact('people'));
		$this->set(compact('addresses'));

		$this->set('team', $this->Team->data);
	}

	/**
	 * Returns changes for the selected team.
	 *
	 * @param $assignment team to get changes for
	 * @return array with changes
	 */
	private function getChanges($team) {

		// initialize return array
		$changes = array();

		// load ActivityLog model
		$this->loadModel('ActivityLog');

		// to do (see assignments)

		// return results
		return $changes;
	}

}

<?php

App::uses('AppController', 'Controller');

/**
 * Seasons Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.3
 */
class SeasonsController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeForm');

	/** Models. */
	public $uses = array('Season');

	/**
	 * Defines actions to perform before the action method is executed.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		$this->Season->recursive = -1;
	}

	/**
	 * Index method.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function index() {

		$this->setAndGetStandardIndex();

		$this->set('title_for_layout', __('Übersicht der Saisons'));
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

		$this->Season->id = $id;
		if (!$this->Season->exists()) {
			throw new NotFoundException(__('Saison mit der ID \'%s\' existiert nicht.', $id));
		}
		$season = $this->Season->read(null, $id);

		$this->setAndGetStandardNewAddView($season);
		$this->set('title_for_layout', __('Detailanzeige Saison'));
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

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Season', $this->request->data)) {

			$tmpData = $this->request->data;

			$this->Season->create();
			if ($this->Season->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Die Saison wurde gespeichert.'));
				$this->redirect(array('action' => 'edit', $this->Season->id));
			} else {
				$this->Session->setFlash(__('Die Saison konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}

		}

		$this->setAndGetStandardNewAddView();
		$this->set('title_for_layout', __('Saison anlegen'));
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

		if ($this->request->is('post') && !empty($this->request->data) && array_key_exists('Season', $this->request->data)) {

			$tmpData = $this->request->data;

			if ($this->Season->saveAssociated($tmpData)) {
				$this->Session->setFlash(__('Die geänderte Saison wurde gespeichert.'));
			} else {
				$this->Session->setFlash(__('Die geänderte Saison konnte nicht gespeichert werden.') . ' ' . __('Bitte versuchen Sie es noch einmal.'));
			}

			$this->redirect(array('action' => 'edit', $this->Season->id));

		}

		$this->Season->id = $id;
		if (!$this->Season->exists()) {
			throw new NotFoundException(__('Die Saison mit der ID \'%s\' existiert nicht.', $id));
		}

		$season = $this->Season->read(null, $id);

		$this->setAndGetStandardNewAddView($season);
		$this->set('title_for_layout', __('Saison editieren'));
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

		$seasons = $this->getSeasons();
		$this->set('seasons', $seasons);
	}

	/**
	 * Set and get standard values for new, add, view.
	 *
	 * @param $season season
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandardNewAddView(&$season = null) {

		$this->setAndGetStandard();

		if ($season === null) {
			$this->set('season', array());
		} else {
			$this->set('season', $season);
			$this->set('id', $season['Season']['id']);
		}

	}

	/**
	 * Set and get standard values.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function setAndGetStandard() {
		$this->set('controller', 'Seasons');
	}

	/**
	 * Returns the seasons.
	 *
	 * @return array of seasons
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function getSeasons() {
		$seasons = $this->Season->getSeasons();
		return $seasons;
	}

}

/* EOF */


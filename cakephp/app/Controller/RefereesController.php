<?php

App::uses('AppController', 'Controller');

/**
 * Referees Controller
 *
 * @property Referee $Referee
 */
class RefereesController extends AppController {

	/**
	 * index method
	 *
	 * @return void
	 */
	public function index() {
		// find referees
		$referees = $this->Referee->find('all');
		//asort($referees, SORT_LOCALE_STRING);

		// pass information to view
		$this->set('referees', $referees);
		$this->set('referees', array());

		// set title
		$this->set('title_for_layout', __('Übersicht der Schiedsrichter'));
	}

}

/* EOF */


<?php

App::uses('AppController', 'Controller');

/**
 * Referees Controller
 *
 * @property Referee $Referee
 */
class RefereesController extends AppController {

	/** Needed helper classes. */
	public $helpers = array('PhpExcel');

	/**
	 * index method
	 *
	 * @return void
	 */
	public function index($export = null) {
		// find referees
		$referees = $this->Referee->find('all');
		usort($referees, array('RefereesController', 'compareTo'));

		// pass information to view
		$this->set('referees', $referees);
		$this->set('export', $export);

		// set title
		$this->set('title_for_layout', __('Übersicht der Schiedsrichter'));
	}

	/**
	 * Compare two objects.
	 *
	 * @param a first object
	 * @param b second object
	 * @return comparison result
	 *  @retval -1 a<b
	 *  @retval 0 a==b
	 *  @retval 1 a>b
	 */
	public static function compareTo($a, $b) {

		// first criterion: name
		if ($a['Person']['name'] < $b['Person']['name']) {
			return -1;
		}
		if ($a['Person']['name'] > $b['Person']['name']) {
			return 1;
		}

		// second criterion: first name
		if ($a['Person']['first_name'] < $b['Person']['first_name']) {
			return -1;
		}
		if ($a['Person']['first_name'] > $b['Person']['first_name']) {
			return 1;
		}

		// equal
		return 0;
	}

}

/* EOF */


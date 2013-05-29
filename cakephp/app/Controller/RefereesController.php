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
	 */
	public function index() {

		$referees = $this->getReferees();

		// pass information to view
		$this->set('referees', $referees);
		$this->set('statustypes', $this->getStatusTypes($referees));

		// set title
		$this->set('title_for_layout', __('Übersicht der Schiedsrichter'));
	}

	/**
	 * export method
	 *
	 * @param type export type (default: excel)
	 */
	public function export($type = 'excel') {

		$referees = $this->getReferees();

		// pass information to view
		$this->set('referees', $referees);
		$this->set('statustypes', $this->getStatusTypes($referees));
		$this->set('type', $type);

		// set title
		$this->set('title_for_layout', __('Export der Schiedsrichter'));
	}

	/**
	 * Returns the referees.
	 *
	 * @return array of referees
	 */
	private function getReferees() {
		// find referees
		$referees = $this->Referee->find('all');
		usort($referees, array('RefereesController', 'compareTo'));

		return $referees;
	}

	/**
	 * Returns the stytus types used by the referees.
	 *
	 * @return array of status types
	 */
	private function getStatusTypes($referees = array()) {
		$statustypes = array();

		foreach ($referees as $referee) {
			if (!array_key_exists($referee['StatusType']['id'], $statustypes)) {
				$statustypes[$referee['StatusType']['id']] = $referee['StatusType'];
			}
		}

		ksort($statustypes);

		return $statustypes;
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


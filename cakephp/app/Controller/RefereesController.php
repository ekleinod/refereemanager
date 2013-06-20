<?php

App::uses('AppController', 'Controller');

/**
 * Referees Controller
 *
 * @property Referee $Referee
 */
class RefereesController extends AppController {

	/** Needed helper classes. */
	public $helpers = array('PHPExcel');

	/**
	 * index method
	 */
	public function index() {

		$referees = $this->getReferees();

		// pass information to view
		$this->set('referees', $referees);
		$this->set('statustypes', $this->getStatusTypes($referees));
		$this->set('contacttypes', $this->getContactTypes());
		$this->set('defaultcontacttypeid', $this->getDefaultContactTypeID());

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
		$this->set('contacttypes', $this->getContactTypes());
		$this->set('defaultcontacttypeid', $this->getDefaultContactTypeID());
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

		// member club
		$this->loadModel('RefereeRelationType');
		$this->RefereeRelationType->recursive = -1;
		$memberRelationType = $this->RefereeRelationType->findBySid('member');
		$memberRelationTypeID = $memberRelationType['RefereeRelationType']['id'];

		// add club, picture, contacts
		$this->loadModel('Club');
		$this->Club->recursive = -1;
		$this->loadModel('Picture');
		$this->Picture->recursive = -1;
		$this->loadModel('Contact');

		foreach ($referees as &$referee) {

			// club
			foreach ($referee['RefereeRelation'] as $refereeRelation) {
				if ($refereeRelation['referee_relation_type_id'] == $memberRelationTypeID) {
					if ($refereeRelation['club_id'] > 0) {
						$memberClub = $this->Club->findById($refereeRelation['club_id']);
						$referee['Club'] = $memberClub['Club'];
					}
				}
			}

			// picture
			$picture = $this->Picture->findByPersonId($referee['Person']['id']);
			if ($picture) {
				$referee['Picture'] = $picture['Picture'];
			}

			// contacts
			$contacts = $this->Contact->findAllByPersonId($referee['Person']['id']);
			$contactkinds = array('Address', 'Email', 'Url', 'PhoneNumber');
			foreach ($contacts as $contact) {
				foreach ($contactkinds as $contactkind) {
					if ($contact[$contactkind]) {
						$referee['Contact'][$contactkind][$contact['ContactType']['id']][] = $contact[$contactkind][0];
					}
				}
			}
		}

		return $referees;
	}

	/**
	 * Returns the status types used by the referees.
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
	 * Returns the contact types.
	 *
	 * @return array of contact types
	 */
	private function getContactTypes() {
		$this->loadModel('ContactType');
		$this->ContactType->recursive = -1;
		$contacttypes = array();
		foreach ($this->ContactType->find('all') as $contacttype) {
			$contacttypes[$contacttype['ContactType']['id']] = $contacttype['ContactType'];
		}
		return $contacttypes;
	}

	/**
	 * Returns the default contact type id.
	 *
	 * @return default contact type id
	 */
	private function getDefaultContactTypeID() {
		$this->loadModel('ContactType');
		$this->ContactType->recursive = -1;
		$contacttype = $this->ContactType->find('first', array('order' => array('id')));
		return $contacttype['ContactType']['id'];
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


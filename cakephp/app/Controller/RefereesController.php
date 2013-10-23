<?php

App::uses('AppController', 'Controller');
App::uses('RefManRefereeFormat', 'Utility');

/**
 * Referees Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class RefereesController extends AppController {

	/** Needed helper classes. */
	public $helpers = array('PHPExcel', 'RefereeFormat', 'RefereeForm');

	/** Sex types. */
	private static $sextypes = null;

	/** Sex type array. */
	private static $sextypearray = null;

	/** Referee relation types. */
	private static $refereerelationtypes = null;

	/** Club array. */
	private static $clubarray = null;

	/**
	 * Index method.
	 *
	 * @param season season to export (default: null == current season)
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function index($season = null) {

		$this->setAndGetStandardIndexExport();

		$this->set('title_for_layout', __('Übersicht der Schiedsrichter_innen'));
	}

	/**
	 * Export method.
	 *
	 * @param season season to export (default: null == current season)
	 * @param type export type (default: excel)
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function export($season = null, $type = 'excel') {

		$this->setAndGetStandardIndexExport();

		$this->set('type', $type);

		$this->set('title_for_layout', __('Export der Schiedsrichter_innen'));
	}

	/**
	 * Set and get standard values for index and export.
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function setAndGetStandardIndexExport($season = null) {

		$referees = $this->getReferees();

		$this->loadModel('Season');
		$theSeason = $this->Season->getSeason($season);

		$this->set('referees', $referees);
		$this->set('season', $theSeason);
//		$this->set('statustypes', $this->getStatusTypes($referees));
//		$this->set('sextypes', $this->getSexTypes());
//		$this->set('contacttypes', $this->getContactTypes());
	}

	/**
	 * View method: show the referee with the given id.
	 *
	 * @param $id id of referee
	 * @return void
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function view($id = null) {

		$this->Referee->id = $id;
		if (!$this->Referee->exists()) {
			throw new NotFoundException(__('Schiedsrichter_in mit der ID \'%s\' existiert nicht.', $id));
		}
		$referee = $this->Referee->read(null, $id);

		$this->setAndGetStandardNewAddView($referee);
		$this->set('title_for_layout', __('Detailanzeige Schiedsrichter%s %s', ($referee['Person']['sex_type_sid'] === 'f') ? 'in' : '', RefManRefereeFormat::formatPerson($referee['Person'], 'fullname')));
	}

	/**
	 * Edi method: edit the referee with the given id.
	 *
	 * @param $id id of referee
	 * @return void
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function edit($id = null) {

		$this->Referee->id = $id;
		if (!$this->Referee->exists()) {
			throw new NotFoundException(__('Schiedsrichter_in mit der ID \'%s\' existiert nicht.', $id));
		}
		$referee = $this->Referee->read(null, $id);

		$this->setAndGetStandardNewAddView($referee);

		$this->set('title_for_layout', __('Schiedsrichter%s %s editieren', ($referee['Person']['sex_type_sid'] === 'f') ? 'in' : '', RefManRefereeFormat::formatPerson($referee['Person'], 'fullname')));
	}

	/**
	 * Set and get standard values for new, add, view.
	 *
	 * @param $referee referee
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function setAndGetStandardNewAddView(&$referee) {

		$sextypes = $this->getSexTypes();
		$referee['Person']['sex_type_sid'] = $sextypes[$referee['Person']['sex_type_id']]['sid'];

		// pass information to view
		$this->set('referee', $referee);
		$this->set('sextypes', $sextypes);
		$this->set('sextypearray', $this->getSexTypeArray());
		$this->set('contacttypes', $this->getContactTypes());
		$this->set('refereerelationtypes', $this->getRefereeRelationTypes());
		$this->set('clubarray', $this->getClubArray());

		$this->set('id', $referee['Referee']['id']);
	}

	/**
	 * Returns the referees.
	 *
	 * @return array of referees
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getReferees() {
		// find referees
		$referees = $this->Referee->find('all');
		usort($referees, array('RefereesController', 'compareTo'));

		// member club
		$memberRelationTypeID = $this->getMemberRelationTypeID();

		// add club, picture, contacts
		$this->loadModel('Club');
		$this->Club->recursive = -1;
		$this->loadModel('Picture');
		$this->Picture->recursive = -1;
		$this->loadModel('Contact');
		$this->loadModel('TrainingLevelType');
		$this->TrainingLevelType->recursive = -1;
		$this->loadModel('TrainingUpdate');
		$this->TrainingUpdate->recursive = -1;

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

			// training level type
			foreach ($referee['TrainingLevel'] as $trainingLevel) {
				$trainingLevelType = $this->TrainingLevelType->findById($trainingLevel['training_level_type_id']);
				$useLevel = empty($referee['TrainingLevelInfo']);
				if (!$useLevel) {
					$useLevel = $trainingLevelType['TrainingLevelType']['rank'] > $referee['TrainingLevelInfo']['rank'];
				}
				if ($useLevel) {
					$referee['TrainingLevelInfo'] = $trainingLevelType['TrainingLevelType'];
					$referee['TrainingLevelInfo']['training_level_id'] = $trainingLevel['id'];
					if (!empty($trainingLevel['since'])) {
						$referee['TrainingLevelInfo']['since'] = $trainingLevel['since'];
					}
				}
			}

			// last training update
			$trainingupdate = $this->TrainingUpdate->findByTrainingLevelId($referee['TrainingLevelInfo']['training_level_id'], array(), array('TrainingUpdate.update' => 'desc'));
			if (!empty($trainingupdate) && !empty($trainingupdate['TrainingUpdate'])) {
				$referee['TrainingLevelInfo']['lastupdate'] = $trainingupdate['TrainingUpdate']['update'];
			}
			if (!empty($referee['TrainingLevelInfo']['since'])) {
				if (empty($referee['TrainingLevelInfo']['lastupdate']) || (strtotime($referee['TrainingLevelInfo']['since']) > strtotime($referee['TrainingLevelInfo']['lastupdate']))) {
					$referee['TrainingLevelInfo']['lastupdate'] = $referee['TrainingLevelInfo']['since'];
				}
			}


			// next training update
			if (!empty($referee['TrainingLevelInfo']['lastupdate'])) {
				$referee['TrainingLevelInfo']['nextupdate'] = strtotime('+2 years', strtotime($referee['TrainingLevelInfo']['lastupdate']));
			}
		}

		return $referees;
	}

	/**
	 * Returns the status types used by the referees.
	 *
	 * @return array of status types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getStatusTypes($referees = array()) {
		$statustypes = array();

		foreach ($referees as $referee) {
			if (!array_key_exists($referee['StatusType']['id'], $statustypes)) {
				$statustypes[$referee['StatusType']['id']] = $referee['StatusType'];
			}
			if ($statustypes[$referee['StatusType']['id']]['is_special']) {
				$statustypes[$referee['StatusType']['id']]['referees'][] = $referee['Person'];
			}
		}

		ksort($statustypes);

		return $statustypes;
	}

	/**
	 * Returns the contact types.
	 *
	 * @return array of contact types
	 *
	 * @version 0.1
	 * @since 0.1
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
	 * Returns the sex types.
	 *
	 * @todo: maybe this method (and the other methods using static variables)
	 * can be static too, but at the moment I don't know how to load and use models static
	 *
	 * @return array of sex types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getSexTypes() {
		if (empty(RefereesController::$sextypes)) {
			$this->loadModel('SexType');
			$this->SexType->recursive = -1;
			RefereesController::$sextypes = array();
			foreach ($this->SexType->find('all') as $sextype) {
				RefereesController::$sextypes[$sextype['SexType']['id']] = $sextype['SexType'];
			}
		}
		return RefereesController::$sextypes;
	}

	/**
	 * Returns the sex type array for use in select fields.
	 *
	 * @return array of sex types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getSexTypeArray() {
		if (empty(RefereesController::$sextypearray)) {
			$this->loadModel('SexType');
			$this->SexType->recursive = -1;
			RefereesController::$sextypearray = $this->SexType->find('list');
		}
		return RefereesController::$sextypearray;
	}

	/**
	 * Returns the club array for use in select fields.
	 *
	 * @return array of club
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getClubArray() {
		if (empty(RefereesController::$clubarray)) {
			$this->loadModel('Club');
			$this->Club->recursive = -1;
			RefereesController::$clubarray = $this->Club->find('list');
			asort(RefereesController::$clubarray, SORT_LOCALE_STRING);
		}
		return RefereesController::$clubarray;
	}

	/**
	 * Returns the referee relation types.
	 *
	 * @todo: maybe this method (and the other methods using static variables)
	 * can be static too, but at the moment I don't know how to load and use models static
	 *
	 * @return array of referee relation types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getRefereeRelationTypes() {
		if (empty(RefereesController::$refereerelationtypes)) {
			$this->loadModel('RefereeRelationType');
			$this->RefereeRelationType->recursive = -1;
			RefereesController::$refereerelationtypes = array();
			foreach ($this->RefereeRelationType->find('all') as $refereerelationtype) {
				RefereesController::$refereerelationtypes[$refereerelationtype['RefereeRelationType']['id']] = $refereerelationtype['RefereeRelationType'];
			}
		}
		return RefereesController::$refereerelationtypes;
	}

	/**
	 * Returns the member relation type id.
	 *
	 * @return member relation type id
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getMemberRelationTypeID() {
		$this->loadModel('RefereeRelationType');
		$this->RefereeRelationType->recursive = -1;
		$memberRelationType = $this->RefereeRelationType->findBySid('member');
		return $memberRelationType['RefereeRelationType']['id'];
	}

	/**
	 * Returns the training level types.
	 *
	 * @return array of training level types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getTrainingLevelTypes() {
		$this->loadModel('TrainingLevelType');
		$this->TrainingLevelType->recursive = -1;
		$trainingleveltypes = array();
		foreach ($this->TrainingLevelType->find('all') as $trainingleveltype) {
			$trainingleveltypes[$trainingleveltype['TrainingLevelType']['id']] = $trainingleveltype['TrainingLevelType'];
		}
		return $trainingleveltypes;
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
	 *
	 * @version 0.1
	 * @since 0.1
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


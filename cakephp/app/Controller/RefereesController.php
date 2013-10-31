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

	/** Helper classes. */
	public $helpers = array('PHPExcel', 'RefereeFormat', 'RefereeForm');

	/** Models. */
	public $uses = array('Referee', 'League', 'RefereeRelationType');

	/**
	 * Defines actions to perform before the action method is executed.
	 */
	public function beforeFilter() {
		parent::beforeFilter();
		$this->League->recursive = -1;
		$this->RefereeRelationType->recursive = -1;
	}

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

		$this->loadModel('Season');
		$theSeason = $this->Season->getSeason($season);

		$referees = $this->getReferees($theSeason);

		$this->set('referees', $referees);
		$this->set('season', $theSeason);
		$this->set('statustypes', $this->getStatusTypes($referees, $theSeason));
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
	 * @param $season season
	 * @return array of referees
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getReferees($season) {
		// find referees
		$referees = $this->Referee->find('all');
		usort($referees, array('RefereesController', 'compareTo'));

		// clean referee array
		$arrReturn = array();

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
		$this->loadModel('StatusType');
		$this->StatusType->recursive = -1;

		foreach ($referees as $referee) {

			$refTemp = array();

			$useReferee = false;
			foreach ($referee['RefereeStatus'] as $refereestatus) {
				if ($refereestatus['season_id'] == $season['id']) {
					$useReferee = true;
					$temp = $this->StatusType->findById($refereestatus['status_type_id']);
					$refTemp['RefereeStatus'] = $temp['StatusType'];
				}
			}

			if ($useReferee) {

				$refTemp['Referee'] = $referee['Referee'];
				$refTemp['Person'] = $referee['Person'];

				$refTemp['RefereeRelation'] = array();
				foreach ($referee['RefereeRelation'] as $refereeRelation) {
					if ($refereeRelation['referee_relation_type_id'] == $this->RefereeRelationType->getRelationTypeID(RefereeRelationType::SID_MEMBER)) {
						$refTemp['RefereeRelation'][RefereeRelationType::SID_MEMBER] = $this->Club->findById($refereeRelation['club_id']);
					}
					if ($refereeRelation['referee_relation_type_id'] == $this->RefereeRelationType->getRelationTypeID(RefereeRelationType::SID_REFFOR)) {
						$memberClub = $this->Club->findById($refereeRelation['club_id']);
						$refTemp['RefereeRelation'][RefereeRelationType::SID_REFFOR] = $this->Club->findById($refereeRelation['club_id']);
					}
					if ($refereeRelation['referee_relation_type_id'] == $this->RefereeRelationType->getRelationTypeID(RefereeRelationType::SID_PREFER)) {
						if ($refereeRelation['club_id'] > 0) {
							$refTemp['RefereeRelation'][RefereeRelationType::SID_PREFER][] = $this->Club->findById($refereeRelation['club_id']);
						}
						if ($refereeRelation['league_id'] > 0) {
							$refTemp['RefereeRelation'][RefereeRelationType::SID_PREFER][] = $this->League->findById($refereeRelation['league_id']);
						}
					}
					if ($refereeRelation['referee_relation_type_id'] == $this->RefereeRelationType->getRelationTypeID(RefereeRelationType::SID_NOASSIGNMENT)) {
						if ($refereeRelation['club_id'] > 0) {
							$refTemp['RefereeRelation'][RefereeRelationType::SID_NOASSIGNMENT][] = $this->Club->findById($refereeRelation['club_id']);
						}
						if ($refereeRelation['league_id'] > 0) {
							$refTemp['RefereeRelation'][RefereeRelationType::SID_NOASSIGNMENT][] = $this->League->findById($refereeRelation['league_id']);
						}
					}
				}

/*
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
*/
//				$refTemp = $referee;

				$arrReturn[] = $refTemp;
			}

		}

		return $arrReturn;
	}

	/**
	 * Returns the status types used by the referees.
	 *
	 * @param $referees referees
	 * @param $season season
	 * @return array of status types
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function getStatusTypes($referees, $season) {
		$this->loadModel('StatusType');
		$this->StatusType->recursive = -1;

		$statustypes = array();
		foreach ($this->StatusType->find('all') as $statustype) {
			$statustypes[$statustype['StatusType']['id']] = $statustype['StatusType'];
		}
		ksort($statustypes);

		foreach ($referees as $referee) {
//			if ($statustypes[$referee['StatusType']['id']]['is_special']) {
//				$statustypes[$referee['RefereeStatus']['id']]['referees'][] = $referee['Person'];
//			}
		}

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
		$this->loadModel('SexType');
		$this->SexType->recursive = -1;

		$sextypes = array();
		foreach ($this->SexType->find('all') as $sextype) {
			$sextypes[$sextype['SexType']['id']] = $sextype['SexType'];
		}
		return $sextypes;
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
		$this->loadModel('SexType');
		$this->SexType->recursive = -1;
		return $this->SexType->find('list');
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
		$this->loadModel('Club');
		$this->Club->recursive = -1;

		$clubarray = $this->Club->find('list');
		asort($clubarray, SORT_LOCALE_STRING);

		return $clubarray;
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
		$this->loadModel('RefereeRelationType');
		$this->RefereeRelationType->recursive = -1;

		$refereerelationtypes = array();
		foreach ($this->RefereeRelationType->find('all') as $refereerelationtype) {
			$refereerelationtypes[$refereerelationtype['RefereeRelationType']['id']] = $refereerelationtype['RefereeRelationType'];
		}

		return $refereerelationtypes;
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


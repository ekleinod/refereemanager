<?php

App::uses('AppModel', 'Model');

/**
 * Referee Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class Referee extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		// I can't get this one working with other tables
		$this->virtualFields['display_referee'] = sprintf(
			'CONCAT(%1$s.id, "-", %1$s.person_id)',
			$this->alias
		);
	}

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'Referee';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_referee';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'person_id' => array('isUnique', 'notempty', 'numeric'),
		'docs_per_letter' => array('boolean'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('Person');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('RefereeAssignment', 'RefereeRelation', 'RefereeStatus', 'TrainingLevel');

	// custom programming

	/**
	 * Returns referees for a given season (or all referees if no season is given).
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param season season, referees have to be associated with
	 * @return array of referees for given season, empty if there are none
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function getReferees($season) {

		$referees = $this->find('all');
		usort($referees, array('Referee', 'compareTo'));

		$arrReturn = array();
		foreach ($referees as $referee) {

			$useReferee = false;

			foreach ($referee['RefereeStatus'] as $refereestatus) {
				$useReferee |= ($refereestatus['season_id'] == $season['id']);
			}

			if ($useReferee) {

				$refTemp = $this->fillReferee($referee, $season);

				if ($refTemp != null) {
					$arrReturn[] = $refTemp;
				}

			}

		}

		return $arrReturn;
	}

	/**
	 * Compare two objects.
	 *
	 * @param a first object
	 * @param b second object
	 * @return comparison result
	 *  @retval <0 a<b
	 *  @retval 0 a==b
	 *  @retval >0 a>b
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public static function compareTo($a, $b) {

		// first criterion: name
		$compResult = strcasecmp($a['Person']['name'], $b['Person']['name']);
		if ($compResult != 0) {
			return $compResult;
		}

		// second criterion: first name
		$compResult = strcasecmp($a['Person']['first_name'], $b['Person']['first_name']);
		if ($compResult != 0) {
			return $compResult;
		}

		// equal
		return 0;
	}

	/**
	 * Fills the referee with the needed data.
	 *
	 * @param $referee referee to use
	 * @param $season season (null if season should be ignored
	 * @return referee
	 * 	@retval null if the referee is not active this season
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	private function fillReferee($referee, $season) {

		$modelClub = ClassRegistry::init('Club');
		$modelClub->recursive = -1;
		$modelContact = ClassRegistry::init('Contact');
		$modelLeague = ClassRegistry::init('League');
		$modelLeague->recursive = -1;
		$modelPicture = ClassRegistry::init('Picture');
		$modelPicture->recursive = -1;
		$modelRefereeRelationType = ClassRegistry::init('RefereeRelationType');
		$modelRefereeRelationType->recursive = -1;
		$modelSexType = ClassRegistry::init('SexType');
		$modelSexType->recursive = -1;
		$modelStatusType = ClassRegistry::init('StatusType');
		$modelStatusType->recursive = -1;
		$modelTrainingUpdate = ClassRegistry::init('TrainingUpdate');
		$modelTrainingUpdate->recursive = -1;
		$modelTrainingLevelType = ClassRegistry::init('TrainingLevelType');
		$modelTrainingLevelType->recursive = -1;

		$refTemp = array();

		// referee status
		foreach ($referee['RefereeStatus'] as $refereestatus) {
			if ($season == null) {
				$refTemp['RefereeStatus'][] = $refereestatus;
			} else {
				if ($refereestatus['season_id'] == $season['id']) {
					$temp = $modelStatusType->findById($refereestatus['status_type_id']);
					$refTemp['RefereeStatus'] = $temp['StatusType'];
				}
			}
		}

		$refTemp['Referee'] = $referee['Referee'];
		$refTemp['Person'] = $referee['Person'];

		// referee relations
		$refTemp['RefereeRelation'] = array();
		foreach ($referee['RefereeRelation'] as $refereeRelation) {
			if ($refereeRelation['club_id'] > 0) {
				$refTemp['RefereeRelation'][$modelRefereeRelationType->getRelationTypeSID($refereeRelation['referee_relation_type_id'])][] = $modelClub->findById($refereeRelation['club_id']);
			}
			if ($refereeRelation['league_id'] > 0) {
				$refTemp['RefereeRelation'][$modelRefereeRelationType->getRelationTypeSID($refereeRelation['referee_relation_type_id'])][] = $modelLeague->findById($refereeRelation['league_id']);
			}
			if ($refereeRelation['sex_type_id'] > 0) {
				$refTemp['RefereeRelation'][$modelRefereeRelationType->getRelationTypeSID($refereeRelation['referee_relation_type_id'])][] = $modelSexType->findById($refereeRelation['sex_type_id']);
			}
			if (!empty($refereeRelation['saturday'])) {
				$refTemp['RefereeRelation'][$modelRefereeRelationType->getRelationTypeSID($refereeRelation['referee_relation_type_id'])][]['Saturday'] = 'saturday';
			}
			if (!empty($refereeRelation['sunday'])) {
				$refTemp['RefereeRelation'][$modelRefereeRelationType->getRelationTypeSID($refereeRelation['referee_relation_type_id'])][]['Sunday'] = 'sunday';
			}
			if (!empty($refereeRelation['remark'])) {
				$refTemp['RefereeRelation'][$modelRefereeRelationType->getRelationTypeSID($refereeRelation['referee_relation_type_id'])][]['Remark'] = $refereeRelation['remark'];
			}
		}

		// picture
		$picture = $modelPicture->findByPersonId($referee['Person']['id']);
		if ($picture) {
			$refTemp['Picture'] = $picture['Picture'];
		}

		// contacts
		$contacts = $modelContact->findAllByPersonId($referee['Person']['id']);
		$contactkinds = array('Address', 'Email', 'Url', 'PhoneNumber');
		foreach ($contacts as $contact) {
			foreach ($contactkinds as $contactkind) {
				if ($contact[$contactkind]) {
					$contact[$contactkind][0]['contact_type'] = $contact['ContactType']['id'];
					$contact[$contactkind][0]['is_primary'] = $contact['Contact']['is_primary'];
					$refTemp['Contact'][$contactkind][$contact['ContactType']['id']][] = $contact[$contactkind][0];
				}
			}
		}

		// training level type
		foreach ($referee['TrainingLevel'] as $trainingLevel) {
			$trainingLevelType = $modelTrainingLevelType->findById($trainingLevel['training_level_type_id']);
			$useLevel = empty($referee['TrainingLevelInfo']);
			if (!$useLevel) {
				$useLevel = $trainingLevelType['TrainingLevelType']['rank'] > $referee['TrainingLevelInfo']['rank'];
			}
			if ($useLevel) {
				$refTemp['TrainingLevelInfo'] = $trainingLevelType['TrainingLevelType'];
				$refTemp['TrainingLevelInfo']['training_level_id'] = $trainingLevel['id'];
				if (!empty($trainingLevel['since'])) {
					$refTemp['TrainingLevelInfo']['since'] = $trainingLevel['since'];
				}
			}
		}

		// last training update
		$trainingupdate = $modelTrainingUpdate->findByTrainingLevelId($refTemp['TrainingLevelInfo']['training_level_id'], array(), array('TrainingUpdate.update' => 'desc'));
		if (!empty($trainingupdate) && !empty($trainingupdate['TrainingUpdate'])) {
			$refTemp['TrainingLevelInfo']['lastupdate'] = $trainingupdate['TrainingUpdate']['update'];
		}
		if (!empty($refTemp['TrainingLevelInfo']['since'])) {
			if (empty($refTemp['TrainingLevelInfo']['lastupdate']) || (strtotime($refTemp['TrainingLevelInfo']['since']) > strtotime($refTemp['TrainingLevelInfo']['lastupdate']))) {
				$refTemp['TrainingLevelInfo']['lastupdate'] = $refTemp['TrainingLevelInfo']['since'];
			}
		}

		// next training update
		if (!empty($refTemp['TrainingLevelInfo']['lastupdate'])) {
			$updateTime = 3;
			if ($refTemp['TrainingLevelInfo']['sid'] == TrainingLevelType::SID_ASSUMP) {
				$updateTime = 2;
			}
			$refTemp['TrainingLevelInfo']['nextupdate'] = strtotime(sprintf('+%d years', $updateTime), strtotime($refTemp['TrainingLevelInfo']['lastupdate']));
		}

		// sex type
		$temp = $modelSexType->findById($refTemp['Person']['sex_type_id']);
		$refTemp['SexType'] = $temp['SexType'];

		return $refTemp;

	}

	// field access functions (no doc needed, hopefully)

	/** Name. */
	public static function getName($referee) {
		return $referee['Person']['name'];
	}

	/** Primary email.*/
	public static function getPrimaryEmail($referee) {
		$arrEmails = Referee::getEmails($referee);

		if (count($arrEmails) > 0) {

			if (count($arrEmails) == 1) {
				return $arrEmails[0];
			}

			foreach (Referee::getEmails($referee) as $email) {
				if ($email['is_primary']) {
					return $email;
				}
			}

		}

		return null;
	}

	/** All email addresses. */
	public static function getEmails($referee) {
		$arrReturn = array();

		if (array_key_exists('Contact', $referee) && array_key_exists('Email', $referee['Contact'])) {
			foreach ($referee['Contact']['Email'] as $contacttype => $emailkind) {
				foreach ($emailkind as $email) {
					$arrReturn[] = $email;
				}
			}
		}

		return $arrReturn;
	}

}

/* EOF */


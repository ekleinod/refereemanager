<?php

App::uses('AppModel', 'Model');
App::uses('CakeTime', 'View/Helper');

/**
 * Referee Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class Referee extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.6
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		// I can't get this one working with other tables
		$this->virtualFields['display_title'] = sprintf(
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
	 * @version 0.6
	 * @since 0.1
	 */
	public $displayField = 'display_title';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'person_id' => array('isUnique', 'notblank', 'numeric'),
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
	 * @param viewVars view vars
	 * @return array of referees for given season, empty if there are none
	 *
	 * @version 0.4
	 * @since 0.1
	 */
	public function getReferees($season, $viewVars) {

		// sorting
		$referees = $this->find('all');
		usort($referees, array('Person', 'compareTo'));

		$arrReturn = array();
		foreach ($referees as &$referee) {

			$useReferee = false;
			foreach ($referee['RefereeStatus'] as $refereestatus) {
				$useReferee |= ($refereestatus['season_id'] == $season['Season']['id']);
			}

			if ($useReferee) {
				$this->fillReferee($referee, $viewVars);
				$arrReturn[] = $referee;
			}

		}

		return $arrReturn;
	}

	/**
	 * Returns referee for a given id.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param id referee id
	 * @param viewVars view vars
	 * @return referee for given id, null if there is none
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public function getRefereeById($id, $viewVars) {

		$referee = $this->findById($id);

		if (empty($referee)) {
			return null;
		}

		$this->fillReferee($referee, $viewVars);

		return $referee;
	}

	/**
	 * Returns referee id for a given person id.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param personid person id
	 * @return referee id for given person, null if there is none
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public function getRefereeIdByPersonId($personid) {

		$refereeid = $this->find(
														 'first',
														 array(
																	 'conditions' => array('person_id' => $personid),
																	 'recursive' => -1,
																	 'fields' => 'id',
																	 )
														 );

		if (empty($refereeid)) {
			return null;
		}

		return $refereeid['Referee']['id'];
	}

	/**
	 * Fills referee with missing data.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param referee referee
	 * @param viewVars view vars
	 *
	 * @version 0.6
	 * @since 0.1
	 */
	public function fillReferee(&$referee, $viewVars) {

		// save values from person data except person and referee
		$person = $this->Person->findById($referee['Referee']['person_id']);
		foreach ($person as $key => $value) {
			if (($key !== 'Person') &&
					($key !== 'Referee')) {
				$referee[$key] = $value;
			}
		}

		// remove person data depending on access rights
		if (!$viewVars['isEditor']) {
			$referee['Person']['sex_type_id'] = null;
			$referee['Person']['birthday'] = null;
			$referee['Person']['dayofdeath'] = null;
			$referee['Person']['remark'] = null;
			$referee['Person']['internal_remark'] = null;
			$referee['Person']['docs_per_letter'] = null;
		}

		// remove picture depending on access rights
		if (!$viewVars['isReferee']) {
			$referee['Picture'] = array();
		}

		// expand referee relations
		$arrTemp = $referee['RefereeRelation'];
		$referee['RefereeRelation'] = array();
		foreach ($arrTemp as $tmpValue) {
			$referee['RefereeRelation'][$tmpValue['id']] = $this->RefereeRelation->findById($tmpValue['id']);
		}

		// expand referee status
		$arrTemp = $referee['RefereeStatus'];
		$referee['RefereeStatus'] = array();
		foreach ($arrTemp as $tmpValue) {
			$referee['RefereeStatus'][$tmpValue['id']] = $this->RefereeStatus->findById($tmpValue['id']);
		}

		// expand training level
		$referee['TrainingLevel'] = $this->TrainingLevel->findAllByRefereeId($referee['Referee']['id'],
																																				 array(),
																																				 array('TrainingLevelType.rank'));
		if (!empty($referee['TrainingLevel'])) {
			// only last level for anonymous
			if (!$viewVars['isReferee']) {
				$referee['TrainingLevel'] = array($referee['TrainingLevel'][count($referee['TrainingLevel']) - 1]);
			}

			// training updates
			$modelTrainingUpdate = ClassRegistry::init('TrainingUpdate');
			foreach($referee['TrainingLevel'] as &$traininglevel) {
				$traininglevel['TrainingUpdate'] = $modelTrainingUpdate->findAllByTrainingLevelId($traininglevel['TrainingLevel']['id'],
																																					 null,
																																					 array('TrainingUpdate.update'),
																																					 null,
																																					 1,
																																					 -1);

				// only last update for anonymous
				if (!empty($traininglevel['TrainingUpdate']) && !$viewVars['isReferee']) {
					$traininglevel['TrainingUpdate'] = array($traininglevel['TrainingUpdate'][count($traininglevel['TrainingUpdate']) - 1]);
				}
			}
		}

		// new training level info: highest TrainingLevelType, last update, next update
		$referee['TrainingLevelInfo'] = array('TrainingLevelType' => null, 'last_update' => null, 'next_update' => null);
		$tmpLevel = (empty($referee['TrainingLevel'])) ? null : $referee['TrainingLevel'][count($referee['TrainingLevel']) - 1];
		if (!empty($tmpLevel)) {
			$referee['TrainingLevelInfo']['TrainingLevelType'] = $tmpLevel['TrainingLevelType'];
			if ($viewVars['isReferee']) {
				$tmpUpdate = (empty($tmpLevel['TrainingUpdate'])) ? null : $tmpLevel['TrainingUpdate'][count($tmpLevel['TrainingUpdate']) - 1];
				$referee['TrainingLevelInfo']['last_update'] = empty($tmpUpdate) ? $tmpLevel['TrainingLevel']['since'] : $tmpUpdate['TrainingUpdate']['update'];
				if (!empty($referee['TrainingLevelInfo']['last_update'])) {
					$referee['TrainingLevelInfo']['next_update'] = strftime('%Y-%m-%d',
																																	strtotime(sprintf('+%d years %s',
																																										$tmpLevel['TrainingLevelType']['update_interval'],
																																										$referee['TrainingLevelInfo']['last_update'])));
				}
			}
		}

		// expand and process contacts
		$arrTemp = $referee['Contact'];
		$referee['Contact'] = array();
		if ($viewVars['isReferee']) {
			$modelContact = ClassRegistry::init('Contact');
			foreach (array_keys($modelContact->hasOne) as $cType) {
				if (($cType !== 'Address') || $viewVars['isEditor']) {
					$referee['Contact'][$cType] = array();
				}
			}
			foreach ($arrTemp as $tmpValue) {
				if (empty($tmpValue['editor_only']) || $viewVars['isEditor']) {
					$cTemp = $modelContact->findById($tmpValue['id']);
					foreach (array_keys($modelContact->hasOne) as $cType) {
						if (($cType !== 'Address') || $viewVars['isEditor']) {
							if (!empty($cTemp[$cType]['id'])) {
								$referee['Contact'][$cType][$tmpValue['id']] = array();
								$referee['Contact'][$cType][$tmpValue['id']]['Contact'] = $cTemp['Contact'];
								$referee['Contact'][$cType][$tmpValue['id']][$cType] = $cTemp[$cType];
								break;
							}
						}
					}
				}
			}
		}

	}

}

/* EOF */


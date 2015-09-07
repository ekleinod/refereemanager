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
	 * @param isEditor is editor?
	 * @return array of referees for given season, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function getReferees($season, $isEditor) {

		$referees = $this->find('all');
		usort($referees, array('Person', 'compareTo'));

		$arrReturn = array();
		foreach ($referees as &$referee) {

			$useReferee = false;
			foreach ($referee['RefereeStatus'] as $refereestatus) {
				$useReferee |= ($refereestatus['season_id'] == $season['Season']['id']);
			}

			if ($useReferee) {
				$this->fillReferee($referee, $isEditor);
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
	 * @param isEditor is user editor?
	 * @return referee for given id, null if there is none
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public function getRefereeById($id, $isEditor) {

		$referee = $this->findById($id);

		if (empty($referee)) {
			return null;
		}

		$this->fillReferee($referee, $isEditor);

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
	 * @param isEditor is editor?
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function fillReferee(&$referee, $isEditor) {

		// save values from person data except person and referee
		$person = $this->Person->findById($referee['Referee']['person_id']);
		foreach ($person as $key => $value) {
			if ((strcmp($key, 'Person') != 0) &&
					(strcmp($key, 'Referee') != 0)) {
				$referee[$key] = $value;
			}
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
		$arrTemp = $referee['TrainingLevel'];
		$referee['TrainingLevel'] = array();
		foreach ($arrTemp as $tmpValue) {
			$referee['TrainingLevel'][$tmpValue['id']] = $this->TrainingLevel->findById($tmpValue['id']);
		}

		// expand contacts and remove contacts for editor only if needed
		$modelContact = ClassRegistry::init('Contact');
		$arrTemp = $referee['Contact'];
		$referee['Contact'] = array();
		foreach ($arrTemp as $tmpValue) {
			if (empty($tmpValue['editor_only']) || $isEditor) {
				$referee['Contact'][$tmpValue['id']] = $modelContact->findById($tmpValue['id']);
			}
		}

	}

}

/* EOF */


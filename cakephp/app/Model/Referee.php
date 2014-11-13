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
	 * @version 0.3
	 * @since 0.1
	 */
	public function getReferees($season) {

		$referees = $this->find('all');
		usort($referees, array('Person', 'compareTo'));

		$arrReturn = array();
		foreach ($referees as &$referee) {

			$useReferee = false;
			foreach ($referee['RefereeStatus'] as $refereestatus) {
				$useReferee |= ($refereestatus['season_id'] == $season['id']);
			}

			if ($useReferee) {
				$this->fillReferee($referee);
				$arrReturn[] = $referee;
			}

		}

		return $arrReturn;
	}

	/**
	 * Fills referee with missing data.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param referee referee
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function fillReferee(&$referee) {
		$person = $this->Person->findById($referee['Referee']['person_id']);
		foreach ($person as $key => $value) {
			if ((strcmp($key, 'Person') != 0) && (strcmp($key, 'Referee') != 0)) {
				$referee[$key] = $value;
			}
		}
	}

}

/* EOF */


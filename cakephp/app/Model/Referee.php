<?php

App::uses('AppModel', 'Model');

/**
 * Referee Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
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
	 * @version 0.1
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array(
			'uuid' => array(
				'rule' => array('uuid'),
			),
		),
		'person_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
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
	 * @param season season, referees have to be associated with (null == current season)
	 * @return array of referees for given season, empty if there are none
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function getReferees($season = null) {
		$referees = $this->find('all');
		usort($referees, array('Referee', 'compareTo'));

		if ($season == null) {
			return $referees;
		}

		$arrReturn = array();
		foreach ($referees as $referee) {

			$useReferee = false;

			foreach ($referee['RefereeStatus'] as $refereestatus) {
				$useReferee |= ($refereestatus['season_id'] == $season['id']);
			}

			if ($useReferee) {
				$arrReturn[] = $referee;
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


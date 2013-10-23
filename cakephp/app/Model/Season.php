<?php

App::uses('AppModel', 'Model');

/**
 * Season Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class Season extends AppModel {

	/**
	 * Declare virtual field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['title_season'] = sprintf(
			'CONCAT(%1$s.year_start, "/", %1$s.year_start + 1)',
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
	public $name = 'Season';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'title_season';

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
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'year_start' => array(
			'notempty' => array(
				'rule' => array('notempty'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('LeagueGame', 'LeaguePlannedReferee', 'RefereeReport', 'TeamSeason',
		'RefereeStatusStart' => array(
			'className' => 'RefereeStatus',
			'foreignKey' => 'start_season_id',
			'dependent' => false,
			'conditions' => '',
			'fields' => '',
			'order' => '',
			'limit' => '',
			'offset' => '',
			'exclusive' => '',
			'finderQuery' => '',
			'counterQuery' => ''
		),
		'RefereeStatusEnd' => array(
			'className' => 'RefereeStatus',
			'foreignKey' => 'end_season_id',
			'dependent' => false,
			'conditions' => '',
			'fields' => '',
			'order' => '',
			'limit' => '',
			'offset' => '',
			'exclusive' => '',
			'finderQuery' => '',
			'counterQuery' => ''
		),
	);

	/**
	 * Returns season for given start year.
	 *
	 * @param year start year of season (null == current season)
	 * @return season for given start year, null if not present
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function getSeason($year = null) {
		// maybe later when I understand how to find things in a static method
		if ($year == null) {
			$year = sprintf('%d', ((idate('m') < 8) ? (idate('Y') - 1) : idate('Y')));
		}

		$this->recursive = -1;
		$ssnReturn = $this->findByYearStart($year);

		if ($ssnReturn == null) {
			return null;
		}

		return $ssnReturn['Season'];
	}

}

/* EOF */


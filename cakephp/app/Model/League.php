<?php

App::uses('AppModel', 'Model');

/**
 * League Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class League extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_league'] = sprintf(
			'CONCAT(%1$s.title, " (", %1$s.abbreviation, ")")',
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
	public $name = 'League';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_league';

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
		'title' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'abbreviation' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'league_type_id' => array(
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
	public $belongsTo = array('LeagueType');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('LeagueGame',  'LeaguePlannedReferee', 'RefereeRelation', 'RefereeReport', 'TeamSeason');

}

/* EOF */


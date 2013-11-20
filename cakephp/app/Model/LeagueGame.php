<?php

App::uses('AppModel', 'Model');

/**
 * LeagueGame Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class LeagueGame extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		// I can't get this one working with season, league
		$this->virtualFields['display_league_game'] = sprintf(
			'CONCAT(%1$s.id, "-", %1$s.game_number)',
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
	public $name = 'LeagueGame';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_league_game';

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
		'assignment_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'game_number' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'season_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'league_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'address_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'filled_referee_report_url' => array(
			'url' => array(
				'rule' => array('url'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('Assignment', 'Season', 'League', 'Address');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('LeagueGameTeam');

}

/* EOF */


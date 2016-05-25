<?php

App::uses('AppModel', 'Model');

/**
 * TeamSeason Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class TeamSeason extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		// I can't get this one working with other tables
		$this->virtualFields['display_team_season'] = sprintf(
			'CONCAT(%1$s.id, "-", %1$s.team_id, "-", %1$s.season_id, "-", %1$s.league_id)',
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
	public $name = 'TeamSeason';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_team_season';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'team_id' => array('notblank', 'numeric'),
		'season_id' => array('notblank', 'numeric'),
		'league_id' => array('notblank', 'numeric'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('Team', 'Season', 'League');

	/**
	 * hasMany associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $hasMany = array('LeagueGameTeam', 'Spokesperson', 'TeamVenue');

}

/* EOF */


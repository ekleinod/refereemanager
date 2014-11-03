<?php

App::uses('AppModel', 'Model');

/**
 * LeagueGameTeam Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class LeagueGameTeam extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		// I can't get this one working with other tables
		$this->virtualFields['display_league_game_team'] = sprintf(
			'CONCAT(%1$s.id, "-", %1$s.league_game_id, "-", %1$s.league_game_team_type_id)',
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
	public $name = 'LeagueGameTeam';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_league_game_team';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'league_game_id' => array('notempty', 'numeric'),
		'league_game_team_type_id' => array('notempty', 'numeric'),
		'team_season_id' => array('notempty', 'numeric'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('LeagueGame', 'LeagueGameTeamType', 'TeamSeason');

}

/* EOF */


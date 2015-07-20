<?php

App::uses('AppModel', 'Model');

/**
 * LeagueGame Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
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
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'assignment_id' => array('isUnique', 'notempty', 'numeric'),
		'game_number' => array('notempty', 'numeric'),
		'season_id' => array('notempty', 'numeric'),
		'league_id' => array('notempty', 'numeric'),
		'team_venue_id' => array('numeric'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $belongsTo = array('Assignment', 'Season', 'League', 'TeamVenue');

	/**
	 * hasMany associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $hasMany = array('LeagueGameTeam');

}

/* EOF */


<?php
App::uses('AppModel', 'Model');
/**
 * LeagueGameTeam Model
 *
 * @property LeagueGame $LeagueGame
 * @property LeagueGameTeamType $LeagueGameTeamType
 * @property TeamSeason $TeamSeason
 */
class LeagueGameTeam extends AppModel {

/**
 * Display field
 *
 * @var string
 */
	public $displayField = 'id';

/**
 * Validation rules
 *
 * @var array
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
		'league_game_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'league_game_team_type_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'team_season_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
	);

	//The Associations below have been created with all possible keys, those that are not needed can be removed

/**
 * belongsTo associations
 *
 * @var array
 */
	public $belongsTo = array(
		'LeagueGame' => array(
			'className' => 'LeagueGame',
			'foreignKey' => 'league_game_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		),
		'LeagueGameTeamType' => array(
			'className' => 'LeagueGameTeamType',
			'foreignKey' => 'league_game_team_type_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		),
		'TeamSeason' => array(
			'className' => 'TeamSeason',
			'foreignKey' => 'team_season_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		)
	);
}

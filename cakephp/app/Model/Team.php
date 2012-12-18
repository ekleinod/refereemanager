<?php
App::uses('AppModel', 'Model');
/**
 * Team Model
 *
 */
class Team extends AppModel {

	/**
	 * Declare virtual field in constructor to be alias-safe.
	 *
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);

		// I can't get this one working, so I program a simple (but possibly bad) workaround
//		$this->virtualFields['title_team'] = sprintf(
//			'CONCAT(Club.name, IF(%1$s.number > 0, CONCAT(" ", %1$s.number), ""))',
//			$this->alias
//		);
		$this->virtualFields['title_team'] = sprintf(
			'CONCAT(%1$s.id, IF(%1$s.number > 0, CONCAT(" #", %1$s.number), ""))',
			$this->alias
		);
	}

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @var string
	 */
	public $name = 'Team';

	/**
	 * Display field
	 *
	 * @var string
	 */
	public $displayField = 'title_team';

	/**
	 * Validation rules
	 *
	 * @var array
	 */
	public $validate = array(
		'number' => array(
			'numeric' => array(
				'rule' => array('numeric'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
			'notempty' => array(
				'rule' => array('notempty'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'club_id' => array(
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

	/**
	 * belongsTo associations
	 *
	 * @var array
	 */
	public $belongsTo = array('Address', 'Club');

	/**
	 * hasAndBelongsToMany associations
	 *
	 * @var array
	 */
	public $hasAndBelongsToMany = array(
		'Assignment' => array(
			'joinTable' => 'team_assignments',
			'unique' => 'keepExisting'
		),
		'Season' => array(
			'joinTable' => 'team_seasons_leagues_spokespeople',
			'unique' => 'keepExisting'
		),
		'League' => array(
			'joinTable' => 'team_seasons_leagues_spokespeople',
			'unique' => 'keepExisting'
		),
		'Person' => array(
			'joinTable' => 'team_seasons_leagues_spokespeople',
			'unique' => 'keepExisting'
		)
	);

	/**
	 * Returns title for given team.
	 *
	 * @param $theTeam team
	 * @return title
	 */
	public static function getTitle($theTeam) {

		if (is_null($theTeam['Team']['name'])) {
			if (array_key_exists('Club', $theTeam)) {
				$name = is_null($theTeam['Club']['shortname']) ? $theTeam['Club']['name'] : $theTeam['Club']['shortname'];
			} else {
				$name = $theTeam['Team']['id'];
			}
		} else {
			$name = $theTeam['Team']['name'];
		}

		$number = ($theTeam['Team']['number'] == 0) ? '' : ' ' . $theTeam['Team']['number'];

		return $name . $number;

	}

}

/* EOF */


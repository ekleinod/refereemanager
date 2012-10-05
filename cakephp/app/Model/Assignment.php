<?php

App::uses('AppModel', 'Model');

/**
 * Assignment Model
 *
 * @property Season $Season
 * @property Referee $Referee
 * @property Team $Team
 */
class Assignment extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @var string
	 */
	public $name = 'Assignment';

	/**
	 * Display field
	 *
	 * @var string
	 */
	public $virtualFields = array("title_assignment" => "CONCAT(Assignment.id, ' #', Assignment.game_number)");
	public $displayField = 'title_assignment';

	/**
	 * Validation rules
	 *
	 * @var array
	 */
	public $validate = array(
		'game_number' => array(
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
		'datetime' => array(
			'datetime' => array(
				'rule' => array('datetime'),
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
		'season_id' => array(
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
	public $belongsTo = array('Season', 'League', 'Address');

	/**
	 * hasAndBelongsToMany associations
	 *
	 * @var array
	 */
	public $hasAndBelongsToMany = array(
		'Referee' => array(
			'joinTable' => 'referee_assignments',
			'unique' => 'keepExisting'
		),
		'Team' => array(
			'joinTable' => 'team_assignments',
			'unique' => 'keepExisting'
		)
	);

}

<?php

App::uses('AppModel', 'Model');

/**
 * Referee Model
 *
 * @property Person $Person
 * @property StatusType $StatusType
 * @property RefereeAssignment $RefereeAssignment
 * @property RefereeRelation $RefereeRelation
 * @property TrainingLevel $TrainingLevel
 */
class Referee extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		// I can't get this one working with persons name, so I program a simple (but possibly bad) workaround
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
	 * @var string
	 */
	public $name = 'Referee';

	/**
	 * Display field
	 *
	 * @var string
	 */
	public $displayField = 'display_referee';


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
		'person_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'status_type_id' => array(
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
	public $belongsTo = array('Person', 'StatusType');

	/**
	 * hasMany associations
	 *
	 * @var array
	 */
	public $hasMany = array('RefereeAssignment', 'RefereeRelation', 'TrainingLevel');

}

/* EOF */


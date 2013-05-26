<?php
App::uses('AppModel', 'Model');
/**
 * RefereeAssignment Model
 *
 * @property Assignment $Assignment
 * @property RefereeAssignmentType $RefereeAssignmentType
 * @property Referee $Referee
 * @property AssignmentStatusType $AssignmentStatusType
 * @property AssignmentRemark $AssignmentRemark
 */
class RefereeAssignment extends AppModel {

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
		'assignment_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'referee_assignment_type_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'referee_id' => array(
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
		'Assignment' => array(
			'className' => 'Assignment',
			'foreignKey' => 'assignment_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		),
		'RefereeAssignmentType' => array(
			'className' => 'RefereeAssignmentType',
			'foreignKey' => 'referee_assignment_type_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		),
		'Referee' => array(
			'className' => 'Referee',
			'foreignKey' => 'referee_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		),
		'AssignmentStatusType' => array(
			'className' => 'AssignmentStatusType',
			'foreignKey' => 'assignment_status_type_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		)
	);

/**
 * hasMany associations
 *
 * @var array
 */
	public $hasMany = array(
		'AssignmentRemark' => array(
			'className' => 'AssignmentRemark',
			'foreignKey' => 'referee_assignment_id',
			'dependent' => false,
			'conditions' => '',
			'fields' => '',
			'order' => '',
			'limit' => '',
			'offset' => '',
			'exclusive' => '',
			'finderQuery' => '',
			'counterQuery' => ''
		)
	);

}

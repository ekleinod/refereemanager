<?php
App::uses('AppModel', 'Model');
/**
 * AssignmentRemark Model
 *
 * @property RefereeAssignment $RefereeAssignment
 * @property AssignmentRemarkType $AssignmentRemarkType
 */
class AssignmentRemark extends AppModel {

/**
 * Use table
 *
 * @var mixed False or table name
 */
	public $useTable = 'assignment_remark';

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
		'referee_assignment_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'assignment_remark_type_id' => array(
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
		'RefereeAssignment' => array(
			'className' => 'RefereeAssignment',
			'foreignKey' => 'referee_assignment_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		),
		'AssignmentRemarkType' => array(
			'className' => 'AssignmentRemarkType',
			'foreignKey' => 'assignment_remark_type_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		)
	);
}

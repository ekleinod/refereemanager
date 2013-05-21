<?php
App::uses('AppModel', 'Model');
/**
 * RefereeAssignment Model
 *
 * @property Assignment $Assignment
 * @property RefereeAssignmentRole $RefereeAssignmentRole
 * @property Referee $Referee
 */
class RefereeAssignment extends AppModel {

/**
 * Validation rules
 *
 * @var array
 */
	public $validate = array(
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
		'referee_assignment_role_id' => array(
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
		'RefereeAssignmentRole' => array(
			'className' => 'RefereeAssignmentRole',
			'foreignKey' => 'referee_assignment_role_id',
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
		)
	);

}

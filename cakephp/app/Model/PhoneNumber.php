<?php
App::uses('AppModel', 'Model');
/**
 * PhoneNumber Model
 *
 * @property ContactKind $ContactKind
 * @property Person $Person
 */
class PhoneNumber extends AppModel {

/**
 * Display field
 *
 * @var string
 */
	public $displayField = 'number';

/**
 * Validation rules
 *
 * @var array
 */
	public $validate = array(
		'number' => array(
			'notempty' => array(
				'rule' => array('notempty'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
			'phone' => array(
				'rule' => array('phone', '/^\d{3,5} \d{5,10}$/'),
				'message' => 'Die Telefonnummer muss folgende Form haben: "#### ##########".',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'contact_kind_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
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
	);

/**
 * belongsTo associations
 *
 * @var array
 */
	public $belongsTo = array(
		'Person' => array(
			'className' => 'Person',
			'foreignKey' => 'person_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		),
		'ContactKind' => array(
			'className' => 'ContactKind',
			'foreignKey' => 'contact_kind_id',
			'conditions' => '',
			'fields' => '',
			'order' => ''
		)
	);
}

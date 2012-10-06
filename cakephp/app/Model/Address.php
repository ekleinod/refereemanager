<?php
App::uses('AppModel', 'Model');
/**
 * Address Model
 *
 * @property Club $Club
 * @property Person $Person
 * @property Team $Team
 */
class Address extends AppModel {

	/**
	 * Declare virtual field in constructor to be alias-safe.
	 *
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['title_address'] = sprintf('CONCAT(%s.street, " ", %s.number, ", ", %s.zip_code, " ", %s.city)', $this->alias, $this->alias, $this->alias, $this->alias);
	}

	/**
	 * Display field
	 *
	 * @var string
	 */
//	public $virtualFields = array("title_address" => "CONCAT(Address.street, ' ', Address.number, ', ', Address.zip_code, ' ', Address.city)");
	public $displayField = 'title_address';

/**
 * Validation rules
 *
 * @var array
 */
	public $validate = array(
		'street' => array(
			'notempty' => array(
				'rule' => array('notempty'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'number' => array(
			'notempty' => array(
				'rule' => array('notempty'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'zip_code' => array(
			'postal' => array(
				'rule' => array('postal'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'city' => array(
			'notempty' => array(
				'rule' => array('notempty'),
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
 * hasMany associations
 *
 * @var array
 */
	public $hasMany = array(
		'Club' => array(
			'className' => 'Club',
			'foreignKey' => 'address_id',
			'dependent' => false,
			'conditions' => '',
			'fields' => '',
			'order' => '',
			'limit' => '',
			'offset' => '',
			'exclusive' => '',
			'finderQuery' => '',
			'counterQuery' => ''
		),
		'Person' => array(
			'className' => 'Person',
			'foreignKey' => 'address_id',
			'dependent' => false,
			'conditions' => '',
			'fields' => '',
			'order' => '',
			'limit' => '',
			'offset' => '',
			'exclusive' => '',
			'finderQuery' => '',
			'counterQuery' => ''
		),
		'Team' => array(
			'className' => 'Team',
			'foreignKey' => 'address_id',
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

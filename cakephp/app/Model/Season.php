<?php
App::uses('AppModel', 'Model');
/**
 * Season Model
 *
 * @property Assignment $Assignment
 * @property TeamSpokesperson $TeamSpokesperson
 * @property UmpireReportRecipient $UmpireReportRecipient
 */
class Season extends AppModel {

/**
 * Display field
 *
 * @var string
 */
	public $displayField = 'year_start';

/**
 * Validation rules
 *
 * @var array
 */
	public $validate = array(
		'year_start' => array(
			'date' => array(
				'rule' => array('date'),
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
	);

	//The Associations below have been created with all possible keys, those that are not needed can be removed

/**
 * hasMany associations
 *
 * @var array
 */
	public $hasMany = array(
		'Assignment' => array(
			'className' => 'Assignment',
			'foreignKey' => 'season_id',
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
		'TeamSpokesperson' => array(
			'className' => 'TeamSpokesperson',
			'foreignKey' => 'season_id',
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
		'UmpireReportRecipient' => array(
			'className' => 'UmpireReportRecipient',
			'foreignKey' => 'season_id',
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

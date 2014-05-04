<?php

App::uses('AppModel', 'Model');

/**
 * Address Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class Address extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_address'] = sprintf(
			'CONCAT(%1$s.street, " ", %1$s.number, ", ", %1$s.zip_code, " ", %1$s.city)',
			$this->alias
		);
	}

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'Address';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_address';

	/**
	 * Validation rules
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array(
			'uuid' => array(
				'rule' => array('uuid'),
			),
		),
		'contact_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'street' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'number' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'city' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('Contact');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('Club', 'LeagueGame', 'Team', 'Tournament');

}

/* EOF */


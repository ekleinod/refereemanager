<?php

App::uses('AppModel', 'Model');

/**
 * Contact Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class Contact extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'Contact';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'id';

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
		'is_primary' => array(
			'boolean' => array(
				'rule' => array('boolean'),
			),
		),
		'contact_type_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'person_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('ContactType', 'Person');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('Address', 'Email', 'PhoneNumber', 'Url');

}

/* EOF */


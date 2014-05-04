<?php

App::uses('AppModel', 'Model');

/**
 * StatusType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class StatusType extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'StatusType';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'title';

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
		'sid' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'title' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('Referee');

	// custom programming

	/* Status types. */
	const SID_MANY = 'many';
	const SID_NORMAL = 'normal';
	const SID_INACTIVESEASON = 'inactiveseason';
	const SID_OTHER = 'other';

}

/* EOF */


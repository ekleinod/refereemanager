<?php

App::uses('AppModel', 'Model');

/**
 * Picture Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class Picture extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'Picture';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'url';

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
		'person_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'url' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
			'url' => array(
				'rule' => array('url'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('Person');

}

/* EOF */


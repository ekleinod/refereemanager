<?php

App::uses('AppModel', 'Model');

/**
 * PhoneNumber Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class PhoneNumber extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_phone'] = sprintf(
			'CONCAT("+" , %1$s.country_code, " ", %1$s.area_code, " ", %1$s.number)',
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
	public $name = 'PhoneNumber';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_phone';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'contact_id' => array('isUnique', 'notblank', 'numeric'),
		'country_code' => array('notblank', 'numeric'),
		'area_code' => array('notblank', 'numeric'),
		'number' => array('notblank', 'numeric'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('Contact');

}

/* EOF */


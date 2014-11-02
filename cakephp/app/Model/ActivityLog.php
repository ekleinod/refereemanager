<?php

App::uses('AppModel', 'Model');

/**
 * ActivityLog Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class ActivityLog extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'ActivityLog';

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
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'database_table_id' => array('notempty', 'numeric'),
		'database_column_id' => array('notempty', 'numeric'),
		'row_id' => array('notempty', 'numeric'),
		'new_value' => array('notempty'),
		'user_id' => array('notempty', 'numeric'),
		'created' => array('datetime'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('DatabaseColumn', 'DatabaseTable', 'User');

}

/* EOF */


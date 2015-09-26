<?php

App::uses('AppModel', 'Model');

/**
 * WishType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.6
 */
class WishType extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public $name = 'WishType';

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_title'] = sprintf(
			'%1$s.title',
			$this->alias
		);
	}

	/**
	 * Display field
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public $displayField = 'display_title';

	/**
	 * Validation rules
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'sid' => array('isUnique', 'notblank'),
		'title' => array('isUnique', 'notblank'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public $hasMany = array('Wish');

	// custom programming

	/* Wish types. */
	const SID_PREFER = 'prefer';
	const SID_AVOID = 'avoid';

}

/* EOF */


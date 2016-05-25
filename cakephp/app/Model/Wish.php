<?php

App::uses('AppModel', 'Model');

/**
 * Wish Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.6
 */
class Wish extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public $name = 'Wish';

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_title'] = sprintf(
			'%1$s.id',
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
		'referee_id' => array('notblank', 'numeric'),
		'wish_type_id' => array('notblank', 'numeric'),
		'club_id' => array('numeric'),
		'league_id' => array('numeric'),
		'sex_type_id' => array('numeric'),
		'saturday' => array('boolean'),
		'sunday' => array('boolean'),
		'tournament' => array('boolean'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public $belongsTo = array('Club', 'League', 'Referee', 'WishType', 'SexType');

}

/* EOF */


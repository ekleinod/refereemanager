<?php

App::uses('AppModel', 'Model');

/**
 * RefereeRelation Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class RefereeRelation extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'RefereeRelation';

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
	 * @since 0.1
	 */
	public $displayField = 'display_title';

	/**
	 * Validation rules
	 *
	 * @version 0.6
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'referee_id' => array('notblank', 'numeric'),
		'referee_relation_type_id' => array('notblank', 'numeric'),
		'club_id' => array('notblank', 'numeric'),
		'season_id' => array('notblank', 'numeric'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.6
	 * @since 0.1
	 */
	public $belongsTo = array('Club', 'Referee', 'RefereeRelationType', 'Season');

}

/* EOF */


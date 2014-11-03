<?php

App::uses('AppModel', 'Model');

/**
 * TrainingUpdate Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class TrainingUpdate extends AppModel {

	/**
	 * Declare virtual field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['title_training_update'] = sprintf(
			'CONCAT(%1$s.training_level_id, " - ", %1$s.update)',
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
	public $name = 'TrainingUpdate';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'title_training_update';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'training_level_id' => array('notempty', 'numeric'),
		'update' => array('date', 'notempty'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('TrainingLevel');

}

/* EOF */


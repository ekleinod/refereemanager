<?php

App::uses('AppModel', 'Model');

/**
 * TrainingLevelType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class TrainingLevelType extends AppModel {

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
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'TrainingLevelType';

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
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'sid' => array('isUnique', 'notblank'),
		'rank' => array('isUnique', 'notblank', 'numeric'),
		'title' => array('isUnique', 'notblank'),
		'abbreviation' => array('isUnique', 'notblank'),
		'update_interval' => array('notblank', 'numeric'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('TrainingLevel');

	// custom programming

	/* Training level types. */
	const SID_ASSUMP = 'assump';
	const SID_NATUMP = 'natump';
	const SID_NATREF = 'natref';
	const SID_INTUMP = 'intump';

	/** Singleton for fast access. */
	private $trainingleveltypelist = null;

	/**
	 * Returns training level type list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of training level types
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function getTrainingLevelTypeList() {
		if ($this->trainingleveltypelist == null) {
			$this->trainingleveltypelist = $this->find('list',
																								 array(
																											 'order' => 'rank',
																											 )
																								 );
		}

		return $this->trainingleveltypelist;
	}

}

/* EOF */


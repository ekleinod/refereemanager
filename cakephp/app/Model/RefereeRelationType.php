<?php

App::uses('AppModel', 'Model');

/**
 * RefereeRelationType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class RefereeRelationType extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'RefereeRelationType';

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
		'title' => array('isUnique', 'notblank'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('RefereeRelation');

	// custom programming

	/* Relation types. */
	const SID_MEMBER = 'member';
	const SID_REFFOR = 'reffor';

	/** Singleton for fast access. */
	private $refereerelationtypes = null;
	private $refereerelationtypelist = null;

	/**
	 * Returns referee relation types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of referee relation types, empty if there are none
	 *
	 * @version 0.6
	 * @since 0.3
	 */
	public function getRefereeRelationTypes() {
		if ($this->refereerelationtypes == null) {
			$this->recursive = -1;
			$this->refereerelationtypes = $this->find('all',
																								array(
																											'order' => 'title',
																											)
																								);
		}

		return $this->refereerelationtypes;
	}

	/**
	 * Returns referee relation type list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of referee relation types
	 *
	 * @version 0.6
	 * @since 0.3
	 */
	public function getTrainingLevelTypeList() {
		if ($this->refereerelationtypelist == null) {
			$this->refereerelationtypelist = $this->find('list',
																									 array(
																												 'order' => 'title',
																												 )
																									 );
		}

		return $this->refereerelationtypelist;
	}

}

/* EOF */


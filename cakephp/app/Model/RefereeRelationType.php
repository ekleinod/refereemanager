<?php

App::uses('AppModel', 'Model');

/**
 * RefereeRelationType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
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
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'sid' => array(
			'notempty' => array(
				'rule' => array('notempty'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
		'title' => array(
			'notempty' => array(
				'rule' => array('notempty'),
				//'message' => 'Your custom message here',
				//'allowEmpty' => false,
				//'required' => false,
				//'last' => false, // Stop validation after this rule
				//'on' => 'create', // Limit validation to 'create' or 'update' operations
			),
		),
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
	const SID_PREFER = 'prefer';
	const SID_NOASSIGNMENT = 'noassignment';

	/** Singleton for fast access to relation types. */
	private $relationtypes = null;

	/**
	 * Returns relation type.
	 *
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param sid relation type's sid
	 * @return relation type
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function getRelationType($sid) {
		if ($this->relationtypes == null) {
			$this->recursive = -1;
			$this->relationtypes = array();
			foreach ($this->find('all') as $relationtype) {
				$this->relationtypes[$relationtype['RefereeRelationType']['sid']] = $relationtype['RefereeRelationType'];
			}
		}

		return $this->relationtypes[$sid];
	}

	/**
	 * Returns relation type's id.
	 *
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param sid relation type's sid
	 * @return relation type's id
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function getRelationTypeID($sid) {
		$relationtype = $this->getRelationType($sid);
		return $relationtype['id'];
	}

}

/* EOF */


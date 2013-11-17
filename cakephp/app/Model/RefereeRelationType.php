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
			),
		),
		'sid' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'title' => array(
			'notempty' => array(
				'rule' => array('notempty'),
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
	public function getRelationTypeBySID($sid) {
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
		$relationtype = $this->getRelationTypeBySID($sid);
		return $relationtype['id'];
	}

	/**
	 * Returns relation type's sid.
	 *
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param id relation type's id
	 * @return relation type's sid
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function getRelationTypeSID($id) {
		$relationtype = $this->findById($id);
		return $relationtype['RefereeRelationType']['sid'];
	}

}

/* EOF */


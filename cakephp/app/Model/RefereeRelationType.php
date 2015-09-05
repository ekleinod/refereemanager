<?php

App::uses('AppModel', 'Model');

/**
 * RefereeRelationType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
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
	const SID_PREFER = 'prefer';
	const SID_NOASSIGNMENT = 'noassignment';

	/** Singleton for fast access. */
	private $refereerelationtypes = null;
	private $refereerelationtypessid = null;
	private $refereerelationtypelist = null;

	/**
	 * Returns referee relation types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of referee relation types, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getRefereeRelationTypes() {
		if ($this->refereerelationtypes == null) {
			$this->recursive = -1;
			$this->refereerelationtypes = array();
			foreach ($this->find('all') as $refereerelationtype) {
				$this->refereerelationtypes[$refereerelationtype['RefereeRelationType']['id']] = $refereerelationtype;
			}
		}

		return $this->refereerelationtypes;
	}

	/**
	 * Returns referee relation types with sid as index.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of referee relation types, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getRefereeRelationTypesSID() {
		if ($this->refereerelationtypessid == null) {
			foreach ($this->getRefereeRelationTypes() as $refreltype) {
				$this->refereerelationtypessid[$refreltype['RefereeRelationType']['sid']] = $refreltype;
			}
		}

		return $this->refereerelationtypessid;
	}

	/**
	 * Returns referee relation type list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of referee relation types
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getRefereeRelationTypeList() {
		if ($this->refereerelationtypelist == null) {

			$this->refereerelationtypelist = array();

			foreach ($this->getRefereeRelationTypes() as $refereerelationtype) {
				$this->refereerelationtypelist[$refereerelationtype['RefereeRelationType']['id']] = $refereerelationtype['RefereeRelationType']['title'];
			}

			asort($this->refereerelationtypelist, SORT_LOCALE_STRING);
		}

		return $this->refereerelationtypelist;
	}

}

/* EOF */


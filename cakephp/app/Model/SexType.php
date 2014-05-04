<?php

App::uses('AppModel', 'Model');

/**
 * SexType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class SexType extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'SexType';

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
	public $hasMany = array('Person', 'RefereeRelation');

	// custom programming

	/* Relation types. */
	const SID_FEMALE = 'female';
	const SID_MALE = 'male';
	const SID_OTHER = 'other';

	/** Singleton for fast access to sex types. */
	private $sextypes = null;

	/**
	 * Returns sex type.
	 *
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param sid sex type's sid
	 * @return sex type
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function getSexType($sid) {
		if ($this->sextypes == null) {
			$this->recursive = -1;
			$this->sextypes = array();
			foreach ($this->find('all') as $sextype) {
				$this->sextypes[$sextype['SexType']['sid']] = $sextype['SexType'];
			}
		}

		return $this->sextypes[$sid];
	}

}

/* EOF */


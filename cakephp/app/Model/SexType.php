<?php

App::uses('AppModel', 'Model');

/**
 * SexType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class SexType extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_sextype'] = sprintf(
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
	public $name = 'SexType';

	/**
	 * Display field.
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $displayField = 'display_sextype';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'sid' => array('isUnique', 'notempty'),
		'title' => array('isUnique', 'notempty'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('LeagueType', 'Person', 'RefereeRelation');

	// custom programming

	/* Relation types. */
	const SID_FEMALE = 'female';
	const SID_MALE = 'male';
	const SID_OTHER = 'other';
	const SID_UNKNOWN = 'unknown';

	/** Singletons for fast access. */
	private $sextypes = null;
	private $sextypelist = null;

	/**
	 * Returns sex types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of sex types
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getSexTypes() {
		if ($this->sextypes == null) {
			$this->recursive = -1;
			$this->sextypes = array();
			foreach ($this->find('all') as $sextype) {
				$this->sextypes[$sextype['SexType']['id']] = $sextype;
			}
		}

		return $this->sextypes;
	}

	/**
	 * Returns sex type.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param sid sex type's sid
	 * @return sex type
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function getSexType($sid) {
		foreach ($this->getSexTypes() as $sextype) {
			if ($sextype['SexType']['sid'] === $sid) {
				return $sextype;
			}
		}

		return null;
	}

	/**
	 * Returns sex type list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of sex types
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getSexTypeList() {
		if ($this->sextypelist == null) {

			$this->sextypelist = array();

			foreach ($this->getSexTypes() as $sextype) {
				$this->sextypelist[$sextype['SexType']['id']] = $sextype['SexType']['display_sextype'];
			}

			asort($this->sextypelist, SORT_LOCALE_STRING);
		}

		return $this->sextypelist;
	}

}

/* EOF */


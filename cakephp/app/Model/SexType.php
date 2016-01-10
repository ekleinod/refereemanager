<?php

App::uses('AppModel', 'Model');

/**
 * SexType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class SexType extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.6
	 * @since 0.3
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
	public $name = 'SexType';

	/**
	 * Display field.
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
	public $hasMany = array('LeagueType', 'Person', 'Wish');

	// custom programming

	/* Sex types. */
	const SID_FEMALE = 'female';
	const SID_MALE = 'male';
	const SID_OTHER = 'other';
	const SID_UNKNOWN = 'unknown';

	/** Singletons for fast access. */
	private $types = null;
	private $typelist = null;

	/**
	 * Returns types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of types
	 *
	 * @version 0.6
	 * @since 0.3
	 */
	public function getTypes() {
		if ($this->types == null) {
			$this->recursive = -1;
			$this->types = array();
			foreach ($this->find('all') as $sextype) {
				$this->types[$sextype['SexType']['id']] = $sextype;
			}
		}

		return $this->types;
	}

	/**
	 * Returns type.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param sid type's sid
	 * @return type
	 *
	 * @version 0.6
	 * @since 0.1
	 */
	public function getType($sid) {
		foreach ($this->getTypes() as $sextype) {
			if ($sextype['SexType']['sid'] === $sid) {
				return $sextype;
			}
		}

		return null;
	}

	/**
	 * Returns type list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of types
	 *
	 * @version 0.6
	 * @since 0.3
	 */
	public function getTypelist() {
		if ($this->typelist == null) {

			$this->typelist = array();

			foreach ($this->gettypes() as $sextype) {
				$this->typelist[$sextype['SexType']['id']] = $sextype['SexType']['display_title'];
			}

			asort($this->typelist, SORT_LOCALE_STRING);
		}

		return $this->typelist;
	}

}

/* EOF */


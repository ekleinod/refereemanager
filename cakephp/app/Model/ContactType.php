<?php

App::uses('AppModel', 'Model');

/**
 * ContactType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class ContactType extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_contacttype'] = sprintf(
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
	public $name = 'ContactType';

	/**
	 * Display field
	 *
	 * @version 0.4
	 * @since 0.1
	 */
	public $displayField = 'display_contacttype';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'title' => array('isUnique', 'notblank'),
		'abbreviation' => array('isUnique', 'notblank'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('Contact');

	// custom programming

	/** Singleton for fast access. */
	private $contacttypes = null;

	/** Singleton for fast access. */
	private $contacttypelist = null;

	/**
	 * Returns contact types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of contact types, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getContactTypes() {
		if ($this->contacttypes == null) {
			$this->recursive = -1;
			$this->contacttypes = array();
			foreach ($this->find('all') as $contacttype) {
				$this->contacttypes[$contacttype['ContactType']['id']] = $contacttype;
			}
			usort($this->contacttypes, array('ContactType', 'compareTo'));
		}

		return $this->contacttypes;
	}

	/**
	 * Compare two objects.
	 *
	 * @param a first object
	 * @param b second object
	 * @return comparison result
	 *  @retval <0 a<b
	 *  @retval 0 a==b
	 *  @retval >0 a>b
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function compareTo($a, $b) {
		return strcasecmp($a['ContactType']['display_contacttype'], $b['ContactType']['display_contacttype']);
	}

	/**
	 * Returns contact type list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of contact types
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public function getContactTypeList() {
		if ($this->contacttypelist == null) {

			$this->contacttypelist = array();

			foreach ($this->getContactTypes() as $contacttype) {
				$this->contacttypelist[$contacttype['ContactType']['id']] = $contacttype['ContactType']['display_contacttype'];
			}

			asort($this->contacttypelist, SORT_LOCALE_STRING);
		}

		return $this->contacttypelist;
	}

}

/* EOF */


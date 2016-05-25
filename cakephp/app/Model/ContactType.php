<?php

App::uses('AppModel', 'Model');

/**
 * ContactType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class ContactType extends AppModel {

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
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.6
	 * @since 0.4
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
	private $types = null;

	/** Singleton for fast access. */
	private $typelist = null;

	/**
	 * Returns contact types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of contact types, empty if there are none
	 *
	 * @version 0.6
	 * @since 0.3
	 */
	public function getTypes() {
		if ($this->types == null) {
			$this->recursive = -1;
			$arrTemp = $this->find('all',
														 array(
																	 'order' => 'display_title',
																	 )
														 );
		}

		return $this->types;
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
	 * @version 0.6
	 * @since 0.3
	 */
	public static function compareTo($a, $b) {
		return strcasecmp($a['ContactType']['display_title'], $b['ContactType']['display_title']);
	}

	/**
	 * Returns contact type list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of contact types
	 *
	 * @version 0.6
	 * @since 0.4
	 */
	public function getTypeList() {
		if ($this->typelist == null) {
			$this->typelist = $this->find('list',
																		array(
																					'order' => 'display_title',
																					)
																		);
		}

		return $this->typelist;
	}

}

/* EOF */


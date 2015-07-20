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
		'id' => array('isUnique', 'notempty', 'numeric'),
		'title' => array('isUnique', 'notempty'),
		'abbreviation' => array('isUnique', 'notempty'),
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
		return strcasecmp($a['ContactType']['title'], $b['ContactType']['title']);
	}

}

/* EOF */


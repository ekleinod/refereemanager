<?php

App::uses('AppModel', 'Model');

/**
 * Club Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class Club extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_club'] = sprintf(
			'IF(%1$s.abbreviation IS NULL OR %1$s.abbreviation = "", %1$s.name, %1$s.abbreviation)',
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
	public $name = 'Club';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_club';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'name' => array('notempty'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('Contact', 'RefereeRelation', 'Spokesperson', 'Team', 'Tournament');

	// custom programming

	/** Singletons for fast access. */
	private $clubs = null;
	private $clublist = null;

	/**
	 * Returns clubs.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of clubs
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getClubs() {
		if ($this->clubs == null) {
			$this->recursive = -1;
			$this->clubs = array();
			foreach ($this->find('all') as $club) {
				$this->clubs[$club['Club']['id']] = $club;
			}
			usort($this->clubs, array('Club', 'compareTo'));
		}

		return $this->clubs;
	}

	/**
	 * Returns club list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of clubs
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getClubList() {
		if ($this->clublist == null) {

			$this->clublist = array();

			foreach ($this->getClubs() as $club) {
				$this->clublist[$club['Club']['id']] = $club['Club']['display_club'];
			}

			asort($this->clublist, SORT_LOCALE_STRING);
		}

		return $this->clublist;
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
		return strcasecmp($a['Club']['display_club'], $b['Club']['display_club']);
	}

}

/* EOF */


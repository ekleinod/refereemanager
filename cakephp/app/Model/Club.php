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
	public $displayField = 'name';

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

	/** Singleton for fast access. */
	private $clubs = null;

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

		$clubs = array();

		foreach ($this->getClubs() as $club) {
			$clubs[$club['Club']['id']] = $club['Club']['title'];
		}

		arsort($clubs, SORT_LOCALE_STRING);

		return $clubs;
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

		// first criterion: abbreviation or, if not existent, name
		$critA = (empty($a['Club']['abbreviation'])) ? $a['Club']['name'] : $a['Club']['abbreviation'];
		$critB = (empty($b['Club']['abbreviation'])) ? $b['Club']['name'] : $b['Club']['abbreviation'];

		$compResult = strcasecmp($critA, $critB);
		if ($compResult != 0) {
			return $compResult;
		}

		// second criterion: name
		$compResult = strcasecmp($a['Club']['name'], $b['Club']['name']);
		if ($compResult != 0) {
			return $compResult;
		}

		// equal
		return 0;
	}

}

/* EOF */


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

	/**
	 * Returns clubs.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of clubs, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getClubs() {

		$clubs = $this->find('all');
		usort($clubs, array('Club', 'compareTo'));

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
		// criterion: title
		return strcasecmp($a['Clubs']['name'], $b['Clubs']['name']);
	}

}

/* EOF */


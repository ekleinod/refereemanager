<?php

App::uses('AppModel', 'Model');

/**
 * LeagueType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class LeagueType extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'LeagueType';

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
		'title' => array('isUnique', 'notblank'),
		'sex_type_id' => array('notblank', 'numeric'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $hasMany = array('League', 'Team');

	/**
	 * belongsTo associations
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public $belongsTo = array('SexType');

	// custom programming

	/**
	 * Returns league types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of league types, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getLeagueTypes() {

		$leaguetypes = $this->find('all');
		usort($leaguetypes, array('LeagueType', 'compareTo'));

		return $leaguetypes;
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
		return strcasecmp($a['LeagueType']['title'], $b['LeagueType']['title']);
	}

}

/* EOF */


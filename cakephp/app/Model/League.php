<?php

App::uses('AppModel', 'Model');

/**
 * League Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class League extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_league'] = sprintf(
			'CONCAT(%1$s.title, " (", %1$s.abbreviation, ")")',
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
	public $name = 'League';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_league';

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
		'league_type_id' => array('notblank', 'numeric'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('LeagueType');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('LeagueGame',  'LeaguePlannedReferee', 'RefereeRelation', 'RefereeReport', 'TeamSeason');

	// custom programming

	/**
	 * Returns leagues.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of leagues, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getLeagues() {

		$leagues = $this->find('all');
		usort($leagues, array('League', 'compareTo'));

		return $leagues;
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
		return strcasecmp($a['League']['title'], $b['League']['title']);
	}

}

/* EOF */


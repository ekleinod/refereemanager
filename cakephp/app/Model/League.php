<?php

App::uses('AppModel', 'Model');

/**
 * League Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class League extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.6
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_title'] = sprintf(
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
		'league__id' => array('notblank', 'numeric'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('League');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('LeagueGame', 'LeaguePlannedReferee', 'RefereeReport', 'TeamSeason', 'League');

	// custom programming

	/** Singleton for fast access. */
	private $leagues = null;
	private $leaguelist = null;

	/**
	 * Returns leagues.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of league s
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function getLeagues() {
		if ($this->leagues == null) {
			$this->recursive = -1;
			$this->leagues = array();
			foreach ($this->find('all') as $league) {
				$this->leagues[$league['League']['id']] = $league;
			}
		}

		return $this->leagues;
	}

	/**
	 * Returns league list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of league s
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function getLeagueList() {
		if ($this->leaguelist == null) {

			$this->leaguelist = array();

			foreach ($this->getLeagues() as $league) {
				$this->leaguelist[$league['League']['id']] = $league['League']['title'];
			}

			asort($this->leaguelist, SORT_LOCALE_STRING);
		}

		return $this->leaguelist;
	}

}

/* EOF */


<?php

App::uses('AppModel', 'Model');

/**
 * Season Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class Season extends AppModel {

	/**
	 * Declare virtual field in constructor to be alias-safe.
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['title_season'] = sprintf(
			'IF ((%1$s.title IS NULL OR %1$s.title = \'\'), CONCAT(%1$s.year_start, "-", %1$s.year_start + 1), %1$s.title)',
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
	public $name = 'Season';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'title_season';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'year_start' => array('notempty', 'numeric'),
		'editor_only' => array('boolean'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $hasMany = array('LeagueGame', 'LeaguePlannedReferee', 'RefereeReport', 'RefereeStatus', 'TeamSeason');

	// custom programming

	/**
	 * Returns season for given start year.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param year start year of season (null == current season)
	 * @return season for given start year, null if not present
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function getSeason($year = null) {
		$theYear = ($year == null) ? sprintf('%d', ((idate('m') < 8) ? (idate('Y') - 1) : idate('Y'))) : $year;

		$this->recursive = -1;
		$ssnReturn = $this->findByYearStart($theYear);

		if (($ssnReturn == null) && ($year != null)) {
			$theYear = sprintf('%d', ((idate('m') < 8) ? (idate('Y') - 1) : idate('Y')));
			$this->recursive = -1;
			$ssnReturn = $this->findByYearStart($theYear);
		}

		if ($ssnReturn == null) {
			return null;
		}

		return $ssnReturn['Season'];
	}

	/**
	 * Returns seasons.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param isEditor is user editor?
	 * @return array of seasons, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getSeasons($isEditor) {

		$seasons = array();

		foreach ($this->find('all') as $season) {
			if (empty($season['Season']['editor_only']) ||
					!$season['Season']['editor_only'] ||
					$isEditor) {
				$seasons[] = $season;
			}
		}

		usort($seasons, array('Season', 'compareTo'));

		return $seasons;
	}

	/**
	 * Returns season lists.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @param isEditor is user editor?
	 * @return season list, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getSeasonList($isEditor) {

		$seasons = array();

		foreach ($this->getSeasons($isEditor) as $season) {
			$seasons[$season['Season']['id']] = $season['Season']['title_season'];
		}

		arsort($seasons, SORT_LOCALE_STRING);

		return $seasons;
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
		// criterion: start year
		return strcasecmp($a['Season']['year_start'], $b['Season']['year_start']);
	}

}

/* EOF */


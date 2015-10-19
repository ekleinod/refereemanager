<?php

App::uses('AppHelper', 'View/Helper');

/**
 * People Helper library.
 *
 * This class contains methods of model class "Person"
 * that have to be called from views too, and thus cannot
 * remain in a model class.
 *
 * @package       Lib.Utility
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.3
 */
class RefManPeople {

	/**
	 * Returns primary contact.
	 *
	 * @param person person
	 * @param contactkind contact kind
	 * @return primary contact (null if there is none)
	 *
	 * @version 0.6
	 * @since 0.3
	 */
	public static function getPrimaryContact($person, $contactkind) {

		// no contact of that kind
		if (empty($person['Contact']) || empty($person['Contact'][$contactkind])) {
			return null;
		}

		// several - search for primary
		foreach ($person['Contact'][$contactkind] as $contact) {
			if ($contact['Contact']['is_primary']) {
				return $contact;
			}
		}

		// primary not given - take first
		reset($person['Contact'][$contactkind]);
		return current($person['Contact'][$contactkind]);

	}

	/**
	 * Returns all referee relations with the given sid for the referee.
	 *
	 * @param referee referee
	 * @param sid sid
	 * @return array of referee relations (empty if there are none)
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getRelations($person, $sid) {
		$arrReturn = array();

		$model = ClassRegistry::init('RefereeRelation');

		foreach ($person['RefereeRelation'] as $relation) {
			$theRel = $model->findById($relation['id']);
			if ($theRel['RefereeRelationType']['sid'] == $sid) {
				$arrReturn[] = $theRel;
			}
		}

		return $arrReturn;
	}

	/**
	 * Returns highest training level for the referee.
	 *
	 * @param referee referee
	 * @return training level (null if there are none)
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getTrainingLevel($person) {
		$levReturn = null;

		$model = ClassRegistry::init('TrainingLevel');

		// training level
		foreach ($person['TrainingLevel'] as $level) {
			$theLevel = $model->findById($level['id']);
			if (empty($levReturn) || ($levReturn['TrainingLevelType']['rank'] < $theLevel['TrainingLevelType']['rank'])) {
				$levReturn = $theLevel;
			}
		}

		// last training
		if (!empty($levReturn)) {

			if (!empty($levReturn['TrainingUpdate'])) {

				foreach ($levReturn['TrainingUpdate'] as $trainingupdate) {
					if (empty($levReturn['lasttrainingupdate']) || ($levReturn['lasttrainingupdate'] < $trainingupdate['update'])) {
						$levReturn['lasttrainingupdate'] = $trainingupdate['update'];
					}
				}

			}

			// next training
			if (!empty($levReturn['lasttrainingupdate'])) {
				$levReturn['nexttrainingupdate'] = strtotime(sprintf('+%d years', $levReturn['TrainingLevelType']['update_interval']),
																										 CakeTime::fromString($levReturn['lasttrainingupdate']));
			}
			if (empty($levReturn['nexttrainingupdate']) && !empty($levReturn['TrainingLevel']['since'])) {
				$levReturn['nexttrainingupdate'] = strtotime(sprintf('+%d years', $levReturn['TrainingLevelType']['update_interval']),
																										 CakeTime::fromString($levReturn['TrainingLevel']['since']));
			}

		}

		return $levReturn;
	}

	/**
	 * Returns the referee status of the referee in the given season.
	 *
	 * @param referee referee
	 * @param season season
	 * @return referee status (null if there is none)
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getRefereeStatus($referee, $season) {
		foreach ($referee['RefereeStatus'] as $refereeStatus) {
			if ($refereeStatus['season_id'] == $season['Season']['id']) {
				return $refereeStatus;
			}
		}
		return null;
	}

}

/* EOF */


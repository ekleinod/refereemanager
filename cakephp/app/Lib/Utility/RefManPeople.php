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
 * @version 0.3
 * @since 0.3
 */
class RefManPeople {

	/**
	 * Returns all contacts.
	 *
	 * @param person person
	 * @param contactkind contact kind
	 * @return array of contacts (empty if there are none)
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getContacts($person, $contactkind) {
		$arrReturn = array();

		$model = ClassRegistry::init($contactkind);
		$model->recursive = -1;
		$modelTypes = ClassRegistry::init('ContactTypes');
		$modelTypes->recursive = -1;

		foreach ($person['Contact'] as $contact) {
			$tmpData = $model->findByContactId($contact['id']);
			if (!empty($tmpData)) {

				$tmpData['info'] = array();
				$tmpData['info']['is_primary'] = $contact['is_primary'];
				$tmpData['info']['title'] = $contact['title'];
				$tmpData['info']['remark'] = $contact['remark'];

				$type = $modelTypes->findById($contact['contact_type_id']);
				$tmpData['ContactType'] = $type['ContactTypes'];

				$arrReturn[] = $tmpData;
			}
		}

		return $arrReturn;
	}

	/**
	 * Returns primary contact.
	 *
	 * @param person person
	 * @param contactkind contact kind
	 * @return primary contact (null if there is none)
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getPrimaryContact($person, $contactkind) {

		$arrContacts = $this->getContacts($person, $contactkind);

		// no contact of that kind
		if (count($arrContacts) == 0) {
			return null;
		}

		// several - search for primary
		foreach ($arrContacts as $contact) {
			if ($contact['info']['is_primary']) {
				return $contact;
			}
		}

		// primary not given - take first
		return $arrContacts[0];

	}

	/**
	 * Returns all referee relations for the referee with the given sid.
	 *
	 * @param referee referee
	 * @param sid sid
	 * @return array of referee relations (empty if there are none)
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getRelations($person, $sid) {
		$arrReturn = array();

		$modelRelation = ClassRegistry::init('RefereeRelation');

		foreach ($person['RefereeRelation'] as $relation) {
			$theRel = $modelRelation->findById($relation['id']);
			if ($theRel['RefereeRelationType']['sid'] == $sid) {
				$arrReturn[] = $theRel;
			}
		}

		return $arrReturn;
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
			if ($refereeStatus['season_id'] == $season['id']) {
				return $refereeStatus;
			}
		}
		return null;
	}

}

/* EOF */


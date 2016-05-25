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

}

/* EOF */


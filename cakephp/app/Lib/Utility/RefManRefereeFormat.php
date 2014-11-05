<?php
/**
 * RefManRefereeFormat Utility
 *
 * Methods for formatting all referee manager components nicely.
 */

App::uses('CakeTime', 'Utility');

/**
 * RefereeFormat Helper library.
 *
 * Methods for formatting all referee manager components nicely.
 *
 * @package       Lib.Utility
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class RefManRefereeFormat {

	/**
	 * Format given date/time for SQL.
	 *
	 * @param string $date date to format
	 * @param string $time time to format
	 * @return string formatted string
	 *
	 * @version 0.2
	 * @since 0.1
	 */
	public function sqlFromDateTime($date, $time = null) {

		if (($date === null) || empty($date)) {
			return null;
		}

		if ($time === null) {
			$datetime = CakeTime::fromString($date);
		} else {
			$datetime = CakeTime::fromString(sprintf('%s %s', $date, $time));
		}

		return CakeTime::format($datetime, '%Y-%m-%d %H:%M:%S');;
	}

	/**
	 * Format given date/time according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @return string formatted string
	 *
	 * @version 0.2
	 * @since 0.1
	 */
	public function formatDate($data, $type) {

		if (($data === null) || empty($data)) {
			return '';
		}

		// @todo set locale correctly, still very mysterious
		$arrlocale = explode('-', Configure::read('Config.language'));
		setlocale(LC_TIME, sprintf('%s_%s.utf8', $arrlocale[0], strtoupper($arrlocale[1])));

		switch ($type) {
			case 'date':
				$formatted = CakeTime::format($data, '%d.%m.%Y');
				break;
			case 'datereverse':
				$formatted = CakeTime::format($data, '%Y-%m-%d');
				break;
			case 'datetime':
				$formatted = CakeTime::format($data, '%d.%m.%Y, %H:%M');
				break;
			case 'longdate':
				$formatted = CakeTime::format($data, '%a %d.%m.%Y');
				break;
			case 'longdatereverse':
				$formatted = CakeTime::format($data, '%d.%m.%Y (%a)');
				break;
			case 'medium':
				$formatted = CakeTime::format($data, '%e. %B %Y');
				break;
			case 'time':
				$formatted = CakeTime::format($data, '%H:%M');
				break;
			case 'year':
				$formatted = CakeTime::format($data, '%Y');
				break;
			default:
				$formatted = $data;
				break;
		}

		return $formatted;
	}

	/**
	 * Format given person according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @param string $datetype format type for dates (default 'date')
	 * @return string formatted string
	 *
	 * @version 0.2
	 * @since 0.1
	 */
	public function formatPerson($data, $type, $datetype = 'date') {

		if (($data === null) || empty($data)) {
			return '';
		}

		switch ($type) {
			case 'birthday':
				$formatted = (empty($data['birthday'])) ? '' : $this->formatDate($data['birthday'], $datetype);
				break;
			case 'dayofdeath':
				$formatted = (empty($data['dayofdeath'])) ? '' : __('+%s', $this->formatDate($data['dayofdeath'], $datetype));
				break;
			case 'first_name':
				$formatted = (empty($data['first_name'])) ? '' : $data['first_name'];
				break;
			case 'fullname':
				$formatted = __('%s%s%s',
												((empty($data['title'])) ? '' : __('%s ', $data['title'])),
												((empty($data['first_name'])) ? '' : __('%s ', $data['first_name'])),
												$data['name']);
				break;
			case 'name':
				$formatted = $data['name'];
				break;
			case 'name_title':
				$formatted = __('%s%s',
												$data['name'],
												((empty($data['title'])) ? '' : __(', %s', $data['title'])));
				break;
			case 'tablename':
				$formatted = __('%s%s%s',
												$data['name'],
												((empty($data['title'])) ? '' : __(', %s', $data['title'])),
												((empty($data['first_name'])) ? '' : __(', %s', $data['first_name'])));
				break;
			case 'title':
				$formatted = (empty($data['title'])) ? '' : $data['title'];
				break;
			default:
				$formatted = $data;
				break;
		}

		return $formatted;
	}

	/**
	 * Format given contacts.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @param string $type contact kind
	 * @param string $type export type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function formatContacts($data, $type, $kind, $export = 'html') {

		if (empty($data)) {
			return '';
		}

		$sReturn = '';

		if (is_array($data)) {

			$bMore = false;
			foreach ($data as $entry) {
				if ($bMore) {
					switch ($export) {
						case 'html':
							$sReturn .= '<br />';
							break;
					}
				}
				$bMore = true;

				if (count($data) > 1) {
					$sReturn .= sprintf('(%s) ', $entry['ContactType']['abbreviation']);
				}

				$sReturn .= $this->formatContact($entry, $type, $kind, $export);
			}

		} else {
			$sReturn = $this->formatContact($data, $type, $kind, $export, 1);
		}

		return $sReturn;
	}

	/**
	 * Format given contact.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @param string $type contact kind
	 * @param string $type export type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	private function formatContact($data, $type, $kind, $export) {
			switch ($kind) {
				case 'Address':
					return $this->formatAddress($data, $type, $export);
				case 'Email':
					return $this->formatEmail($data, $type, $export);
				case 'PhoneNumber':
					return $this->formatPhoneNumber($data, $type, $export);
				case 'Url':
					return $this->formatUrl($data, $type, $export);
			}

			return null;
	}

	/**
	 * Format given address according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @param string $type export type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function formatAddress($data, $type, $export) {

		$sReturn = '';

		switch ($export) {

			case 'html':
				switch ($type) {
					case 'fulladdress':
						if (!empty($data['Address']['street'])) {
							$sReturn .= __('%s', $data['Address']['street']);
						}
						if (!empty($sReturn) && !empty($data['Address']['number'])) {
							$sReturn .= __(' %s', $data['Address']['number']);
						}
						if (!empty($sReturn) && (!empty($data['Address']['zip_code']) || !empty($data['Address']['city']))) {
							$sReturn .= __(',');
						}
						if (!empty($data['Address']['zip_code'])) {
							$sReturn .= __(' %s', $data['Address']['zip_code']);
						}
						if (!empty($data['Address']['city'])) {
							$sReturn .= __(' %s', $data['Address']['city']);
						}
						break;
					case 'streetnumber':
						if (!empty($data['Address']['street'])) {
							$sReturn .= __('%s', $data['Address']['street']);
						}
						if (!empty($sReturn) && !empty($data['Address']['number'])) {
							$sReturn .= __(' %s', $data['Address']['number']);
						}
						break;
					case 'zipcity':
						if (!empty($data['Address']['zip_code'])) {
							$sReturn .= __('%s', $data['Address']['zip_code']);
						}
						if (!empty($data['Address']['city'])) {
							$sReturn .= __(' %s', $data['Address']['city']);
						}
						break;
					default:
						$sReturn = $data;
						break;
				}
		}

		return $sReturn;
	}

	/**
	 * Format given email according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @param string $type export type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function formatEmail($data, $type, $export) {

		$sReturn = '';

		switch ($export) {

			case 'html':
				switch ($type) {
					case 'link':
						$sReturn .= sprintf('<a href="mailto:%1$s">%1$s</a>', $data['Email']['email']);
						break;
					case 'text':
						$sReturn .= $data['Email']['email'];
						break;
				}
				break;

		}

		return $sReturn;
	}

	/**
	 * Format given phone number according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @param string $type export type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function formatPhoneNumber($data, $type, $export) {

		$sReturn = '';

		switch ($export) {

			case 'html':
				switch ($type) {
					case 'international':
						$sReturn .= __('+%s %s %s',
													($data['PhoneNumber']['country_code'] === '') ? Configure::read('RefMan.defaultcountrycode') : $data['PhoneNumber']['country_code'],
													($data['PhoneNumber']['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $data['PhoneNumber']['area_code'],
													$data['PhoneNumber']['number']);
						break;
					case 'national':
						if (($data['PhoneNumber']['country_code'] != '') && ($data['PhoneNumber']['country_code'] != Configure::read('RefMan.defaultcountrycode'))) {
							$sReturn .= __('+%s %s %s',
														 $data['PhoneNumber']['country_code'],
														 ($data['PhoneNumber']['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $data['PhoneNumber']['area_code'],
														 $data['PhoneNumber']['number']);
						} else {
							$sReturn .= __('0%s %s',
														($data['PhoneNumber']['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $data['PhoneNumber']['area_code'],
														$data['PhoneNumber']['number']);
						}
						break;
				}
				break;

		}

		return $sReturn;

	}

	/**
	 * Format given url according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @param string $type export type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function formatUrl($data, $type, $export) {

		$sReturn = '';

		switch ($export) {

			case 'html':
				switch ($type) {
					case 'link':
						$sReturn .= sprintf('<a href="http://%1$s">%1$s</a>', $data['Url']['url']);
						break;
					case 'text':
						$sReturn .= $data['Url']['url'];
						break;
				}

		}

		return $sReturn;
	}

	/**
	 * Format relation with sid using separator character.
	 *
	 * @param string $data data to format
	 * @param string $sid sid of relation
	 * @param string $sep separator
	 * @return string formatted string
	 *
	 * @version 0.2
	 * @since 0.1
	 */
	public function formatRelationBySID($data, $sid, $sep = '; ') {

		if (($data === null) || empty($data)) {
			return '';
		}

		if (!array_key_exists($sid, $data)) {
			return '';
		}

		return $this->formatRelation($data[$sid], $sep);
	}

	/**
	 * Format relation using separator character.
	 *
	 * @param string $data data to format
	 * @param string $sep separator
	 * @return string formatted string
	 *
	 * @version 0.2
	 * @since 0.1
	 */
	public function formatRelation($data, $sep = '; ') {

		if (($data === null) || empty($data)) {
			return '';
		}

		$formatted = '';

		$hasMore = false;
		foreach ($data as $relation) {
			if ($hasMore) {
				$formatted .= $sep;
			}
			if (array_key_exists('Club', $relation)) {
				$formatted .= $relation['Club']['name'];
			}
			if (array_key_exists('League', $relation)) {
				$formatted .= $relation['League']['title'];
			}
			if (array_key_exists('SexType', $relation)) {
				$formatted .= $relation['SexType']['title'];
			}
			if (array_key_exists('Saturday', $relation)) {
				$formatted .= __('Sonnabend');
			}
			if (array_key_exists('Sunday', $relation)) {
				$formatted .= __('Sonntag');
			}
			if (array_key_exists('Remark', $relation)) {
				$formatted .= $relation['Remark'];
			}
			$hasMore = true;
		}

		return $formatted;
	}

}

/* EOF */


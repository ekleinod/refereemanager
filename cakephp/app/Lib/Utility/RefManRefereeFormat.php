<?php

App::uses('CakeTime', 'Utility');

/**
 * RefereeFormat Helper library.
 *
 * Methods for formatting all referee manager components nicely.
 *
 * @package       Lib.Utility
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class RefManRefereeFormat {

	/**
	 * Format date/time for SQL.
	 *
	 * @param $date date to format
	 * @param $time time to format
	 * @return string formatted string
	 *
	 * @version 0.4
	 * @since 0.1
	 */
	public static function sqlFromDateTime($date, $time = null) {

		if (empty($date)) {
			return null;
		}

		$sReturn = null;

		if (empty($time)) {
			$sReturn = RefManRefereeFormat::formatDate(CakeTime::fromString($date), 'sqldate');
		} else {
			$sReturn = RefManRefereeFormat::formatDate(CakeTime::fromString(sprintf('%s %s', $date, $time)), 'sqldatetime');
		}

		return $sReturn;
	}

	/**
	 * Format date/time according to specified type.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @return string formatted string
	 *
	 * @version 0.4
	 * @since 0.1
	 */
	public static function formatDate($data, $type) {

		if (empty($data)) {
			return '';
		}

		// @todo set locale correctly, still very mysterious
		$arrlocale = explode('-', Configure::read('Config.language'));
		if (count($arrlocale) < 1) {
			$arrlocale[] = "en";
		}
		if (count($arrlocale) < 2) {
			$arrlocale[] = strtoupper($arrlocale[0]);
		}
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
			case 'sqldate':
				$formatted = CakeTime::format($data, '%Y-%m-%d');
				break;
			case 'sqldatetime':
				$formatted = CakeTime::format($data, '%Y-%m-%d %H:%M:%S');
				break;
			case 'time':
				$formatted = CakeTime::format($data, '%H:%M');
				break;
			case 'year':
				$formatted = CakeTime::format($data, '%Y');
				break;
		}

		return $formatted;
	}

	/**
	 * Format person according to specified type.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public static function formatPerson($data, $type) {

		if (empty($data)) {
			return '';
		}

		switch ($type) {
			case 'fullname':
				$formatted = __('%s%s%s',
												((empty($data['Person']['title'])) ? '' : __('%s ', $data['Person']['title'])),
												((empty($data['Person']['first_name'])) ? '' : __('%s ', $data['Person']['first_name'])),
												$data['Person']['name']);
				break;
			case 'name_title':
				$formatted = __('%s%s',
												$data['Person']['name'],
												((empty($data['Person']['title'])) ? '' : __(', %s', $data['Person']['title'])));
				break;
			case 'tablename':
				$formatted = __('%s%s%s',
												$data['Person']['name'],
												((empty($data['Person']['title'])) ? '' : __(', %s', $data['Person']['title'])),
												((empty($data['Person']['first_name'])) ? '' : __(', %s', $data['Person']['first_name'])));
				break;
		}

		return $formatted;
	}

	/**
	 * Format multiline data.
	 *
	 * @param $data data to format
	 * @param $separator separator character
	 * @return formatted string
	 *
	 * @version 0.6
	 * @since 0.3
	 */
	public static function formatMultiline($data, $separator) {
		if (empty($data)) {
			return '';
		}

		$sReturn = '';

		if (is_array($data)) {

			$bMore = false;
			foreach ($data as $entry) {
				if ($bMore) {
					$sReturn .= $separator;
				}
				$bMore = true;

				$sReturn .= $entry;
			}

		} else {
			$sReturn .= $data;
		}

		return $sReturn;
	}

	/**
	 * Format contacts.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @param $type contact kind
	 * @param $export export type
	 * @return formatted string
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function formatContacts($data, $type, $kind, $export = 'html') {

		if (empty($data)) {
			return '';
		}

		$sTextArray = array();

		foreach ($data as $entry) {
			$sTextArray[] = RefManRefereeFormat::formatContact($entry, $type, $kind, $export, count($data));
		}

		$sSeparator = '';
		switch ($export) {
			case 'html':
				$sSeparator = '<br />';
				break;
			case 'excel':
				$sSeparator = "\n";
				break;
			case 'text':
				$sSeparator = ', ';
				break;
		}

		return RefManRefereeFormat::formatMultiline($sTextArray, $sSeparator);
	}

	/**
	 * Format contact.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @param $kind contact kind
	 * @param $count number of contacts
	 * @return string formatted string
	 *
	 * @version 0.6
	 * @since 0.3
	 */
	public static function formatContact($data, $type, $kind, $count = 1) {
			$sReturn = '';

			if ($count > 1) {
				$sReturn .= sprintf('(%s) ', $data['ContactType']['abbreviation']);
			}

			$sFormatFunction = sprintf('format%s', $kind);
			$sReturn .= RefManRefereeFormat::$sFormatFunction($data, $type);

			if (!empty($data['info']['editor_only']) || !empty($data['info']['remark'])) {

				$txtout = array();
				if (!empty($data['info']['editor_only'])) {
					$txtout[] = __('privat');
				}
				if (!empty($data['info']['remark'])) {
					$txtout[] = $data['info']['remark'];
				}
				$txtout = RefManRefereeFormat::formatMultiline($txtout, ', ');

				switch ($type) {
					case 'html':
						$sReturn .= sprintf(' <span style="font-size: smaller; font-style: italic;">(%s)</span>', $txtout);
						break;
					case 'excel':
					case 'text':
						$sReturn .= sprintf(' (%s)', $txtout);
						break;
				}
			}

			return $sReturn;
	}

	/**
	 * Format address according to specified type.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @param $export export type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public static function formatAddress($data, $type, $export) {

		$sReturn = '';

		switch ($export) {

			case 'html':
			case 'excel':
			case 'text':
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
				}
		}

		return $sReturn;
	}

	/**
	 * Format email.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @return string formatted string
	 *
	 * @version 0.6
	 * @since 0.1
	 */
	private static function formatEmail($data, $type) {

		$sReturn = '';

		switch ($type) {

			case 'link':
				$sReturn .= sprintf('<a href="mailto:%1$s">%1$s</a>', $data['Email']['email']);
				break;

			case 'text':
				$sReturn .= $data['Email']['email'];
				break;

		}

		return $sReturn;
	}

	/**
	 * Format phone number according to specified type.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @param $export export type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private static function formatPhoneNumber($data, $type, $export) {

		$sReturn = '';

		switch ($export) {

			case 'html':
			case 'excel':
			case 'text':
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
	 * Format url according to specified type.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @param $export export type
	 * @return string formatted string
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private static function formatUrl($data, $type, $export) {

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
				break;

			case 'excel':
			case 'text':
				$sReturn .= $data['Url']['url'];
				break;

		}

		return $sReturn;
	}

	/**
	 * Format relations.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @param $export export type
	 * @return formatted string
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function formatRelations($data, $type, $export = 'html') {

		if (empty($data)) {
			return '';
		}

		$sTextArray = array();

		foreach ($data as $entry) {
			$sTextArray[] = RefManRefereeFormat::formatRelation($entry, $type, $export);
		}

		return RefManRefereeFormat::formatMultiline($sTextArray, '; ');
	}

	/**
	 * Format relation using separator character.
	 *
	 * @param $data data to format
	 * @param $type format type
	 * @param $export export type
	 * @return formatted string
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public static function formatRelation($data, $type, $export = 'html') {

		if (empty($data)) {
			return '';
		}

		$sTextArray = array();

		if (!empty($data['RefereeRelation']['club_id'])) {
			$sTextArray[] = empty($data['Club']['abbreviation']) ? $data['Club']['name'] : $data['Club']['abbreviation'];
		}
		if (!empty($data['RefereeRelation']['league_id'])) {
			$sTextArray[] = $data['League']['title'];
		}
		if (!empty($data['RefereeRelation']['sex_type_id'])) {
			$sTextArray[] = $data['SexType']['title'];
		}
		if (!empty($data['RefereeRelation']['saturday']) && $data['RefereeRelation']['saturday']) {
			$sTextArray[] = __('Sonnabend');
		}
		if (!empty($data['RefereeRelation']['sunday']) && $data['RefereeRelation']['sunday']) {
			$sTextArray[] = __('Sonntag');
		}
		if (!empty($data['RefereeRelation']['remark'])) {
			$sTextArray[] = $data['RefereeRelation']['remark'];
		}

		return RefManRefereeFormat::formatMultiline($sTextArray, ' - ');
	}

}

/* EOF */


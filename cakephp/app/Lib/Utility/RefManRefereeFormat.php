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
 */
class RefManRefereeFormat {

	/**
	 * Format given date/time according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @return string formatted string
	 */
	public function formatDate($data, $type) {

		if (($data === null) || empty($data)) {
			return '';
		}

		switch ($type) {
			case 'longdate':
				$formatted = CakeTime::format('D d.m.Y', $data);
				break;
			case 'date':
				$formatted = CakeTime::format('d.m.Y', $data);
				break;
			case 'datereverse':
				$formatted = CakeTime::format('Y-m-d', $data);
				break;
			case 'datetime':
				$formatted = CakeTime::format('d.m.Y, H:i', $data);
				break;
			case 'time':
				$formatted = CakeTime::format('H:i', $data);
				break;
			case 'year':
				$formatted = CakeTime::format('Y', $data);
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
	 */
	public function formatPerson($data, $type, $datetype = 'date') {

		if (($data === null) || empty($data)) {
			return '';
		}

		switch ($type) {
			case 'birthday':
				$formatted = (empty($data['birthday'])) ? '' : $this->formatDate($data['birthday'], $datetype);
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
	 * Format given email according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @return string formatted string
	 */
	public function formatEMail($data, $type) {

		if (($data === null) || empty($data)) {
			return '';
		}

		switch ($type) {
			case 'link':
				$formatted = sprintf('<a href="mailto:%1$s">%1$s</a>', $data['email']);
				break;
			case 'text':
				$formatted = $data['email'];
				break;
			default:
				$formatted = $data;
				break;
		}

		return $formatted;
	}

	/**
	 * Format given phone number according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @return string formatted string
	 */
	public function formatPhone($data, $type) {

		if (($data === null) || empty($data)) {
			return '';
		}

		switch ($type) {
			case 'international':
				$formatted = __('+%s %s ', ($data['country_code'] === '') ? Configure::read('RefMan.defaultcountrycode') : $data['country_code'], ($data['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $data['area_code']);
				$formatted .= $data['number'];
				break;
			case 'normal':
				$formatted = '';
				if (($data['country_code'] != '') && ($data['country_code'] != Configure::read('RefMan.defaultcountrycode'))) {
					$formatted .= __('+%s %s ', $data['country_code'], ($data['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $data['area_code']);
				} else {
					$formatted .= __('0%s ', ($data['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $data['area_code']);
				}
				$formatted .= $data['number'];
				break;
			default:
				$formatted = $data;
				break;
		}

		return $formatted;
	}

	/**
	 * Format given url according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @return string formatted string
	 */
	public function formatURL($data, $type) {

		if (($data === null) || empty($data)) {
			return '';
		}

		switch ($type) {
			case 'link':
				$formatted = sprintf('<a href="%1$s">%1$s</a>', $data['url']);
				break;
			case 'text':
				$formatted = $data['url'];
				break;
			default:
				$formatted = $data;
				break;
		}

		return $formatted;
	}

	/**
	 * Format given address according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type format type
	 * @return string formatted string
	 */
	public function formatAddress($data, $type) {

		if (($data === null) || empty($data)) {
			return '';
		}

		switch ($type) {
			case 'fulladdress':
				$formatted = '';
				if ($data['street'] != '') {
					$formatted .= __('%s', $data['street']);
				}
				if (($formatted != '') && ($data['number'] != '')) {
					$formatted .= __(' %s', $data['number']);
				}
				if (($formatted != '') && (($data['zip_code'] != '') || ($data['city'] != ''))) {
					$formatted .= __(',');
				}
				if ($data['zip_code'] != '') {
					$formatted .= __(' %s', $data['zip_code']);
				}
				if ($data['city'] != '') {
					$formatted .= __(' %s', $data['city']);
				}
				break;
			default:
				$formatted = $data;
				break;
		}

		return $formatted;
	}

}

/* EOF */


<?php
/**
 * RefereeFormat Helper class file.
 *
 */

App::uses('AppHelper', 'View/Helper');

/**
 * RefereeFormat Helper class for referee manager specific formatting.
 *
 * @package       Cake.View.Helper
 */
class RefereeFormatHelper extends AppHelper {

	/** Helpers that are used within this class. */
	public $helpers = array('Time');

	/**
	 * Format given date/time according to specified type.
	 *
	 * @param string $data date/time to format
	 * @param string $type format type
	 * @return string formatted string
	 */
	public function formatDate($data, $type) {

		$formatted = NULL;

		if (($data === null) || empty($data)) {
			return '';
		}

		switch ($type) {
			case 'longdate':
				$formatted = $this->Time->format('D d.m.Y', $data);
				break;
			case 'date':
				$formatted = $this->Time->format('d.m.Y', $data);
				break;
			case 'datereverse':
				$formatted = $this->Time->format('Y-m-d', $data);
				break;
			case 'datetime':
				$formatted = $this->Time->format('d.m.Y, H:i', $data);
				break;
			case 'time':
				$formatted = $this->Time->format('H:i', $data);
				break;
			case 'year':
				$formatted = $this->Time->format('Y', $data);
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
	 * @param string $data person to format
	 * @param string $type format type
	 * @return string formatted string
	 */
	public function formatPerson($data, $type) {

		$formatted = NULL;

		if (($data === null) || empty($data)) {
			return '';
		}

		switch ($type) {
			case 'birthday':
				$formatted = (empty($data['birthday'])) ? '' : $this->formatDate($data['birthday'], 'date');
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

}

/* EOF */


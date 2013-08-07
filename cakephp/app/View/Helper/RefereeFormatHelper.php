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
	 * Format given data according to specified type.
	 *
	 * @param string $data data to format
	 * @param string $type data type
	 * @return string formatted string
	 */
	public function format($data, $type) {

		$formatted = NULL;

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

}

/* EOF */


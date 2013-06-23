<?php

	if ($type == 'excel') {

		// compute different styles
		foreach ($statustypes as &$statustypeedit) {
			$statustypeedit['outputstyle'] = array();
			if ($statustypeedit['style'] || $statustypeedit['color'] || $statustypeedit['bgcolor']) {

				switch ($statustypeedit['style']) {
					case 'normal':
					case 'italic':
					case 'oblique':
						$statustypeedit['outputstyle']['font-style'] = $statustypeedit['style'];
						break;
					case 'normal':
					case 'bold':
					case 'bolder':
					case 'lighter':
						$statustypeedit['outputstyle']['font-weight'] = $statustypeedit['style'];
						break;
				}

				if ($statustypeedit['color']) {
					$statustypeedit['outputstyle']['color'] = $statustypeedit['color'];
				}

				if ($statustypeedit['bgcolor']) {
					$statustypeedit['outputstyle']['bg-color'] = $statustypeedit['bgcolor'];
				}

			}
		}

		// start table
		$this->PhpExcel->createWorksheet();
		$this->PhpExcel->setDefaultFont('DejaVu Sans', 11);

		// header
		$header = array();
			$header[] = array('text' => __('Vorname'), 'width' => '15');
			$header[] = array('text' => __('Name'));
			$header[] = array('text' => __('Club'));

			if ($isReferee) {
				$header[] = array('text' => __('E-Mail'));
				$header[] = array('text' => __('Telefon'));
			}

			if ($isEditor) {
				$header[] = array('text' => __('Adresse'));
			}

		$header[] = array('text' => __('Anmerkung'));
		$this->PhpExcel->addTableHeader($header, array('font-weight' => 'bold', 'font-size' => 10, 'width' => 'auto'), 0, true);

		// datarows
		foreach ($referees as $referee) {

			$refformat = array();

			$datarow = array();
			$datarow[] = array('text' => $referee['Person']['first_name']);
			$datarow[] = array('text' => $referee['Person']['name']);
			$datarow[] = array('text' => (empty($referee['Club'])) ? '' : $referee['Club']['name']);

			if ($isReferee) {
				// email
				$text = '';
				if (array_key_exists('Contact', $referee) && array_key_exists('Email', $referee['Contact'])) {
					$hasMore = false;
					$printType = (count($referee['Contact']['Email']) > 1);
					foreach ($referee['Contact']['Email'] as $contacttype => $emailkind) {
						$printType |= (count($emailkind) > 1);
						foreach ($emailkind as $email) {
							if ($hasMore) {
								$text .= "\n";
							}
							if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
								$text .=  __('%s: ', $contacttypes[$contacttype]['short']);
							}
							$text .= $email['email'];
							$hasMore = true;
						}
					}
				}
				$datarow[] = array('text' => $text);

				// phone
				$text = '';
				if (array_key_exists('Contact', $referee) && array_key_exists('PhoneNumber', $referee['Contact'])) {
					$hasMore = false;
					$printType = (count($referee['Contact']['PhoneNumber']) > 1);
					foreach ($referee['Contact']['PhoneNumber'] as $contacttype => $phonekind) {
						$printType |= (count($phonekind) > 1);
						foreach ($phonekind as $phone) {
							if ($hasMore) {
								$text .= "\n";
							}
							if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
								$text .= __('%s: ', $contacttypes[$contacttype]['short']);
							}
							if (($phone['country_code'] != '') && ($phone['country_code'] != Configure::read('RefMan.defaultcountrycode'))) {
								$text .= __('+%s %s ', $phone['country_code'], ($phone['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $phone['area_code']);
							} else {
								$text .= __('0%s ', ($phone['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $phone['area_code']);
							}
							$text .= $phone['number'];
							$hasMore = true;
						}
					}
				}
				$datarow[] = array('text' => $text);
			}

			if ($isEditor) {
				// address
				$text = '';
				if (array_key_exists('Contact', $referee) && array_key_exists('Address', $referee['Contact'])) {
					$hasMore = false;
					$printType = (count($referee['Contact']['Address']) > 1);
					foreach ($referee['Contact']['Address'] as $contacttype => $addresskind) {
						$printType |= (count($addresskind) > 1);
						foreach ($addresskind as $address) {
							if ($hasMore) {
								$text .= '<br />';
							}
							if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
								$text .= __('%s: ', $contacttypes[$contacttype]['short']);
							}
							if ($address['street'] != '') {
								$text .= __('%s', $address['street']);
							}
							if (($text != '') && ($address['number'] != '')) {
								$text .= __(' %s', $address['number']);
							}
							if (($text != '') && (($address['zip_code'] != '') || ($address['city'] != ''))) {
								$text .= __(',');
							}
							if ($address['zip_code'] != '') {
								$text .= __(' %s', $address['zip_code']);
							}
							if ($address['city'] != '') {
								$text .= __(' %s', $address['city']);
							}
							$hasMore = true;
						}
					}
				}
				$datarow[] = array('text' => $text);
			}

			$datarow[] = array('text' => (empty($referee['Person']['remark'])) ? '' : $referee['Person']['remark']);

			$this->PhpExcel->addTableRow($datarow, $statustypes[$referee['StatusType']['id']]['outputstyle']);
		}

		// legend
		$this->PhpExcel->addTableRow(array());
		$this->PhpExcel->addTableRow(array('text' => array(__('Legende:'))));
		foreach ($statustypes as $statustype) {
			$this->PhpExcel->addTableRow(array(array('text' => ($statustype['remark']) ? $statustype['remark'] : $statustype['title'])), $statustype['outputstyle']);
		}

		// output
		$this->PhpExcel->output('VSR.xlsx');

	} else {
		throw new CakeException(__('Exporttyp "%s" nicht unterstützt!', $type));
	}

?>


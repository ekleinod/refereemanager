<?php

	if ($type == 'excel') {

		// start table
		$this->PhpExcel->createWorksheet();
		$this->PhpExcel->setDefaultFont('Calibri', 11);

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
		$this->PhpExcel->addTableHeader($header, array('font-weight' => 'bold', 'font-size' => 10, 'width' => 'auto'), 0, true);

		// datarows
		foreach ($referees as $referee) {

			$refformat = array();

			$datarow = array();
			$datarow[] = array('text' => $referee['Person']['first_name']);
			$datarow[] = array('text' => $referee['Person']['name']);
			$datarow[] = array('text' => (empty($referee['Club'])) ? '' : $referee['Club']['name']);

			if ($isReferee) {
				$datarow[] = array('text' => __('E-Mail'));
				$datarow[] = array('text' => __('Telefon'));
			}

			if ($isEditor) {
				$datarow[] = array('text' => __('Adresse'));
			}

			$this->PhpExcel->addTableRow($datarow);
		}

		// legend
		$this->PhpExcel->addTableRow(array());
		$this->PhpExcel->addTableRow(array('text' => array(__('Legende:'))));
		foreach ($statustypes as $statustype) {
			$this->PhpExcel->addTableRow(array(array('text' => ($statustype['remark']) ? $statustype['remark'] : $statustype['title'])));
		}

		// output
		$this->PhpExcel->output('VSR.xlsx');

	} else {
		throw new CakeException(__('Exporttyp "%s" nicht unterstützt!', $type));
	}

?>


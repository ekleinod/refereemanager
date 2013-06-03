<?php

	if ($type == 'excel') {

		// start table
		$this->PhpExcel->createWorksheet();
		$this->PhpExcel->setDefaultFont('Calibri', 11);

		// header
		$header = array();
		$header[] = array('label' => __('Vorname'), 'width' => 'auto');
		$header[] = array('label' => __('Name'), 'width' => 'auto');
		$header[] = array('label' => __('Club'), 'width' => 'auto', 'filter' => true);

		if ($isReferee) {
			$header[] = array('label' => __('E-Mail'), 'width' => 'auto');
			$header[] = array('label' => __('Telefon'), 'width' => 'auto');
		}

		if ($isEditor) {
			$header[] = array('label' => __('Adresse'), 'width' => 'auto', 'wrap' => true);
		}

		$this->PhpExcel->addTableHeader($header, array('bold' => true, 'size' => 10));

		// datarows
		foreach ($referees as $referee) {

			$refformat = array();

			$datarow = array();
			$datarow[] = array('label' => $referee['Person']['first_name']);
			$datarow[] = array('label' => $referee['Person']['name']);
			$datarow[] = array('label' => (empty($referee['Club'])) ? '' : $referee['Club']['name']);

			if ($isReferee) {
				$datarow[] = array('label' => __('E-Mail'));
				$datarow[] = array('label' => __('Telefon'));
			}

			if ($isEditor) {
				$datarow[] = array('label' => __('Adresse'));
			}

			$this->PhpExcel->addTableRow($datarow);
		}

		// legend
		$this->PhpExcel->addTableRow(array());
		$this->PhpExcel->addTableRow(array('label' => array(__('Legende:'))));
		foreach ($statustypes as $statustype) {
			$this->PhpExcel->addTableRow(array(array('label' => ($statustype['remark']) ? $statustype['remark'] : $statustype['title'])));
		}

		// footer
		$this->PhpExcel->addTableFooter();

		// output
		$this->PhpExcel->output('VSR.xlsx');

	} else {
		throw new CakeException(__('Exporttyp "%s" nicht unterstützt!', $type));
	}

?>


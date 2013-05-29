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

		// footer
		$this->PhpExcel->addTableFooter();

		// output
		$this->PhpExcel->output('VSR.xlsx');

	} else {
		throw new CakeException(__('Exporttyp "%s" nicht unterstützt!', $type));
	}

?>


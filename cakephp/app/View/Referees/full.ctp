<?php

		// start table
		$this->PHPExcel->createWorksheet();
		$this->PHPExcel->setDefaultFont('Calibri', 11);

		// header
		$header = array();
		$header[] = array('label' => 'Header 1');
		$header[] = array('label' => 'Header 2', 'font' => 'Sans', 'size' => '8', 'bold' => true, 'italic' => true, 'color' => '0000FF', 'width' => 'auto', 'wrap' => true);
		$header[] = array('label' => 'Header 3', 'size' => '15', 'width' => 75, 'wrap' => false);
		$header[] = array('label' => 'Header 4', 'bold' => false, 'italic' => false);
		$header[] = array('label' => 'Header 5', 'wrap' => false);
		$this->PHPExcel->addTableHeader($header, array('bold' => true, 'size' => 10, 'color' => '008800', 'wrap' => true), 2, true);

		// normal rows
		$data = array();
		$data[] = array('label' => 'I am a text. And me too.');
		$data[] = array('label' => 'I am a text. And me too.');
		$data[] = array('label' => 'I am a text. And me too.');
		$data[] = array('label' => 'I am a text. And me too.');
		$data[] = array('label' => 'I am a text. And me too.');
		$this->PHPExcel->addTableRow($data);

		// footer
		$this->PHPExcel->addTableFooter();

		// output
		$this->PHPExcel->output('Full.xlsx');

?>


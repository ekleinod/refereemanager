<?php

		// start table
		$this->PHPExcel->createWorksheet();
		$this->PHPExcel->setDefaultFont('Calibri', 11);

		// header
		$header = array();
		$header[] = array('text' => 'Header 1');
		$header[] = array('text' => 'Header 2', 'font-name' => 'Serif', 'font-size' => '8', 'font-weight' => 'bold', 'font-style' => 'italic', 'color' => '0000FF', 'bg-color' => '00DD00', 'width' => 'auto', 'wrap' => true);
		$header[] = array('text' => 'Header 3', 'font-size' => '15', 'width' => 75, 'wrap' => false);
		$header[] = array('text' => 'Header 4', 'font-weight' => 'bold', 'font-style' => 'normal');
		$header[] = array('text' => 'Header 5', 'wrap' => false);
		$header[] = array('text' => 'Header 6', 'column' => array('wrap' => true, 'font-weight' => 'bold'));
		$this->PHPExcel->addTableHeader($header, array('font-weight' => 'bold', 'font-size' => 10, 'color' => '008800', 'wrap' => true), 2, true);

		// normal rows
		for ($row = 1; ($row <= 10); $row++) {
			$data = array();
			for ($column = 1; ($column <= count($header)); $column++) {
				$data[] = array('text' => sprintf('I am a text in %d:%d. I am a bit longer in order to test wrapping.', $row, $column));
			}
			$this->PHPExcel->addTableRow($data);
		}

		// footer
		$this->PHPExcel->addTableFooter();

		// output
		$this->PHPExcel->output('Full.xlsx');

?>


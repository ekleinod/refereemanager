<?php

	if ($export == 'excel') {
		$this->PhpExcel->createWorksheet();
		$this->PhpExcel->setDefaultFont('Calibri', 11);
	}

	if ($export == 'excel') {
		$this->PhpExcel->addTableFooter();
		$this->PhpExcel->output('VSR.xlsx');
	}

?>


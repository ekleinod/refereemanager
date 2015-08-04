<?php

	$isRefView = isset($isRefView) && $isRefView;

	if (($type === 'excel') || ($type === 'referee_view_zip') || ($type === 'pdf')) {

		include_once('columns.php');

		if ($type === 'excel') {
			// start table
			$this->PHPExcel->createWorksheet(null, 11, PHPExcel_Style_Alignment::VERTICAL_TOP, PHPExcel_Style_Alignment::HORIZONTAL_LEFT);

			// meta information
			$this->PHPExcel->addTableRow(array('text' => array(__('Verbandsschiedsrichter_innen BTTV Saison %s, Stand: %s', $season['Season']['title_season'], $this->RefereeFormat->formatDate(time(), 'date')))), array('font-weight' => 'normal', 'font-size' => 14));
			$this->PHPExcel->addTableRow(array());

			$this->PHPExcel->getXLS()->getProperties()
					->setCreator(__('RefereeManager'))
					->setLastModifiedBy(__('RefereeManager'))
					->setTitle(__('Verbandsschiedsrichter_innen des BTTV Saison %s, Stand: %s', $season['Season']['title_season'], $this->RefereeFormat->formatDate(time(), 'date')))
					->setSubject(__('Verbandsschiedsrichter_innen des BTTV'))
					->setDescription(__('Übersicht der Verbandsschiedsrichter_innen, exportiert aus dem RefereeManager'))
					->setKeywords(__('Verbandsschiedsrichter_innen BTTV %s', $season['Season']['title_season']))
					->setCategory(__('Schiedsrichterliste'));

			// header
			foreach ($columns[$type] as $column) {
				$header[] = array('text' => $column['title'], 'width' => $column[$type]['width']);
			}
		}

		if ($type === 'pdf') {
			App::import('Vendor','RefManTCPDF');

			$orientation = ($isEditor) ? 'L' : PDF_PAGE_ORIENTATION;
			$tcpdf = new RefManTCPDF($orientation, PDF_UNIT, PDF_PAGE_FORMAT, true, 'UTF-8', false);
			$tcpdf->SetAutoPageBreak(false);

			$tcpdf->setTitle(__('Verbandsschiedsrichter BTTV Saison %s, Stand: %s', $season['Season']['title_season'], $this->RefereeFormat->formatDate(time(), 'date')));
			$tcpdf->setSubject(__('Verbandsschiedsrichter des BTTV'));
			$tcpdf->setCreator(PDF_CREATOR);
			$tcpdf->setAuthor(PDF_AUTHOR);
			$tcpdf->setKeywords(__('Verbandsschiedsrichter BTTV %s', $season['Season']['title_season']));
			$tcpdf->rmSetHeader(__('Verbandsschiedsrichter BTTV Saison %s, Stand: %s', $season['Season']['title_season'], $this->RefereeFormat->formatDate(time(), 'date')),
													__('Seite %s von %s'));

			$tcpdf->setHeaderFont(Array(PDF_FONT_NAME_MAIN, '', PDF_FONT_SIZE_DATA));
			$tcpdf->setFooterFont(Array(PDF_FONT_NAME_DATA, '', PDF_FONT_SIZE_DATA));
			$tcpdf->SetDefaultMonospacedFont(PDF_FONT_MONOSPACED);

			$tcpdf->SetMargins(PDF_MARGIN_LEFT, PDF_MARGIN_TOP, PDF_MARGIN_RIGHT);
			$tcpdf->SetHeaderMargin(PDF_MARGIN_HEADER);
			$tcpdf->SetFooterMargin(PDF_MARGIN_FOOTER);

			$tcpdf->SetAutoPageBreak(TRUE, PDF_MARGIN_BOTTOM);

			$tcpdf->AddPage();

		}

		if ($type === 'referee_view_zip') {
			$tplRefView = RefManTemplate::getTemplate('refereeview');
			RefManTemplate::openZip(Configure::read('RefMan.template.refereeviewout'));
		}

		if (empty($people)) {
			if ($type === 'excel') {
				$this->PHPExcel->addTableTexts(__('Es sind keine Schiedsrichter_innen gespeichert.'));
			}
			if ($type === 'pdf') {
				$tcpdf->Write(0, __('Es sind keine Schiedsrichter_innen gespeichert.'));
			}
			if ($type === 'referee_view_zip') {
				echo __('Es sind keine Schiedsrichter_innen gespeichert.');
			}
		} else {

			if ($type === 'excel') {
				$this->PHPExcel->addTableHeader($header, array('font-weight' => 'bold', 'font-size' => 10, 'width' => 'auto'), true);
			}

			if ($type === 'pdf') {
				$header = array();

				foreach ($columns[$type] as $column) {
					$header[] = array('text' => $column['title'], 'width' => $column[$type]['width']);
				}

				$pdf_data = array();
			}

			// datarows
			foreach ($people as $person) {

				$tmpStatusStyles = null;
				if ($isRefView) {
					$tmpStatus = $this->People->getRefereeStatus($person, $season);
					if (($tmpStatus !== null) && (array_key_exists($tmpStatus['status_type_id'], $statustypes))) {
						$tmpFormat = $this->Html->style(Configure::read(sprintf('RefMan.statustypes.%s', $statustypes[$tmpStatus['status_type_id']]['StatusType']['sid'])));
					}
				}

				$datarow = array();

				if ($type === 'excel') {
					foreach ($columns[$type] as $column) {
						$datarow[] = array('text' => $this->Template->replaceRefereeData($column['content'], $person, 'text', 'excel'));
					}
					$this->PHPExcel->addTableRow($datarow, (($tmpStatusStyles === null) ? array() : $tmpStatusStyles['excel']));
				}

				if ($type === 'pdf') {
					foreach ($columns[$type] as $column) {
						$datarow[] = array('text' => $this->Template->replaceRefereeData($column['content'], $person, 'text', 'html'));
					}
					$pdf_data[] = array('data' => $datarow, 'style' => (($tmpStatusStyles === null) ? '' : $tmpStatusStyles['html']));
				}

				if ($type === 'referee_view_zip') {

					$txtRefView = $tplRefView;

					$txtRefView = RefManTemplate::replaceDateTimeData($txtRefView);
					$txtRefView = RefManTemplate::replaceSeasonData($txtRefView, $season, 'text', 'text');

					$txtRefView = RefManTemplate::replaceRefereeData($txtRefView, $person, 'text', 'text');

					RefManTemplate::addToZip('mmd',
																	 sprintf('%s_%s_%03d',
																					 RefManTemplate::fileName($person['Person']['name']),
																					 RefManTemplate::fileName($person['Person']['first_name']),
																					 $person['Person']['id']),
																	 $txtRefView);
				}

			}

			// finish
			if ($type === 'excel') {
				// legend
				$this->PHPExcel->addTableRow(array());
				$this->PHPExcel->addTableRow(array('text' => array(__('Legende:'))), array('font-weight' => 'bold', 'font-size' => 10));
				foreach ($statustypes as $thestatustype) {
					$this->PHPExcel->addTableRow(
																			 array(array('text' => ($thestatustype['StatusType']['remark']) ? $thestatustype['StatusType']['remark'] : $thestatustype['StatusType']['title'])),
																			 $thestatustype['outputstyle']['excel']);
				}

				$this->PHPExcel->addTableRow(array());
				$this->PHPExcel->addTableRow(array('text' => array(__('Zusatzinformationen'))), array('font-weight' => 'bold', 'font-size' => 10));
				foreach ($statustypes as $outstatustype) {
					$this->PHPExcel->addTableRow(
																			 array(array('text' => ($outstatustype['StatusType']['remark']) ? $outstatustype['StatusType']['remark'] : $outstatustype['StatusType']['title'])),
																			 array('font-weight' => 'bold', 'font-size' => 10));
					$arrNames = array();
					foreach ($outstatustype['referees'] as $referee) {
						$arrNames[] = $this->RefereeFormat->formatPerson($referee, 'fullname');
					}
					$this->PHPExcel->addTableTexts($this->RefereeFormat->formatMultiline($arrNames, ', '));
				}
			}

			if ($type === 'pdf') {
				$tcpdf->WriteHTML($tcpdf->getTable($header, $pdf_data), true, false, true, false, '');
				$tcpdf->WriteHTML(sprintf('<p style="font-size: %spt; font-weight: bold;">%s</p>', PDF_FONT_SIZE_MAIN, __('Legende')), true, false, true, false, '');
				foreach ($statustypes as $statustype) {
					$tcpdf->WriteHTML(sprintf('<p style="font-size: %spt; %s">%s</p>',
																		PDF_FONT_SIZE_DATA,
																		$statustype['outputstyle']['html'],
																		($statustype['StatusType']['remark']) ? h($statustype['StatusType']['remark']) : h($statustype['StatusType']['title'])),
														true, false, true, false, '');
				}
			}

			if ($type === 'referee_view_zip') {
				$zipfile = RefManTemplate::closeZip();
				$this->response->file($zipfile);
				echo $this->response;
			}

		}

		// output/save file
		if ($type === 'excel') {
			$this->PHPExcel->output('VSR.xlsx');
		}

		if ($type === 'pdf') {
			echo $tcpdf->Output('referees.pdf', 'D');
		}

	} else {
		throw new CakeException(__('Exporttyp "%s" nicht unterstützt!', $type));
	}

?>

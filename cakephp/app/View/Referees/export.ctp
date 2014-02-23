<?php

	if (($type === 'excel') || ($type === 'referee_view_zip') || ($type === 'pdf')) {

		// compute different styles
		foreach ($statustypes as &$statustypeedit) {
			$statustypeedit['outputstyle'] = array();
			$statustypeedit['htmlstyle'] = '';

			if ($statustypeedit['style']) {
				switch ($statustypeedit['style']) {
					case 'normal':
					case 'italic':
					case 'oblique':
						$statustypeedit['outputstyle']['font-style'] = $statustypeedit['style'];
						$statustypeedit['htmlstyle'] .= sprintf('font-style: %s; ', $statustypeedit['style']);
						break;
					case 'normal':
					case 'bold':
					case 'bolder':
					case 'lighter':
						$statustypeedit['outputstyle']['font-weight'] = $statustypeedit['style'];
						$statustypeedit['htmlstyle'] .= sprintf('font-weight: %s; ', $statustypeedit['style']);
						break;
				}
			}

			if ($statustypeedit['color']) {
				$statustypeedit['outputstyle']['color'] = $statustypeedit['color'];
				$statustypeedit['htmlstyle'] .= sprintf('color: #%s; ', $statustypeedit['color']);
			}

			if ($statustypeedit['bgcolor']) {
				$statustypeedit['outputstyle']['bg-color'] = $statustypeedit['bgcolor'];
				$statustypeedit['htmlstyle'] .= sprintf('background-color: #%s; ', $statustypeedit['bgcolor']);
			}
		}

		// header
		$header = array();
			$header[] = array('text' => __('Name'), 'width' => '15');
			$header[] = array('text' => __('Vorname'));

			foreach ($allrefereerelationtypes as $sid => $refereerelationtype) {
				if (array_key_exists($sid, $refereerelationtypes) && (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor)) {
					$header[] = array('text' => __($refereerelationtype['title']));
				}
			}

			if ($isReferee) {
				$header[] = array('text' => __('E-Mail'));
				$header[] = array('text' => __('Telefon'));
			}

			if ($isEditor) {
				$header[] = array('text' => __('Adresse'));
				$header[] = array('text' => __('Geschlecht'));
				$header[] = array('text' => __('Geburtstag'));
			}

			$header[] = array('text' => __('Ausbildung'));

			if ($isEditor) {
				$header[] = array('text' => __('Letzte Ausbildung'));
				$header[] = array('text' => __('Letzte Fortbildung'));
				$header[] = array('text' => __('Nächste Fortbildung'));
				$header[] = array('text' => __('Anmerkung'));
				$header[] = array('text' => __('Interne Anmerkung'));
			}

		if ($type === 'excel') {
			// start table
			$this->PHPExcel->createWorksheet(null, 11, PHPExcel_Style_Alignment::VERTICAL_TOP, PHPExcel_Style_Alignment::HORIZONTAL_LEFT);

			// meta information
			$this->PHPExcel->addTableRow(array('text' => array(__('Verbandsschiedsrichter BTTV Saison %s, Stand: %s', $season['title_season'], $this->RefereeFormat->formatDate(time(), 'date')))), array('font-weight' => 'normal', 'font-size' => 14));
			$this->PHPExcel->addTableRow(array());

			$this->PHPExcel->getXLS()->getProperties()
					->setCreator(__('RefereeManager'))
					->setLastModifiedBy(__('RefereeManager'))
					->setTitle(__('Verbandsschiedsrichter des BTTV Saison %s, Stand: %s', $season['title_season'], $this->RefereeFormat->formatDate(time(), 'date')))
					->setSubject(__('Verbandsschiedsrichter des BTTV'))
					->setDescription(__('Übersicht der Verbandsschiedsrichter, exportiert aus dem RefereeManager'))
					->setKeywords(__('Verbandsschiedsrichter BTTV %s', $season['title_season']))
					->setCategory(__('Schiedsrichterliste'));

			$this->PHPExcel->addTableHeader($header, array('font-weight' => 'bold', 'font-size' => 10, 'width' => 'auto'), true);
		}

		$nbrtoken = '`%s`';
		if ($type === 'referee_view_zip') {
			$template = file_get_contents(sprintf('%s%s%s', WWW_ROOT, Configure::read('RefMan.template.path'), Configure::read('RefMan.template.referee_view')));
			$tpltoken = '#%s#';
			$refview_empty = '---';

			$latexallrefs = file_get_contents(sprintf('%s%s%s', WWW_ROOT, Configure::read('RefMan.template.path'), Configure::read('RefMan.template.referee_view_all')));
			$fletoken = 'referee%04d';
			$latexreftoken = sprintf("\t\\includepdf{generated/%s.pdf}\n", $fletoken);

			$zip = new ZipArchive();
			$zipfile = sprintf('%s%s.zip', TMP, Configure::read('RefMan.template.referee_view'));
			if ($zip->open($zipfile, ZipArchive::OVERWRITE) === TRUE) {
				$zip->addFile(sprintf('%s%s%s.build.xml', WWW_ROOT, Configure::read('RefMan.template.path'), Configure::read('RefMan.template.referee_view')), 'build.xml');
			} else {
				echo __('Zip-Archiv "%s" konnte nicht angelegt werden.', $zipfile);
			}
		}

		if ($type === 'pdf') {
			App::import('Vendor','RefManTCPDF');

			$orientation = ($isEditor) ? 'L' : PDF_PAGE_ORIENTATION;
			$tcpdf = new RefManTCPDF($orientation, PDF_UNIT, PDF_PAGE_FORMAT, true, 'UTF-8', false);
			$tcpdf->SetAutoPageBreak(false);

			$tcpdf->setTitle(__('Verbandsschiedsrichter BTTV Saison %s, Stand: %s', $season['title_season'], $this->RefereeFormat->formatDate(time(), 'date')));
			$tcpdf->setSubject(__('Verbandsschiedsrichter des BTTV'));
			$tcpdf->setCreator(PDF_CREATOR);
			$tcpdf->setAuthor(PDF_AUTHOR);
			$tcpdf->setKeywords(__('Verbandsschiedsrichter BTTV %s', $season['title_season']));
			$tcpdf->rmSetHeader(__('Verbandsschiedsrichter BTTV Saison %s, Stand: %s', $season['title_season'], $this->RefereeFormat->formatDate(time(), 'date')),
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

		if (empty($referees)) {
			if ($type === 'excel') {
				$this->PHPExcel->addTableTexts(__('Es sind keine Schiedsrichter_innen gespeichert.'));
			}
			if ($type === 'referee_view_zip') {
				echo __('Es sind keine Schiedsrichter_innen gespeichert.');
			}
			if ($type === 'pdf') {
				$tcpdf->Write(0, __('Es sind keine Schiedsrichter_innen gespeichert.'));
			}
		} else {

			if ($type === 'pdf') {
				$pdf_header = array();

				$width = 120;
				if ($isReferee) {
					$width = 110;
				}
				if ($isEditor) {
					$width = 100;
				}
				$pdf_header[] = array('text' => __('Name'), 'width' => $width);

				$sid1 = RefereeRelationType::SID_MEMBER;
				$sid2 = RefereeRelationType::SID_REFFOR;
				if (array_key_exists($sid1, $refereerelationtypes) || array_key_exists($sid2, $refereerelationtypes)) {
					$relout = '';
					if (array_key_exists($sid1, $refereerelationtypes)) {
						$relout = __($allrefereerelationtypes[$sid1]['title']);
						if (array_key_exists($sid2, $refereerelationtypes)) {
							$relout .= sprintf('<br /><em>%s</em>', __($allrefereerelationtypes[$sid2]['title']));
						}
					} else {
						$relout = __($allrefereerelationtypes[$sid2]['title']);
					}

					$width = 200;
					if ($isReferee) {
						$width = 170;
					}
					if ($isEditor) {
						$width = 110;
					}
					$pdf_header[] = array('text' => $relout, 'width' => $width);
				}
				if ($isEditor) {
					$sid1 = RefereeRelationType::SID_PREFER;
					$sid2 = RefereeRelationType::SID_NOASSIGNMENT;
					if (array_key_exists($sid1, $refereerelationtypes) || array_key_exists($sid2, $refereerelationtypes)) {
						$relout = '';
						if (array_key_exists($sid1, $refereerelationtypes)) {
							$relout = __($allrefereerelationtypes[$sid1]['title']);
							if (array_key_exists($sid2, $refereerelationtypes)) {
								$relout .= '<br />' . __($allrefereerelationtypes[$sid2]['title']);
							}
						} else {
							$relout = __($allrefereerelationtypes[$sid2]['title']);
						}

						$width = 110;
						$pdf_header[] = array('text' => $relout, 'width' => $width);
					}
				}

				if ($isReferee) {
					$width = 180;
					if ($isEditor) {
						$width = 150;
					}
					$pdf_header[] = array('text' => __('Kontakt'), 'width' => $width);
				}

				if ($isEditor) {
					$width = 90;
					$pdf_header[] = array('text' => __('Adresse'), 'width' => $width);
					$width = 60;
					$pdf_header[] = array('text' => __('Geschlecht<br />Geburtstag'), 'width' => $width);
				}

				$width = 50;
				$pdf_header[] = array('text' => __('Ausbildung'), 'width' => $width);

				if ($isEditor) {
					$width = 85;
					$pdf_header[] = array('text' => __('Letzte Ausbildung<br /><em>Letzte Fortbildung</em><br />Nächste Fortbildung'), 'width' => $width);
				}

				$pdf_data = array();
			}

			// datarows
			foreach ($referees as $referee) {

				$datarow = array();

				// prepare
				if ($type === 'referee_view_zip') {
					$filledTemplate = $template;
					$repltoken = 'date';
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $this->RefereeFormat->formatDate(time(), 'medium'), $filledTemplate);
					$repltoken = 'season';
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $season['title_season'], $filledTemplate);
				}

				if ($type === 'excel') {
					$datarow[] = array('text' => $this->RefereeFormat->formatPerson($referee['Person'], 'name_title'));
					$datarow[] = array('text' => $this->RefereeFormat->formatPerson($referee['Person'], 'first_name'));
				}
				if ($type === 'pdf') {
					$datarow[] = array('text' => $this->RefereeFormat->formatPerson($referee['Person'], 'tablename'));
				}
				if ($type === 'referee_view_zip') {
					$repltoken = 'fullname';
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $this->RefereeFormat->formatPerson($referee['Person'], 'fullname'), $filledTemplate);
					$repltoken = 'title';
					$refview_text = $this->RefereeFormat->formatPerson($referee['Person'], 'title');
					$refview_text = (empty($refview_text)) ? $refview_empty : $refview_text;
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					$repltoken = 'first_name';
					$refview_text = $this->RefereeFormat->formatPerson($referee['Person'], 'first_name');
					$refview_text = (empty($refview_text)) ? $refview_empty : $refview_text;
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					$repltoken = 'name';
					$refview_text = $this->RefereeFormat->formatPerson($referee['Person'], 'name');
					$refview_text = (empty($refview_text)) ? $refview_empty : $refview_text;
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
				}

				// relations
				if (($type === 'excel') || ($type === 'referee_view_zip')) {
					foreach ($allrefereerelationtypes as $sid => $refereerelationtype) {
						if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {

							$excel_text = $this->RefereeFormat->formatRelationBySID($referee['RefereeRelation'], $sid);

							if (($type === 'excel') && array_key_exists($sid, $refereerelationtypes)) {
								$datarow[] = array('text' => $excel_text);
							}
							if ($type === 'referee_view_zip') {
								$repltoken = $sid;
								$refview_text = (empty($excel_text)) ? $refview_empty : $excel_text;
								$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
							}
						}
					}
				}
				if ($type === 'pdf') {
					$sid1 = RefereeRelationType::SID_MEMBER;
					$sid2 = RefereeRelationType::SID_REFFOR;
					if (array_key_exists($sid1, $refereerelationtypes) || array_key_exists($sid2, $refereerelationtypes)) {
						$pdf_text = '';
						if (array_key_exists($sid1, $referee['RefereeRelation']) || array_key_exists($sid2, $referee['RefereeRelation'])) {
							if (array_key_exists($sid1, $referee['RefereeRelation'])) {
								$pdf_text = $this->RefereeFormat->formatRelationBySID($referee['RefereeRelation'], $sid1);
								if (array_key_exists($sid2, $referee['RefereeRelation'])) {
									$pdf_text .= sprintf('<br /><em>%s</em>', $this->RefereeFormat->formatRelationBySID($referee['RefereeRelation'], $sid2));
								}
							} else {
								$pdf_text = $this->RefereeFormat->formatRelationBySID($referee['RefereeRelation'], $sid2);
							}
						}
						$datarow[] = array('text' => $pdf_text);
					}
					if ($isEditor) {
						$sid1 = RefereeRelationType::SID_PREFER;
						$sid2 = RefereeRelationType::SID_NOASSIGNMENT;
						if (array_key_exists($sid1, $refereerelationtypes) || array_key_exists($sid2, $refereerelationtypes)) {
							$pdf_text = '';
							if (array_key_exists($sid1, $referee['RefereeRelation']) || array_key_exists($sid2, $referee['RefereeRelation'])) {
								if (array_key_exists($sid1, $referee['RefereeRelation'])) {
									$pdf_text = $this->RefereeFormat->formatRelationBySID($referee['RefereeRelation'], $sid1);
									if (array_key_exists($sid2, $referee['RefereeRelation'])) {
										$pdf_text .= sprintf('<br /><em>%s</em>', $this->RefereeFormat->formatRelationBySID($referee['RefereeRelation'], $sid2));
									}
								} else {
									$pdf_text = $this->RefereeFormat->formatRelationBySID($referee['RefereeRelation'], $sid2);
								}
							}
							$datarow[] = array('text' => $pdf_text);
						}
					}
				}


				if ($isReferee) {

					// email
					$excel_text = '';
					$refview_text = '';
					$pdf_text = '';
					if (array_key_exists('Contact', $referee) && array_key_exists('Email', $referee['Contact'])) {
						$hasMore = false;
						$printType = (count($referee['Contact']['Email']) > 1);
						foreach ($referee['Contact']['Email'] as $contacttype => $emailkind) {
							$printType |= (count($emailkind) > 1);
							foreach ($emailkind as $email) {
								if ($hasMore) {
									$excel_text .= "\n";
									$refview_text .= "<!-- \\newline -->";
									$pdf_text .= "<br />";
								}
								if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
									$excel_text .=  __('%s: ', $contacttypes[$contacttype]['abbreviation']);
									$refview_text .=  __('%s: ', $contacttypes[$contacttype]['abbreviation']);
									$pdf_text .=  __('%s: ', $contacttypes[$contacttype]['abbreviation']);
								}
								$excel_text .= $this->RefereeFormat->formatEMail($email, 'text');
								$refview_text .= sprintf($nbrtoken, $this->RefereeFormat->formatEMail($email, 'text'));
								$pdf_text .= $this->RefereeFormat->formatEMail($email, 'text');
								$hasMore = true;
							}
						}
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
					}
					if ($type === 'pdf') {
						$pdf_email = $pdf_text;
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'email';
						$refview_text = (empty($refview_text) || ($refview_text === $nbrtoken)) ? $refview_empty : $refview_text;
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}

					// phone
					$excel_text = '';
					$refview_text = '';
					$pdf_text = '';
					if (array_key_exists('Contact', $referee) && array_key_exists('PhoneNumber', $referee['Contact'])) {
						$hasMore = false;
						$printType = (count($referee['Contact']['PhoneNumber']) > 1);
						foreach ($referee['Contact']['PhoneNumber'] as $contacttype => $phonekind) {
							$printType |= (count($phonekind) > 1);
							foreach ($phonekind as $phone) {
								if ($hasMore) {
									$excel_text .= "\n";
									$refview_text .= "<!-- \\newline -->";
									$pdf_text .= "<br />";
								}
								if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
									$excel_text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
									$refview_text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
									$pdf_text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
								}
								$excel_text .= $this->RefereeFormat->formatPhone($phone, 'normal');
								$refview_text .= sprintf($nbrtoken, $this->RefereeFormat->formatPhone($phone, 'normal'));
								$pdf_text .= $this->RefereeFormat->formatPhone($phone, 'normal');
								$hasMore = true;
							}
						}
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
					}
					if ($type === 'pdf') {
						if (empty($pdf_email)) {
							$datarow[] = array('text' => $pdf_text);
						} else {
							$datarow[] = array('text' => sprintf('%s<br />%s', $pdf_email, $pdf_text));
						}
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'phone';
						$refview_text = (empty($refview_text) || ($refview_text === $nbrtoken)) ? $refview_empty : $refview_text;
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}
				}

				if ($isEditor) {

					// address
					$excel_text = '';
					$pdf_text = '';
					if (array_key_exists('Contact', $referee) && array_key_exists('Address', $referee['Contact'])) {
						$hasMore = false;
						$printType = (count($referee['Contact']['Address']) > 1);
						foreach ($referee['Contact']['Address'] as $contacttype => $addresskind) {
							$printType |= (count($addresskind) > 1);
							foreach ($addresskind as $address) {
								if ($hasMore) {
									$excel_text .= '\n';
									$pdf_text .= "<br />";
								}
								if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
									$excel_text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
									$pdf_text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
								}
								$excel_text .= $this->RefereeFormat->formatAddress($address, 'fulladdress');
								$pdf_text .= $this->RefereeFormat->formatAddress($address, 'fulladdress');
								$hasMore = true;
							}
						}
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
					}
					if ($type === 'pdf') {
						$datarow[] = array('text' => $pdf_text);
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'streetnumber';
						$refview_text = $this->RefereeFormat->formatAddress($address, $repltoken);
						$refview_text = (empty($refview_text)) ? $refview_empty : $refview_text;
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
						$repltoken = 'zipcity';
						$refview_text = $this->RefereeFormat->formatAddress($address, $repltoken);
						$refview_text = (empty($refview_text)) ? $refview_empty : $refview_text;
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}

					// sex
					if ($type === 'excel') {
						$datarow[] = array('text' => __($referee['SexType']['title']));
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'sex_type';
						$refview_text = __($referee['SexType']['title']);
						$refview_text = (empty($refview_text)) ? $refview_empty : $refview_text;
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}

					// birthday
					$excel_text = '';
					if (!empty($referee['Person']['birthday'])) {
						$excel_text .= $this->RefereeFormat->formatPerson($referee['Person'], 'birthday');
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
					}
					if ($type === 'pdf') {
						$pdf_text = __($referee['SexType']['title']);
						if (!empty($excel_text)) {
							$pdf_text .= sprintf('<br />%s', $excel_text);
						}
						$datarow[] = array('text' => $pdf_text);
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'birthday';
						$refview_text = (empty($excel_text)) ? $refview_empty : sprintf($nbrtoken, $excel_text);
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}
				}

				// training level
				$excel_text = '';
				$refview_text = '';
				if (!empty($referee['TrainingLevelInfo'])) {
					$excel_text .= __($referee['TrainingLevelInfo']['abbreviation']);
					$refview_text .= __($referee['TrainingLevelInfo']['title']);
				}
				if ($type === 'excel') {
					$datarow[] = array('text' => $excel_text);
				}
				if ($type === 'pdf') {
					$datarow[] = array('text' => $excel_text);
				}
				if ($type === 'referee_view_zip') {
					$repltoken = 'training_level';
					$refview_text = (empty($refview_text)) ? $refview_empty : $refview_text;
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					$repltoken = 'training_level_abbr';
					$refview_text = (empty($excel_text)) ? $refview_empty : $excel_text;
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
				}

				if ($isEditor) {
					// since
					$excel_text = '';
					$pdf_text = '';
					if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['since'])) {
						$excel_text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['since'], 'date');
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
					}
					if ($type === 'pdf') {
						$pdf_text .= $excel_text;
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'since';
						$refview_text = (empty($excel_text)) ? $refview_empty : sprintf($nbrtoken, $excel_text);
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}

					// last update
					$excel_text = '';
					if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['lastupdate'])) {
						$excel_text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['lastupdate'], 'date');
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
					}
					if ($type === 'pdf') {
						if (!empty($excel_text)) {
							if (empty($pdf_text)) {
								$pdf_text = sprintf('<em>%s</em>', $excel_text);
							} else {
								$pdf_text .= sprintf('<br /><em>%s</em>', $excel_text);
							}
						}
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'last_update';
						$refview_text = (empty($excel_text)) ? $refview_empty : sprintf($nbrtoken, $excel_text);
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}

					// next update
					$excel_text = '';
					if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['nextupdate'])) {
						$excel_text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['nextupdate'], 'year');
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
					}
					if ($type === 'pdf') {
						if (!empty($excel_text)) {
							if (empty($pdf_text)) {
								$pdf_text = $excel_text;
							} else {
								$pdf_text .= sprintf('<br />%s', $excel_text);
							}
						}
						$datarow[] = array('text' => $pdf_text);
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'next_update';
						$refview_text = (empty($excel_text)) ? $refview_empty : sprintf($nbrtoken, $excel_text);
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}

					// remark
					if ($type === 'excel') {
						$datarow[] = array('text' => (empty($referee['Person']['remark'])) ? '' : __($referee['Person']['remark']));
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'remark';
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), (empty($referee['Person']['remark'])) ? $refview_empty : __($referee['Person']['remark']), $filledTemplate);
					}

					// internal remark
					if ($type === 'excel') {
						$datarow[] = array('text' => (empty($referee['Person']['internal_remark'])) ? '' : __($referee['Person']['internal_remark']));
					}

					// remarks
					if ($type === 'pdf') {
						if (!empty($referee['Person']['remark']) || !empty($referee['Person']['internal_remark'])) {
							$pdf_data[] = array('data' => $datarow, 'style' => $statustypes[$referee['RefereeStatus']['sid']]['htmlstyle']);
							$datarow = array();

							$pdf_text = $referee['Person']['remark'];
							if (!empty($referee['Person']['internal_remark'])) {
								if (empty($pdf_text)) {
									$pdf_text = sprintf('intern: %s', $referee['Person']['internal_remark']);
								} else {
									$pdf_text .= sprintf('<br />intern: %s', $referee['Person']['internal_remark']);
								}
							}
							$datarow[] = array('text' => '');
							$datarow[] = array('text' => $pdf_text, 'colspan' => 7);
						}
					}

				}

				// finish row
				if ($type === 'excel') {
					$this->PHPExcel->addTableRow($datarow, $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']);
				}
				if ($type === 'pdf') {
					$pdf_data[] = array('data' => $datarow, 'style' => $statustypes[$referee['RefereeStatus']['sid']]['htmlstyle']);
				}

				if ($type === 'referee_view_zip') {
					$repltoken = 'status_type';
					$refview_text = ($statustypes[$referee['RefereeStatus']['sid']]['remark']) ?
							$statustypes[$referee['RefereeStatus']['sid']]['remark'] :
							$statustypes[$referee['RefereeStatus']['sid']]['title'];
					$refview_text = (empty($refview_text)) ? $refview_empty : $refview_text;
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);

					$zip->addFromString(sprintf('mmd/%s.mmd', sprintf($fletoken, $referee['Referee']['id'])), $filledTemplate);
					$latexallrefs = str_replace(sprintf($tpltoken, 'includepdf'), sprintf('%s%s', sprintf($latexreftoken, $referee['Referee']['id']), sprintf($tpltoken, 'includepdf')), $latexallrefs);
				}

			}

			// finish
			if ($type === 'excel') {
				// legend
				$this->PHPExcel->addTableRow(array());
				$this->PHPExcel->addTableRow(array('text' => array(__('Legende:'))), array('font-weight' => 'bold', 'font-size' => 10));
				foreach ($statustypes as $statustype) {
					if (($statustype['sid'] == StatusType::SID_MANY) ||
							($statustype['sid'] == StatusType::SID_INACTIVESEASON) ||
							($statustype['sid'] == StatusType::SID_OTHER)) {
						$this->PHPExcel->addTableRow(array(array('text' => ($statustype['remark']) ? $statustype['remark'] : $statustype['title'])), $statustype['outputstyle']);
					}
				}

				$this->PHPExcel->addTableRow(array());
				$this->PHPExcel->addTableRow(array('text' => array(__('Zusatzinformationen'))), array('font-weight' => 'bold', 'font-size' => 10));
				foreach ($statustypes as $statustype) {
					if (($statustype['sid'] == StatusType::SID_MANY) ||
							($statustype['sid'] == StatusType::SID_INACTIVESEASON) ||
							($statustype['sid'] == StatusType::SID_OTHER)) {
						$this->PHPExcel->addTableRow(array(array('text' => ($statustype['remark']) ? $statustype['remark'] : $statustype['title'])), array('font-weight' => 'bold', 'font-size' => 10));

						$hasMore = false;
						$excel_text = '';
						foreach ($statustype['referees'] as $referee) {
							if ($hasMore) {
								$excel_text .= ', ';
							}
							$excel_text .= __($this->RefereeFormat->formatPerson($referee, 'fullname'));
							$hasMore = true;
						}
						$this->PHPExcel->addTableTexts($excel_text);
					}
				}
			}

			if ($type === 'pdf') {
				$tcpdf->writeHTML($tcpdf->getTable($pdf_header, $pdf_data), true, false, true, false, '');
				$tcpdf->WriteHTML(sprintf('<p style="font-size: %spt; font-weight: bold;">%s</p>', PDF_FONT_SIZE_MAIN, __('Legende')), true, false, true, false, '');
				foreach ($statustypes as $stleg) {
					$tcpdf->WriteHTML(sprintf('<p style="font-size: %spt; %s">%s</p>',
																		PDF_FONT_SIZE_DATA,
																		$stleg['htmlstyle'],
																		($stleg['remark']) ? h($stleg['remark']) : h($stleg['title'])),
														true, false, true, false, '');
				}
			}

		}

		if ($type === 'excel') {
			$this->PHPExcel->output('VSR.xlsx');
		}

		if ($type === 'referee_view_zip') {
			$latexallrefs = str_replace(sprintf($tpltoken, 'includepdf'), '', $latexallrefs);
			$zip->addFromString(Configure::read('RefMan.template.referee_view_all'), $latexallrefs);
			$zip->close();

			$this->response->file($zipfile);
			echo $this->response;
		}

		if ($type === 'pdf') {
			echo $tcpdf->Output('referees.pdf', 'D');
		}

	} else {
		throw new CakeException(__('Exporttyp "%s" nicht unterstützt!', $type));
	}

?>


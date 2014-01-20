<?php

	if (($type === 'excel') || ($type === 'referee_view_zip')) {

		if ($type === 'excel') {
			// compute different styles
			foreach ($statustypes as &$statustypeedit) {
				$statustypeedit['outputstyle'] = array();

				if ($statustypeedit['style']) {
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
				}

				if ($statustypeedit['color']) {
					$statustypeedit['outputstyle']['color'] = $statustypeedit['color'];
				}

				if ($statustypeedit['bgcolor']) {
					$statustypeedit['outputstyle']['bg-color'] = $statustypeedit['bgcolor'];
				}
			}

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

		if (empty($referees)) {
			if ($type === 'excel') {
				$this->PHPExcel->addTableTexts(__('Es sind keine Schiedsrichter_innen gespeichert.'));
			}
			if ($type === 'referee_view_zip') {
				echo __('Es sind keine Schiedsrichter_innen gespeichert.');
			}
		} else {
			// datarows
			$refcount = 0;
			foreach ($referees as $referee) {

				if ($type === 'excel') {
					$refformat = array();
					$datarow = array();
				}
				if ($type === 'referee_view_zip') {
					$filledTemplate = $template;
					$repltoken = 'date';
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $this->RefereeFormat->formatDate(time(), 'medium'), $filledTemplate);
				}

				if ($type === 'excel') {
					$datarow[] = array('text' => $this->RefereeFormat->formatPerson($referee['Person'], 'name_title'));
					$datarow[] = array('text' => $this->RefereeFormat->formatPerson($referee['Person'], 'first_name'));
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
				foreach ($allrefereerelationtypes as $sid => $refereerelationtype) {
					if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
						$excel_text = '';
						$hasMore = false;
						if (array_key_exists($sid, $referee['RefereeRelation'])) {
							foreach ($referee['RefereeRelation'][$sid] as $refereerelation) {
								if ($hasMore) {
									$excel_text .= '; ';
								}
								if (array_key_exists('Club', $refereerelation)) {
									$excel_text .= $refereerelation['Club']['name'];
								}
								if (array_key_exists('League', $refereerelation)) {
									$excel_text .= $refereerelation['League']['title'];
								}
								$hasMore = true;
							}
						}
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

				if ($isReferee) {

					// email
					$excel_text = '';
					$refview_text = '';
					if (array_key_exists('Contact', $referee) && array_key_exists('Email', $referee['Contact'])) {
						$hasMore = false;
						$printType = (count($referee['Contact']['Email']) > 1);
						foreach ($referee['Contact']['Email'] as $contacttype => $emailkind) {
							$printType |= (count($emailkind) > 1);
							foreach ($emailkind as $email) {
								if ($hasMore) {
									$excel_text .= "\n";
									$refview_text .= "<!-- \\newline -->";
								}
								if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
									$excel_text .=  __('%s: ', $contacttypes[$contacttype]['abbreviation']);
									$refview_text .=  __('%s: ', $contacttypes[$contacttype]['abbreviation']);
								}
								$excel_text .= $this->RefereeFormat->formatEMail($email, 'text');
								$refview_text .= sprintf($nbrtoken, $this->RefereeFormat->formatEMail($email, 'text'));
								$hasMore = true;
							}
						}
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'email';
						$refview_text = (empty($refview_text) || ($refview_text === $nbrtoken)) ? $refview_empty : $refview_text;
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}

					// phone
					$excel_text = '';
					$refview_text = '';
					if (array_key_exists('Contact', $referee) && array_key_exists('PhoneNumber', $referee['Contact'])) {
						$hasMore = false;
						$printType = (count($referee['Contact']['PhoneNumber']) > 1);
						foreach ($referee['Contact']['PhoneNumber'] as $contacttype => $phonekind) {
							$printType |= (count($phonekind) > 1);
							foreach ($phonekind as $phone) {
								if ($hasMore) {
									$excel_text .= "\n";
									$refview_text .= "<!-- \\newline -->";
								}
								if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
									$excel_text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
									$refview_text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
								}
								$excel_text .= $this->RefereeFormat->formatPhone($phone, 'normal');
								$refview_text .= sprintf($nbrtoken, $this->RefereeFormat->formatPhone($phone, 'normal'));
								$hasMore = true;
							}
						}
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
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
					if (array_key_exists('Contact', $referee) && array_key_exists('Address', $referee['Contact'])) {
						$hasMore = false;
						$printType = (count($referee['Contact']['Address']) > 1);
						foreach ($referee['Contact']['Address'] as $contacttype => $addresskind) {
							$printType |= (count($addresskind) > 1);
							foreach ($addresskind as $address) {
								if ($hasMore) {
									$excel_text .= '\n';
								}
								if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
									$excel_text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
								}
								$excel_text .= $this->RefereeFormat->formatAddress($address, 'fulladdress');
								$hasMore = true;
							}
						}
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
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
					if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['since'])) {
						$excel_text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['since'], 'date');
					}
					if ($type === 'excel') {
						$datarow[] = array('text' => $excel_text);
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
					if ($type === 'referee_view_zip') {
						$repltoken = 'next_update';
						$refview_text = (empty($excel_text)) ? $refview_empty : sprintf($nbrtoken, $excel_text);
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
					}

					if ($type === 'excel') {
						$datarow[] = array('text' => (empty($referee['Person']['remark'])) ? '' : __($referee['Person']['remark']));
					}
					if ($type === 'referee_view_zip') {
						$repltoken = 'remark';
						$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), (empty($referee['Person']['remark'])) ? $refview_empty : __($referee['Person']['remark']), $filledTemplate);
					}

				}

				if ($type === 'excel') {
					$this->PHPExcel->addTableRow($datarow, $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']);
				}
				if ($type === 'referee_view_zip') {
					$repltoken = 'status_type';
					$refview_text = ($statustypes[$referee['RefereeStatus']['sid']]['remark']) ?
							$statustypes[$referee['RefereeStatus']['sid']]['remark'] :
							$statustypes[$referee['RefereeStatus']['sid']]['title'];
					$refview_text = (empty($refview_text)) ? $refview_empty : $refview_text;
					$filledTemplate = str_replace(sprintf($tpltoken, $repltoken), $refview_text, $filledTemplate);
				}

				if ($type === 'referee_view_zip') {
					$zip->addFromString(sprintf('mmd/%s.mmd', sprintf($fletoken, ++$refcount)), $filledTemplate);
					$latexallrefs = str_replace(sprintf($tpltoken, 'includepdf'), sprintf('%s%s', sprintf($latexreftoken, $refcount), sprintf($tpltoken, 'includepdf')), $latexallrefs);
				}
			}

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

	} else {
		throw new CakeException(__('Exporttyp "%s" nicht unterstützt!', $type));
	}

?>


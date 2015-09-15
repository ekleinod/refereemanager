<div class="row">
	<div class="col-sm-12">

		<?php
			$tmpForm = $this->Form->create('Referee', array('class' => 'form-horizontal'));

			// person data
			$tmpFieldset = $this->Html->tag('legend', __('Personendaten'));

			$tmpA = array('Person', 'title', __('Titel'));
			$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
												 $tmpA[2],
												 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
												 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
												 false, true);

			$tmpA = array('Person', 'first_name', __('Vorname'));
			$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
												 $tmpA[2],
												 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
												 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
												 false, false);

			$tmpA = array('Person', 'name', __('Nachname'));
			$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
												 $tmpA[2],
												 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
												 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
												 true, false);

			if ($isEditor) {
				$tmpA = array('Person', 'sex_type_id', __('Geschlecht'));
				if (!empty($referee[$tmpA[0]][$tmpA[1]]) || $isEdit) {
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'select', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
														 $tmpA[2],
														 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
														 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														 true, false, 0, $sextypelist);
				}

				$tmpA = array('Person', 'birthday', __('Geburtstag'), __('tt.mm.yyyy'));
				if (!empty($referee[$tmpA[0]][$tmpA[1]]) || $isEdit) {
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'date', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
														 $tmpA[2],
														 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
														 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														 false, false);
				}

				$tmpA = array('Person', 'dayofdeath', __('Todestag'), __('tt.mm.yyyy'));
				if (!empty($referee[$tmpA[0]][$tmpA[1]]) || $isEdit) {
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'date', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
														 $tmpA[2],
														 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
														 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														 false, false);

				}

				$tmpA = array('Person', 'docs_per_letter', __('Dokumentversand'), __('zusätzlich per Brief'));
				$tmpFieldset .= $this->RefereeForm->getInputField($action, 'checkbox',
													sprintf('%s.%s', $tmpA[0], $tmpA[1]),
													$tmpA[2],
													RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
													null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
													false, false);

				$tmpA = array('Person', 'remark', __('Anmerkung'));
				$tmpFieldset .= $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
													 $tmpA[2],
													 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
													 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
													 false, false);

				$tmpA = array('Person', 'internal_remark', __('Interne Anmerkung'));
				$tmpFieldset .= $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
													 $tmpA[2],
													 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
													 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
													 false, false);
			}

			$tmpForm .= $this->Html->tag('fieldset', $tmpFieldset);


			// picture
			if (!empty($referee['Picture']) || $isEdit) {
				$tmpFieldset = $this->Html->tag('legend', __('Bild'));

				$tmpA = array('Picture', 'url', __('Bild'), __('Bild-URL'));
				$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
													 $tmpA[2],
													 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
													 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
													 false, false);

				$tmpA = array('Picture', 'remark', __('Anmerkung'));
				$tmpFieldset .= $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
													 $tmpA[2],
													 RefManTemplate::getReplaceToken(sprintf('%s:%s', strtolower($tmpA[0]), strtolower($tmpA[1]))),
													 null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
													 false, false);

				$tmpForm .= $this->Html->tag('fieldset', $tmpFieldset);
			}

			// contacts
			foreach ($referee['Contact'] as $contactKind => $arrContacts) {
				foreach ($arrContacts as $contactid => $contact) {
					$tmpFieldset = $this->Html->tag('legend', __('Kontakt: %s', $contactKind));

					$tmpID = $contact[$contactKind]['id'];

					switch ($contactKind) {
						case 'Address':
							$tmpA = array('Address', 'street', __('Straße'));
							$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
																sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
																$tmpA[2],
																RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
																null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																true, false);

							$tmpA = array('Address', 'number', __('Hausnummer'));
							$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
																sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
																$tmpA[2],
																RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
																null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																false, false);

							$tmpA = array('Address', 'zip_code', __('PLZ'));
							$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
																sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
																$tmpA[2],
																RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
																null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																false, false);

							$tmpA = array('Address', 'city', __('Stadt'));
							$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
																sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
																$tmpA[2],
																RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
																null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																true, false);
							break;

						case 'Email':
							$tmpA = array('Email', 'email', __('E-Mail-Adresse'));
							$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
																sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
																$tmpA[2],
																RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
																null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																true, false);
							break;

						case 'PhoneNumber':
							$tmpA = array('PhoneNumber', 'country_code', __('Ländervorwahl'));
							$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
																sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
																$tmpA[2],
																RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
																null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																true, false);

							$tmpA = array('PhoneNumber', 'area_code', __('Vorwahl'));
							$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
																sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
																$tmpA[2],
																RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
																null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																true, false);

							$tmpA = array('PhoneNumber', 'number', __('Nummer'));
							$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
																sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
																$tmpA[2],
																RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
																null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																true, false);
							break;

						case 'Url':
							$tmpA = array('Url', 'url', __('URL'));
							$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
																sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
																$tmpA[2],
																RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
																null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																true, false);
							break;
					}

					$tmpID = $contactid;

					$tmpA = array('Contact', 'contact_type_id', __('Kontaktart'));
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'select',
														sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
														$tmpA[2],
														RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
														null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														true, false, 0, $contacttypelist);

					$tmpA = array('Contact', 'title', __('Titel'));
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'text',
														sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
														$tmpA[2],
														RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
														null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														false, false);

					$tmpA = array('Contact', 'is_primary', __('Primärkontakt'), __('Primärkontakt'));
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'checkbox',
														sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
														$tmpA[2],
														RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
														null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														false, false);

					$tmpA = array('Contact', 'editor_only', __('Sichtbarkeit'), __('Nur für Editoren'));
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'checkbox',
														sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
														$tmpA[2],
														RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
														null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														false, false);

					$tmpA = array('Contact', 'remark', __('Anmerkung'));
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'textarea',
														sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
														$tmpA[2],
														RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
														null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														false, false);

					$tmpForm .= $this->Html->tag('fieldset', $tmpFieldset);
				}
			}

			// training level and updates
			foreach ($referee['TrainingLevel'] as $traininglevel) {
				$tmpFieldset = $this->Html->tag('legend', __('Ausbildung: %s', $traininglevel['TrainingLevelType']['display_title']));
				$tmpID = $traininglevel['TrainingLevelType']['id'];

				$tmpA = array('TrainingLevel', 'training_level_type_id', __('Ausbildungsart'));
				$tmpFieldset .= $this->RefereeForm->getInputField($action, 'select',
													sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
													$tmpA[2],
													RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
													null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
													true, false, 0, $trainingleveltypelist);

				$tmpA = array('TrainingLevel', 'since', __('Seit'));
				$tmpFieldset .= $this->RefereeForm->getInputField($action, 'date',
													sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
													$tmpA[2],
													RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
													null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
													true, false);

				foreach ($traininglevel['TrainingUpdate'] as $trainingupdate) {
					$tmpID = $trainingupdate['TrainingUpdate']['id'];

					$tmpA = array('TrainingUpdate', 'update', __('Fortbildung'));
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'date',
														sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
														$tmpA[2],
														RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
														null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														true, false);
				}

				$tmpForm .= $this->Html->tag('fieldset', $tmpFieldset);
			}

			// referee status
			foreach ($referee['RefereeStatus'] as $refereestatus) {
				$tmpFieldset = $this->Html->tag('legend', __('Schiri-Status: Saison %s', $refereestatus['Season']['display_title']));
				$tmpID = $refereestatus['RefereeStatus']['id'];

				$tmpA = array('RefereeStatus', 'status_type_id', __('Statustyp'));
				$tmpFieldset .= $this->RefereeForm->getInputField($action, 'select',
													sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
													$tmpA[2],
													RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
													null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
													true, false, 0, $statustypelist);

				$tmpA = array('RefereeStatus', 'season_id', __('Saison'));
				$tmpFieldset .= $this->RefereeForm->getInputField($action, 'select',
													sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
													$tmpA[2],
													RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
													null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
													true, false, 0, $seasonlist);

				if ($isEditor) {
					$tmpA = array('RefereeStatus', 'remark', __('Anmerkung'));
					$tmpFieldset .= $this->RefereeForm->getInputField($action, 'textarea',
														sprintf('%s.%d.%s', $tmpA[0], $tmpID, $tmpA[1]),
														$tmpA[2],
														RefManTemplate::getReplaceToken(sprintf('%s:%d:%s', strtolower($tmpA[0]), $tmpID, strtolower($tmpA[1]))),
														null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
														false, false);
				}

				$tmpForm .= $this->Html->tag('fieldset', $tmpFieldset);
			}


			// fill referee data and output form
			echo RefManTemplate::replaceRefereeData($tmpForm, $referee, 'text', 'html');
		?>

			<fieldset>
				<legend><?php echo __('Vereine und Wünsche'); ?></legend>
					<?php
						foreach ($refereerelationtypes as $sid => $refereerelationtype) {
							if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
								$tmpA = array('RefereeRelation', 'id', $refereerelationtype['RefereeRelationType']['title']);
								$tmpARem = array('RefereeRelation', 'remark', __('.'));

								$count = 0;
								foreach ($referee['RefereeRelation'] as $refrel) {
									if ($refrel['referee_relation_type_id'] == $refereerelationtype['RefereeRelationType']['id']) {

										$tmpValue = 0;
										$tmpAr = null;

										if (!empty($refrel['club_id'])) {
											$tmpValue = $refrel['club_id'];
											$tmpAr = $clubarray;
										}

										if (!empty($tmpValue)) {
											echo $this->RefereeForm->getInputField($action, 'select', sprintf('%s.%s.%d', $tmpA[0], $tmpA[1], $count),
																														 $tmpA[2], $tmpValue, true, '', 0, false, $tmpAr);

											$tmpValueRem = (empty($referee[$tmpARem[0]]) || empty($referee[$tmpARem[0]][$tmpARem[1]])) ? '' : $referee[$tmpARem[0]][$tmpARem[1]];
											echo $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s.%d', $tmpARem[0], $tmpARem[1], $count),
																														 $tmpARem[2], $tmpValueRem, false,
																														 (count($tmpARem) > 3) ? $tmpARem[3] : $tmpARem[2]);
										}

										$count++;
									}
								}

								if ($count == 0) {
									$tmpValue = 0;
									$tmpAr = $clubarray;
									echo $this->RefereeForm->getInputField($action, 'select', sprintf('%s.%s.%d', $tmpA[0], $tmpA[1], $count),
																												 $tmpA[2], $tmpValue, true, '', 0, false, $tmpAr);
								}
							}
						}

						/*foreach ($refereerelationtypes as $refereerelationtype) {
							foreach ($referee['RefereeRelation'] as $refereerelation) {

								if ($refereerelation['referee_relation_type_id'] == $refereerelationtype['id']) {
									if ($refereerelationtype['is_membership'] == 1) {
										echo $this->RefereeForm->getInputField($action, 'select', 'RefereeRelation.id', __($refereerelationtype['title']), $refereerelation['club_id'], true, '', 0, false, $clubarray);
									} else {
										if ($isEditor) {
										echo $this->RefereeForm->getInputField($action, 'select', 'RefereeRelation.id', __($refereerelationtype['title']), $refereerelation['club_id'], false, '', 0, false, $clubarray);
										}
									}
								}

							}
						}*/

					?>
			</fieldset>

			<?php if (($action === 'add') || ($action === 'edit')) { ?>
				<div class="form-group">
					<div class="col-sm-12">
						<?php echo $this->Form->button(__('Daten speichern'), array('type' => 'submit', 'class' => 'btn btn-sm btn-default')); ?>
					</div>
				</div>
			<?php } ?>

		<?php echo $this->Form->end(); ?>
	</div>
</div>

<?php debug($referee); ?>


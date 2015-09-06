<div class="row">
	<div class="col-sm-12">

		<?php echo $this->Form->create('Referee', array('class' => 'form-horizontal')); ?>

			<fieldset>
				<legend><?php echo __('Personendaten'); ?></legend>

				<?php

					$tmpA = array('Person', 'title', __('Titel'));
					$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
					echo $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpValue, null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																								 false, true);

					$tmpA = array('Person', 'first_name', __('Vorname'));
					$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
					echo $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpValue, null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																								 false, false);

					$tmpA = array('Person', 'name', __('Nachname'));
					$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
					echo $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpValue, null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																								 true, false);

					if ($isEditor) {

						$tmpA = array('Person', 'sex_type_id', __('Geschlecht'));
						$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
						echo $this->RefereeForm->getInputField($action, 'select', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																									 $tmpA[2], $tmpValue, null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																									 true, false, 0, $sextypearray);

						$tmpA = array('Person', 'birthday', __('Geburtstag'), __('tt.mm.yyyy'));
						$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
						$tmpValue = $this->RefereeFormat->formatDate($tmpValue, 'date');
						echo $this->RefereeForm->getInputField($action, 'date', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																									 $tmpA[2], $tmpValue, null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																									 false, false);

						$tmpA = array('Person', 'dayofdeath', __('Todestag'), __('tt.mm.yyyy'));
						$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
						$tmpValue = $this->RefereeFormat->formatDate($tmpValue, 'date');
						echo $this->RefereeForm->getInputField($action, 'date', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																									 $tmpA[2], $tmpValue, null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																									 false, false);

					}

					$tmpA = array('Person', 'remark', __('Anmerkung'));
					$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
					echo $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpValue, null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																								 false, false);

					$tmpA = array('Person', 'internal_remark', __('Interne Anmerkung'));
					$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
					echo $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpValue, null, (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2],
																								 false, false);
				?>
			</fieldset>

			<?php if ($isReferee) { ?>
				<fieldset>
					<legend><?php echo __('Bild'); ?></legend>
					<ol>
						<?php

							$tmpA = array('Picture', 'url', __('Bild'), __('Bild-URL'));
							$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
							echo $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																										 $tmpA[2], $tmpValue, true,
																										 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2]);

							$tmpA = array('Picture', 'remark', __('Anmerkung'));
							$tmpValue = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
							echo $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																										 $tmpA[2], $tmpValue, false,
																										 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2]);

						?>
					</ol>
				</fieldset>
			<?php } ?>

			<fieldset>
				<legend><?php echo __('Vereine und Wünsche'); ?></legend>
				<ol>
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
				</ol>
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


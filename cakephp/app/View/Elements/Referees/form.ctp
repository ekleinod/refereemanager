<?php echo $this->Form->create('Referee'); ?>

	<fieldset>
		<legend><?php echo __('Die Person'); ?></legend>
		<ol>
			<?php

				$tmpA = array('Person', 'title', __('Titel'));
				$tmpV = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
				echo $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																							 $tmpA[2], $tmpV, false,
																							 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2], 50, true);

				$tmpA = array('Person', 'first_name', __('Vorname'));
				$tmpV = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
				echo $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																							 $tmpA[2], $tmpV, false,
																							 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2]);

				$tmpA = array('Person', 'name', __('Nachname'));
				$tmpV = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
				echo $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																							 $tmpA[2], $tmpV, true,
																							 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2]);

				if ($isEditor) {

					$tmpA = array('Person', 'sex_type_id', __('Geschlecht'));
					$tmpV = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? 0 : $referee[$tmpA[0]][$tmpA[1]];
					echo $this->RefereeForm->getInputField($action, 'select', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpV, true, '', 0, false, $sextypearray);

					$tmpA = array('Person', 'birthday', __('Geburtstag'), __('tt.mm.yyyy'));
					$tmpV = $this->RefereeFormat->formatDate((empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]], 'date');
					echo $this->RefereeForm->getInputField($action, 'date', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpV, false,
																								 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2], 10);

					$tmpA = array('Person', 'dayofdeath', __('Todestag'), __('tt.mm.yyyy'));
					$tmpV = $this->RefereeFormat->formatDate((empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]], 'date');
					echo $this->RefereeForm->getInputField($action, 'date', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpV, false,
																								 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2], 10);

				}

				$tmpA = array('Person', 'remark', __('Anmerkung'));
				$tmpV = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
				echo $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																							 $tmpA[2], $tmpV, false,
																							 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2]);

				$tmpA = array('Person', 'internal_remark', __('Interne Anmerkung'));
				$tmpV = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
				echo $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																							 $tmpA[2], $tmpV, false,
																							 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2]);
			?>
		</ol>
	</fieldset>

	<?php if ($isReferee) { ?>
		<fieldset>
			<legend><?php echo __('Bild'); ?></legend>
			<ol>
				<?php

					$tmpA = array('Picture', 'url', __('Bild'), __('Bild-URL'));
					$tmpV = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
					echo $this->RefereeForm->getInputField($action, 'text', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpV, true,
																								 (count($tmpA) > 3) ? $tmpA[3] : $tmpA[2]);

					$tmpA = array('Picture', 'remark', __('Anmerkung'));
					$tmpV = (empty($referee[$tmpA[0]]) || empty($referee[$tmpA[0]][$tmpA[1]])) ? '' : $referee[$tmpA[0]][$tmpA[1]];
					echo $this->RefereeForm->getInputField($action, 'textarea', sprintf('%s.%s', $tmpA[0], $tmpA[1]),
																								 $tmpA[2], $tmpV, false,
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

						$count = 0;
						foreach ($referee['RefereeRelation'] as $refrel) {
							$tmpV = $refrel['referee_relation_type_id'];
							echo $this->RefereeForm->getInputField($action, 'select', sprintf('%s.%s.%d', $tmpA[0], $tmpA[1], $count),
																										 $tmpA[2], $tmpV, true, '', 0, false, $refereerelationtypearray);
							$count++;
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
		<fieldset>
			<?php echo $this->Form->button(__('Speichern'), array('type' => 'submit')); ?>
			<?php echo $this->Form->button(__('Zurücksetzen'), array('type' => 'reset')); ?>
		</fieldset>
	<?php } ?>

<?php echo $this->Form->end(); ?>

<?php debug($referee); ?>


<?php echo $this->Form->create('RefereeAssignment'); ?>

	<fieldset>
		<legend><?php echo __('Die Person'); ?></legend>
		<ol>
			<?php
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.title', __('Titel'), $referee['Person']['title'], false, __('Titel'), 50, true);
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.first_name', __('Vorname'), $referee['Person']['first_name'], false, __('Vorname'));
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.name', __('Nachname'), $referee['Person']['name'], true, __('Nachname'));
				echo $this->RefereeForm->getInputField($action, 'textarea', 'Person.remark', __('Anmerkung'), $referee['Person']['remark'], false, __('Anmerkung'));

				if ($isEditor) {

					echo $this->RefereeForm->getInputField($action, 'select', 'Person.sex_type_id', __('Geschlecht'), $referee['Person']['sex_type_id'], true, '', 0, false, $sextypearray);
					echo $this->RefereeForm->getInputField($action, 'date', 'Person.birthday', __('Geburtstag'), $this->RefereeFormat->formatDate($referee['Person']['birthday'], 'date'), false, __('tt.mm.yyyy'), 10);

				}

			?>
		</ol>
	</fieldset>

	<fieldset>
		<legend><?php echo __('Verein'); ?></legend>
		<ol>
			<?php
				foreach ($refereerelationtypes as $refereerelationtype) {
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
				}

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


<!--
			$columns[] = __('Bild');
				$columns[] = __('Club');
					$columns[] = __('Ausbildung (seit)');
				if ($isReferee) {
					$columns[] = __('E-Mail');
					$columns[] = __('Telefon');
				}
				if ($isEditor) {
					$columns[] = __('Adresse');
					$columns[] = __('Letzte Fortbildung');
					$columns[] = __('Nächste Fortbildung');
				}
-->


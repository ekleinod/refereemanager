<?php echo $this->Form->create('Referee'); ?>

	<fieldset>
		<legend><?php echo __('Die Person'); ?></legend>
		<ol>
			<?php
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.title', __('Titel'), $referee['Person']['title'], false, __('Titel'), 50, true);
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.first_name', __('Vorname'), $referee['Person']['first_name'], false, __('Vorname'));
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.name', __('Nachname'), $referee['Person']['name'], true, __('Nachname'));

				if ($isEditor) {
					echo $this->RefereeForm->getInputField($action, 'select', 'Person.sex_type', __('Geschlecht'), $referee['Person']['sex_type_id'], true, '', 0, false, $sextypearray);
//					echo $this->RefereeForm->getInputField($action, 'date', 'Person.birthday', __('Geburtstag'), $this->RefereeFormat->formatDate($referee['Person']['birthday'], 'date'), false, __('tt.mm.yyyy'), 20);
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


<!--					$columns[] = __('Bild');
				}
				$columns[] = __('Vorname');
				$columns[] = __('Name');
				$columns[] = __('Club');
				if ($isReferee) {
					$columns[] = __('E-Mail');
					$columns[] = __('Telefon');
				}
				if ($isEditor) {
					$columns[] = __('Adresse');
					$columns[] = __('Geschlecht');
					$columns[] = __('Geburtstag');
					$columns[] = __('Ausbildung (seit)');
					$columns[] = __('Letzte Fortbildung');
					$columns[] = __('Nächste Fortbildung');
				}
				$columns[] = __('Anmerkung');-->


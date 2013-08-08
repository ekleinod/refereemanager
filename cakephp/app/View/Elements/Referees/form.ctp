<?php echo $this->Form->create('Referee'); ?>

	<fieldset>
		<legend><?php echo __('Die Person'); ?></legend>
		<ol>
			<?php
				// first name
				echo $this->RefereeForm->getInputText($action, 'Person.first_name', __('Vorname'), $referee['Person']['first_name'], true, __('Vorname'), 100, true);

			?>
		</ol>
	</fieldset>

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


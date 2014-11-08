<?php echo $this->Form->create('Person'); ?>

	<?php
		// ids
		if (array_key_exists('Person', $person)) {
			echo $this->Form->input('Person.id', array('type' => 'hidden', 'value' => $person['Person']['id']));
		}
	?>


	<fieldset>
		<legend><?php echo __('Daten'); ?></legend>
		<ol>
			<?php

				// name
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['name'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.name', __('Name'), $tmpValue, true, '', 100, true);

				// title
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['title'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.title', __('Titel'), $tmpValue, true, '', 50);

				// first_name
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['first_name'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.first_name', __('Vorname'), $tmpValue, true, '', 100, true);

				// SexType type
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['sex_type_id'] : '';
				echo $this->RefereeForm->getInputField($action, 'select', 'Person.sex_type_id', __('Geschlecht'), $tmpValue, true, '', 0, false, $sextypearray);

				// birthday
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['birthday'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.birthday.date', __('Geburtstag'), $this->RefereeFormat->formatDate($tmpValue, 'date'), true, __('tt.mm.yyyy'), 10);

				// day of death
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['dayofdeath'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.dayofdeath.date', __('Todestag'), $this->RefereeFormat->formatDate($tmpValue, 'date'), true, __('tt.mm.yyyy'), 10);

			?>
		</ol>
	</fieldset>

	<fieldset>
		<legend><?php echo __('Weiteres'); ?></legend>
		<ol>
			<?php

				// picture url
				$tmpValue = (array_key_exists('Picture', $person)) ? $person['Picture']['url'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Picture.url', __('Bild'), $tmpValue, false, __('http://...'), 200);

				// remark
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['remark'] : '';
				echo $this->RefereeForm->getInputField($action, 'textarea', 'Person.remark', __('Anmerkung'), $tmpValue, false);

				// internal remark
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['internal_remark'] : '';
				echo $this->RefereeForm->getInputField($action, 'textarea', 'Person.internal_remark', __('Interne Anmerkung'), $tmpValue, false);

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

<?php echo pr($person); ?>


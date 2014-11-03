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

				// title
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['title'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Person.title', __('Titel'), $tmpValue, true, '', 50);

				// SexType type
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['sex_type_id'] : '';
				echo $this->RefereeForm->getInputField($action, 'select', 'Person.sex_type_id', __('Geschlecht'), $tmpValue, true, '', 0, false, $sextypearray);

				// remark
				$tmpValue = (array_key_exists('Person', $person)) ? $person['Person']['remark'] : '';
				echo $this->RefereeForm->getInputField($action, 'textarea', 'Person.remark', __('Anmerkung'), $tmpValue, false);

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


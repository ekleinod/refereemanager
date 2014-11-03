<?php echo $this->Form->create('Club'); ?>

	<?php
		// ids
		if (array_key_exists('Club', $club)) {
			echo $this->Form->input('Club.id', array('type' => 'hidden', 'value' => $club['Club']['id']));
		}
	?>


	<fieldset>
		<legend><?php echo __('Daten'); ?></legend>
		<ol>
			<?php

				// name
				$tmpValue = (array_key_exists('Club', $club)) ? $club['Club']['name'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Club.name', __('Name'), $tmpValue, true, '', 100);

				// abbreviation
				$tmpValue = (array_key_exists('Club', $club)) ? $club['Club']['abbreviation'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Club.abbreviation', __('Abkürzung'), $tmpValue, false, '', 100);

				// remark
				$tmpValue = (array_key_exists('Club', $club)) ? $club['Club']['remark'] : '';
				echo $this->RefereeForm->getInputField($action, 'textarea', 'Club.remark', __('Anmerkung'), $tmpValue, false);

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

<?php echo pr($club); ?>


<?php echo $this->Form->create('League'); ?>

	<?php
		// ids
		if (array_key_exists('League', $league)) {
			echo $this->Form->input('League.id', array('type' => 'hidden', 'value' => $league['League']['id']));
		}
	?>


	<fieldset>
		<legend><?php echo __('Daten'); ?></legend>
		<ol>
			<?php

				// title
				$tmpValue = (array_key_exists('League', $league)) ? $league['League']['title'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'League.title', __('Titel'), $tmpValue, true, '', 100);

				// abbreviation
				$tmpValue = (array_key_exists('League', $league)) ? $league['League']['abbreviation'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'League.abbreviation', __('Abkürzung'), $tmpValue, true, '', 20);

				// league type
				$tmpValue = (array_key_exists('League', $league)) ? $league['League']['league_type_id'] : '';
				echo $this->RefereeForm->getInputField($action, 'select', 'League.league_type_id', __('Liga-Typ'), $tmpValue, true, '', 0, false, $leaguetypearray);

				// remark
				$tmpValue = (array_key_exists('League', $league)) ? $league['League']['remark'] : '';
				echo $this->RefereeForm->getInputField($action, 'textarea', 'League.remark', __('Anmerkung'), $tmpValue, false);

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

<?php echo pr($league); ?>


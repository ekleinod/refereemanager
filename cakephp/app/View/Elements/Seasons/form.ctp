<?php echo $this->Form->create('Season'); ?>

	<?php
		// ids
		if (array_key_exists('Season', $season)) {
			echo $this->Form->input('Season.id', array('type' => 'hidden', 'value' => $season['Season']['id']));
		}
	?>


	<fieldset>
		<legend><?php echo __('Daten'); ?></legend>
		<ol>
			<?php

				// start year
				$tmpValue = (array_key_exists('Season', $season)) ? $season['Season']['year_start'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Season.year_start', __('Startjahr'), $tmpValue, true, '####', 10, true);

				// title
				$tmpValue = (array_key_exists('Season', $season)) ? $season['Season']['title'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Season.title', __('Titel'), $tmpValue, false, 'Titel', 100);

				// editor only?
				$tmpValue = (array_key_exists('Season', $season)) ? !empty($season['Season']['editor_only']) : false;
				echo $this->RefereeForm->getInputField($action, 'checkbox', 'Season.editor_only', __('Nur Editoren?'), $tmpValue, false);

				// remark
				$tmpValue = (array_key_exists('Season', $season)) ? $season['Season']['remark'] : '';
				echo $this->RefereeForm->getInputField($action, 'textarea', 'Season.remark', __('Anmerkung'), $tmpValue, false, '', 1000);

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

<?php echo pr($season); ?>


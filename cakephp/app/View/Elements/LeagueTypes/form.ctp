<?php echo $this->Form->create('LeagueType'); ?>

	<?php
		// ids
		if (array_key_exists('LeagueType', $leaguetype)) {
			echo $this->Form->input('LeagueType.id', array('type' => 'hidden', 'value' => $leaguetype['LeagueType']['id']));
		}
	?>


	<fieldset>
		<legend><?php echo __('Daten'); ?></legend>
		<ol>
			<?php

				// title
				$tmpValue = (array_key_exists('LeagueType', $leaguetype)) ? $leaguetype['LeagueType']['title'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'LeagueType.title', __('Titel'), $tmpValue, true, '', 100);

				// SexType type
				$tmpValue = (array_key_exists('LeagueType', $leaguetype)) ? $leaguetype['LeagueType']['sex_type_id'] : '';
				echo $this->RefereeForm->getInputField($action, 'select', 'LeagueType.sex_type_id', __('Liga-Typ'), $tmpValue, true, '', 0, false, $sextypearray);

				// remark
				$tmpValue = (array_key_exists('LeagueType', $leaguetype)) ? $leaguetype['LeagueType']['remark'] : '';
				echo $this->RefereeForm->getInputField($action, 'textarea', 'LeagueType.remark', __('Anmerkung'), $tmpValue, false);

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

<?php echo pr($leaguetype); ?>


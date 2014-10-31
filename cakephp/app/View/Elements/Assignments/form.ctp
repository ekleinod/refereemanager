<?php echo $this->Form->create('Assignment'); ?>

	<fieldset>
		<legend><?php echo __('Daten'); ?></legend>
		<ol>
			<?php

				// date
				$tmpValue = '';
				if (array_key_exists('Assignment', $assignment) &&
						 ($assignment['Assignment']['start'] != null)) {
						$tmpValue = $assignment['Assignment']['start'];
				}
				echo $this->RefereeForm->getInputField($action, 'text', 'Assignment.start.date', __('Datum'), $this->RefereeFormat->formatDate($tmpValue, 'date'), true, __('tt.mm.yyyy'), 10, true);
				echo $this->RefereeForm->getInputField($action, 'text', 'Assignment.start.time', __('Zeit'), $this->RefereeFormat->formatDate($tmpValue, 'time'), true, __('hh:mm'), 5);

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

<?php echo pr($assignment); ?>


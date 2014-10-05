<?php echo $this->Form->create('RefereeAssignment'); ?>

	<fieldset>
		<legend><?php echo __('Daten'); ?></legend>
		<ol>
			<?php
				// date
				$tmpValue = '';
				if (array_key_exists('RefereeAssignment', $refereeassignment) &&
						 ($refereeassignment['RefereeAssignment']['start'] != null)) {
						$tmpValue = $refereeassignment['RefereeAssignment']['start'];
				}
				echo $this->RefereeForm->getInputField($action, 'text', 'RefereeAssignment.start.date', __('Datum'), $this->RefereeFormat->formatDate($tmpValue, 'date'), true, __('tt.mm.yyyy'), 10, true);
				echo $this->RefereeForm->getInputField($action, 'text', 'RefereeAssignment.start.time', __('Zeit'), $this->RefereeFormat->formatDate($tmpValue, 'time'), true, __('hh:mm'), 5);

				if ($isEditor) {


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

<?php echo pr($refereeassignment); ?>


<div class="row">
	<div class="col-sm-12">
		<?php echo $this->Form->create('Assignments', array('class' => 'form-horizontal')); ?>

			<fieldset>
				<legend><?php echo __('CVS-Datei'); ?></legend>

				<?php
					$tmpValue = empty($importdata) ? null : $importdata['Assignments']['csvfile'];
					echo $this->RefereeForm->getInputField(null, 'text', 'csvfile',
																								 __('CSV-Datei'), $tmpValue, __('Verzeichnis: "%s".', TMP), __('Dateiname'),
																								 true, true);
				?>
			</fieldset>


			<fieldset>
				<legend><?php echo __('Einstellungen'); ?></legend>
				<?php
					$tmpValue = empty($importdata) ? null : $importdata['Assignments']['delimiter'];
					echo $this->RefereeForm->getInputField(null, 'text', 'delimiter',
																								 __('Trennzeichen'), $tmpValue, null, __('Trennzeichen (default: ;)'));

					$tmpValue = empty($importdata) ? null : $importdata['Assignments']['enclosure'];
					echo $this->RefereeForm->getInputField(null, 'text', 'enclosure',
																								 __('Textbegrenzer'), $tmpValue, null, __('Textbegrenzer (default: ")'));

					$tmpValue = empty($importdata) ? null : $importdata['Assignments']['escape'];
					echo $this->RefereeForm->getInputField(null, 'text', 'escape',
																								 __('Escape-Zeichen'), $tmpValue, null, __('Escape-Zeichen (default: \\\\)'));
				?>
			</fieldset>

			<div class="form-group">
				<div class="col-sm-12">
					<?php echo $this->Form->button(__('Import starten'), array('type' => 'submit', 'class' => 'btn btn-sm btn-default')); ?>
				</div>
			</div>

		<?php echo $this->Form->end(); ?>
	</div>
</div>


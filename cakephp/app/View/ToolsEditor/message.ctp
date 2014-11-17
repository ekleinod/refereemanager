<?php if (isset($messageresult)) { ?>

	<p><?php echo $this->RefereeFormat->formatMultiline($messageresult, '</p><p>'); ?></p>

<?php } else { ?>

	<?php echo $this->element('filter');	?>

	<?php echo $this->Form->create('ToolsEditor'); ?>
		<?php echo $this->Form->hidden('Filter.season', array('value' => $season['id'])); ?>

		<fieldset>
			<legend><?php echo __('Einstellungen'); ?></legend>
			<ol>
				<?php
					echo $this->RefereeForm->getInputField(null, 'select', 'recipient', __('Empfänger'), null, true, null, 0, false,
																								 array('a' => __('Alle SR'),
																											 'm' => __('SR mit E-Mail-Adresse'),
																											 's' => __('SR mit Post-Adresse')));
				?>
			</ol>
		</fieldset>

		<fieldset>
			<legend><?php echo __('Nachricht (mit Platzhaltern)'); ?></legend>
			<ol>
				<?php
					echo $this->RefereeForm->getInputField(null, 'textarea', 'body', __('Text'), null, true, __('Nachricht'), false);
					echo $this->RefereeForm->getInputField(null, 'text', 'subject', __('Betreff'), null, true, __('Betreff'));
					echo $this->RefereeForm->getInputField(null, 'text', 'opening', __('Anrede'), 'Hallo **generated first_name**,', true, __('Hallo **generated first_name**,'));
					echo $this->RefereeForm->getInputField(null, 'text', 'closing', __('Schlussformel'), 'Mit sportlichen Grüßen,', true, __('Mit sportlichen Grüßen,'));
					echo $this->RefereeForm->getInputField(null, 'text', 'signature', __('Unterschrift'), 'Ekkart.', true, __('Ekkart.'));
					echo $this->RefereeForm->getInputField(null, 'text', 'encl', __('Dateianhang'), null, false, __('Dateianhang'));
				?>
			</ol>
		</fieldset>

		<fieldset>
			<?php echo $this->Form->button(__('Erzeugen'), array('type' => 'submit')); ?>
			<?php echo $this->Form->button(__('Zurücksetzen'), array('type' => 'reset')); ?>
		</fieldset>

	<?php echo $this->Form->end(); ?>

<?php } ?>


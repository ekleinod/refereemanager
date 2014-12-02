<?php if (isset($messageresult)) { ?>

	<p><?php echo $this->RefereeFormat->formatMultiline($messageresult, '</p><p>'); ?></p>

	<h3><?php echo __('Nachrichtendetails'); ?></h3>

<?php } ?>

<?php echo $this->Form->create('ToolsEditor'); ?>

	<fieldset>
		<legend><?php echo __('Einstellungen'); ?></legend>
		<ol>
			<?php
				echo $this->RefereeForm->getInputField(null, 'select', 'Filter.season', __('Saison'), $season['id'], false, null, 0, true, $seasonarray);

				$tmpValue = empty($messagedata) ? 'i' : $messagedata['ToolsEditor']['recipient'];
				echo $this->RefereeForm->getInputField(null, 'select', 'recipient', __('Empfänger'), $tmpValue, true, null, 0, false,
																							 array('a' => __('Alle SR'),
																										 'm' => __('SR mit E-Mail-Adresse'),
																										 's' => __('SR mit Post-Adresse'),
																										 'i' => __('Mich (Test)')));

				$tmpValue = empty($messagedata) ? 's' : $messagedata['ToolsEditor']['mailkind'];
				echo $this->RefereeForm->getInputField(null, 'radio', 'mailkind', __('Mailversand'), $tmpValue, true, null, 0, false,
																							 array('s' => __('Einzelne E-Mail pro Schiri'),
																										 'b' => __('Schiris als BCC')));

				$tmpValue = empty($messagedata) ? false : $messagedata['ToolsEditor']['test_only'];
				echo $this->RefereeForm->getInputField(null, 'checkbox', 'test_only', __('Testen?'), null, false);
			?>
		</ol>
	</fieldset>

	<fieldset>
		<legend><?php echo __('Nachricht (mit Platzhaltern)'); ?></legend>
		<ol>
			<?php
				$tmpValue = empty($messagedata) ? null : $messagedata['ToolsEditor']['body'];
				echo $this->RefereeForm->getInputField(null, 'textarea', 'body', __('Text'), $tmpValue, true, __('Nachricht'), false);

				$tmpValue = empty($messagedata) ? null : $messagedata['ToolsEditor']['subject'];
				echo $this->RefereeForm->getInputField(null, 'text', 'subject', __('Betreff'), $tmpValue, true, __('Betreff'));

				$tmpValue = empty($messagedata) ? Configure::read('RefMan.message.opening') : $messagedata['ToolsEditor']['opening'];
				echo $this->RefereeForm->getInputField(null, 'text', 'opening', __('Anrede'), $tmpValue, true, __('Anrede'));

				$tmpValue = empty($messagedata) ? Configure::read('RefMan.message.closing') : $messagedata['ToolsEditor']['closing'];
				echo $this->RefereeForm->getInputField(null, 'text', 'closing', __('Schlussformel'), $tmpValue, true, __('Schlussformel'));

				$tmpValue = empty($messagedata) ? Configure::read('RefMan.message.signature') : $messagedata['ToolsEditor']['signature'];
				echo $this->RefereeForm->getInputField(null, 'text', 'signature', __('Unterschrift'), $tmpValue, true, __('Unterschrift'));

				$tmpValue = empty($messagedata) ? null : $messagedata['ToolsEditor']['attachment'];
				echo $this->RefereeForm->getInputField(null, 'text', 'attachment', __('Dateianhang'), $tmpValue, false, __('Dateianhang'));
			?>
		</ol>
	</fieldset>

	<fieldset>
		<?php echo $this->Form->button(__('Erzeugen'), array('type' => 'submit')); ?>
		<?php echo $this->Form->button(__('Zurücksetzen'), array('type' => 'reset')); ?>
	</fieldset>

<?php echo $this->Form->end(); ?>


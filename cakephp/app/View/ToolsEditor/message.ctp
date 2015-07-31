<div class="row">
	<div class="col-sm-12">
		<?php echo $this->Form->create('ToolsEditor', array('class' => 'form-horizontal')); ?>

			<fieldset>
				<legend><?php echo __('Nachricht versenden'); ?></legend>
				<p>
					<span class="help-block"><?php echo __('Der Einsatz von Platzhaltern ist möglich.'); ?></span>
				</p>

				<?php
					$tmpValue = empty($messagedata) ? null : $messagedata['ToolsEditor']['subject'];
					echo $this->RefereeForm->getInputField(null, 'text', 'subject',
																								 __('Betreff'), $tmpValue, null, __('Betreff'),
																								 true, true);

					$tmpValue = empty($messagedata) ? Configure::read('RefMan.message.opening') : $messagedata['ToolsEditor']['opening'];
					echo $this->RefereeForm->getInputField(null, 'text', 'opening',
																								 __('Anrede'), $tmpValue, null, __('Anrede'),
																								 true);

					$tmpValue = empty($messagedata) ? null : $messagedata['ToolsEditor']['body'];
					echo $this->RefereeForm->getInputField(null, 'textarea', 'body',
																								 __('Text'), $tmpValue, null, __('Nachricht'),
																								 true);

					$tmpValue = empty($messagedata) ? Configure::read('RefMan.message.closing') : $messagedata['ToolsEditor']['closing'];
					echo $this->RefereeForm->getInputField(null, 'text', 'closing',
																								 __('Schlussformel'), $tmpValue, null, __('Schlussformel'),
																								 true);

					$tmpValue = empty($messagedata) ? Configure::read('RefMan.message.signature') : $messagedata['ToolsEditor']['signature'];
					echo $this->RefereeForm->getInputField(null, 'text', 'signature',
																								 __('Unterschrift'), $tmpValue, null, __('Unterschrift'),
																								 true);

					$tmpValue = empty($messagedata) ? null : $messagedata['ToolsEditor']['attachment'];
					echo $this->RefereeForm->getInputField(null, 'text', 'attachment',
																								 __('Dateianhänge'), $tmpValue, sprintf('Verzeichnis: "%s". Mehrere Dateien semikolonsepariert angeben.', TMP), __('Dateianhänge'));
				?>
			</fieldset>


			<fieldset>
				<legend><?php echo __('Einstellungen'); ?></legend>
				<ol>
					<?php
						echo $this->RefereeForm->getInputField(null, 'select', 'Filter.season',
																									 __('Saison'), $season['Season']['id'], null, null,
																									 true, false, 0,
																									 $seasonarray);

						$tmpValue = empty($messagedata) ? 'i' : $messagedata['ToolsEditor']['recipient'];
						echo $this->RefereeForm->getInputField(null, 'select', 'recipient',
																									 __('Empfänger'), $tmpValue, null, null,
																									 true, false, 0,
																									 array('a' => __('Alle SR'),
																												 'm' => __('SR mit E-Mail-Adresse'),
																												 's' => __('SR mit Post-Adresse'),
																												 'i' => __('Nur ich')));

						$tmpValue = empty($messagedata) ? 's' : $messagedata['ToolsEditor']['mailkind'];
						echo $this->RefereeForm->getInputField(null, 'radio', 'mailkind',
																									 __('Mailversand'), $tmpValue, null, null,
																									 true, false, 0,
																									 array('s' => __('Einzelne E-Mail pro Schiri'),
																												 'b' => __('Schiris als BCC')));

						$tmpValue = empty($messagedata) ? false : $messagedata['ToolsEditor']['test_only'];
						echo $this->RefereeForm->getInputField(null, 'checkbox', 'test_only',
																									 __('Sendemodus'), $tmpValue, 'Die Nachricht wird erzeugt und verarbeitet aber es wird keine E-Mail gesendet und kein PDF erzeugt.', 'nur Test');
					?>
				</ol>
			</fieldset>

			<div class="form-group">
				<div class="col-sm-12">
					<?php echo $this->Form->button(__('Nachricht versenden'), array('type' => 'submit', 'class' => 'btn btn-sm btn-default')); ?>
				</div>
			</div>

		<?php echo $this->Form->end(); ?>
	</div>
</div>


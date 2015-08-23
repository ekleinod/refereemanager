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

				?>
			</fieldset>


			<fieldset>
				<legend><?php echo __('Dateianhänge'); ?></legend>
				<?php
					$tmpValue = empty($messagedata) ? null : $messagedata['ToolsEditor']['attachment'];
					echo $this->RefereeForm->getInputField(null, 'text', 'attachment',
																								 __('Dateien'), $tmpValue, __('Verzeichnis: "%s%s". Mehrere Dateien semikolonsepariert angeben.', TMP, Configure::read('RefMan.template.attachments.path')), __('Dateien'));

					$tmpValue = empty($messagedata) ? false : $messagedata['ToolsEditor']['person_data'];
					echo $this->RefereeForm->getInputField(null, 'checkbox', 'person_data',
																								 __('Personendaten'), $tmpValue, __('Generierte Dateien in "%s%s%s.pdf".', TMP, Configure::read('RefMan.template.person_data.path'), sprintf(Configure::read('RefMan.template.person_data.file'), __('Name'), __('Vorname'), 0)), __('Generierte Personendaten'));

					$tmpValue = empty($messagedata) ? false : $messagedata['ToolsEditor']['assignments_all'];
					echo $this->RefereeForm->getInputField(null, 'checkbox', 'assignments_all',
																								 __('Einsatzplan'), $tmpValue, __('Generierte Datei in "%s%s%s".', TMP, Configure::read('RefMan.template.assignments.path'), sprintf(Configure::read('RefMan.template.assignments.file_all'), $season['Season']['title_season'])), __('Gesamteinsatzplan'));

					$tmpValue = empty($messagedata) ? false : $messagedata['ToolsEditor']['assignments_person'];
					echo $this->RefereeForm->getInputField(null, 'checkbox', 'assignments_person',
																								 __('Einsatzplan'), $tmpValue, __('Generierte Dateien in "%s%s%s".', TMP, Configure::read('RefMan.template.assignments.path'), sprintf(Configure::read('RefMan.template.assignments.file_person'), $season['Season']['title_season'], __('Name'), __('Vorname'), 0)), __('Einsatzplan für einzelne_n Schiedsrichter_in'));
				?>
			</fieldset>


			<fieldset>
				<legend><?php echo __('Einstellungen'); ?></legend>
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

					$tmpValue = empty($messagedata) ? null : $messagedata['ToolsEditor']['skiptill'];
					echo $this->RefereeForm->getInputField(null, 'text', 'skiptill',
																								 __('Überspringen bis'), $tmpValue, __('Alle Nachrichten werden erst ab diesem Namen erzeugt, den Namen nicht mit eingeschlossen.'), __('Name'),
																								 false);

					$tmpValue = empty($messagedata) ? 's' : $messagedata['ToolsEditor']['mailkind'];
					echo $this->RefereeForm->getInputField(null, 'radio', 'mailkind',
																								 __('Mailversand'), $tmpValue, null, null,
																								 true, false, 0,
																								 array('s' => __('Einzelne E-Mail pro Schiri'),
																											 'b' => __('Schiris als BCC')));

					$tmpValue = empty($messagedata) ? false : $messagedata['ToolsEditor']['test_only'];
					echo $this->RefereeForm->getInputField(null, 'checkbox', 'test_only',
																								 __('Sendemodus'), $tmpValue, __('Die Nachricht wird erzeugt und verarbeitet aber es wird keine E-Mail gesendet und kein PDF erzeugt.'), 'nur Test');
				?>
			</fieldset>

			<div class="form-group">
				<div class="col-sm-12">
					<?php echo $this->Form->button(__('Nachricht versenden'), array('type' => 'submit', 'class' => 'btn btn-sm btn-default')); ?>
				</div>
			</div>

		<?php echo $this->Form->end(); ?>
	</div>
</div>


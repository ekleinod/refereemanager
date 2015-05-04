<?php echo $this->element('filter');	?>

<?php echo $this->Form->create('ToolsEditor'); ?>
	<?php echo $this->Form->hidden('Filter.season', array('value' => $season['Season']['id'])); ?>

	<fieldset>
		<legend><?php echo __('Liste der E-Mail-Adressen'); ?></legend>
		<p>Bitte die Liste einfach herauskopieren.</p>
		<ol>
			<?php
				$arrMails = array();
				$email = '';
				foreach ($referees as $referee) {
					$email = $this->People->getPrimaryContact($referee, 'Email');
					if ($email != null) {
						$arrMails[] = $this->RefereeFormat->formatContacts(array($email), 'text', 'Email', 'text');
					}
				}

				echo $this->RefereeForm->getInputField(null, 'textarea', 'mailinglist', __('Liste (eine Zeile)'), $this->RefereeFormat->formatMultiline($arrMails, $separator), false, __('Liste'));
				echo $this->RefereeForm->getInputField(null, 'textarea', 'mailinglist', __('Liste (mehrere Zeilen)'), $this->RefereeFormat->formatMultiline($arrMails, $separator . "\n"), false, __('Liste'));

				echo $this->RefereeForm->getInputField(null, 'text', 'separator', __('Trennzeichen'), $separator, false, __(','));
			?>
		</ol>
	</fieldset>

	<fieldset>
		<?php echo $this->Form->button(__('Erzeugen'), array('type' => 'submit')); ?>
		<?php echo $this->Form->button(__('Zurücksetzen'), array('type' => 'reset')); ?>
	</fieldset>

<?php echo $this->Form->end(); ?>

<?php debug($referees); ?>


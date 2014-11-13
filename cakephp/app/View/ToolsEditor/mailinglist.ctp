<?php echo $this->element('filter');	?>

<?php echo $this->Form->create('ToolsEditor'); ?>
	<?php echo $this->Form->hidden('Filter.season', array('value' => $season['id'])); ?>

	<fieldset>
		<legend><?php echo __('Liste der E-Mail-Adressen'); ?></legend>
		<p>Bitte die Liste einfach herauskopieren.</p>
		<ol>
			<?php
				$sMailinglist = "";
				$bMore = false;
				foreach ($referees as $referee) {
					$email = Person::getPrimaryContact($referee, 'Email');
					if ($email != null) {

						if ($bMore) {
							$sMailinglist .= $separator;
						}
						$sMailinglist .= $this->RefereeFormat->formatContacts(array($email), 'text', 'Email', 'text');

						$bMore = true;
					}
				}
				echo $this->RefereeForm->getInputField(null, 'textarea', 'mailinglist', __('Liste (eine Zeile)'), $sMailinglist, false, __('Liste'));

				$sMailinglist = "";
				$bMore = false;
				foreach ($referees as $referee) {
					$email = Person::getPrimaryContact($referee, 'Email');
					if ($email != null) {

						if ($bMore) {
							$sMailinglist .= $separator . "\n";
						}
						$sMailinglist .= $this->RefereeFormat->formatContacts(array($email), 'text', 'Email', 'text');

						$bMore = true;
					}
				}
				echo $this->RefereeForm->getInputField(null, 'textarea', 'mailinglist', __('Liste (mehrere Zeilen)'), $sMailinglist, false, __('Liste'));

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


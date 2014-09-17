<!-- view filters -->
<div class="filter">
	<p>Saison: <?php echo $season['title_season']; ?></p>
</div>

<?php echo $this->Form->create('ToolsEditor'); ?>

	<fieldset>
		<legend><?php echo __('Liste der E-Mail-Adressen'); ?></legend>
		<p>Bitte die Liste einfach herauskopieren.</p>
		<ol>
			<?php
				$sMailinglist = "";
				$bMore = false;
				foreach ($referees as $referee) {
					if ($bMore) {
						$sMailinglist .= $separator;
					}
					$sMailinglist .= $referee['Person']['name'];
					$bMore = true;
				}
				echo $this->RefereeForm->getInputField(null, 'textarea', 'mailinglist', __('Liste (eine Zeile)'), $sMailinglist, false, __('Liste'));

				$sMailinglist = "";
				$bMore = false;
				foreach ($referees as $referee) {
					if ($bMore) {
						$sMailinglist .= $separator . "\n";
					}
					$sMailinglist .= $referee['Person']['name'];
					$bMore = true;
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


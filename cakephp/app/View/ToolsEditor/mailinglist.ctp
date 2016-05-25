<?php echo $this->element('filter');	?>

<div class="row">
	<div class="col-sm-12">
		<?php echo $this->Form->create('ToolsEditor', array('class' => 'form-horizontal')); ?>
		<?php echo $this->Form->hidden('Filter.season', array('value' => $season['Season']['id'])); ?>

			<fieldset>
				<legend><?php echo __('Liste der E-Mail-Adressen'); ?></legend>
				<p>
					<span class="help-block"><?php echo __('Bitte die Liste einfach herauskopieren.'); ?></span>
				</p>

				<?php
					echo $this->RefereeForm->getInputField(null, 'textarea', 'mailinglist.oneline',
																								 __('Liste (eine Zeile)'), $this->RefereeFormat->formatMultiline($emails, $separator), null, null,
																								 false);
					echo $this->RefereeForm->getInputField(null, 'textarea', 'mailinglist.multiline',
																								 __('Liste (mehrere Zeilen)'), $this->RefereeFormat->formatMultiline($emails, "\n"), null, null,
																								 false);

					echo $this->RefereeForm->getInputField(null, 'text', 'separator',
																								 __('Trennzeichen'), $separator, false, null, null,
																								 false);
				?>
			</fieldset>

			<div class="form-group">
				<div class="col-sm-12">
					<?php echo $this->Form->button(__('Listen neu erzeugen'), array('type' => 'submit', 'class' => 'btn btn-sm btn-default')); ?>
				</div>
			</div>

		<?php echo $this->Form->end(); ?>
	</div>
</div>

<?php
	//debug($emails);
?>


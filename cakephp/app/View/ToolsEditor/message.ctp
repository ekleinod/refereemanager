<?php echo $this->Form->create('ToolsEditor'); ?>

	<fieldset>
		<legend><?php echo __('Einstellungen'); ?></legend>
		<ol>
			<?php
				echo $this->RefereeForm->getInputField(null, 'select', 'season.id', __('Saison'), null, true, null, 0, true, $seasonarray);
				echo $this->RefereeForm->getInputField(null, 'select', 'season.id', __('Empfänger'), null, true, null, 0, true, $seasonarray);
				echo $this->RefereeForm->getInputField(null, 'select', 'season.id', __('Art'), null, true, null, 0, true, $seasonarray);
			?>
		</ol>
	</fieldset>

	<fieldset>
		<legend><?php echo __('Nachricht (mit Platzhaltern)'); ?></legend>
		<ol>
			<?php
				echo $this->RefereeForm->getInputField(null, 'textarea', 'message', __('Text'), null, true, __('Nachricht'));
			?>
		</ol>
	</fieldset>

	<fieldset>
		<?php echo $this->Form->button(__('Erzeugen'), array('type' => 'submit')); ?>
		<?php echo $this->Form->button(__('Zurücksetzen'), array('type' => 'reset')); ?>
	</fieldset>

<?php echo $this->Form->end(); ?>


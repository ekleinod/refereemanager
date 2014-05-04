<?php echo $this->Form->create('ToolsEditor'); ?>

	<fieldset>
		<legend><?php echo __('Einstellungen'); ?></legend>
		<ol>
			<?php
				echo $this->RefereeForm->getInputField(null, 'select', 'season', __('Saison'), null, true, null, 0, true, $seasonarray);
				echo $this->RefereeForm->getInputField(null, 'select', 'recipient', __('Empfänger'), null, true, null, 0, true,
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
				echo $this->RefereeForm->getInputField(null, 'textarea', 'message', __('Text'), null, true, __('Nachricht'));
				echo $this->RefereeForm->getInputField(null, 'text', 'opening', __('Anrede (E-Mail)'), null, false, __('Hallo Ihr,'));
				echo $this->RefereeForm->getInputField(null, 'text', 'closing', __('Schluss (E-Mail)'), null, false, __('Gruß, xyz.'));
				echo $this->RefereeForm->getInputField(null, 'textarea', 'header', __('Metainfo (Brief)'), null, false, __('Meta'));
			?>
		</ol>
	</fieldset>

	<fieldset>
		<?php echo $this->Form->button(__('Erzeugen'), array('type' => 'submit')); ?>
		<?php echo $this->Form->button(__('Zurücksetzen'), array('type' => 'reset')); ?>
	</fieldset>

<?php echo $this->Form->end(); ?>

<?php if ($result) { ?>

	<h3><?php echo __('Ergebnisse') ?></h3>

	<?php pr($data) ?>

<?php } ?>


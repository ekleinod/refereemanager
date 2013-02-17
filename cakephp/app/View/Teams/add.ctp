<?php echo $this->Form->create('Team'); ?>

<?php echo $this->element('Teams/form'); ?>

<fieldset>
	<?php echo $this->Form->button(__('Speichern'), array('type' => 'submit')); ?>
	<?php echo $this->Form->button(__('Inhalte zurücksetzen'), array('type' => 'reset')); ?>
</fieldset>

<?php echo $this->Form->end(); ?>

<!-- ?php pr($team); ? -->


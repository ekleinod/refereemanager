<?php echo $this->Form->create('Team'); ?>

<fieldset>
	<legend><?php echo __('Neues Team hinzufügen'); ?></legend>

	<?php echo $this->element('Teams/form'); ?>
</fieldset>

<?php echo $this->Form->button('Inhalte zurücksetzen', array('type' => 'reset')); ?>
<?php echo $this->Form->end(__('Speichern')); ?>

<!-- ?php pr($team); ? -->


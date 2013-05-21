<div class="users form">
<?php echo $this->Form->create('User'); ?>
	<fieldset>
		<legend><?php echo __('Nutzer anlegen'); ?></legend>
		<?php
			echo $this->Form->input('username', array('label' => __('Nutzername')));
			echo $this->Form->input('password', array('label' => __('Passwort')));
			echo $this->Form->input('user_role_id', array('label' => __('Rolle'), 'selected' => '3'));
			echo $this->Form->input('person_id', array('label' => __('Person'), 'empty' => __('keine Person')));
		?>
	</fieldset>
	<?php echo $this->Form->end(__('Anlegen')); ?>
</div>


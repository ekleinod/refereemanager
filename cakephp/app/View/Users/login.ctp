<div class="users form">

<?php echo $this->Session->flash('auth'); ?>

<?php echo $this->Form->create('User'); ?>
	<fieldset>
		<legend><?php echo __('Bitte geben Sie Nutzername und Passwort ein.'); ?></legend>
		<?php
			echo $this->Form->input('username', array('label' => __('Nutzername')));
			echo $this->Form->input('password', array('label' => __('Passwort')));
		?>
	</fieldset>
<?php echo $this->Form->end(__('Einloggen')); ?>

</div>


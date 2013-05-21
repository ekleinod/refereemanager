<div class="assignments form">
	<?php
		if ($isEditor) {
			echo $this->Form->create('Assignment'); ?>
			<fieldset>
				<legend><?php echo __('Schiedsrichtereinsatz hinzufügen'); ?></legend>

				<?php echo $this->element('Assignments/form'); ?>
			</fieldset>

			<fieldset>
				<?php echo $this->Form->button(__('Speichern'), array('type' => 'submit')); ?>
				<?php echo $this->Form->button(__('Inhalte zurücksetzen'), array('type' => 'reset')); ?>
			</fieldset>

			<?php echo $this->Form->end(); ?>

		<!-- ?php pr($assignment); ? -->

	<?php } else { ?>
		<h2><?php  echo __('Schiedsrichtereinsatz hinzufügen'); ?></h2>
		<p><?php echo __("Sie besitzen nicht genügend Rechte, um einen Schiedsrichtereinsatz hinzuzufügen."); ?></p>
	<?php }?>
</div>


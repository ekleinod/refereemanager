<div class="assignments form">
	<?php
		if ($isEditor) {
			echo $this->Form->create('Assignment'); ?>
			<fieldset>
				<legend><?php echo __('Schiedsrichtereinsatz editieren'); ?></legend>

				<?php echo $this->element('assignments_form_edit'); ?>
			</fieldset>

			<?php echo $this->Form->button('Inhalte zurücksetzen', array('type' => 'reset')); ?>
			<?php echo $this->Form->end(__('Speichern')); ?>

		<!-- ?php pr($assignment); ? -->

	<?php } else { ?>
		<h2><?php  echo __('Schiedsrichtereinsatz editieren'); ?></h2>
		<p><?php echo __("Sie besitzen nicht genügend Rechte, um den Schiedsrichtereinsatz zu editieren."); ?></p>
	<?php }?>
</div>


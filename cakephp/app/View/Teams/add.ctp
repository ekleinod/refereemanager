<div class="teams form">
	<?php
		if ($isEditor) {
			echo $this->Form->create('Team'); ?>
			<fieldset>
				<legend><?php echo __('Neues Team hinzufügen'); ?></legend>

				<?php echo $this->element('Teams/form'); ?>
			</fieldset>

			<?php echo $this->Form->button('Inhalte zurücksetzen', array('type' => 'reset')); ?>
			<?php echo $this->Form->end(__('Speichern')); ?>

		<!-- ?php pr($team); ? -->

	<?php } else { ?>
		<h2><?php  echo __('Team hinzufügen'); ?></h2>
		<p class="message"><?php echo __("Sie besitzen nicht genügend Rechte, um ein Team hinzuzufügen."); ?></p>
	<?php }?>
</div>


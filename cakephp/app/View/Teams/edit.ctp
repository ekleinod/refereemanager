<div class="teams form">
	<?php
		if ($isEditor) {
			echo $this->Form->create('Team'); ?>
			<fieldset>
				<legend><?php echo __('Team editieren'); ?></legend>

				<?php echo $this->element('Teams/form'); ?>
			</fieldset>

			<?php echo $this->Form->button('Inhalte zurücksetzen', array('type' => 'reset')); ?>
			<?php echo $this->Form->end(__('Erzeugen')); ?>

		<?php pr($team); ?>

	<?php } else { ?>
		<h2><?php  echo __('Team editieren'); ?></h2>
		<p><?php echo __("Sie besitzen nicht genügend Rechte, um das Team zu editieren."); ?></p>
	<?php }?>
</div>


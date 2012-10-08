<div class="assignments form">
	<?php echo $this->Form->create('Assignment'); ?>
		<fieldset>
			<legend><?php echo __('Edit Referee Assignment'); ?></legend>
			<?php
				echo $this->Form->input('Assignment.id');
				echo $this->Form->input('Assignment.game_number');
				echo $this->Form->input('Assignment.datetime', array('label' => __('Date and Time')));
				echo $this->Form->input('Assignment.season_id');
				echo $this->Form->input('Assignment.league_id');
				echo $this->Form->input('Assignment.address_id');
			?>
		</fieldset>
	<?php echo $this->Form->button('Reset the Form', array('type' => 'reset')); ?>
	<?php echo $this->Form->end(__('Submit')); ?>

	<?php pr($assignment); ?>

</div>


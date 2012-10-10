<div class="assignments form">
	<?php echo $this->Form->create('Assignment'); ?>
		<fieldset>
			<legend><?php echo __('Edit Referee Assignment'); ?></legend>
			<?php
				echo $this->Form->input('Assignment.id');
				echo $this->Form->input('Assignment.season_id');

				$label = $this->Form->label('Assignment.game_number');
				$input = $this->Form->text('Assignment.game_number',
						array('type' => 'date',
									'placeholder' => __('number'), 'title' => __('number'),
									'min' => '1', 'pattern' => '[1-9][0-9]*',
									'required' => 'required')
				);
				echo $this->Html->tag('div',
						$label . $input,
						array('class' => 'input number required')
				);

				echo $this->Form->input('Assignment.datetime', array('label' => __('Date and Time'), 'type' => 'date'));
				echo $this->Form->input('Assignment.league_id');
				echo $this->Form->input('TeamAssignment.team_id', array('label' => __('Home Team')));
				echo $this->Form->input('TeamAssignment.team_id', array('label' => __('Off Team')));
				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('OSR')));
				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('SSR')));
				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('SR')));
				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('SR')));
				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('SR')));
				echo $this->Form->input('Assignment.address_id', array('label' => __('Venue')));
			?>
		</fieldset>
	<?php echo $this->Form->button('Reset the Form', array('type' => 'reset')); ?>
	<?php echo $this->Form->end(__('Submit')); ?>

</div>


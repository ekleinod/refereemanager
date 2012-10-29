<div class="assignments form">
	<?php echo $this->Form->create('Assignment'); ?>
		<fieldset>
			<legend><?php echo __('Edit Referee Assignment'); ?></legend>
			<?php
				echo $this->Form->input('Assignment.id');
				echo $this->Form->input('Assignment.season_id');

				// game number
					$label = $this->Form->label('Assignment.game_number');
					$input = $this->Form->text('Assignment.game_number',
							array('type' => 'number',
										'placeholder' => __('number'), 'title' => __('number'),
										'min' => '1', 'pattern' => '[1-9][0-9]*',
										'required' => 'required')
					);
					echo $this->Html->tag('div',
							$label . $input,
							array('class' => 'input number required')
					);

				// date
					$label = $this->Form->label('Assignment.datetime.date', __('Date'));
					$input = $this->Form->text('Assignment.datetime.date',
							array('type' => 'date',
										'placeholder' => __('tt.mm.jjjj'), 'title' => __('tt.mm.jjjj'),
										'pattern' => '[0-3][0-9]\.[0-1][0-9]\.2[0-9][0-9][0-9]',
										'required' => 'required')
					);
					echo $this->Html->tag('div',
							$label . $input,
							array('class' => 'input date required')
					);

				// time
					$label = $this->Form->label('Assignment.datetime.time', __('Time'));
					$input = $this->Form->text('Assignment.datetime.time',
							array('type' => 'time',
										'placeholder' => __('hh:mm'), 'title' => __('hh:mm'),
										'pattern' => '[0-2][0-9]:[0-5][0-9]',
										'required' => 'required')
					);
					echo $this->Html->tag('div',
							$label . $input,
							array('class' => 'input time required')
					);

				// league
				echo $this->Form->input('Assignment.league_id');

				// teams
				echo $this->Form->input('TeamAssignment.home.team_id', array('label' => __('Home Team'), 'selected' => $hometeamid));
				echo $this->Form->input('TeamAssignment.off.team_id', array('label' => __('Off Team'), 'selected' => $offteamid));


				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('OSR'), 'empty' => __('none')));
				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('SSR'), 'empty' => __('none')));
				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('SR'), 'empty' => __('none')));
				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('SR'), 'empty' => __('none')));
				echo $this->Form->input('RefereeAssignment.referee_id', array('label' => __('SR'), 'empty' => __('none')));
				echo $this->Form->input('Assignment.address_id', array('label' => __('Venue'), 'empty' => __('none')));
			?>
		</fieldset>

	<?php echo $this->Form->button('Reset the Form', array('type' => 'reset')); ?>
	<?php echo $this->Form->end(__('Submit')); ?>

	<?php pr($teams); ?>
	<?php pr($leagues); ?>
	<?php pr($assignment); ?>

</div>


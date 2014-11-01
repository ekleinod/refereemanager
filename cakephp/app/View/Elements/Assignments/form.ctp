<?php echo $this->Form->create('Assignment'); ?>

	<fieldset>
		<legend><?php echo __('Daten'); ?></legend>
		<ol>
			<?php

				// date
				$tmpValue = (array_key_exists('Assignment', $assignment)) ? $assignment['Assignment']['start'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Assignment.start.date', __('Datum'), $this->RefereeFormat->formatDate($tmpValue, 'date'), true, __('tt.mm.yyyy'), 10, true);
				echo $this->RefereeForm->getInputField($action, 'text', 'Assignment.start.time', __('Zeit'), $this->RefereeFormat->formatDate($tmpValue, 'time'), true, __('hh:mm'), 5);

				// game number
				$tmpValue = (array_key_exists('Assignment', $assignment)) ? $assignment['Assignment']['LeagueGame']['game_number'] : '';
				echo $this->RefereeForm->getInputField($action, 'text', 'Assignment.LeagueGame.game_number', __('Spiel-Nr.'), $tmpValue, true, '', 5);

				// season
				$tmpValue = (array_key_exists('Assignment', $assignment)) ? $assignment['Assignment']['LeagueGame']['season_id'] : $season['id'];
				echo $this->RefereeForm->getInputField($action, 'select', 'Assignment.LeagueGame.season_id', __('Saison'), $tmpValue, true, '', 0, false, $seasonarray);

				// league
				$tmpValue = (array_key_exists('Assignment', $assignment)) ? $assignment['Assignment']['LeagueGame']['league_id'] : '';
				echo $this->RefereeForm->getInputField($action, 'select', 'Assignment.LeagueGame.league_id', __('Liga'), $tmpValue, true, '', 0, false, $leaguearray);

			?>
		</ol>
	</fieldset>

	<?php if (($action === 'add') || ($action === 'edit')) { ?>
		<fieldset>
			<?php echo $this->Form->button(__('Speichern'), array('type' => 'submit')); ?>
			<?php echo $this->Form->button(__('Zurücksetzen'), array('type' => 'reset')); ?>
		</fieldset>
	<?php } ?>

<?php echo $this->Form->end(); ?>

<?php echo pr($assignment); ?>


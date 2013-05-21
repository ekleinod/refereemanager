<?php
	// id (hidden)
		echo $this->Form->input('Assignment.id');

	// season
		echo $this->Form->input('Assignment.season_id', array('label' => __('Saison')));

	// game number
		$label = $this->Form->label('Assignment.game_number', __('Spielnr.'));
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
		$label = $this->Form->label('Assignment.date.datetime', __('Datum'));
		$input = $this->Form->text('Assignment.date.datetime',
				array('type' => 'date',
							'placeholder' => __('tt.mm.jjjj'), 'title' => __('tt.mm.jjjj'),
							'pattern' => '[0-3][0-9]\.[0-1][0-9]\.2[0-9][0-9][0-9]',
							'required' => 'required',
							'value' => (($datetime == null) ? '' : $this->RefereeFormat->format($datetime, 'date')))
		);
		echo $this->Html->tag('div',
				$label . $input,
				array('class' => 'input date required')
		);

	// time
		$label = $this->Form->label('Assignment.time.datetime', __('Uhrzeit'));
		$input = $this->Form->text('Assignment.time.datetime',
				array('type' => 'time',
							'placeholder' => __('hh:mm'), 'title' => __('hh:mm'),
							'pattern' => '[0-2][0-9]:[0-5][0-9]',
							'required' => 'required',
							'value' => (($datetime == null) ? '' : $this->RefereeFormat->format($datetime, 'time')))
		);
		echo $this->Html->tag('div',
				$label . $input,
				array('class' => 'input time required')
		);

	// league
		echo $this->Form->input('Assignment.league_id', array('label' => __('Liga')));

	// teams
		echo $this->Form->input('TeamAssignment.home.team_id',
						array('label' => __('Heimteam'), 'selected' => $hometeamid, 'div' => 'input select required'));
		echo $this->Form->input('TeamAssignment.off.team_id',
						array('label' => __('Auswärtsteam'), 'selected' => $offteamid, 'div' => 'input select required'));

	// referees
		foreach ($refereeroles as $refereerole) {
			$maxcount = ($refereerole['code'] == 'SR') ? 3 : 1;
			for ($i = 1; $i <= $maxcount; $i++) {
				$refereeid = '';
				if (array_key_exists($refereerole['id'], $selectedreferees) &&
						array_key_exists($i - 1, $selectedreferees[$refereerole['id']])) {
					$refereeid = $selectedreferees[$refereerole['id']][$i - 1];
				}
				echo $this->Form->input('RefereeAssignment.' . $refereerole['code'] . $i . '.referee_id',
								array('label' => __($refereerole['title']),
											'empty' => __('none'),
											'selected' => $refereeid));
			}
		}

	// venue
		echo $this->Form->input('Assignment.address_id', array('label' => __('Spielort'), 'empty' => __('none')));
?>


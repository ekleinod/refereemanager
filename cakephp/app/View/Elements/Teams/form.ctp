<?php

	// id (hidden)
		echo $this->Form->input('Team.id');

	// team number
		$label = $this->Form->label('Team.number', __('Team-Nummer'));
		$input = $this->Form->text('Team.number',
				array('type' => 'number',
							'placeholder' => __('Nummer'), 'title' => __('Team-Nummer'),
							'min' => '1', 'pattern' => '[1-9][0-9]*',
							'required' => 'required')
		);
		echo $this->Html->tag('div',
				$label . $input,
				array('class' => 'input number required')
		);

	// name
		$label = $this->Form->label('Team.name', __('Name (wenn nicht angegeben, wird der Clubname verwendet)'));
		$input = $this->Form->text('Team.name',
				array('type' => 'text',
							'placeholder' => __('Name'), 'title' => __('Team-Name'))
		);
		echo $this->Html->tag('div',
				$label . $input,
				array('class' => 'input text')
		);

	// description
		$label = $this->Form->label('Team.description', __('Beschreibung (textarea!)'));
		$input = $this->Form->text('Team.description',
				array('type' => 'textarea',
							'placeholder' => __('Beschreibung'), 'title' => __('Beschreibung'))
		);
		echo $this->Html->tag('div',
				$label . $input,
				array('class' => 'input textarea')
		);

	// club
		echo $this->Form->input('Team.club_id', array('label' => __('Club')));

	// venue
		echo $this->Form->input('Team.address_id', array('label' => __('Spielort (wenn nicht angegeben, wird die Clubadresse verwendet)'), 'empty' => __('none')));

	// season
		echo $this->Form->input('Team.season_id', array('label' => __('Saison')));

	// league
		echo $this->Form->input('Team.league_id', array('label' => __('Liga')));

	// venue
		echo $this->Form->input('Team.person_id', array('label' => __('Ansprechperson (wenn nicht angegeben, wird die des Clubs verwendet)'), 'empty' => __('none')));

?>


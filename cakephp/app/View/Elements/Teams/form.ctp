<?php
	// id (hidden)
		echo $this->Form->input('Team.id');
?>

<fieldset>
	<legend><?php echo __('Grundlegendes'); ?></legend>

	<ol>
<?php
		// club
			$label = $this->Form->label('Team.club_id', __('Club'), 'required');
			$input = $this->Form->select('Team.club_id',
					$clubs,
					array('autofocus' => 'autofocus',
								'required' => 'required')
			);
			echo $this->Html->tag('li',
					$label . $input,
					array('class' => 'input select required')
			);

		// team number
			$label = $this->Form->label('Team.number', __('Team-Nummer'));
			$input = $this->Form->text('Team.number',
					array('type' => 'number',
								'placeholder' => __('Nummer'), 'title' => __('Team-Nummer'),
								'min' => '1', 'pattern' => '[1-9][0-9]*',
								'required' => 'required')
			);
			echo $this->Html->tag('li',
					$label . $input,
					array('class' => 'input number required')
			);

		// name
			$label = $this->Form->label('Team.name', __('Name'));
			$input = $this->Form->text('Team.name',
					array('type' => 'text',
								'placeholder' => __('Name'), 'title' => __('Team-Name'))
			);
			$remark = $this->Html->tag('span',
					_('Wenn nicht angegeben, wird der des Clubs verwendet.'),
					array('class' => 'remark')
			);
			echo $this->Html->tag('li',
					$label . $input . $remark,
					array('class' => 'input text')
			);

		// description
			$label = $this->Form->label('Team.description', __('Beschreibung'));
			$input = $this->Form->textarea('Team.description',
					array('type' => 'textarea',
								'placeholder' => __('Beschreibung'), 'title' => __('Beschreibung'))
			);
			echo $this->Html->tag('li',
					$label . $input,
					array('class' => 'input textarea')
			);

		// venue
			$label = $this->Form->label('Team.address_id', __('Spielort'));
			$input = $this->Form->select('Team.address_id',
					$addresses
			);
			$remark = $this->Html->tag('span',
					_('Wenn nicht angegeben, wird der des Clubs verwendet.'),
					array('class' => 'remark')
			);
			echo $this->Html->tag('li',
					$label . $input . $remark,
					array('class' => 'input select')
			);

?>

	</ol>
</fieldset>

<fieldset>
	<legend><?php echo __('Saisondaten'); ?></legend>

	<ol>
<?php

		// season
			$label = $this->Form->label('Team.season_id', __('Saison'));
			$input = $this->Form->select('Team.season_id',
					$seasons,
					array('required' => 'required')
			);
			echo $this->Html->tag('li',
					$label . $input,
					array('class' => 'input select required')
			);

		// league
			$label = $this->Form->label('Team.league_id', __('Liga'));
			$input = $this->Form->select('Team.league_id',
					$leagues,
					array('required' => 'required')
			);
			echo $this->Html->tag('li',
					$label . $input,
					array('class' => 'input select required')
			);

		// person
			$label = $this->Form->label('Team.person_id', __('Ansprechperson'));
			$input = $this->Form->select('Team.person_id',
					$people
			);
			$remark = $this->Html->tag('span',
					_('Wenn nicht angegeben, wird die des Clubs verwendet.'),
					array('class' => 'remark')
			);
			echo $this->Html->tag('li',
					$label . $input . $remark,
					array('class' => 'input select')
			);

?>
	</ol>
</fieldset>



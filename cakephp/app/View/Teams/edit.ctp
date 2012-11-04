<div class="teams form">
<?php echo $this->Form->create('Team'); ?>
	<fieldset>
		<legend><?php echo __('Edit Team'); ?></legend>
	<?php
		echo $this->Form->input('id');
		echo $this->Form->input('number');
		echo $this->Form->input('name');
		echo $this->Form->input('description');
		echo $this->Form->input('club_id');
		echo $this->Form->input('address_id');
		echo $this->Form->input('league_id');
	?>
	</fieldset>
<?php echo $this->Form->end(__('Submit')); ?>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>

		<li><?php echo $this->Form->postLink(__('Delete'), array('action' => 'delete', $this->Form->value('Team.id')), null, __('Are you sure you want to delete # %s?', $this->Form->value('Team.id'))); ?></li>
		<li><?php echo $this->Html->link(__('List Teams'), array('action' => 'index')); ?></li>
		<li><?php echo $this->Html->link(__('List Addresses'), array('controller' => 'addresses', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Address'), array('controller' => 'addresses', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Clubs'), array('controller' => 'clubs', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Club'), array('controller' => 'clubs', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Leagues'), array('controller' => 'leagues', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New League'), array('controller' => 'leagues', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Team Assignments'), array('controller' => 'team_assignments', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Team Assignment'), array('controller' => 'team_assignments', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Team Spokespeople'), array('controller' => 'team_spokespeople', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Team Spokesperson'), array('controller' => 'team_spokespeople', 'action' => 'add')); ?> </li>
	</ul>
</div>

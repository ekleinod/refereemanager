<div class="referees form">
<?php echo $this->Form->create('Referee'); ?>
	<fieldset>
		<legend><?php echo __('Edit Referee'); ?></legend>
	<?php
		echo $this->Form->input('id');
		echo $this->Form->input('license');
		echo $this->Form->input('last_referee_training');
		echo $this->Form->input('assignment_quantity_id');
		echo $this->Form->input('status_id');
		echo $this->Form->input('club_id');
		echo $this->Form->input('person_id');
		echo $this->Form->input('referee_kind_id');
	?>
	</fieldset>
<?php echo $this->Form->end(__('Submit')); ?>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>

		<li><?php echo $this->Form->postLink(__('Delete'), array('action' => 'delete', $this->Form->value('Referee.id')), null, __('Are you sure you want to delete # %s?', $this->Form->value('Referee.id'))); ?></li>
		<li><?php echo $this->Html->link(__('List Referees'), array('action' => 'index')); ?></li>
		<li><?php echo $this->Html->link(__('List Clubs'), array('controller' => 'clubs', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Club'), array('controller' => 'clubs', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List People'), array('controller' => 'people', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Person'), array('controller' => 'people', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Statuses'), array('controller' => 'statuses', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Status'), array('controller' => 'statuses', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Assignment Quantities'), array('controller' => 'assignment_quantities', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Assignment Quantity'), array('controller' => 'assignment_quantities', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Referee Kinds'), array('controller' => 'referee_kinds', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Referee Kind'), array('controller' => 'referee_kinds', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Referee Assignments'), array('controller' => 'referee_assignments', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Referee Assignment'), array('controller' => 'referee_assignments', 'action' => 'add')); ?> </li>
	</ul>
</div>

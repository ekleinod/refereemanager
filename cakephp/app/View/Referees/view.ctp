<div class="referees view">
<h2><?php  echo __('Referee'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($referee['Referee']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('License'); ?></dt>
		<dd>
			<?php echo h($referee['Referee']['license']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Last Referee Training'); ?></dt>
		<dd>
			<?php echo h($referee['Referee']['last_referee_training']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Assignment Quantity'); ?></dt>
		<dd>
			<?php echo $this->Html->link($referee['AssignmentQuantity']['title'], array('controller' => 'assignment_quantities', 'action' => 'view', $referee['AssignmentQuantity']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Status'); ?></dt>
		<dd>
			<?php echo $this->Html->link($referee['Status']['title'], array('controller' => 'statuses', 'action' => 'view', $referee['Status']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Club'); ?></dt>
		<dd>
			<?php echo $this->Html->link($referee['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $referee['Club']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Person'); ?></dt>
		<dd>
			<?php echo $this->Html->link($referee['Person']['title_person'], array('controller' => 'people', 'action' => 'view', $referee['Person']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Referee Kind'); ?></dt>
		<dd>
			<?php echo $this->Html->link($referee['RefereeKind']['title'], array('controller' => 'referee_kinds', 'action' => 'view', $referee['RefereeKind']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Created'); ?></dt>
		<dd>
			<?php echo h($referee['Referee']['created']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Modified'); ?></dt>
		<dd>
			<?php echo h($referee['Referee']['modified']); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Edit Referee'), array('action' => 'edit', $referee['Referee']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Delete Referee'), array('action' => 'delete', $referee['Referee']['id']), null, __('Are you sure you want to delete # %s?', $referee['Referee']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('List Referees'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Referee'), array('action' => 'add')); ?> </li>
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
<div class="related">
	<h3><?php echo __('Related Referee Assignments'); ?></h3>
	<?php if (!empty($referee['RefereeAssignment'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Assignment Id'); ?></th>
		<th><?php echo __('Referee Assignment Role Id'); ?></th>
		<th><?php echo __('Referee Id'); ?></th>
		<th><?php echo __('Created'); ?></th>
		<th><?php echo __('Modified'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($referee['RefereeAssignment'] as $refereeAssignment): ?>
		<tr>
			<td><?php echo $refereeAssignment['id']; ?></td>
			<td><?php echo $refereeAssignment['assignment_id']; ?></td>
			<td><?php echo $refereeAssignment['referee_assignment_role_id']; ?></td>
			<td><?php echo $refereeAssignment['referee_id']; ?></td>
			<td><?php echo $refereeAssignment['created']; ?></td>
			<td><?php echo $refereeAssignment['modified']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'referee_assignments', 'action' => 'view', $refereeAssignment['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'referee_assignments', 'action' => 'edit', $refereeAssignment['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'referee_assignments', 'action' => 'delete', $refereeAssignment['id']), null, __('Are you sure you want to delete # %s?', $refereeAssignment['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Referee Assignment'), array('controller' => 'referee_assignments', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>

<div class="teams view">
<h2><?php  echo __('Team'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($team['Team']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Number'); ?></dt>
		<dd>
			<?php echo h($team['Team']['number']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Name'); ?></dt>
		<dd>
			<?php echo h($team['Team']['name']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Description'); ?></dt>
		<dd>
			<?php echo h($team['Team']['description']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Club'); ?></dt>
		<dd>
			<?php echo $this->Html->link($team['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $team['Club']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Address'); ?></dt>
		<dd>
			<?php echo $this->Html->link($team['Address']['title_address'], array('controller' => 'addresses', 'action' => 'view', $team['Address']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('League'); ?></dt>
		<dd>
			<?php echo $this->Html->link($team['League']['title'], array('controller' => 'leagues', 'action' => 'view', $team['League']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Created'); ?></dt>
		<dd>
			<?php echo h($team['Team']['created']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Modified'); ?></dt>
		<dd>
			<?php echo h($team['Team']['modified']); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Edit Team'), array('action' => 'edit', $team['Team']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Delete Team'), array('action' => 'delete', $team['Team']['id']), null, __('Are you sure you want to delete # %s?', $team['Team']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('List Teams'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Team'), array('action' => 'add')); ?> </li>
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
<div class="related">
	<h3><?php echo __('Related Team Assignments'); ?></h3>
	<?php if (!empty($team['TeamAssignment'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Home'); ?></th>
		<th><?php echo __('Assignment Id'); ?></th>
		<th><?php echo __('Team Id'); ?></th>
		<th><?php echo __('Created'); ?></th>
		<th><?php echo __('Modified'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($team['TeamAssignment'] as $teamAssignment): ?>
		<tr>
			<td><?php echo $teamAssignment['id']; ?></td>
			<td><?php echo $teamAssignment['home']; ?></td>
			<td><?php echo $teamAssignment['assignment_id']; ?></td>
			<td><?php echo $teamAssignment['team_id']; ?></td>
			<td><?php echo $teamAssignment['created']; ?></td>
			<td><?php echo $teamAssignment['modified']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'team_assignments', 'action' => 'view', $teamAssignment['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'team_assignments', 'action' => 'edit', $teamAssignment['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'team_assignments', 'action' => 'delete', $teamAssignment['id']), null, __('Are you sure you want to delete # %s?', $teamAssignment['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Team Assignment'), array('controller' => 'team_assignments', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>
<div class="related">
	<h3><?php echo __('Related Team Spokespeople'); ?></h3>
	<?php if (!empty($team['TeamSpokesperson'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Team Id'); ?></th>
		<th><?php echo __('Person Id'); ?></th>
		<th><?php echo __('Season Id'); ?></th>
		<th><?php echo __('Created'); ?></th>
		<th><?php echo __('Modified'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($team['TeamSpokesperson'] as $teamSpokesperson): ?>
		<tr>
			<td><?php echo $teamSpokesperson['id']; ?></td>
			<td><?php echo $teamSpokesperson['team_id']; ?></td>
			<td><?php echo $teamSpokesperson['person_id']; ?></td>
			<td><?php echo $teamSpokesperson['season_id']; ?></td>
			<td><?php echo $teamSpokesperson['created']; ?></td>
			<td><?php echo $teamSpokesperson['modified']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'team_spokespeople', 'action' => 'view', $teamSpokesperson['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'team_spokespeople', 'action' => 'edit', $teamSpokesperson['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'team_spokespeople', 'action' => 'delete', $teamSpokesperson['id']), null, __('Are you sure you want to delete # %s?', $teamSpokesperson['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Team Spokesperson'), array('controller' => 'team_spokespeople', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>

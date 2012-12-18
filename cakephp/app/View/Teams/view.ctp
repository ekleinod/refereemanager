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
		<li><?php echo $this->Html->link(__('List Assignments'), array('controller' => 'assignments', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Assignment'), array('controller' => 'assignments', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Seasons'), array('controller' => 'seasons', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Season'), array('controller' => 'seasons', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Leagues'), array('controller' => 'leagues', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New League'), array('controller' => 'leagues', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List People'), array('controller' => 'people', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Person'), array('controller' => 'people', 'action' => 'add')); ?> </li>
	</ul>
</div>
<div class="related">
	<h3><?php echo __('Related Assignments'); ?></h3>
	<?php if (!empty($team['Assignment'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Game Number'); ?></th>
		<th><?php echo __('Datetime'); ?></th>
		<th><?php echo __('Season Id'); ?></th>
		<th><?php echo __('League Id'); ?></th>
		<th><?php echo __('Address Id'); ?></th>
		<th><?php echo __('Created'); ?></th>
		<th><?php echo __('Modified'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($team['Assignment'] as $assignment): ?>
		<tr>
			<td><?php echo $assignment['id']; ?></td>
			<td><?php echo $assignment['game_number']; ?></td>
			<td><?php echo $assignment['datetime']; ?></td>
			<td><?php echo $assignment['season_id']; ?></td>
			<td><?php echo $assignment['league_id']; ?></td>
			<td><?php echo $assignment['address_id']; ?></td>
			<td><?php echo $assignment['created']; ?></td>
			<td><?php echo $assignment['modified']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'assignments', 'action' => 'view', $assignment['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'assignments', 'action' => 'edit', $assignment['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'assignments', 'action' => 'delete', $assignment['id']), null, __('Are you sure you want to delete # %s?', $assignment['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Assignment'), array('controller' => 'assignments', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>
<div class="related">
	<h3><?php echo __('Related Seasons'); ?></h3>
	<?php if (!empty($team['Season'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Year Start'); ?></th>
		<th><?php echo __('Description'); ?></th>
		<th><?php echo __('Created'); ?></th>
		<th><?php echo __('Modified'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($team['Season'] as $season): ?>
		<tr>
			<td><?php echo $season['id']; ?></td>
			<td><?php echo $season['year_start']; ?></td>
			<td><?php echo $season['description']; ?></td>
			<td><?php echo $season['created']; ?></td>
			<td><?php echo $season['modified']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'seasons', 'action' => 'view', $season['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'seasons', 'action' => 'edit', $season['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'seasons', 'action' => 'delete', $season['id']), null, __('Are you sure you want to delete # %s?', $season['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Season'), array('controller' => 'seasons', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>
<div class="related">
	<h3><?php echo __('Related Leagues'); ?></h3>
	<?php if (!empty($team['League'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Title'); ?></th>
		<th><?php echo __('Description'); ?></th>
		<th><?php echo __('Code'); ?></th>
		<th><?php echo __('Women'); ?></th>
		<th><?php echo __('Umpire Report Link'); ?></th>
		<th><?php echo __('Created'); ?></th>
		<th><?php echo __('Modified'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($team['League'] as $league): ?>
		<tr>
			<td><?php echo $league['id']; ?></td>
			<td><?php echo $league['title']; ?></td>
			<td><?php echo $league['description']; ?></td>
			<td><?php echo $league['code']; ?></td>
			<td><?php echo $league['women']; ?></td>
			<td><?php echo $league['umpire_report_link']; ?></td>
			<td><?php echo $league['created']; ?></td>
			<td><?php echo $league['modified']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'leagues', 'action' => 'view', $league['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'leagues', 'action' => 'edit', $league['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'leagues', 'action' => 'delete', $league['id']), null, __('Are you sure you want to delete # %s?', $league['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New League'), array('controller' => 'leagues', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>
<div class="related">
	<h3><?php echo __('Related People'); ?></h3>
	<?php if (!empty($team['Person'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Name'); ?></th>
		<th><?php echo __('First Name'); ?></th>
		<th><?php echo __('Birthday'); ?></th>
		<th><?php echo __('Address Id'); ?></th>
		<th><?php echo __('Created'); ?></th>
		<th><?php echo __('Modified'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($team['Person'] as $person): ?>
		<tr>
			<td><?php echo $person['id']; ?></td>
			<td><?php echo $person['name']; ?></td>
			<td><?php echo $person['first_name']; ?></td>
			<td><?php echo $person['birthday']; ?></td>
			<td><?php echo $person['address_id']; ?></td>
			<td><?php echo $person['created']; ?></td>
			<td><?php echo $person['modified']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'people', 'action' => 'view', $person['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'people', 'action' => 'edit', $person['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'people', 'action' => 'delete', $person['id']), null, __('Are you sure you want to delete # %s?', $person['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Person'), array('controller' => 'people', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>

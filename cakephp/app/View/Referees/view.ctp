<div class="referees view">
<h2><?php  echo __('Referee'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($referee['Referee']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Person'); ?></dt>
		<dd>
			<?php echo $this->Html->link($referee['Person']['name'], array('controller' => 'people', 'action' => 'view', $referee['Person']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Status Type'); ?></dt>
		<dd>
			<?php echo $this->Html->link($referee['StatusType']['title'], array('controller' => 'status_types', 'action' => 'view', $referee['StatusType']['id'])); ?>
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
		<li><?php echo $this->Html->link(__('List People'), array('controller' => 'people', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Person'), array('controller' => 'people', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Status Types'), array('controller' => 'status_types', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Status Type'), array('controller' => 'status_types', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Referee Assignments'), array('controller' => 'referee_assignments', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Referee Assignment'), array('controller' => 'referee_assignments', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Referee Relations'), array('controller' => 'referee_relations', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Referee Relation'), array('controller' => 'referee_relations', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Training Levels'), array('controller' => 'training_levels', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Training Level'), array('controller' => 'training_levels', 'action' => 'add')); ?> </li>
	</ul>
</div>
<div class="related">
	<h3><?php echo __('Related Referee Assignments'); ?></h3>
	<?php if (!empty($referee['RefereeAssignment'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Assignment Id'); ?></th>
		<th><?php echo __('Referee Assignment Type Id'); ?></th>
		<th><?php echo __('Referee Id'); ?></th>
		<th><?php echo __('Assignment Status Type Id'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($referee['RefereeAssignment'] as $refereeAssignment): ?>
		<tr>
			<td><?php echo $refereeAssignment['id']; ?></td>
			<td><?php echo $refereeAssignment['assignment_id']; ?></td>
			<td><?php echo $refereeAssignment['referee_assignment_type_id']; ?></td>
			<td><?php echo $refereeAssignment['referee_id']; ?></td>
			<td><?php echo $refereeAssignment['assignment_status_type_id']; ?></td>
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
<div class="related">
	<h3><?php echo __('Related Referee Relations'); ?></h3>
	<?php if (!empty($referee['RefereeRelation'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Referee Id'); ?></th>
		<th><?php echo __('Referee Relation Type Id'); ?></th>
		<th><?php echo __('Club Id'); ?></th>
		<th><?php echo __('League Id'); ?></th>
		<th><?php echo __('Sex Type Id'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($referee['RefereeRelation'] as $refereeRelation): ?>
		<tr>
			<td><?php echo $refereeRelation['id']; ?></td>
			<td><?php echo $refereeRelation['referee_id']; ?></td>
			<td><?php echo $refereeRelation['referee_relation_type_id']; ?></td>
			<td><?php echo $refereeRelation['club_id']; ?></td>
			<td><?php echo $refereeRelation['league_id']; ?></td>
			<td><?php echo $refereeRelation['sex_type_id']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'referee_relations', 'action' => 'view', $refereeRelation['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'referee_relations', 'action' => 'edit', $refereeRelation['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'referee_relations', 'action' => 'delete', $refereeRelation['id']), null, __('Are you sure you want to delete # %s?', $refereeRelation['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Referee Relation'), array('controller' => 'referee_relations', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>
<div class="related">
	<h3><?php echo __('Related Training Levels'); ?></h3>
	<?php if (!empty($referee['TrainingLevel'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Referee Id'); ?></th>
		<th><?php echo __('Training Level Type Id'); ?></th>
		<th><?php echo __('Since'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($referee['TrainingLevel'] as $trainingLevel): ?>
		<tr>
			<td><?php echo $trainingLevel['id']; ?></td>
			<td><?php echo $trainingLevel['referee_id']; ?></td>
			<td><?php echo $trainingLevel['training_level_type_id']; ?></td>
			<td><?php echo $trainingLevel['since']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'training_levels', 'action' => 'view', $trainingLevel['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'training_levels', 'action' => 'edit', $trainingLevel['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'training_levels', 'action' => 'delete', $trainingLevel['id']), null, __('Are you sure you want to delete # %s?', $trainingLevel['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Training Level'), array('controller' => 'training_levels', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>

<div class="users view">
<h2><?php  echo __('User'); ?></h2>
	<dl>
		<dt><?php echo __('Id'); ?></dt>
		<dd>
			<?php echo h($user['User']['id']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Username'); ?></dt>
		<dd>
			<?php echo h($user['User']['username']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Password'); ?></dt>
		<dd>
			<?php echo h($user['User']['password']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('User Role'); ?></dt>
		<dd>
			<?php echo $this->Html->link($user['UserRole']['title'], array('controller' => 'user_roles', 'action' => 'view', $user['UserRole']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Person'); ?></dt>
		<dd>
			<?php echo $this->Html->link($user['Person']['title_person'], array('controller' => 'people', 'action' => 'view', $user['Person']['id'])); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Created'); ?></dt>
		<dd>
			<?php echo h($user['User']['created']); ?>
			&nbsp;
		</dd>
		<dt><?php echo __('Modified'); ?></dt>
		<dd>
			<?php echo h($user['User']['modified']); ?>
			&nbsp;
		</dd>
	</dl>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('Edit User'), array('action' => 'edit', $user['User']['id'])); ?> </li>
		<li><?php echo $this->Form->postLink(__('Delete User'), array('action' => 'delete', $user['User']['id']), null, __('Are you sure you want to delete # %s?', $user['User']['id'])); ?> </li>
		<li><?php echo $this->Html->link(__('List Users'), array('action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New User'), array('action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List User Roles'), array('controller' => 'user_roles', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New User Role'), array('controller' => 'user_roles', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List People'), array('controller' => 'people', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Person'), array('controller' => 'people', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Activity Logs'), array('controller' => 'activity_logs', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Activity Log'), array('controller' => 'activity_logs', 'action' => 'add')); ?> </li>
	</ul>
</div>
<div class="related">
	<h3><?php echo __('Related Activity Logs'); ?></h3>
	<?php if (!empty($user['ActivityLog'])): ?>
	<table cellpadding = "0" cellspacing = "0">
	<tr>
		<th><?php echo __('Id'); ?></th>
		<th><?php echo __('Table Name'); ?></th>
		<th><?php echo __('Row Id'); ?></th>
		<th><?php echo __('Column Name'); ?></th>
		<th><?php echo __('Old Value'); ?></th>
		<th><?php echo __('New Value'); ?></th>
		<th><?php echo __('User Id'); ?></th>
		<th><?php echo __('Description'); ?></th>
		<th><?php echo __('Created'); ?></th>
		<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
		$i = 0;
		foreach ($user['ActivityLog'] as $activityLog): ?>
		<tr>
			<td><?php echo $activityLog['id']; ?></td>
			<td><?php echo $activityLog['table_name']; ?></td>
			<td><?php echo $activityLog['row_id']; ?></td>
			<td><?php echo $activityLog['column_name']; ?></td>
			<td><?php echo $activityLog['old_value']; ?></td>
			<td><?php echo $activityLog['new_value']; ?></td>
			<td><?php echo $activityLog['user_id']; ?></td>
			<td><?php echo $activityLog['description']; ?></td>
			<td><?php echo $activityLog['created']; ?></td>
			<td class="actions">
				<?php echo $this->Html->link(__('View'), array('controller' => 'activity_logs', 'action' => 'view', $activityLog['id'])); ?>
				<?php echo $this->Html->link(__('Edit'), array('controller' => 'activity_logs', 'action' => 'edit', $activityLog['id'])); ?>
				<?php echo $this->Form->postLink(__('Delete'), array('controller' => 'activity_logs', 'action' => 'delete', $activityLog['id']), null, __('Are you sure you want to delete # %s?', $activityLog['id'])); ?>
			</td>
		</tr>
	<?php endforeach; ?>
	</table>
<?php endif; ?>

	<div class="actions">
		<ul>
			<li><?php echo $this->Html->link(__('New Activity Log'), array('controller' => 'activity_logs', 'action' => 'add')); ?> </li>
		</ul>
	</div>
</div>

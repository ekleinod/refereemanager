<div class="users view">
	<h2><?php echo sprintf('%s "%s"', __('Nutzer'), h($user['User']['username'])); ?></h2>

	<dl>
		<dt><?php echo __('Nutzername'); ?></dt>
		<dd><?php echo h($user['User']['username']); ?></dd>
		<dt><?php echo __('Rolle'); ?></dt>
		<dd>
			<?php echo $this->Html->link($user['UserRole']['title'], array('controller' => 'user_roles', 'action' => 'view', $user['UserRole']['id'])); ?>
		</dd>
		<dt><?php echo __('Person'); ?></dt>
		<dd>
			<?php echo $this->Html->link($user['Person']['title_person'], array('controller' => 'people', 'action' => 'view', $user['Person']['id'])); ?>
		</dd>
	</dl>
	<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $user['User']['id'])); ?>
</div>
<div class="related">
	<h3><?php echo __('Aktivitäten'); ?></h3>
	<?php if (empty($user['ActivityLog'])) { ?>
		<p>Keine Aktivitäten dieses Nutzers aufgezeichnet.</p>
	<?php } else { ?>
		<table>
			<thead>
				<tr>
					<th><?php echo __('Tabelle'); ?></th>
					<th><?php echo __('Zeile'); ?></th>
					<th><?php echo __('Spalte'); ?></th>
					<th><?php echo __('Alter Wert'); ?></th>
					<th><?php echo __('Neuer Wert'); ?></th>
					<th><?php echo __('Beschreibung'); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php
					foreach ($user['ActivityLog'] as $activityLog): ?>
						<tr>
							<td><?php echo $activityLog['table_name']; ?></td>
							<td><?php echo $activityLog['row_id']; ?></td>
							<td><?php echo $activityLog['column_name']; ?></td>
							<td><?php echo $activityLog['old_value']; ?></td>
							<td><?php echo $activityLog['new_value']; ?></td>
							<td><?php echo $activityLog['description']; ?></td>
						</tr>
				<?php endforeach; ?>
			</tbody>
		</table>
	<?php } ?>
</div>


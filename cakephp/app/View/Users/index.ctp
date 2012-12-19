<div class="users index">
	<h2><?php echo __('Nutzer'); ?></h2>
	<?php
		if (count($users) < 1) {
	?>
		<p><?php echo __('Es gibt keine Nutzer.'); ?></p>
	<?php
		} else {
	?>
		<table>
			<thead>
				<tr>
					<th><?php echo __('Nutzername'); ?></th>
					<th><?php echo __('Rolle'); ?></th>
					<th><?php echo __('Person'); ?></th>
					<th><?php echo __('Aktionen'); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php
					foreach ($users as $user):
				?>
					<tr>
						<td><?php echo $this->Html->link(h($user['User']['username']), array('action' => 'view', $user['User']['id'])); ?></td>
						<td>
							<?php echo $this->Html->link($user['UserRole']['title'], array('controller' => 'user_roles', 'action' => 'view', $user['UserRole']['id'])); ?>
						</td>
						<td>
							<?php echo $this->Html->link($user['Person']['title_person'], array('controller' => 'people', 'action' => 'view', $user['Person']['id'])); ?>
						</td>
						<td class="actions">
							<?php echo $this->Html->link(__('Ansehen'), array('action' => 'view', $user['User']['id'])); ?>
							<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $user['User']['id'])); ?>
							<?php echo $this->Form->postLink(__('Löschen'), array('action' => 'delete', $user['User']['id']), null, __('Are you sure you want to delete # %s?', $user['User']['id'])); ?>
						</td>
					</tr>
				<?php endforeach; ?>
			</tbody>
		</table>
	<?php
		}
	?>

	<!--?php pr($users); ?-->

</div>


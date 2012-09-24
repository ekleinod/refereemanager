<div class="assignments index">
	<h2><?php echo __('Referee Assignments'); ?></h2>
	<p>Season: <?php echo $season; ?><!--?php echo $this->Html->link($assignment['Season']['year_start'], array('controller' => 'seasons', 'action' => 'view', $assignment['Season']['id'])); ?--></p>
	<table>
		<thead>
			<tr>
				<th><?php echo __('Date'); ?></th>
				<th><?php echo __('Time'); ?></th>
				<th><?php echo __('Game-No.'); ?></th>
				<th><?php echo __('Ligue'); ?></th>
				<th><?php echo __('Home Team'); ?></th>
				<th><?php echo __('Off Team'); ?></th>
				<th><?php echo __('OSR'); ?></th>
				<th><?php echo __('Stellv. OSR'); ?></th>
				<th><?php echo __('SR'); ?></th>
				<th><?php echo __('Last Change'); ?></th>
				<th><?php echo __('Aktionen'); ?></th>
			</tr>
		</thead>
		<tbody>
			<?php
			pr($assignments);
			foreach ($assignments as $assignment): ?>
				<tr>
					<td><?php echo h($this->Time->format('D d.m.Y', $assignment['Assignment']['datetime'])); ?></td>
					<td><?php echo h($this->Time->format('H:i', $assignment['Assignment']['datetime'])); ?></td>
					<td><?php echo h($assignment['Assignment']['game_number']); ?></td>
					<td><?php echo h($assignment['League']['title']); ?></td>
					<td><!--?php echo h($assignment['Team']['number']); ?--></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><?php echo h($this->Time->format('d.m.Y, H:i', $assignment['Assignment']['modified'])); ?></td>
					<td class="actions">
						<?php echo $this->Html->link(__('View'), array('action' => 'view', $assignment['Assignment']['id'])); ?>
						<?php echo $this->Html->link(__('iCal File'), array('action' => 'ics', $assignment['Assignment']['id'])); ?>
					</td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>

</div>


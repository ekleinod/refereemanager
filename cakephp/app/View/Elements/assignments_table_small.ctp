<table>
	<thead>
		<tr>
			<th><?php echo __('Spielnr.'); ?></th>
			<th><?php echo __('Datum'); ?></th>
			<th><?php echo __('Zeit'); ?></th>
		</tr>
	</thead>
	<tbody>
		<?php foreach ($assignments as $assignment) { ?>
			<tr>
				<td><?php echo $this->Html->link($assignment['game_number'], array('controller' => 'assignments', 'action' => 'view', $assignment['id'])); ?></td>
				<td><?php echo $this->Html->link($this->RefereeFormat->format($assignment['datetime'], 'longdate'), array('controller' => 'dates', 'action' => 'view', $this->RefereeFormat->format($assignment['datetime'], 'datereverse'))); ?></td>
				<td><?php echo h($this->RefereeFormat->format($assignment['datetime'], 'time')); ?></td>
			</tr>
		<?php } ?>
	</tbody>
</table>


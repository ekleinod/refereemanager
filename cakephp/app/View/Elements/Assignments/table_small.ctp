<table>
	<thead>
		<tr>
			<th><?php echo __('Saison'); ?></th>
			<th><?php echo __('Spielnr.'); ?></th>
			<th><?php echo __('Datum'); ?></th>
			<th><?php echo __('Zeit'); ?></th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th><?php echo __('Saison'); ?></th>
			<th><?php echo __('Spielnr.'); ?></th>
			<th><?php echo __('Datum'); ?></th>
			<th><?php echo __('Zeit'); ?></th>
		</tr>
	</tfoot>
	<tbody>
		<?php foreach ($assignments as $assignment) { ?>
			<tr>
				<td data-title="<?php echo __('Saison'); ?>"><?php echo $this->Html->link($assignment['season_id'], array('controller' => 'seasons', 'action' => 'view', $assignment['season_id'])); ?></td>
				<td data-title="<?php echo __('Spielnr.'); ?>"><?php echo $this->Html->link($assignment['game_number'], array('controller' => 'assignments', 'action' => 'view', $assignment['id'])); ?></td>
				<td data-title="<?php echo __('Datum'); ?>"><?php echo $this->Html->link($this->RefereeFormat->format($assignment['datetime'], 'longdate'), array('controller' => 'dates', 'action' => 'view', $this->RefereeFormat->format($assignment['datetime'], 'datereverse'))); ?></td>
				<td data-title="<?php echo __('Zeit'); ?>"><?php echo h($this->RefereeFormat->format($assignment['datetime'], 'time')); ?></td>
			</tr>
		<?php } ?>
	</tbody>
</table>


<table>
	<thead>
		<tr>
			<th><?php echo __('Datum'); ?></th>
			<th><?php echo __('Änderung'); ?></th>
			<th><?php echo __('Werte'); ?></th>
		</tr>
	</thead>
	<tbody>
		<?php foreach ($changes as $change) { ?>
			<?php foreach ($change as $single_change) { ?>
				<tr>
					<td><?php echo h($this->RefereeFormat->format($single_change['datetime'], 'date')); ?></td>
					<td><?php echo h($single_change['description']); ?></td>
					<td><?php
						if ($single_change['old_value'] != '') {
							echo h($this->RefereeFormat->format($single_change['old_value'], $single_change['type']));
							echo ' &rarr; ';
						}
						echo h($this->RefereeFormat->format($single_change['new_value'], $single_change['type']));
					?></td>
				</tr>
			<?php } ?>
		<?php } ?>
	</tbody>
</table>


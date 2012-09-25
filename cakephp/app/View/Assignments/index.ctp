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
			foreach ($assignments as $assignment): ?>
				<tr>
					<td><?php echo h($this->Time->format('D d.m.Y', $assignment['Assignment']['datetime'])); ?></td>
					<td><?php echo h($this->Time->format('H:i', $assignment['Assignment']['datetime'])); ?></td>
					<td><?php echo h($assignment['Assignment']['game_number']); ?></td>
					<td><?php echo h($assignment['League']['title']); ?></td>
					<td><?php echo h((array_key_exists('HomeTeam', $assignment)) ? $assignment['HomeTeam']['title_team'] : '??'); ?></td>
					<td><?php echo h((array_key_exists('RoadTeam', $assignment)) ? $assignment['RoadTeam']['title_team'] : '??'); ?></td>
					<td><?php echo h((array_key_exists('Umpire', $assignment)) ? $assignment['Umpire']['person_id'] : ''); ?></td>
					<td><?php echo h((array_key_exists('Standby', $assignment)) ? $assignment['Standby']['person_id'] : ''); ?></td>
					<td><?php
						if (array_key_exists('SimpleReferee', $assignment)) {
							$moreReferees = false;
							foreach ($assignment['SimpleReferee'] as $referee):
								echo h($referee['person_id']);
								if ($moreReferees) {
									echo h('<br />');
								}
								$moreReferees = true;
							endforeach;
						}
					?></td>
					<td><?php echo h($this->Time->format('d.m.Y, H:i', $assignment['Assignment']['modified'])); ?></td>
					<td class="actions">
						<?php echo $this->Html->link(__('View'), array('action' => 'view', $assignment['Assignment']['id'])); ?>
						<!--?php echo $this->Html->link(__('iCal File'), array('action' => 'ics', $assignment['Assignment']['id'])); ?-->
					</td>
				</tr>
			<?php endforeach; ?>
		</tbody>
	</table>

	<?php pr($assignments); ?>

</div>


<div class="assignments index">
	<h2><?php echo __('Referee Assignments'); ?></h2>
	<p>Season: <?php echo $season; ?><!--?php echo $this->Html->link($assignment['Season']['year_start'], array('controller' => 'seasons', 'action' => 'view', $assignment['Season']['id'])); ?--></p>
	<?php
		if (count($assignments) < 1) {
	?>
		<p><?php echo __('There are no assignments for this season.'); ?></p>
	<?php
		} else {
	?>
		<table>
			<thead>
				<tr>
					<th><?php echo __('Date'); ?></th>
					<th><?php echo __('Time'); ?></th>
					<th><?php echo __('Game-No.'); ?></th>
					<th><?php echo __('Ligue'); ?></th>
					<th><?php echo __('Home Team'); ?></th>
					<th><?php echo __('Off Team'); ?></th>
					<?php foreach ($refereeroles as $refereerole): ?>
						<th><?php echo '<span title="' . __($refereerole['title']) . '">' . __($refereerole['code']) . '</span>'; ?></th>
					<?php endforeach; ?>
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
						<?php foreach ($refereeroles as $refereerole): ?>
							<td><?php
								if (array_key_exists($refereerole['code'], $assignment)) {
									$moreReferees = false;
									foreach ($assignment[$refereerole['code']] as $referee):
										echo h($referee['Person']['Person']['title_person']);
										if ($moreReferees) {
											echo h('<br />');
										}
										$moreReferees = true;
									endforeach;
								}
							?></td>
						<?php endforeach; ?>
						<td><?php echo h($this->Time->format('d.m.Y, H:i', $assignment['Assignment']['modified'])); ?></td>
						<td class="actions">
							<?php echo $this->Html->link(__('View'), array('action' => 'view', $assignment['Assignment']['id'])); ?>
							<!--?php echo $this->Html->link(__('iCal File'), array('action' => 'ics', $assignment['Assignment']['id'])); ?-->
						</td>
					</tr>
				<?php endforeach; ?>
			</tbody>
		</table>
	<?php
		}
	?>

	<?php pr($assignments); ?>

</div>


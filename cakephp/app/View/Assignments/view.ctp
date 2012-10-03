<div class="assignments view">
	<h2><?php  echo __('Referee Assignment'); ?></h2>
	<dl>
		<dt><?php echo __('Season'); ?></dt>
			<dd><?php echo $this->Html->link($assignment['Season']['title_season'], array('controller' => 'seasons', 'action' => 'view', $assignment['Season']['id'])); ?></dd>
		<dt><?php echo __('Date'); ?></dt>
			<dd><?php echo h($this->Time->format('D d.m.Y', $assignment['Assignment']['datetime'])); ?></dd>
		<dt><?php echo __('Time'); ?></dt>
			<dd><?php echo h($this->Time->format('H:i', $assignment['Assignment']['datetime'])); ?></dd>
		<dt><?php echo __('Game-No.'); ?></dt>
			<dd><?php echo h($assignment['Assignment']['game_number']); ?></dd>
		<dt><?php echo __('Ligue'); ?></dt>
			<dd><?php echo h($assignment['League']['title']); ?></dd>
		<dt><?php echo __('Home Team'); ?></dt>
			<dd><?php echo h((array_key_exists('HomeTeam', $assignment)) ? $assignment['HomeTeam']['title_team'] : '??'); ?></dd>
		<dt><?php echo __('Off Team'); ?></dt>
			<dd><?php echo h((array_key_exists('RoadTeam', $assignment)) ? $assignment['RoadTeam']['title_team'] : '??'); ?></dd>
		<?php foreach ($refereeroles as $refereerole): ?>
			<dt><?php echo __($refereerole['title']) . ' (' . __($refereerole['code']) . ')'; ?></dt>
				<?php
					if (array_key_exists($refereerole['code'], $assignment)) {
						foreach ($assignment[$refereerole['code']] as $referee):
							echo '<dd>' . h($referee['Person']['Person']['title_person']) . '</dd>';
						endforeach;
					} else {
						echo '<dd>&nbsp;</dd>';
					}
				?>
		<?php endforeach; ?>
		<dt><?php echo __('Last Change'); ?></dt>
			<dd><?php echo h($this->Time->format('d.m.Y, H:i', $assignment['Assignment']['modified'])); ?></dd>
	</dl>
	<!--?php pr($assignment); ?-->

	<h3><?php  echo __('Changes of this Referee Assignment'); ?></h3>
	<?php if (count($changes) < 1) { ?>
		<p><?php  echo __('This referee assignment was not changed so far.'); ?></p>
	<?php } else { ?>
		<p><?php  echo __('The following changes were made to this referee assignment:'); ?></p>
		<table>
			<thead>
				<tr>
					<th><?php echo __('Date'); ?></th>
					<th><?php echo __('Change'); ?></th>
					<th><?php echo __('Values'); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php foreach ($changes as $change): ?>
					<?php foreach ($change as $single_change): ?>
						<tr>
							<td><?php echo h($this->Time->format('d.m.Y', $single_change['datetime'])); ?></td>
							<td><?php echo h($single_change['description']); ?></td>
							<td><?php
								if ($single_change['old_value'] != '') {
									switch ($single_change['type']) {
										case 'datetime':
											echo h($this->Time->format('d.m.Y, H:i', $single_change['old_value']));
											break;
										default:
											echo h($single_change['old_value']);
											break;
									}
									echo h(' -> ');
								}
								switch ($single_change['type']) {
									case 'datetime':
										echo h($this->Time->format('d.m.Y, H:i', $single_change['new_value']));
										break;
									default:
										echo h($single_change['new_value']);
										break;
								}
							?></td>
						</tr>
					<?php endforeach; ?>
				<?php endforeach; ?>
			</tbody>
		</table>
	<?php } ?>

	<?php pr($changes); ?>

</div>

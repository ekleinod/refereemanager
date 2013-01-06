<div class="assignments index">
	<h2><?php echo __('Schiedsrichtereinsätze'); ?></h2>

	<?php if ($isEditor) { ?>
		<p class="actions"><?php echo $this->Html->link(__('Neuen Schiedsrichtereinsatz hinzufügen'), array('action' => 'add')); ?></p>
	<?php } ?>

	<p>Saison: <?php echo $season . "/" . ($season + 1); ?></p>

	<?php
		if (empty($assignments)) {
	?>
		<p><?php echo __('Es gibt keine Schiedsrichtereinsätze für diese Saison.'); ?></p>
	<?php
		} else {
	?>
		<table>
			<thead>
				<tr>
					<th><?php echo __('Datum'); ?></th>
					<th><?php echo __('Zeit'); ?></th>
					<th><?php echo __('Spiel-Nr.'); ?></th>
					<th><?php echo __('Liga'); ?></th>
					<th><?php echo __('Heimteam'); ?></th>
					<th><?php echo __('Auswärtsteam'); ?></th>
					<?php foreach ($refereeroles as $refereerole): ?>
						<th><?php echo '<span title="' . __($refereerole['title']) . '">' . __($refereerole['code']) . '</span>'; ?></th>
					<?php endforeach; ?>
					<th><?php echo __('Aktionen'); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php
				foreach ($assignments as $assignment): ?>
					<tr>
						<td><?php echo $this->Html->link($this->RefereeFormat->format($assignment['Assignment']['datetime'], 'longdate'), array('controller' => 'dates', 'action' => 'view', $this->RefereeFormat->format($assignment['Assignment']['datetime'], 'datereverse'))); ?></td>
						<td><?php echo h($this->RefereeFormat->format($assignment['Assignment']['datetime'], 'time')); ?></td>
						<td><?php echo $this->Html->link($assignment['Assignment']['game_number'], array('controller' => 'assignments', 'action' => 'view', $assignment['Assignment']['id'])); ?></td>
						<td><?php echo $this->Html->link($assignment['League']['title'], array('controller' => 'leagues', 'action' => 'view', $assignment['League']['id'])); ?></td>
						<td><?php echo (array_key_exists('HomeTeam', $assignment)) ? $this->Html->link($assignment['HomeTeam']['Team']['title_team'], array('controller' => 'teams', 'action' => 'view', $assignment['HomeTeam']['Team']['id'])) : h('??'); ?></td>
						<td><?php echo (array_key_exists('RoadTeam', $assignment)) ? $this->Html->link($assignment['RoadTeam']['Team']['title_team'], array('controller' => 'teams', 'action' => 'view', $assignment['RoadTeam']['Team']['id'])) : h('??'); ?></td>
						<?php foreach ($refereeroles as $refereerole): ?>
							<td><?php
								if (array_key_exists($refereerole['code'], $assignment)) {
									$moreReferees = false;
									foreach ($assignment[$refereerole['code']] as $referee):
										if ($moreReferees) {
											echo '<br />';
										}
										$moreReferees = true;
										echo $this->Html->link($referee['Person']['Person']['title_person'], array('controller' => 'people', 'action' => 'view', $referee['Person']['Person']['id']));
									endforeach;
								}
							?></td>
						<?php endforeach; ?>
						<td class="actions">
							<?php echo $this->Html->link(__('Ansehen'), array('action' => 'view', $assignment['Assignment']['id'])); ?>
							<!--?php echo $this->Html->link(__('iCal File'), array('action' => 'ics', $assignment['Assignment']['id'])); ?-->
						</td>
					</tr>
				<?php endforeach; ?>
			</tbody>
		</table>
	<?php
		}
	?>

	<!--?php pr($assignments); ?-->

</div>


<div class="assignments view">
	<h2><?php  echo __('Schiedsrichtereinsatz'); ?></h2>
	<dl>
		<dt><?php echo __('Saison'); ?></dt>
			<dd><?php echo $this->Html->link($assignment['Season']['title_season'], array('controller' => 'seasons', 'action' => 'view', $assignment['Season']['id'])); ?></dd>
		<dt><?php echo __('Datum'); ?></dt>
			<dd><?php echo $this->Html->link($this->RefereeFormat->format($assignment['Assignment']['datetime'], 'longdate'), array('controller' => 'dates', 'action' => 'view', $this->RefereeFormat->format($assignment['Assignment']['datetime'], 'datereverse'))); ?></dd>
		<dt><?php echo __('Zeit'); ?></dt>
			<dd><?php echo h($this->RefereeFormat->format($assignment['Assignment']['datetime'], 'time')); ?></dd>
		<dt><?php echo __('Spiel-Nr.'); ?></dt>
			<dd><?php echo h($assignment['Assignment']['game_number']); ?></dd>
		<dt><?php echo __('Liga'); ?></dt>
			<dd><?php echo $this->Html->link($assignment['League']['title'], array('controller' => 'leagues', 'action' => 'view', $assignment['League']['id'])); ?></dd>
		<dt><?php echo __('Heimteam'); ?></dt>
			<dd><?php echo (array_key_exists('HomeTeam', $assignment)) ? $this->Html->link($assignment['HomeTeam']['Team']['title_team'], array('controller' => 'teams', 'action' => 'view', $assignment['HomeTeam']['Team']['id'])) : h('??'); ?></dd>
		<dt><?php echo __('Auswärtsteam'); ?></dt>
			<dd><?php echo (array_key_exists('RoadTeam', $assignment)) ? $this->Html->link($assignment['RoadTeam']['Team']['title_team'], array('controller' => 'teams', 'action' => 'view', $assignment['RoadTeam']['Team']['id'])) : h('??'); ?></dd>
		<?php foreach ($refereeroles as $refereerole): ?>
			<dt><?php echo '<span title="' . __($refereerole['title']) . '">' . __($refereerole['code']) . '</span>'; ?></dt>
				<dd><?php
					if (array_key_exists($refereerole['code'], $assignment)) {
						$moreReferees = false;
						foreach ($assignment[$refereerole['code']] as $referee):
							if ($moreReferees) {
								echo '<br />';
							}
							$moreReferees = true;
							echo $this->Html->link($referee['Person']['Person']['title_person'], array('controller' => 'people', 'action' => 'view', $referee['Person']['Person']['id']));
						endforeach;
					} else {
						echo '&nbsp;';
					}
				?></dd>
		<?php endforeach; ?>
		<dt><?php echo __('Spielort'); ?></dt>
			<dd><?php echo is_null($venue) ? __('Unknown') : $this->Html->link($venue['title_address'], array('controller' => 'addresses', 'action' => 'view', $venue['id'])); ?></dd>
	</dl>

	<h3><?php  echo __('Aktionen für diesen Schiedsrichtereinsatz'); ?></h3>
	<p class="actions"><?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $assignment['Assignment']['id'])); ?></p>

	<h3><?php  echo __('Änderungen dieses Schiedsrichtereinsatzes'); ?></h3>
	<?php if (count($changes) < 1) { ?>
		<p><?php  echo __('Dieser Schiedsrichtereinsatz wurde bisher nicht geändert.'); ?></p>
	<?php } else { ?>
		<p><?php  echo __('Die folgenden Änderungen am Schiedsrichtereinsatz wurden durchgeführt:'); ?></p>
		<table>
			<thead>
				<tr>
					<th><?php echo __('Datum'); ?></th>
					<th><?php echo __('Änderung'); ?></th>
					<th><?php echo __('Werte'); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php foreach ($changes as $change): ?>
					<?php foreach ($change as $single_change): ?>
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
					<?php endforeach; ?>
				<?php endforeach; ?>
			</tbody>
		</table>
	<?php } ?>

	<?php pr($changes); ?>

</div>

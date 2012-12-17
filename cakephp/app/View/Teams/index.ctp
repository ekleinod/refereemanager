<div class="teams index">
	<h2><?php echo __('Teams'); ?></h2>

	<p class="actions"><?php echo $this->Html->link(__('Neues Team hinzufügen'), array('action' => 'add')); ?></p>

	<p>Saison: <?php echo $season . "/" . ($season + 1); ?></p>

	<?php
		if (count($teams) < 1) {
	?>
		<p><?php echo __('Es gibt keine Teams für diese Saison.'); ?></p>
	<?php
		} else {
	?>
		<table>
			<thead>
				<tr>
					<th><?php echo __('Name'); ?></th>
					<th><?php echo __('Beschreibung'); ?></th>
					<th><?php echo __('Club'); ?></th>
					<th><?php echo __('Liga'); ?></th>
					<th><?php echo __('Saison'); ?></th>
					<th><?php echo __('Ansprechpartner'); ?></th>
					<th><?php echo __('Spielort'); ?></th>
					<th><?php echo __('Aktionen'); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php
					foreach ($teams as $team):
				?>
					<tr>
						<td><?php echo $this->Html->link($team['Team']['name'], array('controller' => 'teams', 'action' => 'view', $team['Team']['id'])); ?></td>
						<td><?php echo h($team['Team']['description']); ?></td>
						<td><?php echo $this->Html->link($team['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $team['Club']['id'])); ?></td>

						<td></td>
						<td></td>
						<td></td>
						<td><?php echo $this->Html->link($team['Address']['title_address'], array('controller' => 'addresses', 'action' => 'view', $team['Address']['id'])); ?></td>
						<td class="actions">
							<?php echo $this->Html->link(__('Ansehen'), array('action' => 'view', $team['Team']['id'])); ?>
						</td>
					</tr>
				<?php endforeach; ?>
			</tbody>
		</table>
	<?php
		}
	?>

	<!--?php pr($teams); ?-->

</div>


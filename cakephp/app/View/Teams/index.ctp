<div class="teams index">
	<h2><?php echo __('Teams'); ?></h2>

	<?php if ($isEditor) { ?>
		<p class="actions"><?php echo $this->Html->link(__('Neues Team hinzufügen'), array('action' => 'add')); ?></p>
	<?php } ?>

	<p>Saison: <?php echo $season . "/" . ($season + 1); ?></p>

	<?php
		if (empty($teams)) {
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

						<td>
							<?php if (empty($team['League'])) { ?>
								&nbsp;
							<?php } else { ?>
								<?php echo $this->Html->link($team['League'][0]['title'], array('controller' => 'leagues', 'action' => 'view', $team['League'][0]['id'])); ?>
							<?php } ?>
						</td>

						<td>
							<?php if (empty($team['Season'])) { ?>
								&nbsp;
							<?php } else { ?>
								<?php echo $this->Html->link($team['Season'][0]['title_season'], array('controller' => 'seasons', 'action' => 'view', $team['Season'][0]['id'])); ?>
							<?php } ?>
						</td>

						<td>
							<?php if (empty($team['Person'])) { ?>
								&nbsp;
							<?php } else { ?>
								<?php echo $this->Html->link($team['Person'][0]['title_person'], array('controller' => 'people', 'action' => 'view', $team['Person'][0]['id'])); ?>
							<?php } ?>
						</td>

						<td><?php echo $this->Html->link($team['Address']['title_address'], array('controller' => 'addresses', 'action' => 'view', $team['Address']['id'])); ?></td>
						<td class="actions">
							<?php echo $this->Html->link(__('Ansehen'), array('action' => 'view', $team['Team']['id'])); ?>
							<?php if ($isEditor) { ?>
								<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $team['Team']['id'])); ?>
								<?php echo $this->Form->postLink(__('Löschen'), array('action' => 'delete', $team['Team']['id']), null, __('Wollen Sie Team "%s" wirklich löschen?', $team['Team']['name'])); ?>
							<?php } ?>
						</td>
					</tr>
				<?php endforeach; ?>
			</tbody>
		</table>
	<?php
		}
	?>

	<?php pr($teams); ?>

</div>


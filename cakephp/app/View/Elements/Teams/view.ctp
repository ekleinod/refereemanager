<table>
	<tbody>
		<tr>
			<th class="th-col"><?php echo __('Nummer'); ?></th>
			<td><?php echo h($team['Team']['number']); ?></td>
		</tr>
		<tr>
			<th class="th-col"><?php echo __('Name'); ?></th>
			<td><?php
				if (!empty($team['Team']['name'])) {
					echo h($team['Team']['name']);
				}
			?></td>
		</tr>
		<tr>
			<th class="th-col"><?php echo __('Beschreibung'); ?></th>
			<td><?php
				if (!empty($team['Team']['description'])) {
					echo h($team['Team']['description']);
				}
			?></td>
		</tr>
		<tr>
			<th class="th-col"><?php echo __('Club'); ?></th>
			<td><?php
				if (!empty($team['Club'])) {
					echo $this->Html->link($team['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $team['Club']['id']));
				}
			?></td>
		</tr>
		<tr>
			<th class="th-col"><?php echo __('Spielort'); ?></th>
			<td><?php
				if (!empty($team['Address'])) {
					echo $this->Html->link($team['Address']['title_address'], array('controller' => 'addresses', 'action' => 'view', $team['Address']['id']));
				}
			?></td>
		</tr>
	</tbody>
</table>

<dl>
	<dt><?php echo __('Liga'); ?></dt>
		<dd>
			<?php if (empty($team['League'])) { ?>
				&nbsp;
			<?php } else { ?>
				<?php echo $this->Html->link($team['League'][0]['title'], array('controller' => 'leagues', 'action' => 'view', $team['League'][0]['id'])); ?>
			<?php } ?>
		</dd>
	<dt><?php echo __('Saison'); ?></dt>
		<dd>
			<?php if (empty($team['Season'])) { ?>
				&nbsp;
			<?php } else { ?>
				<?php echo $this->Html->link($team['Season'][0]['title_season'], array('controller' => 'seasons', 'action' => 'view', $team['Season'][0]['id'])); ?>
			<?php } ?>
		</dd>
	<dt><?php echo __('Ansprechpartner'); ?></dt>
		<dd>
			<?php if (empty($team['Person'])) { ?>
				&nbsp;
			<?php } else { ?>
				<?php echo $this->Html->link($team['Person'][0]['title_person'], array('controller' => 'people', 'action' => 'view', $team['Person'][0]['id'])); ?>
			<?php } ?>
		</dd>
</dl>


<!-- header actions -->
<?php echo $this->element('actions_header', array('id' => $team['Team']['id']));	?>

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

<h3><?php echo __('Saisons des Teams'); ?></h3>
<p>todo</p>
<table>
	<thead>
		<tr>
			<th><?php echo __('Saison'); ?></th>
			<th><?php echo __('Liga'); ?></th>
			<th><?php echo __('Ansprechpartner'); ?></th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th><?php echo __('Saison'); ?></th>
			<th><?php echo __('Liga'); ?></th>
			<th><?php echo __('Ansprechpartner'); ?></th>
		</tr>
	</tfoot>
	<tbody>
		<tr>
			<td data-title="<?php echo __('Saison'); ?>"><?php
				if (!empty($team['Season'])) {
					echo $this->Html->link($team['Season'][0]['title_season'], array('controller' => 'seasons', 'action' => 'view', $team['Season'][0]['id']));
				}
			?></td>
			<td data-title="<?php echo __('Liga'); ?>"><?php
				if (!empty($team['League'])) {
					echo $this->Html->link($team['League'][0]['title'], array('controller' => 'leagues', 'action' => 'view', $team['League'][0]['id']));
				}
			?></td>
			<td data-title="<?php echo __('Ansprechpartner'); ?>"><?php
				if (!empty($team['Person'])) {
					echo $this->Html->link($team['Person'][0]['title_person'], array('controller' => 'people', 'action' => 'view', $team['Person'][0]['id']));
				}
			?></td>
		</tr>
	</tbody>
<table>

<h3><?php echo __('Spiele des Teams'); ?></h3>

<p>todo</p>
<?php if (empty($team['Assignment'])) { ?>
	<p><?php echo __('Dieses Team ist noch für keine Spiele eingetragen.'); ?></p>
<?php
	} else {
		echo $this->element('Assignments/table_small', array('assignments' => $team['Assignment']));
	}
?>

<?php echo $this->element('Changes/table_small', array('changes' => $changes));	?>

<!--?php pr($team); ?-->


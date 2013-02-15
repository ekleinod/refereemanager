<!-- header actions -->
<?php if ($isEditor) { ?>
	<div class="actions">
		<p><?php echo $this->Html->link(__('Neues Team hinzufügen'), array('action' => 'add'), array('class' => 'button-link access-editor')); ?></p>
	</div>
<?php } ?>

<!-- view filters -->
<div class="filter">
	<p>Saison: <?php echo $season . "/" . ($season + 1); ?></p>
</div>

<!-- view content -->
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
				<th><?php echo __('Ansprechpartner'); ?></th>
				<th><?php echo __('Spielort'); ?></th>
				<th><?php echo __('Aktionen'); ?></th>
			</tr>
		</thead>
		<tbody>
			<?php
				foreach ($teams as $team) {
			?>
					<tr>
						<td data-title="<?php echo __('Name'); ?>"><?php echo $this->Html->link($team['Team']['name'], array('controller' => 'teams', 'action' => 'view', $team['Team']['id'])); ?></td>

						<td data-title="<?php echo __('Beschreibung'); ?>"><?php
							if (!empty($team['Team']['description'])) {
								echo h($team['Team']['description']);
							}
						?></td>

						<td data-title="<?php echo __('Club'); ?>"><?php
							if (!empty($team['Club'])) {
								echo $this->Html->link($team['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $team['Club']['id']));
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

						<td data-title="<?php echo __('Spielort'); ?>"><?php
							if (!empty($team['Address'])) {
								echo $this->Html->link($team['Address']['title_address'], array('controller' => 'addresses', 'action' => 'view', $team['Address']['id']));
							}
						?></td>

						<td class="actions" data-title="<?php echo __('Aktionen'); ?>">
							<?php echo $this->element('actions_table', array('id' => $team['Team']['id']));	?>
						</td>
					</tr>
			<?php } ?>
		</tbody>
	</table>
<?php
	}
?>

<!--?php pr($teams); ?-->


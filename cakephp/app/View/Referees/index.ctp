<!-- header actions -->
<?php echo $this->element('actions_header');	?>

<!-- view filters -->
<div class="filter">
</div>

<!-- view content -->
<?php
	if (empty($referees)) {
?>
	<p><?php echo __('Es sind keine Schiedsrichter gespeichert.'); ?></p>
<?php
	} else {
?>
	<p>Legende:</p>
	<ul class="legend">
		<?php
			foreach ($statustypes as &$statustype) {
				$style = '';
				if ($statustype['style'] || $statustype['color']) {
					$style = ' style="';

					switch ($statustype['style']) {
						case 'italic':
						case 'oblique':
							$style .= sprintf('font-style: %s; ', $statustype['style']);
							break;
						case 'bold':
						case 'bolder':
						case 'lighter':
							$style .= sprintf('font-weight: %s; ', $statustype['style']);
							break;
					}

					if ($statustype['color']) {
						 $style .= sprintf('color: #%s; ', $statustype['color']);
					}

					$style .= '"';
				}
				$statustype['htmlstyle'] = $style;
		?>
			<li<?php echo $statustype['htmlstyle']; ?>><?php echo ($statustype['remark']) ? $statustype['remark'] : $statustype['title']; ?></li>
		<?php
			}
		?>
	</ul>
	<table>
		<thead>
			<tr>
				<?php if ($isReferee) { ?>
					<th><?php echo __('Bild'); ?></th>
				<?php } ?>
				<th><?php echo __('Vorname'); ?></th>
				<th><?php echo __('Name'); ?></th>
				<th><?php echo __('Club'); ?></th>
				<?php if ($isReferee) { ?>
					<th><?php echo __('E-Mail'); ?></th>
					<th><?php echo __('Telefon'); ?></th>
				<?php } ?>
				<?php if ($isEditor) { ?>
					<th><?php echo __('Adresse'); ?></th>
					<th><?php echo __('Geschlecht'); ?></th>
					<th><?php echo __('Geburtstag'); ?></th>
					<th><?php echo __('Ausbildung'); ?></th>
					<th><?php echo __('Letzte Fortbildung'); ?></th>
					<th><?php echo __('Status'); ?></th>
					<th><?php echo __('Anmerkung'); ?></th>
				<?php } ?>
				<th><?php echo __('Aktionen'); ?></th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<?php if ($isReferee) { ?>
					<th><?php echo __('Bild'); ?></th>
				<?php } ?>
				<th><?php echo __('Vorname'); ?></th>
				<th><?php echo __('Name'); ?></th>
				<th><?php echo __('Club'); ?></th>
				<?php if ($isReferee) { ?>
					<th><?php echo __('E-Mail'); ?></th>
					<th><?php echo __('Telefon'); ?></th>
				<?php } ?>
				<?php if ($isEditor) { ?>
					<th><?php echo __('Adresse'); ?></th>
					<th><?php echo __('Geschlecht'); ?></th>
					<th><?php echo __('Geburtstag'); ?></th>
					<th><?php echo __('Ausbildung'); ?></th>
					<th><?php echo __('Letzte Fortbildung'); ?></th>
					<th><?php echo __('Status'); ?></th>
					<th><?php echo __('Anmerkung'); ?></th>
				<?php } ?>
				<th><?php echo __('Aktionen'); ?></th>
			</tr>
		</tfoot>
		<tbody>
			<?php
				foreach ($referees as $referee) {
			?>
					<tr>
						<?php if ($isReferee) { ?>
							<td><?php echo $this->Html->image('http://placekitten.com/50/50', array('alt' => __('Bild von %s', $referee['Person']['title_person']), 'title' => $referee['Person']['title_person'])); ?></td>
						<?php } ?>

						<td data-title="<?php echo __('Vorname'); ?>"<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo $this->Html->link($referee['Person']['first_name'], array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id'])); ?></td>

						<td data-title="<?php echo __('Name'); ?>"<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo $this->Html->link($referee['Person']['name'], array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id']));  ?></td>

						<td data-title="<?php echo __('Club'); ?>"<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php
							if (!empty($referee['Club'])) {
								echo $this->Html->link($referee['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $referee['Club']['id']));
							}
						?></td>

						<?php if ($isReferee) { ?>
							<td<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo __('E-Mail'); ?></td>
							<td<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo __('Telefon'); ?></td>
						<?php } ?>

						<?php if ($isEditor) { ?>
							<td<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo __('Adresse'); ?></td>
							<td<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo __('Geschlecht'); ?></td>
							<td<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo __('Geburtstag'); ?></td>
							<td<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo __('Ausbildung'); ?></td>
							<td<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo __('Letzte Fortbildung'); ?></td>
							<td<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php echo __('Status'); ?></td>

							<td data-title="<?php echo __('Anmerkung'); ?>"<?php echo $statustypes[$referee['StatusType']['id']]['htmlstyle']; ?>><?php
								if (!empty($referee['Referee']['description'])) {
									echo h($referee['Referee']['description']);
								}
							?></td>
						<?php } ?>

						<td class="actions" data-title="<?php echo __('Aktionen'); ?>">
							<?php echo $this->element('actions_table', array('id' => $referee['Referee']['id']));	?>
						</td>
					</tr>
			<?php } ?>
		</tbody>
	</table>
<?php
	}
?>

<p><?php echo $this->Html->link('Export to Excel', array('controller' => 'referees', 'action' => 'export', 'excel')); ?></p>

<?php pr($referees); ?>


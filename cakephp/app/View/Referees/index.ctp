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
		if ($export != null) {
	?>
		<p><?php echo __('Daher wurde kein Export durchgeführt.'); ?></p>
	<?php
		}
	?>
<?php
	} else {

		if ($export == 'excel') {
			$this->PhpExcel->createWorksheet();
			$this->PhpExcel->setDefaultFont('Calibri', 11);
		}
?>
	<p>Legende:</p>
	<ul class="legend">
		<li class="referee-more">an besonders vielen Einsätzen interessiert</li>
		<li class="referee-notactive">nicht aktiv</li>
		<li class="referee-notactiveseason">nicht aktiv in dieser Saison</li>
		<?php if ($isEditor) { ?>
			<li class="referee-nolicense">Lizenz entzogen</li>
		<?php } ?>
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

						<td data-title="<?php echo __('Vorname'); ?>"><?php echo $this->Html->link($referee['Person']['first_name'], array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id'])); ?></td>

						<td data-title="<?php echo __('Name'); ?>"><?php echo $this->Html->link($referee['Person']['name'], array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id']));  ?></td>

						<td data-title="<?php echo __('Club'); ?>"><?php
							if (!empty($referee['Club'])) {
								echo $this->Html->link($referee['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $referee['Club']['id']));
							}
						?></td>

						<?php if ($isReferee) { ?>
							<td><?php echo __('E-Mail'); ?></td>
							<td><?php echo __('Telefon'); ?></td>
						<?php } ?>

						<?php if ($isEditor) { ?>
							<td><?php echo __('Adresse'); ?></td>
							<td><?php echo __('Geschlecht'); ?></td>
							<td><?php echo __('Geburtstag'); ?></td>
							<td><?php echo __('Ausbildung'); ?></td>
							<td><?php echo __('Letzte Fortbildung'); ?></td>
							<td><?php echo __('Status'); ?></td>

							<td data-title="<?php echo __('Anmerkung'); ?>"><?php
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

		if ($export == 'excel') {
			$this->PhpExcel->addTableFooter();
			$this->PhpExcel->output('VSR.xlsx');
		}

	}
?>

<p><?php echo $this->Html->link('Export to Excel', array('controller' => 'referees', 'action' => 'index', 'excel')); ?></p>

<?php pr($referees); ?>


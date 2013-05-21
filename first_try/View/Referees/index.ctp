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
	}
?>

<?php pr($referees); ?>


<div class="referees index">
	<h2><?php echo __('Referees'); ?></h2>
	<table cellpadding="0" cellspacing="0">
	<tr>
			<th><?php echo $this->Paginator->sort('id'); ?></th>
			<th><?php echo $this->Paginator->sort('license'); ?></th>
			<th><?php echo $this->Paginator->sort('last_referee_training'); ?></th>
			<th><?php echo $this->Paginator->sort('assignment_quantity_id'); ?></th>
			<th><?php echo $this->Paginator->sort('status_id'); ?></th>
			<th><?php echo $this->Paginator->sort('club_id'); ?></th>
			<th><?php echo $this->Paginator->sort('person_id'); ?></th>
			<th><?php echo $this->Paginator->sort('referee_kind_id'); ?></th>
			<th><?php echo $this->Paginator->sort('created'); ?></th>
			<th><?php echo $this->Paginator->sort('modified'); ?></th>
			<th class="actions"><?php echo __('Actions'); ?></th>
	</tr>
	<?php
	foreach ($referees as $referee): ?>
	<tr>
		<td><?php echo h($referee['Referee']['id']); ?>&nbsp;</td>
		<td><?php echo h($referee['Referee']['license']); ?>&nbsp;</td>
		<td><?php echo h($referee['Referee']['last_referee_training']); ?>&nbsp;</td>
		<td>
			<?php echo $this->Html->link($referee['AssignmentQuantity']['title'], array('controller' => 'assignment_quantities', 'action' => 'view', $referee['AssignmentQuantity']['id'])); ?>
		</td>
		<td>
			<?php echo $this->Html->link($referee['Status']['title'], array('controller' => 'statuses', 'action' => 'view', $referee['Status']['id'])); ?>
		</td>
		<td>
			<?php echo $this->Html->link($referee['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $referee['Club']['id'])); ?>
		</td>
		<td>
			<?php echo $this->Html->link($referee['Person']['title_person'], array('controller' => 'people', 'action' => 'view', $referee['Person']['id'])); ?>
		</td>
		<td>
			<?php echo $this->Html->link($referee['RefereeKind']['title'], array('controller' => 'referee_kinds', 'action' => 'view', $referee['RefereeKind']['id'])); ?>
		</td>
		<td><?php echo h($referee['Referee']['created']); ?>&nbsp;</td>
		<td><?php echo h($referee['Referee']['modified']); ?>&nbsp;</td>
		<td class="actions">
			<?php echo $this->Html->link(__('View'), array('action' => 'view', $referee['Referee']['id'])); ?>
			<?php echo $this->Html->link(__('Edit'), array('action' => 'edit', $referee['Referee']['id'])); ?>
			<?php echo $this->Form->postLink(__('Delete'), array('action' => 'delete', $referee['Referee']['id']), null, __('Are you sure you want to delete # %s?', $referee['Referee']['id'])); ?>
		</td>
	</tr>
<?php endforeach; ?>
	</table>
	<p>
	<?php
	echo $this->Paginator->counter(array(
	'format' => __('Page {:page} of {:pages}, showing {:current} records out of {:count} total, starting on record {:start}, ending on {:end}')
	));
	?>	</p>

	<div class="paging">
	<?php
		echo $this->Paginator->prev('< ' . __('previous'), array(), null, array('class' => 'prev disabled'));
		echo $this->Paginator->numbers(array('separator' => ''));
		echo $this->Paginator->next(__('next') . ' >', array(), null, array('class' => 'next disabled'));
	?>
	</div>
</div>
<div class="actions">
	<h3><?php echo __('Actions'); ?></h3>
	<ul>
		<li><?php echo $this->Html->link(__('New Referee'), array('action' => 'add')); ?></li>
		<li><?php echo $this->Html->link(__('List Clubs'), array('controller' => 'clubs', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Club'), array('controller' => 'clubs', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List People'), array('controller' => 'people', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Person'), array('controller' => 'people', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Statuses'), array('controller' => 'statuses', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Status'), array('controller' => 'statuses', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Assignment Quantities'), array('controller' => 'assignment_quantities', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Assignment Quantity'), array('controller' => 'assignment_quantities', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Referee Kinds'), array('controller' => 'referee_kinds', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Referee Kind'), array('controller' => 'referee_kinds', 'action' => 'add')); ?> </li>
		<li><?php echo $this->Html->link(__('List Referee Assignments'), array('controller' => 'referee_assignments', 'action' => 'index')); ?> </li>
		<li><?php echo $this->Html->link(__('New Referee Assignment'), array('controller' => 'referee_assignments', 'action' => 'add')); ?> </li>
	</ul>
</div>

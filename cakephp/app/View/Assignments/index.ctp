<div class="assignments index">
	<h2><?php echo $title_for_layout; ?></h2>

	<?php if ($isEditor) { ?>
		<p class="actions"><?php echo $this->Html->link(__('Neuen Schiedsrichtereinsatz hinzufügen'), array('action' => 'add'), array('class' => 'button-link access-editor')); ?></p>
	<?php } ?>

	<p>
		Saison: <?php echo $season . "/" . ($season + 1); ?>
		Startdatum: ##.##.####
		Enddatum: ##.##.####
	</p>

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
					<th><?php echo __('Datum/Zeit'); ?></th>
					<th><?php echo __('Spiel'); ?></th>
					<th><?php echo __('Liga'); ?></th>
					<th><?php echo __('Heimteam'); ?></th>
					<th><?php echo __('Auswärtsteam'); ?></th>
					<th><?php echo __('Schiedsrichter'); ?></th>
					<th><?php echo __('Aktionen'); ?></th>
				</tr>
			</thead>
			<tbody>
				<?php
					foreach ($assignments as $assignment) {
				?>
						<tr>

							<td data-title="<?php echo __('Datum/Zeit'); ?>">
								<?php echo $this->Html->link($this->RefereeFormat->format($assignment['Assignment']['datetime'], 'longdate'), array('controller' => 'dates', 'action' => 'view', $this->RefereeFormat->format($assignment['Assignment']['datetime'], 'datereverse'))); ?>,
								<?php echo h($this->RefereeFormat->format($assignment['Assignment']['datetime'], 'time')); ?>&nbsp;Uhr
							</td>

							<td data-title="<?php echo __('Spiel'); ?>"><?php echo $this->Html->link($assignment['Assignment']['game_number'], array('controller' => 'assignments', 'action' => 'view', $assignment['Assignment']['id'])); ?></td>

							<td data-title="<?php echo __('Liga'); ?>"><?php echo $this->Html->link($assignment['League']['code'], array('controller' => 'leagues', 'action' => 'view', $assignment['League']['id'])); ?></td>

							<td data-title="<?php echo __('Heimteam'); ?>"><?php echo (array_key_exists('HomeTeam', $assignment)) ? $this->Html->link($assignment['HomeTeam']['Team']['title_team'], array('controller' => 'teams', 'action' => 'view', $assignment['HomeTeam']['Team']['id'])) : h('??'); ?></td>

							<td data-title="<?php echo __('Auswärtsteam'); ?>"><?php echo (array_key_exists('RoadTeam', $assignment)) ? $this->Html->link($assignment['RoadTeam']['Team']['title_team'], array('controller' => 'teams', 'action' => 'view', $assignment['RoadTeam']['Team']['id'])) : h('??'); ?></td>

							<td data-title="<?php echo __('Schiedsrichter'); ?>">
								<?php foreach ($refereeroles as $refereerole) { ?>
									<?php
										if (array_key_exists($refereerole['code'], $assignment)) {
											$moreReferees = false;
											$refcount = 1;
											$refcountout = (count($assignment[$refereerole['code']]) > 1);
											foreach ($assignment[$refereerole['code']] as $referee) {
												if ($moreReferees) {
													echo '<br />';
												}
									?>
												<span title="<?php echo __($refereerole['title']); ?>" class="refrole"><?php
													echo __($refereerole['code']);
													if ($refcountout) {
														echo " ";
														echo $refcount;
													}
												?></span>
									<?php
												echo $this->Html->link($referee['Person']['Person']['title_person'], array('controller' => 'people', 'action' => 'view', $referee['Person']['Person']['id']));

												$moreReferees = true;
												$refcount++;
											}
											if (!$refcountout) {
												echo '<br />';
											}
										}
									?>
								<?php } ?>
							</td>
							<td class="actions" data-title="<?php echo __('Aktionen'); ?>">
								<?php echo $this->Html->link(__('iCal File'), array('action' => 'ics', $assignment['Assignment']['id']), array('class' => 'button-link')); ?>
								<?php echo $this->element('actions_table', array('id' => $assignment['Assignment']['id']));	?>
							</td>
						</tr>
				<?php } ?>
			</tbody>
		</table>
	<?php
		}
	?>

	<!--?php pr($assignments); ?-->

</div>


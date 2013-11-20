<?php echo $this->element('actions_header');	?>
<!-- header actions -->

<!-- view filters -->
<div class="filter">
	<p>Saison: <?php echo $season['title_season']; ?></p>
</div>

<!-- view content -->
<?php
	if (empty($refereeassignments)) {
?>
	<p><?php echo __('Es sind keine Schiedsrichtereinsätze gespeichert.'); ?></p>
<?php
	} else {
?>
	<table>
		<?php
			$columns = array();
				$columns[] = __('Datum');
				$columns[] = __('Uhrzeit');
				$columns[] = __('Spiel');
				$columns[] = __('Liga');
				$columns[] = __('Heimteam');
				$columns[] = __('Auswärtsteam');
				$columns[] = __('Schiedsrichter');

				if ($isEditor) {
					$columns[] = __('Anmerkung');
				}

				$columns[] = __('Aktionen');
			$table = array('thead', 'tfoot');

			foreach ($table as $tabletag) {
				echo sprintf('<%s><tr>', $tabletag);
				foreach ($columns as $column) {
					echo sprintf('<th>%s</th>', $column);
				}
				echo sprintf('</tr></%s>', $tabletag);
			}
		?>
		<tbody>
			<?php
				foreach ($refereeassignments as $refereeassignment) {
					$curcol = 0;
			?>
					<tr>
						<?php if ($isReferee) { ?>
							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								if (!empty($referee['Picture'])) {
									echo $this->Html->link($this->Html->image($referee['Picture']['url'], array('width' => '50', 'alt' => __('Bild von %s %s', $referee['Person']['first_name'], $referee['Person']['name']), 'title' => __('%s %s', $referee['Person']['first_name'], $referee['Person']['name']))), $referee['Picture']['url'], array('escape' => false));
								}
							?></td>
						<?php } ?>

						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php echo $this->Html->link($this->RefereeFormat->formatPerson($referee['Person'], 'name'), array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']));  ?></td>

						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php echo $this->Html->link($this->RefereeFormat->formatPerson($referee['Person'], 'first_name'), array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle'])); ?></td>

						<?php
							foreach ($refereerelationtypes as $sid => $refereerelationtype) {
								if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
						?>
							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								$hasMore = false;
								if (array_key_exists($sid, $referee['RefereeRelation'])) {
									foreach ($referee['RefereeRelation'][$sid] as $refereerelation) {
										if ($hasMore) {
											$text .= '; ';
										}
										if (array_key_exists('Club', $refereerelation)) {
											$text .= $this->Html->link($refereerelation['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $refereerelation['Club']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']));
										}
										if (array_key_exists('League', $refereerelation)) {
											$text .= $this->Html->link($refereerelation['League']['title'], array('controller' => 'leagues', 'action' => 'view', $refereerelation['League']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']));
										}
										$hasMore = true;
									}
								}
								echo $text;
							?></td>
						<?php
								}
							}
						?>

						<?php if ($isReferee) { ?>
							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (array_key_exists('Contact', $referee) && array_key_exists('Email', $referee['Contact'])) {
									$hasMore = false;
									$printType = (count($referee['Contact']['Email']) > 1);
									foreach ($referee['Contact']['Email'] as $contacttype => $emailkind) {
										$printType |= (count($emailkind) > 1);
										foreach ($emailkind as $email) {
											if ($hasMore) {
												$text .= '<br />';
											}
											if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
												$text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
											}
											$text .= $this->RefereeFormat->formatEMail($email, 'link');
											$hasMore = true;
										}
									}
								}
								echo $text;
							?></td>

							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (array_key_exists('Contact', $referee) && array_key_exists('PhoneNumber', $referee['Contact'])) {
									$hasMore = false;
									$printType = (count($referee['Contact']['PhoneNumber']) > 1);
									foreach ($referee['Contact']['PhoneNumber'] as $contacttype => $phonekind) {
										$printType |= (count($phonekind) > 1);
										foreach ($phonekind as $phone) {
											if ($hasMore) {
												$text .= '<br />';
											}
											if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
												$text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
											}
											$text .= $this->RefereeFormat->formatPhone($phone, 'normal');
											$hasMore = true;
										}
									}
								}
								echo $text;
							?></td>
						<?php } ?>

						<?php if ($isEditor) { ?>
							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (array_key_exists('Contact', $referee) && array_key_exists('Address', $referee['Contact'])) {
									$hasMore = false;
									$printType = (count($referee['Contact']['Address']) > 1);
									foreach ($referee['Contact']['Address'] as $contacttype => $addresskind) {
										$printType |= (count($addresskind) > 1);
										foreach ($addresskind as $address) {
											if ($hasMore) {
												$text .= '<br />';
											}
											if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
												$text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
											}
											$text .= $this->RefereeFormat->formatAddress($address, 'fulladdress');
											$hasMore = true;
										}
									}
								}
								echo $text;
							?></td>

							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								echo __($referee['SexType']['title']);
							?></td>

							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (!empty($referee['Person']['birthday'])) {
									$text .= $this->RefereeFormat->formatPerson($referee['Person'], 'birthday');
								}
								echo $text;
							?></td>
						<?php } ?>

						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
							$text = '';
							if (!empty($referee['TrainingLevelInfo'])) {
								$text .= $this->Html->link($referee['TrainingLevelInfo']['abbreviation'], array('controller' => 'trainingleveltype', 'action' => 'view', $referee['TrainingLevelInfo']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']));
							}
							echo $text;
						?></td>

						<?php if ($isEditor) { ?>
							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['lastupdate'])) {
									$text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['lastupdate'], 'date');
								}
								echo $text;
							?></td>

							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								$text = '';
								if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['nextupdate'])) {
									$text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['nextupdate'], 'year');
								}
								echo $text;
							?></td>

							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								if (!empty($referee['Person']['remark'])) {
									echo h($referee['Person']['remark']);
								}
							?></td>
						<?php } ?>

						<td class="actions" data-title="<?php echo __('Aktionen'); ?>">
							<?php echo $this->element('actions_table', array('id' => $referee['Referee']['id']));	?>
						</td>
					</tr>
			<?php
				}
			?>
		</tbody>
	</table>

<?php
	}
?>

<?php pr($refereeassignments); ?>


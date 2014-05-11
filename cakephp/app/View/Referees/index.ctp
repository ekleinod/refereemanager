<?php echo $this->element('actions_header');	?>
<!-- header actions -->

<!-- view filters -->
<div class="filter">
	<p>Saison: <?php echo $season['title_season']; ?></p>
</div>


<!-- view content -->
<?php
	if (empty($referees)) {
?>
	<p><?php echo __('Es sind keine Schiedsrichter_innen gespeichert.'); ?></p>
<?php
	} else {
?>
	<p><?php echo __('Legende:'); ?></p>
	<ul class="legend">
		<?php
			// compute different styles
			foreach ($statustypes as &$statustype) {

				$statustype['outputstyle'] = '';

				if ($statustype['style']) {
					switch ($statustype['style']) {
						case 'normal':
						case 'italic':
						case 'oblique':
							$statustype['outputstyle'] .= sprintf('font-style: %s; ', $statustype['style']);
							break;
						case 'normal':
						case 'bold':
						case 'bolder':
						case 'lighter':
							$statustype['outputstyle'] .= sprintf('font-weight: %s; ', $statustype['style']);
							break;
					}
				}

				if ($statustype['color']) {
					$statustype['outputstyle'] .= sprintf('color: #%s; ', $statustype['color']);
				}

				if ($statustype['bgcolor']) {
					$statustype['outputstyle'] .= sprintf('background-color: #%s; ', $statustype['bgcolor']);
				}

				if (($statustype['sid'] == StatusType::SID_MANY) ||
						($statustype['sid'] == StatusType::SID_INACTIVESEASON) ||
						($statustype['sid'] == StatusType::SID_OTHER)) {
		?>
			<li style="<?php echo $statustype['outputstyle']; ?>"><?php echo ($statustype['remark']) ? h($statustype['remark']) : h($statustype['title']); ?></li>
		<?php
				}
			}
		?>
	</ul>

	<p>
		<?php echo $this->Html->link('Export als Excel-Datei', array('controller' => 'referees', 'action' => 'export', $season['year_start'], 'excel')); ?>
		|
		<?php echo $this->Html->link('Export als PDF', array('controller' => 'referees', 'action' => 'export', $season['year_start'], 'pdf')); ?>
		<?php if ($isEditor) { ?>
			|
			<?php echo $this->Html->link('Datenübersicht (zip)', array('controller' => 'referees', 'action' => 'export', $season['year_start'], 'referee_view_zip')); ?>
		<?php } ?>

	</p>

	<table>
		<?php
			$columns = array();
				if ($isReferee) {
					$columns[] = __('Bild');
				}

				$columns[] = __('Name');
				$columns[] = __('Vorname');

				foreach ($refereerelationtypes as $sid => $refereerelationtype) {
					if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
						$columns[] = __($refereerelationtype['title']);
					}
				}

				if ($isReferee) {
					$columns[] = __('E-Mail');
					$columns[] = __('Telefon');
				}

				if ($isEditor) {
					$columns[] = __('Adresse');
					$columns[] = __('Geschlecht');
					$columns[] = __('Geburtstag');
				}

				$columns[] = __('Ausbildung');

				if ($isEditor) {
					$columns[] = __('Letzte Ausbildung');
					$columns[] = __('Letzte Fortbildung');
					$columns[] = __('Nächste Fortbildung');
					$columns[] = __('Anmerkung');
					$columns[] = __('Interne Anmerkung');
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
				foreach ($referees as $referee) {
					$curcol = 0;
			?>
					<tr>
						<?php if ($isReferee) { ?>
							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								if (!empty($referee['Picture'])) {
									echo $this->Html->link($this->Html->image($referee['Picture']['url'], array('width' => '50', 'alt' => __('Bild von %s', $this->RefereeFormat->formatPerson($referee['Person'], 'fullname')), 'title' => h($this->RefereeFormat->formatPerson($referee['Person'], 'fullname')))), $referee['Picture']['url'], array('escape' => false));
								}
							?></td>
						<?php } ?>

						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php echo $this->Html->link($this->RefereeFormat->formatPerson($referee['Person'], 'name_title'), array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id']), array('style' => $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']));  ?></td>

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
										if (array_key_exists('SexType', $refereerelation)) {
											$text .= h($refereerelation['SexType']['title']);
										}
										if (array_key_exists('Saturday', $refereerelation)) {
											$text .= __('Sonnabend');
										}
										if (array_key_exists('Sunday', $refereerelation)) {
											$text .= __('Sonntag');
										}
										if (array_key_exists('Remark', $refereerelation)) {
											$text .= h($refereerelation['Remark']);
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
								if (!empty($referee['Person']['dayofdeath'])) {
									$text .= __(' %s', $this->RefereeFormat->formatPerson($referee['Person'], 'dayofdeath'));
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
								if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['since'])) {
									$text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['since'], 'date');
								}
								echo $text;
							?></td>

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

							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']; ?>"><?php
								if (!empty($referee['Person']['internal_remark'])) {
									echo h($referee['Person']['internal_remark']);
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

	<h3><?php echo __('Zusatzinformationen'); ?></h3>
	<?php
		foreach ($statustypes as $outstatustype) {
			if (($outstatustype['sid'] == StatusType::SID_MANY) ||
					($outstatustype['sid'] == StatusType::SID_INACTIVESEASON) ||
					($outstatustype['sid'] == StatusType::SID_OTHER)) {
	?>
		<p><strong><?php echo ($outstatustype['remark']) ? h($outstatustype['remark']) : h($outstatustype['title']); ?></strong></p>
		<p>
			<?php
				$hasMore = false;
				$text = '';
				foreach ($outstatustype['referees'] as $referee) {
					if ($hasMore) {
						$text .= ', ';
					}
					$text .= h($this->RefereeFormat->formatPerson($referee, 'fullname'));
					$hasMore = true;
				}
				echo $text;
			?>
		</p>
	<?php
			}
		}
	?>

<?php
	}
?>

<?php pr($refereerelationtypes); ?>
<?php pr($referees); ?>


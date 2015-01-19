<?php echo $this->element('Functions/table');	?>

<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<?php $isRefView = isset($isRefView) && $isRefView; ?>

<!-- view content -->
<p><?php
	echo __('Es %s %s %s gespeichert.',
					(count($people) == 1) ? 'ist' : 'sind',
					(empty($people)) ? 'keine' : count($people),
					(count($people) == 1) ? (($isRefView) ? 'Schiedsrichter_in' : 'Person') : (($isRefView) ? 'Schiedsrichter_innen' : 'Personen')
					);
?></p>
<?php
	if (!empty($people)) {
?>

	<?php if ($isRefView) { ?>
		<p><?php echo __('Legende:'); ?></p>
		<ul class="legend">
			<?php
				// compute different output styles
				foreach ($statustypes as &$statustype) {

					$statustype['outputstyle'] = '';

					if ($statustype['StatusType']['style']) {
						switch ($statustype['StatusType']['style']) {
							case 'normal':
							case 'italic':
							case 'oblique':
								$statustype['outputstyle'] .= sprintf('font-style: %s; ', $statustype['StatusType']['style']);
								break;
							case 'normal':
							case 'bold':
							case 'bolder':
							case 'lighter':
								$statustype['outputstyle'] .= sprintf('font-weight: %s; ', $statustype['StatusType']['style']);
								break;
						}
					}

					if ($statustype['StatusType']['color']) {
						$statustype['outputstyle'] .= sprintf('color: #%s; ', $statustype['StatusType']['color']);
					}

					if ($statustype['StatusType']['bgcolor']) {
						$statustype['outputstyle'] .= sprintf('background-color: #%s; ', $statustype['StatusType']['bgcolor']);
					}

			?>
					<li style="<?php echo $statustype['outputstyle']; ?>"><?php echo ($statustype['StatusType']['remark']) ? h($statustype['StatusType']['remark']) : h($statustype['StatusType']['title']); ?></li>
			<?php
				}
			?>
		</ul>

		<p>
			<?php echo $this->Html->link('Export als Excel-Datei', array('action' => 'export', $season['year_start'], 'excel')); ?>
			|
			<?php echo $this->Html->link('Export als PDF', array('action' => 'export', $season['year_start'], 'pdf')); ?>
			<?php if ($isEditor) { ?>
				|
				<?php echo $this->Html->link('Datenübersicht (zip)', array('action' => 'export', $season['year_start'], 'referee_view_zip')); ?>
			<?php } ?>
		</p>
	<?php } ?>


	<table>
		<?php
			$columns = array();
				if ($isReferee) {
					$columns[] = __('Bild');
				}

				$columns[] = __('Name');
				$columns[] = __('Vorname');

				if ($isRefView) {
					foreach ($refereerelationtypes as $sid => $refereerelationtype) {
						if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
							$columns[] = __($refereerelationtype['title']);
						}
					}
				}

				if ($isReferee) {
					$columns[] = __('E-Mail');
					$columns[] = __('Telefon');
				}

				if ($isEditor) {
					$columns[] = __('Adresse');
				}

				if ($isReferee) {
					$columns[] = __('URL');
				}

				if ($isEditor) {
					$columns[] = __('Geschlecht');
					$columns[] = __('Geburtstag<br />Todestag');
				}

				if ($isRefView) {
					$columns[] = __('Ausbildung');
				}

				if ($isRefView && $isEditor) {
					$columns[] = __('Letzte Ausbildung');
					$columns[] = __('Letzte Fortbildung');
					$columns[] = __('Nächste Fortbildung');
				}

				if ($isEditor) {
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
				foreach ($people as $person) {
					$curcol = 0;
					$tmpFormat = '';
					$refLine = '';
					if ($isRefView) {
						$tmpStatus = $this->People->getRefereeStatus($person, $season);
						if (($tmpStatus !== null) && (array_key_exists($tmpStatus['status_type_id'], $statustypes))) {
							$tmpFormat = $statustypes[$tmpStatus['status_type_id']]['outputstyle'];
						}
					}
			?>
					<tr>
						<?php

							if ($isReferee) {
								$refLine .= getTD($columns[$curcol++], $tmpFormat, (empty($person['Picture']['url'])) ? '' :
																	$this->Html->link($this->Html->image($person['Picture']['url'], array('width' => '50', 'alt' => __('Bild von **generated fullname**.'), 'title' => '**generated fullname**')), $person['Picture']['url'], array('escape' => false)));
							}

							$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated name_title**');

							$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated first_name**');

							if ($isRefView) {
								foreach ($refereerelationtypes as $sid => $refereerelationtype) {
									if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
										$refLine .= getTD($columns[$curcol++], $tmpFormat, sprintf('**generated referee_relation_%s**', $sid));
									}
								}
							}

							if ($isReferee) {
								$tmpValue = $this->RefereeFormat->formatContacts($this->People->getContacts($person, 'Email'), 'text', 'Email', 'html');
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated emails**');

								$tmpValue = $this->RefereeFormat->formatContacts($this->People->getContacts($person, 'PhoneNumber'), 'national', 'PhoneNumber', 'html');
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated phone_numbers**');
							}

							if ($isEditor) {
								$tmpValue = $this->RefereeFormat->formatContacts($this->People->getContacts($person, 'Address'), 'fulladdress', 'Address', 'html');
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated addresses**');
							}

							if ($isReferee) {
								$tmpValue = $this->RefereeFormat->formatContacts($this->People->getContacts($person, 'Url'), 'text', 'Url', 'html');
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated urls**');
							}

							if ($isEditor) {
								$tmpValue = h($person['SexType']['title']);
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated sextype**');

								$tmpValue = array();
								if (!empty($person['Person']['birthday'])) {
									$tmpValue[] = '**generated birthday**';
								}
								if (!empty($person['Person']['dayofdeath'])) {
									$tmpValue[] = '&dagger;&nbsp;**generated dayofdeath**';
								}
								$refLine .= getTD($columns[$curcol++], $tmpFormat, $this->RefereeFormat->formatMultiline($tmpValue));
							}

							if ($isRefView) {
								$theTrainingLevel = $this->People->getTrainingLevel($person);
								$tmpValue = $theTrainingLevel['TrainingLevelType']['abbreviation'];
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated traininglevel**');
							}

							if ($isEditor) {
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated lasttraining**');

								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated lasttrainingupdate**');

								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated nexttrainingupdate**');
							}

							if ($isEditor) {
								$tmpValue = (empty($person['Person']['remark'])) ? '' : $person['Person']['remark'];
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated remark**');

								$tmpValue = (empty($person['Person']['internal_remark'])) ? '' : $person['Person']['internal_remark'];
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated internal_remark**');
							}

							$refLine .= getTD(__('Aktionen'), '', $this->element('actions_table', array('id' => $person['Person']['id'])), 'actions');

							echo $this->Template->replaceRefereeData($refLine, $person, 'text', 'html');
						?>
					</tr>
			<?php
				}
			?>
		</tbody>
	</table>

	<?php if ($isRefView) { ?>
		<h3><?php echo __('Zusatzinformationen'); ?></h3>
		<?php
			foreach ($statustypes as $outstatustype) {
		?>
			<p><strong><?php echo ($outstatustype['StatusType']['remark']) ? h($outstatustype['StatusType']['remark']) : h($outstatustype['StatusType']['title']); ?></strong></p>
			<p>
				<?php
					$arrNames = array();
					foreach ($outstatustype['referees'] as $referee) {
						$arrNames[] = $this->RefereeFormat->formatPerson($referee, 'fullname');
					}
					echo $this->RefereeFormat->formatMultiline($arrNames, ', ');
				?>
			</p>
	<?php
			}
		}
	?>

<?php
	}
?>

<?php debug($people); ?>


<?php $this->element('Functions/table');	?>

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

		<?php include_once('statustypes.php'); ?>

		<p><?php echo __('Legende:'); ?></p>
		<ul class="legend">

			<?php
				foreach ($statustypes as $statustype) {
			?>
					<li style="<?php echo $statustype['outputstyle']['html']; ?>"><?php echo ($statustype['StatusType']['remark']) ? h($statustype['StatusType']['remark']) : h($statustype['StatusType']['title']); ?></li>
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
		<?php include_once('columns.php'); ?>

		<?php
			$table = array('thead', 'tfoot');

			foreach ($table as $tabletag) {
				echo sprintf('<%s><tr>', $tabletag);
				foreach ($columns as $column) {
					echo sprintf('<th>%s</th>', $column['title']);
				}
				echo sprintf('</tr></%s>', $tabletag);
			}
		?>
		<tbody>
			<?php
				foreach ($people as $person) {
					$tmpDataID = '';
					$tmpFormat = '';
					$refLine = '';
					if ($isRefView) {
						$tmpStatus = $this->People->getRefereeStatus($person, $season);
						if (($tmpStatus !== null) && (array_key_exists($tmpStatus['status_type_id'], $statustypes))) {
							$tmpFormat = $statustypes[$tmpStatus['status_type_id']]['outputstyle']['html'];
						}
					}
			?>
					<tr>
						<?php

							$tmpDataID = 'picture';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, (empty($person['Picture']['url'])) ? '' :
																	$this->Html->link($this->Html->image($person['Picture']['url'], array('width' => '50', 'alt' => __('Bild von **generated fullname**.'), 'title' => $this->Template->getReplaceToken('fullname'))), $person['Picture']['url'], array('escape' => false)));
							}

							$tmpDataID = 'name_title';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'first_name';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							if ($isRefView) {
								foreach ($refereerelationtypes as $sid => $refereerelationtype) {
									if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
										$tmpDataID = sprintf('referee_relation_%s', $sid);
										if (array_key_exists($tmpDataID, $columns)) {
											$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
										}
									}
								}
							}

							$tmpDataID = 'emails';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'phone_numbers_national';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'addresses_fulladdress';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'urls';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'sextype';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'birthday';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'dayofdeath';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'traininglevel';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'traininglevelsince';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'lasttrainingupdate';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'nexttrainingupdate';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'remark';
							if (array_key_exists($tmpDataID, $columns)) {
								$tmpValue = (empty($person['Person'][$tmpDataID])) ? '' : $person['Person'][$tmpDataID];
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'internal_remark';
							if (array_key_exists($tmpDataID, $columns)) {
								$tmpValue = (empty($person['Person'][$tmpDataID])) ? '' : $person['Person'][$tmpDataID];
								$refLine .= getTD($columns[$tmpDataID]['title'], $tmpFormat, $this->Template->getReplaceToken($tmpDataID));
							}

							$tmpDataID = 'actions';
							if (array_key_exists($tmpDataID, $columns)) {
								$refLine .= getTD($columns[$tmpDataID]['title'], '', $this->element('actions_table', array('id' => $person['Person']['id'])), $tmpDataID);
							}

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


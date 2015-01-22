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
		<?php include_once('columns.php'); ?>

		<?php
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
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated emails**');

								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated phone_numbers_national**');
							}

							if ($isEditor) {
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated addresses_fulladdress**');
							}

							if ($isReferee) {
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated urls**');
							}

							if ($isEditor) {
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated sextype**');

								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated birthday**');

								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated dayofdeath**');
							}

							if ($isRefView) {
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated traininglevel**');
							}

							if ($isEditor) {
								$refLine .= getTD($columns[$curcol++], $tmpFormat, '**generated traininglevelsince**');

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


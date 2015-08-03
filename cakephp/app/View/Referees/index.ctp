<?php $this->element('Functions/table');	?>

<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<?php $isRefView = isset($isRefView) && $isRefView; ?>

<!-- view content -->
<div class="row">
	<div class="col-sm-12">

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
				<?php

					$ulout = array();
					foreach ($statustypes as $statustype) {
						$ulout[] = $this->Html->tag('span',
																				($statustype['StatusType']['remark']) ? h($statustype['StatusType']['remark']) : h($statustype['StatusType']['title']),
																				array('class' => $statustype['StatusType']['sid'])
																				);
					}
					echo $this->Html->nestedList($ulout, array('class' => 'legend'));
				?>

							<!--li style=" echo $statustype['outputstyle']['html']; "-->

				<p>
					<?php echo $this->Html->link('Export als Excel-Datei', array('action' => 'export', $season['Season']['year_start'], 'excel')); ?>
					|
					<?php echo $this->Html->link('Export als PDF', array('action' => 'export', $season['Season']['year_start'], 'pdf')); ?>
					<?php if ($isEditor) { ?>
						|
						<?php echo $this->Html->link('Datenübersicht (zip)', array('action' => 'export', $season['Season']['year_start'], 'referee_view_zip')); ?>
					<?php } ?>
				</p>
			<?php } ?>


			<table>
				<?php include_once('columns.php'); ?>

				<?php
					$table = array('thead', 'tfoot');

					foreach ($table as $tabletag) {
						echo sprintf('<%s><tr>', $tabletag);
						foreach ($columns['index'] as $column) {
							echo sprintf('<th>%s</th>', $column['title']);
						}
						echo sprintf('<th>%s</th>', __('Aktionen'));
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

									foreach ($columns['index'] as $column) {
										$refLine .= getTD($column['title'], $tmpFormat, $column['content']);
									}

									$refLine .= getTD(__('Aktionen'), '', $this->element('actions_table', array('id' => $person['Person']['id'])), $tmpDataID);

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

	</div>
</div>


<?php echo $this->element('Functions/table');	?>
<?php echo $this->element('Functions/assignments');	?>

<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<!-- view content -->
<div class="row">
	<div class="col-sm-12">

		<?php
			if (empty($assignments)) {
		?>
				<p><?php echo __('Es sind keine Schiedsrichtereinsätze gespeichert.'); ?></p>
		<?php
			} else {
		?>
				<table class="table table-striped table-bordered table-condensed table-responsive">
					<caption><?php echo $title_for_layout; ?> <?php echo $season['Season']['title_season']; ?></caption>
					<?php
						$cells = array();
							$cells[] = __('Datum');
							$cells[] = __('Uhrzeit');
							$cells[] = __('Spiel');
							$cells[] = __('Liga');
							$cells[] = __('Heimteam');
							$cells[] = __('Auswärtsteam');
							$cells[] = __('Schiedsrichter');
							$cells[] = __('OSR-Bericht');

							if ($isEditor) {
								$cells[] = __('Anmerkung');
							}

							$cells[] = __('Aktionen');

						echo $this->Html->tag('thead', $this->Html->tableHeaders($cells));
						echo $this->Html->tag('tfoot', $this->Html->tableHeaders($cells));

					?>
					<tbody>
						<?php
							foreach ($assignments as $assignment) {
								$cells = array();
									$cells[] = $this->RefereeFormat->formatDate($assignment['Assignment']['start'], 'longdatereverse');
									$cells[] = $this->RefereeFormat->formatDate($assignment['Assignment']['start'], 'time');
									$cells[] = h($assignment['LeagueGame']['game_number']);
									$cells[] = __($assignment['LeagueGame']['League']['abbreviation']);

									$cells[] = __('todo');
									$cells[] = __('todo');
									$cells[] = __('todo');

									$tmpRefRep = getRefereeReport($assignment, $season);
									$cells[] = ($tmpRefRep === null) ?
															array('&cross;', array('class' => 'danger')) :
															array('&check;', array('class' => 'success'));

									if ($isEditor) {
										$cells[] = (empty($assignment['remark'])) ? '' : h($assignment['remark']);
									}

									$cells[] = $this->element('actions_table', array('id' => $assignment['Assignment']['id']));

								echo $this->Html->tableCells($cells);
							}
						?>
					</tbody>
				</table>

		<?php
			}
		?>

		<?php debug($assignments); ?>

	</div>
</div>


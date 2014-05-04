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

		$styles = array();
		$styles['normal'] = '';
		$styles['changed'] = sprintf('background-color: #%s; ', 'FFFFDD');

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

						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$refereeassignment['status']]; ?>"><?php echo $this->RefereeFormat->formatDate($refereeassignment['start'], 'longdatereverse'); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$refereeassignment['status']]; ?>"><?php echo $this->RefereeFormat->formatDate($refereeassignment['start'], 'time'); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$refereeassignment['status']]; ?>"><?php echo h($refereeassignment['game_number']); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$refereeassignment['status']]; ?>"><?php echo __($refereeassignment['league']); ?></td>

						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$refereeassignment['status']]; ?>"><?php echo __('todo'); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$refereeassignment['status']]; ?>"><?php echo __('todo'); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$refereeassignment['status']]; ?>"><?php echo __('todo'); ?></td>

						<?php if ($isEditor) { ?>
							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$refereeassignment['status']]; ?>"><?php
								if (!empty($refereeassignment['remark'])) {
									echo h($refereeassignment['remark']);
								}
							?></td>
						<?php } ?>

						<td class="actions" data-title="<?php echo __('Aktionen'); ?>">
							<?php echo $this->element('actions_table', array('id' => $refereeassignment['id']));	?>
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


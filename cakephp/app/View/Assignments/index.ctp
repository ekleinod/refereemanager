<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<!-- view content -->
<?php
	if (empty($assignments)) {
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
				foreach ($assignments as $assignment) {
					$curcol = 0;
			?>
					<tr>

						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$assignment['status']]; ?>"><?php echo $this->RefereeFormat->formatDate($assignment['start'], 'longdatereverse'); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$assignment['status']]; ?>"><?php echo $this->RefereeFormat->formatDate($assignment['start'], 'time'); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$assignment['status']]; ?>"><?php echo h($assignment['game_number']); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$assignment['status']]; ?>"><?php echo __($assignment['league']); ?></td>

						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$assignment['status']]; ?>"><?php echo __('todo'); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$assignment['status']]; ?>"><?php echo __('todo'); ?></td>
						<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$assignment['status']]; ?>"><?php echo __('todo'); ?></td>

						<?php if ($isEditor) { ?>
							<td data-title="<?php echo $columns[$curcol++]; ?>" style="<?php echo $styles[$assignment['status']]; ?>"><?php
								if (!empty($assignment['remark'])) {
									echo h($assignment['remark']);
								}
							?></td>
						<?php } ?>

						<td class="actions" data-title="<?php echo __('Aktionen'); ?>">
							<?php echo $this->element('actions_table', array('id' => $assignment['id']));	?>
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

<?php pr($assignments); ?>


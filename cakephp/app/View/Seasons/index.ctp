<?php echo $this->element('Functions/table');	?>

<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<!-- view content -->
<?php
	if (empty($seasons)) {
?>
	<p><?php echo __('Es sind keine Saisons gespeichert.'); ?></p>
<?php
	} else {
?>
	<table>
		<?php
			$columns = array();
				$columns[] = __('Titel');
				$columns[] = __('Startjahr');

				if ($isEditor) {
					$columns[] = __('Nur Editoren');
				}

				$columns[] = __('Anmerkung');

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
				foreach ($seasons as $season) {
					$curcol = 0;
			?>
					<tr>
						<?php
							echo(getTD($columns[$curcol++], '', h($season['Season']['title_season'])));
							echo(getTD($columns[$curcol++], '', h($season['Season']['year_start'])));

							if ($isEditor) {
								echo(getTD($columns[$curcol++], '', ((empty($season['Season']['editor_only'])) ? '&cross;' : '&check;')));
							}

							echo(getTD($columns[$curcol++], '', h($season['Season']['remark'])));

							echo(getTD(__('Aktionen'), '', $this->element('actions_table', array('id' => $season['Season']['id'])), 'actions'));
						?>
					</tr>
			<?php
				}
			?>
		</tbody>
	</table>

<?php
	}
?>

<?php debug($seasons); ?>


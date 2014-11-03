<?php echo $this->element('Functions/table');	?>

<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<!-- view content -->
<?php
	if (empty($leagues)) {
?>
	<p><?php echo __('Es sind keine Ligen gespeichert.'); ?></p>
<?php
	} else {
?>
	<table>
		<?php
			$columns = array();
				$columns[] = __('Titel');
				$columns[] = __('Abkürzung');
				$columns[] = __('Liga-Typ');
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
				foreach ($leagues as $league) {
					$curcol = 0;
			?>
					<tr>
						<?php
							echo(getTD($columns[$curcol++], '', h($league['League']['title'])));
							echo(getTD($columns[$curcol++], '', h($league['League']['abbreviation'])));
							echo(getTD($columns[$curcol++], '', h($league['LeagueType']['title'])));
							echo(getTD($columns[$curcol++], '', h($league['League']['remark'])));

							echo(getTD(__('Aktionen'), '', $this->element('actions_table', array('id' => $league['League']['id'])), 'actions'));
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

<?php debug($leagues); ?>


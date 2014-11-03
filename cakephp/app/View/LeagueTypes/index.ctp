<?php echo $this->element('Functions/table');	?>

<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<!-- view content -->
<?php
	if (empty($leaguetypes)) {
?>
	<p><?php echo __('Es sind keine Liga-Typen gespeichert.'); ?></p>
<?php
	} else {
?>
	<table>
		<?php
			$columns = array();
				$columns[] = __('Titel');
				$columns[] = __('Geschlecht');
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
				foreach ($leaguetypes as $leaguetype) {
					$curcol = 0;
			?>
					<tr>
						<?php
							echo(getTD($columns[$curcol++], '', h($leaguetype['LeagueType']['title'])));
							echo(getTD($columns[$curcol++], '', h($leaguetype['SexType']['title'])));
							echo(getTD($columns[$curcol++], '', h($leaguetype['LeagueType']['remark'])));

							echo(getTD(__('Aktionen'), '', $this->element('actions_table', array('id' => $leaguetype['LeagueType']['id'])), 'actions'));
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

<?php debug($leaguetypes); ?>


<?php echo $this->element('Functions/table');	?>

<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<!-- view content -->
<?php
	if (empty($people)) {
?>
	<p><?php echo __('Es sind keine Personen gespeichert.'); ?></p>
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
				foreach ($people as $person) {
					$curcol = 0;
			?>
					<tr>
						<?php
							echo(getTD($columns[$curcol++], '', h($person['Person']['title'])));
							echo(getTD($columns[$curcol++], '', h($person['SexType']['title'])));
							echo(getTD($columns[$curcol++], '', h($person['Person']['remark'])));

							echo(getTD(__('Aktionen'), '', $this->element('actions_table', array('id' => $person['Person']['id'])), 'actions'));
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

<?php debug($people); ?>


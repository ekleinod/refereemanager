<?php echo $this->element('Functions/table');	?>

<?php echo $this->element('filter');	?>

<?php echo $this->element('actions_header');	?>

<!-- view content -->
<?php
	if (empty($clubs)) {
?>
	<p><?php echo __('Es sind keine Clubs gespeichert.'); ?></p>
<?php
	} else {
?>
	<table>
		<?php
			$columns = array();
				$columns[] = __('Abkürzung');
				$columns[] = __('Name');
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
				foreach ($clubs as $club) {
					$curcol = 0;
			?>
					<tr>
						<?php
							echo(getTD($columns[$curcol++], '', h($club['Club']['abbreviation'])));
							echo(getTD($columns[$curcol++], '', h($club['Club']['name'])));
							echo(getTD($columns[$curcol++], '', h($club['Club']['remark'])));

							echo(getTD(__('Aktionen'), '', $this->element('actions_table', array('id' => $club['Club']['id'])), 'actions'));
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

<?php debug($clubs); ?>


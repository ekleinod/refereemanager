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
	<p><?php echo __('Es sind %d Personen gespeichert.', count($people)); ?></p>
	<table>
		<?php
			$columns = array();
				if ($isReferee) {
					$columns[] = __('Bild');
				}

				$columns[] = __('Name');
				$columns[] = __('Vorname');

				if ($isReferee) {
					$columns[] = __('E-Mail');
					$columns[] = __('Telefon');
				}

				if ($isEditor) {
					$columns[] = __('Adresse');
					$columns[] = __('Geschlecht');
					$columns[] = __('Geburtstag');
					$columns[] = __('Todestag');
					$columns[] = __('Anmerkung');
					$columns[] = __('Interne Anmerkung');
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
				foreach ($people as $person) {
					$curcol = 0;
			?>
					<tr>
						<?php

							$tmpValue = ($person['Picture']['url'] === null) ? '' :
									$this->Html->link($this->Html->image($person['Picture']['url'], array('width' => '50', 'alt' => __('Bild von %s', $this->RefereeFormat->formatPerson($person['Person'], 'fullname')), 'title' => h($this->RefereeFormat->formatPerson($person['Person'], 'fullname')))), $person['Picture']['url'], array('escape' => false));
							echo(getTD($columns[$curcol++], '', $tmpValue));

							$tmpValue = ($person['Person']['name'] === null) ? '' : $this->RefereeFormat->formatPerson($person['Person'], 'name_title');
							echo(getTD($columns[$curcol++], '', $tmpValue));

							$tmpValue = ($person['Person']['first_name'] === null) ? '' : $this->RefereeFormat->formatPerson($person['Person'], 'first_name');
							echo(getTD($columns[$curcol++], '', $tmpValue));

							debug(Person::getEmails($person));

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


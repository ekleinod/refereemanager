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
				}

				if ($isReferee) {
					$columns[] = __('URL');
				}

				if ($isEditor) {
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

							$tmpValue = (empty($person['Picture']['url'])) ? '' :
									$this->Html->link($this->Html->image($person['Picture']['url'], array('width' => '50', 'alt' => __('Bild von %s', $this->RefereeFormat->formatPerson($person['Person'], 'fullname')), 'title' => h($this->RefereeFormat->formatPerson($person['Person'], 'fullname')))), $person['Picture']['url'], array('escape' => false));
							echo(getTD($columns[$curcol++], '', $tmpValue));

							$tmpValue = (empty($person['Person']['name'])) ? '' : $this->RefereeFormat->formatPerson($person['Person'], 'name_title');
							echo(getTD($columns[$curcol++], '', $tmpValue));

							$tmpValue = (empty($person['Person']['first_name'])) ? '' : $this->RefereeFormat->formatPerson($person['Person'], 'first_name');
							echo(getTD($columns[$curcol++], '', $tmpValue));

							$tmpValue = $this->RefereeFormat->formatContacts(Person::getContacts($person, 'Email'), 'link', 'Email', 'html');
							echo(getTD($columns[$curcol++], '', $tmpValue));

							$tmpValue = $this->RefereeFormat->formatContacts(Person::getContacts($person, 'PhoneNumber'), 'national', 'PhoneNumber', 'html');
							echo(getTD($columns[$curcol++], '', $tmpValue));

							$tmpValue = $this->RefereeFormat->formatContacts(Person::getContacts($person, 'Address'), 'fulladdress', 'Address', 'html');
							echo(getTD($columns[$curcol++], '', $tmpValue));

							$tmpValue = $this->RefereeFormat->formatContacts(Person::getContacts($person, 'Url'), 'link', 'Url', 'html');
							echo(getTD($columns[$curcol++], '', $tmpValue));

							$tmpValue = h($person['SexType']['title']);
							echo(getTD($columns[$curcol++], '', $tmpValue));

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


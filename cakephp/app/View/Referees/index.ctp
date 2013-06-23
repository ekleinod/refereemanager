<?php echo $this->element('actions_header');	?>
<!-- header actions -->

<!-- view filters -->
<div class="filter">
</div>

<!-- view content -->
<?php
	if (empty($referees)) {
?>
	<p><?php echo __('Es sind keine Schiedsrichter gespeichert.'); ?></p>
<?php
	} else {
?>
	<p><?php echo __('Legende:'); ?></p>
	<ul class="legend">
		<?php
			// compute different styles
			foreach ($statustypes as &$statustype) {

				$style = '';

				if ($statustype['style'] || $statustype['color'] || $statustype['bgcolor']) {

					switch ($statustype['style']) {
						case 'normal':
						case 'italic':
						case 'oblique':
							$style .= sprintf('font-style: %s; ', $statustype['style']);
							break;
						case 'normal':
						case 'bold':
						case 'bolder':
						case 'lighter':
							$style .= sprintf('font-weight: %s; ', $statustype['style']);
							break;
					}

					if ($statustype['color']) {
						$style .= sprintf('color: #%s; ', $statustype['color']);
					}

					if ($statustype['bgcolor']) {
						$style .= sprintf('background-color: #%s; ', $statustype['bgcolor']);
					}

				}
				$statustype['outputstyle'] = $style;
		?>
			<li style="<?php echo $statustype['outputstyle']; ?>"><?php echo ($statustype['remark']) ? $statustype['remark'] : $statustype['title']; ?></li>
		<?php
			}
		?>
	</ul>

	<p><?php echo $this->Html->link('Export to Excel', array('controller' => 'referees', 'action' => 'export', 'excel')); ?></p>

	<table>
		<?php
			$columns = array();
				if ($isReferee) {
					$columns[] = __('Bild');
				}
				$columns[] = __('Vorname');
				$columns[] = __('Name');
				$columns[] = __('Club');
				if ($isReferee) {
					$columns[] = __('E-Mail');
					$columns[] = __('Telefon');
				}
				if ($isEditor) {
					$columns[] = __('Adresse');
					$columns[] = __('Geschlecht');
					$columns[] = __('Geburtstag');
					$columns[] = __('Ausbildung (seit)');
					$columns[] = __('Letzte Fortbildung');
					$columns[] = __('Nächste Fortbildung');
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
				foreach ($referees as $referee) {
			?>
					<tr>
						<?php if ($isReferee) { ?>
							<td data-title="<?php echo __('Bild'); ?>" style="<?php echo $statustypes[$referee['StatusType']['id']]['outputstyle']; ?>"><?php
								if (!empty($referee['Picture'])) {
									echo $this->Html->image($referee['Picture']['url'], array('width' => '50', 'alt' => __('Bild von %s %s', $referee['Person']['first_name'], $referee['Person']['name']), 'title' => __('%s %s', $referee['Person']['first_name'], $referee['Person']['name'])));
								}
							?></td>
						<?php } ?>

						<td data-title="<?php echo __('Vorname'); ?>" style="<?php echo $statustypes[$referee['StatusType']['id']]['outputstyle']; ?>"><?php echo $this->Html->link($referee['Person']['first_name'], array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id']), array('style' => $statustypes[$referee['StatusType']['id']]['outputstyle'])); ?></td>

						<td data-title="<?php echo __('Name'); ?>" style="<?php echo $statustypes[$referee['StatusType']['id']]['outputstyle']; ?>"><?php echo $this->Html->link($referee['Person']['name'], array('controller' => 'referees', 'action' => 'view', $referee['Referee']['id']), array('style' => $statustypes[$referee['StatusType']['id']]['outputstyle']));  ?></td>

						<td data-title="<?php echo __('Club'); ?>" style="<?php echo $statustypes[$referee['StatusType']['id']]['outputstyle']; ?>"><?php
							if (!empty($referee['Club'])) {
								echo $this->Html->link($referee['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $referee['Club']['id']), array('style' => $statustypes[$referee['StatusType']['id']]['outputstyle']));
							}
						?></td>

						<?php if ($isReferee) { ?>
							<td data-title="<?php echo __('E-Mail'); ?>" style="<?php echo $statustypes[$referee['StatusType']['id']]['outputstyle']; ?>"><?php
								if (array_key_exists('Contact', $referee) && array_key_exists('Email', $referee['Contact'])) {
									$hasMore = false;
									$printType = (count($referee['Contact']['Email']) > 1);
									foreach ($referee['Contact']['Email'] as $contacttype => $emailkind) {
										$printType |= (count($emailkind) > 1);
										foreach ($emailkind as $email) {
											if ($hasMore) {
												echo '<br />';
											}
											if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
												echo __('%s: ', $contacttypes[$contacttype]['short']);
											}
											echo $this->Html->link($email['email'], __('mailto:%s', $email['email']), array('style' => $statustypes[$referee['StatusType']['id']]['outputstyle']));
											$hasMore = true;
										}
									}
								}
							?></td>
							<td data-title="<?php echo __('Telefon'); ?>" style="<?php echo $statustypes[$referee['StatusType']['id']]['outputstyle']; ?>"><?php
								if (array_key_exists('Contact', $referee) && array_key_exists('PhoneNumber', $referee['Contact'])) {
									$hasMore = false;
									$printType = (count($referee['Contact']['PhoneNumber']) > 1);
									foreach ($referee['Contact']['PhoneNumber'] as $contacttype => $phonekind) {
										$printType |= (count($phonekind) > 1);
										foreach ($phonekind as $phone) {
											if ($hasMore) {
												echo '<br />';
											}
											if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
												echo __('%s: ', $contacttypes[$contacttype]['short']);
											}
											if (($phone['country_code'] != '') && ($phone['country_code'] != Configure::read('RefMan.defaultcountrycode'))) {
												echo __('+%s %s ', $phone['country_code'], ($phone['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $phone['area_code']);
											} else {
												echo __('0%s ', ($phone['area_code'] === '') ? Configure::read('RefMan.defaultareacode') : $phone['area_code']);
											}
											echo $phone['number'];
											$hasMore = true;
										}
									}
								}
							?></td>
						<?php } ?>

						<?php if ($isEditor) { ?>
							<td><?php echo __('Adresse'); ?></td>
							<td><?php echo __('Geschlecht'); ?></td>
							<td><?php echo __('Geburtstag'); ?></td>
							<td><?php echo __('Ausbildung (seit)'); ?></td>
							<td><?php echo __('Letzte Fortbildung'); ?></td>
							<td><?php echo __('Nächste Fortbildung'); ?></td>
						<?php } ?>

						<td data-title="<?php echo __('Anmerkung'); ?>" style="<?php echo $statustypes[$referee['StatusType']['id']]['outputstyle']; ?>"><?php
							if (!empty($referee['Person']['remark'])) {
								echo h($referee['Person']['remark']);
							}
						?></td>

						<td class="actions" data-title="<?php echo __('Aktionen'); ?>">
							<?php echo $this->element('actions_table', array('id' => $referee['Referee']['id']));	?>
						</td>
					</tr>
			<?php } ?>
		</tbody>
	</table>
<?php
	}
?>

<?php pr($referees); ?>


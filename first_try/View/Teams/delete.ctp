<div class="teams delete">
	<h2><?php  echo __('Team löschen'); ?></h2>

	<?php if ($isAdmin) { ?>
			<p class="message"><?php echo __("Möchten Sie wirklich nachfolgendes Team löschen?"); ?></p>
			<p><?php echo __("Bitte beachten Sie, dass folgende Daten unwiderruflich gelöscht werden:"); ?></p>
			<ul>
				<li><?php echo __("das Team selbst"); ?></li>
				<li><?php echo __("der Spielort des Teams, sofern dieser nicht anderweitig verwendet wird"); ?></li>
				<li><?php echo __("alle Spiele, an denen das Team beteiligt war"); ?></li>
				<li><?php echo __("alle Änderungen, die zum Team gespeichert wurden"); ?></li>
			</ul>

			<p><?php echo __("Wenn das Team lediglich zurückgezogen wurde o.ä. ist es evtl. besser, den Status des Teams zu ändern."); ?></p>

			<div class="actions">
				<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $team['Team']['id'])); ?>
				<?php echo $this->Form->postLink(__('Team löschen'), array('action' => 'delete', $team['Team']['id'])); ?>
			</div>

			<h3><?php echo __("Das Team"); ?></h3>
			<?php echo $this->element('Teams/view'); ?>

			<h3><?php echo __('Spiele dieses Teams'); ?></h3>
			<?php if (empty($team['Assignment'])) { ?>
				<p><?php echo __('Dieses Team ist noch für keine Spiele eingetragen.'); ?></p>
			<?php
				} else {
					echo $this->element('Assignments/table_small', array('assignments' => $team['Assignment']));
				}
			?>

			<?php echo $this->element('Changes/table_small', array('changes' => $changes));	?>
	<?php } else { ?>
		<p class="message"><?php echo __("Sie besitzen nicht genügend Rechte, um das Team zu löschen."); ?></p>
	<?php }?>

</div>

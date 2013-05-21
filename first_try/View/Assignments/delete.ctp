<div class="assignments delete">
	<h2><?php  echo __('Schiedsrichtereinsatz löschen'); ?></h2>

	<?php if ($isAdmin) { ?>
			<p class="message"><?php echo __("Möchten Sie wirklich nachfolgenden Schiedsrichtereinsatz löschen?"); ?></p>
			<p><?php echo __("Bitte beachten Sie, dass folgende Daten unwiderruflich gelöscht werden:"); ?></p>
			<ul>
				<li><?php echo __("der Schiedsrichtereinsatz selbst"); ?></li>
				<li><?php echo __("alle Änderungen, die zum Schiedsrichtereinsatz gespeichert wurden"); ?></li>
			</ul>

			<div class="actions">
				<?php echo $this->Form->postLink(__('Schiedsrichtereinsatz löschen'), array('action' => 'delete', $assignment['Assignment']['id'])); ?>
			</div>

			<h3><?php echo __("Der Schiedsrichtereinsatz"); ?></h3>
			<?php echo $this->element('Assignments/view'); ?>

			<?php echo $this->element('Changes/table_small', array('changes' => $changes));	?>
	<?php } else { ?>
		<p class="message"><?php echo __("Sie besitzen nicht genügend Rechte, um den Schiedsrichtereinsatz zu löschen."); ?></p>
	<?php }?>

</div>

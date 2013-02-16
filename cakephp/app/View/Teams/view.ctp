<!-- header actions -->
<?php echo $this->element('actions_header', array('id' => $team['Team']['id']));	?>

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


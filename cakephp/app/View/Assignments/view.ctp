<div class="assignments view">
	<h2><?php  echo __('Schiedsrichtereinsatz'); ?></h2>

	<?php echo $this->element('assignments_view'); ?>

	<?php echo $this->element('actions_h3', array('id' => $assignment['Assignment']['id']));	?>

	<?php echo $this->element('changes_table_small', array('changes' => $changes));	?>

	<?php pr($changes); ?>

</div>

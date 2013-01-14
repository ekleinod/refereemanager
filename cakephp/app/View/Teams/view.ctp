<div class="teams view">
	<h2><?php  echo __('Team "%s"', $team['Team']['title_team']); ?></h2>

	<?php echo $this->element('Teams/view'); ?>

	<?php echo $this->element('actions_h3', array('id' => $team['Team']['id']));	?>
</div>

<div class="related">
	<h3><?php echo __('Spiele dieses Teams'); ?></h3>
	<?php if (empty($team['Assignment'])) { ?>
		<p><?php echo __('Dieses Team ist noch für keine Spiele eingetragen.'); ?></p>
	<?php
		} else {
			echo $this->element('Assignments/table_small', array('assignments' => $team['Assignment']));
		}
	?>

	<?php echo $this->element('Changes/table_small', array('changes' => $changes));	?>
</div>


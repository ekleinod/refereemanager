<!-- main navigation for big devices -->
<?php
	if (isset($season)) {
?>
	<div class="filter">
		<ol class="currentfilter">
			<?php if (isset($season)) { ?>
				<li><label><?php echo __('Saison'); ?></label><?php echo $season['Season']['title_season']; ?></li>
			<?php } ?>
		</ol>
		<?php echo $this->Form->create('Filter'); ?>
			<fieldset>
				<legend><?php echo __('Filter'); ?></legend>
				<ol>
					<?php
						if (isset($season)) {
							echo $this->RefereeForm->getInputField(null, 'select', 'season', __('Saison'), $season['Season']['id'], false, null, 0, true, $seasonarray);
						}
					?>
				</ol>
				<?php echo $this->Form->button(__('Filtern'), array('type' => 'submit')); ?>
			</fieldset>
		<?php echo $this->Form->end(); ?>
	</div>
<?php
	}
?>


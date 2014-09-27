<!-- main navigation for big devices -->
<div class="filter">
	<ol class="currentfilter">
		<li><label><?php echo __('Saison'); ?></label><?php echo $season['title_season']; ?></li>
	</ol>
	<?php echo $this->Form->create('Filter'); ?>
		<fieldset>
			<legend><?php echo __('Filter'); ?></legend>
			<ol>
				<?php echo $this->RefereeForm->getInputField(null, 'select', 'season', __('Saison'), $season['id'], false, null, 0, true, $seasonarray); ?>
			</ol>
			<?php echo $this->Form->button(__('Filtern'), array('type' => 'submit')); ?>
		</fieldset>
	<?php echo $this->Form->end(); ?>
</div>


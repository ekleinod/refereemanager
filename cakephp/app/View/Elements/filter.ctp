<!-- main navigation for big devices -->
<div class="filter">
	<dl>
		<dt><?php echo __('Saison'); ?></dt>
		<dd><?php echo $season['title_season']; ?></dd>
	</dl>
	<?php echo $this->Form->create('Filter'); ?>
		<fieldset>
			<legend><?php echo __('Filter'); ?></legend>
			<ol>
				<?php echo $this->RefereeForm->getInputField(null, 'select', 'season', __('Saison'), $season['id'], true, null, 0, true, $seasonarray); ?>
			</ol>
			<?php echo $this->Form->button(__('Filtern'), array('type' => 'submit')); ?>
		</fieldset>
	<?php echo $this->Form->end(); ?>
</div>


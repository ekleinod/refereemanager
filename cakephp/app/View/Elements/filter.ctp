<?php
	if (isset($season)) {
?>
		<!-- filter form -->
			<div class="row">
				<div class="col-sm-12">
					<?php echo $this->Form->create('Filter', array('class' => 'form-horizontal')); ?>
						<fieldset>
							<legend><?php echo __('Filter'); ?></legend>

							<?php
								if (isset($season)) {
									echo $this->RefereeForm->getInputField(null, 'select', 'season',
																												 __('Saison'), $season['Season']['id'], sprintf('aktiv: %s', $season['Season']['title_season']), null,
																												 false, true, 0,
																												 $seasonarray);
								}
							?>

						</fieldset>
						<div class="form-group">
							<div class="col-sm-12">
								<?php echo $this->Form->button(__('Filtern'), array('type' => 'submit', 'class' => 'btn btn-sm btn-default')); ?>
							</div>
						</div>
					<?php echo $this->Form->end(); ?>
				</div>
			</div>
		<!-- /filter form -->
<?php
	}
?>


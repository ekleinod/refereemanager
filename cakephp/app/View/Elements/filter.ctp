<?php
	if (isset($season)) {
?>
		<!-- filter form -->
			<div class="row">
				<div class="col-sm-12">
					<?php echo $this->Form->create('Filter', array('class' => 'form-horizontal')); ?>
						<fieldset>
							<legend><?php echo __('Filter'); ?></legend>
							<div class="form-group">
								<label for="FilterSeason" class="col-sm-2 control-label">Saison</label>
								<div class="col-sm-10">
									<select class="form-control" name="data[Filter][season]" title="Saison" autofocus="autofocus" id="FilterSeason">
										<option value="3">VSR-Lehrgang</option>
										<option value="4">2015-2016</option>
										<option value="2" selected="">2014-2015</option>
										<option value="1">2013-2014</option>
									</select>
									<span class="help-block">aktiv: 2014-2015</span>
								</div>
							</div>
							<?php
								if (isset($season)) {
									echo $this->RefereeForm->getInputField(null, 'select', 'season', __('Saison'), $season['Season']['id'], false, null, 0, true, $seasonarray);
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


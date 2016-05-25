<div class="row">
	<div class="col-sm-12">
		<div class="alert alert-<?php echo $params['class']; ?> alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			<?php
				echo empty($params['nohtml']) ? h($message) : $message;
			?>
		</div>
	</div>
</div>


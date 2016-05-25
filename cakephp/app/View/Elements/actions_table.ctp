<!-- actions -->
<div class="btn-group btn-group-xs">
	<?php
		echo $this->Html->link(
													 __('Ansehen'),
													 array('action' => 'view', $id),
													 array(
																 'class' => 'btn btn-success',
																 'role' => 'button',
																 )
													 );
		if ($isEditor) {
			echo $this->Html->link(
														 __('Ändern'),
														 array('action' => 'edit', $id),
														 array(
																	 'class' => 'btn btn-warning',
																	 'role' => 'button',
																	 )
														 );
			echo $this->Html->link(
														 __('Löschen'),
														 array('action' => 'delete', $id),
														 array(
																	 'class' => 'btn btn-danger',
																	 'role' => 'button',
																	 )
														 );
		}
	?>
</div>
<!-- /actions -->


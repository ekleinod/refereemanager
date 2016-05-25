<!-- actions -->
	<div class="row">
		<div class="col-sm-12">
			<div class="btn-group btn-group-sm">

				<?php
					echo $this->Html->link(
																 __('Übersicht'),
																 array('action' => 'index'),
																 array(
																			 'class' => 'btn btn-success',
																			 'role' => 'button',
																			 )
																 );

					if ($isEditor) {
						echo $this->Html->link(
																	 __('Neu'),
																	 array('action' => 'add'),
																	 array(
																				 'class' => 'btn btn-warning',
																				 'role' => 'button',
																				 )
																	 );

						if (isset($id)) {
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

					}

				?>

			</div>
		</div>
	</div>
<!-- /actions -->


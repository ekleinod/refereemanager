<?php if ($isEditor) { ?>

	<div class="actions">
		<?php echo $this->Html->link(__('Neu'), array('action' => 'add'), array('class' => 'button-link access-editor')); ?>

		<?php if (isset($id)) { ?>
			<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $id), array('class' => 'button-link access-editor')); ?>

			<?php if ($isAdmin) { ?>
				<?php echo $this->Html->link(__('Löschen'), array('action' => 'delete', $id), array('class' => 'button-link access-admin')); ?>
			<?php } ?>
		<?php } ?>

	</div>

<?php } ?>


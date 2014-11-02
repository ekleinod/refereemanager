<?php if ($isEditor) { ?>

	<div class="actions">
		<?php echo $this->Html->link(__('Neu'), array('action' => 'add'), array('class' => 'button-link access-editor')); ?>

		<?php if (isset($id)) { ?>
			<?php echo $this->Html->link(__('Ansehen'), array('action' => 'view', $id), array('class' => 'button-link access-editor')); ?>
			<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $id), array('class' => 'button-link access-editor')); ?>
			<?php echo $this->Html->link(__('Löschen'), array('action' => 'delete', $id), array('class' => 'button-link access-admin')); ?>
		<?php } ?>

		<?php echo $this->Html->link(__('Übersicht'), array('action' => 'index'), array('class' => 'button-link access-editor')); ?>

	</div>

<?php } ?>


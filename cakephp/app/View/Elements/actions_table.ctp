<?php echo $this->Html->link(__('Ansehen'), array('action' => 'view', $id), array('class' => 'button-link access-anonymous')); ?>
<?php if ($isEditor) { ?>
	<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $id), array('class' => 'button-link access-editor')); ?>
<?php } ?>
<?php if ($isAdmin) { ?>
	<?php echo $this->Html->link(__('Löschen'), array('action' => 'delete', $id), array('class' => 'button-link access-admin')); ?>
<?php } ?>


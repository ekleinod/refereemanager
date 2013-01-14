<?php echo $this->Html->link(__('Ansehen'), array('action' => 'view', $id)); ?>
<?php if ($isEditor) { ?>
	<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $id)); ?>
<?php } ?>
<?php if ($isAdmin) { ?>
	<?php echo $this->Html->link(__('Löschen'), array('action' => 'delete', $id)); ?>
<?php } ?>


<?php echo $this->Html->link(__('Ansehen'), array('action' => 'view', $id), array('class' => 'button-link')); ?>
<?php if ($isEditor) { ?>
	<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $id), array('class' => 'button-link')); ?>
<?php } ?>
<?php if ($isAdmin) { ?>
	<?php echo $this->Html->link(__('Löschen'), array('action' => 'delete', $id), array('class' => 'button-link')); ?>
<?php } ?>


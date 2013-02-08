<?php if ($isEditor) { ?>
	<h3><?php  echo __('Aktionen'); ?></h3>
	<p class="actions">
		<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $id), array('class' => 'button-link')); ?>
		<?php if ($isAdmin) { ?>
			<?php echo $this->Html->link(__('Löschen'), array('action' => 'delete', $id), array('class' => 'button-link')); ?>
		<?php } ?>
	</p>
<?php } ?>


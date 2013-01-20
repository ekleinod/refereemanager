<ul>
	<li><?php echo $this->Html->link(__('Schiedsrichtereinsätze'), array('action' => 'index', 'controller' => 'assignments')); ?>
		<ul>
			<li><?php echo $this->Html->link(__('Ansehen'), array('action' => 'index', 'controller' => 'assignments')); ?></li>
			<li><?php echo $this->Html->link(__('Suchen'), array('action' => 'search', 'controller' => 'assignments')); ?></li>
			<li><?php echo $this->Html->link(__('Hinzufügen'), array('action' => 'add', 'controller' => 'assignments')); ?></li>
		</ul>
	</li>
	<li><?php echo $this->Html->link(__('Teams'), array('action' => 'index', 'controller' => 'teams')); ?>
		<ul>
			<li><?php echo $this->Html->link(__('Ansehen'), array('action' => 'index', 'controller' => 'teams')); ?></li>
			<li><?php echo $this->Html->link(__('Suchen'), array('action' => 'search', 'controller' => 'teams')); ?></li>
			<li><?php echo $this->Html->link(__('Hinzufügen'), array('action' => 'add', 'controller' => 'teams')); ?></li>
		</ul>
	</li>
</ul>
<select onchange="if (this.value) window.location.href=this.value">
	<option selected="selected" value="">Go to...</option>
	<option value="<?php echo Router::url(array('action' => 'index', 'controller' => 'assignments')); ?>"><?php echo __('Schiedsrichtereinsätze'); ?></option>
	<option value="<?php echo Router::url(array('action' => 'index', 'controller' => 'assignments')); ?>"><?php echo __('- Ansehen'); ?></option>
	<option value="<?php echo Router::url(array('action' => 'search', 'controller' => 'assignments')); ?>"><?php echo __('- Suchen'); ?></option>
	<option value="<?php echo Router::url(array('action' => 'add', 'controller' => 'assignments')); ?>"><?php echo __('- Hinzufügen'); ?></option>
	<option value="<?php echo Router::url(array('action' => 'index', 'controller' => 'teams')); ?>"><?php echo __('Teams'); ?></option>
	<option value="<?php echo Router::url(array('action' => 'index', 'controller' => 'teams')); ?>"><?php echo __('- Ansehen'); ?></option>
	<option value="<?php echo Router::url(array('action' => 'search', 'controller' => 'teams')); ?>"><?php echo __('- Suchen'); ?></option>
	<option value="<?php echo Router::url(array('action' => 'add', 'controller' => 'teams')); ?>"><?php echo __('- Hinzufügen'); ?></option>
</select>


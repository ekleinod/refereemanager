<?php echo $this->Session->flash('auth'); ?>

<?php echo $this->Form->create('User'); ?>
	<fieldset>
		<legend><?php echo __('Nutzername und Passwort'); ?></legend>
		<ol>
			<?php
				// username
				$label = $this->Form->label('User.username', __('Nutzername'), 'required');
				$input = $this->Form->text('User.username',
						array('type' => 'text',
									'placeholder' => __('Nutzername'), 'title' => __('Nutzername'),
									'maxlength' => '100',
									'autofocus' => 'autofocus',
									'required' => 'required')
				);
				echo $this->Html->tag('li',
						$label . $input,
						array('class' => 'input text required')
				);

				// password
				$label = $this->Form->label('User.password', __('Passwort'), 'required');
				$input = $this->Form->text('User.password',
						array('type' => 'password',
									'placeholder' => __('Passwort'), 'title' => __('Passwort'),
									'maxlength' => '100',
									'required' => 'required')
				);
				echo $this->Html->tag('li',
						$label . $input,
						array('class' => 'input password required')
				);
			?>
		</ol>
	</fieldset>
	<fieldset>
		<?php echo $this->Form->button(__('Einloggen'), array('type' => 'submit')); ?>
		<?php echo $this->Form->button(__('Inhalte zurücksetzen'), array('type' => 'reset')); ?>
	</fieldset>
<?php echo $this->Form->end(); ?>


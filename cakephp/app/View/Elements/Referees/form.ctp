<?php echo $this->Form->create('Referee'); ?>

	<fieldset>
		<legend><?php echo __('Die Person'); ?></legend>
		<ol>
			<?php
				// first name
				$label = $this->Form->label('Person.first_name', __('Vorname'));
				$input = $this->Form->text('Person.first_name',
						array('type' => 'text',
									'placeholder' => __('Vorname'), 'title' => __('Vorname'),
									'maxlength' => '100',
									'autofocus' => 'autofocus',
									'readonly' => 'readonly')
				);
				echo $this->Html->tag('li',
						$label . $input,
						array('class' => 'input text')
				);

			?>
		</ol>
	</fieldset>

<?php echo $this->Form->end(); ?>


<p><?php echo $action; ?></p>
<!--					$columns[] = __('Bild');
				}
				$columns[] = __('Vorname');
				$columns[] = __('Name');
				$columns[] = __('Club');
				if ($isReferee) {
					$columns[] = __('E-Mail');
					$columns[] = __('Telefon');
				}
				if ($isEditor) {
					$columns[] = __('Adresse');
					$columns[] = __('Geschlecht');
					$columns[] = __('Geburtstag');
					$columns[] = __('Ausbildung (seit)');
					$columns[] = __('Letzte Fortbildung');
					$columns[] = __('Nächste Fortbildung');
				}
				$columns[] = __('Anmerkung');-->


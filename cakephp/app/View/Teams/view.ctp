<div class="teams view">
	<h2><?php  echo __('Team'); ?></h2>

	<dl>
		<dt><?php echo __('Nummer'); ?></dt>
			<dd><?php echo h($team['Team']['number']); ?></dd>
		<dt><?php echo __('Name'); ?></dt>
			<dd>
				<?php if (empty($team['Team']['name'])) { ?>
					&nbsp;
				<?php } else { ?>
					<?php echo h($team['Team']['name']); ?>
				<?php } ?>
			</dd>
		<dt><?php echo __('Beschreibung'); ?></dt>
			<dd>
				<?php if (empty($team['Team']['description'])) { ?>
					&nbsp;
				<?php } else { ?>
					<?php echo h($team['Team']['description']); ?>
				<?php } ?>
			</dd>
		<dt><?php echo __('Club'); ?></dt>
			<dd><?php echo $this->Html->link($team['Club']['name'], array('controller' => 'clubs', 'action' => 'view', $team['Club']['id'])); ?></dd>
		<dt><?php echo __('Liga'); ?></dt>
			<dd>
				<?php if (empty($team['League'])) { ?>
					&nbsp;
				<?php } else { ?>
					<?php echo $this->Html->link($team['League'][0]['title'], array('controller' => 'leagues', 'action' => 'view', $team['League'][0]['id'])); ?>
				<?php } ?>
			</dd>
		<dt><?php echo __('Saison'); ?></dt>
			<dd>
				<?php if (empty($team['Season'])) { ?>
					&nbsp;
				<?php } else { ?>
					<?php echo $this->Html->link($team['Season'][0]['title_season'], array('controller' => 'seasons', 'action' => 'view', $team['Season'][0]['id'])); ?>
				<?php } ?>
			</dd>
		<dt><?php echo __('Ansprechpartner'); ?></dt>
			<dd>
				<?php if (empty($team['Person'])) { ?>
					&nbsp;
				<?php } else { ?>
					<?php echo $this->Html->link($team['Person'][0]['title_person'], array('controller' => 'people', 'action' => 'view', $team['Person'][0]['id'])); ?>
				<?php } ?>
			</dd>
		<dt><?php echo __('Spielort'); ?></dt>
			<dd>
				<?php if (empty($team['Address']['title_address'])) { ?>
					&nbsp;
				<?php } else { ?>
					<?php echo $this->Html->link($team['Address']['title_address'], array('controller' => 'addresses', 'action' => 'view', $team['Address']['id'])); ?>
				<?php } ?>
			</dd>
	</dl>

	<?php if (!$isEditor) { ?>
		<h3><?php  echo __('Aktionen für dieses Team'); ?></h3>
		<p class="actions">
			<?php echo $this->Html->link(__('Editieren'), array('action' => 'edit', $team['Team']['id'])); ?>
			<?php echo $this->Form->postLink(__('Löschen'), array('controller' => 'teams', 'action' => 'delete', $team['Team']['id']), null, __('Wollen Sie Team "%s" wirklich löschen?', $team['Team']['number'])); ?>
			<?php echo $this->Html->link(__('Neues Spiel'), array('controller' => 'assignments', 'action' => 'add')); ?>
		</p>
	<?php } ?>

</div>

<div class="related">
	<h3><?php echo __('Spiele dieses Teams'); ?></h3>

	<?php if (empty($team['Assignment'])) { ?>
		<p>Dieses Team ist noch für keine Spiele eingetragen.
	<?php
		} else {
			echo $this->element('assignments_table_small',
													array('assignments' => $team['Assignment']));
		}
	?>
</div>


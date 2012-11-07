<!DOCTYPE html>
<html lang="de">

	<head>

		<!-- technical declarations -->
		<?php echo $this->Html->charset(); ?>

		<!-- references -->
		<?php echo $this->Html->meta('icon'); ?>

		<?php echo $this->Html->css('cake.generic'); ?>

		<?php echo $this->fetch('meta'); ?>
		<?php
			$this->Html->css('refereemanager', null, array('inline' => false, 'media' => 'print'));
			echo $this->fetch('css');
		?>
		<?php echo $this->fetch('script'); ?>

		<!-- title -->
		<title><?php echo $title_for_layout; ?></title>

	</head>
	<body>
		<header>
			<h1><?php echo $title_for_layout; ?></h1>
		</header>

		<nav>
			<ul class="actions">
				<li><?php echo $this->Html->link(__('Referee Assignments'), array('action' => 'index', 'controller' => 'assignments'), array('class' => 'active')); ?></li>
				<li><?php echo $this->Html->link(__('Teams'), array('action' => 'index', 'controller' => 'teams'), array('class' => '')); ?></li>
			</ul>
		</nav>

		<article>
			<div id="flash"><?php echo $this->Session->flash(); ?></div>
			<div id="content">
				<?php echo $this->fetch('content'); ?>
			</div>
		</article>

		<footer>
			<?php echo $this->Html->link(__('Referee Homepage'), 'http://schiri.bettv.de/'); ?>
			<?php echo $this->element('sql_dump'); ?>
		</footer>
	</body>
</html>

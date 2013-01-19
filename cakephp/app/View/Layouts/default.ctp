<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="de"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="de"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="de"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="de"> <!--<![endif]-->

	<head>

		<?php
			// character encoding
			echo $this->Html->charset();

			// meta tags
			$this->Html->meta(array('name' => 'viewport', 'content' => 'width=device-width, initial-scale=1, maximum-scale=1'), null, array('inline' => false));
			echo $this->fetch('meta');

			// css files
			$this->Html->css('base', null, array('inline' => false));
			$this->Html->css('skeleton', null, array('inline' => false));
			$this->Html->css('layout', null, array('inline' => false));
			echo $this->fetch('css');

			// favicons
		?>

		<!-- title -->
		<title><?php echo $title_for_layout; ?></title>

	</head>
	<body>
		<header>
			<h1><?php echo $title_for_layout; ?></h1>
		</header>

		<nav>
			<ul class="actions">
				<li><?php echo $this->Html->link(__('Schiedsrichtereinsätze'), array('action' => 'index', 'controller' => 'assignments'), array('class' => 'active')); ?></li>
				<li><?php echo $this->Html->link(__('Teams'), array('action' => 'index', 'controller' => 'teams'), array('class' => '')); ?></li>
				<li><?php echo $this->Html->link(__('Login'), array('action' => 'login', 'controller' => 'users'), array('class' => '')); ?></li>
				<li><?php echo $this->Html->link(__('Logout'), array('action' => 'logout', 'controller' => 'users'), array('class' => '')); ?></li>
			</ul>
		</nav>

		<article>
			<div id="flash"><?php echo $this->Session->flash(); ?></div>
			<div id="content">
				<?php echo $this->fetch('content'); ?>
			</div>
		</article>

		<footer>
			<?php echo $this->Html->link(__('Schiedsrichter-Homepage'), 'http://schiri.bettv.de/'); ?>
			<?php echo $this->element('sql_dump'); ?>
		</footer>

		<?php
			// load scripts at end of page in order to improve page load speed
			echo $this->fetch('script');
		?>

	</body>
</html>

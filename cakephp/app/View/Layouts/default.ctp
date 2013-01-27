<?php echo $this->Html->docType('html5'); ?>
<?php
	$attLang = Configure::read('Config.language');
	if (strpos($attLang, "-")) {
		$attLang = substr($attLang, 0, strpos($attLang, "-"));
	}
?>
<!--[if lt IE 7 ]><html class="ie ie6" lang="<?php echo $attLang; ?>"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="<?php echo $attLang; ?>"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="<?php echo $attLang; ?>"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="<?php echo $attLang; ?>"> <!--<![endif]-->

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
		?>

		<!-- favicons by hand, maybe later using cakephp methods -->
		<link rel="shortcut icon" href="img/favicon.ico" />
		<link rel="apple-touch-icon" href="img/apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="72x72" href="img/apple-touch-icon-72x72.png" />
		<link rel="apple-touch-icon" sizes="114x114" href="img/apple-touch-icon-114x114.png" />

		<!-- title -->
		<title><?php echo $title_for_layout; ?></title>

	</head>
	<body>

		<div class="page">

			<div class="band header">

				<header class="container main">

					<div class="sixteen columns">

						<header>
							<h1><?php echo $this->Html->link(__('Schiedsrichterverwaltung'), array('controller' => 'pages', 'action' => 'display', 'home')); ?></a></h1>
						</header>

					</div><!-- end sixteen -->

				</header><!-- end container -->

			</div><!-- end band header -->

			<div class="band navigation">

				<nav class="container primary">

					<div class="sixteen columns">

						<?php
						$navarray = array(
															array('title' => __('Schiedsrichtereinsätze'), 'routing' => array('action' => 'index', 'controller' => 'assignments'),
																		'subnav' => array(
																											array('title' => __('Ansehen'), 'routing' => array('action' => 'index', 'controller' => 'assignments')),
																											array('title' => __('Suchen'), 'routing' => array('action' => 'search', 'controller' => 'assignments')),
																											array('title' => __('Hinzufügen'), 'routing' => array('action' => 'add', 'controller' => 'assignments')),
																											)
																		),
															array('title' => __('Teams'), 'routing' => array('action' => 'index', 'controller' => 'teams'),
																		'subnav' => array(
																											array('title' => __('Ansehen'), 'routing' => array('action' => 'index', 'controller' => 'teams')),
																											array('title' => __('Suchen'), 'routing' => array('action' => 'search', 'controller' => 'teams')),
																											array('title' => __('Hinzufügen'), 'routing' => array('action' => 'add', 'controller' => 'teams')),
																											)
																		),
															);
							echo $this->element('navigation', array('navarray' => $navarray));
						?>

					</div><!-- end sixteen -->

				</nav><!-- end container -->

			</div><!-- end band navigation -->

			<div class="band content">

				<div class="container">

					<div class="sixteen columns">

						<article>
							<div id="flash"><?php echo $this->Session->flash(); ?></div>
							<div id="content">
								<?php echo $this->fetch('content'); ?>
							</div>
						</article>

					</div><!-- end sixteen -->

				</div><!-- end container -->

			</div><!-- end band content -->

			<div class="band footer">

				<footer class="container main">

					<div class="four columns">

						<?php echo $this->Html->link(__('Schiedsrichter-Homepage'), 'http://schiri.bettv.de/'); ?>

					</div><!-- end four -->

					<div class="four columns">

						<?php echo $this->Html->link(__('Login'), array('action' => 'login', 'controller' => 'users'), array('class' => '')); ?>
						<?php echo $this->Html->link(__('Logout'), array('action' => 'logout', 'controller' => 'users'), array('class' => '')); ?>

					</div><!-- end four -->

				</div><!-- end container -->

			</div><!-- end band footer -->

			<div class="band sqldump">

				<footer class="container">

					<div class="sixteen columns">

						<?php echo $this->element('sql_dump'); ?>

					</div><!-- end sixteen -->

				</div><!-- end container -->

			</div><!-- end band footer -->

		</div><!-- end page -->

		<?php
			// load scripts at end of page in order to improve page load speed
			echo $this->fetch('script');
		?>

	</body>
</html>

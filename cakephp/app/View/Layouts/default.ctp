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
			$this->Html->meta(array('name' => 'description', 'content' => $this->fetch('title')), null, array('inline' => false));
			$this->Html->meta(array('name' => 'author', 'content' => 'Ekkart Kleinod'), null, array('inline' => false));
			echo $this->fetch('meta');

			// css files
			$this->Html->css('bootstrap.min', null, array('inline' => false));
			$this->Html->css('bootstrap-theme.min', null, array('inline' => false));
			$this->Html->css('refman', null, array('inline' => false));
			echo $this->fetch('css');
		?>

		<!-- favicons by hand, maybe later using cakephp methods -->
		<link rel="shortcut icon" href="img/favicon.ico" />
		<link rel="apple-touch-icon" href="img/apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="72x72" href="img/apple-touch-icon-72x72.png" />
		<link rel="apple-touch-icon" sizes="114x114" href="img/apple-touch-icon-114x114.png" />

		<!-- title -->
		<title><?php echo $this->fetch('title'); ?></title>

	</head>
	<body>

		<!-- navigation -->
			<nav class="navbar navbar-default navbar-fixed-top">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<?php echo $this->Html->link(__('Schiedsrichterverwaltung'), array('controller' => 'pages', 'action' => 'display', 'home'), array('class' => 'navbar-brand')); ?>
					</div>
					<div id="navbar" class="collapse navbar-collapse">
						<?php
						$navarray = array(
															array('title' => __('Schiedsrichtereinsätze'),
																		'routing' => array('action' => 'index', 'controller' => 'assignments')
																		),
															array('title' => __('Schiedsrichter_innen'),
																		'routing' => array('action' => 'index', 'controller' => 'referees')
																		),
															array('title' => __('Sonstiges'),
																		'routing' => array('action' => 'index', 'controller' => 'other'),
																		'subnav' => array(
																											array('title' => __('Clubs'), 'routing' => array('action' => 'index', 'controller' => 'clubs')),
																											array('title' => __('Liga-Typen'), 'routing' => array('action' => 'index', 'controller' => 'league_types')),
																											array('title' => __('Ligen'), 'routing' => array('action' => 'index', 'controller' => 'leagues')),
																											array('divider' => 'divider'),
																											array('title' => __('Personen'), 'role' => 'referee', 'routing' => array('action' => 'index', 'controller' => 'people')),
																											array('title' => __('Saisons'), 'routing' => array('action' => 'index', 'controller' => 'seasons')),
																											array('title' => __('Teams'), 'routing' => array('action' => 'index', 'controller' => 'teams')),
																											)
																		),
															array('title' => __('Editor-Werkzeuge'),
																		'role' => 'editor',
																		'routing' => array('action' => 'index', 'controller' => 'tools_editor'),
																		'subnav' => array(
																											array('title' => __('Mailverteiler'), 'routing' => array('action' => 'mailinglist', 'controller' => 'tools_editor')),
																											array('title' => __('Nachricht'), 'routing' => array('action' => 'message', 'controller' => 'tools_editor')),
																											)
																		),
															);
							echo $this->element('navigation', array('navarray' => $navarray));
						?>
					</div><!--/.nav-collapse -->
				</div>
			</nav>

		<div class="page">

			<div class="band header">

				<header class="container main">

					<div class="sixteen columns">

						<h1><?php echo $this->Html->link(__('Schiedsrichterverwaltung'), array('controller' => 'pages', 'action' => 'display', 'home')); ?></h1>

					</div><!-- end sixteen -->

				</header><!-- end container -->

			</div><!-- end band header -->

			<?php if (isset($username)) { ?>
				<div class="band username">
					<div class="container">
						<div class="sixteen columns">
							<p>
								Eingeloggt als: "<?php echo $username; ?>"
								(<?php echo $this->Html->link(__('Logout'), array('action' => 'logout', 'controller' => 'users'), array('class' => '')); ?>)
							</p>
						</div><!-- end sixteen -->
					</div><!-- end container -->
				</div><!-- end band content -->
			<?php } ?>

			<div class="band content">

				<div class="container">

					<div class="sixteen columns">

						<article>
							<div id="flash"><?php echo $this->Session->flash(); ?></div>
							<div id="content">
								<h2><?php echo $this->fetch('title'); ?></h2>
								<?php echo $this->fetch('content'); ?>
							</div>
						</article>

					</div><!-- end sixteen -->

				</div><!-- end container -->

			</div><!-- end band content -->

			<div class="band footer">

				<footer class="container">

					<div class="four columns">

						<ul>
							<li><?php echo $this->Html->link(__('Schiedsrichter-Homepage'), 'http://schiri.bettv.de/'); ?></li>
						</ul>

					</div><!-- end four -->

					<div class="four columns">

						<ul>
							<li><?php echo $this->Html->link(__('Login'), array('action' => 'login', 'controller' => 'users'), array('class' => '')); ?></li>
							<li><?php echo $this->Html->link(__('Logout'), array('action' => 'logout', 'controller' => 'users'), array('class' => '')); ?></li>
						</ul>

					</div><!-- end four -->

				</footer><!-- end container -->

			</div><!-- end band footer -->

			<div class="band sqldump">

				<footer class="container">

					<div class="sixteen columns">

						<?php echo $this->element('sql_dump'); ?>

					</div><!-- end sixteen -->

				</footer><!-- end container -->

			</div><!-- end band footer -->

		</div><!-- end page -->

		<?php
			// load scripts at end of page in order to improve page load speed
			$this->Html->script('jquery.min', array('inline' => false));
			$this->Html->script('bootstrap.min', array('inline' => false));
			echo $this->fetch('script');
		?>

	</body>
</html>


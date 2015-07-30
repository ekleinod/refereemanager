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
																											array('title' => __('Nachricht versenden'), 'routing' => array('action' => 'message', 'controller' => 'tools_editor')),
																											)
																		),
															);
							echo $this->element('navigation', array('navarray' => $navarray));
						?>
					</div>
				</div>
			</nav>
		<!-- /navigation -->

		<!-- content container -->
			<div class="container">

				<!-- page header -->
					<div class="page-header">
						<h1><?php echo $this->fetch('title'); ?></h1>
					</div>
				<!-- /page header -->

				<!-- alert -->
					<?php
						$temp = $this->Session->flash();
						if (!empty($temp)) {
					?>
							<div class="row">
								<div class="col-sm-12">
									<div class="alert alert-danger alert-dismissable">
										<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
										<?php echo $temp; ?>
									</div>
								</div>
							</div>
					<?php } ?>
				<!-- /alert -->

				<!-- content -->
					<?php echo $this->fetch('content'); ?>
				<!-- /content -->

			</div>
		<!-- /content container -->

		<!-- sql dump -->
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<?php echo $this->element('sql_dump'); ?>
					</div>
				</div>
			</div>
		<!-- /sql dump -->

		<!-- footer -->
			<footer class="footer">
				<div class="container">
					<div class="row">
						<div class="col-sm-8">
							<p class="text-muted">
								<a href="http://schiri.bettv.de/">Schiedsrichter-Homepage</a>
							</p>
						</div>
						<div class="col-sm-4">
							<p class="text-muted">
								<?php if (isset($username)) { ?>
									Eingeloggt als "<?php echo $username; ?>".
									<?php echo $this->Html->link(__('Logout'), array('action' => 'logout', 'controller' => 'users'), array('class' => '')); ?>
								<?php } else { ?>
									<?php echo $this->Html->link(__('Login'), array('action' => 'login', 'controller' => 'users'), array('class' => '')); ?>
								<?php } ?>
							</p>
						</div>
					</div>
				</div>
			</footer>
		<!-- /footer -->

		<?php
			// load scripts at end of page in order to improve page load speed
			$this->Html->script('jquery.min', array('inline' => false));
			$this->Html->script('bootstrap.min', array('inline' => false));
			echo $this->fetch('script');
		?>

	</body>
</html>


<?php
$config = array (
	'RefMan' => array (
		'defaultcontacttypeid' => 1,
		'defaultcountrycode' => '49',
		'defaultareacode' => '30',

		'template' => array (
			'path' => 'files/templates/',
			'merge' => 'merge.tex',
			'build' => 'build.xml',
			'refereeview' => 'referee_view.mmd',
			'refereeviewout' => 'referee_views',
			'email' => 'email.mmd',
			'letter' => 'letter.mmd',
			'letterout' => 'letters',
		),

		'refreport' => array (
			'path' => 'files/refereereports/',
			'pattern' => 'OSR_%s_%02d_',
		),
	),
);
?>
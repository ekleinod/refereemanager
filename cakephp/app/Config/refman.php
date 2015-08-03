<?php
$config = array (
	'RefMan' => array (
		'defaultcontacttypeid' => 1,
		'defaultcountrycode' => '49',
		'defaultareacode' => '30',

		'message' => array (
			'opening' => 'Hallo **generated first_name**,',
			'closing' => 'Mit sportlichen Grüßen,',
			'signature' => 'Ekkart.',
		),

		'refreport' => array (
			'path' => 'files/refereereports/',
			'pattern' => 'OSR_%s_%02d_',
		),

		'statustypes' => array (
			'many' => array('font-weight' => 'bold'),
			'normal' => array(),
			'inactiveseason' => array('color' => '#777777'),
			'mailonly' => array('font-style' => 'italic', 'color' => '#777777'),
			'other' => array('font-style' => 'italic'),
		),

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
	),
);
?>
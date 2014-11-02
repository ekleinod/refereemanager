<?php
$config = array (
	'Config' => array (
		'language' => NULL,
	),
	'RefMan' => array (
		'defaultcontacttypeid' => 1,
		'defaultcountrycode' => '49',
		'defaultareacode' => '30',

		'template' => array (
			'path' => 'files/templates/',
			'referee_view' => 'referee_view.mmd',
			'referee_view_all' => 'referee_view_all.tex',
		),

		'refreport' => array (
			'path' => 'files/refereereports/',
			'pattern' => 'OSR_%s_%02d_',
		),
	),
);
?>
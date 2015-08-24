<?php
$config = array(
	'RefMan' => array(
		'contacts' => array(
			'defaultcontacttypeid' => 1,
			'phone' => array(
				'countrycode' => '49',
				'areacode' => '30',
			),
		),

		'message' => array (
			'opening' => 'Hallo **generated first_name**,',
			'closing' => 'Mit sportlichen Grüßen,',
			'signature' => 'Ekkart.',
		),

		'refreport' => array (
			'path' => 'files/refereereports/',
			'pattern' => 'OSR_%s_%02d_',
		),

		'statustypes' => array(
			'many' => array('font-weight' => 'bold'),
			'normal' => array(),
			'inactiveseason' => array('color' => '#777777'),
			'mailonly' => array('font-style' => 'italic', 'color' => '#777777'),
			'other' => array('font-style' => 'italic'),
		),

		'template' => array(
			'assignments' => array(
				'file_all' => '%s_BeTTV_Einsatzplan.pdf',
				'file_person' => '%s_Einsatzplan_%s_%s_%03d.pdf',
				'path' => 'assignments/',
			),
			'attachments' => array(
				'path' => 'attachments/',
			),
			'email' => 'email.mmd',
			'letter' => 'letter.mmd',
			'letterout' => 'letters',
			'person_data' => array(
				'build' => 'build.xml',
				'file' => '%s_%s_%03d',
				'merge' => 'merge.tex',
				'path' => 'person-data/',
				'template' => 'person-data.mmd',
			),
			'path' => 'files/templates/',
			'refereeviewout' => 'referee_views',
		),
	),
);
?>
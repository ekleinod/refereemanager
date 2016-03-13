<?php

// column definitions.
$columns = array();

// ids
if ($isEditor) {
	$columns['index'][] = array('title' => __('P-ID'),
															'content' => $this->Template->getReplaceToken('person:id'));
	$columns['index'][] = array('title' => __('R-ID'),
															'content' => $this->Template->getReplaceToken('referee:id'));
}

// image
if ($isReferee) {
	$columns['index'][] = array('title' => __('Bild'),
															'content' => sprintf('%s%s',
																									 $this->Template->getEmptyToken('picture:url', ''),
																									 $this->Template->getNotEmptyToken('picture:url',
																																											$this->Html->link($this->Html->image($this->Template->getReplaceToken('picture:url'),
																																																														array('width' => '50',
																																																																		'alt' => __('Bild von %s.', $this->Template->getReplaceToken('person:fullname')),
																																																																		'title' => $this->Template->getReplaceToken('person:fullname'))),
																																																				$this->Template->getReplaceToken('picture:url'),
																																																				array('escape' => false)))));
}

// name
	// name
	$params = array();
	$params['width'] = 15;
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Name'),
																	'content' => $this->Template->getReplaceToken('person:name_title'),
																	'excel' => $params);

	// first name
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Vorname'),
																	'content' => $this->Template->getReplaceToken('person:first_name'));

	// table name
	$params = array();
	$params['width'] = 120;
	if ($isReferee) {
		$params['width'] = 110;
	}
	if ($isEditor) {
		$params['width'] = 100;
	}
	$columns['pdf'][] = array('title' => __('Name'),
														'content' => $this->Template->getReplaceToken('person:tablename'),
														'pdf' => $params);

// relations
	if ($isRefView) {
		foreach ($refereerelationtypes as $refereerelationtype) {
			$columns['index'][] =
					$columns['excel'][] = array('title' => __($refereerelationtype['RefereeRelationType']['display_title']),
																			'content' => $this->Template->getReplaceToken(sprintf('refereerelation:%s:%s:%s', $refereerelationtype['RefereeRelationType']['sid'], RefManTemplate::KEY_CURRENT, RefManTemplate::KEY_TITLE)));
		}

		// combined relations
		$params = array();
		$params['width'] = 200;
		if ($isReferee) {
			$params['width'] = 170;
		}
		if ($isEditor) {
			$params['width'] = 100;
		}

		// member and reffor
		$columns['pdf'][] = array('title' => sprintf('%s<br /><em>%s</em>',
																								 __($refereerelationtypes[RefereeRelationType::SID_MEMBER]['RefereeRelationType']['display_title']),
																								 __($refereerelationtypes[RefereeRelationType::SID_REFFOR]['RefereeRelationType']['display_title'])),
															'content' => sprintf('%s%s',
																									 $this->Template->getReplaceToken(sprintf('refereerelation:%s:%s:%s', RefereeRelationType::SID_MEMBER, RefManTemplate::KEY_CURRENT, RefManTemplate::KEY_TITLE)),
																									 $this->Template->getNotEmptyToken(sprintf('refereerelation:%s:%s:%s', RefereeRelationType::SID_REFFOR, RefManTemplate::KEY_CURRENT, RefManTemplate::KEY_TITLE), sprintf('<br /><em>%s</em>', $this->Template->getReplaceToken(sprintf('refereerelation:%s:%s:%s', RefereeRelationType::SID_REFFOR, RefManTemplate::KEY_CURRENT, RefManTemplate::KEY_TITLE))))),
															'pdf' => $params);

	}

// wishes
	if ($isRefView) {
		foreach ($wishtypes as $wishtype) {
			$columns['index'][] =
					$columns['pdf'][] =
					$columns['excel'][] = array('title' => __($wishtype['WishType']['display_title']),
																			'content' => $this->Template->getReplaceToken(sprintf('wish:%s:%s:%s', $wishtype['WishType']['sid'], RefManTemplate::KEY_CURRENT, RefManTemplate::KEY_TITLE)));
		}
	}

// contact
	// email
	if ($isReferee) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('E-Mail'),
																		'content' => $this->Template->getReplaceToken('contacts:emails'));
	}

	// phone
	if ($isReferee) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Telefon'),
															 'content' => $this->Template->getReplaceToken('contacts:phonenumbers_national'));
	}

	// combined contact
	if ($isReferee) {
		$params = array();
		$params['width'] = 180;
		if ($isEditor) {
			$params['width'] = 150;
		}

		// email and phone
		$columns['pdf'][] = array('title' => __('Kontakt'),
															'content' => sprintf('%s%s',
																									 $this->Template->getReplaceToken('contacts:emails'),
																									 $this->Template->getNotEmptyToken('contacts:phonenumbers_national',
																																										 sprintf('<br />%s', $this->Template->getReplaceToken('contacts:phonenumbers_national')))),
															'pdf' => $params);
	}


// address
if ($isEditor) {
	$params = array();
	$params['width'] = 90;
	$columns['index'][] =
			$columns['pdf'][] =
			$columns['excel'][] = array('title' => __('Adresse'),
																	'content' => $this->Template->getReplaceToken('contacts:addresses_fulladdress'),
																	'pdf' => $params);
}

// url
if ($isReferee) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('URL'),
																	'content' => $this->Template->getReplaceToken('contacts:urls'));
}

// sex and birthday
	// sex type
	if ($isEditor) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Geschlecht'),
																		'content' => $this->Template->getReplaceToken('sextype:title'));
	}

	// birthday
	if ($isEditor) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Geburtstag'),
																		'content' => $this->Template->getReplaceToken('person:birthday'));
	}

	// sex and birthday
	if ($isEditor) {
		$params = array();
		$params['width'] = 60;
		$columns['pdf'][] = array('title' => sprintf('%s<br />%s', __('Geschlecht'), __('Geburtstag')),
															'content' => sprintf('%s%s',
																									 $this->Template->getReplaceToken('sextype:title'),
																									 $this->Template->getNotEmptyToken('person:birthday',
																																										 sprintf('<br />%s', $this->Template->getReplaceToken('person:birthday')))),
															'pdf' => $params);
	}

// dayofdeath
if ($isEditor) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Todestag'),
																	'content' => $this->Template->getReplaceToken('person:dayofdeath'));
}

// training level
if ($isRefView) {
	$params = array();
	$params['width'] = 50;
	$columns['index'][] =
			$columns['pdf'][] =
			$columns['excel'][] = array('title' => __('Ausbildung'),
																	'content' => $this->Template->getReplaceToken('traininglevel:abbreviation'),
																	'pdf' => $params);
}

// traininglevelsince
if ($isEditor) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Letzte Ausbildung'),
																	'content' => $this->Template->getReplaceToken('traininglevel:since'));
}

// lasttrainingupdate
if ($isEditor) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Letzte Fortbildung'),
																	'content' => $this->Template->getReplaceToken('traininglevel:lasttrainingupdate'));
}

// nexttrainingupdate
if ($isEditor) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Nächste Fortbildung'),
																	'content' => $this->Template->getReplaceToken('traininglevel:nexttrainingupdate'));
}

// information per letter
if ($isEditor) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Briefversand'),
																	'content' => $this->Template->getReplaceToken('referee:docs_per_letter'));
}

// remarks
	// remark
	if ($isEditor) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Anmerkung'),
																		'content' => sprintf('%s %s', $this->Template->getReplaceToken('person:remark'), $this->Template->getReplaceToken(sprintf('refereestatus:%s:remark', $season['Season']['id']))));
	}

	// internal_remark
	if ($isEditor) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Interne Anmerkung'),
																		'content' => $this->Template->getReplaceToken('person:internal_remark'));
	}

	// combined remark
	if ($isEditor) {
		$params = array();
		$params['width'] = 85;
		$columns['pdf'][] = array('title' => __('Anmerkung'),
															'content' => sprintf('%s%s%s',
																									 $this->Template->getNotEmptyToken('person:remark',
																																							sprintf('%s ', $this->Template->getReplaceToken('person:remark'))),
																									 $this->Template->getNotEmptyToken(sprintf('refereestatus:%s:remark', $season['Season']['id']),
																																							sprintf('%s ', $this->Template->getReplaceToken(sprintf('refereestatus:%s:remark', $season['Season']['id'])))),
																									 $this->Template->getNotEmptyToken('person:internal_remark',
																																							sprintf('intern: %s', $this->Template->getReplaceToken('person:internal_remark')))),
															'pdf' => $params);

	}

	// fill missing params
	foreach ($columns as $key => &$value) {
		foreach ($value as &$coldef) {
			if (!array_key_exists($key, $coldef)) {
				$coldef[$key] = array();
			}
			if (!array_key_exists('width', $coldef[$key])) {
				$coldef[$key]['width'] = ($key === 'excel') ? 'auto' : '';
			}
		}
	}

?>

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
																									 $this->Template->getEmptyToken('picture_url', ''),
																									 $this->Template->getNotEmptyToken('picture_url',
																																											$this->Html->link($this->Html->image($this->Template->getReplaceToken('picture_url'),
																																																														array('width' => '50',
																																																																		'alt' => __('Bild von %s.', $this->Template->getReplaceToken('fullname')),
																																																																		'title' => $this->Template->getReplaceToken('fullname'))),
																																																				$this->Template->getReplaceToken('picture_url'),
																																																				array('escape' => false)))));
}

// name
	// name
	$params = array();
	$params['width'] = 15;
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Name'),
																	'content' => $this->Template->getReplaceToken('name_title'),
																	'excel' => $params);

	// first name
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Vorname'),
																	'content' => $this->Template->getReplaceToken('first_name'));

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
														'content' => $this->Template->getReplaceToken('tablename'),
														'pdf' => $params);

// relations
	// relations
	if ($isRefView) {
		foreach ($refereerelationtypes as $sid => $refereerelationtype) {
			if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
				$columns['index'][] =
						$columns['excel'][] = array('title' => __($refereerelationtype['title']),
																				'content' => $this->Template->getReplaceToken(sprintf('referee_relation_%s', $sid)));
			}
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
																								 __($refereerelationtypes[RefereeRelationType::SID_MEMBER]['title']),
																								 __($refereerelationtypes[RefereeRelationType::SID_REFFOR]['title'])),
															'content' => sprintf('%s%s',
																									 $this->Template->getReplaceToken(sprintf('referee_relation_%s', RefereeRelationType::SID_MEMBER)),
																									 $this->Template->getNotEmptyToken(sprintf('referee_relation_%s', RefereeRelationType::SID_REFFOR), sprintf('<br /><em>%s</em>', $this->Template->getReplaceToken(sprintf('referee_relation_%s', RefereeRelationType::SID_REFFOR))))),
															'pdf' => $params);

		// prefer and noassignment
		if ($isEditor) {
			$columns['pdf'][] = array('title' => sprintf('%s<br /><em>%s</em>',
																									 __($refereerelationtypes[RefereeRelationType::SID_PREFER]['title']),
																									 __($refereerelationtypes[RefereeRelationType::SID_NOASSIGNMENT]['title'])),
																'content' => sprintf('%s%s',
																										 $this->Template->getReplaceToken(sprintf('referee_relation_%s', RefereeRelationType::SID_PREFER)),
																										 $this->Template->getNotEmptyToken(sprintf('referee_relation_%s', RefereeRelationType::SID_NOASSIGNMENT), sprintf('<br /><em>%s</em>', $this->Template->getReplaceToken(sprintf('referee_relation_%s', RefereeRelationType::SID_NOASSIGNMENT))))),
																'pdf' => $params);

			// other (should not be reached)
			foreach ($refereerelationtypes as $sid => $refereerelationtype) {
				if (($sid != RefereeRelationType::SID_MEMBER) &&
						($sid != RefereeRelationType::SID_REFFOR) &&
						($sid != RefereeRelationType::SID_PREFER) &&
						($sid != RefereeRelationType::SID_NOASSIGNMENT)) {
					$columns['pdf'][] = array('title' => __($refereerelationtype['title']),
																		'content' => $this->Template->getReplaceToken(sprintf('referee_relation_%s', $sid)),
																		'pdf' => $params);
				}
			}
		}
	}

// contact
	// email
	if ($isReferee) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('E-Mail'),
																		'content' => $this->Template->getReplaceToken('emails'));
	}

	// phone
	if ($isReferee) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Telefon'),
															 'content' => $this->Template->getReplaceToken('phone_numbers_national'));
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
																									 $this->Template->getReplaceToken('emails'),
																									 $this->Template->getNotEmptyToken('phone_numbers_national',
																																										 sprintf('<br />%s', $this->Template->getReplaceToken('phone_numbers_national')))),
															'pdf' => $params);
	}


// address
if ($isEditor) {
	$params = array();
	$params['width'] = 90;
	$columns['index'][] =
			$columns['pdf'][] =
			$columns['excel'][] = array('title' => __('Adresse'),
																	'content' => $this->Template->getReplaceToken('addresses_fulladdress'),
																	'pdf' => $params);
}

// url
if ($isReferee) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('URL'),
																	'content' => $this->Template->getReplaceToken('urls'));
}

// sex and birthday
	// sex type
	if ($isEditor) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Geschlecht'),
																		'content' => $this->Template->getReplaceToken('sextype'));
	}

	// birthday
	if ($isEditor) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Geburtstag'),
																		'content' => $this->Template->getReplaceToken('birthday'));
	}

	// sex and birthday
	if ($isEditor) {
		$params = array();
		$params['width'] = 60;
		$columns['pdf'][] = array('title' => sprintf('%s<br />%s', __('Geschlecht'), __('Geburtstag')),
															'content' => sprintf('%s%s',
																									 $this->Template->getReplaceToken('sextype'),
																									 $this->Template->getNotEmptyToken('birthday',
																																										 sprintf('<br />%s', $this->Template->getReplaceToken('birthday')))),
															'pdf' => $params);
	}

// dayofdeath
if ($isEditor) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Todestag'),
																	'content' => $this->Template->getReplaceToken('dayofdeath'));
}

// training level
if ($isRefView) {
	$params = array();
	$params['width'] = 50;
	$columns['index'][] =
			$columns['pdf'][] =
			$columns['excel'][] = array('title' => __('Ausbildung'),
																	'content' => $this->Template->getReplaceToken('traininglevel'),
																	'pdf' => $params);
}

// traininglevelsince
if ($isEditor) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Letzte Ausbildung'),
																	'content' => $this->Template->getReplaceToken('traininglevelsince'));
}

// lasttrainingupdate
if ($isEditor) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Letzte Fortbildung'),
																	'content' => $this->Template->getReplaceToken('lasttrainingupdate'));
}

// nexttrainingupdate
if ($isEditor) {
	$columns['index'][] =
			$columns['excel'][] = array('title' => __('Nächste Fortbildung'),
																	'content' => $this->Template->getReplaceToken('nexttrainingupdate'));
}

// remarks
	// remark
	if ($isEditor) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Anmerkung'),
																		'content' => sprintf('%s %s', $this->Template->getReplaceToken('remark'), $this->Template->getReplaceToken(sprintf('refereestatus_%s_remark', $season['Season']['id']))));
	}

	// internal_remark
	if ($isEditor) {
		$columns['index'][] =
				$columns['excel'][] = array('title' => __('Interne Anmerkung'),
																		'content' => $this->Template->getReplaceToken('internal_remark'));
	}

	// combined remark
	if ($isEditor) {
		$params = array();
		$params['width'] = 85;
		$columns['pdf'][] = array('title' => __('Anmerkung'),
															'content' => sprintf('%s%s%s',
																									 $this->Template->getNotEmptyToken('remark',
																																							sprintf('%s ', $this->Template->getReplaceToken('remark'))),
																									 $this->Template->getNotEmptyToken(sprintf('refereestatus_%s_remark', $season['Season']['id']),
																																							sprintf('%s ', $this->Template->getReplaceToken(sprintf('refereestatus_%s_remark', $season['Season']['id'])))),
																									 $this->Template->getNotEmptyToken('internal_remark',
																																							sprintf('intern: %s', $this->Template->getReplaceToken('internal_remark')))),
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

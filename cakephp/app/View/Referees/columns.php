<?php

// column definitions.

$isExport = isset($type);
$isIndex = !$isExport;
$isExcelExport = $isExport && ($type === 'excel');
$isPDFExport = $isExport && ($type === 'pdf');
$isZipExport = $isExport && ($type === 'referee_view_zip');


if (!isset($type)) {
	$type = 'none';
}

$columns = array();

// image
if ($isIndex &&
		$isReferee) {
	$columns[] = array('title' => __('Bild'),
										 'content' => __('Bild von %s', $this->Template->getReplaceToken('fullname')));
}

// name
if ($isIndex || $isExcelExport) {
	$columns[] = array('title' => __('Name'),
										 'content' => $this->Template->getReplaceToken('name_title'));
}

// first name
if ($isIndex || $isExcelExport) {
	$columns[] = array('title' => __('Vorname'),
										 'content' => $this->Template->getReplaceToken('first_name'));
}

// full name
if ($isPDFExport) {
	$params = array();
	$params['width'] = 120;
	if ($isReferee) {
		$params['width'] = 110;
	}
	if ($isEditor) {
		$params['width'] = 100;
	}
	$columns[] = array('title' => __('Name'),
										 'content' => $this->Template->getReplaceToken('fullname'),
										 $type => $params);
}

// relations
if ($isIndex && $isRefView) {
	foreach ($refereerelationtypes as $sid => $refereerelationtype) {
		if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
			$columns[] = array('title' => __($refereerelationtype['title']),
												 'content' => $this->Template->getReplaceToken(sprintf('referee_relation_%s', $sid)));
		}
	}
}

// combined relations
if ($isPDFExport && $isRefView) {
	$params = array();
	$params['width'] = 200;
	if ($isReferee) {
		$params['width'] = 170;
	}
	if ($isEditor) {
		$params['width'] = 100;
	}

		$columns[] = array('title' => sprintf('%s<br /><em>%s</em>',
																					__($refereerelationtypes[RefereeRelationType::SID_MEMBER]['title']),
																					__($refereerelationtypes[RefereeRelationType::SID_REFFOR]['title'])),
											 'content' => sprintf('%s<br /><em>%s</em>',
																						$this->Template->getReplaceToken(sprintf('referee_relation_%s', RefereeRelationType::SID_MEMBER)),
																						$this->Template->getReplaceToken(sprintf('referee_relation_%s', RefereeRelationType::SID_REFFOR))),
											 $type => $params);

	if ($isEditor) {
		$columns[] = array('title' => sprintf('%s<br /><em>%s</em>',
																					__($refereerelationtypes[RefereeRelationType::SID_PREFER]['title']),
																					__($refereerelationtypes[RefereeRelationType::SID_NOASSIGNMENT]['title'])),
											 'content' => sprintf('%s<br /><em>%s</em>',
																						$this->Template->getReplaceToken(sprintf('referee_relation_%s', RefereeRelationType::SID_PREFER)),
																						$this->Template->getReplaceToken(sprintf('referee_relation_%s', RefereeRelationType::SID_NOASSIGNMENT))),
											 $type => $params);

		foreach ($refereerelationtypes as $sid => $refereerelationtype) {
			if (($sid != RefereeRelationType::SID_MEMBER) &&
					($sid == RefereeRelationType::SID_REFFOR) &&
					($sid == RefereeRelationType::SID_PREFER) &&
					($sid == RefereeRelationType::SID_NOASSIGNMENT)) {
				$columns[] = array('title' => __($refereerelationtype['title']),
													 'content' => $this->Template->getReplaceToken(sprintf('referee_relation_%s', $sid)));
			}
		}
	}
}

// email
if ($isReferee) {
	$columns[] = array('title' => __('E-Mail'),
										 'content' => $this->Template->getReplaceToken('emails'));
}

// phone
if ($isReferee) {
	$columns[] = array('title' => __('Telefon'),
										 'content' => $this->Template->getReplaceToken('phone_numbers_national'));
}

// address
if ($isEditor) {
	$columns[] = array('title' => __('Adresse'),
										 'content' => $this->Template->getReplaceToken('addresses_fulladdress'));
}

// url
if ($isReferee) {
	$columns[] = array('title' => __('URL'),
										 'content' => $this->Template->getReplaceToken('urls'));
}

// sex type
if ($isEditor) {
	$columns[] = array('title' => __('Geschlecht'),
										 'content' => $this->Template->getReplaceToken('sextype'));
}

// birthday
if ($isEditor) {
	$columns[] = array('title' => __('Geburtstag'),
										 'content' => $this->Template->getReplaceToken('birthday'));
}

// dayofdeath
if ($isEditor) {
	$columns[] = array('title' => __('Todestag'),
										 'content' => $this->Template->getReplaceToken('dayofdeath'));
}

// training level
if ($isRefView) {
	$params = array();
	$params['width'] = 50;
	$columns[] = array('title' => __('Ausbildung'),
										 'content' => $this->Template->getReplaceToken('traininglevel'),
										 $type => $params);
}

// traininglevelsince
if ($isEditor) {
	$columns[] = array('title' => __('Letzte Ausbildung'),
										 'content' => $this->Template->getReplaceToken('traininglevelsince'));
}

// lasttrainingupdate
if ($isEditor) {
	$columns[] = array('title' => __('Letzte Fortbildung'),
										 'content' => $this->Template->getReplaceToken('lasttrainingupdate'));
}

// nexttrainingupdate
if ($isEditor) {
	$columns[] = array('title' => __('Nächste Fortbildung'),
										 'content' => $this->Template->getReplaceToken('nexttrainingupdate'));
}

// remark
if ($isEditor) {
	$columns[] = array('title' => __('Anmerkung'),
										 'content' => sprintf('%s %s', $this->Template->getReplaceToken('remark'), $this->Template->getReplaceToken(sprintf('refereestatus_%s_remark', $season['id']))));
}

// internal_remark
if ($isEditor) {
	$columns[] = array('title' => __('Interne Anmerkung'),
										 'content' => $this->Template->getReplaceToken('internal_remark'));
}

?>

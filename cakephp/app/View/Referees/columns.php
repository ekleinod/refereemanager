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
										 'content' => $this->Template->getReplaceToken('name_title'));
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
	$columns['fullname'] = array('title' => __('Name'), $type => $params);
}

// relations
if ($isIndex && $isRefView) {
	foreach ($refereerelationtypes as $sid => $refereerelationtype) {
		if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
			$columns[sprintf('referee_relation_%s', $sid)] = array('title' => __($refereerelationtype['title']));
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

	$columns[sprintf('referee_relation_%s referee_relation_%s', RefereeRelationType::SID_MEMBER, RefereeRelationType::SID_REFFOR)] =
			array('title' => sprintf('%s<br /><em>%s</em>', __($refereerelationtypes[RefereeRelationType::SID_MEMBER]['title']), __($refereerelationtypes[RefereeRelationType::SID_REFFOR]['title'])), $type => $params);

	if ($isEditor) {
		$columns[sprintf('referee_relation_%s referee_relation_%s', RefereeRelationType::SID_PREFER, RefereeRelationType::SID_NOASSIGNMENT)] =
				array('title' => sprintf('%s<br /><em>%s</em>', __($refereerelationtypes[RefereeRelationType::SID_PREFER]['title']), __($refereerelationtypes[RefereeRelationType::SID_NOASSIGNMENT]['title'])), $type => $params);

		foreach ($refereerelationtypes as $sid => $refereerelationtype) {
			if (($sid != RefereeRelationType::SID_MEMBER) &&
					($sid == RefereeRelationType::SID_REFFOR) &&
					($sid == RefereeRelationType::SID_PREFER) &&
					($sid == RefereeRelationType::SID_NOASSIGNMENT)) {
				$columns[sprintf('referee_relation_%s', $sid)] = array('title' => __($refereerelationtype['title']));
			}
		}
	}
}

// email
if ($isReferee) {
	$columns['emails'] = array('title' => __('E-Mail'));
}

// phone
if ($isReferee) {
	$columns['phone_numbers_national'] = array('title' => __('Telefon'));
}

// address
if ($isEditor) {
	$columns['addresses_fulladdress'] = array('title' => __('Adresse'));
}

// url
if ($isReferee) {
	$columns['urls'] = array('title' => __('URL'));
}

// sex type
if ($isEditor) {
	$columns['sextype'] = array('title' => __('Geschlecht'));
}

// birthday
if ($isEditor) {
	$columns['birthday'] = array('title' => __('Geburtstag'));
}

// dayofdeath
if ($isEditor) {
	$columns['dayofdeath'] = array('title' => __('Todestag'));
}

// training level
if ($isRefView) {
	$params = array();
	$params['width'] = 50;
	$columns['traininglevel'] = array('title' => __('Ausbildung'), $type => $params);
}

// traininglevelsince
if ($isEditor) {
	$columns['traininglevelsince'] = array('title' => __('Letzte Ausbildung'));
}

// lasttrainingupdate
if ($isEditor) {
	$columns['lasttrainingupdate'] = array('title' => __('Letzte Fortbildung'));
}

// nexttrainingupdate
if ($isEditor) {
	$columns['nexttrainingupdate'] = array('title' => __('Nächste Fortbildung'));
}

// remark
if ($isEditor) {
	$columns['remark'] = array('title' => __('Anmerkung'));
}

// internal_remark
if ($isEditor) {
	$columns['internal_remark'] = array('title' => __('Interne Anmerkung'));
}


?>

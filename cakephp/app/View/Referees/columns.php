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
	$params = array();
	$columns['picture'] = array('title' => __('Bild'));
}

// name
if ($isIndex || $isExcelExport) {
	$columns['name_title'] = array('title' => __('Name'));
}

// first name
if ($isIndex || $isExcelExport) {
	$columns['first_name'] = array('title' => __('Vorname'));
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
if ($isRefView) {
	$params = array();
	$params['width'] = 200;
	if ($isReferee) {
		$params['width'] = 170;
	}
	if ($isEditor) {
		$params['width'] = 100;
	}
	$reltypes1 = '';
	$reltypes2 = '';
	foreach ($refereerelationtypes as $sid => $refereerelationtype) {
		if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
			$columns[sprintf('referee_relation_%s', $sid)] = array('title' => __($refereerelationtype['title']), $type => $params);
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

// actions
if ($isIndex) {
	$columns['actions'] = array('title' => __('Aktionen'));
}

?>

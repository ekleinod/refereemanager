<?php

// column definitions.

$isExport = isset($type);
$isPDFExport = $isExport && ($type === 'pdf');

if (!isset($type)) {
	$type = 'none';
}

$columns = array();

// image
$params = array();
if (!$isExport && $isReferee) {
	$columns[] = array('title' => __('Bild'));
}

// name (combined for pdf output)
$params = array();
$params['width'] = 120;
if ($isReferee) {
	$params['width'] = 110;
}
if ($isEditor) {
	$params['width'] = 100;
}
$columns[] = array('title' => __('Name'), $type => $params);

// first name
if (!$isPDFExport) {
	$columns[] = array('title' => __('Vorname'));
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
			$columns[] = array('title' => __($refereerelationtype['title']), $type => $params);
		}
	}
}

if ($isReferee) {
	$columns[] = array('title' => __('E-Mail'));
	$columns[] = array('title' => __('Telefon'));
}

if ($isEditor) {
	$columns[] = array('title' => __('Adresse'));
}

if ($isReferee) {
	$columns[] = array('title' => __('URL'));
}

if ($isEditor) {
	$columns[] = array('title' => __('Geschlecht'));
	$columns[] = array('title' => __('Geburtstag'));
	$columns[] = array('title' => __('Todestag'));
}

if ($isRefView) {
	$params = array();
	$params['width'] = 50;
	$columns[] = array('title' => __('Ausbildung'), $type => $params);
}

if ($isRefView && $isEditor) {
	$columns[] = array('title' => __('Letzte Ausbildung'));
	$columns[] = array('title' => __('Letzte Fortbildung'));
	$columns[] = array('title' => __('Nächste Fortbildung'));
}

if ($isEditor) {
	$columns[] = array('title' => __('Anmerkung'));
	$columns[] = array('title' => __('Interne Anmerkung'));
}

if (!$isExport) {
	$columns[] = array('title' => __('Aktionen'));
}

?>

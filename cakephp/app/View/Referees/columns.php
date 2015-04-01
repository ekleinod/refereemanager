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
$columns['picture'] = array('title' => __('Bild'));

// name (combined for pdf output)
$params = array();
$params['width'] = 120;
if ($isReferee) {
	$params['width'] = 110;
}
if ($isEditor) {
	$params['width'] = 100;
}
$columns['name_title'] = array('title' => __('Name'), $type => $params);
$columns['fullname'] = array('title' => __('Name'), $type => $params);

// first name
$columns['first_name'] = array('title' => __('Vorname'));

// relations
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
		$tmpSID = $sid; //sprintf('referee_relation_%s', $sid);
		$columns[$tmpSID] = array('title' => __($refereerelationtype['title']), $type => $params);
	}
}

$columns['emails'] = array('title' => __('E-Mail'));
$columns['phone_numbers_national'] = array('title' => __('Telefon'));

$columns['addresses_fulladdress'] = array('title' => __('Adresse'));

$columns['urls'] = array('title' => __('URL'));

$columns['sextype'] = array('title' => __('Geschlecht'));
$columns['birthday'] = array('title' => __('Geburtstag'));
$columns['dayofdeath'] = array('title' => __('Todestag'));

$params = array();
$params['width'] = 50;
$columns['traininglevel'] = array('title' => __('Ausbildung'), $type => $params);

$columns['traininglevelsince'] = array('title' => __('Letzte Ausbildung'));
$columns['lasttrainingupdate'] = array('title' => __('Letzte Fortbildung'));
$columns['nexttrainingupdate'] = array('title' => __('Nächste Fortbildung'));

$columns['remark'] = array('title' => __('Anmerkung'));
$columns['internal_remark'] = array('title' => __('Interne Anmerkung'));

$columns['actions'] = array('title' => __('Aktionen'));

?>

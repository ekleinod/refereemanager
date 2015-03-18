<?php

// column definitions.

$columns = array();

if ($isReferee) {
	$columns[] = array('title' => __('Bild'));
}

$columns[] = array('title' => __('Name'));
$columns[] = array('title' => __('Vorname'));

if ($isRefView) {
	foreach ($refereerelationtypes as $sid => $refereerelationtype) {
		if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
			$columns[] = array('title' => __($refereerelationtype['title']));
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
	$columns[] = array('title' => __('Ausbildung'));
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

$columns[] = array('title' => __('Aktionen'));

?>

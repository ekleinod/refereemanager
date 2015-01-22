<?php

// column definitions.

$columns = array();

if ($isReferee) {
	$columns[] = __('Bild');
}

$columns[] = __('Name');
$columns[] = __('Vorname');

if ($isRefView) {
	foreach ($refereerelationtypes as $sid => $refereerelationtype) {
		if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
			$columns[] = __($refereerelationtype['title']);
		}
	}
}

if ($isReferee) {
	$columns[] = __('E-Mail');
	$columns[] = __('Telefon');
}

if ($isEditor) {
	$columns[] = __('Adresse');
}

if ($isReferee) {
	$columns[] = __('URL');
}

if ($isEditor) {
	$columns[] = __('Geschlecht');
	$columns[] = __('Geburtstag');
	$columns[] = __('Todestag');
}

if ($isRefView) {
	$columns[] = __('Ausbildung');
}

if ($isRefView && $isEditor) {
	$columns[] = __('Letzte Ausbildung');
	$columns[] = __('Letzte Fortbildung');
	$columns[] = __('Nächste Fortbildung');
}

if ($isEditor) {
	$columns[] = __('Anmerkung');
	$columns[] = __('Interne Anmerkung');
}

$columns[] = __('Aktionen');

?>


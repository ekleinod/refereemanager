<?php

/** Returns referee report filenames or empty array. */
function getRefereeReportFiles($season) {
	static $refereeReportFiles; // singleton by php magic

	if ($refereeReportFiles === null) {
		$refereeReportFiles = array();
		$tmpFiles = scandir(sprintf('%s%s%s', WWW_ROOT, Configure::read('RefMan.refreport.path'), $season['title_season']));
		if ($tmpFiles !== FALSE) {
			foreach ($tmpFiles as $filename) {
				if ((strcmp($filename, ".") != 0) && (strcmp($filename, "..") != 0)) {
					$refereeReportFiles[] = $filename;
				}
			}
		}
	}

	return $refereeReportFiles;
}

/** Returns referee report if existent. */
function getRefereeReport($assignment, $season) {

	$fileReturn = null;

	$reportstart = sprintf(Configure::read('RefMan.refreport.pattern'), $assignment['LeagueGame']['League']['abbreviation'], $assignment['LeagueGame']['game_number']);
	foreach (getRefereeReportFiles($season) as $filename) {
		if (substr_compare($filename, $reportstart, 0, strlen($reportstart)) == 0) {
			$fileReturn = $filename;
		}
	}

	return $fileReturn;
}

?>


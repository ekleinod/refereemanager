<?php

/** Returns td line. */
function getTD($title, $style, $content, $class = null) {

	$contentOut = $content;
	if (is_array($content)) {
		$bMore = false;
		foreach ($content as $entry) {
			if ($bMore) {
				$contentOut .= '<br />';
			}
			$bMore = true;
			$contentOut .=;
		}
	}

	return sprintf('<td %s data-title="%s" style="%s">%s</td>',
								 ($class === null) ? '' : sprintf('class="%s"', $class),
								 $title, $style, $contentOut);
}

?>


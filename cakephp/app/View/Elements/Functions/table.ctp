<?php

/** Returns td line. */
function getTD($title, $style, $content, $class = null) {
	return sprintf('<td %s data-title="%s" style="%s">%s</td>',
								 ($class === null) ? '' : sprintf('class="%s"', $class),
								 $title, $style, $content);
}

?>


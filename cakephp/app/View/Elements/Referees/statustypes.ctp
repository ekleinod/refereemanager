<?php

/** Computes different output styles. */
function fillStatusTypes(&$statustypes) {

	foreach ($statustypes as &$statustype) {

		$statustype['outputstyle'] = '';

		if ($statustype['StatusType']['style']) {
			switch ($statustype['StatusType']['style']) {
				case 'normal':
				case 'italic':
				case 'oblique':
					$statustype['outputstyle'] .= sprintf('font-style: %s; ', $statustype['StatusType']['style']);
					break;
				case 'normal':
				case 'bold':
				case 'bolder':
				case 'lighter':
					$statustype['outputstyle'] .= sprintf('font-weight: %s; ', $statustype['StatusType']['style']);
					break;
			}
		}

		if ($statustype['StatusType']['color']) {
			$statustype['outputstyle'] .= sprintf('color: #%s; ', $statustype['StatusType']['color']);
		}

		if ($statustype['StatusType']['bgcolor']) {
			$statustype['outputstyle'] .= sprintf('background-color: #%s; ', $statustype['StatusType']['bgcolor']);
		}

	}

}

?>


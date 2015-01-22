<?php

// Computes different output styles

foreach ($statustypes as &$statustypeedit) {

	$statustypeedit['outputstyle']['index'] = '';

	if ($statustypeedit['StatusType']['style']) {
		switch ($statustypeedit['StatusType']['style']) {
			case 'normal':
			case 'italic':
			case 'oblique':
				$statustypeedit['outputstyle']['index'] .= sprintf('font-style: %s; ', $statustypeedit['StatusType']['style']);
				break;
			case 'normal':
			case 'bold':
			case 'bolder':
			case 'lighter':
				$statustypeedit['outputstyle']['index'] .= sprintf('font-weight: %s; ', $statustypeedit['StatusType']['style']);
				break;
		}
	}

	if ($statustypeedit['StatusType']['color']) {
		$statustypeedit['outputstyle']['index'] .= sprintf('color: #%s; ', $statustypeedit['StatusType']['color']);
	}

	if ($statustypeedit['StatusType']['bgcolor']) {
		$statustypeedit['outputstyle']['index'] .= sprintf('background-color: #%s; ', $statustypeedit['StatusType']['bgcolor']);
	}

}

?>


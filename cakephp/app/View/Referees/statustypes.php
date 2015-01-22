<?php

// Computes different output styles

foreach ($statustypes as &$statustypeedit) {

	$statustypeedit['outputstyle'] = '';

	if ($statustypeedit['StatusType']['style']) {
		switch ($statustypeedit['StatusType']['style']) {
			case 'normal':
			case 'italic':
			case 'oblique':
				$statustypeedit['outputstyle'] .= sprintf('font-style: %s; ', $statustypeedit['StatusType']['style']);
				break;
			case 'normal':
			case 'bold':
			case 'bolder':
			case 'lighter':
				$statustypeedit['outputstyle'] .= sprintf('font-weight: %s; ', $statustypeedit['StatusType']['style']);
				break;
		}
	}

	if ($statustypeedit['StatusType']['color']) {
		$statustypeedit['outputstyle'] .= sprintf('color: #%s; ', $statustypeedit['StatusType']['color']);
	}

	if ($statustypeedit['StatusType']['bgcolor']) {
		$statustypeedit['outputstyle'] .= sprintf('background-color: #%s; ', $statustypeedit['StatusType']['bgcolor']);
	}

}

?>


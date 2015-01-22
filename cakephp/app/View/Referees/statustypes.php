<?php

// Computes different output styles

foreach ($statustypes as &$statustypeedit) {

	$statustypeedit['outputstyle']['html'] = '';
	$statustypeedit['outputstyle']['excel'] = array();

	if ($statustypeedit['StatusType']['style']) {
		switch ($statustypeedit['StatusType']['style']) {
			case 'normal':
			case 'italic':
			case 'oblique':
				$statustypeedit['outputstyle']['html'] .= sprintf('font-style: %s; ', $statustypeedit['StatusType']['style']);
				$statustypeedit['outputstyle']['excel']['font-style'] = $statustypeedit['StatusType']['style'];
				break;
			case 'normal':
			case 'bold':
			case 'bolder':
			case 'lighter':
				$statustypeedit['outputstyle']['html'] .= sprintf('font-weight: %s; ', $statustypeedit['StatusType']['style']);
				$statustypeedit['outputstyle']['excel']['font-weight'] = $statustypeedit['StatusType']['style'];
				break;
		}
	}

	if ($statustypeedit['StatusType']['color']) {
		$statustypeedit['outputstyle']['html'] .= sprintf('color: #%s; ', $statustypeedit['StatusType']['color']);
		$statustypeedit['outputstyle']['excel']['color'] = $statustypeedit['StatusType']['color'];
	}

	if ($statustypeedit['StatusType']['bgcolor']) {
		$statustypeedit['outputstyle']['html'] .= sprintf('background-color: #%s; ', $statustypeedit['StatusType']['bgcolor']);
		$statustypeedit['outputstyle']['excel']['bg-color'] = $statustypeedit['StatusType']['bgcolor'];
	}

}

?>

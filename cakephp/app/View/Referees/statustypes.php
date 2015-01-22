<?php

// Computes different output styles

foreach ($statustypes as &$statustypedef) {

	$statustypedef['outputstyle'] = '';

	if ($statustypedef['StatusType']['style']) {
		switch ($statustypedef['StatusType']['style']) {
			case 'normal':
			case 'italic':
			case 'oblique':
				$statustypedef['outputstyle'] .= sprintf('font-style: %s; ', $statustypedef['StatusType']['style']);
				break;
			case 'normal':
			case 'bold':
			case 'bolder':
			case 'lighter':
				$statustypedef['outputstyle'] .= sprintf('font-weight: %s; ', $statustypedef['StatusType']['style']);
				break;
		}
	}

	if ($statustypedef['StatusType']['color']) {
		$statustypedef['outputstyle'] .= sprintf('color: #%s; ', $statustypedef['StatusType']['color']);
	}

	if ($statustypedef['StatusType']['bgcolor']) {
		$statustypedef['outputstyle'] .= sprintf('background-color: #%s; ', $statustypedef['StatusType']['bgcolor']);
	}

}

?>


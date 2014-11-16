<?php

/**
 * Template Helper library.
 *
 * Methods for using and filling templates.
 *
 * @package       Lib.Utility
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.3
 */
class RefManTemplate {

	/**
	 * Returns template text.
	 *
	 * @param $id template id
	 * @return string template content
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getTemplate($id) {
		return file_get_contents(sprintf('%s%s%s', WWW_ROOT, Configure::read('RefMan.template.path'), Configure::read(sprintf('RefMan.template.%s', $id))));
	}

}

/* EOF */


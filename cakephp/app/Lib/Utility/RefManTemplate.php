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
	 * @return template content
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getTemplate($id) {
		return file_get_contents(sprintf('%s%s%s', WWW_ROOT, Configure::read('RefMan.template.path'), Configure::read(sprintf('RefMan.template.%s', $id))));
	}

	/**
	 * Replaces token in text with value.
	 *
	 * @param $text text
	 * @param $token token
	 * @param $value value
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replace($text, $token, $value) {
		return str_replace(sprintf('**generated %s**', $token), $value, $text);
	}

	/**
	 * Replaces all referee tokens in text.
	 *
	 * @param $text text
	 * @param $referee referee
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replaceRefereeData($text, $referee) {
		$txtReturn = $text;

		$txtReturn = RefManTemplate::replacePersonData($txtReturn, $referee);

		return $txtReturn;
	}

	/**
	 * Replaces all person tokens in text.
	 *
	 * @param $text text
	 * @param $person person
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replacePersonData($text, $person) {
		$txtReturn = $text;

		$txtReturn = RefManTemplate::replace($txtReturn, 'fullname', RefManRefereeFormat::formatPerson($person, 'fullname'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'first_name', $person['Person']['first_name']);

		return $txtReturn;
	}

}

/* EOF */


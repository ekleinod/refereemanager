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

	/** Zip archive. */
	private static $zip = null;

	/** Merge file. */
	private static $merge = null;

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
		return file_get_contents(sprintf('%s%s%s',
																		 WWW_ROOT,
																		 Configure::read('RefMan.template.path'),
																		 Configure::read(sprintf('RefMan.template.%s', $id))));
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

	/**
	 * Replaces special characters in filenames.
	 *
	 * @param $filename filename
	 * @return cleaned filename
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function fileName($filename) {
		$txtReturn = $filename;

		$txtReturn = str_replace('ä', 'ae', $txtReturn);
		$txtReturn = str_replace('Ä', 'Ae', $txtReturn);
		$txtReturn = str_replace('ö', 'oe', $txtReturn);
		$txtReturn = str_replace('Ö', 'Oe', $txtReturn);
		$txtReturn = str_replace('ü', 'ue', $txtReturn);
		$txtReturn = str_replace('Ü', 'Ue', $txtReturn);
		$txtReturn = str_replace('ß', 'ss', $txtReturn);
		$txtReturn = str_replace('.', '_', $txtReturn);
		$txtReturn = str_replace('-', '_', $txtReturn);

		return $txtReturn;
	}

	/**
	 * Open zip archive.
	 *
	 * @param $filename filename
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function openZip($filename) {
		RefManTemplate::$zip = new ZipArchive();
		$zipfile = sprintf('%s%s.zip', TMP, $filename);
		if (RefManTemplate::$zip->open($zipfile, ZipArchive::OVERWRITE) !== TRUE) {
			throw new CakeException(__('Zip-Archiv "%s" konnte nicht angelegt werden.', $zipfile));
		}
		RefManTemplate::$merge = RefManTemplate::getTemplate('merge');
	}

	/**
	 * Adds file to zip archive.
	 *
	 * @param $filename filename
	 * @param $content content
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function addToZip($filename, $content) {
		RefManTemplate::$zip->addFromString(sprintf('%s.mmd', $filename), $content);
		RefManTemplate::$merge = str_replace('#includepdf#',
																				 sprintf("\t\\includepdf{generated/%s.pdf}\n#includepdf#", $filename),
																				 RefManTemplate::$merge);
	}

	/**
	 * Close zip archive.
	 *
	 * @return filename
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function closeZip() {
		RefManTemplate::$merge = str_replace('#includepdf#', '', RefManTemplate::$merge);
		RefManTemplate::$zip->addFromString(Configure::read('RefMan.template.merge'), RefManTemplate::$merge);

		RefManTemplate::$zip->addFile(sprintf('%s%s%s',
																					WWW_ROOT,
																					Configure::read('RefMan.template.path'),
																					Configure::read('RefMan.template.build')),
																	Configure::read('RefMan.template.build'));

		$zipfile = RefManTemplate::$zip->filename;
		RefManTemplate::$zip->close();

		return $zipfile;
	}

}

/* EOF */


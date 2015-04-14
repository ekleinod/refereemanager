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

	/** Referee relations. */
	private static $refereerelationtypes = null;

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
	 * Returns replace token.
	 *
	 * @param $id id
	 * @return replace token with id
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getReplaceToken($id) {
		return sprintf('**generated %s**', $id);
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
		return str_replace(RefManTemplate::getReplaceToken($token), $value, $text);
	}

	/**
	 * Replaces all referee tokens in text.
	 *
	 * @param $text text
	 * @param $referee referee
	 * @param $type format type
	 * @param $export export type
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replaceRefereeData($text, $referee, $type, $export) {
		$txtReturn = $text;

		$txtReturn = RefManTemplate::replacePersonData($txtReturn, $referee, $type, $export);

		// referee relation types
		if (empty(RefManTemplate::$refereerelationtypes)) {
			$model = ClassRegistry::init('RefereeRelationType');
			$model->recursive = -1;
			RefManTemplate::$refereerelationtypes = $model->find('all');
		}
		foreach (RefManTemplate::$refereerelationtypes as $relationtype) {
			$sid = $relationtype['RefereeRelationType']['sid'];
			$txtReturn = RefManTemplate::replace($txtReturn, sprintf('referee_relation_%s', $sid),
																					 RefManRefereeFormat::formatRelations(RefManPeople::getRelations($referee, $sid), $type, $export));
		}

		// training
		$traininglevel = RefManPeople::getTrainingLevel($referee);
		//echo pr($traininglevel);
		$txtReturn = RefManTemplate::replace($txtReturn, 'traininglevel',
																				 (empty($traininglevel) || empty($traininglevel['TrainingLevelType']['abbreviation'])) ? '' : $traininglevel['TrainingLevelType']['abbreviation']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'traininglevelsince',
																				 (empty($traininglevel) || empty($traininglevel['TrainingLevel']['since'])) ? '' : RefManRefereeFormat::formatDate($traininglevel['TrainingLevel']['since'], 'date'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'lasttrainingupdate',
																				 (empty($traininglevel) || empty($traininglevel['lasttrainingupdate'])) ? '' : RefManRefereeFormat::formatDate($traininglevel['lasttrainingupdate'], 'date'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'nexttrainingupdate',
																				 (empty($traininglevel) || empty($traininglevel['nexttrainingupdate'])) ? '' : RefManRefereeFormat::formatDate($traininglevel['nexttrainingupdate'], 'year'));

		// status
		foreach ($referee['RefereeStatus'] as $refereestatus) {
			$txtReturn = RefManTemplate::replace($txtReturn, sprintf('refereestatus_%s_remark', $refereestatus['season_id']),
																					 (empty($refereestatus['remark'])) ? '' : $refereestatus['remark']);
		}

		return $txtReturn;
	}

	/**
	 * Replaces all person tokens in text.
	 *
	 * @param $text text
	 * @param $person person
	 * @param $type format type
	 * @param $export export type
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replacePersonData($text, $person, $type, $export) {
		$txtReturn = $text;

		$txtReturn = RefManTemplate::replace($txtReturn, 'fullname',
																				 (empty($person['Person']['name'])) ? '' : RefManRefereeFormat::formatPerson($person, 'fullname'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'first_name',
																				 (empty($person['Person']['first_name'])) ? '' : $person['Person']['first_name']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'name_title',
																				 (empty($person['Person']['name'])) ? '' : RefManRefereeFormat::formatPerson($person, 'name_title'));

		// dates
		$txtReturn = RefManTemplate::replace($txtReturn, 'birthday',
																				 (empty($person['Person']['birthday'])) ? '' : RefManRefereeFormat::formatDate($person['Person']['birthday'], 'date'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'dayofdeath',
																				 (empty($person['Person']['dayofdeath'])) ? '' : sprintf('†&nbsp;%s', RefManRefereeFormat::formatDate($person['Person']['dayofdeath'], 'date')));

		// contacts
		$txtReturn = RefManTemplate::replace($txtReturn, 'emails',
																				 RefManRefereeFormat::formatContacts(RefManPeople::getContacts($person, 'Email'), $type, 'Email', $export));
		$txtReturn = RefManTemplate::replace($txtReturn, 'phone_numbers_national',
																				 RefManRefereeFormat::formatContacts(RefManPeople::getContacts($person, 'PhoneNumber'), 'national', 'PhoneNumber', $export));
		$txtReturn = RefManTemplate::replace($txtReturn, 'addresses_fulladdress',
																				 RefManRefereeFormat::formatContacts(RefManPeople::getContacts($person, 'Address'), 'fulladdress', 'Address', $export));
		$txtReturn = RefManTemplate::replace($txtReturn, 'urls',
																				 RefManRefereeFormat::formatContacts(RefManPeople::getContacts($person, 'Url'), $type, 'Url', $export));

		// other
		$txtReturn = RefManTemplate::replace($txtReturn, 'sextype',
																				 (empty($person['SexType']['title'])) ? '' : $person['SexType']['title']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'remark',
																				 (empty($person['Person']['remark'])) ? '' : $person['Person']['remark']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'internal_remark',
																				 (empty($person['Person']['internal_remark'])) ? '' : $person['Person']['internal_remark']);

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
	 * @param $directory directory
	 * @param $filename filename
	 * @param $content content
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function addToZip($directory, $filename, $content) {
		RefManTemplate::$zip->addFromString(sprintf('%s/%s.mmd', $directory, $filename), $content);
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


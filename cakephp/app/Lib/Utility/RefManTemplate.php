<?php

/**
 * Template Helper library.
 *
 * Methods for using and filling templates.
 *
 * @package       Lib.Utility
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.4
 * @since 0.3
 */
class RefManTemplate {

	/** Zip archive. */
	private static $zip = null;

	/** Merge file. */
	private static $mergetex = null;

	/** Merge include line. */
	private static $mergeincludetex = null;

	/** Referee relation types. */
	private static $refereerelationtypes = null;

	/** Referee status types. */
	private static $statustypes = null;

	/**
	 * Returns template text.
	 *
	 * @param $id template id
	 * @param $path template path
	 * @return template content
	 *
	 * @version 0.4
	 * @since 0.3
	 */
	public static function getTemplate($id, $path = null) {
		return file_get_contents(sprintf('%s%s%s%s',
																		 WWW_ROOT,
																		 Configure::read('RefMan.template.path'),
																		 empty($path) ? '' : Configure::read(sprintf('RefMan.template.%s', $path)),
																		 Configure::read(sprintf('RefMan.template.%s', $id))));
	}

	/**
	 * Returns general token.
	 *
	 * @param $text text
	 * @return token with text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getToken($text) {
		return sprintf('**%s**', $text);
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
		return RefManTemplate::getToken(sprintf('generated %s', $id));
	}

	/**
	 * Returns if token.
	 *
	 * @param $condition condition
	 * @param $id id
	 * @param $text text
	 * @return if token
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getIfToken($condition, $id, $text) {
		return sprintf('%s%s%s', RefManTemplate::getToken(sprintf('if%s %s', $condition, $id)), $text, RefManTemplate::getToken(sprintf('end%s %s', $condition, $id)));
	}

	/**
	 * Returns empty token.
	 *
	 * @param $id id
	 * @param $text empty text
	 * @return empty token
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getEmptyToken($id, $text) {
		return RefManTemplate::getIfToken('empty', $id, $text);
	}

	/**
	 * Returns empty token.
	 *
	 * @param $id id
	 * @param $text not empty text
	 * @return not empty token
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function getNotEmptyToken($id, $text) {
		return RefManTemplate::getIfToken('notempty', $id, $text);
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

		$txtReturn = $text;

		// resolve conditions
		$conditions = array();
		$conditions['empty'] = sprintf('/%s/', str_replace('(.\*)', '(.*)', str_replace('*', '\*', RefManTemplate::getIfToken('empty', $token, '(.*)'))));
		$conditions['notempty'] = sprintf('/%s/', str_replace('(.\*)', '(.*)', str_replace('*', '\*', RefManTemplate::getIfToken('notempty', $token, '(.*)'))));

		$replacements = array();
		if (empty($value)) {
			$replacements['empty'] = '$1';
			$replacements['notempty'] = '';
		} else {
			$replacements['empty'] = '';
			$replacements['notempty'] = '$1';
		}

		ksort($conditions);
		ksort($replacements);
		$txtReturn = preg_replace($conditions, $replacements, $txtReturn);

		// replace token with value
		$txtReturn = str_replace(RefManTemplate::getReplaceToken($token), $value, $txtReturn);

		return $txtReturn;
	}

	/**
	 * Replaces all referee tokens in text.
	 *
	 * @param $text text
	 * @param $referee referee
	 * @param $type format type ('text')
	 * @param $export export type ('html')
	 * @return replaced text
	 *
	 * @version 0.4
	 * @since 0.3
	 */
	public static function replaceRefereeData($text, $referee, $type, $export) {
		$txtReturn = $text;

		$txtReturn = RefManTemplate::replacePersonData($txtReturn, $referee, $type, $export);
/*
		// referee relation types
		if (empty(RefManTemplate::$refereerelationtypes)) {
			$model = ClassRegistry::init('RefereeRelationType');
			$model->recursive = -1;
			RefManTemplate::$refereerelationtypes = $model->find('all');
		}
		foreach (RefManTemplate::$refereerelationtypes as $relationtype) {
			$sid = $relationtype['RefereeRelationType']['sid'];
			$txtReturn = RefManTemplate::replace($txtReturn, sprintf('referee:referee_relation_%s', $sid),
																					 RefManRefereeFormat::formatRelations(RefManPeople::getRelations($referee, $sid), $type, $export));
		}

		// training
		$traininglevel = RefManPeople::getTrainingLevel($referee);
		$txtReturn = RefManTemplate::replace($txtReturn, 'traininglevel:title',
																				 (empty($traininglevel) || empty($traininglevel['TrainingLevelType']['title'])) ? '' : $traininglevel['TrainingLevelType']['title']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'traininglevel:abbreviation',
																				 (empty($traininglevel) || empty($traininglevel['TrainingLevelType']['abbreviation'])) ? '' : $traininglevel['TrainingLevelType']['abbreviation']);
		$txtReturn = RefManTemplate::replace($txtReturn, 'traininglevel:since',
																				 (empty($traininglevel) || empty($traininglevel['TrainingLevel']['since'])) ? '' : RefManRefereeFormat::formatDate($traininglevel['TrainingLevel']['since'], 'date'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'traininglevel:lasttrainingupdate',
																				 (empty($traininglevel) || empty($traininglevel['lasttrainingupdate'])) ? '' : RefManRefereeFormat::formatDate($traininglevel['lasttrainingupdate'], 'date'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'traininglevel:nexttrainingupdate',
																				 (empty($traininglevel) || empty($traininglevel['nexttrainingupdate'])) ? '' : RefManRefereeFormat::formatDate($traininglevel['nexttrainingupdate'], 'year'));

		// status
		if (empty(RefManTemplate::$statustypes)) {
			$model = ClassRegistry::init('StatusType');
			$model->recursive = -1;
			$tmp = $model->find('all');
			RefManTemplate::$statustypes = array();
			foreach ($tmp as $statustype) {
				RefManTemplate::$statustypes[$statustype['StatusType']['id']] = $statustype;
			}
		}
		foreach ($referee['RefereeStatus'] as $refereestatus) {
			$txtReturn = RefManTemplate::replace($txtReturn, sprintf('refereestatus:%s:title', $refereestatus['season_id']),
																					 RefManTemplate::$statustypes[$refereestatus['status_type_id']]['StatusType']['title']);
			$txtReturn = RefManTemplate::replace($txtReturn, sprintf('refereestatus:%s:remark', $refereestatus['season_id']),
																					 (empty($refereestatus['remark'])) ? '' : $refereestatus['remark']);
		}

		// catch all
		$txtReturn = RefManTemplate::replaceSimpleObjectData($txtReturn, $referee);*/

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

		// names
		foreach (array('fullname', 'name_title', 'tablename') as $theToken) {
			$txtReturn = RefManTemplate::replace($txtReturn, sprintf('person:%s', $theToken),
																					 (empty($person['Person']['name'])) ? '' : RefManRefereeFormat::formatPerson($person, $theToken));
		}

		// dates
		$txtReturn = RefManTemplate::replace($txtReturn, 'person:birthday',
																				 (empty($person['Person']['birthday'])) ? '' : RefManRefereeFormat::formatDate($person['Person']['birthday'], 'date'));
		$txtReturn = RefManTemplate::replace($txtReturn, 'person:dayofdeath',
																				 (empty($person['Person']['dayofdeath'])) ? '' : sprintf('†&nbsp;%s', RefManRefereeFormat::formatDate($person['Person']['dayofdeath'], 'date')));

		// contacts
		$tmpContacts = $person['Contact']['Email'];
		$txtReturn = RefManTemplate::replace($txtReturn, 'contacts:emails',
																				 RefManRefereeFormat::formatContacts($tmpContacts, $type, 'Email', $export));
		$isPrivate = false;
		foreach ($tmpContacts as $tmpContact) {
			$isPrivate |= $tmpContact['info']['editor_only'];
		}
		$txtReturn = RefManTemplate::replace($txtReturn, 'contacts:emails:editor_only', $isPrivate);
/*
		$tmpContacts = RefManPeople::getContacts($person, 'PhoneNumber');
		$txtReturn = RefManTemplate::replace($txtReturn, 'contacts:phonenumbers_national',
																				 RefManRefereeFormat::formatContacts($tmpContacts, 'national', 'PhoneNumber', $export));
		$isPrivate = false;
		foreach ($tmpContacts as $tmpContact) {
			$isPrivate |= $tmpContact['info']['editor_only'];
		}
		$txtReturn = RefManTemplate::replace($txtReturn, 'contacts:phonenumbers:editor_only', $isPrivate);

		$tmpContacts = RefManPeople::getContacts($person, 'Address');
		$txtReturn = RefManTemplate::replace($txtReturn, 'contacts:addresses_fulladdress',
																				 RefManRefereeFormat::formatContacts($tmpContacts, 'fulladdress', 'Address', $export));
		$isPrivate = false;
		foreach ($tmpContacts as $tmpContact) {
			$isPrivate |= $tmpContact['info']['editor_only'];
		}
		$txtReturn = RefManTemplate::replace($txtReturn, 'contacts:addresses:editor_only', $isPrivate);

		$txtReturn = RefManTemplate::replace($txtReturn, 'contacts:urls',
																				 RefManRefereeFormat::formatContacts(RefManPeople::getContacts($person, 'Url'), $type, 'Url', $export));

		// primary contact
		$primaryAddress = RefManPeople::getPrimaryContact($person, 'Address');
		$txtReturn = RefManTemplate::replace($txtReturn, 'contacts:primary:streetnumber',
																					RefManRefereeFormat::formatAddress($primaryAddress, 'streetnumber', $type));
		$txtReturn = RefManTemplate::replace($txtReturn, 'contacts:primary:zipcity',
																					RefManRefereeFormat::formatAddress($primaryAddress, 'zipcity', $type));
*/
		// catch all
		$txtReturn = RefManTemplate::replaceSimpleObjectData($txtReturn, $person);

		return $txtReturn;
	}

	/**
	 * Replaces all season tokens in text.
	 *
	 * @param $text text
	 * @param $season season
	 * @param $type format type
	 * @param $export export type
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replaceSeasonData($text, $season, $type, $export) {
		$txtReturn = $text;

		// catch all
		$txtReturn = RefManTemplate::replaceSimpleObjectData($txtReturn, $season);

		return $txtReturn;
	}

	/**
	 * Replaces all date/time tokens in text.
	 *
	 * @param $text text
	 * @param $time timestamp (current, if null)
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replaceDateTimeData($text, $time = null) {
		$txtReturn = $text;

		if (empty($time)) {
			$time = time();
		}

		$type = 'date';
		$txtReturn = RefManTemplate::replace($txtReturn, sprintf('date:%s', $type), RefManRefereeFormat::formatDate($time, $type));
		$type = 'medium';
		$txtReturn = RefManTemplate::replace($txtReturn, sprintf('date:%s', $type), RefManRefereeFormat::formatDate($time, $type));

		return $txtReturn;
	}

	/**
	 * Replaces all simple object tokens in text.
	 *
	 * @param $text text
	 * @param $object object
	 * @return replaced text
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function replaceSimpleObjectData($text, $object) {
		$txtReturn = $text;

		foreach ($object as $objectkey => $objectentry) {
			foreach ($objectentry as $entrykey => $entryvalue) {
				if (!is_array($entryvalue)) {
					$txtReturn = RefManTemplate::replace($txtReturn,
																							 sprintf('%s:%s', strtolower($objectkey), strtolower($entrykey)),
																							 empty($entryvalue) ? '' : $entryvalue);
				}
			}
		}

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

		// convenience
		$txtReturn = str_replace('ä', 'ae', $txtReturn);
		$txtReturn = str_replace('Ä', 'Ae', $txtReturn);
		$txtReturn = str_replace('é', 'e', $txtReturn);
		$txtReturn = str_replace('è', 'e', $txtReturn);
		$txtReturn = str_replace('ö', 'oe', $txtReturn);
		$txtReturn = str_replace('Ö', 'Oe', $txtReturn);
		$txtReturn = str_replace('ü', 'ue', $txtReturn);
		$txtReturn = str_replace('Ü', 'Ue', $txtReturn);
		$txtReturn = str_replace('ß', 'ss', $txtReturn);

		// catch all
		$txtReturn = preg_replace('/[^a-z0-9]/i', '_', $txtReturn);

		return $txtReturn;
	}

	/**
	 * Open zip archive.
	 *
	 * @param $filename filename
	 *
	 * @version 0.4
	 * @since 0.3
	 */
	public static function openZip($filename) {
		RefManTemplate::$zip = new ZipArchive();
		$zipfile = sprintf('%s%s', TMP, $filename);
		if (RefManTemplate::$zip->open($zipfile, ZipArchive::OVERWRITE) !== TRUE) {
			throw new CakeException(__('Zip-Archiv "%s" konnte nicht angelegt werden.', $zipfile));
		}
	}

	/**
	 * Open merge content.
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public static function openMerge() {
		RefManTemplate::$mergetex = RefManTemplate::getTemplate('merge.tex');
		RefManTemplate::$mergeincludetex = RefManTemplate::getTemplate('merge.includetex');
	}

	/**
	 * Adds file to zip archive.
	 *
	 * @param $filename filename
	 * @param $content content
	 * @param $directory directory
	 *
	 * @version 0.4
	 * @since 0.3
	 */
	public static function addToZip($filename, $content, $directory = null) {
		$outdir = empty($directory) ? '' : sprintf('%s/', $directory);
		RefManTemplate::$zip->addFromString(sprintf('%s%s', $outdir, $filename), $content);
	}

	/**
	 * Adds file to merge content.
	 *
	 * @param $filename filename
	 * @param $directory directory
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public static function addToMerge($filename, $directory = null, $landscape = false) {
		$outdir = empty($directory) ? '' : sprintf('%s/', $directory);
		RefManTemplate::$mergetex = RefManTemplate::replace(RefManTemplate::$mergetex,
																												Configure::read('RefMan.template.merge.includetoken'),
																												sprintf('%s%s',
																																sprintf(RefManTemplate::$mergeincludetex,
																																				$landscape ? 'true' : 'false',
																																				sprintf('%s%s', $outdir, $filename)),
																																RefManTemplate::getReplaceToken(Configure::read('RefMan.template.merge.includetoken'))));
	}

	/**
	 * Close zip archive.
	 *
	 * @return filename
	 *
	 * @version 0.4
	 * @since 0.3
	 */
	public static function closeZip() {
		RefManTemplate::$zip->addFile(sprintf('%s%s%s',
																					WWW_ROOT,
																					Configure::read('RefMan.template.path'),
																					Configure::read('RefMan.template.build')),
																	Configure::read('RefMan.template.build'));

		$zipfile = RefManTemplate::$zip->filename;
		RefManTemplate::$zip->close();

		return $zipfile;
	}

	/**
	 * Close merge content.
	 *
	 * @param $filename filename
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public static function closeMerge($filename) {
		RefManTemplate::$mergetex = RefManTemplate::replace(RefManTemplate::$mergetex,
																												Configure::read('RefMan.template.merge.includetoken'),
																												'');
		RefManTemplate::addToZip($filename, RefManTemplate::$mergetex);
	}

}

/* EOF */


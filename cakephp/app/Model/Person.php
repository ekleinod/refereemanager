<?php

App::uses('AppModel', 'Model');

/**
 * Person Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class Person extends AppModel {

	/**
	 * Declare virtual field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['title_person'] = sprintf(
			'CONCAT(%1$s.name, ", ", %1$s.first_name)',
			$this->alias
		);
	}

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'Person';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'title_person';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'sex_type_id' => array('notempty', 'numeric'),
		'name' => array('notempty'),
		'birthday' => array('date'),
		'dayofdeath' => array('date'),
	);

	/**
	 * hasOne associations
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public $hasOne = array('Referee', 'User');

	/**
	 * hasMany associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $hasMany = array('Contact', 'PersonPreferences', 'Picture', 'RefereeReportRecipient', 'Spokesperson', 'Tournament');

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('SexType');

}

/* EOF */


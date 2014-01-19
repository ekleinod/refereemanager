<?php

App::uses('AppModel', 'Model');

/**
 * Person Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
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
	 * @version 0.1
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array(
			'uuid' => array(
				'rule' => array('uuid'),
			),
		),
		'sex_type_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'name' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'birthday' => array(
			'date' => array(
				'rule' => array('date'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('SexType');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('Contact', 'PersonPreferences', 'Picture', 'RefereeReportRecipient', 'Referee', 'Spokesperson', 'Tournament', 'User');

}

/* EOF */


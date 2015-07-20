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
			'IF (%1$s.first_name IS NULL OR %1$s.first_name = \'\', %1$s.name, CONCAT(%1$s.name, ", ", %1$s.first_name))',
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
		'birthday' => array(
												'rule' => 'date',
												'allowEmpty' => true,
												),
		'dayofdeath' => array(
													'rule' => 'date',
													'allowEmpty' => true,
													),
	);

	/**
	 * hasOne associations
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public $hasOne = array('Picture', 'Referee');

	/**
	 * hasMany associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $hasMany = array('Contact', 'PersonPreferences', 'RefereeReportRecipient', 'Spokesperson', 'Tournament', 'User');

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('SexType');

	// custom programming

	/**
	 * Returns people.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of people, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getPeople() {

		$people = $this->find('all');
		usort($people, array('Person', 'compareTo'));

		return $people;
	}

	/**
	 * Compare two objects.
	 *
	 * @param a first object
	 * @param b second object
	 * @return comparison result
	 *  @retval <0 a<b
	 *  @retval 0 a==b
	 *  @retval >0 a>b
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public static function compareTo($a, $b) {

		// first criterion: name
		$compResult = strcasecmp($a['Person']['name'], $b['Person']['name']);
		if ($compResult != 0) {
			return $compResult;
		}

		// second criterion: first name
		$compResult = strcasecmp($a['Person']['first_name'], $b['Person']['first_name']);
		if ($compResult != 0) {
			return $compResult;
		}

		// equal
		return 0;
	}

}

/* EOF */


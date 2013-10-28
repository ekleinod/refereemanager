<?php

App::uses('AppModel', 'Model');

/**
 * LeaguePlannedReferee Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class LeaguePlannedReferee extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'LeaguePlannedReferee';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'id';

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
		'league_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'season_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'assignment_type_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'quantity' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('League', 'Season', 'AssignmentType');

}

/* EOF */


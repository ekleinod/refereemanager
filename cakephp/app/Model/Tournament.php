<?php

App::uses('AppModel', 'Model');

/**
 * Tournament Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class Tournament extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'Tournament';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'name';

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
		'name' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'start' => array(
			'datetime' => array(
				'rule' => array('datetime'),
			),
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'end' => array(
			'datetime' => array(
				'rule' => array('datetime'),
			),
			'notempty' => array(
				'rule' => array('notempty'),
			),
		),
		'announcement_url' => array(
			'url' => array(
				'rule' => array('url'),
			),
		),
		'information_url' => array(
			'url' => array(
				'rule' => array('url'),
			),
		),
		'club_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'person_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('Club', 'Person');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('TournamentGame', 'TournamentVenue');

}

/* EOF */


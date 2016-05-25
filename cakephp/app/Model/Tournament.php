<?php

App::uses('AppModel', 'Model');

/**
 * Tournament Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
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
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'name' => array('notblank'),
		'start' => array('datetime', 'notblank'),
		'end' => array('datetime', 'notblank'),
		'announcement_url' => array('url'),
		'information_url' => array('url'),
		'club_id' => array('numeric'),
		'person_id' => array('numeric'),
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


<?php

App::uses('AppModel', 'Model');

/**
 * LeaguePlannedReferee Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
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
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'league_id' => array('notempty', 'numeric'),
		'season_id' => array('notempty', 'numeric'),
		'referee_assignment_type_id' => array('notempty', 'numeric'),
		'quantity' => array('notempty', 'numeric'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $belongsTo = array('League', 'RefereeAssignmentType', 'Season');

}

/* EOF */


<?php

App::uses('AppModel', 'Model');

/**
 * RefereeReport Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class RefereeReport extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'RefereeReport';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'url';

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
		'url' => array(
			'notempty' => array(
				'rule' => array('notempty'),
			),
			'url' => array(
				'rule' => array('url'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('League', 'Season');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('RefereeReportRecipient');

}

/* EOF */


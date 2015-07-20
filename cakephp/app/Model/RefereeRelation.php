<?php

App::uses('AppModel', 'Model');

/**
 * RefereeRelation Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class RefereeRelation extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'RefereeRelation';

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
		'referee_id' => array('notempty', 'numeric'),
		'referee_relation_type_id' => array('notempty', 'numeric'),
		'club_id' => array('numeric'),
		'league_id' => array('numeric'),
		'sex_type_id' => array('numeric'),
		'saturday' => array('boolean'),
		'sunday' => array('boolean'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $belongsTo = array('Club', 'League', 'Referee', 'RefereeRelationType', 'SexType');

}

/* EOF */


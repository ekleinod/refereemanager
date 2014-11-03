<?php

App::uses('AppModel', 'Model');

/**
 * TrainingLevelType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class TrainingLevelType extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'TrainingLevelType';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'title';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'sid' => array('isUnique', 'notempty'),
		'rank' => array('isUnique', 'notempty', 'numeric'),
		'title' => array('isUnique', 'notempty'),
		'abbreviation' => array('isUnique', 'notempty'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('TrainingLevel');

	// custom programming

	/* Training level types. */
	const SID_ASSUMP = 'assump';
	const SID_NATUMP = 'natump';
	const SID_NATREF = 'natref';
	const SID_INTUMP = 'intump';

}

/* EOF */


<?php

App::uses('AppModel', 'Model');

/**
 * AssignmentRemark Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class AssignmentRemark extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'AssignmentRemark';

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
		'referee_assignment_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'assignment_remark_type_id' => array(
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
	public $belongsTo = array('RefereeAssignment', 'AssignmentRemarkType');

}

/* EOF */


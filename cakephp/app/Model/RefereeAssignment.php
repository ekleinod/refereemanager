<?php

App::uses('AppModel', 'Model');

/**
 * RefereeAssignment Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class RefereeAssignment extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		// I can't get this one working with referee's name and game number
		$this->virtualFields['display_referee_assignment'] = sprintf(
			'CONCAT(%1$s.id, "-", %1$s.referee_id)',
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
	public $name = 'RefereeAssignment';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_referee_assignment';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'assignment_id' => array('notempty', 'numeric'),
		'referee_assignment_type_id' => array('notempty', 'numeric'),
		'referee_id' => array('notempty', 'numeric'),
		'referee_assignment_status_type_id' => array('numeric'),
		'referee_assignment_remark_type_id' => array('numeric'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $belongsTo = array('Assignment', 'Referee', 'RefereeAssignmentType', 'RefereeAssignmentRemarkType', 'RefereeAssignmentStatusType');

}

/* EOF */


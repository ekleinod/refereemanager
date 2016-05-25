<?php

App::uses('AppModel', 'Model');

/**
 * StatusType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class StatusType extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'StatusType';

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		$this->virtualFields['display_title'] = sprintf(
			'%1$s.title',
			$this->alias
		);
	}

	/**
	 * Display field
	 *
	 * @version 0.6
	 * @since 0.1
	 */
	public $displayField = 'display_title';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'sid' => array('isUnique', 'notblank'),
		'title' => array('isUnique', 'notblank'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('RefereeStatus');

	// custom programming

	/* Status types. */
	const SID_MANY = 'many';
	const SID_NORMAL = 'normal';
	const SID_INACTIVESEASON = 'inactiveseason';
	const SID_MAILONLY = 'mailonly';
	const SID_OTHER = 'other';

	/** Singleton for fast access. */
	private $types = null;
	private $typelist = null;

	/**
	 * Returns types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of types, empty if there are none
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function getTypes() {
		if ($this->types == null) {
			$this->recursive = -1;
			$arrTemp = $this->find('all',
														 array(
																	 'order' => 'title',
																	 )
														 );

			// sids for keys (maybe there is a better/faster solution?)
			$this->types = array();
			foreach ($arrTemp as $dtaTemp) {
				$this->types[$dtaTemp['StatusType']['sid']] = $dtaTemp;
			}
		}

		return $this->types;
	}

	/**
	 * Returns type list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of types
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function getTypeList() {
		if ($this->typelist == null) {
			$this->typelist = $this->find('list',
																		array(
																					'order' => 'title',
																					)
																		);
		}

		return $this->typelist;
	}

}

/* EOF */


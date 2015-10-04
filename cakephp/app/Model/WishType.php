<?php

App::uses('AppModel', 'Model');

/**
 * WishType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.6
 */
class WishType extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public $name = 'WishType';

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
	 * @since 0.6
	 */
	public $displayField = 'display_title';

	/**
	 * Validation rules
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'sid' => array('isUnique', 'notblank'),
		'title' => array('isUnique', 'notblank'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public $hasMany = array('Wish');

	// custom programming

	/* Wish types. */
	const SID_PREFER = 'prefer';
	const SID_AVOID = 'avoid';

	/** Singleton for fast access. */
	private $wishtypes = null;
	private $wishtypelist = null;

	/**
	 * Returns wish types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of wish types
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function getWishTypes() {
		if ($this->wishtypes == null) {
			$this->recursive = -1;
			$this->wishtypes = array();
			foreach ($this->find('all') as $wishtype) {
				$this->wishtypes[$wishtype['WishType']['id']] = $wishtype;
			}
		}

		return $this->wishtypes;
	}

	/**
	 * Returns wish type list.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return list of wish types
	 *
	 * @version 0.6
	 * @since 0.6
	 */
	public function getWishTypeList() {
		if ($this->wishtypelist == null) {

			$this->wishtypelist = array();

			foreach ($this->getWishTypes() as $wishtype) {
				$this->wishtypelist[$wishtype['WishType']['id']] = $wishtype['WishType']['title'];
			}

			asort($this->wishtypelist, SORT_LOCALE_STRING);
		}

		return $this->wishtypelist;
	}

}

/* EOF */


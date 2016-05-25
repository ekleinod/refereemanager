<?php

App::uses('AppModel', 'Model');

/**
 * Contact Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class Contact extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'Contact';

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
		'id' => array('isUnique', 'notblank', 'numeric'),
		'contact_type_id' => array('notblank', 'numeric'),
		'is_primary' => array('boolean'),
		'editor_only' => array('boolean'),
		'person_id' => array('numeric'),
		'club_id' => array('numeric'),
	);

	/**
	 * hasOne associations
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public $hasOne = array('Address', 'Email', 'PhoneNumber', 'Url');

	/**
	 * hasMany associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $hasMany = array('TeamVenue', 'TournamentVenue');

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('ContactType', 'Person', 'Club');

	// custom programming

	/**
	 * Returns all possible contact kinds.
	 *
	 *
	 * @return array of contact kinds
	 *
	 * @version 0.4
	 * @since 0.4
	 */
	public function getContactKinds() {
		return array_keys($this->hasOne);
	}

}

/* EOF */


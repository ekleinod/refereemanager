<?php

App::uses('AppModel', 'Model');

/**
 * User Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class User extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'User';

	/**
	 * Display field.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'username';

	/**
	 * Hash the password before saving.
	 *
	 * @param options
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function beforeSave($options = array()) {
		if (isset($this->data[$this->alias]['password']) && isset($this->data[$this->alias]['salt'])) {
			$this->data[$this->alias]['password'] =
					Security::hash($this->data[$this->alias]['password'] . $this->data[$this->alias]['salt']);
		}
		return true;
	}

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'username' => array(
			'notempty' => array(
				'rule' => array('notempty'),
				'allowEmpty' => false,
				'required' => true,
				'message' => 'Der Nutzername (login) muss angegeben werden.',
			),
			'unique' => array(
				'rule' => array('isUnique'),
				'message' => 'Der Nutzername (login) existiert bereits. Bitte anderen Nutzernamen wählen.',
				'on' => 'create',
			),
			'alphaNumeric' => array(
				'rule' => array('alphaNumeric'),
				'required' => true,
				'message' => 'Der Nutzername (login) darf nur aus Buchstaben und Zahlen bestehen.',
			),
			'between' => array(
				'rule' => array('between', 3, 100),
				'message' => 'Der Nutzername (login) muss mindestens 3 und darf höchstens 100 Zeichen lang sein.',
			),
		),
		'password' => array(
			'notempty' => array(
				'rule' => array('notempty'),
				'allowEmpty' => false,
				'required' => true,
				'message' => 'Das Passwort muss angegeben werden.',
			),
			'between' => array(
				'rule' => array('between', 8, 20),
				'message' => 'Das Passwort muss mindestens 8 und darf höchstens 20 Zeichen lang sein.',
			),
		),
		'user_role_id' => array('notempty', 'numeric'),
		'person_id' => array('numeric'),
		'created' => array('datetime', 'notempty'),
		'modified' => array('datetime', 'notempty'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $belongsTo = array('Person', 'UserRole');

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('ActivityLog');

}

/* EOF */


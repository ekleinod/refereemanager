<?php
App::uses('AppModel', 'Model');
/**
 * User Model
 *
 */
class User extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @var string
	 */
	public $name = 'User';

	/**
	 * Display field.
	 *
	 * @var string
	 */
	public $displayField = 'username';

	/**
	 * Hash the password before saving.
	 *
	 * @param options
	 */
	public function beforeSave($options = array()) {
		if (isset($this->data[$this->alias]['password']) && isset($this->data[$this->alias]['salt'])) {
			$this->data[$this->alias]['password'] =
					Security::hash($this->data[$this->alias]['password'].$this->data[$this->alias]['salt']);
		}
		return true;
	}

	/**
	 * Validation rules
	 *
	 * @var array
	 */
	public $validate = array(
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
				'rule' => array('between', 3, 20),
				'message' => 'Der Nutzername (login) muss mindestens 3 und darf höchstens 20 Zeichen lang sein.',
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
		'user_role_id' => array(
			'notempty' => array(
				'rule' => array('notempty'),
				'allowEmpty' => false,
				'required' => true,
				'message' => 'Eine Rolle muss selektiert sein.',
			),
		),
		'person_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @var array
	 */
	public $belongsTo = array('UserRole', 'Person');

	/**
	 * hasMany associations
	 *
	 * @var array
	 */
	public $hasMany = array('ActivityLog');

}

/* EOF */


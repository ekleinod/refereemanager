<?php
App::uses('FormAuthenticate', 'Controller/Component/Auth');
/**
* SaltFormAuthenticate: Authentication with salts
*
* @author nooclear.com
* taken from: http://nooclear.com/content/cakephp-20-authentication-tutorial-unique-salts
*/
class SaltFormAuthenticate extends FormAuthenticate {

	/**
	 * Returns user with given name and password.
	 *
	 * @param username name of the user
	 * @param password password to hash
	 * @return user
	 */
	protected function _findUser($username, $password){
		$userModel = $this->settings['userModel'];
		list($plugin, $model) = pluginSplit($userModel);
		$fields = $this->settings['fields'];

		$conditions = array(
			$model . '.' . $fields['username'] => $username,
		);
		if (!empty($this->settings['scope'])) {
			$conditions = array_merge($conditions, $this->settings['scope']);
		}
		$result = ClassRegistry::init($userModel)->find('first', array(
			'conditions' => $conditions,
			'recursive' => 0,
			'fields' => array('username', 'password', 'salt'),
		));
		if (empty($result) || empty($result[$model])) {
			return false;
		}
		if($result[$model][$fields['password']] != $this->_password($password, $result[$model]['salt'])){
			return false;
		}
		unset($result[$model][$fields['password']]);
		unset($result[$model]['salt']);
		return $result[$model];
	}

	/**
	 * Returns hashed password.
	 *
	 * Originally, the author hashed three times, I try with one time for starters.
	 *
	 * @param password password to hash
	 * @param salt salt to use
	 * @return hashed password
	 */
	protected function _password($password, $salt){
		return Security::hash($password.$salt);
	}
}
?>

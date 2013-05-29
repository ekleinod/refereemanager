<?php
/**
 * Application level Controller
 *
 * This file is application-wide controller file. You can put all
 * application-wide controller-related methods here.
 *
 * PHP 5
 *
 * CakePHP(tm) : Rapid Development Framework (http://cakephp.org)
 * Copyright 2005-2012, Cake Software Foundation, Inc. (http://cakefoundation.org)
 *
 * Licensed under The MIT License
 * Redistributions of files must retain the above copyright notice.
 *
 * @copyright     Copyright 2005-2012, Cake Software Foundation, Inc. (http://cakefoundation.org)
 * @link          http://cakephp.org CakePHP(tm) Project
 * @package       app.Controller
 * @since         CakePHP(tm) v 0.2.9
 * @license       MIT License (http://www.opensource.org/licenses/mit-license.php)
 */

App::uses('Controller', 'Controller');
App::uses('Security', 'Utility'); // use more secure salt hash

/**
 * Application Controller
 *
 * Add your application-wide methods in the class below, your controllers
 * will inherit them.
 *
 * @package       app.Controller
 * @link http://book.cakephp.org/2.0/en/controllers.html#the-app-controller
 */
class AppController extends Controller {

	/** Role: referee. */
	const ROLE_REFEREE = 'referee';

	/** Role: statistician. */
	const ROLE_STATISTICIAN = 'statistician';

	/** Role: editor. */
	const ROLE_EDITOR = 'editor';

	/** Role: admin. */
	const ROLE_ADMIN = 'admin';

	/**
	 * Extend components array by Auth component for simple authentication.
	 *
	 * @var array
	 */
	public $components = array(
		'Session',
		'Auth' => array(
			'loginRedirect' => array('controller' => 'referees', 'action' => 'index'),
			'logoutRedirect' => array('controller' => 'referees', 'action' => 'index'),
			'authenticate' => array('SaltForm') // use own authentication class
		)
	);

	/**
	 * Defines actions to perform before the action method is executed.
	 *
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		Configure::write('Config.language', $this->Session->read('Config.language'));

		Security::setHash('sha512');
		$this->Auth->allow('index', 'view', 'export');

		// set user role checks
		$user = $this->Auth->user();
		$this->set('isReferee', $this->isReferee($user));
		$this->set('isStatistician', $this->isStatistician($user));
		$this->set('isEditor', $this->isEditor($user));
		$this->set('isAdmin', $this->isAdmin($user));

		// set user name
		if (($user != null) && !empty($user) && ($user['id'] !== null)) {
			$this->set('username', $user['username']);
		}

	}

	/**
	 * Returns if the user has the rights of a referee.
	 *
	 * This includes statisticians, editors and admins.
	 *
	 * @param $user user to check
	 * @return true = referee, false = no referee
	 */
	public function isReferee($user = null) {

		// no user given
		if ($user == null) {
			return false;
		}

		// user not logged in
		if (empty($user) || ($user['id'] == null)) {
			return false;
		}

		// hierarchy
		if ($this->isAdmin($user)) {
			return true;
		}
		if ($this->isEditor($user)) {
			return true;
		}
		if ($this->isStatistician($user)) {
			return true;
		}

		// check rights
		$this->loadModel('User');
		$this->User->id = $user['id'];
		if (!$this->User->exists()) {
			throw new NotFoundException(__('Ungültiger Nutzer: ') . $user['id']);
		}
		$theUser = $this->User->read(null, $user['id']);

		if ($theUser['UserRole']['sid'] == self::ROLE_REFEREE) {
			return true;
		}

		return false;
	}

	/**
	 * Returns if the user has the rights of a statistician.
	 *
	 * This includes editors and admins.
	 *
	 * @param $user user to check
	 * @return true = statistician, false = no referee
	 */
	public function isStatistician($user = null) {

		// no user given
		if ($user == null) {
			return false;
		}

		// user not logged in
		if (empty($user) || ($user['id'] == null)) {
			return false;
		}

		// hierarchy
		if ($this->isAdmin($user)) {
			return true;
		}
		if ($this->isEditor($user)) {
			return true;
		}

		// check rights
		$this->loadModel('User');
		$this->User->id = $user['id'];
		if (!$this->User->exists()) {
			throw new NotFoundException(__('Ungültiger Nutzer: ') . $user['id']);
		}
		$theUser = $this->User->read(null, $user['id']);

		if ($theUser['UserRole']['sid'] == self::ROLE_STATISTICIAN) {
			return true;
		}

		return false;
	}

	/**
	 * Returns if the user has the rights of an editor.
	 *
	 * This includes admins.
	 *
	 * @param $user user to check
	 * @return true = editor, false = no editor
	 */
	public function isEditor($user = null) {

		// no user given
		if ($user == null) {
			return false;
		}

		// user not logged in
		if (empty($user) || ($user['id'] == null)) {
			return false;
		}

		// hierarchy
		if ($this->isAdmin($user)) {
			return true;
		}

		// check rights
		$this->loadModel('User');
		$this->User->id = $user['id'];
		if (!$this->User->exists()) {
			throw new NotFoundException(__('Ungültiger Nutzer: ') . $user['id']);
		}
		$theUser = $this->User->read(null, $user['id']);

		if ($theUser['UserRole']['sid'] == self::ROLE_EDITOR) {
			return true;
		}

		return false;
	}

	/**
	 * Returns if the user has the rights of an admin.
	 *
	 * @param $user user to check
	 * @return true = admin, false = no admin
	 */
	public function isAdmin($user = null) {

		// no user given
		if ($user == null) {
			return false;
		}

		// user not logged in
		if (empty($user) || ($user['id'] == null)) {
			return false;
		}

		// check rights
		$this->loadModel('User');
		$this->User->id = $user['id'];
		if (!$this->User->exists()) {
			throw new NotFoundException(__('Ungültiger Nutzer: ') . $user['id']);
		}
		$theUser = $this->User->read(null, $user['id']);

		if ($theUser['UserRole']['sid'] == self::ROLE_ADMIN) {
			return true;
		}

		return false;
	}

}

/* EOF */


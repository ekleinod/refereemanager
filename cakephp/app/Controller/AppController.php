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
		$theUserRoleSID = null;
		if (($user != null) && !empty($user) && ($user['id'] != null)) {
			// get user role sid
			$this->loadModel('User');
			$this->User->recursive = -1;
			$theUser = $this->User->findById($user['id']);
			$this->loadModel('UserRole');
			$this->UserRole->recursive = -1;
			$theUserRole = $this->UserRole->findById($theUser['User']['user_role_id']);
			$theUserRoleSID = $theUserRole['UserRole']['sid'];

			// set user name
			$this->set('username', $user['username']);
		}

		$this->set('isReferee', $this->isReferee($theUserRoleSID));
		$this->set('isStatistician', $this->isStatistician($theUserRoleSID));
		$this->set('isEditor', $this->isEditor($theUserRoleSID));
		$this->set('isAdmin', $this->isAdmin($theUserRoleSID));

	}

	/**
	 * Returns if the userrole is a referee.
	 *
	 * This includes statisticians, editors and admins.
	 *
	 * @param $userrole user to check
	 * @return true = referee, false = no referee
	 */
	private function isReferee($userrole) {

		// no user given
		if ($userrole === null) {
			return false;
		}

		// hierarchy
		if ($this->isAdmin($userrole)) {
			return true;
		}
		if ($this->isEditor($userrole)) {
			return true;
		}
		if ($this->isStatistician($userrole)) {
			return true;
		}

		// check
		return $userrole === self::ROLE_REFEREE;
	}

	/**
	 * Returns if the userrole is a statistician.
	 *
	 * This includes editors and admins.
	 *
	 * @param $userrole user to check
	 * @return true = statistician, false = no referee
	 */
	private function isStatistician($userrole) {

		// no user given
		if ($userrole === null) {
			return false;
		}

		// hierarchy
		if ($this->isAdmin($userrole)) {
			return true;
		}
		if ($this->isEditor($userrole)) {
			return true;
		}

		// check
		return $userrole === self::ROLE_STATISTICIAN;
	}

	/**
	 * Returns if the userrole is an editor.
	 *
	 * This includes admins.
	 *
	 * @param $userrole user to check
	 * @return true = editor, false = no editor
	 */
	private function isEditor($userrole) {

		// no user given
		if ($userrole === null) {
			return false;
		}

		// hierarchy
		if ($this->isAdmin($userrole)) {
			return true;
		}

		// check
		return $userrole === self::ROLE_EDITOR;
	}

	/**
	 * Returns if the userrole is an admin.
	 *
	 * @param $userrole user to check
	 * @return true = admin, false = no admin
	 */
	private function isAdmin($userrole) {

		// no user given
		if ($userrole === null) {
			return false;
		}

		// check
		return $userrole === self::ROLE_ADMIN;
	}

}

/* EOF */


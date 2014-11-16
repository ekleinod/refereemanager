<?php

App::uses('AppController', 'Controller');
App::uses('CakeEmail', 'Network/Email');
App::uses('RefManRefereeFormat', 'Utility');
App::uses('RefManTemplate', 'Utility');

/**
 * Tools editor Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class ToolsEditorController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeFormat');

	/** Models. */
	public $uses = array('Referee', 'Season');

	/**
	 * Defines actions to perform before the action method is executed.
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		if (!$this->viewVars['isEditor']) {
			$this->Session->setFlash(__('Der Zugriff ist nur für Editoren erlaubt. Bitte loggen Sie sich ein.'));
			$this->redirect(array('controller' => 'users', 'action' => 'login'));
		}
	}

	/**
	 * Index method.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function index() {
		$this->set('title_for_layout', __('Editor-Werkzeuge'));
	}

	/**
	 * Mailinglist method.
	 *
	 * @param season season to use (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function mailinglist($season = null) {

		$this->getSeason($season);
		$this->setAndGetStandard($this->viewVars['season']);

		$theSeparator = ',';
		if (!empty($this->request->data) && array_key_exists('ToolsEditor', $this->request->data)) {
			$theSeparator = $this->request->data['ToolsEditor']['separator'];
		}

		$this->set('separator', $theSeparator);

		$this->set('title_for_layout', __('Mailverteiler'));
	}

	/**
	 * Message method.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function message($season = null) {

		$this->getSeason($season);
		$this->setAndGetStandard($this->viewVars['season']);

		$this->set('title_for_layout', __('Nachricht'));

		// create messages
		if (!empty($this->request->data)) {

			$messageresult = array();

			$sendEmail = ($this->request->data['ToolsEditor']['recipient'] === 'a') || ($this->request->data['ToolsEditor']['recipient'] === 'm');
			$sendLetter = ($this->request->data['ToolsEditor']['recipient'] === 'a') || ($this->request->data['ToolsEditor']['recipient'] === 's');

			// read templates
			$tplEmail = RefManTemplate::getTemplate('email');
			$tplLetter = RefManTemplate::getTemplate('letter');

			$messageresult[] = '<pre>' . $tplEmail . '</pre>';
			$messageresult[] = '<pre>' . $tplLetter . '</pre>';

/*
			// email test (set up email config correctly
			/*$Email = new CakeEmail('default');
			$Email
					->to('') // mailadress(es)
					->bcc('') // mailadress(es)
					->subject('Nachricht der Schiedsrichterverwaltung');*/
			//$Email->send('Die Nachricht.');

			$this->set('messageresult', $messageresult);
		}

	}

	/**
	 * Set and get standard values.
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function setAndGetStandard($season = null) {

		$theSeason = $this->getSeason($season);

		$seasonarray = $this->Season->find('list');
		asort($seasonarray, SORT_LOCALE_STRING);

		$this->set('seasonarray', $seasonarray);

		$this->set('controller', 'ToolsEditor');

		$referees = $this->Referee->getReferees($theSeason);
		$this->set('referees', $referees);
	}

}

/* EOF */


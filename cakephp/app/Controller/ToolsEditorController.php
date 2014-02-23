<?php

App::uses('AppController', 'Controller');

/**
 * Tools editor Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class ToolsEditorController extends AppController {

	/** Models. */
	public $uses = array('Season');

	/**
	 * Defines actions to perform before the action method is executed.
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		if (!$this->viewVars['isEditor']) {
			throw new ForbiddenException(__('Nur für Editoren!'));
		}

		$this->Season->recursive = -1;
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
	 * Message method.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function message() {
		$this->set('title_for_layout', __('Nachricht'));

		$seasonarray = $this->Season->find('list');
		asort($seasonarray, SORT_LOCALE_STRING);
		$this->set('seasonarray', $seasonarray);
	}

}

/* EOF */


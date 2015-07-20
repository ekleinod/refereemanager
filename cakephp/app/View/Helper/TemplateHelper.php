<?php

App::uses('AppHelper', 'View/Helper');
App::uses('RefManTemplate', 'Utility');

/**
 * Template Helper class for using and filling templates.
 *
 * @package       View.Helper
 */
class TemplateHelper extends AppHelper {

	/**
	 * RefManTemplate instance
	 *
	 * @var RefManTemplate
	 */
	protected $_engine = null;

	/**
	 * Default Constructor
	 *
	 * ### Settings:
	 *
	 * - `engine` Class name to use to replace CakeNumber functionality
	 *            The class needs to be placed in the `Utility` directory.
	 *
	 * @param View $View The View this helper is being attached to.
	 * @param array $settings Configuration settings for the helper
	 * @throws CakeException When the engine class could not be found.
	 */
	public function __construct(View $View, $settings = array()) {
		$settings = Hash::merge(array('engine' => 'RefManTemplate'), $settings);
		parent::__construct($View, $settings);
		list($plugin, $engineClass) = pluginSplit($settings['engine'], true);
		App::uses($engineClass, $plugin . 'Utility');
		if (class_exists($engineClass)) {
			$this->_engine = new $engineClass($settings);
		} else {
			throw new CakeException(__d('cake_dev', '%s could not be found', $engineClass));
		}
	}

	/**
	 * Call methods from RefManTemplate utility class
	 */
	public function __call($method, $params) {
		return call_user_func_array(array($this->_engine, $method), $params);
	}

}

/* EOF */


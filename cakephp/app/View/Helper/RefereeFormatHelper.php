<?php

App::uses('AppHelper', 'View/Helper');
App::uses('RefManRefereeFormat', 'Utility');

/**
 * RefereeFormat Helper class for referee manager specific formatting.
 *
 * @package       View.Helper
 */
class RefereeFormatHelper extends AppHelper {

	/**
	 * RefManRefereeFormat instance
	 *
	 * @var RefManRefereeFormat
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
		$settings = Hash::merge(array('engine' => 'RefManRefereeFormat'), $settings);
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
	 * Call methods from RefManRefereeFormat utility class
	 */
	public function __call($method, $params) {
		return call_user_func_array(array($this->_engine, $method), $params);
	}

}

/* EOF */


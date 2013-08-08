<?php
/**
 * RefereeForm Helper class file.
 *
 */

App::uses('AppHelper', 'View/Helper');

/**
 * RefereeForm Helper class for referee manager specific form operations.
 *
 * @package       View.Helper
 */
class RefereeFormHelper extends AppHelper {

	/** Other helpers needed. */
	public $helpers = array('Form', 'Html');

	/**
	 * Returns text label and field.
	 */
	public function getInputText($action, $fieldid, $title, $value = '', $required = false, $placeholder = '', $maxlength = 100, $autofocus = false) {

		$cssclass = 'input text';

		$inputparams = array();
		$inputparams['type'] = 'text';

		$inputparams['title'] = $title;
		$inputparams['label'] = $title;

		if (!empty($value)) {
			$inputparams['value'] = $value;
		}

		if (($action !== 'view') && $required) {
			$inputparams['required'] = 'required';
			$cssclass .= ' required';
		}
		if (($action !== 'view') && !empty($placeholder)) {
			$inputparams['placeholder'] = $placeholder;
		}

		if (!empty($maxlength)) {
			$inputparams['maxlength'] = $maxlength;
		}
		if ($autofocus) {
			$inputparams['autofocus'] = 'autofocus';
		}

		if ($action === 'view') {
			$inputparams['readonly'] = 'readonly';
		}

		$inputparams['div'] = false;
		$inputparams['before'] = $this->Html->tag('li', null, array('class' => $cssclass));
		$inputparams['after'] = '</li>';

		return $this->Form->input($fieldid, $inputparams);

	}

}

/* EOF */


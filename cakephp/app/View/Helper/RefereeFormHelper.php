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
	public function getInputField($action, $type, $fieldid, $title, $value = null, $required = false, $placeholder = null, $maxlength = 100, $autofocus = false, $values = null) {

		$cssclass = 'input text';

		$inputparams = array();
		$inputparams['type'] = $type;

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

		if (!empty($values)) {
			$inputparams['options'] = $values;
		}

		$inputparams['div'] = false;
		$inputparams['before'] = $this->Html->tag('li', null, array('class' => $cssclass));
		$inputparams['after'] = '</li>';

		return $this->Form->input($fieldid, $inputparams);

	}

}

/* EOF */


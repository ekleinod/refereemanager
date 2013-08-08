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

		// css class
		$cssclass = 'input text';

		// label
		$label = $this->Form->label($fieldid, $title);

		// input
		$inputparams = array();
		$inputparams['type'] = 'text';
		$inputparams['title'] = $title;
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

		$input = $this->Form->text($fieldid, $inputparams);

		// tag
		return $this->Html->tag('li',
				$label . $input,
				array('class' => $cssclass)
		);

	}

}

/* EOF */


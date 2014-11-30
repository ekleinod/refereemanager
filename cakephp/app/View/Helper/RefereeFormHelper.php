<?php

App::uses('AppHelper', 'View/Helper');

/**
 * RefereeForm Helper class for referee manager specific form operations.
 *
 * @package       View.Helper
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class RefereeFormHelper extends AppHelper {

	/** Other helpers needed. */
	public $helpers = array('Form', 'Html');

	/**
	 * Returns text label and field.
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getInputField($action, $type, $fieldid, $title, $value = null, $required = false, $placeholder = null, $maxlength = 100, $autofocus = false, $values = null) {

		$cssclass = 'input';

		$inputparams = array();
		$inputparams['type'] = $type;
		// fix the not very pretty date input of cakephp
		if (($type === 'date') || ($type === 'datetime') || ($type === 'time')) {
			$inputparams['type'] = 'text';
			$inputparams['class'] = $type;
		}

		$inputparams['title'] = $title;
		$inputparams['label'] = $title;

		if (!empty($value)) {
			if ($type === 'checkbox') {
				$inputparams['checked'] = 'checked';
			} else {
				$inputparams['value'] = $value;
			}
		}

		if (!empty($maxlength)) {
			$inputparams['maxlength'] = $maxlength;
		}
		if ($autofocus) {
			$inputparams['autofocus'] = 'autofocus';
		}

		if (!empty($values)) {
			$inputparams['options'] = $values;
		}

		// depending on action
		if ($action === 'view') {

			$inputparams['readonly'] = 'readonly';
			if ($type === 'select') {
				$inputparams['type'] = 'text';
				if (!empty($values) && (!empty($value) || ($value > 0))) {
					$inputparams['value'] = $values[$value];
				}
			}

		} else {

			if ($required) {
				$inputparams['required'] = 'required';
				$cssclass .= ' required';
			}
			if (!empty($placeholder)) {
				$inputparams['placeholder'] = $placeholder;
			}

		}

		$cssclass .= ' ' . $inputparams['type'];

		// styling
		$inputparams['div'] = false;
		$inputparams['before'] = $this->Html->tag('li', null, array('class' => $cssclass));
		$inputparams['after'] = '</li>';

		return $this->Form->input($fieldid, $inputparams);

	}

}

/* EOF */


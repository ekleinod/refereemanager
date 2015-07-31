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
	public function getInputField(
																$action,
																$type,
																$fieldid,
																$title,
																$value = null,
																$help = null,
																$placeholder = null,
																$required = false,
																$autofocus = false,
																$maxlength = 100,
																$values = null
																) {

		$sReturn = '';

		// label
		$sReturn .= $this->Form->label(
																	sprintf('%s.%s', implode('.', $this->entity()), $fieldid),
																	$title,
																	array('class' => 'col-sm-2 control-label')
																	);
		$sReturn .= "\n";

		$inputparams = array();

		// bounding div
		$inputparams['div'] = array(
																'class' => sprintf('form-group %s', (($action !== 'view') && $required) ? 'required' : ''),
																);

		// no label
		$inputparams['label'] = false;

		// bounding div for input element
		$inputparams['between'] = '<div class="col-sm-10">';

		// help text and end of div
		$inputparams['after'] = sprintf('%s</div>',
																		empty($help) ? '' : sprintf('<span class="help-block">%s</span>', $help)
																		);

		$inputparams['class'] = 'form-control';

		$inputparams['title'] = $title;

		$inputparams['type'] = $type;
		// fix the not very pretty date input of cakephp
		if (($type === 'date') || ($type === 'datetime') || ($type === 'time')) {
			$inputparams['type'] = 'text';
			$inputparams['class'] .= ' ' . $type;
		}

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
				$inputparams['required'] = '';
			}
			if (!empty($placeholder)) {
				$inputparams['placeholder'] = $placeholder;
			}

		}

		$sReturn .= $this->Form->input($fieldid, $inputparams);

		return $sReturn;

	}

}

/* EOF */


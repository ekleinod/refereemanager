<?php

App::uses('AppHelper', 'View/Helper');

/**
 * RefereeForm Helper class for referee manager specific form operations.
 *
 * This class can not be put into Lib/Utility because of following execution errors whicha re not solved yet.
 *
 * @package       View.Helper
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.6
 * @since 0.1
 */
class RefereeFormHelper extends AppHelper {

	/** Other helpers needed. */
	public $helpers = array('Form', 'Html');

	/**
	 * Returns text label and field.
	 *
	 * @version 0.6
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
																$maxlength = 0,
																$values = null
																) {

		// label
		$sLabel = $this->Form->label(
																	sprintf('%s.%s', implode('.', $this->entity()), $fieldid),
																	$title,
																	array('class' => 'col-sm-2 control-label')
																	);

		// input field parameters
		$inputparams = array();

		// no label, no div, no legend
		$inputparams['label'] = false;
		$inputparams['div'] = false;
		$inputparams['legend'] = false;

		// set simple parameters
		$inputparams['class'] = 'form-control';

		$inputparams['title'] = $title;

		if ($maxlength > 0) {
			$inputparams['maxlength'] = $maxlength;
		}

		if ($autofocus) {
			$inputparams['autofocus'] = 'autofocus';
		}

		if (!empty($value)) {
			if ($type === 'checkbox') {
				$inputparams['checked'] = 'checked';
			} else {
				$inputparams['value'] = $value;
			}
		}

		if (!empty($values)) {
			$inputparams['options'] = $values;
		}

		// type
		$inputparams['type'] = $type;
		// fix the not very pretty date input of cakephp
		if (($type === 'date') || ($type === 'datetime') || ($type === 'time')) {
			$inputparams['type'] = 'text';
			$inputparams['class'] .= ' ' . $type;
		}

		// radio input: wrap with label, empty class
		if ($type === 'radio') {
			$inputparams['before'] = "\n<label>\n";
			$inputparams['after'] = "\n</label>\n";
			$inputparams['separator'] = "\n</label>\n</div>\n<div class=\"col-sm-offset-2 col-sm-10 radio\">\n<label>\n";
			$inputparams['class'] = false;
		}

		// checkbox input: no hidden field
		if ($type === 'checkbox') {
			$inputparams['class'] = false;
		}

		// depending on action
		if ($action === 'view') {

			$inputparams['disabled'] = 'disabled';

			if ($type === 'select') {
				$inputparams['type'] = 'text';
				if (!empty($values) && (!empty($value) || ($value > 0))) {
					$inputparams['value'] = $values[$value];
				}
			}
			if ($type === 'checkbox') {
				$inputparams['type'] = 'text';
				$inputparams['value'] = ((empty($value)) ?
																 __('nein') :
																 (($value == 1) ? __('ja') : h($value))
																 );
			}

			// create input field
			$sField = $this->Html->para(
																	'form-control-static',
																	empty($inputparams['value']) ? '&mdash;' : $inputparams['value']
																	);

			if ($type === 'checkbox') {
				$sField = sprintf("%s (%s)", $sField, $placeholder);
			}

		} else {

			if ($required) {
				$inputparams['required'] = 'required';
			}
			if (!empty($placeholder)) {
				$inputparams['placeholder'] = $placeholder;
			}

			// create input field
			$sField = $this->Form->input($fieldid, $inputparams);

			if ($type === 'checkbox') {
				$sField = sprintf("\n<label>\n%s %s\n</label>\n", $sField, $placeholder);
			}

		}

		if (!empty($help)) {
			$sField .= sprintf('<span class="help-block">%s</span>', $help);
		}
		$sInput = $this->Html->div(sprintf('col-sm-10 %s', $inputparams['type']), $sField);

		return $this->Html->div(
														sprintf('form-group%s', (($action !== 'view') && $required) ? ' required' : ''),
														sprintf("%s\n%s", $sLabel, $sInput)
														);

	}

}

/* EOF */


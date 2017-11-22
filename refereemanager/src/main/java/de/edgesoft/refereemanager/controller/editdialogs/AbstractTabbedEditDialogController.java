package de.edgesoft.refereemanager.controller.editdialogs;
import java.util.ArrayList;
import java.util.List;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.controller.inputforms.IInputFormController;

/**
 * Abstract controller for tabbed edit dialog scenes.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2017 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of TT-Schiri: Referee Manager.
 *
 * TT-Schiri: Referee Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TT-Schiri: Referee Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TT-Schiri: Referee Manager. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.15.0
 * @since 0.15.0
 */
public abstract class AbstractTabbedEditDialogController extends AbstractEditDialogController implements ITabbedEditDialogController {

	/**
	 * List of input form controllers.
	 */
	private List<IInputFormController> lstInputFormControllers = null;

	/**
	 * Adds (embedded) input form controller.
	 *
	 * @param theInputFormController input form controller
	 */
	@Override
	public void addInputFormController(final IInputFormController theInputFormController) {

		if (lstInputFormControllers == null) {
			lstInputFormControllers = new ArrayList<>();
		}

		lstInputFormControllers.add(theInputFormController);
	}

	/**
	 * Initializes form: sets introspection classes and marks required fields.
	 *
	 * @param theClasses list of introspection classes
	 */
	@Override
	public void initForm(final List<Class<?>> theClasses) {

		assert (lstInputFormControllers != null) : "list of input form controllers not initialized!";

		lstInputFormControllers.stream().forEach(form -> form.initForm(theClasses));

    }

	/**
	 * Fills form with data to be edited.
	 *
	 * @param theData data object
	 */
	@Override
	public <U extends ModelClassExt> void fillForm(final U theData) {

		assert (lstInputFormControllers != null) : "list of input form controllers not initialized!";

		lstInputFormControllers.stream().forEach(form -> form.fillForm(theData));

    }

	/**
	 * Fills data object with form data.
	 *
	 * @param theData data object
	 */
	@Override
	public <V extends ModelClassExt> void fillData(V theData) {

		assert (lstInputFormControllers != null) : "list of input form controllers not initialized!";

		lstInputFormControllers.stream().forEach(form -> form.fillData(theData));

	}

}

/* EOF */

package de.edgesoft.refereemanager.controller.editdialogs;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.controller.inputforms.IInputFormController;

/**
 * Interface for the tabbed edit dialog controller.
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
public interface ITabbedEditDialogController<T extends ModelClassExt> extends IEditDialogController {

	/**
	 * Adds (embedded) input form controller.
	 *
	 * @param theInputFormController input form controller
	 */
	public void addInputFormController(final IInputFormController<T> theInputFormController);

}

/* EOF */

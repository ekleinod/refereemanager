package de.edgesoft.refereemanager.controller.crud;

import javafx.beans.value.ObservableBooleanValue;

/**
 * Interface for controller scenes with included CRUD buttons.
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
public interface IEmbedCRUDButtonsController {

	/**
	 * Initializes the CRUD buttons.
	 *
	 * @param theActionsController CRUD actions controller
	 * @param disableEdit when to disable edit button
	 * @param disableDelete when to disable delete button
	 */
	public void initCRUDButtons(
			final ICRUDActionsController theActionsController,
			final ObservableBooleanValue disableEdit,
			final ObservableBooleanValue disableDelete
	);

}

/* EOF */

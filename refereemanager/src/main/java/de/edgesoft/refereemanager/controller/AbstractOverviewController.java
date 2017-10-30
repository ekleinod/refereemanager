package de.edgesoft.refereemanager.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;

/**
 * Abstract controller for overview scenes.
 *
 * I would rather have {@link AbstractOverviewController}, {@link AbstractTitledIDDetailsController} and
 * {@link AbstractEmbedCRUDButtonsController} separated, but since Java forbids multiple
 * inheritance, this is not possible.
 *
 * Therefore, this class extends {@link AbstractEmbedCRUDButtonsController}, limiting
 * the reuse a little bit, but practically not very much.
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
public abstract class AbstractOverviewController extends AbstractTitledIDDetailsController {

	/**
	 * List view part.
	 */
	@FXML
	private Parent embeddedList;

	/**
	 * List view part controller.
	 */
	@FXML
	private IListController embeddedListController;

	/**
	 * Returns list controller.
	 *
	 * @return list controller
	 */
	protected IListController getListController() {
		return embeddedListController;
	}


}

/* EOF */

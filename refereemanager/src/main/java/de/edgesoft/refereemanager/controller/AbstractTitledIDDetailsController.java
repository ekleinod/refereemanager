package de.edgesoft.refereemanager.controller;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import javafx.fxml.FXML;
import javafx.scene.Parent;

/**
 * Abstract controller scenes with included titled ID details and CRUD buttons.
 *
 * I would rather have {@link AbstractTitledIDDetailsController} and
 * {@link AbstractCRUDController} separated, but since Java forbids multiple
 * inheritance, this is not possible.
 *
 * Therefore, this class extends {@link AbstractCRUDController}, limiting
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
public abstract class AbstractTitledIDDetailsController extends AbstractCRUDController implements IDetailsController {

	/**
	 * Titled ID details view part.
	 */
	@FXML
	private Parent embeddedDetailsTitledID;

	/**
	 * Titled ID details view part controller.
	 */
	@FXML
	private DetailsTitledIDController embeddedDetailsTitledIDController;

	/**
	 * Shows detail data.
	 *
	 * @param theDetailData (null if no data to show)
	 */
	@Override
	public <T extends ModelClassExt> void showDetails(final T theDetailData) {
		embeddedDetailsTitledIDController.showDetails((TitledIDType) theDetailData);
	}

}

/* EOF */

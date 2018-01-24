package de.edgesoft.refereemanager.controller.editdialogs;
import java.util.ArrayList;
import java.util.Arrays;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.refereemanager.controller.inputforms.IInputFormController;
import de.edgesoft.refereemanager.jaxb.EventDate;
import de.edgesoft.refereemanager.jaxb.OtherEvent;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import javafx.fxml.FXML;
import javafx.scene.Parent;

/**
 * Controller for the other event edit dialog scene.
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
public class EditDialogOtherEventController extends AbstractTabbedEditDialogController {

	/**
	 * Event data.
	 */
	@FXML
	private Parent embeddedInputFormEventData;

	/**
	 * Event data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormEventDataController;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {

		addInputFormController(embeddedInputFormEventDataController);

		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{IDType.class, TitledIDType.class, EventDate.class, OtherEvent.class})));

		super.initialize();

	}

}

/* EOF */

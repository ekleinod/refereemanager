package de.edgesoft.refereemanager.controller.inputforms;
import de.edgesoft.refereemanager.jaxb.StatusType;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Controller for the status type data edit dialog tab.
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
public class InputFormStatusTypeDataController extends AbstractInputFormController<StatusType> {

	/**
	 * Checkbox for active.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "active", jaxbclass = StatusType.class)
	protected CheckBox chkActive;

	/**
	 * MMD start field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "mmdmarkupstart", jaxbclass = StatusType.class)
	protected TextField txtMmdmarkupstart;

	/**
	 * MMD end field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "mmdmarkupend", jaxbclass = StatusType.class)
	protected TextField txtMmdmarkupend;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// nothing to do for now

	}

}

/* EOF */

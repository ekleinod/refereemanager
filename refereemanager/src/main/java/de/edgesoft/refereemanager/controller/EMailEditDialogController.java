package de.edgesoft.refereemanager.controller;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller for the email edit dialog scene.
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
 * @version 0.14.0
 * @since 0.14.0
 */
public class EMailEditDialogController extends AbstractContactEditDialogController {

	/**
	 * EMail text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "eMail", jaxbclass = EMail.class)
	protected TextField txtEMail;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {

		super.initialize();

		// enable ok button for valid entries only
		btnOK.disableProperty().bind(
				txtEMail.textProperty().isEmpty()
		);

	}

}

/* EOF */

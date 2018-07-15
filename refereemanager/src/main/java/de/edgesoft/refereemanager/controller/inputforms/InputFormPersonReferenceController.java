package de.edgesoft.refereemanager.controller.inputforms;
import java.util.Map;

import de.edgesoft.refereemanager.controller.crud.ListCRUDController;
import de.edgesoft.refereemanager.jaxb.PersonReference;
import de.edgesoft.refereemanager.jaxb.PersonVenueReferrer;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Controller for the person reference edit dialog tab.
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
public class InputFormPersonReferenceController extends AbstractInputFormController<PersonVenueReferrer> {

	/**
	 * CRUD buttons person reference.
	 */
	@FXML
	private Parent embeddedCRUDPersonReference;

	/**
	 * CRUD buttons referee report recipient.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "person", jaxbclass = PersonVenueReferrer.class)
	protected ListCRUDController<PersonReference> embeddedCRUDPersonReferenceController;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		Map.Entry<Parent, FXMLLoader> nodeRefereeReportRecipient = Resources.loadNode("inputforms/PartInputFormPersonList");
		embeddedCRUDPersonReferenceController.initController(
				nodeRefereeReportRecipient.getValue().getController(),
				nodeRefereeReportRecipient.getKey(),
				AppModel.factory::createPersonReference);

	}

}

/* EOF */

package de.edgesoft.refereemanager.controller.inputforms;
import java.util.Map;

import de.edgesoft.refereemanager.controller.crud.ListCRUDController;
import de.edgesoft.refereemanager.jaxb.Address;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.URL;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Controller for the address data edit dialog tab.
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
public class InputFormAddressDataController extends AbstractInputFormController<Person> {

	/**
	 * CRUD buttons address.
	 */
	@FXML
	private Parent embeddedCRUDAddress;

	/**
	 * CRUD buttons address controller.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "address", jaxbclass = Person.class)
	protected ListCRUDController<Address> embeddedCRUDAddressController;

	/**
	 * CRUD buttons url.
	 */
	@FXML
	private Parent embeddedCRUDURL;

	/**
	 * CRUD buttons url controller.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "URL", jaxbclass = Person.class)
	protected ListCRUDController<URL> embeddedCRUDURLController;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		Map.Entry<Parent, FXMLLoader> nodeAddress = Resources.loadNode("inputforms/PartInputFormAddress");
		embeddedCRUDAddressController.initController(
				nodeAddress.getValue().getController(),
				nodeAddress.getKey(),
				"Adresse",
				AppModel.factory::createAddress);

		Map.Entry<Parent, FXMLLoader> nodeURL = Resources.loadNode("inputforms/PartInputFormURL");
		embeddedCRUDURLController.initController(
				nodeURL.getValue().getController(),
				nodeURL.getKey(),
				"URL",
				AppModel.factory::createURL);

	}

}

/* EOF */

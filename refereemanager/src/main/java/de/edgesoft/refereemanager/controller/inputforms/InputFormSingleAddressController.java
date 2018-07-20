package de.edgesoft.refereemanager.controller.inputforms;
import de.edgesoft.refereemanager.jaxb.Address;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import javafx.fxml.FXML;

/**
 * Controller for the single address edit dialog tab.
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
public class InputFormSingleAddressController extends AbstractInputFormController<League> {

	/**
	 * CRUD buttons address controller.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "address", jaxbclass = League.class)
	protected IInputFormController<Address> embeddedAddressController;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

//		Map.Entry<Parent, FXMLLoader> nodeAddress = Resources.loadNode("inputforms/PartInputFormAddress");
//		embeddedAddressController.initController(
//				nodeAddress.getValue().getController(),
//				nodeAddress.getKey(),
//				AppModel.factory::createAddress);

	}

}

/* EOF */

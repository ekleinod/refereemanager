package de.edgesoft.refereemanager.controller.inputforms;
import de.edgesoft.refereemanager.jaxb.Address;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller for the address edit dialog scene.
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
public class PartInputFormAddressController extends AbstractContactInputFormController<Address> {

	/**
	 * Label for street text field.
	 *
	 * @since 0.15.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "street", jaxbclass = Address.class)
	protected Label lblStreet;

	/**
	 * Street text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "street", jaxbclass = Address.class)
	protected TextField txtStreet;

	/**
	 * Label for number text field.
	 *
	 * @since 0.15.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "number", jaxbclass = Address.class)
	protected Label lblNumber;

	/**
	 * Number text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "number", jaxbclass = Address.class)
	protected TextField txtNumber;

	/**
	 * Label for zip code text field.
	 *
	 * @since 0.15.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "zipCode", jaxbclass = Address.class)
	protected Label lblZipCode;

	/**
	 * Zip code text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "zipCode", jaxbclass = Address.class)
	protected TextField txtZipCode;

	/**
	 * Label for city text field.
	 *
	 * @since 0.15.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "city", jaxbclass = Address.class)
	protected Label lblCity;

	/**
	 * City text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "city", jaxbclass = Address.class)
	protected TextField txtCity;

}

/* EOF */
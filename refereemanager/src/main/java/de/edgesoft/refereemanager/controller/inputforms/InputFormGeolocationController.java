package de.edgesoft.refereemanager.controller.inputforms;
import de.edgesoft.refereemanager.jaxb.Venue;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.SpinnerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;

/**
 * Controller for the geolocation data edit dialog tab.
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
public class InputFormGeolocationController extends AbstractInputFormController<Venue> {

	/**
	 * Spinner for latitude.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "latitude", jaxbclass = Venue.class)
	protected Spinner<Double> spnLatitude;

	/**
	 * Spinner for longitude.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "longitude", jaxbclass = Venue.class)
	protected Spinner<Double> spnLongitude;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// spinners
        SpinnerUtils.prepareDoubleSpinner(spnLatitude, -90.0, 90.0);
        SpinnerUtils.prepareDoubleSpinner(spnLongitude, -180.0, 180.0);

	}

}

/* EOF */

package de.edgesoft.refereemanager.controller.inputforms;
import java.util.ArrayList;
import java.util.Arrays;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.EventDay;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import jfxtras.scene.control.LocalTimeTextField;

/**
 * Controller for the input form part: event day without referee assignments.
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
public class PartInputFormEventDayNoAssignmentController extends AbstractInputFormController {

	/**
	 * Label for picker for date.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "date", jaxbclass = EventDay.class)
	protected Label lblDate;

	/**
	 * Picker for date.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "date", jaxbclass = EventDay.class)
	protected DatePicker pckDate;

	/**
	 * Label for spinner for start time.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "startTime", jaxbclass = EventDay.class)
	protected Label lblStartTime;

	/**
	 * Spinner for start time.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "startTime", jaxbclass = EventDay.class)
	protected LocalTimeTextField pckStartTime;

	/**
	 * Label for spinner for end time.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "endTime", jaxbclass = EventDay.class)
	protected Label lblEndTime;

	/**
	 * Spinner for end time.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "endTime", jaxbclass = EventDay.class)
	protected LocalTimeTextField pckEndTime;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// set date picker date format
        pckDate.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

		// init form
		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{EventDay.class})));

	}

}

/* EOF */

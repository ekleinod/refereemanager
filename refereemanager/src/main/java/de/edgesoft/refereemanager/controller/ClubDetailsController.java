package de.edgesoft.refereemanager.controller;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.jaxb.Club;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;

/**
 * Controller for the club details scene.
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
public class ClubDetailsController implements IDetailsController {

	/**
	 * Heading club data.
	 */
	@FXML
	private Label lblHeadingClub;

	/**
	 * Filename.
	 */
	@FXML
	private Label lblFilename;

	/**
	 * Local club?.
	 */
	@FXML
	private Label lblLocal;

	/**
	 * URL.
	 */
	@FXML
	private Label lblURL;

	/**
	 * Venue.
	 */
	@FXML
	private Label lblVenue;

	/**
	 * Contact.
	 */
	@FXML
	private Label lblContact;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// headings
		lblHeadingClub.setFont(FontUtils.getDerived(lblHeadingClub.getFont(), FontWeight.BOLD));

	}

	/**
	 * Shows detail data.
	 *
	 * @param theDetailData (null if no data to show)
	 */
	@Override
	public <T extends ModelClassExt> void showDetails(final T theDetailData) {

		if ((theDetailData == null) || !(theDetailData instanceof Club)) {

			LabelUtils.setText(lblFilename, null);
			LabelUtils.setText(lblLocal, null);
			LabelUtils.setText(lblURL, null);
			LabelUtils.setText(lblVenue, null);
			LabelUtils.setText(lblContact, null);

		} else {

			Club theData = (Club) theDetailData;

			LabelUtils.setText(lblFilename, theData.getFilename());
			LabelUtils.setText(lblLocal, new SimpleStringProperty("ToDo"));
			LabelUtils.setText(lblURL, new SimpleStringProperty("ToDo"));
			LabelUtils.setText(lblVenue, new SimpleStringProperty("ToDo"));
			LabelUtils.setText(lblContact, new SimpleStringProperty("ToDo"));

		}

	}

}

/* EOF */

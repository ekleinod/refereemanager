package de.edgesoft.refereemanager.controller.details;

import java.util.stream.Collectors;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.model.ClubModel;
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
public class DetailsClubController<T extends ClubModel> implements IDetailsController<T> {

	/**
	 * Heading.
	 */
	@FXML
	private Label lblHeading;

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
	private Label lblContactPerson;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// headings
		lblHeading.setFont(FontUtils.getDerived(lblHeading.getFont(), FontWeight.BOLD));

	}

	/**
	 * Shows detail data.
	 *
	 * @param theDetailData (null if no data to show)
	 */
	@Override
	public void showDetails(final T theDetailData) {

		if (theDetailData == null) {

			LabelUtils.setText(lblFilename, null);
			LabelUtils.setText(lblLocal, null);
			LabelUtils.setText(lblURL, null);
			LabelUtils.setText(lblVenue, null);
			LabelUtils.setText(lblContactPerson, null);

		} else {

			LabelUtils.setText(lblFilename, theDetailData.getFilename());

			lblLocal.setText(theDetailData.getLocal().getValue() ? "ja" : "nein");

			lblURL.setText(theDetailData.getURL().stream().map(url -> url.getDisplayText().getValueSafe()).collect(Collectors.joining("\n")));

			lblVenue.setText(theDetailData.getVenueList().stream().map(venue -> venue.getDisplayText().getValueSafe()).collect(Collectors.joining("\n")));

			lblContactPerson.setText(theDetailData.getContactPersonList().stream().map(person -> person.getDisplayText().getValueSafe()).collect(Collectors.joining("\n")));

		}

	}

}

/* EOF */

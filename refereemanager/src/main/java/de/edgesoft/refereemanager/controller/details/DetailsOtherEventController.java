package de.edgesoft.refereemanager.controller.details;

import java.util.stream.Collectors;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.model.OtherEventModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;

/**
 * Controller for the other event details scene.
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
public class DetailsOtherEventController<T extends OtherEventModel> implements IDetailsController<T> {

	/**
	 * Heading.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * Date label.
	 */
	@FXML
	private Label lblDate;

	/**
	 * Time label.
	 */
	@FXML
	private Label lblTime;

	/**
	 * Venue label.
	 */
	@FXML
	private Label lblVenues;

	/**
	 * Type label.
	 */
	@FXML
	private Label lblType;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

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

			LabelUtils.setText(lblDate, null);
			LabelUtils.setText(lblTime, null);
			LabelUtils.setText(lblVenues, null);
			LabelUtils.setText(lblType, null);

		} else {

			LabelUtils.setText(lblDate, theDetailData.getDateText());
			LabelUtils.setText(lblTime, theDetailData.getTimeText());

			lblVenues.setText(theDetailData.getVenue().stream().map(venue -> venue.getDisplayText().getValueSafe()).collect(Collectors.joining(System.lineSeparator())));

			lblType.setText(
					(theDetailData.getType() == null) ?
							null :
							theDetailData.getType().getDisplayTitle().getValueSafe());

		}

	}

}

/* EOF */

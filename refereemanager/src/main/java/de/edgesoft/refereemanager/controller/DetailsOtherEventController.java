package de.edgesoft.refereemanager.controller;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.model.OtherEventModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;

/**
 * Controller for the event details scene.
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
public class DetailsOtherEventController implements IDetailsController {
	
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
	private Label lblVenue;

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
	public <T extends ModelClassExt> void showDetails(final T theDetailData) {
		
		Class<OtherEventModel> theClass = OtherEventModel.class;

		assert 
				((theDetailData == null) || theClass.isInstance(theDetailData)) 
				: String.format("Detail data is not of type %s but of type %s.", theClass.getName(), (theDetailData == null) ? "null" : theDetailData.getClass().getName());

		if (theDetailData == null) {

			LabelUtils.setText(lblDate, null);
			LabelUtils.setText(lblTime, null);
			LabelUtils.setText(lblVenue, null);
			LabelUtils.setText(lblType, null);

		} else {

			OtherEventModel theData = theClass.cast(theDetailData);

			LabelUtils.setText(lblDate, theData.getDateText());
			LabelUtils.setText(lblTime, theData.getTimeText());

			lblVenue.setText(
					(theData.getVenue() == null) ?
							null :
							theData.getVenue().getDisplayTitle().getValueSafe());

			LabelUtils.setText(lblType, new SimpleStringProperty("ToDo"));

		}

	}

}

/* EOF */

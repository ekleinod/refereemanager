package de.edgesoft.refereemanager.controller.details;

import java.text.MessageFormat;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.model.TrainingLevelTypeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;

/**
 * Controller for the training level details scene.
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
public class DetailsTrainingLevelTypeController<T extends TrainingLevelTypeModel> implements IDetailsController<T> {

	/**
	 * Heading.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * Rank.
	 */
	@FXML
	private Label lblRank;

	/**
	 * Update interval.
	 */
	@FXML
	private Label lblUpdateInterval;


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

			LabelUtils.setText(lblRank, null);
			LabelUtils.setText(lblUpdateInterval, null);

		} else {

			lblRank.setText(
					(theDetailData.getRank() == null) ?
							null :
							theDetailData.getRank().getValue().toString());

			lblUpdateInterval.setText(
					(theDetailData.getUpdateInterval() == null) ?
							null :
							MessageFormat.format("{0,choice,0#nie|1#jedes Jahr|1<{0,number,integer} Jahre}", theDetailData.getUpdateInterval().getValue()));

		}

	}

}

/* EOF */

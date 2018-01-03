package de.edgesoft.refereemanager.controller.details;

import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.model.RefereeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for the referee details scene.
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
public class DetailsRefereeController<T extends RefereeModel> extends DetailsPersonController<T> {

	/**
	 * Training level.
	 */
	@FXML
	private Label lblTrainingLevel;

	/**
	 * Club.
	 */
	@FXML
	private Label lblClub;

	/**
	 * Last update.
	 */
	@FXML
	private Label lblLastUpdate;

	/**
	 * Next update.
	 */
	@FXML
	private Label lblNextUpdate;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {
		super.initialize();
	}

	/**
	 * Shows detail data.
	 *
	 * @param theDetailData (null if no data to show)
	 */
	@Override
	public void showDetails(final T theDetailData) {

		super.showDetails(theDetailData);

		if (theDetailData == null) {

			LabelUtils.setText(lblTrainingLevel, null);
			LabelUtils.setText(lblClub, null);
			LabelUtils.setText(lblLastUpdate, null);
			LabelUtils.setText(lblNextUpdate, null);

		} else {

			lblTrainingLevel.setText(
					(theDetailData.getHighestTrainingLevel() == null) ?
							null :
							theDetailData.getHighestTrainingLevel().getType().getDisplayTitleShort().getValue());

			lblClub.setText(
					(theDetailData.getMember() == null) ?
							null :
							theDetailData.getMember().getDisplayText().getValue());

			LabelUtils.setText(lblLastUpdate, theDetailData.getLastTrainingUpdate(), null);
			LabelUtils.setText(lblNextUpdate, theDetailData.getNextTrainingUpdate(), "yyyy");

		}

	}

}

/* EOF */

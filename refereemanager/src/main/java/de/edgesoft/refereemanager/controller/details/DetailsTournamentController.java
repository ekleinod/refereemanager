package de.edgesoft.refereemanager.controller.details;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.model.TournamentModel;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;

/**
 * Controller for the tournament details scene.
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
public class DetailsTournamentController<T extends TournamentModel> implements IDetailsController<T> {

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
	 * Club label.
	 */
	@FXML
	private Label lblClub;

	/**
	 * Organizer label.
	 */
	@FXML
	private Label lblOrganizer;

	/**
	 * Venue label.
	 */
	@FXML
	private Label lblVenue;

	/**
	 * Referee report box.
	 */
	@FXML
	private HBox boxRefereeReport;

	/**
	 * Referee report label.
	 */
	@FXML
	private Label lblRefereeReport;

	/**
	 * Referee report indicator label.
	 */
	@FXML
	private Label lblRefereeReportIndicator;

	/**
	 * Referee report copy button.
	 */
	@FXML
	private Button btnRefereeReportCopy;

	/**
	 * Type.
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

		// icons
		btnRefereeReportCopy.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-copy.png")));

	}

	/**
	 * Shows detail data.
	 *
	 * @param theDetailData (null if no data to show)
	 */
	@Override
	public void showDetails(final T theDetailData) {

		if (theDetailData == null) {

			lblHeading.setText("Details");

			LabelUtils.setText(lblDate, null);
			LabelUtils.setText(lblTime, null);
			LabelUtils.setText(lblClub, null);
			LabelUtils.setText(lblOrganizer, null);
			LabelUtils.setText(lblVenue, null);
			LabelUtils.setText(lblRefereeReport, null);

			lblRefereeReportIndicator.setGraphic(null);
			btnRefereeReportCopy.setDisable(true);

			LabelUtils.setText(lblType, null);

		} else {

			LabelUtils.setText(lblDate, theDetailData.getDateText());
			LabelUtils.setText(lblTime, theDetailData.getTimeText());


			lblVenue.setText(
					(theDetailData.getVenue() == null) ?
							null :
								theDetailData.getVenue().getDisplayTitle().getValueSafe());

			lblClub.setText(
					(theDetailData.getOrganizingClub() == null) ?
							null :
							theDetailData.getOrganizingClub().getDisplayTitleShort().getValueSafe());

			lblOrganizer.setText(
					(theDetailData.getOrganizer() == null) ?
							null :
							theDetailData.getOrganizer().getDisplayText().getValueSafe());

			LabelUtils.setText(lblRefereeReport, theDetailData.getRefereeReportFilename());

			if (theDetailData.existsRefereeReportFile()) {
				lblRefereeReportIndicator.setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-success.png")));
			} else {
				lblRefereeReportIndicator.setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-error.png")));
			}
			btnRefereeReportCopy.setDisable(false);

			LabelUtils.setText(lblType, new SimpleStringProperty("ToDo"));

		}

	}

	/**
	 * Copies referee report filename to clipboard.
	 */
	@FXML
	private void handleRefereeReportCopy() {
		ClipboardContent clpContent = new ClipboardContent();
		clpContent.putString(lblRefereeReport.getText());
		Clipboard.getSystemClipboard().setContent(clpContent);
	}

}

/* EOF */

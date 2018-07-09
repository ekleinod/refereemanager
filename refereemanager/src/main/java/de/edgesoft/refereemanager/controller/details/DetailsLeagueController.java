package de.edgesoft.refereemanager.controller.details;

import java.util.stream.Collectors;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.controller.AbstractHyperlinkController;
import de.edgesoft.refereemanager.model.LeagueModel;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;

/**
 * Controller for the league details scene.
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
public class DetailsLeagueController<T extends LeagueModel> extends AbstractHyperlinkController implements IDetailsController<T> {

	/**
	 * Heading.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * Sex type.
	 */
	@FXML
	private Label lblSexType;

	/**
	 * Rank.
	 */
	@FXML
	private Label lblRank;

	/**
	 * Results.
	 */
	@FXML
	private Hyperlink lnkResultsURL;

	/**
	 * Referee report.
	 */
	@FXML
	private Hyperlink lnkRefereeReportURL;

	/**
	 * Referee report recipients.
	 */
	@FXML
	private Label lblRefereeReportRecipient;


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

			LabelUtils.setText(lblSexType, null);
			LabelUtils.setText(lblRank, null);
			lnkResultsURL.setText(null);
			lnkRefereeReportURL.setText(null);
			LabelUtils.setText(lblRefereeReportRecipient, null);

		} else {

			lblSexType.setText(
					(theDetailData.getSexType() == null) ?
							null :
							theDetailData.getSexType().getDisplayText().getValue());

			lblRank.setText(
					(theDetailData.getRank() == null) ?
							null :
							theDetailData.getRank().getValue().toString());

			lnkResultsURL.setText(
					(theDetailData.getResultsURL() == null) ?
							null :
							theDetailData.getResultsURL().getValue().toString());

			lnkRefereeReportURL.setText(
					(theDetailData.getRefereeReportURL() == null) ?
							null :
							theDetailData.getRefereeReportURL().getValue().toString());

			lblRefereeReportRecipient.setText(
					(theDetailData.getRefereeReportRecipient().isEmpty()) ?
							null :
							theDetailData.getRefereeReportRecipient().stream()
								.map(person -> person.getDisplayText().getValueSafe())
								.collect(Collectors.joining(System.lineSeparator())));

		}

	}

}

/* EOF */
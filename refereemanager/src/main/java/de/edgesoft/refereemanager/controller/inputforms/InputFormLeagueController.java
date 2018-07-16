package de.edgesoft.refereemanager.controller.inputforms;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.ButtonUtils;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import de.edgesoft.refereemanager.utils.SpinnerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Controller for the league data edit dialog tab.
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
public class InputFormLeagueController extends AbstractInputFormController<League> {

	/**
	 * Combobox for sex types.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "sexType", jaxbclass = League.class)
	protected ComboBox<ModelClassExt> cboSexType;

	/**
	 * Clear sex types.
	 */
	@FXML
	private Button btnSexTypeClear;

	/**
	 * Spinner for rank.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "rank", jaxbclass = League.class)
	protected Spinner<Integer> spnRank;

	/**
	 * Checkbox for national leagues.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "national", jaxbclass = League.class)
	protected CheckBox chkNational;

	/**
	 * Results (URL).
	 */
	@FXML
	@JAXBMatch(jaxbfield = "resultsURL", jaxbclass = League.class)
	protected TextField txtResultsURL;

	/**
	 * Referee report (URL).
	 */
	@FXML
	@JAXBMatch(jaxbfield = "refereeReportURL", jaxbclass = League.class)
	protected TextField txtRefereeReportURL;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboSexType, AppModel.getData().getContent().getSexType());

        // setup spinners
        SpinnerUtils.prepareIntegerSpinner(spnRank, 0, 100);

		// enable buttons
        ButtonUtils.bindDisable(btnSexTypeClear, cboSexType);

		// icons
		btnSexTypeClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Clears sex type selection.
	 */
	@FXML
	private void handleSexTypeClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboSexType);
	}

}

/* EOF */

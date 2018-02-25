package de.edgesoft.refereemanager.controller.editdialogs;
import java.util.ArrayList;
import java.util.Arrays;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.EventDate;
import de.edgesoft.refereemanager.jaxb.LeagueGame;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import de.edgesoft.refereemanager.utils.SpinnerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;

/**
 * Controller for the league game edit dialog scene.
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
public class EditDialogLeagueGameController extends AbstractTabbedEditDialogController<LeagueGame> {

	/**
	 * Combobox for league.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "league", jaxbclass = EventDate.class)
	protected ComboBox<ModelClassExt> cboLeague;

	/**
	 * Clear league.
	 */
	@FXML
	private Button btnLeagueClear;

	/**
	 * Spinner for game number.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "gameNumber", jaxbclass = LeagueGame.class)
	protected Spinner<Integer> spnGameNumber;

	/**
	 * Combobox for home team.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "homeTeam", jaxbclass = LeagueGame.class)
	protected ComboBox<ModelClassExt> cboHomeTeam;

	/**
	 * Clear home team.
	 */
	@FXML
	private Button btnHomeTeamClear;

	/**
	 * Combobox for off team.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "offTeam", jaxbclass = LeagueGame.class)
	protected ComboBox<ModelClassExt> cboOffTeam;

	/**
	 * Clear off team.
	 */
	@FXML
	private Button btnOffTeamClear;


//	/**
//	 * Event data.
//	 */
//	@FXML
//	private Parent embeddedInputFormEventData;
//
//	/**
//	 * Event data controller.
//	 */
//	@FXML
//	private IInputFormController embeddedInputFormEventDataController;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {

//		addInputFormController(embeddedInputFormEventDataController);

		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{IDType.class, TitledIDType.class, EventDate.class, LeagueGame.class})));

		super.initialize();


		// to move

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboLeague, AppModel.getData().getContent().getLeague());
        ComboBoxUtils.prepareComboBox(cboHomeTeam, AppModel.getData().getContent().getTeam());
        ComboBoxUtils.prepareComboBox(cboOffTeam, AppModel.getData().getContent().getTeam());

        // setup spinners
        SpinnerUtils.prepareIntegerSpinner(spnGameNumber, 0, 200);
        spnGameNumber.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

		// enable buttons
		btnLeagueClear.disableProperty().bind(
				cboLeague.getSelectionModel().selectedItemProperty().isNull()
		);
		btnHomeTeamClear.disableProperty().bind(
				cboHomeTeam.getSelectionModel().selectedItemProperty().isNull()
		);
		btnOffTeamClear.disableProperty().bind(
				cboOffTeam.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnLeagueClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnHomeTeamClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnOffTeamClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Clears league selection.
	 */
	@FXML
	private void handleLeagueClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboLeague);
	}

	/**
	 * Clears home team selection.
	 */
	@FXML
	private void handleHomeTeamClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboHomeTeam);
	}

	/**
	 * Clears off team selection.
	 */
	@FXML
	private void handleOffTeamClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboOffTeam);
	}

}

/* EOF */
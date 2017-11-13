package de.edgesoft.refereemanager.controller;
import java.util.ArrayList;
import java.util.Arrays;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.Wish;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

/**
 * Controller for the referee edit dialog scene.
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
 * @version 0.14.0
 * @since 0.14.0
 */
public class WishEditDialogController extends AbstractEditDialogController<Wish> {

	/**
	 * Combobox for clubs.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "club", jaxbclass = Wish.class)
	protected ComboBox<ModelClassExt> cboClub;

	/**
	 * Clear clubs.
	 */
	@FXML
	private Button btnClubClear;

	/**
	 * Combobox for leagues.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "league", jaxbclass = Wish.class)
	protected ComboBox<ModelClassExt> cboLeague;

	/**
	 * Clear leagues.
	 */
	@FXML
	private Button btnLeagueClear;

	/**
	 * Combobox for sex types.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "sexType", jaxbclass = Wish.class)
	protected ComboBox<ModelClassExt> cboSexType;

	/**
	 * Clear sex types.
	 */
	@FXML
	private Button btnSexTypeClear;

	/**
	 * Checkbox for saturdays.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "saturday", jaxbclass = Wish.class)
	protected CheckBox chkSaturday;

	/**
	 * Checkbox for sundays.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "sunday", jaxbclass = Wish.class)
	protected CheckBox chkSunday;

	/**
	 * Checkbox for tournaments only.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "tournamentOnly", jaxbclass = Wish.class)
	protected CheckBox chkTournamentOnly;

	/**
	 * Checkbox for league games only.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "leagueGamesOnly", jaxbclass = Wish.class)
	protected CheckBox chkLeagueGamesOnly;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {

		setClasses(new ArrayList<>(Arrays.asList(new Class<?>[]{Wish.class})));
		super.initialize();

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboClub, AppModel.getData().getContent().getClub());
        ComboBoxUtils.prepareComboBox(cboLeague, AppModel.getData().getContent().getLeague());
        ComboBoxUtils.prepareComboBox(cboSexType, AppModel.getData().getContent().getSexType());

		// enable buttons
		btnClubClear.disableProperty().bind(
				cboClub.getSelectionModel().selectedItemProperty().isNull()
		);
		btnLeagueClear.disableProperty().bind(
				cboLeague.getSelectionModel().selectedItemProperty().isNull()
		);
		btnSexTypeClear.disableProperty().bind(
				cboSexType.getSelectionModel().selectedItemProperty().isNull()
		);


		// icons
		btnClubClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnLeagueClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnSexTypeClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Sets data to be edited.
	 *
	 * @param theData data
	 */
	@Override
	public void setData(Wish theData) {

		super.setData(theData);

    }

	/**
	 * Clears club selection.
	 */
	@FXML
	private void handleClubClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboClub);
	}

	/**
	 * Clears league selection.
	 */
	@FXML
	private void handleLeagueClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboLeague);
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

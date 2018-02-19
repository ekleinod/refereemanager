package de.edgesoft.refereemanager.controller.editdialogs;
import java.util.ArrayList;
import java.util.Arrays;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.EventDate;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.jaxb.Tournament;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Controller for the tournament edit dialog scene.
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
public class EditDialogTournamentController extends AbstractTabbedEditDialogController<Tournament> {

	/**
	 * Text field for announcement URL.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "announcementURL", jaxbclass = TitledIDType.class)
	protected TextField txtAnnouncementURL;

	/**
	 * Text field for information URL.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "informationURL", jaxbclass = TitledIDType.class)
	protected TextField txtInformationURL;

	/**
	 * Text field for result URL.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "resultURL", jaxbclass = TitledIDType.class)
	protected TextField txtResultURL;

	/**
	 * Combobox for organizing club.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "organizingClub", jaxbclass = Tournament.class)
	protected ComboBox<ModelClassExt> cboOrganizingClub;

	/**
	 * Clear organizing club.
	 */
	@FXML
	private Button btnOrganizingClubClear;

	/**
	 * Combobox for organizer.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "organizer", jaxbclass = Tournament.class)
	protected ComboBox<ModelClassExt> cboOrganizer;

	/**
	 * Clear organizer.
	 */
	@FXML
	private Button btnOrganizerClear;


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

		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{IDType.class, TitledIDType.class, EventDate.class, Tournament.class})));

		super.initialize();



		// to move

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboOrganizingClub, AppModel.getData().getContent().getClub());
        ComboBoxUtils.prepareComboBox(cboOrganizer, AppModel.getData().getContent().getPerson());

		// enable buttons
		btnOrganizingClubClear.disableProperty().bind(
				cboOrganizingClub.getSelectionModel().selectedItemProperty().isNull()
		);
		btnOrganizerClear.disableProperty().bind(
				cboOrganizer.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnOrganizingClubClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnOrganizerClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Clears organizing club selection.
	 */
	@FXML
	private void handleOrganizingClubClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboOrganizingClub);
	}

	/**
	 * Clears organizer selection.
	 */
	@FXML
	private void handleOrganizerClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboOrganizer);
	}

}

/* EOF */

package de.edgesoft.refereemanager.controller;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import de.edgesoft.edgeutils.ClassUtils;
import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.EventDate;
import de.edgesoft.refereemanager.jaxb.LeagueGame;
import de.edgesoft.refereemanager.jaxb.OtherEvent;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.jaxb.Tournament;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.EventDateModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.JAXBMatchUtils;
import de.edgesoft.refereemanager.utils.Resources;
import de.edgesoft.refereemanager.utils.SpinnerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for the event edit dialog scene.
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
public class EventEditDialogController {

	/**
	 * Classes for introspection when setting/getting values.
	 */
	private Class<?>[] theClasses = new Class<?>[]{IDType.class, TitledIDType.class, EventDate.class, OtherEvent.class, LeagueGame.class, Tournament.class};


	// event date data

	/**
	 * ID text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "id", jaxbclass = IDType.class)
	protected TextField txtID;

	/**
	 * Title text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "title", jaxbclass = TitledIDType.class)
	protected TextField txtTitle;

	/**
	 * Short title text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "shorttitle", jaxbclass = TitledIDType.class)
	protected TextField txtShorttitle;

	/**
	 * Start datetime picker pane.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "start", jaxbclass = EventDate.class)
	protected AnchorPane pneStart;

	/**
	 * End datetime picker pane.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "end", jaxbclass = EventDate.class)
	protected AnchorPane pneEnd;

	/**
	 * Combobox for venue.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "venue", jaxbclass = EventDate.class)
	protected ComboBox<ModelClassExt> cboVenue;

	/**
	 * Clear venue.
	 */
	@FXML
	private Button btnVenueClear;

	/**
	 * Text area for remark.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "remark", jaxbclass = TitledIDType.class)
	protected TextArea txtRemark;



	// league games

	/**
	 * Tab for league games.
	 */
	@FXML
	private Tab tabLeagueGame;

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



	// tournament

	/**
	 * Tab for tournament.
	 */
	@FXML
	private Tab tabTournament;

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



	/**
	 * OK button.
	 */
	@FXML
	private Button btnOK;

	/**
	 * Cancel button.
	 */
	@FXML
	private Button btnCancel;



	/**
	 * Reference to dialog stage.
	 */
	private Stage dialogStage;

	/**
	 * Current event.
	 */
	private EventDateModel currentEvent;

	/**
	 * OK clicked?.
	 */
	private boolean okClicked;

	/**
	 * Fields of class and abstract subclasses.
	 *
	 * @since 0.14.0
	 */
	private List<Field> lstDeclaredFields = null;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// set date/time picker date format

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboVenue, AppModel.getData().getContent().getVenue());
        ComboBoxUtils.prepareComboBox(cboLeague, AppModel.getData().getContent().getLeague());
        ComboBoxUtils.prepareComboBox(cboHomeTeam, AppModel.getData().getContent().getTeam());
        ComboBoxUtils.prepareComboBox(cboOffTeam, AppModel.getData().getContent().getTeam());
        ComboBoxUtils.prepareComboBox(cboOrganizingClub, AppModel.getData().getContent().getClub());
        ComboBoxUtils.prepareComboBox(cboOrganizer, AppModel.getData().getContent().getPerson());

        // setup list views

        // setup spinners
        SpinnerUtils.prepareIntegerSpinner(spnGameNumber, 0, 200);
        spnGameNumber.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

		// declared fields
        lstDeclaredFields = ClassUtils.getDeclaredFieldsFirstAbstraction(getClass());

		// required fields
        for (Field theFXMLField : lstDeclaredFields) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.markRequired(theFXMLField, fieldObject, theClasses);

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

		// enable ok button for valid entries only
//		btnOK.disableProperty().bind(
//		);

		// enable buttons
		btnVenueClear.disableProperty().bind(
				cboVenue.getSelectionModel().selectedItemProperty().isNull()
		);
		btnLeagueClear.disableProperty().bind(
				cboLeague.getSelectionModel().selectedItemProperty().isNull()
		);
		btnHomeTeamClear.disableProperty().bind(
				cboHomeTeam.getSelectionModel().selectedItemProperty().isNull()
		);
		btnOffTeamClear.disableProperty().bind(
				cboOffTeam.getSelectionModel().selectedItemProperty().isNull()
		);
		btnOrganizingClubClear.disableProperty().bind(
				cboOrganizingClub.getSelectionModel().selectedItemProperty().isNull()
		);
		btnOrganizerClear.disableProperty().bind(
				cboOrganizer.getSelectionModel().selectedItemProperty().isNull()
		);


		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

		btnVenueClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnHomeTeamClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnOffTeamClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnOrganizingClubClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnOrganizerClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 */
	public void setDialogStage(final Stage theStage) {
        dialogStage = theStage;
    }

	/**
	 * Sets event to be edited.
	 *
	 * @param theEvent event
	 */
	public void setEvent(EventDateModel theEvent) {

		Objects.requireNonNull(theEvent);

        currentEvent = theEvent;

        if (currentEvent instanceof LeagueGame) {
    		tabLeagueGame.setDisable(false);
        }

        if (currentEvent instanceof Tournament) {
    		tabTournament.setDisable(false);
        }

        // fill fields
        for (Field theFXMLField : lstDeclaredFields) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.setField(theFXMLField, fieldObject, theEvent, theClasses);

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

    }

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 */
	@FXML
    private void handleOk() {

        for (Field theFXMLField : lstDeclaredFields) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.getField(theFXMLField, fieldObject, currentEvent, theClasses);

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

        okClicked = true;
        dialogStage.close();

    }

	/**
	 * Returns if user clicked ok.
	 *
	 * @return did user click ok?
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Stores non-ok click and closes dialog.
	 */
	@FXML
    private void handleCancel() {
		okClicked = false;
        dialogStage.close();
    }


	/**
	 * Clears venue selection.
	 */
	@FXML
	private void handleVenueClear() {
		cboVenue.getSelectionModel().clearSelection();
		cboVenue.setValue(null);
	}

	/**
	 * Clears league selection.
	 */
	@FXML
	private void handleLeagueClear() {
		cboLeague.getSelectionModel().clearSelection();
		cboLeague.setValue(null);
	}

	/**
	 * Clears home team selection.
	 */
	@FXML
	private void handleHomeTeamClear() {
		cboHomeTeam.getSelectionModel().clearSelection();
		cboHomeTeam.setValue(null);
	}

	/**
	 * Clears off team selection.
	 */
	@FXML
	private void handleOffTeamClear() {
		cboOffTeam.getSelectionModel().clearSelection();
		cboOffTeam.setValue(null);
	}

	/**
	 * Clears organizing club selection.
	 */
	@FXML
	private void handleOrganizingClubClear() {
		cboOrganizingClub.getSelectionModel().clearSelection();
		cboOrganizingClub.setValue(null);
	}

	/**
	 * Clears organizer selection.
	 */
	@FXML
	private void handleOrganizerClear() {
		cboOrganizer.getSelectionModel().clearSelection();
		cboOrganizer.setValue(null);
	}

}

/* EOF */

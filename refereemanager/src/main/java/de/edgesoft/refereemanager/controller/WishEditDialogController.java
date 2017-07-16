package de.edgesoft.refereemanager.controller;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import de.edgesoft.edgeutils.ClassUtils;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.Wish;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.WishModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.JAXBMatchUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
public class WishEditDialogController {

	/**
	 * Classes for introspection when setting/getting values.
	 */
	private Class<?>[] theClasses = new Class<?>[]{Wish.class};


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
	 * Current wish.
	 */
	private WishModel currentWish;

	/**
	 * OK clicked?.
	 */
	private boolean okClicked;

	/**
	 * Fields of class and abstract subclasses.
	 */
	private List<Field> lstDeclaredFields = null;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboClub, AppModel.getData().getContent().getClub());
        ComboBoxUtils.prepareComboBox(cboLeague, AppModel.getData().getContent().getLeague());
        ComboBoxUtils.prepareComboBox(cboSexType, AppModel.getData().getContent().getSexType());

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
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

		btnClubClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnLeagueClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnSexTypeClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

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
	 * Sets wish to be edited.
	 *
	 * @param theWish wish
	 */
	public void setWish(WishModel theWish) {

		Objects.requireNonNull(theWish);

        currentWish = theWish;

        // fill fields
        for (Field theFXMLField : lstDeclaredFields) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.setField(theFXMLField, fieldObject, theWish, theClasses);

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

        		JAXBMatchUtils.getField(theFXMLField, fieldObject, currentWish, theClasses);

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
	 * Clears club selection.
	 */
	@FXML
	private void handleClubClear() {
		cboClub.getSelectionModel().clearSelection();
		cboClub.setValue(null);
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
	 * Clears sex type selection.
	 */
	@FXML
	private void handleSexTypeClear() {
		cboSexType.getSelectionModel().clearSelection();
		cboSexType.setValue(null);
	}

}

/* EOF */

package de.edgesoft.refereemanager.controller;
import java.lang.reflect.Field;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.JAXBMatchUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
 * @since 0.13.0
 */
public class RefereeEditDialogController {

	/**
	 * ID text field.
	 *
	 * @version 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "id", jaxbclass = IDType.class)
	private TextField txtID;

	/**
	 * Title text field.
	 *
	 * @version 0.13.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "title", jaxbclass = TitledIDType.class)
	private TextField txtTitle;

	/**
	 * First name text field.
	 *
	 * @version 0.13.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "firstName", jaxbclass = Person.class)
	private TextField txtFirstName;

	/**
	 * Name text field label.
	 *
	 * @version 0.13.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "name", jaxbclass = Person.class)
	private Label lblName;

	/**
	 * Name text field.
	 *
	 * @version 0.13.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "name", jaxbclass = Person.class)
	private TextField txtName;

	/**
	 * Birthday picker.
	 *
	 * @version 0.13.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "birthday", jaxbclass = Person.class)
	private DatePicker pckBirthday;

	/**
	 * Day of death picker.
	 *
	 * @version 0.13.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "dayOfDeath", jaxbclass = Person.class)
	private DatePicker pckDayOfDeath;

	/**
	 * Combobox for sex types.
	 *
	 * @version 0.13.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "sexType", jaxbclass = Person.class)
	private ComboBox<ModelClassExt> cboSexType;

	/**
	 * Text area for remark.
	 *
	 * @version 0.13.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "remark", jaxbclass = TitledIDType.class)
	private TextArea txtRemark;


	/**
	 * OK button.
	 *
	 * @version 0.13.0
	 */
	@FXML
	private Button btnOK;

	/**
	 * Cancel button.
	 *
	 * @version 0.13.0
	 */
	@FXML
	private Button btnCancel;

	/**
	 * Reference to dialog stage.
	 *
	 * @version 0.13.0
	 */
	private Stage dialogStage;

	/**
	 * Current event.
	 *
	 * @version 0.13.0
	 */
	private Referee currentReferee;

	/**
	 * OK clicked?.
	 *
	 * @version 0.13.0
	 */
	private boolean okClicked;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.14.0
	 */
	@FXML
	private void initialize() {

		// set date picker date format
		pckBirthday.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));
        pckDayOfDeath.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

		// fill sex types
        ComboBoxUtils.prepareComboBox(cboSexType, AppModel.getData().getContent().getSexType());

		// required fields
        for (Field theFXMLField : getClass().getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.markRequired(theFXMLField, fieldObject, IDType.class, TitledIDType.class, Person.class);

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

		// enable ok button for valid entries only
		btnOK.disableProperty().bind(
				txtName.textProperty().isEmpty()
		);

		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

	}

	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 *
	 * @version 0.13.0
	 */
	public void setDialogStage(final Stage theStage) {
        dialogStage = theStage;
    }

	/**
	 * Sets referee to be edited.
	 *
	 * @param thePerson referee
	 *
	 * @version 0.13.0
	 */
	public void setPerson(PersonModel thePerson) {

		Objects.requireNonNull(thePerson);

        currentReferee = (Referee) thePerson;

        for (Field theFXMLField : getClass().getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.setField(theFXMLField, fieldObject, thePerson, IDType.class, TitledIDType.class, Person.class);

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

    }

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 *
	 * @version 0.13.0
	 */
	@FXML
    private void handleOk() {

        for (Field theFXMLField : getClass().getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.getField(theFXMLField, fieldObject, currentReferee, IDType.class, TitledIDType.class, Person.class);

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
	 *
	 * @version 0.13.0
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Stores non-ok click and closes dialog.
	 *
	 * @version 0.13.0
	 */
	@FXML
    private void handleCancel() {
		okClicked = false;
        dialogStage.close();
    }

}

/* EOF */

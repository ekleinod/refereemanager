package de.edgesoft.refereemanager.controller;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.model.AddressModel;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContactModel;
import de.edgesoft.refereemanager.model.EMailModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.PhoneNumberModel;
import de.edgesoft.refereemanager.model.URLModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.JAXBMatchUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
	 * Classes for introspection when setting/getting values.
	 *
	 * @since 0.14.0
	 */
	private final Class<?>[] theClasses = new Class<?>[]{IDType.class, TitledIDType.class, Person.class};

	/**
	 * ID text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "id", jaxbclass = IDType.class)
	private TextField txtID;

	/**
	 * Title text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "title", jaxbclass = TitledIDType.class)
	private TextField txtTitle;

	/**
	 * First name text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "firstName", jaxbclass = Person.class)
	private TextField txtFirstName;

	/**
	 * Name text field label.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "name", jaxbclass = Person.class)
	private Label lblName;

	/**
	 * Name text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "name", jaxbclass = Person.class)
	private TextField txtName;

	/**
	 * Birthday picker.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "birthday", jaxbclass = Person.class)
	private DatePicker pckBirthday;

	/**
	 * Day of death picker.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "dayOfDeath", jaxbclass = Person.class)
	private DatePicker pckDayOfDeath;

	/**
	 * Combobox for sex types.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "sexType", jaxbclass = Person.class)
	private ComboBox<ModelClassExt> cboSexType;

	/**
	 * Text area for remark.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "remark", jaxbclass = TitledIDType.class)
	private TextArea txtRemark;


	/**
	 * List view for emails.
	 *
	 * @since 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "eMail", jaxbclass = Person.class)
	private ListView<ModelClassExt> lstEMail;

	/**
	 * Add email.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnEMailAdd;

	/**
	 * Edit email.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnEMailEdit;

	/**
	 * Delete email.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnEMailDelete;


	/**
	 * List view for phone numbers.
	 *
	 * @since 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "phoneNumber", jaxbclass = Person.class)
	private ListView<ModelClassExt> lstPhoneNumber;

	/**
	 * Add phone number.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnPhoneNumberAdd;

	/**
	 * Edit phone number.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnPhoneNumberEdit;

	/**
	 * Delete phone number.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnPhoneNumberDelete;


	/**
	 * List view for addresses.
	 *
	 * @since 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "address", jaxbclass = Person.class)
	private ListView<ModelClassExt> lstAddress;

	/**
	 * Add address.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnAddressAdd;

	/**
	 * Edit address.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnAddressEdit;

	/**
	 * Delete address.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnAddressDelete;


	/**
	 * List view for URLs.
	 *
	 * @since 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "URL", jaxbclass = Person.class)
	private ListView<ModelClassExt> lstURL;

	/**
	 * Add URL.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnURLAdd;

	/**
	 * Edit URL.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnURLEdit;

	/**
	 * Delete URL.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnURLDelete;


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
	private Referee currentReferee;

	/**
	 * OK clicked?.
	 */
	private boolean okClicked;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// set date picker date format
		pckBirthday.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));
        pckDayOfDeath.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

		// fill sex types
        ComboBoxUtils.prepareComboBox(cboSexType, AppModel.getData().getContent().getSexType());

        // setup list views
        lstEMail.setCellFactory(ComboBoxUtils.getCallback());
        lstPhoneNumber.setCellFactory(ComboBoxUtils.getCallback());
        lstAddress.setCellFactory(ComboBoxUtils.getCallback());
        lstURL.setCellFactory(ComboBoxUtils.getCallback());

		// required fields
        for (Field theFXMLField : getClass().getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.markRequired(theFXMLField, fieldObject, theClasses);

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

		// enable ok button for valid entries only
		btnOK.disableProperty().bind(
				txtName.textProperty().isEmpty()
		);

		// enable buttons
		btnEMailEdit.disableProperty().bind(
				lstEMail.getSelectionModel().selectedItemProperty().isNull()
		);
		btnEMailDelete.disableProperty().bind(
				lstEMail.getSelectionModel().selectedItemProperty().isNull()
		);
		btnPhoneNumberEdit.disableProperty().bind(
				lstPhoneNumber.getSelectionModel().selectedItemProperty().isNull()
		);
		btnPhoneNumberDelete.disableProperty().bind(
				lstPhoneNumber.getSelectionModel().selectedItemProperty().isNull()
		);
		btnAddressEdit.disableProperty().bind(
				lstAddress.getSelectionModel().selectedItemProperty().isNull()
		);
		btnAddressDelete.disableProperty().bind(
				lstAddress.getSelectionModel().selectedItemProperty().isNull()
		);
		btnURLEdit.disableProperty().bind(
				lstURL.getSelectionModel().selectedItemProperty().isNull()
		);
		btnURLDelete.disableProperty().bind(
				lstURL.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

		btnEMailAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnPhoneNumberAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnAddressAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnURLAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));

		btnEMailEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnPhoneNumberEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnAddressEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnURLEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));

		btnEMailDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnPhoneNumberDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnAddressDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnURLDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

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
	 * Sets referee to be edited.
	 *
	 * @param thePerson referee
	 */
	public void setPerson(PersonModel thePerson) {

		Objects.requireNonNull(thePerson);

        currentReferee = (Referee) thePerson;

        for (Field theFXMLField : getClass().getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.setField(theFXMLField, fieldObject, thePerson, theClasses);

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

        for (Field theFXMLField : getClass().getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.getField(theFXMLField, fieldObject, currentReferee, theClasses);

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
	 * Opens edit dialog for new data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleEMailAdd() {
		addContact(lstEMail, new EMailModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleEMailEdit() {
		editContact(lstEMail);
	}

	/**
	 * Deletes selected data from list.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleEMailDelete() {
		deleteContact(lstEMail);
	}


	/**
	 * Opens edit dialog for new data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handlePhoneNumberAdd() {
		addContact(lstPhoneNumber, new PhoneNumberModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handlePhoneNumberEdit() {
		editContact(lstPhoneNumber);
	}

	/**
	 * Deletes selected data from list.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handlePhoneNumberDelete() {
		deleteContact(lstPhoneNumber);
	}


	/**
	 * Opens edit dialog for new data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleAddressAdd() {
		addContact(lstAddress, new AddressModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleAddressEdit() {
		editContact(lstAddress);
	}

	/**
	 * Deletes selected data from list.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleAddressDelete() {
		deleteContact(lstAddress);
	}


	/**
	 * Opens edit dialog for new data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleURLAdd() {
		addContact(lstURL, new URLModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleURLEdit() {
		editContact(lstURL);
	}

	/**
	 * Deletes selected data from list.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleURLDelete() {
		deleteContact(lstURL);
	}


	/**
	 * Opens edit dialog for new data.
	 *
	 * @param theListView list view
	 * @param newContact new contact
	 *
	 * @since 0.14.0
	 */
	private void addContact(ListView<ModelClassExt> theListView, ContactModel newContact) {

		if (showContactEditDialog(newContact)) {
			theListView.getItems().add(newContact);
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @param theListView list view
	 *
	 * @since 0.14.0
	 */
	private void editContact(ListView<ModelClassExt> theListView) {

		if (!theListView.getSelectionModel().isEmpty()) {
			showContactEditDialog((ContactModel) theListView.getSelectionModel().getSelectedItem());
		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @param theListView list view
	 *
	 * @since 0.14.0
	 */
	private void deleteContact(ListView<ModelClassExt> theListView) {

		if (!theListView.getSelectionModel().isEmpty()) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, dialogStage,
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", theListView.getSelectionModel().getSelectedItem().getDisplayText().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						theListView.getItems().remove(theListView.getSelectionModel().getSelectedItem());
			});

		}

	}

	/**
	 * Opens the contact edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theContact the contact to be edited
	 * @return true if the user clicked OK, false otherwise.
	 *
	 * @since 0.14.0
	 */
	private boolean showContactEditDialog(ContactModel theContact) {

		Objects.requireNonNull(theContact);

		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane(String.format("%sEditDialog", theContact.getClass().getSimpleName().replace("Model", "")));

		AnchorPane editDialog = (AnchorPane) pneLoad.getKey();

		// Create the dialog Stage.
		Stage editDialogStage = new Stage();
		editDialogStage.initModality(Modality.WINDOW_MODAL);
		editDialogStage.initOwner(dialogStage);
		editDialogStage.setTitle("Kontakt editieren");

		Scene scene = new Scene(editDialog);
		editDialogStage.setScene(scene);

		// Set the referee
		AbstractContactEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(editDialogStage);
		editController.setContact(theContact);

		// Show the dialog and wait until the user closes it
		editDialogStage.showAndWait();

		return editController.isOkClicked();

	}

}

/* EOF */

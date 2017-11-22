package de.edgesoft.refereemanager.controller.inputforms;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.ButtonUtils;
import de.edgesoft.refereemanager.controller.AbstractContactEditDialogController;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.model.AddressModel;
import de.edgesoft.refereemanager.model.ContactModel;
import de.edgesoft.refereemanager.model.EMailModel;
import de.edgesoft.refereemanager.model.PhoneNumberModel;
import de.edgesoft.refereemanager.model.URLModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the person data edit dialog tab.
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
public class InputFormContactDataController extends AbstractInputFormController {

	/**
	 * List view for emails.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "eMail", jaxbclass = Person.class)
	protected ListView<ModelClassExt> lstEMail;

	/**
	 * Add email.
	 */
	@FXML
	private Button btnEMailAdd;

	/**
	 * Edit email.
	 */
	@FXML
	private Button btnEMailEdit;

	/**
	 * Delete email.
	 */
	@FXML
	private Button btnEMailDelete;


	/**
	 * List view for phone numbers.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "phoneNumber", jaxbclass = Person.class)
	protected ListView<ModelClassExt> lstPhoneNumber;

	/**
	 * Add phone number.
	 */
	@FXML
	private Button btnPhoneNumberAdd;

	/**
	 * Edit phone number.
	 */
	@FXML
	private Button btnPhoneNumberEdit;

	/**
	 * Delete phone number.
	 */
	@FXML
	private Button btnPhoneNumberDelete;


	/**
	 * List view for addresses.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "address", jaxbclass = Person.class)
	protected ListView<ModelClassExt> lstAddress;

	/**
	 * Add address.
	 */
	@FXML
	private Button btnAddressAdd;

	/**
	 * Edit address.
	 */
	@FXML
	private Button btnAddressEdit;

	/**
	 * Delete address.
	 */
	@FXML
	private Button btnAddressDelete;


	/**
	 * List view for URLs.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "URL", jaxbclass = Person.class)
	protected ListView<ModelClassExt> lstURL;

	/**
	 * Add URL.
	 */
	@FXML
	private Button btnURLAdd;

	/**
	 * Edit URL.
	 */
	@FXML
	private Button btnURLEdit;

	/**
	 * Delete URL.
	 */
	@FXML
	private Button btnURLDelete;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

        // setup list views
        lstEMail.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstPhoneNumber.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstAddress.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstURL.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());

		// enable buttons
        ButtonUtils.bindDisable(btnEMailEdit, lstEMail);
        ButtonUtils.bindDisable(btnEMailDelete, lstEMail);

        ButtonUtils.bindDisable(btnPhoneNumberEdit, lstPhoneNumber);
        ButtonUtils.bindDisable(btnPhoneNumberDelete, lstPhoneNumber);

        ButtonUtils.bindDisable(btnAddressEdit, lstAddress);
        ButtonUtils.bindDisable(btnAddressDelete, lstAddress);

        ButtonUtils.bindDisable(btnURLEdit, lstURL);
        ButtonUtils.bindDisable(btnURLDelete, lstURL);

		// icons
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
	 * Opens edit dialog for new data.
	 */
	@FXML
	private void handleEMailAdd() {
		addContact(lstEMail, new EMailModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@FXML
	private void handleEMailEdit() {
		editContact(lstEMail);
	}

	/**
	 * Deletes selected data from list.
	 */
	@FXML
	private void handleEMailDelete() {
		deleteContact(lstEMail);
	}


	/**
	 * Opens edit dialog for new data.
	 */
	@FXML
	private void handlePhoneNumberAdd() {
		addContact(lstPhoneNumber, new PhoneNumberModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@FXML
	private void handlePhoneNumberEdit() {
		editContact(lstPhoneNumber);
	}

	/**
	 * Deletes selected data from list.
	 */
	@FXML
	private void handlePhoneNumberDelete() {
		deleteContact(lstPhoneNumber);
	}


	/**
	 * Opens edit dialog for new data.
	 */
	@FXML
	private void handleAddressAdd() {
		addContact(lstAddress, new AddressModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@FXML
	private void handleAddressEdit() {
		editContact(lstAddress);
	}

	/**
	 * Deletes selected data from list.
	 */
	@FXML
	private void handleAddressDelete() {
		deleteContact(lstAddress);
	}


	/**
	 * Opens edit dialog for new data.
	 */
	@FXML
	private void handleURLAdd() {
		addContact(lstURL, new URLModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@FXML
	private void handleURLEdit() {
		editContact(lstURL);
	}

	/**
	 * Deletes selected data from list.
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
	 */
	private void editContact(ListView<ModelClassExt> theListView) {

		if (!theListView.getSelectionModel().isEmpty()) {
			showContactEditDialog((ContactModel) theListView.getSelectionModel().getSelectedItem());
			theListView.refresh();
		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @param theListView list view
	 */
	private void deleteContact(ListView<ModelClassExt> theListView) {

		if (!theListView.getSelectionModel().isEmpty()) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, getDialogStage(),
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
	 */
	private boolean showContactEditDialog(ContactModel theContact) {

		Objects.requireNonNull(theContact);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode(String.format("%sEditDialog", theContact.getClass().getSimpleName().replace("Model", "")));

		// Create the dialog Stage.
		Stage editDialogStage = new Stage();
		editDialogStage.initModality(Modality.WINDOW_MODAL);
		editDialogStage.initOwner(getDialogStage());
		editDialogStage.setTitle("Kontakt editieren");

		editDialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set the referee
		AbstractContactEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(editDialogStage);
		editController.setData(theContact);

		// Show the dialog and wait until the user closes it
		editDialogStage.showAndWait();

		return editController.isOkClicked();

	}

}

/* EOF */

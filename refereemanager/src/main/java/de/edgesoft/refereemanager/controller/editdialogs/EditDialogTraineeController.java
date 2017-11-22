package de.edgesoft.refereemanager.controller.editdialogs;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.controller.AbstractContactEditDialogController;
import de.edgesoft.refereemanager.controller.TrainingLevelEditDialogController;
import de.edgesoft.refereemanager.controller.WishEditDialogController;
import de.edgesoft.refereemanager.controller.inputforms.IInputFormController;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.jaxb.Trainee;
import de.edgesoft.refereemanager.model.AddressModel;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContactModel;
import de.edgesoft.refereemanager.model.EMailModel;
import de.edgesoft.refereemanager.model.PhoneNumberModel;
import de.edgesoft.refereemanager.model.TrainingLevelModel;
import de.edgesoft.refereemanager.model.URLModel;
import de.edgesoft.refereemanager.model.WishModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import de.edgesoft.refereemanager.utils.SpinnerUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
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
public class EditDialogTraineeController extends AbstractTabbedEditDialogController {

	/**
	 * Person data.
	 */
	@FXML
	private Parent embeddedInputFormPersonData;

	/**
	 * Person data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormPersonDataController;


	// contact data

	/**
	 * List view for emails.
	 *
	 * @since 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "eMail", jaxbclass = Person.class)
	protected ListView<ModelClassExt> lstEMail;

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
	protected ListView<ModelClassExt> lstPhoneNumber;

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
	protected ListView<ModelClassExt> lstAddress;

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
	protected ListView<ModelClassExt> lstURL;

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


	// referee data

	/**
	 * Tab for referee data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	protected Tab tabRefereeData;

	/**
	 * Combobox for member clubs.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "member", jaxbclass = Referee.class)
	protected ComboBox<ModelClassExt> cboMember;

	/**
	 * Clear member clubs.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnMemberClear;

	/**
	 * Combobox for reffor clubs.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "reffor", jaxbclass = Referee.class)
	protected ComboBox<ModelClassExt> cboReffor;

	/**
	 * Clear reffor clubs.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnRefforClear;

	/**
	 * Combobox for status.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "status", jaxbclass = Referee.class)
	protected ComboBox<ModelClassExt> cboStatus;

	/**
	 * Clear status.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnStatusClear;

	/**
	 * Checkbox for docs by letter.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "docsByLetter", jaxbclass = Referee.class)
	protected CheckBox chkDocsByLetter;



	// wishes

	/**
	 * Tab for referee wishes.
	 *
	 * @since 0.14.0
	 */
	@FXML
	protected Tab tabRefereeWishes;

	/**
	 * List view for prefers.
	 *
	 * @since 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "prefer", jaxbclass = Referee.class)
	protected ListView<ModelClassExt> lstPrefer;

	/**
	 * Add prefer.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnPreferAdd;

	/**
	 * Edit prefer.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnPreferEdit;

	/**
	 * Delete prefer.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnPreferDelete;


	/**
	 * List view for avoids.
	 *
	 * @since 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "avoid", jaxbclass = Referee.class)
	protected ListView<ModelClassExt> lstAvoid;

	/**
	 * Add avoid.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnAvoidAdd;

	/**
	 * Edit avoid.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnAvoidEdit;

	/**
	 * Delete avoid.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnAvoidDelete;



	// training levels

	/**
	 * Tab for referee training levels.
	 *
	 * @since 0.14.0
	 */
	@FXML
	protected Tab tabRefereeTrainingLevel;

	/**
	 * List view for training level.
	 *
	 * @since 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "trainingLevel", jaxbclass = Referee.class)
	protected ListView<ModelClassExt> lstTrainingLevel;

	/**
	 * Add training level.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnTrainingLevelAdd;

	/**
	 * Edit training level.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnTrainingLevelEdit;

	/**
	 * Delete training level.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnTrainingLevelDelete;



	// exam

	/**
	 * Tab for trainee exams.
	 *
	 * @since 0.14.0
	 */
	@FXML
	protected Tab tabTraineeExam;

	/**
	 * Checkbox for withdrawn.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "withdrawn", jaxbclass = Trainee.class)
	protected CheckBox chkWithdrawn;

	/**
	 * Checkbox for did not start.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "didNotStart", jaxbclass = Trainee.class)
	protected CheckBox chkDidNotStart;

	/**
	 * Picker for exam date.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "examDate", jaxbclass = Trainee.class)
	protected DatePicker pckExamDate;

	/**
	 * Spinner for points written A.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "pointsWrittenA", jaxbclass = Trainee.class)
	protected Spinner<Integer> spnPointsWrittenA;

	/**
	 * Spinner for points written B.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "pointsWrittenB", jaxbclass = Trainee.class)
	protected Spinner<Integer> spnPointsWrittenB;

	/**
	 * Checkbox for written passed.
	 */
	@FXML
	protected CheckBox chkPassedWritten;

	/**
	 * Spinner for points practical.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "pointsPractical", jaxbclass = Trainee.class)
	protected Spinner<Integer> spnPointsPractical;

	/**
	 * Checkbox for practical passed.
	 */
	@FXML
	protected CheckBox chkPassedPractical;

	/**
	 * Spinner for points oral.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "pointsOral", jaxbclass = Trainee.class)
	protected Spinner<Integer> spnPointsOral;

	/**
	 * Checkbox for oral passed.
	 */
	@FXML
	protected CheckBox chkPassedOral;

	/**
	 * Checkbox for passed.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "passed", jaxbclass = Trainee.class)
	protected CheckBox chkPassed;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {

		addInputFormController(embeddedInputFormPersonDataController);

		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{IDType.class, TitledIDType.class, Person.class, Referee.class, Trainee.class})));

		super.initialize();

		// set date picker date format
        pckExamDate.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboMember, AppModel.getData().getContent().getClub());
        ComboBoxUtils.prepareComboBox(cboReffor, AppModel.getData().getContent().getClub());
        ComboBoxUtils.prepareComboBox(cboStatus, AppModel.getData().getContent().getStatusType());

        // setup list views
        lstEMail.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstPhoneNumber.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstAddress.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstURL.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstPrefer.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstAvoid.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstTrainingLevel.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());

        // setup spinners
        SpinnerUtils.prepareIntegerSpinner(spnPointsWrittenA, 0, AppModel.getData().getContent().getExam().getMaxPointsWrittenA().getValue());
        spnPointsWrittenA.valueProperty().addListener((observable, oldValue, newValue) -> computeExam());
        SpinnerUtils.prepareIntegerSpinner(spnPointsWrittenB, 0, AppModel.getData().getContent().getExam().getMaxPointsWrittenB().getValue());
        spnPointsWrittenB.valueProperty().addListener((observable, oldValue, newValue) -> computeExam());
        SpinnerUtils.prepareIntegerSpinner(spnPointsPractical, 0, AppModel.getData().getContent().getExam().getMaxPointsPractical().getValue());
        spnPointsPractical.valueProperty().addListener((observable, oldValue, newValue) -> computeExam());
        SpinnerUtils.prepareIntegerSpinner(spnPointsOral, 0, AppModel.getData().getContent().getExam().getMaxPointsOral().getValue());
        spnPointsOral.valueProperty().addListener((observable, oldValue, newValue) -> computeExam());

        spnPointsWrittenA.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spnPointsWrittenB.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spnPointsPractical.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spnPointsOral.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

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
		btnPreferEdit.disableProperty().bind(
				lstPrefer.getSelectionModel().selectedItemProperty().isNull()
		);
		btnPreferDelete.disableProperty().bind(
				lstPrefer.getSelectionModel().selectedItemProperty().isNull()
		);
		btnAvoidEdit.disableProperty().bind(
				lstAvoid.getSelectionModel().selectedItemProperty().isNull()
		);
		btnAvoidDelete.disableProperty().bind(
				lstAvoid.getSelectionModel().selectedItemProperty().isNull()
		);
		btnTrainingLevelEdit.disableProperty().bind(
				lstTrainingLevel.getSelectionModel().selectedItemProperty().isNull()
		);
		btnTrainingLevelDelete.disableProperty().bind(
				lstTrainingLevel.getSelectionModel().selectedItemProperty().isNull()
		);

		btnMemberClear.disableProperty().bind(
				cboMember.getSelectionModel().selectedItemProperty().isNull()
		);
		btnRefforClear.disableProperty().bind(
				cboReffor.getSelectionModel().selectedItemProperty().isNull()
		);
		btnStatusClear.disableProperty().bind(
				cboStatus.getSelectionModel().selectedItemProperty().isNull()
		);

		// enable spinners
		spnPointsWrittenA.disableProperty().bind(
				pckExamDate.valueProperty().isNull()
		);
		spnPointsWrittenB.disableProperty().bind(
				pckExamDate.valueProperty().isNull()
		);
		spnPointsPractical.disableProperty().bind(
				pckExamDate.valueProperty().isNull()
		);
		spnPointsOral.disableProperty().bind(
				pckExamDate.valueProperty().isNull()
		);


		// icons
		btnEMailAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnPhoneNumberAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnAddressAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnURLAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnPreferAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnAvoidAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnTrainingLevelAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));

		btnEMailEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnPhoneNumberEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnAddressEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnURLEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnPreferEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnAvoidEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnTrainingLevelEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));

		btnEMailDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnPhoneNumberDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnAddressDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnURLDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnPreferDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnAvoidDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnTrainingLevelDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

		btnMemberClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnRefforClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnStatusClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Fills form with data to be edited.
	 *
	 * @param theData data object
	 */
	@Override
	public <U extends ModelClassExt> void fillForm(final U theData) {

		super.fillForm(theData);
		computeExam();

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
			theListView.refresh();
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
	 *
	 * @since 0.14.0
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

	/**
	 * Opens edit dialog for new data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handlePreferAdd() {
		addWish(lstPrefer, new WishModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handlePreferEdit() {
		editWish(lstPrefer);
	}

	/**
	 * Deletes selected data from list.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handlePreferDelete() {
		deleteWish(lstPrefer);
	}

	/**
	 * Opens edit dialog for new data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleAvoidAdd() {
		addWish(lstAvoid, new WishModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleAvoidEdit() {
		editWish(lstAvoid);
	}

	/**
	 * Deletes selected data from list.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleAvoidDelete() {
		deleteWish(lstAvoid);
	}

	/**
	 * Opens edit dialog for new data.
	 *
	 * @param theListView list view
	 * @param newWish new wish
	 *
	 * @since 0.14.0
	 */
	private void addWish(ListView<ModelClassExt> theListView, WishModel newWish) {

		if (showWishEditDialog(newWish)) {
			theListView.getItems().add(newWish);
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @param theListView list view
	 *
	 * @since 0.14.0
	 */
	private void editWish(ListView<ModelClassExt> theListView) {

		if (!theListView.getSelectionModel().isEmpty()) {
			showWishEditDialog((WishModel) theListView.getSelectionModel().getSelectedItem());
			theListView.refresh();
		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @param theListView list view
	 *
	 * @since 0.14.0
	 */
	private void deleteWish(ListView<ModelClassExt> theListView) {

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
	 * Opens the wish edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theWish the wish to be edited
	 * @return true if the user clicked OK, false otherwise.
	 *
	 * @since 0.14.0
	 */
	private boolean showWishEditDialog(WishModel theWish) {

		Objects.requireNonNull(theWish);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("WishEditDialog");

		// Create the dialog Stage.
		Stage editDialogStage = new Stage();
		editDialogStage.initModality(Modality.WINDOW_MODAL);
		editDialogStage.initOwner(getDialogStage());
		editDialogStage.setTitle("Wunsch editieren");

		editDialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set the referee
		WishEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(editDialogStage);
		editController.setData(theWish);

		// Show the dialog and wait until the user closes it
		editDialogStage.showAndWait();

		return editController.isOkClicked();

	}

	/**
	 * Clears member selection.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleMemberClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboMember);
	}

	/**
	 * Clears reffor selection.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleRefforClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboReffor);
	}

	/**
	 * Clears status selection.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleStatusClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboStatus);
	}

	/**
	 * Opens edit dialog for new data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleTrainingLevelAdd() {

		TrainingLevelModel newTrainingLevel = new TrainingLevelModel();
		if (showTrainingLevelEditDialog(newTrainingLevel)) {
			lstTrainingLevel.getItems().add(newTrainingLevel);
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleTrainingLevelEdit() {

		if (!lstTrainingLevel.getSelectionModel().isEmpty()) {
			showTrainingLevelEditDialog((TrainingLevelModel) lstTrainingLevel.getSelectionModel().getSelectedItem());
		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleTrainingLevelDelete() {

		if (!lstTrainingLevel.getSelectionModel().isEmpty()) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, getDialogStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", lstTrainingLevel.getSelectionModel().getSelectedItem().getDisplayText().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						lstTrainingLevel.getItems().remove(lstTrainingLevel.getSelectionModel().getSelectedItem());
			});

		}

	}

	/**
	 * Opens the training level edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theTrainingLevel the training level to be edited
	 * @return true if the user clicked OK, false otherwise.
	 *
	 * @since 0.14.0
	 */
	private boolean showTrainingLevelEditDialog(TrainingLevelModel theTrainingLevel) {

		Objects.requireNonNull(theTrainingLevel);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("TrainingLevelEditDialog");

		// Create the dialog Stage.
		Stage editDialogStage = new Stage();
		editDialogStage.initModality(Modality.WINDOW_MODAL);
		editDialogStage.initOwner(getDialogStage());
		editDialogStage.setTitle("Ausbildungsstufe editieren");

		editDialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set the referee
		TrainingLevelEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(editDialogStage);
		editController.setData(theTrainingLevel);

		// Show the dialog and wait until the user closes it
		editDialogStage.showAndWait();

		return editController.isOkClicked();

	}

	/**
	 * Computes if exam is passed.
	 *
	 * @since 0.14.0
	 */
	private void computeExam() {

		int iPointsWritten = spnPointsWrittenA.getValue() + spnPointsWrittenB.getValue();
		int iPointsPractical = spnPointsPractical.getValue();
		int iPointsOral = spnPointsOral.getValue();
		int iPointsSum = iPointsWritten + iPointsPractical + iPointsOral;

		int iNeededWritten = AppModel.getData().getContent().getExam().getNeededPointsWritten().getValue();
		int iNeededPractical = AppModel.getData().getContent().getExam().getNeededPointsPractical().getValue();
		int iNeededOral = AppModel.getData().getContent().getExam().getNeededPointsOral().getValue();
		int iNeededSum = AppModel.getData().getContent().getExam().getNeededPoints().getValue();

		chkPassedWritten.setSelected(
				(iPointsWritten >= iNeededWritten)
		);

		chkPassedPractical.setSelected(
				(iPointsPractical >= iNeededPractical)
		);

		chkPassedOral.setSelected(
				(iPointsOral >= iNeededOral)
		);

		chkPassed.setSelected(
				(iPointsWritten >= iNeededWritten) &&
				(iPointsPractical >= iNeededPractical) &&
				(iPointsOral >= iNeededOral) &&
				(iPointsSum >= iNeededSum)
		);

	}

}

/* EOF */

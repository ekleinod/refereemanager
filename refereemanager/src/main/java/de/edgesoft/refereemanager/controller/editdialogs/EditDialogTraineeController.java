package de.edgesoft.refereemanager.controller.editdialogs;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.edgeutils.javafx.ButtonUtils;
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

	/**
	 * Contact data.
	 */
	@FXML
	private Parent embeddedInputFormContactData;

	/**
	 * Contact data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormContactDataController;


	// referee data


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
		addInputFormController(embeddedInputFormContactDataController);

		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{IDType.class, TitledIDType.class, Person.class, Referee.class, Trainee.class})));

		super.initialize();

		// set date picker date format
        pckExamDate.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

        // setup list views
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
        ButtonUtils.bindDisable(btnPreferEdit, lstPrefer);
        ButtonUtils.bindDisable(btnPreferDelete, lstPrefer);

        ButtonUtils.bindDisable(btnAvoidEdit, lstAvoid);
        ButtonUtils.bindDisable(btnAvoidDelete, lstAvoid);

        ButtonUtils.bindDisable(btnTrainingLevelEdit, lstTrainingLevel);
        ButtonUtils.bindDisable(btnTrainingLevelDelete, lstTrainingLevel);

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
		btnPreferAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnAvoidAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnTrainingLevelAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));

		btnPreferEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnAvoidEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnTrainingLevelEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));

		btnPreferDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnAvoidDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnTrainingLevelDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

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

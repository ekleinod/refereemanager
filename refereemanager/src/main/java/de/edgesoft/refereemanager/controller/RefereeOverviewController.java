package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.Trainee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.model.TraineeModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the referee overview scene.
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
 * @since 0.10.0
 */
public class RefereeOverviewController extends AbstractTitledIDDetailsController {

	/**
	 * Heading label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblHeading;

	/**
	 * Heading label person data.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Label lblHeadingPerson;

	/**
	 * Name label.
	 */
	@FXML
	private Label lblName;

	/**
	 * Name label label.
	 */
	@FXML
	private Label lblNameLabel;

	/**
	 * First name label.
	 */
	@FXML
	private Label lblFirstName;

	/**
	 * First name label label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblFirstNameLabel;

	/**
	 * Training level label.
	 */
	@FXML
	private Label lblTrainingLevel;

	/**
	 * Training level label label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblTrainingLevelLabel;

	/**
	 * Club label.
	 */
	@FXML
	private Label lblClub;

	/**
	 * Club label label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblClubLabel;

	/**
	 * Birthday label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblBirthday;

	/**
	 * Birthday label label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblBirthdayLabel;

	/**
	 * Last update label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblLastUpdate;

	/**
	 * Last update label label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblLastUpdateLabel;

	/**
	 * Next update label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblNextUpdate;

	/**
	 * Next update label label.
	 *
	 * @since 0.13.0
	 */
	@FXML
	private Label lblNextUpdateLabel;

	/**
	 * Role label.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Label lblRole;

	/**
	 * Role label label.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Label lblRoleLabel;

	/**
	 * Referee image.
	 */
	@FXML
	private ImageView imgReferee;

	/**
	 * Split pane.
	 */
	@FXML
	private SplitPane pneSplit;


	/**
	 * Main app controller.
	 */
	private AppLayoutController appController = null;


	/**
	 * Referee list controller.
	 */
	private RefereeListController ctlRefList;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// list
		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("RefereeList");
		AnchorPane refList = (AnchorPane) pneLoad.getKey();

		// add referee list to split pane
		pneSplit.getItems().add(0, refList);

		// store referee table controller
		ctlRefList = pneLoad.getValue().getController();

		// clear event details
		showDetails(null);

		// listen to selection changes, show person
		ctlRefList.getRefereesSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
		ctlRefList.getTraineesSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
		ctlRefList.getPeopleSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
		ctlRefList.getTabReferees().selectedProperty().addListener((event, oldTab, newTab) -> showDetails());
		ctlRefList.getTabTrainees().selectedProperty().addListener((event, oldTab, newTab) -> showDetails());
		ctlRefList.getTabPeople().selectedProperty().addListener((event, oldTab, newTab) -> showDetails());

		// disabling labels
		ObservableBooleanValue isReferee = ctlRefList.getTabReferees().selectedProperty();
		lblTrainingLevelLabel.visibleProperty().bind(isReferee);
		lblTrainingLevelLabel.managedProperty().bind(isReferee);
		lblTrainingLevel.visibleProperty().bind(isReferee);
		lblTrainingLevel.managedProperty().bind(isReferee);
		lblLastUpdateLabel.visibleProperty().bind(isReferee);
		lblLastUpdateLabel.managedProperty().bind(isReferee);
		lblLastUpdate.visibleProperty().bind(isReferee);
		lblLastUpdate.managedProperty().bind(isReferee);
		lblNextUpdateLabel.visibleProperty().bind(isReferee);
		lblNextUpdateLabel.managedProperty().bind(isReferee);
		lblNextUpdate.visibleProperty().bind(isReferee);
		lblNextUpdate.managedProperty().bind(isReferee);

		ObservableBooleanValue isRefereeOrTrainee = ctlRefList.getTabReferees().selectedProperty().or(ctlRefList.getTabTrainees().selectedProperty());
		lblClubLabel.visibleProperty().bind(isRefereeOrTrainee);
		lblClubLabel.managedProperty().bind(isRefereeOrTrainee);
		lblClub.visibleProperty().bind(isRefereeOrTrainee);
		lblClub.managedProperty().bind(isRefereeOrTrainee);
		imgReferee.visibleProperty().bind(isRefereeOrTrainee);
		imgReferee.managedProperty().bind(isRefereeOrTrainee);

		// set divider position
		pneSplit.setDividerPositions(Double.parseDouble(Prefs.get(PrefKey.REFEREE_OVERVIEW_SPLIT)));

		// if changed, save divider position to preferences
		pneSplit.getDividers().get(0).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(PrefKey.REFEREE_OVERVIEW_SPLIT, Double.toString(newValue.doubleValue()));
		});

		// CRUD buttons setup
		ObservableBooleanValue isOneItemSelected = ctlRefList.getTabReferees().selectedProperty().not().or(ctlRefList.getRefereesSelectionModel().selectedItemProperty().isNull())
				.and(ctlRefList.getTabTrainees().selectedProperty().not().or(ctlRefList.getTraineesSelectionModel().selectedItemProperty().isNull()))
				.and(ctlRefList.getTabPeople().selectedProperty().not().or(ctlRefList.getPeopleSelectionModel().selectedItemProperty().isNull()));
		initCRUDButtons(isOneItemSelected, isOneItemSelected);

		// headings
		Font fntTemp = lblHeading.getFont();
		lblHeading.setFont(Font.font(fntTemp.getFamily(), FontWeight.BOLD, fntTemp.getSize() + 2));
		lblHeadingPerson.setFont(Font.font(fntTemp.getFamily(), FontWeight.BOLD, fntTemp.getSize()));

	}

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theAppController app controller
	 */
	public void initController(final AppLayoutController theAppController) {

		appController = theAppController;

		ctlRefList.setItems();

	}

	/**
	 * Shows selected data in detail window.
	 *
	 * Checks if an item is selected in visible tab, uses this.
	 */
	private void showDetails() {

		ObservableList<PersonModel> lstSelected = ctlRefList.getVisibleTabSelection();

		if (lstSelected.size() == 1) {
			showDetails(lstSelected.get(0));
		} else {
			showDetails(null);
		}

	}

	/**
	 * Shows selected data in detail window.
	 *
	 * @param theDetailData event (null if none is selected)
	 */
	public void showDetails(final Person theDetailData) {

		super.showDetails(theDetailData);

		if (theDetailData == null) {

			lblHeading.setText("Details");

			LabelUtils.setText(lblName, null);
			LabelUtils.setText(lblFirstName, null);
			LabelUtils.setText(lblTrainingLevel, null);
			LabelUtils.setText(lblClub, null);
			LabelUtils.setText(lblBirthday, null);
			LabelUtils.setText(lblLastUpdate, null);
			LabelUtils.setText(lblNextUpdate, null);
			LabelUtils.setText(lblRole, null);

			imgReferee.setImage(null);

		} else {

			LabelUtils.setText(lblHeading, theDetailData.getDisplayTitle());

			LabelUtils.setText(lblName, theDetailData.getName());
			LabelUtils.setText(lblFirstName, theDetailData.getFirstName());
			LabelUtils.setText(lblBirthday, theDetailData.getBirthday(), null);

			lblRole.setText(
					(theDetailData.getRole() == null) ?
							null :
							theDetailData.getRole().getDisplayText().getValue());

			if (theDetailData instanceof RefereeModel) {
				RefereeModel mdlTemp = (RefereeModel) theDetailData;

				lblTrainingLevel.setText(
						(mdlTemp.getHighestTrainingLevel() == null) ?
								null :
								mdlTemp.getHighestTrainingLevel().getType().getDisplayTitleShort().getValue());

				lblClub.setText(
						(mdlTemp.getMember() == null) ?
								null :
								mdlTemp.getMember().getDisplayText().getValue());

				LabelUtils.setText(lblLastUpdate, mdlTemp.getLastTrainingUpdate(), null);
				LabelUtils.setText(lblNextUpdate, mdlTemp.getNextTrainingUpdate(), "yyyy");

				try {
					if (mdlTemp.existsImageFile(Prefs.get(PrefKey.PATHS_IMAGE))) {
						File fleImage = Paths.get(Prefs.get(PrefKey.PATHS_IMAGE), String.format("%s.jpg", mdlTemp.getFileName().getValue())).toFile();
						imgReferee.setImage(new Image(fleImage.toURI().toURL().toString()));
					} else {
						File fleImage = Paths.get(Prefs.get(PrefKey.PATHS_IMAGE), "missing.jpg").toFile();
						if (fleImage.exists()) {
							imgReferee.setImage(new Image(fleImage.toURI().toURL().toString()));
						} else {
							imgReferee.setImage(null);
						}
					}
				} catch (Exception e) {
					RefereeManager.logger.throwing(e);
					imgReferee.setImage(null);
				}

			}


		}

	}

	/**
	 * Opens edit dialog for new data.
	 *
	 * @param event calling action event
	 */
	@FXML
	@Override
	public void handleAdd(ActionEvent event) {

		PersonModel newPerson = null;

		if (ctlRefList.getTabReferees().isSelected()) {
			newPerson = new RefereeModel();
		}

		if (ctlRefList.getTabTrainees().isSelected()) {
			newPerson = new TraineeModel();
		}

		if (ctlRefList.getTabPeople().isSelected()) {
			newPerson = new PersonModel();
		}

		if (showEditDialog(newPerson)) {

			if (ctlRefList.getTabReferees().isSelected()) {
				((ContentModel) AppModel.getData().getContent()).getObservableReferees().add((Referee) newPerson);
				ctlRefList.getRefereesSelectionModel().select((Referee) newPerson);
			}

			if (ctlRefList.getTabTrainees().isSelected()) {
				((ContentModel) AppModel.getData().getContent()).getObservableTrainees().add((Trainee) newPerson);
				ctlRefList.getTraineesSelectionModel().select((Trainee) newPerson);
			}

			if (ctlRefList.getTabPeople().isSelected()) {
				((ContentModel) AppModel.getData().getContent()).getObservablePeople().add(newPerson);
				ctlRefList.getPeopleSelectionModel().select(newPerson);
			}

			AppModel.setModified(true);
			appController.setAppTitle();
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @param event calling action event
	 */
	@FXML
	@Override
	public void handleEdit(ActionEvent event) {

		ObservableList<PersonModel> lstSelected = ctlRefList.getVisibleTabSelection();

		if (lstSelected.size() == 1) {
			if (showEditDialog(lstSelected.get(0))) {
				showDetails(lstSelected.get(0));
				AppModel.setModified(true);
				appController.setAppTitle();
			}
		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @param event calling action event
	 */
	@FXML
	@Override
	public void handleDelete(ActionEvent event) {

		ObservableList<PersonModel> lstSelected = ctlRefList.getVisibleTabSelection();

		if (lstSelected.size() == 1) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, appController.getPrimaryStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", lstSelected.get(0).getDisplayTitle().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						if (ctlRefList.getTabReferees().isSelected()) {
							((ContentModel) AppModel.getData().getContent()).getObservableReferees().remove(lstSelected.get(0));
						}

						if (ctlRefList.getTabTrainees().isSelected()) {
							((ContentModel) AppModel.getData().getContent()).getObservableTrainees().remove(lstSelected.get(0));
						}

						if (ctlRefList.getTabPeople().isSelected()) {
							((ContentModel) AppModel.getData().getContent()).getObservablePeople().remove(lstSelected.get(0));
						}
						AppModel.setModified(true);
						appController.setAppTitle();
						});

		}

	}

	/**
	 * Opens the data edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param thePerson the person to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	private boolean showEditDialog(PersonModel thePerson) {

		Objects.requireNonNull(thePerson);

		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("PersonEditDialog");

		AnchorPane editDialog = (AnchorPane) pneLoad.getKey();

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(appController.getPrimaryStage());
		dialogStage.setTitle(String.format("%s editieren",
				ctlRefList.getTabReferees().isSelected() ? "Schiedsrichter_in" :
					ctlRefList.getTabTrainees().isSelected() ? "Azubi" :
						"Person"));

		Scene scene = new Scene(editDialog);
		dialogStage.setScene(scene);

		// Set the referee
		PersonEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(dialogStage);
		editController.setPerson(thePerson);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

		return editController.isOkClicked();

	}

}

/* EOF */

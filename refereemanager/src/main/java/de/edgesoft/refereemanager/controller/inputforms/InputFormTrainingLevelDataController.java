package de.edgesoft.refereemanager.controller.inputforms;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.ButtonUtils;
import de.edgesoft.refereemanager.controller.TrainingLevelEditDialogController;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.TrainingLevelModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the training level data edit dialog tab.
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
public class InputFormTrainingLevelDataController extends AbstractInputFormController {

	/**
	 * List view for training level.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "trainingLevel", jaxbclass = Referee.class)
	protected ListView<ModelClassExt> lstTrainingLevel;

	/**
	 * Add training level.
	 */
	@FXML
	private Button btnTrainingLevelAdd;

	/**
	 * Edit training level.
	 */
	@FXML
	private Button btnTrainingLevelEdit;

	/**
	 * Delete training level.
	 */
	@FXML
	private Button btnTrainingLevelDelete;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

        // setup list view
        lstTrainingLevel.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());

		// enable buttons
        ButtonUtils.bindDisable(btnTrainingLevelEdit, lstTrainingLevel);
        ButtonUtils.bindDisable(btnTrainingLevelDelete, lstTrainingLevel);

		// icons
		btnTrainingLevelAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnTrainingLevelEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnTrainingLevelDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

	}

	/**
	 * Opens edit dialog for new data.
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
	 */
	@FXML
	private void handleTrainingLevelEdit() {

		if (!lstTrainingLevel.getSelectionModel().isEmpty()) {
			showTrainingLevelEditDialog((TrainingLevelModel) lstTrainingLevel.getSelectionModel().getSelectedItem());
		}

	}

	/**
	 * Deletes selected data from list.
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

}

/* EOF */

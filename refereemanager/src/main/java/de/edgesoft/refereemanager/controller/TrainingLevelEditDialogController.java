package de.edgesoft.refereemanager.controller;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.controller.editdialogs.AbstractEditDialogController;
import de.edgesoft.refereemanager.jaxb.TrainingLevel;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the training level edit dialog scene.
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
public class TrainingLevelEditDialogController extends AbstractEditDialogController<TrainingLevel> {

	/**
	 * Since picker.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "since", jaxbclass = TrainingLevel.class)
	protected DatePicker pckSince;

	/**
	 * Combobox for training level types.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "type", jaxbclass = TrainingLevel.class)
	protected ComboBox<ModelClassExt> cboTrainingLevelType;

	/**
	 * Clear training level type.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnTrainingLevelTypeClear;

	/**
	 * List view for updates.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "update", jaxbclass = TrainingLevel.class)
	protected ListView<SimpleObjectProperty<LocalDate>> lstUpdate;

	/**
	 * Add update.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnUpdateAdd;

	/**
	 * Edit update.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnUpdateEdit;

	/**
	 * Delete update.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnUpdateDelete;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {

		setClasses(new ArrayList<>(Arrays.asList(new Class<?>[]{TrainingLevel.class})));
		super.initialize();

		// set date picker date format
		pckSince.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboTrainingLevelType, AppModel.getData().getContent().getTrainingLevelType());

        // setup list views
        lstUpdate.setCellFactory(ComboBoxUtils.getCallbackLocalDateProperty());

		// enable ok button for valid entries only
		btnOK.disableProperty().bind(
				cboTrainingLevelType.getSelectionModel().selectedItemProperty().isNull()
		);

		// enable buttons
		btnUpdateEdit.disableProperty().bind(
				lstUpdate.getSelectionModel().selectedItemProperty().isNull()
		);
		btnUpdateDelete.disableProperty().bind(
				lstUpdate.getSelectionModel().selectedItemProperty().isNull()
		);
		btnTrainingLevelTypeClear.disableProperty().bind(
				cboTrainingLevelType.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnUpdateAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnUpdateEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnUpdateDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

		btnTrainingLevelTypeClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Sets data to be edited.
	 *
	 * @param theData data
	 */
	@Override
	public void setData(TrainingLevel theData) {

		super.setData(theData);

    }


	/**
	 * Opens edit dialog for new data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleUpdateAdd() {

		SimpleObjectProperty<LocalDate> newUpdate = new SimpleObjectProperty<>();
		if (showTrainingLevelUpdateEditDialog(newUpdate)) {
			lstUpdate.getItems().add(newUpdate);
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleUpdateEdit() {

		if (!lstUpdate.getSelectionModel().isEmpty()) {
			showTrainingLevelUpdateEditDialog(lstUpdate.getSelectionModel().getSelectedItem());
			lstUpdate.refresh();
		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleUpdateDelete() {

		if (!lstUpdate.getSelectionModel().isEmpty()) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, getDialogStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", DateTimeUtils.formatDate(lstUpdate.getSelectionModel().getSelectedItem().getValue())),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						lstUpdate.getItems().remove(lstUpdate.getSelectionModel().getSelectedItem());
			});

		}

	}

	/**
	 * Opens the training level edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theUpdate the update to be edited
	 * @return true if the user clicked OK, false otherwise.
	 *
	 * @since 0.14.0
	 */
	private boolean showTrainingLevelUpdateEditDialog(SimpleObjectProperty<LocalDate> theUpdate) {

		Objects.requireNonNull(theUpdate);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("TrainingLevelUpdateEditDialog");

		// Create the dialog Stage.
		Stage editDialogStage = new Stage();
		editDialogStage.initModality(Modality.WINDOW_MODAL);
		editDialogStage.initOwner(getDialogStage());
		editDialogStage.setTitle("Fortbildung");

		editDialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set the update
		TrainingLevelUpdateEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(editDialogStage);
		editController.setUpdate(theUpdate);

		// Show the dialog and wait until the user closes it
		editDialogStage.showAndWait();

		return editController.isOkClicked();

	}

	/**
	 * Clears training level type selection.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleTrainingLevelTypeClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboTrainingLevelType);
	}

}

/* EOF */

package de.edgesoft.refereemanager.controller;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.ClassUtils;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.TrainingLevel;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.TrainingLevelModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.JAXBMatchUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
public class TrainingLevelEditDialogController {

	/**
	 * Classes for introspection when setting/getting values.
	 */
	private Class<?>[] theClasses = new Class<?>[]{TrainingLevel.class};


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
	 * Current training level.
	 */
	private TrainingLevelModel currentTrainingLevel;

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

		// set date picker date format
		pckSince.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboTrainingLevelType, AppModel.getData().getContent().getTrainingLevelType());

        // setup list views
        lstUpdate.setCellFactory(ComboBoxUtils.getCallbackLocalDateProperty());

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

		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

		btnUpdateAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnUpdateEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnUpdateDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

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
	 * Sets training level to be edited.
	 *
	 * @param theTrainingLevel training level
	 */
	public void setTrainingLevel(TrainingLevelModel theTrainingLevel) {

		Objects.requireNonNull(theTrainingLevel);

        currentTrainingLevel = theTrainingLevel;

        // fill fields
        for (Field theFXMLField : lstDeclaredFields) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.setField(theFXMLField, fieldObject, theTrainingLevel, theClasses);

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

        		JAXBMatchUtils.getField(theFXMLField, fieldObject, currentTrainingLevel, theClasses);

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
	private void handleUpdateAdd() {

//		TrainingLevelModel newTrainingLevel = new TrainingLevelModel();
//		if (showTrainingLevelEditDialog(newTrainingLevel)) {
//			lstTrainingLevel.getItems().add(newTrainingLevel);
//		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleUpdateEdit() {

//		if (!lstTrainingLevel.getSelectionModel().isEmpty()) {
//			showTrainingLevelEditDialog((TrainingLevelModel) lstTrainingLevel.getSelectionModel().getSelectedItem());
//		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleUpdateDelete() {

//		if (!lstTrainingLevel.getSelectionModel().isEmpty()) {
//
//			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, dialogStage,
//					"Löschbestätigung",
//					MessageFormat.format("Soll ''{0}'' gelöscht werden?", lstTrainingLevel.getSelectionModel().getSelectedItem().getDisplayText().get()),
//					null);
//
//			alert.showAndWait()
//					.filter(response -> response == ButtonType.OK)
//					.ifPresent(response -> {
//						lstTrainingLevel.getItems().remove(lstTrainingLevel.getSelectionModel().getSelectedItem());
//			});
//
//		}

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

		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("TrainingLevelEditDialog");

		AnchorPane editDialog = (AnchorPane) pneLoad.getKey();

		// Create the dialog Stage.
		Stage editDialogStage = new Stage();
		editDialogStage.initModality(Modality.WINDOW_MODAL);
		editDialogStage.initOwner(dialogStage);
		editDialogStage.setTitle("Ausbildungsstufe editieren");

		Scene scene = new Scene(editDialog);
		editDialogStage.setScene(scene);

		// Set the referee
//		TrainingLevelEditDialogController editController = pneLoad.getValue().getController();
//		editController.setDialogStage(editDialogStage);
//		editController.setWish(theTrainingLevel);
//
//		// Show the dialog and wait until the user closes it
//		editDialogStage.showAndWait();
//
//		return editController.isOkClicked();
return false;

	}

}

/* EOF */

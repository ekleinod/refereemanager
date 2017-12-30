package de.edgesoft.refereemanager.controller.crud;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.controller.editdialogs.IEditDialogController;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the overview part: CRUD buttons with list.
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
public class ListCRUDController implements ICRUDActionsController<ModelClassExt> {

	/**
	 * Heading.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * List view for avoids.
	 */
	@FXML
	protected ListView<ModelClassExt> lstData;

	/**
	 * Button for clearing list selection.
	 */
	@FXML
	protected Button btnClearList;

	/**
	 * CRUD button view part.
	 */
	@FXML
	private Parent embeddedCRUDButtons;

	/**
	 * CRUD button view part controller.
	 */
	@FXML
	private CRUDButtonsController embeddedCRUDButtonsController;

	/**
	 * Grid pane.
	 */
	@FXML
	private GridPane grdListCRUD;





	/**
	 * Class for instances.
	 */
	private Class<ModelClassExt> clsClass;

	/**
	 * View name.
	 */
	private String sViewName;

	/**
	 * View noun.
	 */
	private String sViewNoun;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// icons
		btnClearList.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

		// buttons setup
		embeddedCRUDButtonsController.getAddButton().setOnAction(this::handleAdd);
		embeddedCRUDButtonsController.getEditButton().setOnAction(this::handleEdit);
		embeddedCRUDButtonsController.getDeleteButton().setOnAction(this::handleDelete);

		// enabling buttons due to selection
		BooleanBinding isOneItemSelected = lstData.getSelectionModel().selectedItemProperty().isNull();

		btnClearList.disableProperty().bind(isOneItemSelected);

		embeddedCRUDButtonsController.getAddButton().disableProperty().bind(isOneItemSelected.not());
		embeddedCRUDButtonsController.getEditButton().disableProperty().bind(isOneItemSelected);
		embeddedCRUDButtonsController.getDeleteButton().disableProperty().bind(isOneItemSelected);

        // setup list views
        lstData.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());

	}

	/**
	 * Initializes the CRUD controller.
	 *
	 * @param theClass class
	 * @param theViewName name of the edit view
	 * @param theViewTitleNoun title noun of the edit view ("edit <noun>")
	 */
	public void initCRUDController(final String theViewName, final String theViewTitleNoun) {

		assert (theViewName != null) : "View name must not be null";
		assert (theViewTitleNoun != null) : "View title noun must not be null";

		sViewName = theViewName;
		sViewNoun = theViewTitleNoun;

		lblHeading.setText(theViewName);

	}

	/**
	 * Adds input form part to grid.
	 *
	 * @param theInputFormPart input form part
	 */
	public void addInputFormPart(final Parent theInputFormPart) {

		assert (theInputFormPart != null) : "InputFormPart must not be null";

		grdListCRUD.add(theInputFormPart, 0, 2);

	}

    /**
     * Sets the underlying data model for the ListView.
     *
     * @param theItems items to set
     */
    public final void setItems(ObservableList<ModelClassExt> theItems) {
        lstData.setItems(theItems);
    }

	/**
	 * Clears list.
	 *
	 * @param event calling action event
	 */
	public void handleClearList(ActionEvent event) {
		lstData.getSelectionModel().clearSelection();
	}

	/**
	 * Opens edit dialog for new data.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleAdd(ActionEvent event) {
		try {
			handleAdd(sViewName, sViewNoun, clsClass.newInstance(), lstData.getItems());
		} catch (InstantiationException | IllegalAccessException e) {
			RefereeManager.logger.catching(e);
		}
	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleEdit(ActionEvent event) {
		handleEdit(sViewName, sViewNoun);
	}

	/**
	 * Deletes selected data from list.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleDelete(ActionEvent event) {
		handleDelete(lstData.getItems());
	}

	/**
	 * Handles add action.
	 *
	 * @param theData data element
	 * @param theDataList data list to add data to
	 */
	@Override
	public void handleAdd(final String theViewName, final String theViewTitleNoun, ModelClassExt theData, ObservableList<ModelClassExt> theDataList) {

		if (showEditDialog(theViewName, theViewTitleNoun, theData)) {
			theDataList.add(theData);
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@Override
	public void handleEdit(final String theViewName, final String theViewTitleNoun) {

		if (!lstData.getSelectionModel().isEmpty()) {
			if (showEditDialog(theViewName, theViewTitleNoun, lstData.getSelectionModel().getSelectedItem())) {
				lstData.refresh();
			}
		}

	}

	/**
	 * Handles delete action.
	 *
	 * @param theData data element
	 * @param theDataList data list to delete data from
	 */
	@Override
	public void handleDelete(ObservableList<ModelClassExt> theDataList) {

		if (!lstData.getSelectionModel().isEmpty()) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, RefereeManager.getAppController().getPrimaryStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", lstData.getSelectionModel().getSelectedItem().getDisplayText().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						theDataList.remove(lstData.getSelectionModel().getSelectedItem());
			});

		}

	}

	/**
	 * Opens the data edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theViewName name of the edit view
	 * @param theViewTitleNoun title noun of the edit view ("edit <noun>")
	 * @param theData the data to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	@Override
	public <S extends ModelClassExt> boolean showEditDialog(final String theViewName, final String theViewTitleNoun, S theData) {

		Objects.requireNonNull(theData);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode(theViewName);

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(RefereeManager.getAppController().getPrimaryStage());
		dialogStage.setTitle(MessageFormat.format("{0} editieren", theViewTitleNoun));

		dialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set data
		IEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(dialogStage);
		editController.fillForm(theData);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

		// ok = store data
		boolean isOkClicked = editController.isOkClicked();

		if (isOkClicked) {
			editController.fillData(theData);
		}

		return isOkClicked;

	}

}

/* EOF */

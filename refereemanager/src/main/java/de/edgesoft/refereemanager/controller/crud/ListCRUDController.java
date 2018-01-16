package de.edgesoft.refereemanager.controller.crud;

import java.text.MessageFormat;
import java.util.function.Supplier;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.controller.inputforms.IInputFormController;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
public class ListCRUDController<T extends ModelClassExt> implements ICRUDActionsController {

	/**
	 * Grid pane.
	 */
	@FXML
	private GridPane grdListCRUD;

	/**
	 * Heading.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * List view.
	 */
	@FXML
	protected ListView<T> lstData;

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
	 * Factory method for instances of T.
	 */
	private Supplier<T> instanceCall = null;

	/**
	 * Input form controller for access to data input
	 */
	private IInputFormController<T> ctlInputForm = null;


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
        lstData.setCellFactory(ComboBoxUtils.getCallbackTModelClassExt());

	}

	/**
	 * Initializes the controller.
	 *
	 * @param theInputFormController input form controller
	 * @param thePartInputForm input form part
	 * @param theViewName name of the edit view (null == no heading)
	 * @param theInstanceCall instance call
	 */
	public void initController(final IInputFormController<T> theInputFormController, final Parent thePartInputForm, final String theViewName, final Supplier<T> theInstanceCall) {

		assert (theInputFormController != null) : "InputFormController must not be null";
		assert (thePartInputForm != null) : "PartInputForm must not be null";
		assert (theInstanceCall != null) : "Instance call must not be null";

		instanceCall = theInstanceCall;
		ctlInputForm = theInputFormController;

		if (theViewName != null) {
			lblHeading.setText(theViewName);
		} else {
			lblHeading.setVisible(false);
			lblHeading.setManaged(false);
		}

		lstData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> ctlInputForm.fillForm(lstData.getSelectionModel().getSelectedItem()));

		grdListCRUD.add(thePartInputForm, 0, 2);

	}

    /**
     * Returns the items of the ListView.
     *
     * @return items
     */
    public ObservableList<T> getItems() {
        return lstData.getItems();
    }

    /**
     * Sets the items of the ListView.
     *
     * @param theItems items to set
     */
    public void setItems(ObservableList<T> theItems) {
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
	 * Adds new data to list.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleAdd(ActionEvent event) {

		T newData = instanceCall.get();
		ctlInputForm.fillData(newData);
		lstData.getItems().add(newData);

		lstData.getSelectionModel().select(newData);
		lstData.refresh();
		lstData.scrollTo(newData);
		lstData.getFocusModel().focus(lstData.getSelectionModel().getSelectedIndex());

	}

	/**
	 * Changes selected data in list.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleEdit(ActionEvent event) {

		if (!lstData.getSelectionModel().isEmpty()) {
			ctlInputForm.fillData(lstData.getSelectionModel().getSelectedItem());
			lstData.refresh();
			lstData.scrollTo(lstData.getSelectionModel().getSelectedItem());
			lstData.getFocusModel().focus(lstData.getSelectionModel().getSelectedIndex());
		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleDelete(ActionEvent event) {

		if (!lstData.getSelectionModel().isEmpty()) {

			T dtaSelected = lstData.getSelectionModel().getSelectedItem();

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, RefereeManager.getAppController().getPrimaryStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", dtaSelected.getDisplayText().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						lstData.getItems().remove(dtaSelected);
						lstData.refresh();
					}
			);

		}

	}

}

/* EOF */

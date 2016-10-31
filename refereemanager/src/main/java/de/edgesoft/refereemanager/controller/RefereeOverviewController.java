package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Controller for the referee list scene.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of refereemanager.
 *
 * refereemanager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * refereemanager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with refereemanager.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.10.0
 * @since 0.10.0
 */
public class RefereeOverviewController {

	/**
	 * Name label.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblName;

	/**
	 * First name label.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblFirstName;

	/**
	 * Training level label.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblTrainingLevel;

	/**
	 * Club label.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblClub;

	/**
	 * Referee image.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private ImageView imgReferee;

	/**
	 * New button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnNew;

	/**
	 * Edit button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnEdit;

	/**
	 * Delete button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnDelete;

	/**
	 * Split pane.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private SplitPane pneSplit;


	/**
	 * Main app controller.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private AppLayoutController appController = null;


	/**
	 * Table view.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private TableView<Referee> tblReferees;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void initialize() {

		// list
    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("RefereeList");
    	AnchorPane refList = (AnchorPane) pneLoad.getKey();

        // add referee list to split pane
        pneSplit.getItems().add(0, refList);

        // store referee table reference
        RefereeListController ctlRefList = pneLoad.getValue().getController();
        tblReferees = ctlRefList.getTableView();

		// clear event details
		showDetails(null);

		// listen to selection changes, show event
		tblReferees.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));

		// enabling edit/delete buttons only with selection
		btnEdit.disableProperty().bind(tblReferees.getSelectionModel().selectedItemProperty().isNull());
		btnDelete.disableProperty().bind(tblReferees.getSelectionModel().selectedItemProperty().isNull());

		// set divider position
		pneSplit.setDividerPositions(Double.parseDouble(Prefs.get(PrefKey.REFEREE_OVERVIEW_SPLIT)));

		// if changed, save divider position to preferences
		pneSplit.getDividers().get(0).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(PrefKey.REFEREE_OVERVIEW_SPLIT, Double.toString(newValue.doubleValue()));
		});

		// icons
		btnNew.setGraphic(new ImageView(Resources.loadImage("icons/actions/list-add-16.png")));
		btnEdit.setGraphic(new ImageView(Resources.loadImage("icons/actions/edit-16.png")));
		btnDelete.setGraphic(new ImageView(Resources.loadImage("icons/actions/list-remove-16.png")));

	}

	/**
	 * Initializes the controller with things, that cannot be done during {@link #initialize()}.
	 *
	 * @param theAppController app controller
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void initController(final AppLayoutController theAppController) {

		appController = theAppController;

		if (AppModel.getData() != null) {
			tblReferees.setItems(((ContentModel) AppModel.getData().getContent()).getObservableReferees());
			tblReferees.refresh();
		} else {
			tblReferees.setItems(null);
			tblReferees.refresh();
		}
    }

	/**
	 * Shows selected data in detail window.
	 *
	 * @param theDetailData event (null if none is selected)
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private void showDetails(final Referee theDetailData) {

	    if (theDetailData == null) {

	        lblName.setText("");
	        lblFirstName.setText("");
	        lblTrainingLevel.setText("");
	        lblClub.setText("");
	        imgReferee.setImage(null);

	    } else {

	        lblName.setText(
	        		(theDetailData.getName() == null) ?
	        				null :
	        				theDetailData.getName().getValue());
	        lblFirstName.setText(
	        		(theDetailData.getFirstName() == null) ?
	        				null :
	        				theDetailData.getFirstName().getValue());
	        lblTrainingLevel.setText(
	        		(((RefereeModel) theDetailData).getHighestTrainingLevel() == null) ?
	        				null :
	        				((RefereeModel) theDetailData).getHighestTrainingLevel().getType().getDisplayTitle().getValue());
	        lblClub.setText(
	        		(theDetailData.getMember() == null) ?
	        				null :
	        				theDetailData.getMember().getDisplayTitle().getValue());

	        try {
		        File fleImage = Paths.get(Prefs.get(PrefKey.IMAGE_PATH), String.format("%s.jpg", theDetailData.getFileName().get())).toFile();
		        if (fleImage.exists()) {
		        	imgReferee.setImage(new Image(fleImage.toURI().toURL().toString()));
		        } else {
			        fleImage = Paths.get(Prefs.get(PrefKey.IMAGE_PATH), String.format("%s.jpg", "missing")).toFile();
			        if (fleImage.exists()) {
			        	imgReferee.setImage(new Image(fleImage.toURI().toURL().toString()));
			        } else {
			        	imgReferee.setImage(null);
			        }
		        }
	        } catch (Exception e) {
	        	imgReferee.setImage(null);
	        }

	    }

	}

	/**
	 * Opens edit dialog for new data.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleNew() {

		RefereeModel newReferee = new RefereeModel();
		if (showEditDialog(newReferee)) {
			((ContentModel) AppModel.getData().getContent()).getObservableReferees().add(newReferee);
			tblReferees.getSelectionModel().select(newReferee);
			AppModel.setModified(true);
			appController.setAppTitle();
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleEdit() {

		RefereeModel editReferee = (RefereeModel) tblReferees.getSelectionModel().getSelectedItem();

	    if (editReferee != null) {

			if (showEditDialog(editReferee)) {
				showDetails(editReferee);
				AppModel.setModified(true);
				appController.setAppTitle();
			}

	    }

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleDelete() {

	    int selectedIndex = tblReferees.getSelectionModel().getSelectedIndex();

	    if (selectedIndex >= 0) {

	    	Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, appController.getPrimaryStage(),
	    			"Bestätigung Schiedsrichter löschen",
	    			"Soll der ausgewählte Schiedsrichter gelöscht werden?",
	    			null);

	        alert.showAndWait()
	        		.filter(response -> response == ButtonType.OK)
	        		.ifPresent(response -> {
	        			tblReferees.getItems().remove(selectedIndex);
	        			AppModel.setModified(true);
	        			appController.setAppTitle();
	        			});

	    }

	}

	/**
	 * Opens the referee edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theReferee the referee to be edited
	 * @return true if the user clicked OK, false otherwise.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private boolean showEditDialog(Referee theReferee) {

//    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("EventEditDialog");
//    	AnchorPane editDialog = (AnchorPane) pneLoad.getKey();
//
//        // Create the dialog Stage.
//        Stage dialogStage = new Stage();
//        dialogStage.initModality(Modality.WINDOW_MODAL);
//        dialogStage.initOwner(appController.getPrimaryStage());
//        dialogStage.setTitle("Ereignis editieren");
//
//        Scene scene = new Scene(editDialog);
//        dialogStage.setScene(scene);
//
//        // Set the event into the controller.
//        EventEditDialogController editController = pneLoad.getValue().getController();
//        editController.setDialogStage(dialogStage);
//        editController.setEvent(theReferee);
//
//        // Show the dialog and wait until the user closes it
//        dialogStage.showAndWait();
//
//        return editController.isOkClicked();
		return false;

	}

}

/* EOF */

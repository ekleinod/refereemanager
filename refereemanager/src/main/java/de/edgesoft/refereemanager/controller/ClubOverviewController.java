package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ClubModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the club overview scene.
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
public class ClubOverviewController extends AbstractTitledIDDetailsController {

	/**
	 * Heading.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * Heading club data.
	 */
	@FXML
	private Label lblHeadingClub;

	/**
	 * Filename.
	 */
	@FXML
	private Label lblFilename;

	/**
	 * Local club?.
	 */
	@FXML
	private Label lblLocal;

	/**
	 * URL.
	 */
	@FXML
	private Label lblURL;

	/**
	 * Venue.
	 */
	@FXML
	private Label lblVenue;

	/**
	 * Contact.
	 */
	@FXML
	private Label lblContact;

	/**
	 * Split pane.
	 */
	@FXML
	private SplitPane pneSplit;


	/**
	 * List view part.
	 */
	@FXML
	private Parent embeddedList;

	/**
	 * List view part controller.
	 */
	@FXML
	private ClubListController embeddedListController;


	/**
	 * Main app controller.
	 */
	private AppLayoutController appController = null;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// clear event details
		showDetails(null);

		// listen to selection changes, show person
		embeddedListController.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));

		// set divider position
		pneSplit.setDividerPositions(Double.parseDouble(Prefs.get(PrefKey.EVENT_OVERVIEW_SPLIT)));

		// if changed, save divider position to preferences
		pneSplit.getDividers().get(0).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(PrefKey.EVENT_OVERVIEW_SPLIT, Double.toString(newValue.doubleValue()));
		});

		// CRUD buttons setup
		ObservableBooleanValue isOneItemSelected = embeddedListController.getSelectionModel().selectedItemProperty().isNull();
		initCRUDButtons(isOneItemSelected, isOneItemSelected);

		// headings
		lblHeading.setFont(FontUtils.getDerived(lblHeading.getFont(), FontWeight.BOLD, 2));
		lblHeadingClub.setFont(FontUtils.getDerived(lblHeadingClub.getFont(), FontWeight.BOLD));

	}

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theAppController app controller
	 */
	public void initController(final AppLayoutController theAppController) {

		appController = theAppController;

		embeddedListController.setItems();

	}

	/**
	 * Shows selected data in detail window.
	 *
	 * @param theDetailData club (null if none is selected)
	 */
	public void showDetails(final Club theDetailData) {

		super.showDetails(theDetailData);

		if (theDetailData == null) {

			lblHeading.setText("Details");

			LabelUtils.setText(lblFilename, null);
			LabelUtils.setText(lblLocal, null);
			LabelUtils.setText(lblURL, null);
			LabelUtils.setText(lblVenue, null);
			LabelUtils.setText(lblContact, null);

		} else {

			LabelUtils.setText(lblHeading, theDetailData.getDisplayText());

			LabelUtils.setText(lblFilename, theDetailData.getFilename());
			LabelUtils.setText(lblLocal, new SimpleStringProperty("ToDo"));
			LabelUtils.setText(lblURL, new SimpleStringProperty("ToDo"));
			LabelUtils.setText(lblVenue, new SimpleStringProperty("ToDo"));
			LabelUtils.setText(lblContact, new SimpleStringProperty("ToDo"));

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

		ClubModel newClub = new ClubModel();

		if (showEditDialog(newClub)) {

			((ContentModel) AppModel.getData().getContent()).getObservableClubs().add(newClub);
			embeddedListController.getSelectionModel().select(newClub);

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

		ObservableList<ClubModel> lstSelected = embeddedListController.getSelection();

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

		ObservableList<ClubModel> lstSelected = embeddedListController.getSelection();

		if (lstSelected.size() == 1) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, appController.getPrimaryStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", lstSelected.get(0).getDisplayTitle().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						((ContentModel) AppModel.getData().getContent()).getObservableClubs().remove(lstSelected.get(0));

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
	 * @param theData the data to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	private boolean showEditDialog(ClubModel theData) {

		Objects.requireNonNull(theData);

		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("ClubEditDialog");

		AnchorPane editDialog = (AnchorPane) pneLoad.getKey();

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(appController.getPrimaryStage());
		dialogStage.setTitle("Club editieren");

		Scene scene = new Scene(editDialog);
		dialogStage.setScene(scene);

		// Set the data
//		ClubEditDialogController editController = pneLoad.getValue().getController();
//		editController.setDialogStage(dialogStage);
//		editController.setEvent(theData);
//
//		// Show the dialog and wait until the user closes it
//		dialogStage.showAndWait();
//
//		return editController.isOkClicked();
		return false;

	}

}

/* EOF */

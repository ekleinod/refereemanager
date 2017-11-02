package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
public class OverviewRefereesController implements ICRUDActionsController, IDetailsController, IOverviewController {

	/**
	 * Overview controller of the underlying view.
	 */
	OverviewController overviewController = null;

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theOverviewController overview controller
	 */
	@Override
	public void initController(final OverviewController theOverviewController) {

		overviewController = theOverviewController;

		overviewController.initController(this, PrefKey.OVERVIEW_REFEREE_SPLIT, "ListReferees", "DetailsReferee");

		// CRUD buttons setup
		ObservableBooleanValue isOneItemSelected = overviewController.getListController().getSelectionModel().selectedItemProperty().isNull();
		overviewController.initCRUDButtons(this, isOneItemSelected, isOneItemSelected);

	}

	/**
	 * Shows selected data in detail window.
	 *
	 * @param theDetailData detail data (null if none is selected)
	 */
	@Override
	public <T extends ModelClassExt> void showDetails(final T theDetailData) {

		overviewController.showDetails(theDetailData);

		if ((theDetailData == null) || !(theDetailData instanceof Person)) {

			overviewController.setHeading(new SimpleStringProperty("Details"));

		} else {

			Referee theData = (Referee) theDetailData;

			overviewController.setHeading(theData.getDisplayTitle());

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

		RefereeModel newReferee = new RefereeModel();

		if (showEditDialog(newReferee)) {

			((ContentModel) AppModel.getData().getContent()).getObservableReferees().add(newReferee);
			((ListRefereesController) overviewController.getListController()).getSelectionModel().select(newReferee);

			AppModel.setModified(true);
			RefereeManager.getAppController().setAppTitle();
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

		ObservableList<RefereeModel> lstSelected = ((ListRefereesController) overviewController.getListController()).getSortedSelectedItems();

		if (lstSelected.size() == 1) {
			if (showEditDialog(lstSelected.get(0))) {
				showDetails(lstSelected.get(0));
				AppModel.setModified(true);
				RefereeManager.getAppController().setAppTitle();
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

		ObservableList<RefereeModel> lstSelected = ((ListRefereesController) overviewController.getListController()).getSortedSelectedItems();

		if (lstSelected.size() == 1) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, RefereeManager.getAppController().getPrimaryStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", lstSelected.get(0).getDisplayTitle().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						((ContentModel) AppModel.getData().getContent()).getObservableReferees().remove(lstSelected.get(0));
						AppModel.setModified(true);
						RefereeManager.getAppController().setAppTitle();
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
	private static boolean showEditDialog(RefereeModel theData) {

		Objects.requireNonNull(theData);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("PersonEditDialog");

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(RefereeManager.getAppController().getPrimaryStage());
		dialogStage.setTitle("Schiedsrichter_in editieren");

		dialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set data
		PersonEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(dialogStage);
		editController.setPerson(theData);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

		return editController.isOkClicked();

	}

}

/* EOF */

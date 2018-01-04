package de.edgesoft.refereemanager.controller.overview;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.controller.crud.ICRUDDialogActionsController;
import de.edgesoft.refereemanager.controller.details.IDetailsController;
import de.edgesoft.refereemanager.controller.editdialogs.IEditDialogController;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Abstract controller for overview scenes.
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
public abstract class AbstractOverviewController<T extends TitledIDTypeModel> implements ICRUDDialogActionsController<T>, IDetailsController<T>, IOverviewController<T> {

	/**
	 * Overview controller of the underlying view.
	 */
	private OverviewController<T> overviewController = null;

	/**
	 * Returns overview controller.
	 *
	 * @return overview controller
	 */
	@Override
	public OverviewController<T> getController() {
		return overviewController;
	}

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theOverviewController overview controller
	 */
	@Override
	public void initController(final OverviewController<T> theOverviewController) {
		overviewController = theOverviewController;
	}

	/**
	 * Shows selected data in detail window.
	 *
	 * @param theDetailData detail data (null if none is selected)
	 */
	@Override
	public void showDetails(final T theDetailData) {

		getController().showDetails(theDetailData);

		if (theDetailData == null) {

			getController().setHeading(new SimpleStringProperty("Details"));

		} else {

			getController().setHeading(theDetailData.getDisplayTitle());

		}

	}

	/**
	 * Handles add action.
	 *
	 * @param theData data element
	 * @param theDataList data list to add data to
	 */
	@Override
	public void handleAdd(final String theViewName, final String theViewTitleNoun, T theData, ObservableList<T> theDataList) {

		if (showEditDialog(theViewName, theViewTitleNoun, theData)) {

			theDataList.add(theData);
			getController().getListController().getDataTable().refresh();
			getController().getListController().getDataTable().getSelectionModel().select(theData);
			getController().getListController().getDataTable().scrollTo(theData);
//			getController().getListController().select(theData);

			AppModel.setModified(true);
			RefereeManager.getAppController().setAppTitle();
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@Override
	public void handleEdit(final String theViewName, final String theViewTitleNoun) {

		Optional<T> theData = getController().getListController().getSelectedItem();

		if (theData.isPresent()) {
			if (showEditDialog(theViewName, theViewTitleNoun, theData.get())) {
				getController().getListController().getDataTable().refresh();
				showDetails(theData.get());
				AppModel.setModified(true);
				RefereeManager.getAppController().setAppTitle();
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
	public void handleDelete(ObservableList<T> theDataList) {

		Optional<T> theData = getController().getListController().getSelectedItem();

		if (theData.isPresent()) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, RefereeManager.getAppController().getPrimaryStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", theData.get().getDisplayText().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						theDataList.remove(theData.get());
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
	 * @param theViewName name of the edit view
	 * @param theViewTitleNoun title noun of the edit view ("edit <noun>")
	 * @param theData the data to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	@Override
	public boolean showEditDialog(final String theViewName, final String theViewTitleNoun, T theData) {

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

package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.Resources;
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
public abstract class AbstractOverviewController<T extends ModelClassExt> implements ICRUDActionsController<T>, IDetailsController, IOverviewController {

	/**
	 * Overview controller of the underlying view.
	 */
	private OverviewController overviewController = null;

	/**
	 * Returns overview controller.
	 *
	 * @return overview controller
	 */
	@Override
	public OverviewController getController() {
		return overviewController;
	}

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theOverviewController overview controller
	 */
	@Override
	public void initController(final OverviewController theOverviewController) {
		overviewController = theOverviewController;
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
			overviewController.getListController().select(theData);

			AppModel.setModified(true);
			RefereeManager.getAppController().setAppTitle();
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@Override
	public void handleEdit(final String theViewName, final String theViewTitleNoun) {

		Optional<? extends ModelClassExt> theData = getController().getListController().getSelectedItem();

		if (theData.isPresent()) {
			if (showEditDialog(theViewName, theViewTitleNoun, theData.get())) {
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

		Optional<? extends ModelClassExt> theData = getController().getListController().getSelectedItem();

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
		IEditDialogController<S> editController = pneLoad.getValue().getController();
		editController.setDialogStage(dialogStage);
		editController.setData(theData);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

		return editController.isOkClicked();

	}

}

/* EOF */
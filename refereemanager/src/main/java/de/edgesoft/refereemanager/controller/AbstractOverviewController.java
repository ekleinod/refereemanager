package de.edgesoft.refereemanager.controller;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	public void handleAdd(T theData, ObservableList<T> theDataList) {

		if (showEditDialog(theData)) {

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
	public void handleEdit() {

		Optional<? extends ModelClassExt> theData = getController().getListController().getSelectedItem();

		if (theData.isPresent()) {
			if (showEditDialog(theData.get())) {
				showDetails(theData.get());
				AppModel.setModified(true);
				RefereeManager.getAppController().setAppTitle();
			}
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
	@Override
	public boolean showEditDialog(T theData) {

		Objects.requireNonNull(theData);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("PersonEditDialog");

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(RefereeManager.getAppController().getPrimaryStage());
		dialogStage.setTitle("Person editieren");

		dialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set data
		PersonEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(dialogStage);
		editController.setData((Person) theData);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

		return editController.isOkClicked();

	}

}

/* EOF */

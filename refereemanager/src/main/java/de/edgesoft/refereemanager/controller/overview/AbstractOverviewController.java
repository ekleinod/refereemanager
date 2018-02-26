package de.edgesoft.refereemanager.controller.overview;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.controller.crud.IEditDialogCRUDActionsController;
import de.edgesoft.refereemanager.controller.details.IDetailsController;
import de.edgesoft.refereemanager.controller.editdialogs.AbstractEditDialogController;
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
public abstract class AbstractOverviewController<T extends TitledIDTypeModel> implements IEditDialogCRUDActionsController<T>, IDetailsController<T>, IOverviewController<T> {

	/**
	 * Overview controller of the underlying view.
	 */
	private OverviewDetailsController<T> overviewController = null;

	/**
	 * Title noun.
	 */
	private String sTitleNoun = null;

	/**
	 * FXML filename.
	 */
	private String sFXMLFilename = null;

	@Override
	public OverviewDetailsController<T> getController() {
		return overviewController;
	}

	@Override
	public void initController(
			final OverviewDetailsController<T> theOverviewController
			) {

		overviewController = theOverviewController;

	}

	@Override
	public void showDetails(
			final T theDetailData
			) {

		getController().showDetails(theDetailData);

		if (theDetailData == null) {

			getController().setHeading(new SimpleStringProperty("Details"));

		} else {

			getController().setHeading(theDetailData.getDisplayTitle());

		}

	}

	@Override
	public void initEditDialogFXMLFilename(
			final String theFXMLFilename,
			final String theTitleNoun
			) {

		sFXMLFilename = theFXMLFilename;
		sTitleNoun = theTitleNoun;

	}

	@Override
	public void handleAdd(
			T theData,
			ObservableList<T> theDataList
			) {

		if (showEditDialog(theData)) {

			theDataList.add(theData);
			getController().getDataTableController().getDataTable().refresh();
			getController().getDataTableController().select(theData);
			getController().getDataTableController().getDataTable().scrollTo(theData);

			AppModel.setModified(true);
			RefereeManager.getAppController().setAppTitle();
			getController().getDataTableController().handleFilterChange();

		}

	}

	@Override
	public void handleEdit() {

		Optional<T> theData = getController().getDataTableController().getSelectedItem();

		if (theData.isPresent()) {
			if (showEditDialog(theData.get())) {
				getController().getDataTableController().getDataTable().refresh();
				showDetails(theData.get());
				AppModel.setModified(true);
				RefereeManager.getAppController().setAppTitle();
				getController().getDataTableController().handleFilterChange();
			}
		}

	}

	@Override
	public void handleDelete(
			ObservableList<T> theDataList
			) {

		Optional<T> theData = getController().getDataTableController().getSelectedItem();

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
						getController().getDataTableController().handleFilterChange();
						});

		}

	}

	@Override
	public boolean showEditDialog(
			T theData
			) {

		Objects.requireNonNull(theData);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode(sFXMLFilename);

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(RefereeManager.getAppController().getPrimaryStage());
		dialogStage.setTitle(MessageFormat.format("{0} editieren", sTitleNoun));

		dialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set data
		AbstractEditDialogController<T> editController = pneLoad.getValue().getController();
		editController.setDialogStage(dialogStage);
		editController.fillFormFromData(theData);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

		// ok = store data
		boolean isOkClicked = editController.isOkClicked();

		if (isOkClicked) {
			editController.fillDataFromForm(theData);
		}

		return isOkClicked;

	}

}

/* EOF */

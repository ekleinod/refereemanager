package de.edgesoft.refereemanager.controller;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.Trainee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.TraineeModel;
import de.edgesoft.refereemanager.utils.PrefKey;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.ActionEvent;

/**
 * Controller for the trainee overview scene.
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
public class OverviewTraineesController extends AbstractOverviewController<Trainee> {

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theOverviewController overview controller
	 */
	@Override
	public void initController(final OverviewController theOverviewController) {

		super.initController(theOverviewController);

		getController().initController(this, PrefKey.OVERVIEW_TRAINEE_SPLIT, "ListTrainees", "DetailsTrainee");

		// CRUD buttons setup
		ObservableBooleanValue isOneItemSelected = getController().getListController().selectedItemProperty().isNull();
		getController().initCRUDButtons(this, isOneItemSelected, isOneItemSelected);

	}

	/**
	 * Shows selected data in detail window.
	 *
	 * @param theDetailData detail data (null if none is selected)
	 */
	@Override
	public <T extends ModelClassExt> void showDetails(final T theDetailData) {

		getController().showDetails(theDetailData);

		if ((theDetailData == null) || !(theDetailData instanceof Trainee)) {

			getController().setHeading(new SimpleStringProperty("Details"));

		} else {

			Trainee theData = (Trainee) theDetailData;

			getController().setHeading(theData.getDisplayTitle());

		}

	}

	/**
	 * Opens edit dialog for new data.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleAdd(ActionEvent event) {
		super.handleAdd("PersonEditDialog", "Azubi", new TraineeModel(), ((ContentModel) AppModel.getData().getContent()).getObservableTrainees());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleEdit(ActionEvent event) {
		handleEdit("PersonEditDialog", "Azubi");
	}

	/**
	 * Deletes selected data from list.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleDelete(ActionEvent event) {
		super.handleDelete(((ContentModel) AppModel.getData().getContent()).getObservableTrainees());
	}

}

/* EOF */

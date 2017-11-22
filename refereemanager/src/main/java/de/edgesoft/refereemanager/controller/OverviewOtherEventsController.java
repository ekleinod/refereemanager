package de.edgesoft.refereemanager.controller;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.OtherEvent;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.utils.PrefKey;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.ActionEvent;

/**
 * Controller for the other events overview scene.
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
public class OverviewOtherEventsController extends AbstractOverviewController<OtherEvent> {

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theOverviewController overview controller
	 */
	@Override
	public void initController(final OverviewController theOverviewController) {

		super.initController(theOverviewController);

		getController().initController(this, PrefKey.OVERVIEW_OTHER_EVENT_SPLIT, "lists/ListOtherEvents", "details/DetailsOtherEvent");

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

		Class<OtherEventModel> theClass = OtherEventModel.class;

		assert
				((theDetailData == null) || theClass.isInstance(theDetailData))
				: String.format("Detail data is not of type %s but of type %s.", theClass.getName(), (theDetailData == null) ? "null" : theDetailData.getClass().getName());

		getController().showDetails(theDetailData);

		if (theDetailData == null) {

			getController().setHeading(new SimpleStringProperty("Details"));

		} else {

			OtherEventModel theData = theClass.cast(theDetailData);

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
		super.handleAdd("EventEditDialog", "Sonstiges Ereignis", new OtherEventModel(), ((ContentModel) AppModel.getData().getContent()).getObservableOtherEvents());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleEdit(ActionEvent event) {
		handleEdit("EventEditDialog", "Sonstiges Ereignis");
	}

	/**
	 * Deletes selected data from list.
	 *
	 * @param event calling action event
	 */
	@Override
	public void handleDelete(ActionEvent event) {
		super.handleDelete(((ContentModel) AppModel.getData().getContent()).getObservableOtherEvents());
	}

}

/* EOF */

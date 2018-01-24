package de.edgesoft.refereemanager.controller.overview;

import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.utils.PrefKey;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.ActionEvent;

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
public class OverviewClubsController extends AbstractOverviewController<Club> {

	@Override
	public void initController(
			final OverviewDetailsController<Club> theOverviewController
			) {

		super.initController(theOverviewController);

		getController().initController(this, PrefKey.OVERVIEW_CLUB_SPLIT, "datatables/DataTableClubs", "details/DetailsClub");

		// CRUD buttons setup
		ObservableBooleanValue isOneItemSelected = getController().getDataTableController().selectedItemProperty().isNull();
		getController().initCRUDButtons(this, isOneItemSelected, isOneItemSelected);

		initEditDialogFXMLFilename("editdialogs/EditDialogClub", "Club");

	}

	@Override
	public void handleAdd(
			ActionEvent event
			) {

		handleAdd(AppModel.factory.createClub(), ((ContentModel) AppModel.getData().getContent()).getObservableClubs());

	}

	@Override
	public void handleEdit(
			ActionEvent event
			) {

		handleEdit();

	}

	@Override
	public void handleDelete(
			ActionEvent event
			) {

		handleDelete(((ContentModel) AppModel.getData().getContent()).getObservableClubs());

	}

}

/* EOF */

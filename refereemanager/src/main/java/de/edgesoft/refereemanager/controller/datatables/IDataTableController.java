package de.edgesoft.refereemanager.controller.datatables;

import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Interface for the data table scene controller.
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
public interface IDataTableController<T extends TitledIDTypeModel> extends IListController<T> {

	/**
	 * Returns data table.
	 */
	public TableView<T> getDataTable();

	/**
	 * Sets table items.
	 */
	public void setDataTableItems();

	/**
	 * Sets table placeholder noun.
	 *
	 * @param thePlaceholderNoun placeholder noun
	 */
	public void setDataTablePlaceholderNoun(final String thePlaceholderNoun);

	/**
	 * Handles filter change events.
	 */
	public void handleFilterChange();

	/**
	 * Returns selection as sorted list.
	 *
	 * @return sorted selection
	 */
	public ObservableList<T> getSortedSelectedItems();

}

/* EOF */

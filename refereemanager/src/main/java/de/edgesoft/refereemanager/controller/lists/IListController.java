package de.edgesoft.refereemanager.controller.lists;

import java.util.Optional;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;

/**
 * Interface for the list scene controller.
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
public interface IListController {

	/**
	 * Returns data table.
	 */
	public TableView<? extends ModelClassExt> getDataTable();

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
	 * Sets selection mode.
	 *
	 * @param theSelectionMode selection mode
	 */
	public void setSelectionMode(final SelectionMode theSelectionMode);

	/**
	 * Sets selected item.
	 *
	 * @param theItem item to select
	 */
	public <T extends ModelClassExt> void select(final T theItem);

	/**
	 * Returns selected item property.
	 *
	 * @return selected item property
	 */
	public ReadOnlyObjectProperty<? extends ModelClassExt> selectedItemProperty();

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 */
	public ObservableList<? extends ModelClassExt> getSortedSelectedItems();

	/**
	 * Returns selected item if there is only one.
	 *
	 * @return selected item
	 */
	public Optional<? extends ModelClassExt> getSelectedItem();

}

/* EOF */

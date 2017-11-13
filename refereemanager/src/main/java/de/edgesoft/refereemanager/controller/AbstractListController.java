package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.util.Optional;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.utils.TableUtils;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;

/**
 * Abstract ontroller for list scenes.
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
public abstract class AbstractListController implements IListController {

	/**
	 * Sets table placeholder noun.
	 *
	 * @param thePlaceholderNoun placeholder noun
	 */
	@Override
	public void setDataTablePlaceholderNoun(final String thePlaceholderNoun) {

		Label lblPlaceholder = new Label(MessageFormat.format(
				(getDataTable().getItems().size() == 0) ? TableUtils.TABLE_NO_DATA : TableUtils.TABLE_FILTERED,
				thePlaceholderNoun));
		lblPlaceholder.setWrapText(true);
		getDataTable().setPlaceholder(lblPlaceholder);

	}

	/**
	 * Sets selection mode.
	 *
	 * @param theSelectionMode selection mode
	 */
	@Override
	public void setSelectionMode(final SelectionMode theSelectionMode) {
		getDataTable().getSelectionModel().setSelectionMode(theSelectionMode);
	}

	/**
	 * Returns selected item property.
	 *
	 * @return selected item property
	 */
	@Override
	public ReadOnlyObjectProperty<? extends ModelClassExt> selectedItemProperty() {
		return getDataTable().getSelectionModel().selectedItemProperty();
	}

	/**
	 * Returns selected item if there is only one.
	 *
	 * @return selected item
	 */
	@Override
	public Optional<? extends ModelClassExt> getSelectedItem() {

		if (getDataTable().getSelectionModel().getSelectedItems().size() != 1) {
			return Optional.empty();
		}

		return Optional.of(getDataTable().getSelectionModel().getSelectedItem());

	}

}

/* EOF */

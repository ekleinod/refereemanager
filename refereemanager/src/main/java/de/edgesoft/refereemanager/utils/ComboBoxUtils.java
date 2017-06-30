package de.edgesoft.refereemanager.utils;

import java.time.LocalDate;
import java.util.List;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Utility methods for {@link ComboBox}.
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
 * @version 0.14.0
 * @since 0.14.0
 */
public class ComboBoxUtils {

	/**
	 * Prepares combobox with data and corresponding cell renderers.
	 *
	 * @param theComboBox combobox to prepare
	 * @param theItems items to fill combobox with
	 *
	 * @version 0.14.0
	 */
	public static final void prepareComboBox(ComboBox<ModelClassExt> theComboBox, final List<? extends ModelClassExt> theItems) {
		theComboBox.setItems(FXCollections.observableArrayList(theItems));
		theComboBox.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
		theComboBox.setButtonCell(ComboBoxUtils.getListCell());
	}

	/**
	 * Returns {@link Callback} for cell factories for lists of {@link ModelClassExt}.
	 *
	 * @return callback for cell factories
	 *
	 * @version 0.14.0
	 */
	public static final Callback<ListView<ModelClassExt>, ListCell<ModelClassExt>> getCallbackModelClassExt() {
		return new Callback<ListView<ModelClassExt>, ListCell<ModelClassExt>>() {
			@SuppressWarnings("unused")
			@Override
			public ListCell<ModelClassExt> call(ListView<ModelClassExt> param) {
				return new ListCell<ModelClassExt>() {
					@Override
					public void updateItem(ModelClassExt item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null) {
							setText(null);
						} else {
							setText(item.getDisplayText().getValue());
						}
					}
				};
			}
		};
	}

	/**
	 * Returns {@link Callback} for cell factories for lists of {@link SimpleObjectProperty}< {@link LocalDate} >.
	 *
	 * @return callback for cell factories
	 *
	 * @version 0.14.0
	 */
	public static final Callback<ListView<SimpleObjectProperty<LocalDate>>, ListCell<SimpleObjectProperty<LocalDate>>> getCallbackLocalDateProperty() {
		return new Callback<ListView<SimpleObjectProperty<LocalDate>>, ListCell<SimpleObjectProperty<LocalDate>>>() {
			@SuppressWarnings("unused")
			@Override
			public ListCell<SimpleObjectProperty<LocalDate>> call(ListView<SimpleObjectProperty<LocalDate>> param) {
				return new ListCell<SimpleObjectProperty<LocalDate>>() {
					@Override
					public void updateItem(SimpleObjectProperty<LocalDate> item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null) {
							setText(null);
						} else {
							setText(DateTimeUtils.formatDate(item.getValue()));
						}
					}
				};
			}
		};
	}

	/**
	 * Returns {@link ListCell} for button cells.
	 *
	 * @return list cell for button cells
	 *
	 * @version 0.14.0
	 */
	public static final ListCell<ModelClassExt> getListCell() {
		return new ListCell<ModelClassExt>() {
			@Override
			public void updateItem(ModelClassExt item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setText(null);
				} else {
					setText(item.getDisplayText().getValue());
				}
			}
		};
	}

}

/* EOF */

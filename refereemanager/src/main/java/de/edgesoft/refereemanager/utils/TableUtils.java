package de.edgesoft.refereemanager.utils;

import java.time.LocalDate;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import javafx.scene.control.TableCell;

/**
 * Utility methods for {@link Table}.
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
public class TableUtils {

	/**
	 * Returns {@link TableCell} for cell factories (person model, local date).
	 *
	 * @return table cell for cell factories (person model, local date)
	 *
	 * @version 0.14.0
	 * @since 0.14.0
	 */
	public static final TableCell<PersonModel, LocalDate> getTableCellPersonDate() {
		return new TableCell<PersonModel, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDate(item));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (referee model, local date).
	 *
	 * @return table cell for cell factories (referee model, local date)
	 *
	 * @version 0.14.0
	 * @since 0.14.0
	 */
	public static final TableCell<RefereeModel, LocalDate> getTableCellRefereeDate() {
		return new TableCell<RefereeModel, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDate(item, "yyyy"));
	            }
	        }
	    };
	}

}

/* EOF */

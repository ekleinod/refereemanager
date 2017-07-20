package de.edgesoft.refereemanager.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.model.LeagueGameModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.model.TournamentModel;
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
	 * @return table cell for cell factories
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
	 * @return table cell for cell factories
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

	/**
	 * Returns {@link TableCell} for cell factories (league game model, date).
	 *
	 * @return table cell for cell factories
	 */
	public static final TableCell<LeagueGameModel, LocalDateTime> getTableCellLeagueGameDate() {
		return new TableCell<LeagueGameModel, LocalDateTime>() {
	        @Override
	        protected void updateItem(LocalDateTime item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDateTimeAsDate(item));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (league game model, time).
	 *
	 * @return table cell for cell factories
	 */
	public static final TableCell<LeagueGameModel, LocalDateTime> getTableCellLeagueGameTime() {
		return new TableCell<LeagueGameModel, LocalDateTime>() {
	        @Override
	        protected void updateItem(LocalDateTime item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDateTimeAsTime(item));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (tournament model, date).
	 *
	 * @return table cell for cell factories
	 */
	public static final TableCell<TournamentModel, LocalDateTime> getTableCellTournamentDate() {
		return new TableCell<TournamentModel, LocalDateTime>() {
	        @Override
	        protected void updateItem(LocalDateTime item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDateTimeAsDate(item));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (other event model, date).
	 *
	 * @return table cell for cell factories
	 */
	public static final TableCell<OtherEventModel, LocalDateTime> getTableCellOtherEventDate() {
		return new TableCell<OtherEventModel, LocalDateTime>() {
	        @Override
	        protected void updateItem(LocalDateTime item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDateTimeAsDate(item));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (other event model, time).
	 *
	 * @return table cell for cell factories
	 */
	public static final TableCell<OtherEventModel, LocalDateTime> getTableCellOtherEventTime() {
		return new TableCell<OtherEventModel, LocalDateTime>() {
	        @Override
	        protected void updateItem(LocalDateTime item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty || (item.toLocalTime() == LocalTime.MIDNIGHT)) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDateTimeAsTime(item));
	            }
	        }
	    };
	}

}

/* EOF */

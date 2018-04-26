package de.edgesoft.refereemanager.utils;

import java.time.LocalDate;
import java.time.LocalTime;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.StatusType;
import de.edgesoft.refereemanager.model.LeagueGameModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.model.TournamentModel;
import de.edgesoft.refereemanager.model.TraineeModel;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

/**
 * Utility methods and constants for {@link Table}.
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
	 * Text for empty table: no data.
	 *
	 * @since 0.15.0
	 */
	public static final String TABLE_NO_DATA = "Es wurden noch keine {0} eingegeben.";

	/**
	 * Text for empty table: filtered.
	 *
	 * @since 0.15.0
	 */
	public static final String TABLE_FILTERED = "Die Filterung schließt alle {0} aus.";

	/**
	 * Returns {@link TableCell} for cell factories (person model, local date).
	 *
	 * @param thePattern pattern (null = standard pattern)
	 * @return table cell for cell factories
	 */
	public static final TableCell<PersonModel, LocalDate> getTableCellPersonDate(final String thePattern) {
		return new TableCell<PersonModel, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDate(item, thePattern));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (referee model, local date).
	 *
	 * @param thePattern pattern (null = standard pattern)
	 * @return table cell for cell factories
	 */
	public static final TableCell<RefereeModel, LocalDate> getTableCellRefereeDate(final String thePattern) {
		return new TableCell<RefereeModel, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDate(item, thePattern));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (trainee model, local date).
	 *
	 * @param thePattern pattern (null = standard pattern)
	 * @return table cell for cell factories
	 */
	public static final TableCell<TraineeModel, LocalDate> getTableCellTraineeDate(final String thePattern) {
		return new TableCell<TraineeModel, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDate(item, thePattern));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (league game model, date).
	 *
	 * @return table cell for cell factories
	 *
	 * @param thePattern pattern (null = standard pattern)
	 * @since 0.15.0
	 */
	public static final TableCell<LeagueGameModel, LocalDate> getTableCellLeagueGameDate(final String thePattern) {
		return new TableCell<LeagueGameModel, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDate(item, thePattern));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (league game model, time).
	 *
	 * @param thePattern pattern (null = standard pattern)
	 * @return table cell for cell factories
	 *
	 * @since 0.15.0
	 */
	public static final TableCell<LeagueGameModel, LocalTime> getTableCellLeagueGameTime(final String thePattern) {
		return new TableCell<LeagueGameModel, LocalTime>() {
	        @Override
	        protected void updateItem(LocalTime item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatTime(item, thePattern));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (tournament model, date).
	 *
	 * @param thePattern pattern (null = standard pattern)
	 * @return table cell for cell factories
	 *
	 * @since 0.15.0
	 */
	public static final TableCell<TournamentModel, LocalDate> getTableCellTournamentDate(final String thePattern) {
		return new TableCell<TournamentModel, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDate(item, thePattern));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (other event model, date).
	 *
	 * @return table cell for cell factories
	 *
	 * @param thePattern pattern (null = standard pattern)
	 * @since 0.15.0
	 */
	public static final TableCell<OtherEventModel, LocalDate> getTableCellOtherEventDate(final String thePattern) {
		return new TableCell<OtherEventModel, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDate(item, thePattern));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (other event model, date).
	 *
	 * @return table cell for cell factories
	 *
	 * @param thePattern pattern (null = standard pattern)
	 * @since 0.15.0
	 */
	public static final <T extends ModelClassExt> TableCell<T, LocalDate> getTableCellTModelClassExtDate(final String thePattern) {
		return new TableCell<T, LocalDate>() {
	        @Override
	        protected void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatDate(item, thePattern));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories (other event model, time).
	 *
	 * @return table cell for cell factories
	 *
	 * @param thePattern pattern (null = standard pattern)
	 * @since 0.15.0
	 */
	public static final TableCell<OtherEventModel, LocalTime> getTableCellOtherEventTime(final String thePattern) {
		return new TableCell<OtherEventModel, LocalTime>() {
	        @Override
	        protected void updateItem(LocalTime item, boolean empty) {
	            super.updateItem(item, empty);

	            if (item == null || empty || (item == LocalTime.MIDNIGHT)) {
	                setText(null);
	            } else {
	                setText(DateTimeUtils.formatTime(item, thePattern));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories.
	 *
	 * @return table cell for cell factories
	 *
	 * @since 0.15.0
	 */
	public static final TableCell<LeagueGameModel, Boolean> getTableCellLeagueGameRefereeReport() {
		return new TableCell<LeagueGameModel, Boolean>() {
	        @Override
	        protected void updateItem(Boolean item, boolean empty) {
	            super.updateItem(item, empty);

	            if (empty) {
	                setGraphic(null);
	            } else if (item == null || !item) {
	                setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-error.png")));
	            } else {
	                setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-success.png")));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories.
	 *
	 * @return table cell for cell factories
	 *
	 * @since 0.15.0
	 */
	public static final TableCell<TournamentModel, Boolean> getTableCellTournamentRefereeReport() {
		return new TableCell<TournamentModel, Boolean>() {
	        @Override
	        protected void updateItem(Boolean item, boolean empty) {
	            super.updateItem(item, empty);

	            if (empty) {
	                setGraphic(null);
	            } else if (item == null || !item) {
	                setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-error.png")));
	            } else {
	                setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-success.png")));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories.
	 *
	 * @return table cell for cell factories
	 *
	 * @since 0.15.0
	 */
	public static final TableCell<RefereeModel, Boolean> getTableCellRefereeActive() {
		return new TableCell<RefereeModel, Boolean>() {
	        @Override
	        protected void updateItem(Boolean item, boolean empty) {
	            super.updateItem(item, empty);

	            if (empty) {
	                setGraphic(null);
	            } else if (item == null || !item) {
	                setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-unchecked.png")));
	            } else {
	                setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-checked.png")));
	            }
	        }
	    };
	}

	/**
	 * Returns {@link TableCell} for cell factories.
	 *
	 * @return table cell for cell factories
	 *
	 * @since 0.15.0
	 */
	public static final TableCell<StatusType, Boolean> getTableCellStatusTypeActive() {
		return new TableCell<StatusType, Boolean>() {
	        @Override
	        protected void updateItem(Boolean item, boolean empty) {
	            super.updateItem(item, empty);

	            if (empty) {
	                setGraphic(null);
	            } else if (item == null || !item) {
	                setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-unchecked.png")));
	            } else {
	                setGraphic(new ImageView(Resources.loadImage("icons/24x24/emblems/emblem-checked.png")));
	            }
	        }
	    };
	}

}

/* EOF */

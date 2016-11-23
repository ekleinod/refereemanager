package de.edgesoft.refereemanager.model;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import de.edgesoft.refereemanager.jaxb.Season;
import javafx.beans.property.SimpleStringProperty;

/**
 * Season model, additional methods for jaxb model class.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
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
 * @version 0.10.0
 * @since 0.5.0
 */
public class SeasonModel extends Season {

	/**
	 * Start month of a new season.
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static final Month NEWSEASON = Month.JUNE;

	/**
	 * Returns start year for current season.
	 *
	 * A season runs from june to may.
	 *
	 * @return start year of current season
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
		public static Integer getCurrentStartYear() {
			return getStartYearForDate(LocalDate.now());
		}

	/**
	 * Returns start year of a season for a given date.
	 *
	 * A season runs from june to may.
	 *
	 * @param theDate date
	 * @return start year of season for date
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
		public static Integer getStartYearForDate(final LocalDate theDate) {

		Objects.requireNonNull(theDate, "date must not be null");

			int iReturn = theDate.getYear();
			if (theDate.getMonth().ordinal() < NEWSEASON.ordinal()) {
				iReturn--;
			}

				return Integer.valueOf(iReturn);
		}

		/**
		 * Gets the value of the title property.
		 *
		 * @return title
	 *
	 * @version 0.10.0
	 * @since 0.5.0
		 */
		@Override
		public SimpleStringProperty getTitle() {
			if (title == null) {
				return new SimpleStringProperty(MessageFormat.format("{0,number,####}-{1,number,####}", getStartYear().get(), getStartYear().get() + 1));
			}
				return title;
		}

}

/* EOF */

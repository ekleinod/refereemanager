package de.edgesoft.refereemanager.model;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.EventDate;
import javafx.beans.property.SimpleStringProperty;

/**
 * Event date model, additional methods for jaxb model class.
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
 * @since 0.9.0
 */
public class EventDateModel extends EventDate {

	/**
	 * Comparator start date/time.
	 */
	public static final Comparator<EventDate> RANK_START = Comparator.comparing(date -> (LocalDateTime) date.getStart().getValue());

	/**
	 * Returns date text.
	 *
	 * @return date text
	 *
	 * @since 0.15.0
	 */
	public SimpleStringProperty getDateText() {

		if (getStartDate() == null) {
			return null;
		}

		if (getEndDate() == null)  {
			return new SimpleStringProperty(DateTimeUtils.formatDate(getStartDate()));
		}

		return new SimpleStringProperty(MessageFormat.format("{0} - {1}",
				DateTimeUtils.formatDate(getStartDate()),
				DateTimeUtils.formatDate(getEndDate())));

	}

	/**
	 * Returns time text.
	 *
	 * @return time text
	 *
	 * @since 0.15.0
	 */
	public SimpleStringProperty getTimeText() {

		if (getStartTime() == null) {
			return null;
		}

		if (getEndTime() == null) {
			return new SimpleStringProperty(DateTimeUtils.formatTime(getStartTime()));
		}

		return new SimpleStringProperty(MessageFormat.format("{0} - {1}",
				DateTimeUtils.formatTime(getStartTime()),
				DateTimeUtils.formatTime(getEndTime())));

	}

	/**
	 * Returns start date.
	 *
	 * @return start date
	 *
	 * @since 0.15.0
	 */
	public LocalDate getStartDate() {

		if ((getStart() == null) || (getStart().getValue() == null)) {
			return null;
		}

		return ((LocalDateTime) getStart().getValue()).toLocalDate();

	}

	/**
	 * Returns end date if different from start date.
	 *
	 * @return end date
	 *
	 * @since 0.15.0
	 */
	public LocalDate getEndDate() {

		if ((getEnd() == null) || (getEnd().getValue() == null) || (getStartDate() == null)) {
			return null;
		}

		LocalDate dteReturn = ((LocalDateTime) getEnd().getValue()).toLocalDate();

		if (getStartDate().isEqual(dteReturn)) {
			return null;
		}

		return dteReturn;

	}

	/**
	 * Returns start time.
	 *
	 * @return start time
	 *
	 * @since 0.15.0
	 */
	public LocalTime getStartTime() {

		if ((getStart() == null) || (getStart().getValue() == null)) {
			return null;
		}

		LocalTime tmeReturn = ((LocalDateTime) getStart().getValue()).toLocalTime();

		if (tmeReturn == LocalTime.MIDNIGHT) {
			return null;
		}

		return tmeReturn;

	}

	/**
	 * Returns end time.
	 *
	 * @return end time
	 *
	 * @since 0.15.0
	 */
	public LocalTime getEndTime() {

		if ((getEnd() == null) || (getEnd().getValue() == null) || (getStartTime() == null)) {
			return null;
		}

		LocalTime tmeReturn = ((LocalDateTime) getEnd().getValue()).toLocalTime();

		if (tmeReturn == LocalTime.MIDNIGHT) {
			return null;
		}

		return tmeReturn;

	}

}

/* EOF */

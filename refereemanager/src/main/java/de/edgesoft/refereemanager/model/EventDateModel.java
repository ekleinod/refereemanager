package de.edgesoft.refereemanager.model;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
 * @version 0.15.0
 * @since 0.9.0
 */
public class EventDateModel extends EventDate {

	/**
	 * Comparator start date/time.
	 */
	public static final Comparator<EventDateModel> DATE_FIRST = Comparator.comparing(date -> (LocalDate) date.getFirstDay().getDate().getValue());

	/**
	 * Returns date text.
	 *
	 * @return date text
	 *
	 * @since 0.15.0
	 */
	public SimpleStringProperty getDateText() {

		if (getFirstDay() == null) {
			return null;
		}

		if (getLastDay() == null)  {
			return new SimpleStringProperty(DateTimeUtils.formatDate((LocalDate) getFirstDay().getDate().getValue()));
		}

		return new SimpleStringProperty(MessageFormat.format("{0} - {1}",
				DateTimeUtils.formatDate((LocalDate) getFirstDay().getDate().getValue()),
				DateTimeUtils.formatDate((LocalDate) getLastDay().getDate().getValue())));

	}

	/**
	 * Returns time text.
	 *
	 * @return time text
	 *
	 * @since 0.15.0
	 */
	public SimpleStringProperty getTimeText() {

		if ((getFirstDay() == null) || (getLastDay() != null)) {
			return null;
		}

		return getFirstDay().getTimeText();

	}

	/**
	 * Returns first day.
	 *
	 * @return first day
	 *
	 * @since 0.15.0
	 */
	public EventDayModel getFirstDay() {

		if (getDay().isEmpty()) {
			return null;
		}

		return (EventDayModel) getDay().get(0);

	}

	/**
	 * Returns last day if different from first day.
	 *
	 * @return last day
	 *
	 * @since 0.15.0
	 */
	public EventDayModel getLastDay() {

		if (getDay().size() < 2) {
			return null;
		}

		return (EventDayModel) getDay().get(getDay().size() - 1);

	}

	/**
	 * Returns day period between first and last date.
	 *
	 * @return day period
	 *
	 * @since 0.15.0
	 */
	public long getDayPeriod() {

		if (getFirstDay() == null) {
			return -1;
		}

		if (getLastDay() == null) {
			return 1;
		}

		return ChronoUnit.DAYS.between((LocalDate) getLastDay().getDate().getValue(), (LocalDate) getFirstDay().getDate().getValue());

	}

}

/* EOF */

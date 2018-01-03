package de.edgesoft.refereemanager.model;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.EventDay;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Event day model, additional methods for jaxb model class.
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
public class EventDayModel extends EventDay {

	/**
	 * Returns date text.
	 *
	 * @return date text
	 */
	public SimpleStringProperty getDateText() {

		if (getDate() == null) {
			return null;
		}

		return new SimpleStringProperty(DateTimeUtils.formatDate((LocalDate) getDate().getValue()));

	}

	/**
	 * Returns time text.
	 *
	 * @return time text
	 */
	public SimpleStringProperty getTimeText() {

		if (getStartTime() == null) {
			return null;
		}

		if (getEndTime() == null) {
			return new SimpleStringProperty(DateTimeUtils.formatTime((LocalTime) getStartTime().getValue()));
		}

		return new SimpleStringProperty(MessageFormat.format("{0} - {1}",
				DateTimeUtils.formatTime((LocalTime) getStartTime().getValue()),
				DateTimeUtils.formatTime((LocalTime) getEndTime().getValue())));

	}

	/**
	 * Returns display text.
	 */
	@Override
	public StringProperty getDisplayText() {
		return getDateText();
	}

}

/* EOF */

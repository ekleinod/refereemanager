package de.edgesoft.refereemanager.model;

import java.text.MessageFormat;
import java.util.List;

import de.edgesoft.edgeutils.files.FileUtils;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Team;
import de.edgesoft.refereemanager.jaxb.Venue;
import javafx.beans.property.SimpleStringProperty;

/**
 * Team model, additional methods for jaxb model class.
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
 * @since 0.8.0
 */
public class TeamModel extends Team {

	/**
	 * Display title.
	 *
	 * @return display title
	 */
	@Override
	public SimpleStringProperty getDisplayTitle() {

		if (getNumber() == null) {
			return getClub().getDisplayTitle();
		}

		return new SimpleStringProperty(MessageFormat.format("{0} {1}", getClub().getDisplayTitle().getValue(), getNumber().getValue()));
	}

	/**
	 * Short display title.
	 *
	 * @return Short display title
	 *
	 * @since 0.15.0
	 */
	@Override
	public SimpleStringProperty getDisplayTitleShort() {

		if (getNumber() == null) {
			return getClub().getDisplayTitleShort();
		}

		return new SimpleStringProperty(MessageFormat.format("{0} {1}", getClub().getDisplayTitleShort().getValue(), getNumber().getValue()));
	}

	/**
	 * Filename.
	 *
	 * @return filename
	 *
	 * @since 0.9.0
	 */
	public SimpleStringProperty getFilename() {

		if (getNumber() == null) {
			return getClub().getFilename();
		}

		return new SimpleStringProperty(FileUtils.cleanFilename(String.format("%s%d", getClub().getFilename().get(), getNumber().get())));
	}

	/**
	 * Return contact person.
	 *
	 * @return contact person
	 *
	 * @since 0.9.0
	 */
	@Override
    public List<Person> getContactPerson() {

		if (super.getContactPerson() != null) {
			return super.getContactPerson();
		}

		if ((getClub() != null) && (getClub().getContactPerson() != null)) {
			return getClub().getContactPerson();
		}

		return null;

	}

	/**
	 * Return venues.
	 *
	 * @return venues
	 *
	 * @since 0.9.0
	 */
	@Override
	public List<Venue> getVenue() {

		if (super.getVenue().isEmpty() && (getClub() != null) && (!getClub().getVenue().isEmpty())) {
			return getClub().getVenue();
		}

		return super.getVenue();

	}

}

/* EOF */

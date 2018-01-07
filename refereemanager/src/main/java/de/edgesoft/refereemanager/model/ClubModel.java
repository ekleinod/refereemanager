package de.edgesoft.refereemanager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.xml.bind.JAXBElement;

import de.edgesoft.edgeutils.files.FileUtils;
import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Venue;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Club model, additional methods for jaxb model class.
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
 * @since 0.5.0
 */
public class ClubModel extends Club {

	/**
	 * Filter predicate for all clubs.
	 */
	public static Predicate<Club> ALL = club -> true;

	/**
	 * Filter predicate for local clubs.
	 */
	public static Predicate<Club> LOCAL = club -> club.getIsLocal().getValue();

	/**
	 * Filter predicate for non-local clubs.
	 */
	public static Predicate<Club> NON_LOCAL = LOCAL.negate();

	/**
	 * Returns if club is local club.
	 *
	 * @return is club local club
	 *
	 * @since 0.9.0
	 */
	@Override
	public SimpleBooleanProperty getIsLocal() {
		return (super.getIsLocal() == null) ? new SimpleBooleanProperty(Boolean.FALSE) : super.getIsLocal();
	}

	/**
	 * Filename.
	 *
	 * @return filename
	 *
	 * @since 0.12.0
	 */
	@Override
	public SimpleStringProperty getFilename() {

		if ((super.getFilename() == null) || (super.getFilename().getValueSafe().isEmpty())) {
			return new SimpleStringProperty(FileUtils.cleanFilename(getDisplayText().getValueSafe(), false));
		}

		return new SimpleStringProperty(FileUtils.cleanFilename(super.getFilename().getValueSafe(), false));
	}

	/**
	 * Returns display text.
	 *
	 * @return display text
	 */
	@Override
	public StringProperty getDisplayText() {
		return getDisplayTitleShort();
	}

    /**
     * Returns the list of venues.
     *
     * For reasons unbeknownst to me, a list of references will be generated as
     * list of references, but internally a list of JAXBElements is used, that wrap
     * the referencing objects.
     *
     * This is very strange, so I use this method as a workaround.
     *
     * @return list of venues
     */
    public List<Venue> getVenueList() {

    	List<Venue> lstReturn = new ArrayList<>();

		for (Object theVenue : super.getVenue()) {
			lstReturn.add(((JAXBElement<Venue>) theVenue).getValue());
		}

        return lstReturn;

    }

    /**
     * Returns the list of contact people.
     *
     * For reasons unbeknownst to me, a list of references will be generated as
     * list of references, but internally a list of JAXBElements is used, that wrap
     * the referencing objects.
     *
     * This is very strange, so I use this method as a workaround.
     *
     * @return list of contact people
     */
    public List<Person> getContactPersonList() {

    	List<Person> lstReturn = new ArrayList<>();

		for (Object thePerson : super.getContactPerson()) {
			lstReturn.add(((JAXBElement<Person>) thePerson).getValue());
		}

        return lstReturn;

    }

}

/* EOF */

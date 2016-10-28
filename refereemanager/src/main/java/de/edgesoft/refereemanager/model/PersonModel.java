package de.edgesoft.refereemanager.model;

import java.text.Collator;
import java.util.Comparator;

import de.edgesoft.refereemanager.jaxb.Address;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.Person;
import javafx.beans.property.SimpleStringProperty;

/**
 * Person model, additional methods for jaxb model class.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of refereemanager.
 *
 * refereemanager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * refereemanager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with refereemanager.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.10.0
 * @since 0.5.0
 */
public class PersonModel extends Person {

	/**
	 * Comparator for people by name.
	 *
	 * @version 0.10.0
	 * @since 0.8.0
	 */
	public static final Comparator<Person> NAME = Comparator.comparing(person -> person.getName().get(), Collator.getInstance());

	/**
	 * Comparator for people by name then first ame.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static final Comparator<Person> NAME_FIRSTNAME = NAME.thenComparing(Comparator.comparing(person -> person.getFirstName().get(), Collator.getInstance()));

	/**
	 * Display title.
	 *
	 * @return display title
	 *
	 * @version 0.10.0
	 * @since 0.8.0
	 */
	@Override
    public SimpleStringProperty getDisplayTitle() {
    	return getFullName();
    }

	/**
	 * Full name of person.
	 *
	 * @return full name of the person
	 *
	 * @version 0.10.0
	 * @since 0.5.0
	 */
    public SimpleStringProperty getFullName() {
    	StringBuilder sbReturn = new StringBuilder();

		if ((getTitle() != null) && !getTitle().get().isEmpty()) {
			sbReturn.append(getTitle());
		}

		if ((getFirstName() != null) && !getFirstName().get().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append(" ");
			}
			sbReturn.append(getFirstName());
		}

		if ((getName() != null) && !getName().get().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append(" ");
			}
			sbReturn.append(getName());
		}

		return new SimpleStringProperty(sbReturn.toString());
    }

	/**
	 * Table name of person.
	 *
	 * @return table name of the person
	 *
	 * @version 0.10.0
	 * @since 0.5.0
	 */
    public SimpleStringProperty getTableName() {
    	StringBuilder sbReturn = new StringBuilder();

		if ((getName() != null) && !getName().get().isEmpty()) {
			sbReturn.append(getName());
		}

		if ((getTitle() != null) && !getTitle().get().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append(", ");
			}
			sbReturn.append(getTitle());
		}

		if ((getFirstName() != null) && !getFirstName().get().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append(", ");
			}
			sbReturn.append(getFirstName());
		}

		return new SimpleStringProperty(sbReturn.toString());
    }

	/**
	 * Filename name of person.
	 *
	 * @return filename name of the person
	 *
	 * @version 0.10.0
	 * @since 0.8.0
	 */
    public SimpleStringProperty getFileName() {
    	StringBuilder sbReturn = new StringBuilder();

		if ((getName() != null) && !getName().get().isEmpty()) {
			sbReturn.append(getName().get().toLowerCase());
		}

		if ((getFirstName() != null) && !getFirstName().get().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append("_");
			}
			sbReturn.append(getFirstName().get().toLowerCase());
		}

		return new SimpleStringProperty(sbReturn.toString());
    }

	/**
	 * Primary address.
	 *
	 * @return primary address
	 *
	 * @version 0.6.0
	 * @since 0.6.0
	 */
    public Address getPrimaryAddress() {
    	return getAddress()
    			.stream()
    			.filter(ContactModel.ISPRIMARY)
    			.findFirst()
    			.orElse(null);
    }

	/**
	 * Primary email.
	 *
	 * @return primary email
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
    public EMail getPrimaryEMail() {
    	return getEMail()
    			.stream()
    			.filter(ContactModel.ISPRIMARY)
    			.findFirst()
    			.orElse(null);
    }

}

/* EOF */

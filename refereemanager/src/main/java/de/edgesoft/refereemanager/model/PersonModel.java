package de.edgesoft.refereemanager.model;

import java.text.Collator;
import java.util.Comparator;
import java.util.function.Predicate;

import de.edgesoft.edgeutils.files.FileUtils;
import de.edgesoft.refereemanager.jaxb.Address;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.PersonRoleType;
import de.edgesoft.refereemanager.jaxb.PhoneNumber;
import javafx.beans.property.SimpleStringProperty;

/**
 * Person model, additional methods for jaxb model class.
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
public class PersonModel extends Person {

	/**
	 * Comparator for people by name.
	 *
	 * @since 0.8.0
	 */
	public static final Comparator<Person> NAME = Comparator.comparing(person -> person.getName().getValueSafe(), Collator.getInstance());

	/**
	 * Comparator for people by name then first ame.
	 *
	 * @since 0.10.0
	 */
	public static final Comparator<Person> NAME_FIRSTNAME = NAME.thenComparing(Comparator.comparing(person -> person.getFirstName().getValueSafe(), Collator.getInstance()));

	/**
	 * Filter predicate for all people.
	 *
	 * @since 0.15.0
	 */
	public static Predicate<Person> ALL = person -> true;

	/**
	 * Returns filter predicate for given role.
	 *
	 * @param theRole role
	 * @return predicate
	 *
	 * @since 0.15.0
	 */
	public static Predicate<Person> getRolePredicate(PersonRoleType theRole) {
		return person -> person.getRole() == theRole;
	}

	/**
	 * Filter predicate for people with email addresses.
	 *
	 * @since 0.10.0
	 */
	public static Predicate<PersonModel> HAS_EMAIL = person -> (person.getPrimaryEMail() != null);

	/**
	 * Filter predicate for people with postal addresses.
	 *
	 * @since 0.12.0
	 */
	public static Predicate<PersonModel> HAS_ADDRESS = person -> (person.getPrimaryAddress() != null);

	/**
	 * Display title.
	 *
	 * @return display title
	 *
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
	 */
	public SimpleStringProperty getFullName() {
		StringBuilder sbReturn = new StringBuilder();

		if ((getTitle() != null) && !getTitle().getValueSafe().isEmpty()) {
			sbReturn.append(getTitle().get());
		}

		if ((getFirstName() != null) && !getFirstName().getValueSafe().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append(" ");
			}
			sbReturn.append(getFirstName().get());
		}

		if ((getName() != null) && !getName().getValueSafe().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append(" ");
			}
			sbReturn.append(getName().get());
		}

		return new SimpleStringProperty(sbReturn.toString());
	}

	/**
	 * Table name of person.
	 *
	 * @return table name of the person
	 */
	public SimpleStringProperty getTableName() {
		StringBuilder sbReturn = new StringBuilder();

		if ((getName() != null) && !getName().getValueSafe().isEmpty()) {
			sbReturn.append(getName().get());
		}

		if ((getTitle() != null) && !getTitle().getValueSafe().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append(", ");
			}
			sbReturn.append(getTitle().get());
		}

		if ((getFirstName() != null) && !getFirstName().getValueSafe().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append(", ");
			}
			sbReturn.append(getFirstName().get());
		}

		return new SimpleStringProperty(sbReturn.toString());
	}

	/**
	 * Filename name of person.
	 *
	 * @return filename name of the person
	 *
	 * @since 0.8.0
	 */
	public SimpleStringProperty getFileName() {
		StringBuilder sbReturn = new StringBuilder();

		if ((getName() != null) && !getName().getValueSafe().isEmpty()) {
			sbReturn.append(getName().get().toLowerCase());
		}

		if ((getFirstName() != null) && !getFirstName().getValueSafe().isEmpty()) {
			if (sbReturn.length() > 0) {
				sbReturn.append("_");
			}
			sbReturn.append(getFirstName().get().toLowerCase());
		}

		return new SimpleStringProperty(FileUtils.cleanFilename(sbReturn.toString()));
	}

	/**
	 * Returns if image file given by image path and filename exists.
	 *
	 * @param theImagePath image path
	 * @return does image file given by image path and filename exist
	 *
	 * @since 0.12.0
	 */
	public boolean existsImageFile(final String theImagePath) {

		if ((getFileName() == null) || getFileName().getValueSafe().isEmpty()) {
			return false;
		}

		return FileUtils.existsFile(theImagePath, String.format("%s.jpg", getFileName().getValue()));

	}

	/**
	 * Primary address.
	 *
	 * @return primary address
	 *
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
	 * @since 0.8.0
	 */
	public EMail getPrimaryEMail() {
		return getEMail()
				.stream()
				.filter(ContactModel.ISPRIMARY)
				.findFirst()
				.orElse(null);
	}

	/**
	 * Primary phone number.
	 *
	 * @return primary phone number
	 *
	 * @since 0.12.0
	 */
	public PhoneNumber getPrimaryPhoneNumber() {
		return getPhoneNumber()
				.stream()
				.filter(ContactModel.ISPRIMARY)
				.findFirst()
				.orElse(null);
	}

}

/* EOF */

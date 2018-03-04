package de.edgesoft.refereemanager.model;

import java.util.function.Predicate;

import de.edgesoft.refereemanager.jaxb.Contact;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;

/**
 * Contact model, additional methods for jaxb model class.
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
public class ContactModel extends Contact {

	/**
	 * Filter predicate for primary contacts.
	 */
	public static final Predicate<Contact> ISPRIMARY = (contact -> (contact.getIsPrimary() == null) || contact.getIsPrimary().get());

	/**
	 * Use private data only?
	 *
	 * This was introduced, because it is far too difficult to provide
	 * the getter methods with a parameter.
	 *
	 * @todo check if there is a better way of implementing this
	 */
	public static boolean isPrivateOnly = false;

	/**
	 * Is contact private?
	 *
	 * @return is contact private
	 */
	public boolean isPrivate() {

		return (getContactType() == null) || ((getContactType() != null) && getContactType().getId().equals(Prefs.get(PrefKey.CONTACT_PRIVATE)));

	}

}

/* EOF */

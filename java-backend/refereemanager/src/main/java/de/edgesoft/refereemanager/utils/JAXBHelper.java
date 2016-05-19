package de.edgesoft.refereemanager.utils;

import org.jooq.types.UInteger;

import de.edgesoft.edgeutils.commons.IDType;

/**
 * Provides methods and properties for jaxb.
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
 * @version 0.1.0
 * @since 0.1.0
 */
public class JAXBHelper {

	/** Template for IDs. */
	public static final String IDS = "%s.%s";

	/**
	 * Returns XML ID from type and string id.
	 * 
	 * @param theType type
	 * @param theID id
	 * @return xml id
	 */
	public static String getID(IDType theType, String theID) {
		return String.format(IDS, theType.getClass().getSimpleName(), theID);
	}
	
	/**
	 * Returns XML ID from type and uint id.
	 * 
	 * @param theType type
	 * @param theID id
	 * @return xml id
	 */
	public static String getID(IDType theType, UInteger theID) {
		return getID(theType, theID.toString());
	}
	
}

/* EOF */

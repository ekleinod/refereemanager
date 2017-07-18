package de.edgesoft.refereemanager.utils;

import java.util.Objects;

import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.model.ContentModel;

/**
 * Database operations.
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
 * @since 0.7.0
 */
public class DBOperations {

	/**
	 * Removes unused clubs.
	 *
	 * @param theDB database
	 */
	public static void removeClubs(final de.edgesoft.refereemanager.jaxb.RefereeManager theDB) {

		Objects.requireNonNull(theDB, "database must not be null");

		ContentModel theContent = ((ContentModel) theDB.getContent());

		int iBefore = theContent.getClub().size();

		theContent.getClub().removeAll(theContent.getNonReferencedClubs());

		int iAfter = theContent.getClub().size();

		RefereeManager.logger.info(String.format("Removed %d of %d clubs, %d clubs remaining.", iBefore - iAfter, iBefore, iAfter));


	}

}

/* EOF */

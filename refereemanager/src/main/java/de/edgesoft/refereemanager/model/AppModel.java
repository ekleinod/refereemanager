package de.edgesoft.refereemanager.model;

import java.nio.file.Paths;

import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;

/**
 * Gebu application model.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of "Das Gebu-Programm".
 *
 * "Das Gebu-Programm" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Das Gebu-Programm" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "Das Gebu-Programm".  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.10.0
 * @since 0.10.0
 */
public final class AppModel {

	/**
	 * Referee manager data.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private static RefereeManager dtaRefMan = null;

	/**
	 * Flag, if data is modified.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private static boolean isModified = false;

	/**
     * Returns referee manager data.
     *
     * @return referee manager data
	 *
	 * @version 0.10.0
	 * @since 0.10.0
     */
    public static RefereeManager getData() {
        return dtaRefMan;
    }

	/**
     * Sets referee manager data.
     *
     * @return referee manager data
	 *
	 * @version 0.10.0
	 * @since 0.10.0
     */
    public static void setData(final RefereeManager theData) {
        dtaRefMan = theData;
    }

	/**
     * Is data modified?.
     *
     * @return Is data modified?
	 *
	 * @version 0.10.0
	 * @since 0.10.0
     */
    public static boolean isModified() {
        return isModified;
    }

	/**
     * Sets modified flag.
     *
     * @param modified data modified?
	 *
	 * @version 0.10.0
	 * @since 0.10.0
     */
    public static void setModified(final boolean modified) {
        isModified = modified;
    }

	/**
	 * Returns the file name.
	 *
	 * @return filename
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static String getFilename() {
		return Prefs.get(PrefKey.FILE);
    }

	/**
	 * Sets the file name.
	 *
	 * @param theFilename filename
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static void setFilename(final String theFilename) {

		if (theFilename == null) {
			Prefs.put(PrefKey.FILE, "");
		} else {
			Prefs.put(PrefKey.FILE, theFilename);
			Prefs.put(PrefKey.PATH, Paths.get(theFilename).getParent().toString());
		}

    }

}

/* EOF */

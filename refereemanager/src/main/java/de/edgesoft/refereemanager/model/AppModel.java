package de.edgesoft.refereemanager.model;

import java.nio.file.Paths;

import de.edgesoft.refereemanager.jaxb.ObjectFactory;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;

/**
 * Referee manager application model.
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
 * @since 0.10.0
 */
public final class AppModel {

	/**
	 * Referee manager data.
	 */
	private static RefereeManager dtaRefMan = null;

	/**
	 * Flag, if data is modified.
	 */
	private static boolean isModified = false;

	/**
	 * Central object factory instance for all classes.
	 */
	public static final ObjectFactory factory = new ObjectFactory();

	/**
     * Returns referee manager data.
     *
     * @return referee manager data
     */
    public static RefereeManager getData() {
        return dtaRefMan;
    }

	/**
     * Sets referee manager data.
     *
     * @return referee manager data
     */
    public static void setData(final RefereeManager theData) {
        dtaRefMan = theData;
    }

	/**
     * Is data modified?.
     *
     * @return Is data modified?
     */
    public static boolean isModified() {
        return isModified;
    }

	/**
     * Sets modified flag.
     *
     * @param modified data modified?
     */
    public static void setModified(final boolean modified) {
        isModified = modified;
    }

	/**
	 * Returns the file name.
	 *
	 * @return filename
	 */
	public static String getFilename() {
		return Prefs.get(PrefKey.FILE);
    }

	/**
	 * Sets the file name.
	 *
	 * @param theFilename filename
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

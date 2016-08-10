package de.edgesoft.refereemanager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

import de.edgesoft.refereemanager.utils.PrefKey;

/**
 * Preferences of the referee manager.
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
 * @version 0.8.0
 * @since 0.8.0
 */
public class Prefs {
	
	/** Preferences object. */
	private static Preferences preferences = null;
	
	/**
	 * Returns preferences.
	 * 
	 * @return preferences
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	private static Preferences getPreferences() {
		if (preferences == null) {
			preferences = Preferences.userNodeForPackage(Prefs.class);
		}
		return preferences;
	}

	/**
	 * Get preference for key.
	 * 
	 * @param theKey preference key
	 * @return preference value
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static String get(final PrefKey theKey) {
		
		switch (theKey) {
			case CONTACT_PRIVATE:
				return getPreferences().get(theKey.value(), "ContactType.p");
			case COUNTRY_CODE:
				return getPreferences().get(theKey.value(), "49");
				
			case FILENAME_PATTERN_DATABASE:
				return getPreferences().get(theKey.value(), "refereemanager_%04d.xml");
				
			case PATH_TEMPLATES:
				return getPreferences().get(theKey.value(), "src/main/templates/");
				
			case TEMPLATE_EMAIL:
				return getPreferences().get(theKey.value(), "email/email.mmd");
			case TEMPLATE_LETTER:
				return getPreferences().get(theKey.value(), "letter/letter.mmd");
				
			default:
				return getPreferences().get(theKey.value(), "");
		}
		
	}

	/**
	 * Set preference value for key.
	 * 
	 * @param theKey preference key
	 * @param theValue value
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void put(final PrefKey theKey, final String theValue) {
		getPreferences().put(theKey.value(), theValue);
	}

	/**
	 * Exports preferences.
	 * 
	 * @param theStream output stream
	 * 
	 * @throws BackingStoreException 
	 * @throws IOException 
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void exportPrefs(final OutputStream theStream) throws IOException, BackingStoreException {
		getPreferences().exportNode(theStream);
	}

	/**
	 * Imports preferences.
	 * 
	 * @param theStream input stream
	 * 
	 * @throws IOException 
	 * @throws InvalidPreferencesFormatException 
	 * @throws BackingStoreException 
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void importPrefs(final InputStream theStream) throws IOException, InvalidPreferencesFormatException, BackingStoreException {
		getPreferences().clear();
		Preferences.importPreferences(theStream);
	}

}

/* EOF */

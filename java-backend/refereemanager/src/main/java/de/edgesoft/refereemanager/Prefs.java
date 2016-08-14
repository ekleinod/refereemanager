package de.edgesoft.refereemanager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

import de.edgesoft.refereemanager.utils.DateTimeFormat;
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
			case FILENAME_PATTERN_REFEREE_DATA:
				return getPreferences().get(theKey.value(), "%s.mmd");
				
			case LOCALE:
				return getPreferences().get(theKey.value(), Locale.GERMANY.toLanguageTag());
				
			case PATH_TEMPLATES:
				return getPreferences().get(theKey.value(), "src/main/templates/");
				
			case TEMPLATE_DOCUMENT:
				return getPreferences().get(theKey.value(), "document/document.mmd");
			case TEMPLATE_EMAIL:
				return getPreferences().get(theKey.value(), "email/email.mmd");
			case TEMPLATE_LETTER:
				return getPreferences().get(theKey.value(), "letter/letter.mmd");
			case TEMPLATE_REFEREE_DATA:
				return getPreferences().get(theKey.value(), "referee-data/referee-data.mmd");
				
			case TEMPLATE_VARIABLE_SEPARATOR:
				return getPreferences().get(theKey.value(), "::");
				
			default:
				return getPreferences().get(theKey.value(), "");
		}
		
	}

	/**
	 * Get preference for date/time format.
	 * 
	 * @param theKey date time format
	 * @return preference value
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static String get(final DateTimeFormat theKey) {
		
		switch (theKey) {
			case DATELONG:
				return getPreferences().get(theKey.value(), "d. MMMM yyyy");
			case DATEMEDIUM:
				return getPreferences().get(theKey.value(), "dd.MM.yyyy");
			case DATEYEAR:
				return getPreferences().get(theKey.value(), "yyyy");
				
			case DATETIMELONG:
				return getPreferences().get(theKey.value(), "ccc: d. MMM yyyy, hh:mm:ss");
				
			default:
				return getPreferences().get(theKey.value(), "'unknown format'");
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
		put(theKey.value(), theValue);
	}

	/**
	 * Set preference value for text key.
	 * 
	 * @param theKey text key
	 * @param theValue value
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void put(final String theKey, final String theValue) {
		getPreferences().put(theKey, theValue);
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

package de.edgesoft.refereemanager.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

import de.edgesoft.refereemanager.RefereeManager;
import javafx.stage.Screen;

/**
 * Preferences of the referee manager.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
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
 * @version 0.12.0
 * @since 0.8.0
 */
public class Prefs {

	/**
	 * Preferences object.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
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
			preferences = Preferences.userNodeForPackage(RefereeManager.class);
		}
		return preferences;
	}

	/**
	 * Get preference for key.
	 *
	 * @param theKey preference key
	 * @return preference value
	 *
	 * @version 0.12.0
	 * @since 0.8.0
	 */
	public static String get(final PrefKey theKey) {

		switch (theKey) {
			case CONTACT_PRIVATE:
				return getPreferences().get(theKey.value(), "ContactType.p");
			case COUNTRY_CODE:
				return getPreferences().get(theKey.value(), "49");

			case DOCUMENTS_TEMPLATE_DOCUMENT:
				return getPreferences().get(theKey.value(), "document/document.mmd");

			case EMAIL_TEMPLATE_EMAIL:
				return getPreferences().get(theKey.value(), "email/email.mmd");

			case FILENAME_PATTERN_DATABASE:
				return getPreferences().get(theKey.value(), "refereemanager_%04d.xml");
			case FILENAME_PATTERN_REFEREE_DATA:
				return getPreferences().get(theKey.value(), "%s.mmd");
			case FILENAME_PATTERN_REFEREE_MERGE:
				return getPreferences().get(theKey.value(), "merge_%s.tex");
			case FILENAME_PATTERN_REFEREES_MERGE:
				return getPreferences().get(theKey.value(), "merge_all.tex");

			case LETTERS_TEMPLATE_LETTER:
				return getPreferences().get(theKey.value(), "letter/letter.mmd");
			case LETTERS_TEMPLATE_MERGE_SINGLE:
				return getPreferences().get(theKey.value(), "letter/merge_referee.tex");
			case LETTERS_TEMPLATE_MERGE_ALL:
				return getPreferences().get(theKey.value(), "letter/merge_referees.tex");

			case LOCALE:
				return getPreferences().get(theKey.value(), Locale.GERMANY.toLanguageTag());

			case REFEREE_COMMUNICATION_SPLIT_0:
				return getPreferences().get(theKey.value(), Double.toString(0.5));
			case REFEREE_COMMUNICATION_SPLIT_1:
				return getPreferences().get(theKey.value(), Double.toString(0.7));

			case REFEREE_OVERVIEW_SPLIT:
				return getPreferences().get(theKey.value(), Double.toString(0.6));

			case STAGE_WIDTH:
				return getPreferences().get(theKey.value(), Double.toString(800));
			case STAGE_X:
				return getPreferences().get(theKey.value(), Double.toString((Screen.getPrimary().getBounds().getWidth() - 800) / 2));

			case STAGE_HEIGHT:
				return getPreferences().get(theKey.value(), Double.toString(600));
			case STAGE_Y:
				return getPreferences().get(theKey.value(), Double.toString((Screen.getPrimary().getBounds().getHeight() - 600) / 2));

			case STAGE_MAXIMIZED:
				return getPreferences().get(theKey.value(), Boolean.FALSE.toString());

			case TITLE_FULLPATH:
				return getPreferences().get(theKey.value(), Boolean.FALSE.toString());

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

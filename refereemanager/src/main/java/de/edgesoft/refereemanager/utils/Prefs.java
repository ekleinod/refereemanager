package de.edgesoft.refereemanager.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
public class Prefs {

	/**
	 * Preferences object.
	 *
	 * @since 0.10.0
	 */
	private static Preferences preferences = null;

	/**
	 * Returns preferences.
	 *
	 * @return preferences
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
	 */
	public static String get(final PrefKey theKey) {

		switch (theKey) {
			case AREA_CODE:
				return getPreferences().get(theKey.value(), "30");

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

			case OTHER_TITLE_FULLPATH:
				return getPreferences().get(theKey.value(), Boolean.FALSE.toString());
			case OTHER_DATA_SORT_LOADING:
				return getPreferences().get(theKey.value(), Boolean.FALSE.toString());

			case OVERVIEW_CLUB_SPLIT:
				return getPreferences().get(theKey.value(), Double.toString(0.6));
			case OVERVIEW_LEAGUE_GAME_SPLIT:
				return getPreferences().get(theKey.value(), Double.toString(0.6));
			case OVERVIEW_OTHER_EVENT_SPLIT:
				return getPreferences().get(theKey.value(), Double.toString(0.6));
			case OVERVIEW_PERSON_SPLIT:
				return getPreferences().get(theKey.value(), Double.toString(0.6));
			case OVERVIEW_REFEREE_SPLIT:
				return getPreferences().get(theKey.value(), Double.toString(0.6));
			case OVERVIEW_SEXTYPE_SPLIT:
				return getPreferences().get(theKey.value(), Double.toString(0.6));
			case OVERVIEW_TOURNAMENT_SPLIT:
				return getPreferences().get(theKey.value(), Double.toString(0.6));
			case OVERVIEW_TRAINEE_SPLIT:
				return getPreferences().get(theKey.value(), Double.toString(0.6));

			case REFEREE_COMMUNICATION_SPLIT_0:
				return getPreferences().get(theKey.value(), Double.toString(0.5));
			case REFEREE_COMMUNICATION_SPLIT_1:
				return getPreferences().get(theKey.value(), Double.toString(0.7));

			case REFEREE_REPORT_LEAGUE_GAMES:
				return getPreferences().get(theKey.value(), "OSR_%1$s_%2$s_%3$s-%4$s.pdf");
			case REFEREE_REPORT_TOURNAMENTS:
				return getPreferences().get(theKey.value(), "OSR_%1$s_%2$s.pdf");

			case STAGE_WIDTH:
				return getPreferences().get(theKey.value(), Double.toString(1200));
			case STAGE_X:
				return getPreferences().get(theKey.value(), Double.toString((Screen.getPrimary().getBounds().getWidth() - 1200) / 2));

			case STAGE_HEIGHT:
				return getPreferences().get(theKey.value(), Double.toString(800));
			case STAGE_Y:
				return getPreferences().get(theKey.value(), Double.toString((Screen.getPrimary().getBounds().getHeight() - 800) / 2));

			case STAGE_MAXIMIZED:
				return getPreferences().get(theKey.value(), Boolean.FALSE.toString());

			case STATISTICS_TEMPLATE_OVERVIEW:
				return getPreferences().get(theKey.value(), "statistics/overview.html");

			case TEXTS_TEMPLATE_TEXT:
				return getPreferences().get(theKey.value(), "text/text.mmd");

			default:
				return getPreferences().get(theKey.value(), "");
		}

	}

	/**
	 * Set preference value for key.
	 *
	 * @param theKey preference key
	 * @param theValue value
	 */
	public static void put(final PrefKey theKey, final String theValue) {
		put(theKey.value(), theValue);
	}

	/**
	 * Set preference value for text key.
	 *
	 * @param theKey text key
	 * @param theValue value
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
	 */
	public static void importPrefs(final InputStream theStream) throws IOException, InvalidPreferencesFormatException, BackingStoreException {
		getPreferences().clear();
		Preferences.importPreferences(theStream);
	}

	/**
	 * Returns preferences map.
	 *
	 * @return preferences map
	 *
	 * @since 0.12.0
	 */
	public static Map<String, Object> getPrefMap() {

		Map<String, Object> mapReturn = new HashMap<>();

		for (PrefKey theKey : PrefKey.values()) {
			mapReturn.put(theKey.value(), get(theKey));
		}

		return mapReturn;
	}

}

/* EOF */

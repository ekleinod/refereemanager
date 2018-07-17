
package de.edgesoft.refereemanager.utils;

/**
 * Preference keys.
 *
 * For enums I use the coding style of jaxb, so there will be no inconsistencies.
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
public enum PrefKey {

	AREA_CODE,

	COUNTRY_CODE,
	CONTACT_PRIVATE,

	DOCUMENTS_TEMPLATE_DOCUMENT,

	EMAIL_FROM_EMAIL,
	EMAIL_FROM_NAME,
	EMAIL_SMTP_HOST,
	EMAIL_SMTP_PASSWORD,
	EMAIL_SMTP_USERNAME,
	EMAIL_TEMPLATE_EMAIL,
	EMAIL_REPLY_TO_EMAIL1,
	EMAIL_REPLY_TO_NAME1,
	EMAIL_REPLY_TO_EMAIL2,
	EMAIL_REPLY_TO_NAME2,

	FILE,

	FILENAME_PATTERN_DATABASE,
	FILENAME_PATTERN_REFEREE_DATA,
	FILENAME_PATTERN_REFEREE_MERGE,
	FILENAME_PATTERN_REFEREES_MERGE,

	LETTERS_TEMPLATE_LETTER,
	LETTERS_TEMPLATE_MERGE_SINGLE,
	LETTERS_TEMPLATE_MERGE_ALL,

	LOCAL_TRAININGLEVEL,

	LOCALE,

	OTHER_TITLE_FULLPATH,
	OTHER_DATA_SORT_LOADING,

	OVERVIEW_CLUB_SPLIT,
	OVERVIEW_CONTACT_SPLIT,
	OVERVIEW_LEAGUE_SPLIT,
	OVERVIEW_LEAGUE_GAME_SPLIT,
	OVERVIEW_OTHER_EVENT_SPLIT,
	OVERVIEW_PERSON_SPLIT,
	OVERVIEW_REFEREE_SPLIT,
	OVERVIEW_ROLETYPE_SPLIT,
	OVERVIEW_SEXTYPE_SPLIT,
	OVERVIEW_STATUSTYPE_SPLIT,
	OVERVIEW_TOURNAMENT_SPLIT,
	OVERVIEW_TRAINEE_SPLIT,
	OVERVIEW_TRAININGLEVELTYPE_SPLIT,
	OVERVIEW_VENUE_SPLIT,

	PATH,

	PATHS_IMAGE,
	PATHS_TEMPLATE,
	PATHS_XSD,

	PREFERENCES_FILE,

	REFEREE_COMMUNICATION_FILE,
	REFEREE_COMMUNICATION_OUTPUT_PATH,
	REFEREE_COMMUNICATION_LAST_ATTACHMENT_PATH,
	REFEREE_COMMUNICATION_SPLIT_0,
	REFEREE_COMMUNICATION_SPLIT_1,

	REFEREE_REPORT_LEAGUE_GAMES,
	REFEREE_REPORT_PATH,
	REFEREE_REPORT_TOURNAMENTS,

	STAGE_HEIGHT,
	STAGE_MAXIMIZED,
	STAGE_WIDTH,
	STAGE_X,
	STAGE_Y,

	STATISTICS_TEMPLATE_OVERVIEW,

	TEXTS_TEMPLATE_TEXT,
	;

	private final String value;

	PrefKey() {
		value = name().toLowerCase();
	}

	public String value() {
			return value;
	}

	public static PrefKey fromValue(String v) {
		for (PrefKey c: PrefKey.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}

/* EOF */

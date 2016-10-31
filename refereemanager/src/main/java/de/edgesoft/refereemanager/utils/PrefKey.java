
package de.edgesoft.refereemanager.utils;

/**
 * Preference keys.
 *
 * For enums I use the coding style of jaxb, so there will be no inconsistencies.
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
 * @version 0.10.0
 * @since 0.8.0
 */
public enum PrefKey {

	AREA_CODE,

	COUNTRY_CODE,
	CONTACT_PRIVATE,

	COMMUNICATION_FROM_EMAIL,
	COMMUNICATION_FROM_NAME,
	COMMUNICATION_SMTP_HOST,
	COMMUNICATION_SMTP_USERNAME,
	COMMUNICATION_SMTP_PASSWORD,
	COMMUNICATION_TEMPLATE_EMAIL,
	COMMUNICATION_TEMPLATE_LETTER,
	COMMUNICATION_TEMPLATE_MERGE_SINGLE,
	COMMUNICATION_TEMPLATE_MERGE_ALL,
	COMMUNICATION_TO_EMAIL,
	COMMUNICATION_TO_NAME,

	FILE,

	FILENAME_PATTERN_DATABASE,
	FILENAME_PATTERN_REFEREE_DATA,
	FILENAME_PATTERN_REFEREE_MERGE,
	FILENAME_PATTERN_REFEREES_MERGE,

	IMAGE_PATH,

	LOCAL_TRAININGLEVEL,

	LOCALE,

	MAXIMIZED,

	MY_EMAIL,
	MY_NAME,

	PATH,
	PATH_DATABASE,
	PATH_OUTPUT,
	PATH_TEMPLATES,

	PREFERENCES_FILE,
	
	REFEREE_OVERVIEW_SPLIT,
	REFEREE_COMMUNICATION_SPLIT_0,
	REFEREE_COMMUNICATION_SPLIT_1,

	STAGE_HEIGHT,
	STAGE_WIDTH,
	STAGE_X,
	STAGE_Y,

	TEMPLATE_DOCUMENT,
	TEMPLATE_REFEREE_DATA,

	TEMPLATE_PATH,

	TEMPLATE_VARIABLE_SEPARATOR,

	TITLE_FULLPATH,
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

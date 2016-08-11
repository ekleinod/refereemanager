
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
 * @version 0.8.0
 * @since 0.8.0
 */
public enum PrefKey {

	COUNTRY_CODE,
	CONTACT_PRIVATE,
	
	EMAIL_FROM,
	EMAIL_FROMNAME,
	EMAIL_TO,
	EMAIL_TONAME,
	EMAIL_SMTP_HOST,
	EMAIL_SMTP_USERNAME,
	EMAIL_SMTP_PASSWORD,
	
	FILENAME_PATTERN_DATABASE,
	FILENAME_PATTERN_REFEREE_DATA,
	
	MY_EMAIL,
	MY_NAME,
	
	PATH_DATABASE,
	PATH_OUTPUT,
	PATH_TEMPLATES,
	
	TEMPLATE_EMAIL,
	TEMPLATE_LETTER,
	TEMPLATE_REFEREE_DATA,
	
	TEMPLATE_VARIABLE_SEPARATOR
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

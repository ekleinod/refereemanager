
package de.edgesoft.refereemanager.utils;

/**
 * Document data variables.
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
public enum DocumentDataVariable {

	ATTACHMENT,
	BODY,
	CLOSING,
	DATE,
	FILENAME,
	OPENING,
	OPTIONS,
	SIGNATURE,
	SUBJECT,
	SUBTITLE,
	;

		private final String value;

		DocumentDataVariable() {
				value = name().toLowerCase();
		}

		public String value() {
				return value;
		}

		public static DocumentDataVariable fromValue(String v) {
				for (DocumentDataVariable c: DocumentDataVariable.values()) {
						if (c.value.equals(v)) {
								return c;
						}
				}
				throw new IllegalArgumentException(v);
		}

}

/* EOF */

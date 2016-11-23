
package de.edgesoft.refereemanager.utils;

import java.util.function.Predicate;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.StatusType;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.model.StatusTypeModel;

/**
 * Status types of console arguments.
 *
 * For enums I use the coding style of jaxb, so there will be no inconsistencies.
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
 * @version 0.6.0
 * @since 0.6.0
 */
public enum ArgumentStatusType {

		ALL(RefereeModel.ALL, StatusTypeModel.ALL),
		ACTIVE(RefereeModel.ACTIVE, StatusTypeModel.ACTIVE),
		INACTIVE(RefereeModel.INACTIVE, StatusTypeModel.INACTIVE);

		private final String value;
		private final Predicate<Referee> ref_filter;
		private final Predicate<StatusType> status_filter;

		ArgumentStatusType(final Predicate<Referee> theRefFilter, final Predicate<StatusType> theStatusFilter) {
				value = name().toLowerCase();
				ref_filter = theRefFilter;
				status_filter = theStatusFilter;
		}

		public Predicate<Referee> ref_filter() {
				return ref_filter;
		}

		public Predicate<StatusType> status_filter() {
				return status_filter;
		}

		public String value() {
				return value;
		}

		public static ArgumentStatusType fromValue(String v) {
				for (ArgumentStatusType c: ArgumentStatusType.values()) {
						if (c.value.equals(v)) {
								return c;
						}
				}
				throw new IllegalArgumentException(v);
		}

}

/* EOF */

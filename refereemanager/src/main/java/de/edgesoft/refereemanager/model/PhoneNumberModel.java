package de.edgesoft.refereemanager.model;

import de.edgesoft.refereemanager.jaxb.PhoneNumber;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import javafx.beans.property.SimpleStringProperty;

/**
 * PhoneNumber model, additional methods for jaxb model class.
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
 * @version 0.10.0
 * @since 0.6.0
 */
public class PhoneNumberModel extends PhoneNumber {

	/**
	 * Display title.
	 *
	 * @return display title
	 *
	 * @version 0.10.0
	 * @since 0.6.0
	 */
	@Override
		public SimpleStringProperty getDisplayTitle() {

			if (isPrivateOnly && !isPrivate()) {
			return null;
			}

			StringBuilder sbReturn = new StringBuilder();

			if ((getCountryCode() != null) && !getCountryCode().get().equals(Prefs.get(PrefKey.COUNTRY_CODE))) {
				sbReturn.append("+");
				sbReturn.append(getCountryCode().get());
				sbReturn.append(" ");
			}

		sbReturn.append("0");
		sbReturn.append((getAreaCode() == null) ? Prefs.get(PrefKey.AREA_CODE) : getAreaCode().get());
		sbReturn.append(" ");

		sbReturn.append(getNumber().get());

			if (!isPrivate()) {
				sbReturn.append(" (");
				sbReturn.append(getContactType().getShorttitle().get());
				sbReturn.append(")");
			}

			return new SimpleStringProperty(sbReturn.toString());

		}

}

/* EOF */

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
 * @version 0.12.0
 * @since 0.6.0
 */
public class PhoneNumberModel extends PhoneNumber {

	/**
	 * Returns number (null if private).
	 *
	 * @return number
	 *  @retval null if private number
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@Override
	public SimpleStringProperty getNumber() {

		if (isPrivateOnly && !isPrivate()) {
			return null;
		}

		return super.getNumber();

	}

	/**
	 * Returns country code.
	 *
	 * @return country code
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@Override
	public SimpleStringProperty getCountryCode() {
		return (super.getCountryCode() == null) ? new SimpleStringProperty(Prefs.get(PrefKey.COUNTRY_CODE)) : super.getCountryCode();
	}

	/**
	 * Returns area code.
	 *
	 * @return area code
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@Override
	public SimpleStringProperty getAreaCode() {
		return (super.getAreaCode() == null) ? new SimpleStringProperty(Prefs.get(PrefKey.AREA_CODE)) : super.getAreaCode();
	}

	/**
	 * Display title.
	 *
	 * @return display title
	 *
	 * @version 0.12.0
	 * @since 0.6.0
	 */
	@Override
	public SimpleStringProperty getDisplayTitle() {

		if (getNumber() == null) {
			return null;
		}

		StringBuilder sbReturn = new StringBuilder();

		if (!getCountryCode().getValue().equals(Prefs.get(PrefKey.COUNTRY_CODE))) {
			sbReturn.append(String.format("+%s ", getCountryCode().getValue()));
		}

		sbReturn.append(String.format("0%s %s", getAreaCode().getValue(), getNumber().getValue()));

		if (!isPrivate()) {
			sbReturn.append(String.format(" (%s)", getContactType().getShorttitle().getValue()));
		}

		return new SimpleStringProperty(sbReturn.toString());

	}

	/**
	 * Returns standard representation.
	 *
	 * @return standard representation
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	public SimpleStringProperty getStandard() {

		if (getNumber() == null) {
			return null;
		}

		return new SimpleStringProperty(String.format("+%s %s %s",
				getCountryCode().getValue(),
				getAreaCode().getValue(),
				getNumber().getValue()));

	}

	/**
	 * Returns vcard representation.
	 *
	 * @return vcard representation
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	public SimpleStringProperty getVCard() {

		if (getStandard() == null) {
			return null;
		}

		return new SimpleStringProperty(getStandard().getValue().replace(' ', '-'));

	}

}

/* EOF */

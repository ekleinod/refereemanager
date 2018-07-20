package de.edgesoft.refereemanager.model;

import java.text.MessageFormat;

import de.edgesoft.refereemanager.jaxb.Venue;
import javafx.beans.property.SimpleStringProperty;

/**
 * Venue model, additional methods for jaxb model class.
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
 * @version 0.15.0
 * @since 0.15.0
 */
public class VenueModel extends Venue {

	/**
	 * Display title.
	 *
	 * @return display title
	 */
	@Override
	public SimpleStringProperty getDisplayTitle() {
		return (getShorttitle() == null) ? ((getTitle() == null) ? super.getDisplayTitle() : getTitle()) : getShorttitle();
	}

	/**
	 * Geolocation.
	 *
	 * @return geolocation
	 */
	public SimpleStringProperty getGeolocation() {

		if ((getLatitude() == null) || (getLongitude() == null)) {
			return null;
		}

		return new SimpleStringProperty(MessageFormat.format("({0,number,#.#####},{1,number,#.#####})",
				(getLatitude() == null) ? 0 : getLatitude().getValue(),
				(getLongitude() == null) ? 0 : getLongitude().getValue()
				));
	}

}

/* EOF */

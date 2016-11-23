package de.edgesoft.refereemanager.model;

import de.edgesoft.refereemanager.jaxb.Wish;
import javafx.beans.property.SimpleStringProperty;

/**
 * Wish model, additional methods for jaxb model class.
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
public class WishModel extends Wish {

	/**
	 * Display title.
	 *
	 * @return display title
	 *
	 * @version 0.10.0
	 * @since 0.6.0
	 */
		public SimpleStringProperty getDisplayTitle() {
			StringBuilder sbReturn = new StringBuilder();

			if (getClub() != null) {
				sbReturn.append(getClub().getDisplayTitle().get());
			}

			if (getLeague() != null) {
				if (sbReturn.length() > 0) {
					sbReturn.append(", ");
				}
				sbReturn.append(getLeague().getDisplayTitle().get());
			}

			if (getSexType() != null) {
				if (sbReturn.length() > 0) {
					sbReturn.append(", ");
				}
				sbReturn.append(getSexType().getDisplayTitle().get());
			}

			if ((getSaturday() != null) && getSaturday().get()) {
				if (sbReturn.length() > 0) {
					sbReturn.append(", ");
				}
				sbReturn.append("sonnabends");
			}

			if ((getSunday() != null) && getSunday().get()) {
				if (sbReturn.length() > 0) {
					sbReturn.append(", ");
				}
				sbReturn.append("sonntags");
			}

			if ((getTournamentOnly() != null) && getTournamentOnly().get()) {
				if (sbReturn.length() > 0) {
					sbReturn.append(", ");
				}
				sbReturn.append("nur Turniere");
			}

			if ((getLeagueGamesOnly() != null) && getLeagueGamesOnly().get()) {
				if (sbReturn.length() > 0) {
					sbReturn.append(", ");
				}
				sbReturn.append("nur Ligaspiele");
			}

			return new SimpleStringProperty(sbReturn.toString());
		}

}

/* EOF */

package de.edgesoft.refereemanager.model;

import de.edgesoft.refereemanager.jaxb.RefereeEvent;
import javafx.beans.property.SimpleStringProperty;

/**
 * Referee event model, additional methods for jaxb model class.
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
public class RefereeEventModel extends RefereeEvent {

    /**
     * Returns referee report filename.
     *
     * @return referee report filename
     */
    public SimpleStringProperty getRefereeReportFilename() {
		return null;
    }

	/**
	 * Returns if referee report file exists.
	 *
	 * @return does referee report file exist?
	 */
	public boolean existsRefereeReportFile() {
		return false;
	}

}

/* EOF */

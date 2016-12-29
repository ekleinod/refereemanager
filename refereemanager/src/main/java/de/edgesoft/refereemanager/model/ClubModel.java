package de.edgesoft.refereemanager.model;

import de.edgesoft.edgeutils.files.FileUtils;
import de.edgesoft.refereemanager.jaxb.Club;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Club model, additional methods for jaxb model class.
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
 * @version 0.12.0
 * @since 0.5.0
 */
public class ClubModel extends Club {

	/**
	 * Returns if club is local club.
	 *
	 * @return is club local club
	 *
	 * @version 0.10.0
	 * @since 0.9.0
	 */
	@Override
	public SimpleBooleanProperty getLocal() {
		return (super.getLocal() == null) ? new SimpleBooleanProperty(Boolean.FALSE) : super.getLocal();
	}

	/**
	 * Filename.
	 *
	 * @return filename
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@Override
	public SimpleStringProperty getFilename() {

		if ((super.getFilename() == null) || (super.getFilename().getValue() == null)) {
			return super.getFilename();
		}

		return new SimpleStringProperty(FileUtils.cleanFilename(super.getFilename().getValue()));
	}

}

/* EOF */

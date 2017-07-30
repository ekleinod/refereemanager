package de.edgesoft.refereemanager.model;

import java.time.LocalDate;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.edgeutils.files.FileUtils;
import de.edgesoft.refereemanager.jaxb.Tournament;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import javafx.beans.property.SimpleStringProperty;

/**
 * Tournament model, additional methods for jaxb model class.
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
public class TournamentModel extends Tournament {

    /**
     * Returns referee report filename.
     *
     * @return referee report filename
     */
    @Override
    public SimpleStringProperty getRefereeReportFilename() {
		return new SimpleStringProperty(FileUtils.cleanFilename(
				String.format(Prefs.get(PrefKey.REFEREE_REPORT_TOURNAMENTS),
						DateTimeUtils.formatDate((LocalDate) getFirstDay().getDate().getValue(), "yyyy-MM-dd"),
						getDisplayTitleShort().getValueSafe()
						)
				));
    }

	/**
	 * Returns if referee report file exists.
	 *
	 * @return does referee report file exist?
	 */
    @Override
	public boolean existsRefereeReportFile() {

		return FileUtils.existsFile(
				String.format(Prefs.get(PrefKey.REFEREE_REPORT_PATH), AppModel.getData().getContent().getSeason().getDisplayText().getValueSafe()),
				getRefereeReportFilename()
				);

	}

}

/* EOF */

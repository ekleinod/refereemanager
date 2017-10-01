package de.edgesoft.refereemanager.model;

import java.text.MessageFormat;
import java.util.function.Predicate;

import de.edgesoft.edgeutils.files.FileUtils;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.LeagueGame;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import javafx.beans.property.SimpleStringProperty;

/**
 * League game model, additional methods for jaxb model class.
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
public class LeagueGameModel extends LeagueGame {

	/**
	 * Filter predicate for all league games.
	 */
	public static Predicate<LeagueGame> ALL = leaguegame -> true;

	/**
	 * Returns filter predicate for given league.
	 *
	 * @param theLeague league
	 * @return predicate
	 */
	public static Predicate<LeagueGame> getLeaguePredicate(League theLeague) {
		return leaguegame -> leaguegame.getHomeTeam().getLeague() == theLeague;
	}

	/**
	 * Returns team text "home - off".
	 *
	 * @return team text
	 */
	public SimpleStringProperty getTeamText() {
		return new SimpleStringProperty(MessageFormat.format("{0} - {1}",
				getHomeTeam().getDisplayTitleShort().getValue(),
				getOffTeam().getDisplayTitleShort().getValue()));
	}

	/**
	 * Returns game number as formatted string.
	 *
	 * @return game number
	 */
	public SimpleStringProperty getGameNumberString() {

		if (getGameNumber() == null) {
			return null;
		}

		return new SimpleStringProperty(String.format("%03d", getGameNumber().getValue()));
	}

	/**
	 * Display title.
	 *
	 * @return display title
	 */
	@Override
	public SimpleStringProperty getDisplayTitle() {
		return getTeamText();
	}

    /**
     * Returns referee report filename.
     *
     * @return referee report filename
     */
    @Override
    public SimpleStringProperty getRefereeReportFilename() {
		return new SimpleStringProperty(FileUtils.cleanFilename(
				String.format(Prefs.get(PrefKey.REFEREE_REPORT_LEAGUE_GAMES),
						getHomeTeam().getLeague().getDisplayTitleShort().getValueSafe(),
						getGameNumberString().getValueSafe(),
						((TeamModel) getHomeTeam()).getFilename().getValueSafe(),
						((TeamModel) getOffTeam()).getFilename().getValueSafe()
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

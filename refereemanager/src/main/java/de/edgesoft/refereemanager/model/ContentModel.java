package de.edgesoft.refereemanager.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlTransient;

import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.jaxb.Content;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.Team;
import de.edgesoft.refereemanager.jaxb.Trainee;
import de.edgesoft.refereemanager.jaxb.Wish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Content model, additional methods for jaxb model class.
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
public class ContentModel extends Content {

	/**
	 * Observable list of referees (singleton).
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@XmlTransient
	private ObservableList<Referee> observableReferees = null;

	/**
	 * Observable list of trainees (singleton).
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@XmlTransient
	private ObservableList<Trainee> observableTrainees = null;

	/**
	 * Observable list of people (singleton).
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@XmlTransient
	private ObservableList<Person> observablePeople = null;


	/**
	 * Returns observable list of referees.
	 *
	 * @return observable list of referees
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public ObservableList<Referee> getObservableReferees() {
		if (observableReferees == null) {
			observableReferees = FXCollections.observableList(getReferee());
		}
		return observableReferees;
	}

	/**
	 * Returns observable list of trainees.
	 *
	 * @return observable list of trainees
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	public ObservableList<Trainee> getObservableTrainees() {
		if (observableTrainees == null) {
			observableTrainees = FXCollections.observableList(getTrainee());
		}
		return observableTrainees;
	}

	/**
	 * Returns observable list of people.
	 *
	 * @return observable list of people
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	public ObservableList<Person> getObservablePeople() {
		if (observablePeople == null) {
			observablePeople = FXCollections.observableList(getPerson());
		}
		return observablePeople;
	}


	/**
	 * Returns all referenced (used) clubs.
	 *
	 * @return referenced clubs (empty if there are none)
	 *
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public Set<Club> getReferencedClubs() {

		Set<Club> setReturn = new HashSet<>();

		for (Referee theReferee : getReferee()) {

			for (Wish theWish : theReferee.getPrefer()) {
				if (theWish.getClub() != null) {
					setReturn.add(theWish.getClub());
				}
			}

			for (Wish theWish : theReferee.getAvoid()) {
				if (theWish.getClub() != null) {
					setReturn.add(theWish.getClub());
				}
			}

			if (theReferee.getMember() != null) {
				setReturn.add(theReferee.getMember());
			}

			if (theReferee.getReffor() != null) {
				setReturn.add(theReferee.getReffor());
			}

		}

		return setReturn;

	}

	/**
	 * Returns all non-referenced (unused) clubs.
	 *
	 * @return non-referenced clubs (empty if there are none)
	 *
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public Set<Club> getNonReferencedClubs() {

		Set<Club> setReturn = new HashSet<>();

		setReturn.addAll(getClub());
		setReturn.removeAll(getReferencedClubs());

		return setReturn;

	}

	/**
	 * Returns all used leagues, i.e. leagues with assignments.
	 *
	 * @todo rewrite to look for assignments
	 *
	 * @return used leagues (empty if there are none)
	 *
	 * @version 0.10.0
	 * @since 0.9.0
	 */
	public List<League> getUsedLeagues() {

		List<League> lstReturn = new ArrayList<>();

		for (League theLeague : getLeague().stream().sorted(LeagueModel.RANK_DISPLAYTITLE).collect(Collectors.toList())) {

			if (theLeague.getNational().get()) {
				lstReturn.add(theLeague);
			}
		}

		return lstReturn;

	}

	/**
	 * Returns all local home teams for a league.
	 *
	 * @todo rewrite to look for assignments
	 *
	 * @param theLeague league
	 * @return used home teams (empty if there are none)
	 *
	 * @version 0.10.0
	 * @since 0.9.0
	 */
	public List<Team> getLocalHomeTeams(final League theLeague) {

		List<Team> lstReturn = new ArrayList<>();

		for (Team theTeam : getTeam().stream().sorted(TitledIDTypeModel.DISPLAYTITLE).collect(Collectors.toList())) {
			if ((theTeam.getLeague() != null) && (theTeam.getLeague() == theLeague)) {
				if ((theTeam.getClub() != null) && (theTeam.getClub().getLocal().get())) {
					lstReturn.add(theTeam);
				}
			}
		}

		return lstReturn;

	}

}

/* EOF */

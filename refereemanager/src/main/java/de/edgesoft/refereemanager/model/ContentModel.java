package de.edgesoft.refereemanager.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlTransient;

import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.jaxb.ContactType;
import de.edgesoft.refereemanager.jaxb.Content;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.LeagueGame;
import de.edgesoft.refereemanager.jaxb.OtherEvent;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.PersonRoleType;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeAssignmentType;
import de.edgesoft.refereemanager.jaxb.SexType;
import de.edgesoft.refereemanager.jaxb.StatusType;
import de.edgesoft.refereemanager.jaxb.Team;
import de.edgesoft.refereemanager.jaxb.Tournament;
import de.edgesoft.refereemanager.jaxb.Trainee;
import de.edgesoft.refereemanager.jaxb.TrainingLevelType;
import de.edgesoft.refereemanager.jaxb.Venue;
import de.edgesoft.refereemanager.jaxb.Wish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Content model, additional methods for jaxb model class.
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
 * @version 0.14.0
 * @since 0.5.0
 */
public class ContentModel extends Content {

	/**
	 * Observable list of referees (singleton).
	 *
	 * @since 0.10.0
	 */
	@XmlTransient
	private ObservableList<Referee> observableReferees = null;

	/**
	 * Observable list of trainees (singleton).
	 *
	 * @since 0.12.0
	 */
	@XmlTransient
	private ObservableList<Trainee> observableTrainees = null;

	/**
	 * Observable list of people (singleton).
	 *
	 * @since 0.12.0
	 */
	@XmlTransient
	private ObservableList<Person> observablePeople = null;

	/**
	 * Observable list of sex types (singleton).
	 *
	 * @since 0.13.0
	 */
	@XmlTransient
	private ObservableList<SexType> observableSexTypes = null;

	/**
	 * Observable list of league games (singleton).
	 *
	 * @since 0.15.0
	 */
	@XmlTransient
	private ObservableList<LeagueGame> observableLeagueGames = null;

	/**
	 * Observable list of tournaments (singleton).
	 *
	 * @since 0.15.0
	 */
	@XmlTransient
	private ObservableList<Tournament> observableTournaments = null;

	/**
	 * Observable list of other events (singleton).
	 *
	 * @since 0.15.0
	 */
	@XmlTransient
	private ObservableList<OtherEvent> observableOtherEvents = null;


	/**
	 * Returns observable list of referees.
	 *
	 * @return observable list of referees
	 *
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
	 * @since 0.12.0
	 */
	public ObservableList<Person> getObservablePeople() {
		if (observablePeople == null) {
			observablePeople = FXCollections.observableList(getPerson());
		}
		return observablePeople;
	}

	/**
	 * Returns observable list of sex types.
	 *
	 * @return observable list of sex types
	 *
	 * @since 0.13.0
	 */
	public ObservableList<SexType> getObservableSexTypes() {
		if (observableSexTypes == null) {
			observableSexTypes = FXCollections.observableList(getSexType());
		}
		return observableSexTypes;
	}

	/**
	 * Returns observable list of league games.
	 *
	 * @return observable list of league games
	 *
	 * @since 0.15.0
	 */
	public ObservableList<LeagueGame> getObservableLeagueGames() {
		if (observableLeagueGames == null) {
			observableLeagueGames = FXCollections.observableList(getLeagueGame());
		}
		return observableLeagueGames;
	}

	/**
	 * Returns observable list of tournaments.
	 *
	 * @return observable list of tournaments
	 *
	 * @since 0.15.0
	 */
	public ObservableList<Tournament> getObservableTournaments() {
		if (observableTournaments == null) {
			observableTournaments = FXCollections.observableList(getTournament());
		}
		return observableTournaments;
	}

	/**
	 * Returns observable list of other events.
	 *
	 * @return observable list of other events
	 *
	 * @since 0.15.0
	 */
	public ObservableList<OtherEvent> getObservableOtherEvents() {
		if (observableOtherEvents == null) {
			observableOtherEvents = FXCollections.observableList(getOtherEvent());
		}
		return observableOtherEvents;
	}


	/**
	 * Returns all referenced (used) clubs.
	 *
	 * @return referenced clubs (empty if there are none)
	 *
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

	/**
	 * Sorts all data in model.
	 *
	 * @since 0.12.0
	 */
	public void sortData() {

		List<Person> lstPerson = getPerson().stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList());
		getPerson().clear();
		getPerson().addAll(lstPerson);

		List<Referee> lstReferee = getReferee().stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList());
		getReferee().clear();
		getReferee().addAll(lstReferee);

		List<Trainee> lstTrainee = getTrainee().stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList());
		getTrainee().clear();
		getTrainee().addAll(lstTrainee);

		List<League> lstLeague = getLeague().stream().sorted(LeagueModel.RANK_DISPLAYTITLE).collect(Collectors.toList());
		getLeague().clear();
		getLeague().addAll(lstLeague);

		List<Club> lstClub = getClub().stream().sorted(TitledIDTypeModel.DISPLAYTITLE).collect(Collectors.toList());
		getClub().clear();
		getClub().addAll(lstClub);

		List<Team> lstTeam = getTeam().stream().sorted(TitledIDTypeModel.DISPLAYTITLE).collect(Collectors.toList());
		getTeam().clear();
		getTeam().addAll(lstTeam);

		List<LeagueGame> lstLeagueGame = getLeagueGame().stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList());
		getLeagueGame().clear();
		getLeagueGame().addAll(lstLeagueGame);

		List<Tournament> lstTournament = getTournament().stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList());
		getTournament().clear();
		getTournament().addAll(lstTournament);

		List<OtherEvent> lstOtherEvent = getOtherEvent().stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList());
		getOtherEvent().clear();
		getOtherEvent().addAll(lstOtherEvent);

		List<Venue> lstVenue = getVenue().stream().sorted(TitledIDTypeModel.DISPLAYTITLE).collect(Collectors.toList());
		getVenue().clear();
		getVenue().addAll(lstVenue);

		List<SexType> lstSexType = getSexType().stream().sorted(TitledIDTypeModel.TITLE).collect(Collectors.toList());
		getSexType().clear();
		getSexType().addAll(lstSexType);

		List<ContactType> lstContactType = getContactType().stream().sorted(TitledIDTypeModel.TITLE).collect(Collectors.toList());
		getContactType().clear();
		getContactType().addAll(lstContactType);

		List<StatusType> lstStatusType = getStatusType().stream().sorted(TitledIDTypeModel.TITLE).collect(Collectors.toList());
		getStatusType().clear();
		getStatusType().addAll(lstStatusType);

		List<RefereeAssignmentType> lstRefereeAssignmentType = getRefereeAssignmentType().stream().sorted(TitledIDTypeModel.DISPLAYTITLE).collect(Collectors.toList());
		getRefereeAssignmentType().clear();
		getRefereeAssignmentType().addAll(lstRefereeAssignmentType);

		List<PersonRoleType> lstPersonRoleType = getRoleType().stream().sorted(TitledIDTypeModel.DISPLAYTITLE).collect(Collectors.toList());
		getRoleType().clear();
		getRoleType().addAll(lstPersonRoleType);

		List<TrainingLevelType> lstTrainingLevelType = getTrainingLevelType().stream().sorted(TrainingLevelTypeModel.RANK).collect(Collectors.toList());
		getTrainingLevelType().clear();
		getTrainingLevelType().addAll(lstTrainingLevelType);

	}

}

/* EOF */

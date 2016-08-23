package de.edgesoft.refereemanager.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.jaxb.ContactType;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeAssignmentType;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.jaxb.SexType;
import de.edgesoft.refereemanager.jaxb.StatusType;
import de.edgesoft.refereemanager.jaxb.Team;
import de.edgesoft.refereemanager.jaxb.Trainee;
import de.edgesoft.refereemanager.jaxb.TrainingLevelType;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.TeamModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.model.TrainingLevelTypeModel;

/**
 * Database operations.
 *
 * ## Legal stuff
 * 
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 * 
 * This file is part of refereemanager.
 * 
 * refereemanager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * refereemanager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with refereemanager.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Ekkart Kleinod
 * @version 0.8.0
 * @since 0.7.0
 */
public class DBOperations {
	
	/**
	 * Sorts entries of database.
	 * 
	 * @param theDB database
	 * 
	 * @version 0.8.0
	 * @since 0.7.0
	 */
	public static void sortDB(final RefereeManager theDB) {
		
		Objects.requireNonNull(theDB, "database must not be null");
		
		ContentModel theContent = ((ContentModel) theDB.getContent());
		
		List<Person> lstPerson = theContent.getPerson().stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList());
		theDB.getContent().getPerson().clear();
		theDB.getContent().getPerson().addAll(lstPerson);
		
		List<Referee> lstReferee = theContent.getReferee().stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList());
		theDB.getContent().getReferee().clear();
		theDB.getContent().getReferee().addAll(lstReferee);
		
		List<Trainee> lstTrainee = theContent.getTrainee().stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList());
		theDB.getContent().getTrainee().clear();
		theDB.getContent().getTrainee().addAll(lstTrainee);
		
		List<League> lstLeague = theContent.getLeague().stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).collect(Collectors.toList());
		theDB.getContent().getLeague().clear();
		theDB.getContent().getLeague().addAll(lstLeague);
		
		List<Club> lstClub = theContent.getClub().stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).collect(Collectors.toList());
		theDB.getContent().getClub().clear();
		theDB.getContent().getClub().addAll(lstClub);
		
		List<Team> lstTeam = theContent.getTeam().stream().sorted(TeamModel.LEAGUE).collect(Collectors.toList());
		theDB.getContent().getTeam().clear();
		theDB.getContent().getTeam().addAll(lstTeam);
		
		List<SexType> lstSexType = theContent.getSexType().stream().sorted(TitledIDTypeModel.TITLE).collect(Collectors.toList());
		theDB.getContent().getSexType().clear();
		theDB.getContent().getSexType().addAll(lstSexType);
		
		List<ContactType> lstContactType = theContent.getContactType().stream().sorted(TitledIDTypeModel.TITLE).collect(Collectors.toList());
		theDB.getContent().getContactType().clear();
		theDB.getContent().getContactType().addAll(lstContactType);
		
		List<StatusType> lstStatusType = theContent.getStatusType().stream().sorted(TitledIDTypeModel.TITLE).collect(Collectors.toList());
		theDB.getContent().getStatusType().clear();
		theDB.getContent().getStatusType().addAll(lstStatusType);
		
		List<RefereeAssignmentType> lstRefereeAssignmentType = theContent.getRefereeAssignmentType().stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).collect(Collectors.toList());
		theDB.getContent().getRefereeAssignmentType().clear();
		theDB.getContent().getRefereeAssignmentType().addAll(lstRefereeAssignmentType);
		
		List<TrainingLevelType> lstTrainingLevelType = theContent.getTrainingLevelType().stream().sorted(TrainingLevelTypeModel.RANK).collect(Collectors.toList());
		theDB.getContent().getTrainingLevelType().clear();
		theDB.getContent().getTrainingLevelType().addAll(lstTrainingLevelType);
		
		Constants.logger.info("sorted database");
		
	}
	
	/**
	 * Removes unused clubs.
	 * 
	 * @param theDB database
	 * 
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public static void removeClubs(final RefereeManager theDB) {
		
		Objects.requireNonNull(theDB, "database must not be null");
		
		ContentModel theContent = ((ContentModel) theDB.getContent());
		
		int iBefore = theContent.getClub().size();
		
		theContent.getClub().removeAll(theContent.getNonReferencedClubs());
		
		int iAfter = theContent.getClub().size();
		
		Constants.logger.info(String.format("Removed %d of %d clubs, %d clubs remaining.", iBefore - iAfter, iBefore, iAfter));
		
		
	}
		
}

/* EOF */

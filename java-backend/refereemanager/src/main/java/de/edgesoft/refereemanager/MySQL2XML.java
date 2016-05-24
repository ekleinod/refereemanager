package de.edgesoft.refereemanager;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.types.UInteger;

import de.edgesoft.edgeutils.EdgeUtilsException;
import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.commons.InfoType;
import de.edgesoft.edgeutils.commons.ext.VersionTypeExt;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.Address;
import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.jaxb.ClubRelation;
import de.edgesoft.refereemanager.jaxb.ClubRelationType;
import de.edgesoft.refereemanager.jaxb.ContactType;
import de.edgesoft.refereemanager.jaxb.Content;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.Exam;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.ObjectFactory;
import de.edgesoft.refereemanager.jaxb.PhoneNumber;
import de.edgesoft.refereemanager.jaxb.Picture;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeAssignmentType;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.jaxb.RefereeQuantity;
import de.edgesoft.refereemanager.jaxb.Season;
import de.edgesoft.refereemanager.jaxb.SexType;
import de.edgesoft.refereemanager.jaxb.StatusType;
import de.edgesoft.refereemanager.jaxb.TrainingLevel;
import de.edgesoft.refereemanager.jaxb.TrainingLevelType;
import de.edgesoft.refereemanager.jaxb.URL;
import de.edgesoft.refereemanager.jaxb.Wish;
import de.edgesoft.refereemanager.jaxb.WishType;
import de.edgesoft.refereemanager.jooq.tables.Addresses;
import de.edgesoft.refereemanager.jooq.tables.Clubs;
import de.edgesoft.refereemanager.jooq.tables.ContactTypes;
import de.edgesoft.refereemanager.jooq.tables.Contacts;
import de.edgesoft.refereemanager.jooq.tables.Emails;
import de.edgesoft.refereemanager.jooq.tables.LeagueTypes;
import de.edgesoft.refereemanager.jooq.tables.Leagues;
import de.edgesoft.refereemanager.jooq.tables.People;
import de.edgesoft.refereemanager.jooq.tables.PhoneNumbers;
import de.edgesoft.refereemanager.jooq.tables.Pictures;
import de.edgesoft.refereemanager.jooq.tables.RefereeAssignmentTypes;
import de.edgesoft.refereemanager.jooq.tables.RefereeRelationTypes;
import de.edgesoft.refereemanager.jooq.tables.RefereeRelations;
import de.edgesoft.refereemanager.jooq.tables.RefereeStatuses;
import de.edgesoft.refereemanager.jooq.tables.Referees;
import de.edgesoft.refereemanager.jooq.tables.Seasons;
import de.edgesoft.refereemanager.jooq.tables.SexTypes;
import de.edgesoft.refereemanager.jooq.tables.StatusTypes;
import de.edgesoft.refereemanager.jooq.tables.TrainingLevelTypes;
import de.edgesoft.refereemanager.jooq.tables.TrainingLevels;
import de.edgesoft.refereemanager.jooq.tables.TrainingUpdates;
import de.edgesoft.refereemanager.jooq.tables.Urls;
import de.edgesoft.refereemanager.jooq.tables.WishTypes;
import de.edgesoft.refereemanager.jooq.tables.Wishes;
import de.edgesoft.refereemanager.utils.ConnectionHelper;
import de.edgesoft.refereemanager.utils.Constants;
import de.edgesoft.refereemanager.utils.JAXBHelper;

/**
 * Data conversion from MySQL to XML.
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
 * @version 0.1.0
 * @since 0.1.0
 */
public class MySQL2XML extends AbstractMainClass {
	
	public final Logger logger = LogManager.getLogger();
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static void main(String[] args) {
		new MySQL2XML().executeOperation(args);
	}

	/**
	 * Programmatic entry point, initializing and executing main functionality.
	 * 
	 * - set description setDescription("...");
	 * - add options addOption("short", "long", "description", argument?, required?);
	 * - call init(args);
	 * - call operation execution with arguments
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	@Override
	public void executeOperation(String[] args) {
		
		setDescription("Converts MySQL data to XML data.");
		
		addOption("p", "path", "output path.", true, true);
		addOption("f", "file", "output file name template.", true, false);
		addOption("s", "season", "season of file.", true, false);
		
		init(args);
		
		convertMySQL2XML(getOptionValue("p"), getOptionValue("f"), getOptionValue("s"));
		
	}

	/**
	 * Converts data from MySQL to XML.
	 * 
	 * @param thePath output path
	 * @param theXMLFile xml filename (empty = refereemanager_%s.xml)
	 * @param theSeason season (empty = all seasons)
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public void convertMySQL2XML(String thePath, String theXMLFile, String theSeason) {
		
		logger.info("start conversion.");
		
		DSLContext create = ConnectionHelper.getContext();
		
		logger.info("connection context created.");
		
		logger.info("initializing conversion.");
		
		Result<Record> result = null;
		
		if (theSeason == null) {
			result = create.select()
					.from(Seasons.SEASONS)
					.fetch();
		} else {
			result = create.select()
					.from(Seasons.SEASONS)
					.where(Seasons.SEASONS.YEAR_START.eq(Integer.parseInt(theSeason)))
					.fetch();
		}
		
		for (Record record : result) {
			RefereeManager mgrDoc = new RefereeManager();
			
			mgrDoc.setInfo(new InfoType());
			mgrDoc.getInfo().setCreated(Calendar.getInstance());
			mgrDoc.getInfo().setModified(Calendar.getInstance());
			mgrDoc.getInfo().setCreator(this.getClass().getCanonicalName());
			mgrDoc.getInfo().setAppversion(new VersionTypeExt(Constants.APPVERSION));
			mgrDoc.getInfo().setDocversion(new VersionTypeExt(Constants.DOCVERSION));
			
			
			mgrDoc.setContent(convertSeason(record));
			
			try {
				StringBuilder sbFilename = new StringBuilder();
				sbFilename.append(thePath);
				sbFilename.append(System.getProperty("file.separator"));
				sbFilename.append(String.format(((theXMLFile == null) ? "refereemanager_%s.xml" : theXMLFile), record.getValue(Seasons.SEASONS.YEAR_START)));
				
				logger.info(MessageFormat.format("writing xml file ''{0}''.", sbFilename.toString()));
				
				JAXBFiles.marshal(new ObjectFactory().createRefereemanager(mgrDoc), sbFilename.toString(), null);
				
			} catch (EdgeUtilsException e) {
				logger.error(e);
			}
		}
		
		logger.info("stopped conversion.");
		
	}
	
	/**
	 * Converts data from MySQL to XML for one season.
	 * 
	 * @param theSeasonRecord season
	 * @return content
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	private Content convertSeason(Record theSeasonRecord) {
		
		Content theContent = new Content();
		
		DSLContext create = ConnectionHelper.getContext();
		Result<Record> result = null;
		Result<Record> result2 = null;
		
		logger.info(String.format("converting season %s.", theSeasonRecord.getValue(Seasons.SEASONS.YEAR_START)));
		
		Season aSeason = new Season();
		aSeason.setStartYear(theSeasonRecord.getValue(Seasons.SEASONS.YEAR_START));
		aSeason.setTitle(theSeasonRecord.getValue(Seasons.SEASONS.TITLE));
		aSeason.setRemark(theSeasonRecord.getValue(Seasons.SEASONS.REMARK));
		theContent.setSeason(aSeason);
		
		UInteger theSeasonUID = theSeasonRecord.getValue(Seasons.SEASONS.ID);
		
		
		logger.info("converting exam.");
		
		Exam anExam = new Exam();
		anExam.setPoints(60);
		anExam.setPointsOral(6);
		anExam.setPointsPractical(12);
		anExam.setPointsWritten(40);
		theContent.setExam(anExam);
		

		logger.info("converting sex types.");
		
		result = create.select()
				.from(SexTypes.SEX_TYPES)
				.orderBy(SexTypes.SEX_TYPES.SID.asc())
				.fetch();
		
		Map<UInteger, SexType> mapSexTypes = new HashMap<>();
		for (Record record : result) {
			SexType aType = new SexType();
			
			aType.setId(JAXBHelper.getID(SexType.class, record.getValue(SexTypes.SEX_TYPES.SID)));
			aType.setTitle(record.getValue(SexTypes.SEX_TYPES.TITLE));
			aType.setRemark(record.getValue(SexTypes.SEX_TYPES.REMARK));
			
			theContent.getSexType().add(aType);
			mapSexTypes.put(record.getValue(SexTypes.SEX_TYPES.ID), aType);
		}
		
		
		logger.info("converting contact types.");
		
		result = create.select()
				.from(ContactTypes.CONTACT_TYPES)
				.orderBy(ContactTypes.CONTACT_TYPES.TITLE.asc())
				.fetch();
		
		Map<UInteger, ContactType> mapContactTypes = new HashMap<>();
		for (Record record : result) {
			ContactType aType = new ContactType();
			
			aType.setId(JAXBHelper.getID(ContactType.class, record.getValue(ContactTypes.CONTACT_TYPES.ABBREVIATION)));
			aType.setTitle(record.getValue(ContactTypes.CONTACT_TYPES.TITLE));
			aType.setAbbreviation(record.getValue(ContactTypes.CONTACT_TYPES.ABBREVIATION));
			aType.setRemark(record.getValue(ContactTypes.CONTACT_TYPES.REMARK));
			
			theContent.getContactType().add(aType);
			mapContactTypes.put(record.getValue(ContactTypes.CONTACT_TYPES.ID), aType);
		}
		
		
		logger.info("converting status types.");
		
		result = create.select()
				.from(StatusTypes.STATUS_TYPES)
				.orderBy(StatusTypes.STATUS_TYPES.SID.asc())
				.fetch();
		
		Map<UInteger, StatusType> mapStatusTypes = new HashMap<>();
		for (Record record : result) {
			StatusType aType = new StatusType();
			
			aType.setId(JAXBHelper.getID(StatusType.class, record.getValue(StatusTypes.STATUS_TYPES.SID)));
			aType.setTitle(record.getValue(StatusTypes.STATUS_TYPES.TITLE));
			aType.setRemark(record.getValue(StatusTypes.STATUS_TYPES.REMARK));
			
			theContent.getStatusType().add(aType);
			mapStatusTypes.put(record.getValue(StatusTypes.STATUS_TYPES.ID), aType);
		}
		
		
		logger.info("converting referee assignment types.");
		
		result = create.select()
				.from(RefereeAssignmentTypes.REFEREE_ASSIGNMENT_TYPES)
				.orderBy(RefereeAssignmentTypes.REFEREE_ASSIGNMENT_TYPES.SID.asc())
				.fetch();
		
		Map<String, RefereeAssignmentType> mapRefereeAssignmentTypes = new HashMap<>();
		for (Record record : result) {
			RefereeAssignmentType aType = new RefereeAssignmentType();
			
			aType.setId(JAXBHelper.getID(RefereeAssignmentType.class, record.getValue(RefereeAssignmentTypes.REFEREE_ASSIGNMENT_TYPES.SID)));
			aType.setTitle(record.getValue(RefereeAssignmentTypes.REFEREE_ASSIGNMENT_TYPES.TITLE));
			aType.setAbbreviation(record.getValue(RefereeAssignmentTypes.REFEREE_ASSIGNMENT_TYPES.ABBREVIATION));
			aType.setRemark(record.getValue(RefereeAssignmentTypes.REFEREE_ASSIGNMENT_TYPES.REMARK));
			
			theContent.getRefereeAssignmentType().add(aType);
			mapRefereeAssignmentTypes.put(record.getValue(RefereeAssignmentTypes.REFEREE_ASSIGNMENT_TYPES.SID), aType);
		}
		
		
		logger.info("converting training level types.");
		
		result = create.select()
				.from(TrainingLevelTypes.TRAINING_LEVEL_TYPES)
				.orderBy(TrainingLevelTypes.TRAINING_LEVEL_TYPES.RANK.asc())
				.fetch();
		
		Map<UInteger, TrainingLevelType> mapTrainingLevelTypes = new HashMap<>();
		for (Record record : result) {
			TrainingLevelType aType = new TrainingLevelType();
			
			aType.setId(JAXBHelper.getID(TrainingLevelType.class, record.getValue(TrainingLevelTypes.TRAINING_LEVEL_TYPES.ABBREVIATION)));
			aType.setTitle(record.getValue(TrainingLevelTypes.TRAINING_LEVEL_TYPES.TITLE));
			aType.setAbbreviation(record.getValue(TrainingLevelTypes.TRAINING_LEVEL_TYPES.ABBREVIATION));
			aType.setRank(record.getValue(TrainingLevelTypes.TRAINING_LEVEL_TYPES.RANK).intValue());
			aType.setUpdateInterval(record.getValue(TrainingLevelTypes.TRAINING_LEVEL_TYPES.UPDATE_INTERVAL).intValue());
			aType.setRemark(record.getValue(TrainingLevelTypes.TRAINING_LEVEL_TYPES.REMARK));
			
			theContent.getTrainingLevelType().add(aType);
			mapTrainingLevelTypes.put(record.getValue(TrainingLevelTypes.TRAINING_LEVEL_TYPES.ID), aType);
		}
		
		
		logger.info("converting clubs.");
		
		result = create.select()
				.from(Clubs.CLUBS)
				.orderBy(Clubs.CLUBS.NAME.asc())
				.fetch();
		
		Map<UInteger, Club> mapClubs = new HashMap<>();
		for (Record record : result) {
			
			Club aClub = new Club();
			
			aClub.setId(JAXBHelper.getID(Club.class, record.getValue(Clubs.CLUBS.ID)));
			aClub.setTitle(record.getValue(Clubs.CLUBS.NAME));
			aClub.setAbbreviation(record.getValue(Clubs.CLUBS.ABBREVIATION));
			aClub.setRemark(record.getValue(Clubs.CLUBS.REMARK));
			
			theContent.getClub().add(aClub);
			mapClubs.put(record.getValue(Clubs.CLUBS.ID), aClub);
			
		}
		
		
		logger.info("converting leagues.");
		
		result = create.select()
				.from(Leagues.LEAGUES)
				.join(LeagueTypes.LEAGUE_TYPES).onKey()
				.orderBy(Leagues.LEAGUES.ABBREVIATION.asc())
				.fetch();
		
		Map<UInteger, League> mapLeagues = new HashMap<>();
		for (Record record : result) {
			
			if (record.getValue(Leagues.LEAGUES.ABBREVIATION).startsWith("1") || 
					record.getValue(Leagues.LEAGUES.ABBREVIATION).startsWith("3") || 
					record.getValue(Leagues.LEAGUES.ABBREVIATION).startsWith("OL") || 
					record.getValue(Leagues.LEAGUES.ABBREVIATION).startsWith("RL")) {
				
				League aLeague = new League();
				
				aLeague.setId(JAXBHelper.getID(League.class, record.getValue(Leagues.LEAGUES.ABBREVIATION)));
				aLeague.setTitle(record.getValue(Leagues.LEAGUES.TITLE));
				aLeague.setAbbreviation(record.getValue(Leagues.LEAGUES.ABBREVIATION));
				aLeague.setRemark(record.getValue(Leagues.LEAGUES.REMARK));
				
				aLeague.setSexType(mapSexTypes.get(record.getValue(LeagueTypes.LEAGUE_TYPES.SEX_TYPE_ID)));
				
				RefereeQuantity aQuantity = new RefereeQuantity();
				aQuantity.setRefereeAssigmentType(mapRefereeAssignmentTypes.get("referee"));
				aQuantity.setQuantity(1);
				aLeague.getRefereeQuantity().add(aQuantity);
				
				if (record.getValue(Leagues.LEAGUES.ABBREVIATION).startsWith("1") || record.getValue(Leagues.LEAGUES.ABBREVIATION).startsWith("3")) {
					aQuantity = new RefereeQuantity();
					aQuantity.setRefereeAssigmentType(mapRefereeAssignmentTypes.get("standbyref"));
					aQuantity.setQuantity(1);
					aLeague.getRefereeQuantity().add(aQuantity);
					
					aQuantity = new RefereeQuantity();
					aQuantity.setRefereeAssigmentType(mapRefereeAssignmentTypes.get("umpire"));
					aQuantity.setQuantity(record.getValue(Leagues.LEAGUES.ABBREVIATION).startsWith("1") ? 3 : 1);
					aLeague.getRefereeQuantity().add(aQuantity);
					
				}
				
				theContent.getLeague().add(aLeague);
				mapLeagues.put(record.getValue(Leagues.LEAGUES.ID), aLeague);
			}
			
		}
		

		logger.info("converting referees.");
		
		result = create.select()
				.from(Referees.REFEREES)
				.join(RefereeStatuses.REFEREE_STATUSES).onKey()
				.join(People.PEOPLE).onKey()
				.where(RefereeStatuses.REFEREE_STATUSES.SEASON_ID.eq(theSeasonUID))
				.orderBy(People.PEOPLE.ID.asc())
				.fetch();
		
		for (Record record : result) {
			Referee aReferee = new Referee();
			aReferee.setId(JAXBHelper.getID(Referee.class, record.getValue(Referees.REFEREES.ID)));
			
			// referee
			aReferee.setDocsByLetter((record.getValue(Referees.REFEREES.DOCS_PER_LETTER) != null) && (record.getValue(Referees.REFEREES.DOCS_PER_LETTER) != 0));
			
			// people
			aReferee.setTitle(record.getValue(People.PEOPLE.TITLE));
			aReferee.setFirstName(record.getValue(People.PEOPLE.FIRST_NAME));
			aReferee.setName(record.getValue(People.PEOPLE.NAME));
			
			if (record.getValue(People.PEOPLE.BIRTHDAY) != null) {
				Calendar calTemp = new GregorianCalendar();
				calTemp.setTime(record.getValue(People.PEOPLE.BIRTHDAY));
				aReferee.setBirthday(calTemp);
			}
			
			if (record.getValue(People.PEOPLE.DAYOFDEATH) != null) {
				Calendar calTemp = new GregorianCalendar();
				calTemp.setTime(record.getValue(People.PEOPLE.DAYOFDEATH));
				aReferee.setDayOfDeath(calTemp);
			}
			
			aReferee.setRemark(record.getValue(People.PEOPLE.REMARK));
			aReferee.setInternalRemark(record.getValue(People.PEOPLE.INTERNAL_REMARK));
			
			// references
			aReferee.setSexType(mapSexTypes.get(record.getValue(People.PEOPLE.SEX_TYPE_ID)));
			aReferee.setStatus(mapStatusTypes.get(record.getValue(RefereeStatuses.REFEREE_STATUSES.STATUS_TYPE_ID)));
			
			// 1:n
			result2 = create.select()
					.from(Pictures.PICTURES)
					.where(Pictures.PICTURES.PERSON_ID.eq(record.getValue(People.PEOPLE.ID)))
					.fetch();
			
			for (Record record2 : result2) {
				Picture aPic = new Picture();
				aPic.setURL(record2.getValue(Pictures.PICTURES.URL));
				aPic.setRemark(record2.getValue(Pictures.PICTURES.REMARK));
				aReferee.getPicture().add(aPic);
			}
			
			
			result2 = create.select()
					.from(Emails.EMAILS)
					.join(Contacts.CONTACTS).onKey()
					.where(Contacts.CONTACTS.PERSON_ID.eq(record.getValue(People.PEOPLE.ID)))
					.fetch();
			
			for (Record record2 : result2) {
				EMail aContact = new EMail();
				
				aContact.setTitle(record2.getValue(Contacts.CONTACTS.TITLE));
				aContact.setRemark(record2.getValue(Contacts.CONTACTS.REMARK));
				if (result2.size() > 1) {
					aContact.setIsPrimary((record2.getValue(Contacts.CONTACTS.IS_PRIMARY) != null) && (record2.getValue(Contacts.CONTACTS.IS_PRIMARY) != 0));
				} else {
					aContact.setIsPrimary(true);
				}
				aContact.setEditorOnly((record2.getValue(Contacts.CONTACTS.EDITOR_ONLY) != null) && (record2.getValue(Contacts.CONTACTS.EDITOR_ONLY) != 0));
				
				aContact.setEMail(record2.getValue(Emails.EMAILS.EMAIL));
				
				aContact.setContactType(mapContactTypes.get(record2.getValue(Contacts.CONTACTS.CONTACT_TYPE_ID)));
				
				aReferee.getEMail().add(aContact);
			}
			
			
			result2 = create.select()
					.from(Addresses.ADDRESSES)
					.join(Contacts.CONTACTS).onKey()
					.where(Contacts.CONTACTS.PERSON_ID.eq(record.getValue(People.PEOPLE.ID)))
					.fetch();
			
			for (Record record2 : result2) {
				Address aContact = new Address();
				
				aContact.setTitle(record2.getValue(Contacts.CONTACTS.TITLE));
				aContact.setRemark(record2.getValue(Contacts.CONTACTS.REMARK));
				if (result2.size() > 1) {
					aContact.setIsPrimary((record2.getValue(Contacts.CONTACTS.IS_PRIMARY) != null) && (record2.getValue(Contacts.CONTACTS.IS_PRIMARY) != 0));
				} else {
					aContact.setIsPrimary(true);
				}
				aContact.setEditorOnly((record2.getValue(Contacts.CONTACTS.EDITOR_ONLY) != null) && (record2.getValue(Contacts.CONTACTS.EDITOR_ONLY) != 0));
				
				aContact.setStreet(record2.getValue(Addresses.ADDRESSES.STREET));
				aContact.setNumber(record2.getValue(Addresses.ADDRESSES.NUMBER));
				aContact.setZipCode(record2.getValue(Addresses.ADDRESSES.ZIP_CODE));
				aContact.setCity(record2.getValue(Addresses.ADDRESSES.CITY));
				
				aContact.setContactType(mapContactTypes.get(record2.getValue(Contacts.CONTACTS.CONTACT_TYPE_ID)));
				
				aReferee.getAddress().add(aContact);
			}
			
			
			result2 = create.select()
					.from(PhoneNumbers.PHONE_NUMBERS)
					.join(Contacts.CONTACTS).onKey()
					.where(Contacts.CONTACTS.PERSON_ID.eq(record.getValue(People.PEOPLE.ID)))
					.fetch();
			
			for (Record record2 : result2) {
				PhoneNumber aContact = new PhoneNumber();
				
				aContact.setTitle(record2.getValue(Contacts.CONTACTS.TITLE));
				aContact.setRemark(record2.getValue(Contacts.CONTACTS.REMARK));
				if (result2.size() > 1) {
					aContact.setIsPrimary((record2.getValue(Contacts.CONTACTS.IS_PRIMARY) != null) && (record2.getValue(Contacts.CONTACTS.IS_PRIMARY) != 0));
				} else {
					aContact.setIsPrimary(true);
				}
				aContact.setEditorOnly((record2.getValue(Contacts.CONTACTS.EDITOR_ONLY) != null) && (record2.getValue(Contacts.CONTACTS.EDITOR_ONLY) != 0));
				
				aContact.setCountryCode(record2.getValue(PhoneNumbers.PHONE_NUMBERS.COUNTRY_CODE));
				aContact.setAreaCode(record2.getValue(PhoneNumbers.PHONE_NUMBERS.AREA_CODE));
				aContact.setNumber(record2.getValue(PhoneNumbers.PHONE_NUMBERS.NUMBER));
				
				aContact.setContactType(mapContactTypes.get(record2.getValue(Contacts.CONTACTS.CONTACT_TYPE_ID)));
				
				aReferee.getPhoneNumber().add(aContact);
			}
			
			
			result2 = create.select()
					.from(Urls.URLS)
					.join(Contacts.CONTACTS).onKey()
					.where(Contacts.CONTACTS.PERSON_ID.eq(record.getValue(People.PEOPLE.ID)))
					.fetch();
			
			for (Record record2 : result2) {
				URL aContact = new URL();
				
				aContact.setTitle(record2.getValue(Contacts.CONTACTS.TITLE));
				aContact.setRemark(record2.getValue(Contacts.CONTACTS.REMARK));
				if (result2.size() > 1) {
					aContact.setIsPrimary((record2.getValue(Contacts.CONTACTS.IS_PRIMARY) != null) && (record2.getValue(Contacts.CONTACTS.IS_PRIMARY) != 0));
				} else {
					aContact.setIsPrimary(true);
				}
				aContact.setEditorOnly((record2.getValue(Contacts.CONTACTS.EDITOR_ONLY) != null) && (record2.getValue(Contacts.CONTACTS.EDITOR_ONLY) != 0));
				
				aContact.setURL(record2.getValue(Urls.URLS.URL));
				
				aContact.setContactType(mapContactTypes.get(record2.getValue(Contacts.CONTACTS.CONTACT_TYPE_ID)));
				
				aReferee.getURL().add(aContact);
			}
			
			
			result2 = create.select()
					.from(RefereeRelations.REFEREE_RELATIONS)
					.join(RefereeRelationTypes.REFEREE_RELATION_TYPES).onKey()
					.where(RefereeRelations.REFEREE_RELATIONS.REFEREE_ID.eq(record.getValue(Referees.REFEREES.ID)))
					.and(RefereeRelations.REFEREE_RELATIONS.SEASON_ID.eq(theSeasonUID))
					.fetch();
			
			for (Record record2 : result2) {
				ClubRelation aClubRelation = new ClubRelation();
				
				String sType = record2.getValue(RefereeRelationTypes.REFEREE_RELATION_TYPES.SID);
				if (sType.equals("reffor")) {
					sType = "referee";
				}
				aClubRelation.setType(ClubRelationType.fromValue(sType));
				aClubRelation.setClub(mapClubs.get(record2.getValue(RefereeRelations.REFEREE_RELATIONS.CLUB_ID)));
				
				aReferee.getClubRelation().add(aClubRelation);
			}
			
			
			result2 = create.select()
					.from(Wishes.WISHES)
					.join(WishTypes.WISH_TYPES).onKey()
					.where(Wishes.WISHES.REFEREE_ID.eq(record.getValue(Referees.REFEREES.ID)))
					.fetch();
			
			for (Record record2 : result2) {
				Wish aWish = new Wish();
				
				aWish.setType(WishType.fromValue(record2.getValue(WishTypes.WISH_TYPES.SID)));
				
				if (record2.getValue(Wishes.WISHES.SATURDAY) != null) {
					aWish.setSaturday(record2.getValue(Wishes.WISHES.SATURDAY) == 1);
				}
				if (record2.getValue(Wishes.WISHES.SUNDAY) != null) {
					aWish.setSunday(record2.getValue(Wishes.WISHES.SUNDAY) == 1);
				}
				if (record2.getValue(Wishes.WISHES.TOURNAMENT) != null) {
					aWish.setTournamentOnly(record2.getValue(Wishes.WISHES.TOURNAMENT) == 1);
				}
				
				if (record2.getValue(Wishes.WISHES.CLUB_ID) != null) {
					aWish.setClub(mapClubs.get(record2.getValue(Wishes.WISHES.CLUB_ID)));
				}
				if ((record2.getValue(Wishes.WISHES.LEAGUE_ID) != null) && (mapLeagues.get(record2.getValue(Wishes.WISHES.LEAGUE_ID)) != null)) {
					aWish.setLeague(mapLeagues.get(record2.getValue(Wishes.WISHES.LEAGUE_ID)));
				}
				if (record2.getValue(Wishes.WISHES.SEX_TYPE_ID) != null) {
					aWish.setSexType(mapSexTypes.get(record2.getValue(Wishes.WISHES.SEX_TYPE_ID)));
				}

				if ((aWish.isSaturday() != null) ||
						(aWish.isSunday() != null) ||
						(aWish.isTournamentOnly() != null) ||
						(aWish.getClub() != null) ||
						(aWish.getLeague() != null) ||
						(aWish.getSexType() != null)) {
					
					aReferee.getWish().add(aWish);
				}
			}
			
			
			result2 = create.select()
					.from(TrainingLevels.TRAINING_LEVELS)
					.where(TrainingLevels.TRAINING_LEVELS.REFEREE_ID.eq(record.getValue(Referees.REFEREES.ID)))
					.orderBy(TrainingLevels.TRAINING_LEVELS.SINCE.asc())
					.fetch();
			
			for (Record record2 : result2) {
				TrainingLevel aTrainingLevel = new TrainingLevel();
				
				aTrainingLevel.setType(mapTrainingLevelTypes.get(record2.getValue(TrainingLevels.TRAINING_LEVELS.TRAINING_LEVEL_TYPE_ID)));
				
				if (record2.getValue(TrainingLevels.TRAINING_LEVELS.SINCE) != null) {
					Calendar calTemp = new GregorianCalendar();
					calTemp.setTime(record2.getValue(TrainingLevels.TRAINING_LEVELS.SINCE));
					aTrainingLevel.setSince(calTemp);
				}
				
				Result<Record> result3 = create.select()
						.from(TrainingUpdates.TRAINING_UPDATES)
						.where(TrainingUpdates.TRAINING_UPDATES.TRAINING_LEVEL_ID.eq(record2.getValue(TrainingLevels.TRAINING_LEVELS.ID)))
						.orderBy(TrainingUpdates.TRAINING_UPDATES.UPDATE.asc())
						.fetch();
				
				for (Record record3 : result3) {
					Calendar calTemp = new GregorianCalendar();
					calTemp.setTime(record3.getValue(TrainingUpdates.TRAINING_UPDATES.UPDATE));
					aTrainingLevel.getUpdate().add(calTemp);
				}
				
				aReferee.getTrainingLevel().add(aTrainingLevel);
			}
			
			
			theContent.getReferee().add(aReferee);
		}
		
		return theContent;
		
	}
	
}

/* EOF */

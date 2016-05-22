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
import de.edgesoft.edgeutils.Messages;
import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.commons.InfoType;
import de.edgesoft.edgeutils.commons.RefType;
import de.edgesoft.edgeutils.commons.ext.VersionTypeExt;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.Address;
import de.edgesoft.refereemanager.jaxb.ContactType;
import de.edgesoft.refereemanager.jaxb.Content;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.ObjectFactory;
import de.edgesoft.refereemanager.jaxb.PhoneNumber;
import de.edgesoft.refereemanager.jaxb.Picture;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.jaxb.SexType;
import de.edgesoft.refereemanager.jaxb.URL;
import de.edgesoft.refereemanager.jooq.tables.Addresses;
import de.edgesoft.refereemanager.jooq.tables.ContactTypes;
import de.edgesoft.refereemanager.jooq.tables.Contacts;
import de.edgesoft.refereemanager.jooq.tables.Emails;
import de.edgesoft.refereemanager.jooq.tables.People;
import de.edgesoft.refereemanager.jooq.tables.PhoneNumbers;
import de.edgesoft.refereemanager.jooq.tables.Pictures;
import de.edgesoft.refereemanager.jooq.tables.Referees;
import de.edgesoft.refereemanager.jooq.tables.SexTypes;
import de.edgesoft.refereemanager.jooq.tables.Urls;
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
		
		addOption("f", "file", "output file.", true, true);
		addOption("h", "help", "display help message", false, false);
		
		init(args);
		
		boolean showHelp = true;
		
		if (hasOption("f")) {
			convertMySQL2XML(getOptionValue("f"));
			showHelp = false;
		} 
		
		if (showHelp) {
			Messages.printMessage(getUsage());
		}
		
	}

	/**
	 * Converts data from MySQL to XML.
	 * 
	 * @param theXMLFile xml filename
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public void convertMySQL2XML(String theXMLFile) {
		
		logger.info("start conversion.");
		
		DSLContext create = ConnectionHelper.getContext();
		Result<Record> result = null;
		Result<Record> result2 = null;
		
		logger.info("connection context created.");
		
		logger.info("initializing conversion.");
		
		RefereeManager mgrDoc = new RefereeManager();
		
		mgrDoc.setInfo(new InfoType());
		mgrDoc.getInfo().setCreated(Calendar.getInstance());
		mgrDoc.getInfo().setModified(Calendar.getInstance());
		mgrDoc.getInfo().setCreator(this.getClass().getCanonicalName());
		mgrDoc.getInfo().setAppversion(new VersionTypeExt(Constants.APPVERSION));
		mgrDoc.getInfo().setDocversion(new VersionTypeExt(Constants.DOCVERSION));
		
		Content theContent = new Content();
		mgrDoc.setContent(theContent);
		
		
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
			
			aType.setId(JAXBHelper.getID(ContactType.class, record.getValue(ContactTypes.CONTACT_TYPES.ID)));
			aType.setTitle(record.getValue(ContactTypes.CONTACT_TYPES.TITLE));
			aType.setAbbreviation(record.getValue(ContactTypes.CONTACT_TYPES.ABBREVIATION));
			aType.setRemark(record.getValue(ContactTypes.CONTACT_TYPES.REMARK));
			
			theContent.getContactType().add(aType);
			mapContactTypes.put(record.getValue(ContactTypes.CONTACT_TYPES.ID), aType);
		}
		
		
		logger.info("converting referees.");
		
		result = create.select()
				.from(Referees.REFEREES)
				.join(People.PEOPLE).onKey()
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
			
			Calendar calTemp = new GregorianCalendar();
			if (record.getValue(People.PEOPLE.BIRTHDAY) != null) {
				calTemp.setTime(record.getValue(People.PEOPLE.BIRTHDAY));
				aReferee.setBirthday(calTemp);
			}
			
			if (record.getValue(People.PEOPLE.DAYOFDEATH) != null) {
				calTemp.setTime(record.getValue(People.PEOPLE.DAYOFDEATH));
				aReferee.setDayOfDeath(calTemp);
			}
			
			aReferee.setRemark(record.getValue(People.PEOPLE.REMARK));
			aReferee.setInternalRemark(record.getValue(People.PEOPLE.INTERNAL_REMARK));
			
			// references
			aReferee.setSexTypeRef(new RefType());
			aReferee.getSexTypeRef().setIdref(mapSexTypes.get(record.getValue(People.PEOPLE.SEX_TYPE_ID)));
			
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
				
				aContact.setContactTypeRef(new RefType());
				aContact.getContactTypeRef().setIdref(mapContactTypes.get(record2.getValue(Contacts.CONTACTS.CONTACT_TYPE_ID)));
				
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
				
				aContact.setContactTypeRef(new RefType());
				aContact.getContactTypeRef().setIdref(mapContactTypes.get(record2.getValue(Contacts.CONTACTS.CONTACT_TYPE_ID)));
				
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
				
				aContact.setContactTypeRef(new RefType());
				aContact.getContactTypeRef().setIdref(mapContactTypes.get(record2.getValue(Contacts.CONTACTS.CONTACT_TYPE_ID)));
				
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
				
				aContact.setContactTypeRef(new RefType());
				aContact.getContactTypeRef().setIdref(mapContactTypes.get(record2.getValue(Contacts.CONTACTS.CONTACT_TYPE_ID)));
				
				aReferee.getURL().add(aContact);
			}

			
			theContent.getReferee().add(aReferee);
		}
		
		
		logger.info(MessageFormat.format("writing xml file ''{0}''.", theXMLFile));
		
		try {
			JAXBFiles.marshal(new ObjectFactory().createRefereemanager(mgrDoc), theXMLFile, null);
		} catch (EdgeUtilsException e) {
			logger.error(e);
		}

		logger.info("stopped conversion.");
		
	}
	
}

/* EOF */

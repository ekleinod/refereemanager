package de.edgesoft.refereemanager;

import java.text.MessageFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.edgesoft.edgeutils.EdgeUtilsException;
import de.edgesoft.edgeutils.Messages;
import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.commons.InfoType;
import de.edgesoft.edgeutils.commons.ext.VersionTypeExt;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.ContentType;
import de.edgesoft.refereemanager.jaxb.ObjectFactory;
import de.edgesoft.refereemanager.jaxb.PersonType;
import de.edgesoft.refereemanager.jaxb.RefereeManagerType;
import de.edgesoft.refereemanager.jaxb.RefereeType;
import de.edgesoft.refereemanager.utils.Constants;

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
		
//		DSLContext create = ConnectionHelper.getContext();
		
		logger.info("connection context created.");
		
		logger.info("initializing conversion.");
		
		RefereeManagerType mgrDoc = new RefereeManagerType();
		
		mgrDoc.setInfo(new InfoType());
		mgrDoc.getInfo().setCreated(Calendar.getInstance());
		mgrDoc.getInfo().setModified(Calendar.getInstance());
		mgrDoc.getInfo().setCreator(this.getClass().getCanonicalName());
		mgrDoc.getInfo().setAppversion(new VersionTypeExt(Constants.APPVERSION));
		mgrDoc.getInfo().setDocversion(new VersionTypeExt(Constants.DOCVERSION));
		
		mgrDoc.setContent(new ContentType());
		
		logger.info("converting people.");
		
		PersonType aPerson = new PersonType();
		aPerson.setFirstName("Max");
		aPerson.setName("Mustermann");
		mgrDoc.getContent().getPerson().add(aPerson);
		
		logger.info("converting referees.");
		
		RefereeType aReferee = new RefereeType();
		aReferee.setTitle("Dr.");
		aReferee.setName("Musterfrau");
		mgrDoc.getContent().getReferee().add(aReferee);
		
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

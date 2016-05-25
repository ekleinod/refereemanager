package de.edgesoft.refereemanager;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.utils.Recipient;

/**
 * Send referee mail.
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
 * @version 0.5.0
 * @since 0.5.0
 */
public class SendMail extends AbstractMainClass {
	
	public final Logger logger = LogManager.getLogger();
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static void main(String[] args) {
		new SendMail().executeOperation(args);
	}

	/**
	 * Programmatic entry point, initializing and executing main functionality.
	 * 
	 * - set description setDescription("...");
	 * - add options addOption("short", "long", "description", argument?, required?);
	 * - call init(args);
	 * - call operation execution with arguments
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	@Override
	public void executeOperation(String[] args) {
		
		setDescription("Sends an email to the selected recipients.");
		
		addOption("p", "path", "input path of data.", true, true);
		addOption("f", "file", "input file name template (empty for refereemanager_%s.xml).", true, false);
		addOption("s", "season", "season.", true, true);
		addOption("m", "mailfile", "file name of mail text.", true, true);
		addOption("r", "recipient", "Recipient of mail (all, referees, trainees, me = default).", true, false);
		addOption("t", "test", "Test run, log events but do not send emails.", false, false);
		
		init(args);
		
		sendMail(getOptionValue("p"), getOptionValue("f"), getOptionValue("s"), getOptionValue("m"), getOptionValue("r"), hasOption("t"));
		
	}

	/**
	 * Converts data from MySQL to XML.
	 * 
	 * @param thePath output path
	 * @param theXMLFile xml filename (null = refereemanager_%s.xml)
	 * @param theSeason season
	 * @param theMailfile mail file
	 * @param theRecipient recipient
	 * @param isTest is test run?
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public void sendMail(String thePath, String theXMLFile, String theSeason, String theMailfile, String theRecipient, boolean isTest) {
		
		logger.info("start.");
		
		try {
			
			logger.info("read mail text.");
			
			StringBuilder sbMailbody = FileAccess.readFile(theMailfile);
			String sSubject = sbMailbody.substring(0, sbMailbody.indexOf(System.lineSeparator()));
			String sBody = sbMailbody.substring(sbMailbody.indexOf(System.lineSeparator()) + 1);
			
			logger.info("read data.");
			
			StringBuilder sbFilename = new StringBuilder();
			sbFilename.append(thePath);
			sbFilename.append(File.separatorChar);
			sbFilename.append(String.format(((theXMLFile == null) ? "refereemanager_%s.xml" : theXMLFile), theSeason));
			
			RefereeManager mgrData = JAXBFiles.unmarshal(sbFilename.toString(), RefereeManager.class);
			
			sendMail(mgrData, sSubject, sBody, Recipient.fromValue((theRecipient == null) ? "me" : theRecipient), isTest);
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		
		logger.info("stop");
		
	}
	
	/**
	 * Converts data from MySQL to XML for one season.
	 *
	 * @param theData data
	 * @param theMailSubject mail subject
	 * @param theMailBody mail body
	 * @param theRecipient recipient
	 * @param isTest is test run?
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public void sendMail(RefereeManager theData, String theMailSubject, String theMailBody, Recipient theRecipient, boolean isTest) throws RefManException {
		
		logger.info("Subject: " + theMailSubject);
		logger.info(theMailBody);
		
	}
	
}

/* EOF */

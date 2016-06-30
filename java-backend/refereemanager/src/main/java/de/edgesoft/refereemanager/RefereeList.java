package de.edgesoft.refereemanager;

import java.io.File;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.utils.Recipient;

/**
 * Fill the referee list.
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
public class RefereeList extends AbstractMainClass {
	
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
		new RefereeList().executeOperation(args);
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
	public void executeOperation(final String[] args) {
		
		setDescription("Sends an email to the selected recipients.");
		
		addOption("p", "path", "input path of data.", true, true);
		addOption("f", "file", "input file name template (empty for refereemanager_%s.xml).", true, false);
		addOption("s", "season", "season (empty for current season).", true, false);
		addOption("t", "template", "template to fill.", true, true);
		addOption("o", "output", "output file (empty for template + '.out').", true, false);
		
		init(args);
		
		listReferees(getOptionValue("p"), getOptionValue("f"), getOptionValue("s"), getOptionValue("t"), getOptionValue("o"));
		
	}

	/**
	 * Generates list of referees.
	 * 
	 * @param thePath output path
	 * @param theXMLFile xml filename (null = refereemanager_%s.xml)
	 * @param theSeason season (null = current season)
	 * @param theTemplatefile template file
	 * @param theOutputfile output file (null = template + ".out"
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public void listReferees(final String thePath, final String theXMLFile, final String theSeason, final String theTemplatefile, final String theOutputfile) {
		
		logger.info("start.");
		
		Objects.requireNonNull(thePath, "path must not be null");
		Objects.requireNonNull(theTemplatefile, "template file must not be null");
		
		try {
			
			logger.info("read mail text.");
			
			StringBuilder sbMailbody = FileAccess.readFile(theTemplatefile);
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

package de.edgesoft.refereemanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.utils.ArgumentCommunicationAction;
import de.edgesoft.refereemanager.utils.ArgumentCommunicationRecipient;
import de.edgesoft.refereemanager.utils.CommunicationHelper;
import de.edgesoft.refereemanager.utils.Constants;
import de.edgesoft.refereemanager.utils.PrefKey;

/**
 * Referee communication.
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
 * @since 0.8.0
 */
public class RefereeCommunication extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void main(String[] args) {
		new RefereeCommunication().executeOperation(args);
	}

	/**
	 * Programmatic entry point, initializing and executing main functionality.
	 * 
	 * - set description setDescription("...");
	 * - add options addOption("short", "long", "description", argument?, required?);
	 * - call init(args);
	 * - call operation execution with arguments
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	@Override
	public void executeOperation(final String[] args) {
		
		setDescription("Communication operations.");
		
		addOption("p", "path", MessageFormat.format("database path (default: {0}).", Prefs.get(PrefKey.PATH_DATABASE)), true, false);
		addOption("d", "database", MessageFormat.format("database file name pattern (default: {0}).", Prefs.get(PrefKey.FILENAME_PATTERN_DATABASE)), true, false);
		addOption("s", "season", "season (empty for current season).", true, false);
		addOption("t", "text", "text to communicate.", true, true);
		addOption("o", "outputpath", "output path.", true, false);
		addOption("a", "action", "action (mail (default), letter).", true, false);
		addOption("r", "recipient", "recipient (me (default), all, mailonly, letteronly).", true, false);
		addOption("n", "trainees", "Send to trainees instead of referees.", false, false);
		addOption("e", "test", "test, don't send anything.", false, false);
		
		init(args);
		
		refereeCommunication(getOptionValue("p"), getOptionValue("d"), getOptionValue("s"), 
				getOptionValue("t"), getOptionValue("o"), getOptionValue("a"), getOptionValue("r"), hasOption("n"), hasOption("e"));
		
	}

	/**
	 * Referee communication.
	 * 
	 * @param theDBPath input path
	 * @param theDBFile database filename (null = {@link Constants#DATAFILENAMEPATTERN})
	 * @param theSeason season (null = current season)
	 * @param theTextfile text file
	 * @param theOutputpath output path
	 * @param theAction action (mail (default), letter)
	 * @param theRecipient recipient (me (default), all, mailonly, letteronly)
	 * @param toTrainees send to trainees instead of referees
	 * @param isTest is test?
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public void refereeCommunication(final String theDBPath, final String theDBFile, final String theSeason, 
			final String theTextfile, final String theOutputpath, final String theAction, final String theRecipient, 
			final boolean toTrainees, final boolean isTest) {
		
		Constants.logger.debug("start.");
		
		Objects.requireNonNull(theTextfile, "text file must not be null");
		
		Integer iSeason = (theSeason == null) ? SeasonModel.getCurrentStartYear() : Integer.valueOf(theSeason);
		
		Path pathDBFile = Paths.get((theDBPath == null) ? Prefs.get(PrefKey.PATH_DATABASE) : theDBPath,
				String.format(((theDBFile == null) ? Prefs.get(PrefKey.FILENAME_PATTERN_DATABASE) : theDBFile), iSeason));
		
		String sOutput = (theOutputpath == null) ? Prefs.get(PrefKey.PATH_OUTPUT) : theOutputpath;
		
		ArgumentCommunicationAction argAction = ArgumentCommunicationAction.MAIL;
		try {
			argAction = ArgumentCommunicationAction.fromValue(theAction);
		} catch (IllegalArgumentException e) {
			// do nothing, remains "mail"
		}
		
		ArgumentCommunicationRecipient argRecipient = ArgumentCommunicationRecipient.ME;
		try {
			argRecipient = ArgumentCommunicationRecipient.fromValue(theRecipient);
		} catch (IllegalArgumentException e) {
			// do nothing, remains "all"
		}
		
		refereeCommunication(pathDBFile, Paths.get(theTextfile), sOutput, argAction, argRecipient, toTrainees, isTest);
		
		Constants.logger.debug("stop");
		
	}
	
	/**
	 * Referee communication.
	 * 
	 * @param theDBPath database filename with path
	 * @param theTextPath text file
	 * @param theOutputPath output path
	 * @param theAction action
	 * @param theRecipient recipient
	 * @param toTrainees send to trainees instead of referees
	 * @param isTest is test?
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public void refereeCommunication(final Path theDBPath, final Path theTextPath, final String theOutputPath,
			final ArgumentCommunicationAction theAction, final ArgumentCommunicationRecipient theRecipient, 
			final boolean toTrainees, final boolean isTest) {
		
		Objects.requireNonNull(theDBPath, "database file path must not be null");
		Objects.requireNonNull(theTextPath, "text file path must not be null");
		Objects.requireNonNull(theAction, "action must not be null");
		Objects.requireNonNull(theRecipient, "recipient must not be null");
		
		try {
			
			Constants.logger.debug(String.format("read text from '%s'.", theTextPath.toString()));
			
			final List<String> lstText = FileAccess.readFileInList(theTextPath);
			
			Constants.logger.debug(String.format("read database '%s'.", theDBPath.toString()));
			
			final RefereeManager mgrData = JAXBFiles.unmarshal(theDBPath.toString(), RefereeManager.class);
			
			Constants.logger.debug(String.format("execute action '%s'.", theAction.value()));
			
			Path pathTemplate = null;
			List<String> lstTemplate = null;
			
			switch (theAction) {
				case DOCUMENT:
					pathTemplate = Paths.get(Prefs.get(PrefKey.PATH_TEMPLATES), Prefs.get(PrefKey.TEMPLATE_DOCUMENT));
					Constants.logger.debug(String.format("read document template from '%s'.", pathTemplate.toString()));
					lstTemplate = FileAccess.readFileInList(pathTemplate);
					
					CommunicationHelper.createLetters(lstText, lstTemplate, mgrData, theRecipient, toTrainees, isTest, theOutputPath);
					break;
					
				case LETTER:
					pathTemplate = Paths.get(Prefs.get(PrefKey.PATH_TEMPLATES), Prefs.get(PrefKey.TEMPLATE_LETTER));
					Constants.logger.debug(String.format("read letter template from '%s'.", pathTemplate.toString()));
					lstTemplate = FileAccess.readFileInList(pathTemplate);
					
					CommunicationHelper.createLetters(lstText, lstTemplate, mgrData, theRecipient, toTrainees, isTest, theOutputPath);
					break;
					
				case MAIL:
					pathTemplate = Paths.get(Prefs.get(PrefKey.PATH_TEMPLATES), Prefs.get(PrefKey.TEMPLATE_EMAIL));
					Constants.logger.debug(String.format("read email template from '%s'.", pathTemplate.toString()));
					lstTemplate = FileAccess.readFileInList(pathTemplate);

					CommunicationHelper.sendMail(lstText, lstTemplate, mgrData, theRecipient, toTrainees, isTest);
					break;
			}
			
			
		} catch (Exception e) {
			Constants.logger.error(e);
			e.printStackTrace();
		}
		
	}
	
}

/* EOF */

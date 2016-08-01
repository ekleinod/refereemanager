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
import de.edgesoft.refereemanager.utils.Constants;

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
		
		setDescription("Sends an email to the selected recipients.");
		
		addOption("p", "path", "input path of data.", true, true);
		addOption("f", "file", MessageFormat.format("input file name template (empty for {0}).", Constants.DATAFILENAMEPATTERN), true, false);
		addOption("s", "season", "season (empty for current season).", true, false);
		addOption("t", "text", "text to communicate.", true, true);
		addOption("o", "outputpath", "output path.", true, false);
		addOption("a", "action", "action (mail (default), letter).", true, false);
		addOption("r", "recipient", "recipient (me (default), all, mailonly, letteronly, trainees).", true, false);
		
		init(args);
		
		refereeCommunication(getOptionValue("p"), getOptionValue("f"), getOptionValue("s"), 
				getOptionValue("t"), getOptionValue("o"), getOptionValue("a"), getOptionValue("r"));
		
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
	 * @param theRecipient recipient (me (default), all, mailonly, letteronly, trainees)
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public void refereeCommunication(final String theDBPath, final String theDBFile, final String theSeason, 
			final String theTextfile, final String theOutputpath, final String theAction, final String theRecipient) {
		
		Constants.logger.info("start.");
		
		Objects.requireNonNull(theDBPath, "database path must not be null");
		Objects.requireNonNull(theTextfile, "text file must not be null");
		
		Integer iSeason = (theSeason == null) ? SeasonModel.getCurrentStartYear() : Integer.valueOf(theSeason);
		
		Path pathDBFile = Paths.get(theDBPath, String.format(((theDBFile == null) ? Constants.DATAFILENAMEPATTERN : theDBFile), iSeason));
		
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
		
		refereeCommunication(pathDBFile, Paths.get(theTextfile), theOutputpath, argAction, argRecipient);
		
		Constants.logger.info("stop");
		
	}
	
	/**
	 * Referee communication.
	 * 
	 * @param theDBPath database filename with path
	 * @param theTextPath text file
	 * @param theOutputPath output path
	 * @param theAction action
	 * @param theRecipient recipient
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public void refereeCommunication(final Path theDBPath, final Path theTextPath, final String theOutputPath, final ArgumentCommunicationAction theAction, final ArgumentCommunicationRecipient theRecipient) {
		
		Objects.requireNonNull(theDBPath, "database file path must not be null");
		Objects.requireNonNull(theTextPath, "text file path must not be null");
		Objects.requireNonNull(theAction, "action must not be null");
		Objects.requireNonNull(theRecipient, "recipient must not be null");
		
		try {
			
			Constants.logger.info("read text.");
			
			final List<String> lstText = FileAccess.readFileInList(theTextPath);
			
			Constants.logger.info("read data.");
			
			final RefereeManager mgrData = JAXBFiles.unmarshal(theDBPath.toString(), RefereeManager.class);
			
			switch (theAction) {
				case LETTER:
					Constants.logger.info("fill text.");
					Constants.logger.info(String.format("save letter for '%s' in '%s'."));
					break;
				case MAIL:
					Constants.logger.info("fill text.");
					Constants.logger.info(String.format("send mail to '%s'."));
					Constants.logger.info(String.format("mail sent to '%s'."));
					break;
			}
			
			
		} catch (Exception e) {
			Constants.logger.error(e);
			e.printStackTrace();
		}
		
	}
	
}

/* EOF */

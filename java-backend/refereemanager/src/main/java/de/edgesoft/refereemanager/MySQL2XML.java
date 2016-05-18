package de.edgesoft.refereemanager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;

import de.edgesoft.edgeutils.Messages;
import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.refereemanager.utils.ConnectionHelper;

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
		
		logger.info("connection context created.");
		
		
		logger.info("stopped conversion.");
		
	}
	
}

/* EOF */

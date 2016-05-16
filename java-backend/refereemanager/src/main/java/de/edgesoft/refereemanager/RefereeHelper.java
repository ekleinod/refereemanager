package de.edgesoft.refereemanager;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import de.edgesoft.edgeutils.Messages;
import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.refereemanager.jooq.tables.People;
import de.edgesoft.refereemanager.jooq.tables.Referees;
import de.edgesoft.refereemanager.utils.ConnectionHelper;

/**
 * Methods for referees.
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
public class RefereeHelper extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static void main(String[] args) {
		new RefereeHelper().executeOperation(args);
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
		
		setDescription("Methods for referees.");
		
		addOption("l", "list", "list all referees of a specific season ", false, false);
		addOption("s", "season", "season to list referees for (omit for current season)", true, false);
		addOption("h", "help", "display help message", false, false);
		
		init(args);
		
		boolean showHelp = true;
		
		if (hasOption("l")) {
			System.out.println(getRefereeList(getOptionValue("s")));
			showHelp = false;
		} 
		
		if (showHelp) {
			Messages.printMessage(getUsage());
		}
		
	}

	/**
	 * Returns season list.
	 * 
	 * @param theSeason season (current season if empty)
	 * 
	 * @return list of referees
	 *  @retval empty if no referees exists
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public Result<Record> getRefereeList(String theSeason) {
		
		DSLContext create = ConnectionHelper.getContext();
		
		Result<Record> result = create.select()
				.from(Referees.REFEREES)
				.join(People.PEOPLE).onKey()
				.fetch();
		
		return result;
		
	}
	
}

/* EOF */

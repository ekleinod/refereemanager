package de.edgesoft.refereemanager;

import static de.edgesoft.refereemanager.jooq.tables.People.PEOPLE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.refereemanager.utils.ConnectionHelper;

/**
 * Returns/displays a list of referees for a given season.
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
public class ListReferees extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static void main(String[] args) {
		new ListReferees().executeOperation(args);
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
		
		setDescription("List referees for a specific season.");
		
		addOption("s", "season", "season to list referees for (omit for current season)", true, false);
		
		init(args);
		
		listReferees(getOptionValue("s"));
		
	}

	/**
	 * Lists referees.
	 * 
	 * @param theSeason season
	 */
	public static void listReferees(String theSeason) {
		
		final Logger logger = LogManager.getLogger();
		
		DSLContext create = DSL.using(ConnectionHelper.getConnection(), SQLDialect.MYSQL);
		
		Result<Record> result = create.select()
				.from(PEOPLE)
				.fetch();
		
		for (Record r : result) {
			UInteger id = r.getValue(PEOPLE.ID);
			String firstName = r.getValue(PEOPLE.FIRST_NAME);
			String lastName = r.getValue(PEOPLE.NAME);

			logger.info("ID: " + id + " first name: " + firstName + " last name: " + lastName);
		}
		
	}
	
}

/* EOF */

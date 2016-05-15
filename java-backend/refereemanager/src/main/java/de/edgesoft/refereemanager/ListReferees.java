package de.edgesoft.refereemanager;

import static de.edgesoft.refereemanager.jooq.tables.People.PEOPLE;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.types.UInteger;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;

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
	
	public static void main(String[] args) {
		
		setCallingClass(ListReferees.class);
		setDescription("List referees for a specific season.");
		addOption("s", "season", "season to list referees for (omit for current season)", true, false);
		
		init(args);
		
		System.out.println(getUsage());
		
//		String userName = "root";
//		String password = "";
//		String url = "jdbc:mysql://localhost:3306/refereemanager";
//
//		// Connection is the only JDBC resource that we need
//		// PreparedStatement and ResultSet are handled by jOOQ, internally
//		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
//			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
//			Result<Record> result = create.select()
//					.from(PEOPLE)
//					.fetch();
//			
//			for (Record r : result) {
//				UInteger id = r.getValue(PEOPLE.ID);
//				String firstName = r.getValue(PEOPLE.FIRST_NAME);
//				String lastName = r.getValue(PEOPLE.NAME);
//
//				System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
//			}
//		}
//
//		// For the sake of this tutorial, let's keep exception handling simple
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		final Logger logger = LogManager.getLogger();
//		
//		logger.debug(getMe());
	}
	
	public static String getMe() {
		String sReturn = "notfound";
		
		String userName = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/refereemanager";
		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			Result<Record> result = create.select()
					.from(PEOPLE)
					.where(PEOPLE.ID.equal(UInteger.valueOf(6)))
					.fetch();
			
			for (Record r : result) {
				sReturn = r.getValue(PEOPLE.FIRST_NAME);
			}
		}

		// For the sake of this tutorial, let's keep exception handling simple
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return sReturn;
	}

}

/* EOF */

package de.edgesoft.refereemanager.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 * Provides methods and properties for jdbc connections.
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
public class ConnectionHelper {
	
	public static final String userName = "root";
	public static final String password = "";
	public static final String url = "jdbc:mysql://localhost:3306/refereemanager";
	
	/** Connection singleton. */
	private static Connection theConnection = null;
	
	/** Context singleton. */
	private static DSLContext theContext = null;
	
	/**
	 * Returns database connection.
	 * 
	 * @return database connection
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static Connection getConnection() {
		if (theConnection == null) {
	        try {
	        	theConnection = DriverManager.getConnection(url, userName, password);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
        return theConnection;
    }

	/**
	 * Returns dsl context.
	 * 
	 * @return dsl context
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static DSLContext getContext() {
		if (theContext == null) {
			theContext = DSL.using(ConnectionHelper.getConnection(), SQLDialect.MYSQL);
		}
		return theContext;
    }

}

/* EOF */

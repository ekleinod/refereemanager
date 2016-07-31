package de.edgesoft.refereemanager.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides constants.
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
 * @version 0.6.0
 * @since 0.4.0
 */
public class Constants {

	/** Program version. */
	public static final String APPVERSION = "0.6.0";
	
	/** Document version. */
	public static final String DOCVERSION = "0.6.0";

	/** Standard file name pattern for data files. */
	public static final String DATAFILENAMEPATTERN = "refereemanager_%04d.xml";
	
	/** Standard country code. */
	public static final String COUNTRY_CODE = "49";
	
	/** Standard contact type. */
	public static final String CONTACT_PRIVATE = "ContactType.p";
	
	/** Logger for all classes. */
	public static final Logger logger = LogManager.getLogger();
	
}

/* EOF */

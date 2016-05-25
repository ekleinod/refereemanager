package de.edgesoft.refereemanager;

import java.time.LocalDate;
import java.time.Month;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import de.edgesoft.edgeutils.Messages;
import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.refereemanager.jooq.tables.Seasons;
import de.edgesoft.refereemanager.utils.ConnectionHelper;

/**
 * Methods for seasons.
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
public class SeasonHelper extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public static void main(String[] args) {
		new SeasonHelper().executeOperation(args);
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
		
		setDescription("Methods for seasons.");
		
		addOption("l", "list", "list all seasons", false, false);
		addOption("c", "current", "list current season", false, false);
		addOption("h", "help", "display help message", false, false);
		
		init(args);
		
		boolean showHelp = true;
		
		if (hasOption("l")) {
			System.out.println(getSeasonList());
			showHelp = false;
		} 
		
		if (hasOption("c")) {
			System.out.println(getCurrentSeason());
			showHelp = false;
		} 

		if (showHelp) {
			Messages.printMessage(getUsage());
		}
		
	}

	/**
	 * Returns season list.
	 * 
	 * @return list of seasons
	 *  @retval empty if no seasons exists
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public Result<Record> getSeasonList() {
		
		DSLContext create = ConnectionHelper.getContext();
		
		Result<Record> result = create.select()
				.from(Seasons.SEASONS)
				.orderBy(Seasons.SEASONS.YEAR_START.desc())
				.fetch();
		
		return result;
	}
	
	/**
	 * Returns current season.
	 * 
	 * @return current season
	 *  @retval null if no current season exists
	 * 
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public Record getCurrentSeason() {
		
		DSLContext create = ConnectionHelper.getContext();
		
		int iYear = (LocalDate.now().getMonth().getValue() <= Month.JULY.getValue()) ?
				LocalDate.now().getYear() - 1 :
					LocalDate.now().getYear();
		
		Result<Record> result = create.select()
				.from(Seasons.SEASONS)
				.where(Seasons.SEASONS.YEAR_START.eq(iYear))
				.fetch();
		
		return (result.isEmpty()) ? null : result.get(0);
	}
	
}

/* EOF */

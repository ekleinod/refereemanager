package de.edgesoft.refereemanager;

import java.time.LocalDate;
import java.time.Month;

import org.jooq.Record;
import org.jooq.Result;
import org.junit.Assert;
import org.junit.Test;

import de.edgesoft.refereemanager.jooq.tables.Seasons;

/**
 * Unit test for SeasonHelper.
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
public class SeasonHelperTest {

	/**
	 * Tests season list.
	 */
	@Test
	public void testList() {
		
		SeasonHelper clsHelper = new SeasonHelper();
		
		Result<Record> result = clsHelper.getSeasonList();
		Assert.assertNotNull(result);
		Assert.assertTrue(result.size() > 0);
		
	}
	
	/**
	 * Tests current season.
	 */
	@Test
	public void testCurrent() {
		
		SeasonHelper clsHelper = new SeasonHelper();
		
		Record result = clsHelper.getCurrentSeason();
		Assert.assertNotNull(result);
		
		int iYear = (LocalDate.now().getMonth().getValue() <= Month.JULY.getValue()) ?
				LocalDate.now().getYear() - 1 :
					LocalDate.now().getYear();
				
		Assert.assertEquals(iYear, result.getValue(Seasons.SEASONS.YEAR_START));
		
	}
	
}

/* EOF */

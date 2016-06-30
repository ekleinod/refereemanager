package de.edgesoft.refereemanager.model;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for SeasonModel.
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
 * @version 0.5.0
 * @since 0.5.0
 */
public class SeasonModelTest {
	
	/**
	 * Rule for expected exception
	 */
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	/**
	 * Tests read file not found.
	 */
	@Test
	public void testErrorGetStartYearForDateNull() throws Exception {

		exception.expect(NullPointerException.class);
		exception.expectMessage("date must not be null");
		SeasonModel.getStartYearForDate(null);
		
	}
	
	/**
	 * Tests getCurrentStartYear.
	 */
	@Test
	public void testGetCurrentStartYear() {
		
		Integer iTest = SeasonModel.getCurrentStartYear();
		
		int iExpected = (LocalDate.now().getMonth().ordinal() < SeasonModel.NEWSEASON.ordinal()) ? LocalDate.now().getYear() - 1 : LocalDate.now().getYear();
		
		Assert.assertEquals(Integer.valueOf(iExpected), iTest);
		
	}

	/**
	 * Tests getStartYearForDate.
	 */
	@Test
	public void testGetStartYearForDate() {
		
		for (Month theMonth : Month.values()) {
			
			Integer iTest = SeasonModel.getStartYearForDate(LocalDate.of(2015, theMonth.getValue(), 01));
			
			Assert.assertEquals(Integer.valueOf((theMonth.ordinal() < SeasonModel.NEWSEASON.ordinal()) ? 2014 : 2015), iTest);
			
		}
		
	}

}

/* EOF */

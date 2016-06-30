package de.edgesoft.refereemanager.model;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;

import org.junit.Assert;
import org.junit.Test;

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
	 * Tests getCurrentStartYear.
	 */
	@SuppressWarnings("static-method")
	@Test
	public void testGetCurrentStartYear() {
		
		Clock testClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
		Clock testClock = Clock.offset(testClock, Duration.of(1, mon));
		Period.ofMonths(1);
		
		Integer iTest = SeasonModel.getCurrentStartYear();
		
		Assert.assertEquals(Integer.valueOf(LocalDate.now().getYear()), iTest);
		
	}

}

/* EOF */

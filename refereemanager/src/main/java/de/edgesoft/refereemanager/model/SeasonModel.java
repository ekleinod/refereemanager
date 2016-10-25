package de.edgesoft.refereemanager.model;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

import de.edgesoft.refereemanager.jaxb.Season;

/**
 * Season model, additional methods for jaxb model class.
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
public class SeasonModel extends Season {
	
	/** Start month of a new season. */
	public static final Month NEWSEASON = Month.JUNE;

	/**
	 * Returns start year for current season.
	 * 
	 * A season runs from june to may. 
	 * 
	 * @return start year of current season
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
    public static Integer getCurrentStartYear() {
    	return getStartYearForDate(LocalDate.now());
    }

	/**
	 * Returns start year of a season for a given date.
	 * 
	 * A season runs from june to may.
	 * 
	 * @param theDate date
	 * @return start year of season for date
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
    public static Integer getStartYearForDate(final LocalDate theDate) {
    	
		Objects.requireNonNull(theDate, "date must not be null");
		
    	int iReturn = theDate.getYear();
    	if (theDate.getMonth().ordinal() < NEWSEASON.ordinal()) {
    		iReturn--;
    	}
    	
        return Integer.valueOf(iReturn);
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public String getTitle() {
    	if (title == null) {
    		return MessageFormat.format("{0,number,####}-{1,number,####}", getStartYear(), getStartYear() + 1);
    	}
        return title;
    }

}

/* EOF */

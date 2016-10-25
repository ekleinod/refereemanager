package de.edgesoft.refereemanager.model;

import java.text.MessageFormat;
import java.util.List;

import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Team;
import de.edgesoft.refereemanager.jaxb.Venue;

/**
 * Team model, additional methods for jaxb model class.
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
 * @version 0.9.0
 * @since 0.8.0
 */
public class TeamModel extends Team {
	
	/**
	 * Display title.
	 * 
	 * @return display title
	 * 
	 * @version 0.9.0
	 * @since 0.8.0
	 */
	@Override
    public String getDisplayTitle() {
		
		if (getClub() == null) {
			return getId();
		}
		
		if (getNumber() == null) {
			return getClub().getDisplayTitle();
		}
		
		return MessageFormat.format("{0} {1}", getClub().getDisplayTitle(), getNumber());
    }
    
	/**
	 * Filename.
	 * 
	 * @return filename
	 * 
	 * @version 0.9.0
	 * @since 0.9.0
	 */
    public String getFilename() {
		
		if (getClub() == null) {
			return getId();
		}
		
		if (getNumber() == null) {
			return getClub().getFilename();
		}
		
		return String.format("%s%d", getClub().getFilename(), getNumber());
    }
    
	/**
	 * Return contact person.
	 * 
	 * @return contact person
	 * 
	 * @version 0.9.0
	 * @since 0.9.0
	 */
    @Override
    public Person getContactPerson() {
    	
		if (super.getContactPerson() != null) {
			return super.getContactPerson();
		}
		
		if ((getClub() != null) && (getClub().getContactPerson() != null)) {
			return getClub().getContactPerson();
		}
		
		return null;
		
    }
    
	/**
	 * Return venues.
	 * 
	 * @return venues
	 * 
	 * @version 0.9.0
	 * @since 0.9.0
	 */
    @Override
    public List<Venue> getVenue() {
    	
		if (super.getVenue().isEmpty() && (getClub() != null) && (!getClub().getVenue().isEmpty())) {
			return getClub().getVenue();
		}
		
		return super.getVenue();
		
    }
    
}

/* EOF */

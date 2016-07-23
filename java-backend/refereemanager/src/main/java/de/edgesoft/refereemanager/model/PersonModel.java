package de.edgesoft.refereemanager.model;

import java.util.Comparator;
import java.util.Objects;

import de.edgesoft.refereemanager.jaxb.Person;

/**
 * Person model, additional methods for jaxb model class.
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
public class PersonModel extends Person {
	
	/** Comparator for people. */
	public static final Comparator<Person> NAME_FIRSTNAME = Comparator.comparing(Person::getName).
			thenComparing(Comparator.comparing(Person::getFirstName));
	
	/**
	 * Full name of person.
	 * 
	 * @return full name of the person
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
    public String getFullName() {
    	return getFormattedName(OutputType.FULLNAME);
    }
    
	/**
	 * Table name of person.
	 * 
	 * @return table name of the person
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
    public String getTableName() {
    	return getFormattedName(OutputType.TABLENAME);
    }
    
	/**
	 * Formatted name of person.
	 * 
	 * @param theOutputType output type
	 * @return formatted name
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
    private String getFormattedName(final OutputType theOutputType) {
		Objects.requireNonNull(theOutputType, "output type must not be null");
		
    	StringBuilder sbReturn = new StringBuilder();

    	switch (theOutputType) {
    		case FULLNAME:
    			
				if ((getTitle() != null) && !getTitle().isEmpty()) {
					sbReturn.append(getTitle());
				}
				
				if ((getFirstName() != null) && !getFirstName().isEmpty()) {
					if (sbReturn.length() > 0) {
						sbReturn.append(" ");
					}
					sbReturn.append(getFirstName());
				}
				
				if ((getName() != null) && !getName().isEmpty()) {
					if (sbReturn.length() > 0) {
						sbReturn.append(" ");
					}
					sbReturn.append(getName());
				}
				
    			break;
    		
			case TABLENAME:
				
				if ((getName() != null) && !getName().isEmpty()) {
					sbReturn.append(getName());
				}
				
				if ((getTitle() != null) && !getTitle().isEmpty()) {
					if (sbReturn.length() > 0) {
						sbReturn.append(", ");
					}
					sbReturn.append(getTitle());
				}
				
				if ((getFirstName() != null) && !getFirstName().isEmpty()) {
					if (sbReturn.length() > 0) {
						sbReturn.append(", ");
					}
					sbReturn.append(getFirstName());
				}
				
				break;
		}
    	
    	return sbReturn.toString();
    }

}

/* EOF */

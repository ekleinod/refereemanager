package de.edgesoft.refereemanager.model;

import java.util.HashSet;
import java.util.Set;

import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.jaxb.Content;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.Wish;

/**
 * Content model, additional methods for jaxb model class.
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
 * @version 0.7.0
 * @since 0.5.0
 */
public class ContentModel extends Content {
	
	/**
	 * Returns all referenced (used) clubs.
	 * 
	 * @return referenced clubs (empty if there are none)
	 * 
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public Set<Club> getReferencedClubs() {

		Set<Club> setReturn = new HashSet<>();
		
		for (Referee theReferee : getReferee()) {
			
			for (Wish theWish : theReferee.getPrefer()) {
				if (theWish.getClub() != null) {
					setReturn.add(theWish.getClub());
				}
			}
			
			for (Wish theWish : theReferee.getAvoid()) {
				if (theWish.getClub() != null) {
					setReturn.add(theWish.getClub());
				}
			}
			
			if (theReferee.getMember() != null) {
				setReturn.add(theReferee.getMember());
			}
			
			if (theReferee.getReffor() != null) {
				setReturn.add(theReferee.getReffor());
			}
			
		}
		
		return setReturn;
		
	}
		
	/**
	 * Returns all non-referenced (unused) clubs.
	 * 
	 * @return non-referenced clubs (empty if there are none)
	 * 
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public Set<Club> getNonReferencedClubs() {

		Set<Club> setReturn = new HashSet<>();
		
		setReturn.addAll(getClub());
		setReturn.removeAll(getReferencedClubs());
		
		return setReturn;
		
	}
		

}

/* EOF */

package de.edgesoft.refereemanager.model;

import de.edgesoft.refereemanager.jaxb.Wish;

/**
 * Wish model, additional methods for jaxb model class.
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
 * @since 0.6.0
 */
public class WishModel extends Wish {
	
	/**
	 * Display title.
	 * 
	 * @return display title
	 * 
	 * @version 0.6.0
	 * @since 0.6.0
	 */
    public String getDisplayTitle() {
    	StringBuilder sbReturn = new StringBuilder();
    	
    	if (getClub() != null) {
    		sbReturn.append(getClub().getDisplayTitle());
    	}
    	
    	if (getLeague() != null) {
    		if (sbReturn.length() > 0) {
    			sbReturn.append(", ");
    		}
    		sbReturn.append(getLeague().getDisplayTitle());
    	}
    	
    	if (getSexType() != null) {
    		if (sbReturn.length() > 0) {
    			sbReturn.append(", ");
    		}
    		sbReturn.append(getSexType().getDisplayTitle());
    	}
    	
    	if ((isSaturday() != null) && isSaturday()) {
    		if (sbReturn.length() > 0) {
    			sbReturn.append(", ");
    		}
    		sbReturn.append("sonnabends");
    	}
    	
    	if ((isSunday() != null) && isSunday()) {
    		if (sbReturn.length() > 0) {
    			sbReturn.append(", ");
    		}
    		sbReturn.append("sonntags");
    	}
    	
    	if ((isTournamentOnly() != null) && isTournamentOnly()) {
    		if (sbReturn.length() > 0) {
    			sbReturn.append(", ");
    		}
    		sbReturn.append("nur Turniere");
    	}
    	
    	if ((isLeagueGamesOnly() != null) && isLeagueGamesOnly()) {
    		if (sbReturn.length() > 0) {
    			sbReturn.append(", ");
    		}
    		sbReturn.append("nur Ligaspiele");
    	}
    	
    	return sbReturn.toString();
    }
    
}

/* EOF */

package de.edgesoft.refereemanager.model;

import java.text.Collator;
import java.util.Comparator;

import de.edgesoft.refereemanager.jaxb.Team;

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
 * @version 0.8.0
 * @since 0.8.0
 */
public class TeamModel extends Team {
	
	/** 
	 * Comparator by league for teams. 
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static final Comparator<Team> LEAGUE = Comparator.comparing(team -> team.getLeague().getShorttitle(), Collator.getInstance());
	
	/** 
	 * Comparator by league hen club for teams. 
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static final Comparator<Team> LEAGUE_CLUB = LEAGUE.thenComparing(Comparator.comparing(team -> team.getClub().getShorttitle(), Collator.getInstance()));
	
	/**
	 * Display title.
	 * 
	 * @return display title
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	@Override
    public String getDisplayTitle() {
    	return getId();
    }
    
}

/* EOF */

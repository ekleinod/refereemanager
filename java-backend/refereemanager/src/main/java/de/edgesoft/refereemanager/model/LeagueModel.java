package de.edgesoft.refereemanager.model;

import de.edgesoft.refereemanager.jaxb.League;

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
 * @version 0.9.0
 * @since 0.9.0
 */
public class LeagueModel extends League {
	
	/**
	 * Returns all used leagues, i.e. leagues with assignments.
	 * 
	 * @todo rewrite to look for assignments
	 * 
	 * @return used leagues (empty if there are none)
	 * 
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	@Override
	public Boolean isNational() {
		return (super.isNational() == null) ? Boolean.FALSE : super.isNational();
	}
		
}

/* EOF */

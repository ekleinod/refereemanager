package de.edgesoft.refereemanager.model;

import java.util.Comparator;
import java.util.stream.Stream;

import de.edgesoft.refereemanager.jaxb.Content;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.StatusType;

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
 * @version 0.5.0
 * @since 0.5.0
 */
public class ContentModel extends Content {
	
	/**
	 * Returns list of sorted referees.
	 * 
	 * @return list of referees
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
    public Stream<Referee> getRefereeStreamSorted() {
    	return getRefereeStreamSorted(PersonModel.NAME_FIRSTNAME);
    }

	/**
	 * Returns list of referees sorted by criterion as stream.
	 * 
	 * @param theComparator comparator
	 * @return list of referees
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
    public Stream<Referee> getRefereeStreamSorted(final Comparator<? super Referee> theComparator) {
    	return getReferee().stream().sorted(theComparator);
    }

	/**
	 * Returns sorted list of status types.
	 * 
	 * @return list of status types
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
    public Stream<StatusType> getStatusTypeStreamSorted() {
    	return getStatusType().stream().sorted(TitledIDTypeModel.TITLE);
    }

}

/* EOF */

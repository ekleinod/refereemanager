package de.edgesoft.refereemanager.utils;

import java.util.List;

import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.OutputType;

/**
 * Provides methods and properties for templates.
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
public class TemplateHelper {
	
	/** Keyword for text to replace. */
	public final String KEY_REPLACE = "generated %s";
	
	/** Keyword for if. */
	public final String KEY_IF = "if %s %s";
	
	/** Keyword for end if. */
	public final String KEY_ENDIF = "endif %s %s";
	
	/** Keyword for foreach. */
	public final String KEY_FOREACH = "foreach %s %s";
	
	/** Keyword for end foreach. */
	public final String KEY_ENDFOREACH = "endforeach %s %s";
	
	/** Condition empty. */
	public final String CONDITION_EMPTY = "empty";
	
	/** Condition not empty. */
	public final String CONDITION_NOTEMPTY = "notempty";
	
	/** A token. */
	public final String TOKEN = "**%s**";
	
	/** Token: replace. */
	public final String TOKEN_REPLACE = String.format("**%s**", KEY_REPLACE);
	
	/**
	 * Returns filled template.
	 * 
	 * @param theTemplate template
	 * @param theData data
	 * @return filled template
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static List<String> fillTemplate(final List<String> theTemplate, final RefereeManager theData) {
		
		((ContentModel) theData.getContent()).getRefereeStreamSorted()
				.map(referee -> referee.getFormattedName(OutputType.TABLENAME))
				.forEach(System.out::println);
		
		return theTemplate;
	}
	
}

/* EOF */

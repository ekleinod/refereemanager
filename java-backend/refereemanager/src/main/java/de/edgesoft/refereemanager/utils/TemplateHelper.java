package de.edgesoft.refereemanager.utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.jaxb.StatusType;

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
	public static final String KEY_REPLACE = "generated %s";
	
	/** Keyword for if. */
	public static final String KEY_IF = "if %1$s %2$s";
	
	/** Keyword for end if. */
	public static final String KEY_ENDIF = "endif %1$s %2$s";
	
	/** Keyword for foreach. */
	public static final String KEY_FOREACH = "foreach %s";
	
	/** Keyword for end foreach. */
	public static final String KEY_ENDFOREACH = "endforeach %s";
	
	/** Condition empty. */
	public static final String CONDITION_EMPTY = "empty";
	
	/** Condition not empty. */
	public static final String CONDITION_NOTEMPTY = "notempty";
	
	/** A token. */
	public static final String TOKEN = "**%s**";
	
	/** Token: replace. */
	public static final String TOKEN_REPLACE = String.format(TOKEN, KEY_REPLACE);
	
	/** Token: if. */
	public static final String TOKEN_IF = String.format("%s%%3$s%s", String.format(TOKEN, KEY_IF), String.format(TOKEN, KEY_ENDIF));
	
	/** Token: if empty. */
	public static final String TOKEN_IF_EMPTY = String.format(TOKEN_IF, CONDITION_EMPTY, "%1$s", "%2$s");
	
	/** Token: if not empty. */
	public static final String TOKEN_IF_NOTEMPTY = String.format(TOKEN_IF, CONDITION_NOTEMPTY, "%1$s", "%2$s");
	
	/** Token: foreach. */
	public static final String TOKEN_FOREACH = String.format(TOKEN, KEY_FOREACH);
	
	/** Token: endforeach. */
	public static final String TOKEN_ENDFOREACH = String.format(TOKEN, KEY_ENDFOREACH);
	
	/**
	 * Returns filled template.
	 * 
	 * Templates are filled line by line.
	 * This may be slow, but for now this seems to be the best way to process
	 * the template without writing a real parser.
	 * 
	 * Thus:
	 * 
	 * - every replace has to be in one line
	 * - every if/endif has to be in one line
	 * - every foreach has to start in one line and to end in one line, every line in between is looped
	 * 	- loops may not be nested
	 * 
	 * @todo whole loop processing
	 * 
	 * @param theTemplate template
	 * @param theData data
	 * @return filled template
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static List<String> fillTemplate(final List<String> theTemplate, final RefereeManager theData) {
		
		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		
		List<String> lstReturn = new ArrayList<>();

		Deque<List<String>> queueLoops = new ArrayDeque<>();
		List<String> lstLoopKeys = Arrays.asList(Referee.class.getSimpleName().toLowerCase(), StatusType.class.getSimpleName().toLowerCase());
		
		for (String sLine : theTemplate) {
			
			
			boolean processLine = true;
			
			// loop ends? Process loop lines.
			for (String sKey : lstLoopKeys) {
				if (sLine.startsWith(String.format(TOKEN_ENDFOREACH, sKey))) {
					List<String> lstLoop = queueLoops.removeFirst();
					switch (sKey) {
						case "referee":
							break;
					}
					processLine = false;
				}
			}
			
			// in loop? Store line
			if (!queueLoops.isEmpty()) {
				queueLoops.peekFirst().add(sLine);
				processLine = false;
			}
			
			// loop started? Ignore line, go into "loop mode".
			for (String sKey : lstLoopKeys) {
				if (sLine.startsWith(String.format(TOKEN_FOREACH, sKey))) {
					queueLoops.addFirst(new ArrayList<>());
					processLine = false;
				}
			}
			
			// process line
			if (processLine) {
				lstReturn.add(fillLine(sLine, theData));
			}
			
		}

		return lstReturn;
	}
	
	/**
	 * Returns filled line.
	 * 
	 * @param theLine line
	 * @param theData data
	 * @return filled line
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private static String fillLine(final String theLine, final RefereeManager theData) {
		
		Objects.requireNonNull(theLine, "line must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		
		String sReturn = theLine;
		
		return sReturn;
	}
	
}

/* EOF */

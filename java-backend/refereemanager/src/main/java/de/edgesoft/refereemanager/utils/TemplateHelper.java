package de.edgesoft.refereemanager.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.jaxb.StatusType;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.model.ContentModel;

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
	 * @param theLoopID id of element that is looped at the moment
	 * @return filled template
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static List<String> fillTemplate(final List<String> theTemplate, final RefereeManager theData, final TitledIDType theLoopID) {
		
		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		
		List<String> lstReturn = new ArrayList<>();

		Deque<List<String>> queueLoops = new ArrayDeque<>();
		List<Class<? extends TitledIDType>> lstLoopKeys = Arrays.asList(Referee.class, StatusType.class);
		
		for (String sLine : theTemplate) {
			
			boolean processLine = true;
			
			// loop ends? Process loop lines.
			for (final Class<? extends TitledIDType> clsKey : lstLoopKeys) {
				if (sLine.startsWith(String.format(TOKEN_ENDFOREACH, clsKey.getSimpleName().toLowerCase()))) {
					
					final List<String> lstLoopContent = queueLoops.removeFirst();
					List<String> lstLoopReturn = new ArrayList<>();
					
					switch (clsKey.getSimpleName().toLowerCase()) {
						case "referee":
							for (final Referee theReferee : ((ContentModel) theData.getContent()).getRefereeStreamSorted().collect(Collectors.toList())) {
								lstLoopReturn.addAll(fillTemplate(lstLoopContent, theData, theReferee));
							}
							break;
						case "statustype":
							for (final StatusType theStatusType : ((ContentModel) theData.getContent()).getStatusTypeStreamSorted().collect(Collectors.toList())) {
								lstLoopReturn.addAll(fillTemplate(lstLoopContent, theData, theStatusType));
							}
							break;
					}
					
					if (queueLoops.isEmpty()) {
						lstReturn.addAll(lstLoopReturn);
					} else {
						queueLoops.peekFirst().addAll(lstLoopReturn);
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
			for (final Class<? extends TitledIDType> clsKey : lstLoopKeys) {
				if (sLine.startsWith(String.format(TOKEN_FOREACH, clsKey.getSimpleName().toLowerCase()))) {
					queueLoops.addFirst(new ArrayList<>());
					processLine = false;
				}
			}
			
			// process line
			if (processLine) {
				lstReturn.add(fillLine(sLine, theData, theLoopID));
			}
			
		}

		return lstReturn;
	}
	
	/**
	 * Returns filled line.
	 * 
	 * @param theLine line
	 * @param theData data
	 * @param theLoopID id of element that is looped at the moment
	 * @return filled line
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private static String fillLine(final String theLine, final RefereeManager theData, final TitledIDType theLoopID) {
		
		Objects.requireNonNull(theLine, "line must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		
		String sReturn = theLine;
		
		sReturn = fillLine(sReturn, theData.getInfo(), theLoopID);
		
		sReturn = fillLine(sReturn, theData.getContent().getSeason(), theLoopID);
		
		return sReturn;
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
	private static String fillLine(final String theLine, final Object theData, final TitledIDType theLoopID) {
		
		String sReturn = theLine;
		
		if (theData != null) {
			
			try {
				
				Map<String, Method> mapProperties =
				
				Arrays.asList(
						Introspector.getBeanInfo(theData.getClass()).getPropertyDescriptors()
				)
						.stream()
						.filter(pd -> Objects.nonNull(pd.getReadMethod()))
						.filter(pd -> Objects.nonNull(pd.getWriteMethod()))
						.collect(
								Collectors.toMap(
										PropertyDescriptor::getName,
										PropertyDescriptor::getReadMethod
										)
								);
				
				for (Entry<String, Method> theProperty : mapProperties.entrySet()) {
					Object oResult = theProperty.getValue().invoke(theData);
					
					if (oResult != null) {
						
						String sToken = String.format("%s:%s", theData.getClass().getSimpleName().toLowerCase(), theProperty.getKey().toLowerCase());
						String sReplacement = oResult.toString();
						
						if (theProperty.getValue().getReturnType() == LocalDateTime.class) {
							
							for (String theType : new String[]{"date", "time", "datetime"}) {
								for (FormatStyle theStyle : FormatStyle.values()) {
									String sNewToken = String.format("%s:%s:%s:%s", 
											theData.getClass().getSimpleName().toLowerCase(), 
											theProperty.getKey().toLowerCase(),
											theType,
											theStyle.toString().toLowerCase());

									if (sReturn.contains(sNewToken)) {
										sToken = sNewToken;
										
										DateTimeFormatter fmtOutput = 
												(theType.equals("date")) ?
														DateTimeFormatter.ofLocalizedDate(theStyle).withLocale(Locale.GERMANY)
														: (theType.equals("time")) ?
																DateTimeFormatter.ofLocalizedTime(theStyle).withLocale(Locale.GERMANY).withZone(ZoneId.systemDefault())
																: DateTimeFormatter.ofLocalizedDateTime(theStyle).withLocale(Locale.GERMANY).withZone(ZoneId.systemDefault());
																
										if (oResult instanceof LocalDateTime) {
											sReplacement = ((LocalDateTime) oResult).format(fmtOutput);
										}
										
										sReturn = replaceText(sReturn, 
												String.format(TOKEN_REPLACE, sToken), 
												sReplacement
												);
									}
								}
							}
							
						} else {
						
							sReturn = replaceText(sReturn, 
									String.format(TOKEN_REPLACE, sToken), 
									sReplacement
									);
						}
						
					}
					
				}
	
			} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		
		return sReturn;
	}
	
	/**
	 * Replaces replacee in text with value.
	 *
	 * @param theText text
	 * @param theReplacee text to be replaced
	 * @param theValue value
	 * @return replaced text
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private static String replaceText(final String theText, final String theReplacee, final String theValue) {
		String sReturn = theText;
		
		while (sReturn.contains(theReplacee)) {
			sReturn = sReturn.replace(theReplacee, theValue);
		}
		
		return sReturn;
	}

}

/* EOF */

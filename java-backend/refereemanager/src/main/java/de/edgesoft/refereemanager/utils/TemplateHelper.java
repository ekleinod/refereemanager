package de.edgesoft.refereemanager.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.commons.ModelClass;
import de.edgesoft.refereemanager.jaxb.Content;
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
	public static final String TOKEN_IF = String.format("%s(.*)%s", String.format(TOKEN, KEY_IF), String.format(TOKEN, KEY_ENDIF));
	
	/** Token: if empty. */
	public static final String TOKEN_IF_EMPTY = String.format(TOKEN_IF, CONDITION_EMPTY, "%1$s");
	
	/** Token: if not empty. */
	public static final String TOKEN_IF_NOTEMPTY = String.format(TOKEN_IF, CONDITION_NOTEMPTY, "%1$s");
	
	/** Token: foreach. */
	public static final String TOKEN_FOREACH = String.format(TOKEN, KEY_FOREACH);
	
	/** Token: endforeach. */
	public static final String TOKEN_ENDFOREACH = String.format(TOKEN, KEY_ENDFOREACH);
	
	/** Map of properties and their getters for a class. */
	private static Map<Class<? extends ModelClass>, Map<String, Method>> mapGetters = null;
	
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
				lstReturn.add(fillLine(sLine, theData, theLoopID, ""));
			}
			
		}

		return lstReturn;
	}
	
	/**
	 * Returns filled line.
	 * 
	 * @param theLine line
	 * @param theData data
	 * @param theLoopElement element that is looped at the moment
	 * @param theTokenPrefix token prefix
	 * 
	 * @return filled line
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private static String fillLine(final String theLine, final ModelClass theData, final TitledIDType theLoopElement, final String theTokenPrefix) {
		
		Objects.requireNonNull(theLine, "line must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		
		String sReturn = theLine;
		
		// fill direct data
		sReturn = fillLineDirectData(sReturn, theData, theLoopElement, theTokenPrefix);
		
		// deal with special cases (recursion, loops etc.)
		try {
			
			for (Entry<String, Method> theGetter : getGetters(theData.getClass()).entrySet()) {
				
				Object oResult = theGetter.getValue().invoke(theData);
				
				if (oResult != null) {
				
					if ((theLoopElement == null) || !theLoopElement.getClass().isInstance(theData) || (theLoopElement == theData)) {
						
						String sTokenClass = getTokenClass(theData);
						
						// avoid double mention of class
						String sTokenPrefix = (theTokenPrefix.indexOf(':') == theTokenPrefix.lastIndexOf(':')) ?
								String.format("%s%s:%s:", theTokenPrefix, sTokenClass, theGetter.getKey()) :
									String.format("%s%s:", theTokenPrefix, theGetter.getKey());
						if ((theData instanceof RefereeManager) || (theData instanceof Content)) {
							sTokenPrefix = "";
						}
						
						if (oResult instanceof ModelClass) {
							
//							Constants.logger.info(sTokenPrefix);
							sReturn = fillLine(sReturn, (ModelClass) oResult, theLoopElement, sTokenPrefix);
							
						} else {
							
							// @todo solve this hack, it is needed, because the lists of idrefs contain jaxbelements!
							try {
								for (ModelClass theDataObject : (List<ModelClass>) oResult) {
									sReturn = fillLine(sReturn, theDataObject, theLoopElement, sTokenPrefix);
								}
							} catch (ClassCastException e) {
//								e.printStackTrace();
							}
							
						}
							
					}
					
				}
				
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
				
		return sReturn;
	}
	
	/**
	 * Fills line with direct data.
	 * 
	 * @param theLine line
	 * @param theData data
	 * @param theLoopElement element that is looped at the moment
	 * @param theTokenPrefix token prefix
	 * 
	 * @return filled line
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private static String fillLineDirectData(final String theLine, final ModelClass theData, final TitledIDType theLoopElement, final String theTokenPrefix) {
		
		Objects.requireNonNull(theLine, "line must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		
		String sReturn = theLine;
		
		try {
			
			if ((theLoopElement == null) || !theLoopElement.getClass().isInstance(theData) || (theLoopElement == theData)) {
				
				String sTokenClass = getTokenClass(theData);
				
				for (Entry<String, Method> theGetter : getGetters(theData.getClass()).entrySet()) {
					
					Object oResult = theGetter.getValue().invoke(theData);
					
					if (!(oResult instanceof ModelClass) && !(oResult instanceof List<?>)) {

						// avoid double mention of class
						String sToken = (theTokenPrefix.indexOf(':') == theTokenPrefix.lastIndexOf(':')) ?
								String.format("%s%s:%s", theTokenPrefix, sTokenClass, theGetter.getKey()) :
									String.format("%s%s", theTokenPrefix, theGetter.getKey());
						
						if ((theGetter.getValue().getReturnType() == LocalDateTime.class) ||
								(theGetter.getValue().getReturnType() == LocalDate.class) ||
								(theGetter.getValue().getReturnType() == LocalTime.class)) {

							// own for loop with replace, for there are more than one datetime tokens allowed in one line
							for (String theType : new String[]{"date", "time", "datetime"}) {
								for (FormatStyle theStyle : FormatStyle.values()) {
									
									String sNewToken = String.format("%s:%s:%s",
											sToken, 
											theType,
											theStyle.toString().toLowerCase());
									
									if (sReturn.contains(sNewToken)) {
										DateTimeFormatter fmtOutput = 
												(theType.equals("date")) ?
														DateTimeFormatter.ofLocalizedDate(theStyle).withLocale(Locale.GERMANY)
														: (theType.equals("time")) ?
																DateTimeFormatter.ofLocalizedTime(theStyle).withLocale(Locale.GERMANY).withZone(ZoneId.systemDefault())
																: DateTimeFormatter.ofLocalizedDateTime(theStyle).withLocale(Locale.GERMANY).withZone(ZoneId.systemDefault());
																
																String sReplacement = "";
																if (oResult instanceof LocalDateTime) {
																	sReplacement = ((LocalDateTime) oResult).format(fmtOutput);
																} else if (oResult instanceof LocalDate) {
																	sReplacement = ((LocalDate) oResult).format(fmtOutput);
																} else if (oResult instanceof LocalTime) {
																	sReplacement = ((LocalTime) oResult).format(fmtOutput);
																}
																
																sReturn = replaceTextAndConditions(sReturn, 
																		sNewToken, 
																		sReplacement
																		);
									}
								}
							}
							
						} else {
							
							if (sToken.startsWith("referee:")) {
								Constants.logger.info(sToken);
//								Constants.logger.info(oResult);
							}
							
							if (sReturn.contains(sToken)) {
								sReturn = replaceTextAndConditions(sReturn, 
										sToken,
										(oResult == null) ? "" : oResult.toString()
										);
							}
							
						}
						
					}
					
				}
				
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
				
		return sReturn;
	}
	
	/**
	 * Replaces replacee-tokens in text with value, considers conditions first.
	 *
	 * @param theText text
	 * @param theReplacee text to be replaced
	 * @param theValue value
	 * @return replaced text
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private static String replaceTextAndConditions(final String theText, final String theReplacee, final String theValue) {
		String sReturn = theText;
		
		
		// conditions
		String sCondition = String.format(TOKEN_IF_EMPTY, theReplacee).replace("**", "\\*\\*");
		sReturn = sReturn.replaceAll(sCondition, theValue.isEmpty() ? "$1" : "");
		
		sCondition = String.format(TOKEN_IF_NOTEMPTY, theReplacee).replace("**", "\\*\\*");
		sReturn = sReturn.replaceAll(sCondition, !theValue.isEmpty() ? "$1" : "");
		
		// replace tokens
		String sToken = String.format(TOKEN_REPLACE, theReplacee);
		while (sReturn.contains(sToken)) {
			sReturn = sReturn.replace(sToken, theValue);
		}
		
		return sReturn;
	}

	/**
	 * Returns the getters of the given class.
	 * 
	 * Works with singleton for speed issues.
	 * 
	 * @param theClass class to get getters for
	 * @return map of properties and their getters
	 *
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private static Map<String, Method> getGetters(final Class<? extends ModelClass> theClass) {
		
		if (mapGetters == null) {
			mapGetters = new HashMap<>();
		}
		
		mapGetters.computeIfAbsent(theClass,
				it -> {
					try {
						return Arrays.asList(Introspector.getBeanInfo(theClass, ModelClass.class).getMethodDescriptors())
								.stream()
								.filter(md -> (md.getName().startsWith("get") || md.getName().startsWith("is")))
								.filter(md -> (md.getMethod().getParameterCount() == 0))
								.collect(
										Collectors.toMap(
												md -> (md.getName().startsWith("get") ? md.getName().substring("get".length()).toLowerCase() : md.getName().substring("is".length()).toLowerCase()),
												MethodDescriptor::getMethod
												)
										);
					} catch (IntrospectionException e) {
						e.printStackTrace();
					}
					return null;
				}
				);
		
		return mapGetters.get(theClass);
		
	}
	
	/**
	 * Returns token class as string.
	 * 
	 * Own model classes have suffix "model", remove for clearer template syntax.
	 * 
	 * @todo is this too much of a hack?
	 * 
	 * @param theData data
	 * 
	 * @return filled line
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	private static String getTokenClass(final ModelClass theData) {
		String sTokenClass = theData.getClass().getSimpleName().toLowerCase();
		if (sTokenClass.endsWith("model")) {
			sTokenClass = sTokenClass.substring(0, (sTokenClass.length() - "model".length()));
		}
		return sTokenClass;
	}

}

/* EOF */

package de.edgesoft.refereemanager.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.commons.ModelClass;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.Prefs;
import de.edgesoft.refereemanager.jaxb.Content;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.jaxb.StatusType;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;

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
 * @version 0.8.0
 * @since 0.5.0
 */
public class TemplateHelper {
	
	/** Keyword for text to replace. */
	public static final String KEY_REPLACE = "generated %s";
	
	/** Keyword for end of somethind. */
	public static final String KEY_END = "end %s";
	
	/** Keyword for if. */
	public static final String KEY_IF = "if %1$s %2$s";
	
	/** Keyword for foreach. */
	public static final String KEY_FOREACH = "foreach %1$s";
	
	/** Keyword for end foreach. */
	public static final String KEY_ENDFOREACH = String.format(KEY_END, KEY_FOREACH);
	
	/** Keyword for separator. */
	public static final String KEY_SEPARATOR = "separator";
	
	/** Condition empty. */
	public static final String CONDITION_EMPTY = "empty";
	
	/** Condition not empty. */
	public static final String CONDITION_NOTEMPTY = "notempty";
	
	/** Condition even. */
	public static final String CONDITION_EVEN = "even";
	
	/** Condition odd. */
	public static final String CONDITION_ODD = "odd";
	
	/** A token. */
	public static final String TOKEN = "**%s**";
	
	/** Token: replace. */
	public static final String TOKEN_REPLACE = String.format(TOKEN, KEY_REPLACE);
	
	/** Token: if. */
	public static final String TOKEN_IF = String.format("%s(.*)%s", String.format(TOKEN, KEY_IF), String.format(TOKEN, String.format(KEY_END, KEY_IF)));
	
	/** Token: if empty. */
	public static final String TOKEN_IF_EMPTY = String.format(TOKEN_IF, CONDITION_EMPTY, "%1$s");
	
	/** Token: if not empty. */
	public static final String TOKEN_IF_NOTEMPTY = String.format(TOKEN_IF, CONDITION_NOTEMPTY, "%1$s");
	
	/** Token: if even. */
	public static final String TOKEN_IF_EVEN = String.format(TOKEN_IF, CONDITION_EVEN, "%1$s");
	
	/** Token: if odd. */
	public static final String TOKEN_IF_ODD = String.format(TOKEN_IF, CONDITION_ODD, "%1$s");
	
	/** Token: foreach. */
	public static final String TOKEN_FOREACH = String.format(TOKEN, KEY_FOREACH);
	
	/** Token: endforeach. */
	public static final String TOKEN_ENDFOREACH = String.format(TOKEN, KEY_ENDFOREACH);
	
	/** Token: foreach-line. */
	public static final String TOKEN_FOREACH_LINE = String.format("%s(.*)%s", String.format(TOKEN, KEY_FOREACH), String.format(TOKEN, KEY_ENDFOREACH));
	
	/** Token: separator. */
	public static final String TOKEN_SEPARATOR = String.format("%s(.*)%s", String.format(TOKEN, KEY_SEPARATOR), String.format(TOKEN, String.format(KEY_END, KEY_SEPARATOR)));
	
	/** A template variable token. */
	public static final String VARIABLE_TOKEN = "%s:";
	
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
	 * @param theLoopElement element that is looped at the moment
	 * @param theLoopCount loop count
	 * @param theStatus status (all (default), active, inactive)
	 * @param isEditor use editor only data?
	 * 
	 * @return filled template
	 * 
	 * @version 0.8.0
	 * @since 0.5.0
	 */
	public static List<String> fillTemplate(final List<String> theTemplate, final RefereeManager theData, 
			final TitledIDType theLoopElement, final int theLoopCount, final ArgumentStatusType theStatus, final boolean isEditor) {
		
		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		Objects.requireNonNull(theStatus, "status must not be null");
		
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
					int iLoop = 1;
					
					switch (clsKey.getSimpleName().toLowerCase()) {
						case "referee":
							for (final Referee theReferee : ((ContentModel) theData.getContent()).getReferee().stream()
									.sorted(PersonModel.NAME_FIRSTNAME)
									.filter(theStatus.ref_filter())
									.collect(Collectors.toList())) {
								lstLoopReturn.addAll(fillTemplate(lstLoopContent, theData, theReferee, iLoop++, theStatus, isEditor));
							}
							break;
						case "statustype":
							for (final StatusType theStatusType : ((ContentModel) theData.getContent()).getStatusType().stream()
									.sorted(TitledIDTypeModel.TITLE)
									.filter(theStatus.status_filter())
									.collect(Collectors.toList())) {
								lstLoopReturn.addAll(fillTemplate(lstLoopContent, theData, theStatusType, iLoop++, theStatus, isEditor));
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
				String sTempLine = sLine;
				
				if (theLoopElement != null) {
					sTempLine = replaceCondition(TOKEN_IF_EVEN, sTempLine, getTokenClass(theLoopElement), (theLoopCount % 2 == 0));
					sTempLine = replaceCondition(TOKEN_IF_ODD, sTempLine, getTokenClass(theLoopElement), (theLoopCount % 2 == 1));
				}
				
				lstReturn.add(fillLine(sTempLine, theData, theLoopElement, "", isEditor));
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
	 * @param isEditor use editor only data?
	 * 
	 * @return filled line
	 * 
	 * @version 0.8.0
	 * @since 0.5.0
	 */
	private static String fillLine(final String theLine, final ModelClass theData, final TitledIDType theLoopElement, final String theTokenPrefix, final boolean isEditor) {
		
		Objects.requireNonNull(theLine, "line must not be null");
		Objects.requireNonNull(theData, MessageFormat.format("data must not be null: {0}", theTokenPrefix));
		
		String sReturn = theLine;
		
		// fill direct data
		sReturn = fillLineDirectData(sReturn, theData, theLoopElement, theTokenPrefix);
		
		// deal with special cases (recursion, loops etc.)
		try {
			
			for (Entry<String, Method> theGetter : getGetters(theData.getClass()).entrySet()) {
				
				Object oResult = theGetter.getValue().invoke(theData);
				
				if ((theLoopElement == null) || !theLoopElement.getClass().isInstance(theData) || (theLoopElement == theData)) {
					
					String sTokenClass = getTokenClass(theData);
					
					// avoid double mention of class
					String sTokenPrefix = (theTokenPrefix.indexOf(':') == theTokenPrefix.lastIndexOf(':')) ?
							String.format("%s%s:%s:", theTokenPrefix, sTokenClass, theGetter.getKey()) :
								String.format("%s%s:", theTokenPrefix, theGetter.getKey());
					if ((theData instanceof RefereeManager) || (theData instanceof Content)) {
						sTokenPrefix = "";
					}
					
					if ((oResult != null) && ModelClass.class.isAssignableFrom(theGetter.getValue().getReturnType())) {
						
						sReturn = fillLine(sReturn, (ModelClass) oResult, theLoopElement, sTokenPrefix, isEditor);
						
					} 
						
					if (List.class.isAssignableFrom(theGetter.getValue().getReturnType())) {
						
						if (sTokenPrefix.isEmpty()) {
							
							// @todo solve this hack, it is needed, because the lists of idrefs contain jaxbelements!
							try {
								for (ModelClass theDataObject : (List<ModelClass>) oResult) {
									sReturn = fillLine(sReturn, theDataObject, theLoopElement, sTokenPrefix, isEditor);
								}
							} catch (ClassCastException e) {
//									Constants.logger.error(sTokenPrefix);
//									e.printStackTrace();
							}
							
						} else {

							String sCondition = String.format(TOKEN_FOREACH_LINE, sTokenPrefix).replace("**", "\\*\\*");
							
//							if (sTokenPrefix.startsWith("referee:email")) {
//								System.out.println(sTokenPrefix);
//							}
//							
							// there is no "contains" for regular expressions
							if (Pattern.compile(sCondition).matcher(sReturn).find()) {
								
								if (((List<ModelClass>) oResult).isEmpty()) {
									
									sReturn = sReturn.replaceAll(sCondition, "");
									
								} else {
									
									String sLoopLine = sReturn.replaceAll(String.format("(.*)%s(.*)", sCondition), "$2");
									
									String sSeparator = sLoopLine.replaceAll(String.format("(.*)%s(.*)", TOKEN_SEPARATOR.replace("**", "\\*\\*")), "$2");
									sLoopLine = sLoopLine.replaceAll(TOKEN_SEPARATOR.replace("**", "\\*\\*"), "");
									
									StringBuilder sbLine = new StringBuilder();
									boolean isMore = false;
									
									for (ModelClass theDataObject : (List<ModelClass>) oResult) {
										if (isMore) {
											sbLine.append(sSeparator);
										}
										sbLine.append(fillLine(sLoopLine, theDataObject, theLoopElement, sTokenPrefix, isEditor));
										isMore = true;
									}
									
									sReturn = sReturn.replaceAll(sCondition, sbLine.toString());
								}
								
							}

							// fill conditions for lists manually
							if (sReturn.contains(sTokenPrefix)) {
								sReturn = replaceCondition(TOKEN_IF_EMPTY, sReturn, sTokenPrefix, ((List<ModelClass>) oResult).isEmpty());
								sReturn = replaceCondition(TOKEN_IF_NOTEMPTY, sReturn, sTokenPrefix, !((List<ModelClass>) oResult).isEmpty());
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

							sReturn = fillLineDateTimeData(sReturn, sToken, oResult);
							
						} else {
							
//							if (sToken.startsWith("referee:")) {
//								Constants.logger.debug(sToken);
////								Constants.logger.debug(oResult);
//							}
							
							if (sReturn.contains(sToken)) {
								
								String sValue = "";
								if (oResult != null) {
									if (oResult instanceof Boolean) {
										sValue = ((Boolean) oResult) ? "true" : "";
									} else {
										sValue = oResult.toString();
									}
								}
								
								sReturn = replaceTextAndConditions(sReturn, sToken, sValue);
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
	 * Fills line with datetime data.
	 * 
	 * @param theLine line
	 * @param theData data
	 * @param theLoopElement element that is looped at the moment
	 * @param theTokenPrefix token prefix
	 * 
	 * @return filled line
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	private static String fillLineDateTimeData(final String theLine, final String theToken, final Object theDateTime) {
		
		Objects.requireNonNull(theLine, "line must not be null");
		Objects.requireNonNull(theToken, "token must not be null");
		
		String sReturn = theLine;
		
		// own for loop with replace, for there are more than one datetime tokens allowed in one line
		for (String theType : new String[]{"date", "time", "datetime"}) {
			for (FormatStyle theStyle : FormatStyle.values()) {
				
				String sNewToken = String.format("%s:%s:%s",
						theToken, 
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
											if (theDateTime instanceof LocalDateTime) {
												sReplacement = ((LocalDateTime) theDateTime).format(fmtOutput);
											} else if (theDateTime instanceof LocalDate) {
												sReplacement = ((LocalDate) theDateTime).format(fmtOutput);
											} else if (theDateTime instanceof LocalTime) {
												sReplacement = ((LocalTime) theDateTime).format(fmtOutput);
											}
											
											sReturn = replaceTextAndConditions(sReturn, 
													sNewToken, 
													sReplacement
													);
				}
			}
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

	/**
	 * Replaces replacee-tokens in text with value, considers conditions first.
	 *
	 * @param theText text
	 * @param theReplacee text to be replaced
	 * @param theValue value
	 * 
	 * @return replaced text
	 *
	 * @version 0.6.0
	 * @since 0.5.0
	 */
	private static String replaceTextAndConditions(final String theText, final String theReplacee, final String theValue) {
		String sReturn = theText;
		
		// conditions
		sReturn = replaceCondition(TOKEN_IF_EMPTY, sReturn, theReplacee, theValue.isEmpty());
		sReturn = replaceCondition(TOKEN_IF_NOTEMPTY, sReturn, theReplacee, !theValue.isEmpty());
		
		// replace tokens
		String sToken = String.format(TOKEN_REPLACE, theReplacee);
		while (sReturn.contains(sToken)) {
			sReturn = sReturn.replace(sToken, theValue);
		}
		
		return sReturn;
	}

	/**
	 * Replaces a condition.
	 *
	 * @param theToken token
	 * @param theText text
	 * @param theTokenID token id to be processed
	 * @param isFulfilled is condition fulfilled?
	 * 
	 * @return replaced text
	 *
	 * @version 0.6.0
	 * @since 0.6.0
	 */
	private static String replaceCondition(final String theToken, final String theText, final String theTokenID, final boolean isFulfilled) {
		String sReturn = theText;
		
		String sCondition = String.format(theToken, theTokenID).replace("**", "\\*\\*");
		sReturn = sReturn.replaceAll(sCondition, isFulfilled ? "$1" : "");
		
		// one step up - for empty model classes
		if (theTokenID.contains(":")) {
			sCondition = String.format(theToken, theTokenID.substring(0, theTokenID.lastIndexOf(':'))).replace("**", "\\*\\*");
			sReturn = sReturn.replaceAll(sCondition, isFulfilled ? "$1" : "");
		}
		
		return sReturn;
	}

	/**
	 * Extracts message parts from text.
	 * 
	 * First lines define variables and their content, an empty line starts body.
	 *
	 * @param theText text
	 * 
	 * @return replaced text
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static Map<TemplateVariable, List<String>> extractMessageParts(final List<String> theText) {
		Map<TemplateVariable, List<String>> mapReturn = new HashMap<>();

		for (TemplateVariable theTemplateVariable : TemplateVariable.values()) {
			mapReturn.put(theTemplateVariable, new ArrayList<>());
		}
		
		boolean isBody = false;
		for (String theLine : theText) {
			
			if (isBody) {
				
				mapReturn.computeIfPresent(TemplateVariable.BODY, (templatevar, list) -> {
					list.add(theLine.trim());
					return list;
				});
				
			} else {
				
				for (TemplateVariable theTemplateVariable : TemplateVariable.values()) {
					String sVarToken = String.format(VARIABLE_TOKEN, theTemplateVariable.value());
					if (theLine.trim().startsWith(sVarToken)) {
						mapReturn.computeIfPresent(theTemplateVariable, (templatevar, list) -> {
							list.add(theLine.trim().substring(sVarToken.length()).trim());
							return list;
						});
					}
				}
				
			}
			
			if (theLine.isEmpty()) {
				isBody = true;
			}
		}
		
		if (mapReturn.get(TemplateVariable.DATE).isEmpty()) {
			mapReturn.computeIfPresent(TemplateVariable.DATE, (templatevar, list) -> {
				list.add(LocalDateTime.now().toString());
				return list;
			});
		}

		if (mapReturn.containsKey(TemplateVariable.ATTACHMENT)) {
			mapReturn.computeIfPresent(TemplateVariable.ATTACHMENTS, (templatevar, list) -> {
				StringBuilder sbAttachments = new StringBuilder();
				boolean isFurther = false;
				for (String sAttachment : mapReturn.get(TemplateVariable.ATTACHMENT)) {
					if (isFurther) {
						sbAttachments.append(", ");
					}
					sbAttachments.append(TemplateHelper.extractAttachmentTitle(sAttachment));
					isFurther = true;
				}
				list.add(sbAttachments.toString());
				return list;
			});
		}

		
		return mapReturn;
	}

	/**
	 * Fills message parts with values.
	 * 
	 * @param theMessageParts the message parts
	 * @param theReferee referee
	 * @param theDB whole database for referee-independent data (such as season etc.)
	 * 
	 * @return filled message parts
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static Map<TemplateVariable, List<String>> fillMessageParts(final Map<TemplateVariable, List<String>> theMessageParts, final Referee theReferee, final RefereeManager theDB) {
		Map<TemplateVariable, List<String>> mapReturn = new HashMap<>();

		for (TemplateVariable theTemplateVariable : TemplateVariable.values()) {
			mapReturn.put(theTemplateVariable, fillText(theMessageParts.get(theTemplateVariable), theReferee, theDB));
		}
		
		return mapReturn;
	}

	/**
	 * Fills text.
	 * 
	 * @param theText the text
	 * @param theReferee referee
	 * @param theDB whole database for referee-independent data (such as season etc.)
	 * 
	 * @return filled text
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static List<String> fillText(final List<String> theText, final Referee theReferee, final RefereeManager theDB) {
		List<String> lstReturn = new ArrayList<>();
		
		for (String theLine : theText) {
			lstReturn.add(fillLine(theLine, theDB, theReferee, "", true));
		}
			
		return lstReturn;
	}

	/**
	 * Converts list of strings to string.

	 * @todo shorter with lambdas?
	 * 
	 * @param theText text as list of strings
	 * 
	 * @return text as string
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static String toText(final List<String> theText) {
		StringBuilder sbReturn = new StringBuilder();
		boolean isFurther = false;
		for (String theLine : theText) {
			if (isFurther) {
				sbReturn.append(System.lineSeparator());
			}
			sbReturn.append(theLine);
			isFurther = true;
		}
		return sbReturn.toString();
	}
		
	/**
	 * Fills document template with content.
	 * 
	 * @todo shorter with lambdas?
	 * 
	 * @param theTemplate template text
	 * @param theMessageParts the message parts
	 * 
	 * @return filled template
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static List<String> fillDocumentTemplate(final List<String> theTemplate, final Map<TemplateVariable, List<String>> theMessageParts) {
		List<String> lstReturn = new ArrayList<>();
		
		for (String theLine : theTemplate) {
			
			for (TemplateVariable theTemplateVariable : TemplateVariable.values()) {
				theLine = replaceTextAndConditions(theLine, theTemplateVariable.value(), toText(theMessageParts.get(theTemplateVariable)));
				if (theTemplateVariable == TemplateVariable.DATE) {
					theLine = fillLineDateTimeData(theLine, "", DateTimeUtils.fromString(toText(theMessageParts.get(theTemplateVariable))));
				}
			}
			lstReturn.add(theLine);
			
		}
		
		return lstReturn;
	}
		
	/**
	 * Extracts filename from template attachment.
	 * 
	 * @param theAttachment attachment text
	 * 
	 * @return filename
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static String extractAttachmentFilename(final String theAttachment) {
		Objects.requireNonNull(theAttachment, "attachment must not be null");
		
		String[] arrAttachmentParts = theAttachment.split(Prefs.get(PrefKey.TEMPLATE_VARIABLE_SEPARATOR));

		return (arrAttachmentParts.length == 1) ? arrAttachmentParts[0].trim() : arrAttachmentParts[1].trim();
	}

	/**
	 * Extracts title from template attachment.
	 * 
	 * @param theAttachment attachment text
	 * 
	 * @return title
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static String extractAttachmentTitle(final String theAttachment) {
		Objects.requireNonNull(theAttachment, "attachment must not be null");
		
		String[] arrAttachmentParts = theAttachment.split(Prefs.get(PrefKey.TEMPLATE_VARIABLE_SEPARATOR));
		
		return arrAttachmentParts[0].trim();
	}

}

/* EOF */

package de.edgesoft.refereemanager.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.OtherDate;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.jaxb.Venue;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;

/**
 * Provides methods and properties for IDs.
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
public class IDHelper {

	/**
	 * Fills ID template.
	 *
	 * @param theTemplate template
	 * @param theData data
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public static List<List<String>> fillIDs(final List<String> theSingleTemplate, final List<String> theA4Template, 
			final List<String> theValidTemplate, final List<String> theValidA4Template, 
			final RefereeManager theData, final Path theImagePath) {

		Objects.requireNonNull(theSingleTemplate, "single template must not be null");
		Objects.requireNonNull(theA4Template, "a4 template must not be null");
		Objects.requireNonNull(theValidTemplate, "valid template must not be null");
		Objects.requireNonNull(theValidA4Template, "valid a4 template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		
		List<List<String>> lstReturn = new ArrayList<>();
		
		// loop through referees
		int iRefCount = 0;
		for (final Referee theReferee : theData.getContent().getReferee().stream()
				.sorted(PersonModel.NAME_FIRSTNAME)
				.collect(Collectors.toList())) {
			
			iRefCount++;
			
			List<String> lstSingle = new ArrayList<>();
			for (String theLine : theSingleTemplate) {
				
				String sFilledLine = TemplateHelper.replaceTextAndConditions(theLine, "VSR-filename", 
						Paths.get(theImagePath.toString(), String.format("%s.jpg", theReferee.getFileName())).toAbsolutePath().toString());
				
				sFilledLine = TemplateHelper.replaceTextAndConditions(sFilledLine, "VSR-since", theReferee.getId());
				sFilledLine = TemplateHelper.replaceTextAndConditions(sFilledLine, "VSR-Name", theReferee.getFullName());
				
				lstSingle.add(sFilledLine);
			}
			
			
			
		}

//		lstReturn.add(lstSingle);

		return lstReturn;

	}

	/**
	 * Fills venues.
	 *
	 * @param theTemplate template
	 * @param theData data
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public static List<String> fillVenues(final List<String> theTemplate, final RefereeManager theData) {

		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");

		List<String> lstContent = new ArrayList<>();

		for (Venue venue : ((ContentModel) theData.getContent()).getVenue()) {
			lstContent.add(String.format("%s:", venue.getId()));
			lstContent.add("  -");
			lstContent.add(String.format("    title: \"%s\"", venue.getTitle()));

			lstContent.add(String.format("    street: \"%s %s\"", venue.getStreet(), venue.getNumber()));
			lstContent.add(String.format("    city: \"%s %s\"", venue.getZipCode(), venue.getCity()));

			if ((venue.getRemark() != null) && !venue.getRemark().isEmpty()) {
				lstContent.add("    description: >");
				for (String sRemarkLine : venue.getRemark().split("\n")) {
					lstContent.add(String.format("      %s", sRemarkLine));
				}
			}

			if ((venue.getLatitude() != null)) {
				lstContent.add("    osm:");
				lstContent.add(String.format("      latitude: %s", venue.getLatitude()));
				lstContent.add(String.format("      longitude: %s", venue.getLongitude()));
			}

			lstContent.add("");
		}

		List<String> lstReturn = new ArrayList<>();

		for (String theLine : theTemplate) {
			lstReturn.add(TemplateHelper.replaceTextAndConditions(theLine, "content", TemplateHelper.toText(lstContent)));
		}
		lstReturn = TemplateHelper.fillTemplate(lstReturn, theData, null, 0, ArgumentStatusType.ALL, true);

		return lstReturn;

	}

	/**
	 * Fills events.
	 *
	 * @param theTemplate template
	 * @param theData data
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public static List<String> fillEvents(final List<String> theTemplate, final RefereeManager theData) {

		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");

		List<String> lstContent = new ArrayList<>();

		for (OtherDate event : ((ContentModel) theData.getContent()).getOtherdate()) {
			lstContent.add("-");
			lstContent.add(String.format("  title: \"%s\"", event.getTitle()));

			lstContent.add(String.format("  date: %s", DateTimeUtils.formatAsDate(event.getStart())));
			
			LocalDateTime tmeTemp = event.getStart();
			if ((tmeTemp != null) && (tmeTemp.getHour() != 0)) {
				lstContent.add(String.format("  start: \"%s\"", DateTimeUtils.formatAsTime(tmeTemp)));
			}
			tmeTemp = event.getEnd();
			if ((tmeTemp != null) && (tmeTemp.getHour() != 0)) {
				lstContent.add(String.format("  end: \"%s\"", DateTimeUtils.formatAsTime(tmeTemp)));
			}

			if ((event.getRemark() != null) && !event.getRemark().isEmpty()) {
				lstContent.add(String.format("  description: \"%s\"", event.getRemark()));
			}

			if (event.getVenue() != null) {
				lstContent.add(String.format("  venue: \"%s\"", event.getVenue().getId()));
			}

			lstContent.add("");
		}

		List<String> lstReturn = new ArrayList<>();

		for (String theLine : theTemplate) {
			lstReturn.add(TemplateHelper.replaceTextAndConditions(theLine, "content", TemplateHelper.toText(lstContent)));
		}
		lstReturn = TemplateHelper.fillTemplate(lstReturn, theData, null, 0, ArgumentStatusType.ALL, true);

		return lstReturn;

	}

}

/* EOF */

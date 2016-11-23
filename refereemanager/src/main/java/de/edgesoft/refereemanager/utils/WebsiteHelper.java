package de.edgesoft.refereemanager.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.OtherDate;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.jaxb.Venue;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;

/**
 * Provides methods and properties for websites.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of TT-Schiri: Referee Manager.
 *
 * TT-Schiri: Referee Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TT-Schiri: Referee Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TT-Schiri: Referee Manager. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.10.0
 * @since 0.9.0
 */
public class WebsiteHelper {

	/**
	 * Fills osr recipient template.
	 *
	 * @param theTemplate template
	 * @param theData data
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public static List<String> fillOSRRecipients(final List<String> theTemplate, final RefereeManager theData) {

		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");

		List<String> lstContent = new ArrayList<>();

		for (League league : ((ContentModel) theData.getContent()).getUsedLeagues()) {
			lstContent.add("-");
			lstContent.add(String.format("  title: \"%s\"", league.getTitle()));
			lstContent.add("  recipients:");

			for (Person person : league.getRefereeReportRecipient()) {
				lstContent.add("    -");
				lstContent.add(String.format("      name: \"%s\"", person.getDisplayTitle()));
				lstContent.add(String.format("      mail: \"%s\"", ((PersonModel) person).getPrimaryEMail().getEMail()));
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
	 * Fills venues.
	 *
	 * @param theTemplate template
	 * @param theData data
	 *
	 * @version 0.10.0
	 * @since 0.9.0
	 */
	public static List<String> fillVenues(final List<String> theTemplate, final RefereeManager theData, final boolean useID) {

		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");

		List<String> lstContent = new ArrayList<>();

		for (Venue venue : ((ContentModel) theData.getContent()).getVenue()) {
			if (useID) {
				lstContent.add(String.format("%s:", venue.getId()));
			}
			String sIndent = (useID) ? "  " : "";
			lstContent.add(String.format("%s-", sIndent));
			lstContent.add(String.format("%s  title: \"%s\"", sIndent, venue.getTitle()));

			lstContent.add(String.format("%s  street: \"%s %s\"", sIndent, venue.getStreet(), venue.getNumber()));
			lstContent.add(String.format("%s  city: \"%s %s\"", sIndent, venue.getZipCode(), venue.getCity()));

			if ((venue.getRemark() != null) && !venue.getRemark().get().isEmpty()) {
				lstContent.add(String.format("%s  details: >", sIndent));
				for (String sRemarkLine : venue.getRemark().get().split("\n")) {
					lstContent.add(String.format("%s    %s", sIndent, sRemarkLine));
				}
			}

			if ((venue.getLatitude() != null)) {
				lstContent.add(String.format("%s  osm:", sIndent));
				lstContent.add(String.format("%s    latitude: %s", sIndent, venue.getLatitude()));
				lstContent.add(String.format("%s    longitude: %s", sIndent, venue.getLongitude()));
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

			lstContent.add(String.format("  date: %s", DateTimeUtils.formatAsDate((LocalDateTime) event.getStart().get())));

			LocalDateTime tmeTemp = (LocalDateTime) event.getStart().get();
			if ((tmeTemp != null) && (tmeTemp.getHour() != 0)) {
				lstContent.add(String.format("  start: \"%s\"", DateTimeUtils.formatAsTime(tmeTemp)));
			}
			tmeTemp = (LocalDateTime) event.getEnd().get();
			if ((tmeTemp != null) && (tmeTemp.getHour() != 0)) {
				lstContent.add(String.format("  end: \"%s\"", DateTimeUtils.formatAsTime(tmeTemp)));
			}

			if ((event.getRemark() != null) && !event.getRemark().get().isEmpty()) {
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

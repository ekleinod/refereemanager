package de.edgesoft.refereemanager.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.edgesoft.refereemanager.Prefs;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;

/**
 * Provides methods and properties for communication.
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
public class CommunicationHelper {
	
	/**
	 * Sends mails.
	 * 
	 * @param theText template
	 * @param theData data
	 * @param theRecipient recipient
	 * @param toTrainees send to trainees instead of referees
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void sendMail(final List<String> theText, final RefereeManager theData, final ArgumentCommunicationRecipient theRecipient, final boolean toTrainees) {
		
		Objects.requireNonNull(theText, "text must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		Objects.requireNonNull(theRecipient, "recipient must not be null");
		
		List<Referee> lstRecipients = new ArrayList<>();

		// compute referees to send email to
		final List<? extends Referee> lstAll = (toTrainees) ? ((ContentModel) theData.getContent()).getTrainee() : ((ContentModel) theData.getContent()).getReferee();
		for (final Referee theReferee : lstAll.stream()
				.sorted(PersonModel.NAME_FIRSTNAME)
				.collect(Collectors.toList())) {
			
			switch (theRecipient) {
				case ALL:
				case MAILONLY:
					if (theReferee.getPrimaryEMail() != null) {
						lstRecipients.add(theReferee);
					}
					break;
				case ME:
					if ((theReferee.getPrimaryEMail() != null) && (theReferee.getPrimaryEMail().getEMail().equals(Prefs.get(PrefKey.MY_EMAIL)))) {
						lstRecipients.add(theReferee);
					}
					break;
				case LETTERONLY:
					break;
			}
			
		}
		
		// send email
		for (final Referee theReferee : lstRecipients.stream().collect(Collectors.toList())) {
			String sEmail = theReferee.getPrimaryEMail().getEMail();
			Constants.logger.info(String.format("add '%s <%s>' to bcc.", theReferee.getFullName(), sEmail));
		}
		
	}
	
}

/* EOF */

package de.edgesoft.refereemanager.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.edgesoft.refereemanager.Prefs;
import de.edgesoft.refereemanager.jaxb.EMail;
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
	 * @param theSubject subject
	 * @param theText text
	 * @param theData data
	 * @param theRecipient recipient
	 * @param toTrainees send to trainees instead of referees
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void sendMail(final String theSubject, final List<String> theText, final RefereeManager theData, final ArgumentCommunicationRecipient theRecipient, final boolean toTrainees) {
		
		Objects.requireNonNull(theText, "text must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		Objects.requireNonNull(theRecipient, "recipient must not be null");
		
		List<Address> lstRecipients = new ArrayList<>();

		// compute referees to send email to
		final List<? extends Referee> lstAll = (toTrainees) ? ((ContentModel) theData.getContent()).getTrainee() : ((ContentModel) theData.getContent()).getReferee();
		for (final Referee theReferee : lstAll.stream()
				.sorted(PersonModel.NAME_FIRSTNAME)
				.collect(Collectors.toList())) {
			
			EMail theEMail = theReferee.getPrimaryEMail();
			
			try {
				switch (theRecipient) {
					case ALL:
					case MAILONLY:
						if (theEMail != null) {
							lstRecipients.add(new InternetAddress(theEMail.getEMail(), theReferee.getFullName()));
						}
						break;
					case ME:
						if ((theEMail != null) && (theEMail.getEMail().equals(Prefs.get(PrefKey.MY_EMAIL)))) {
							lstRecipients.add(new InternetAddress(theEMail.getEMail(), theReferee.getFullName()));
						}
						break;
					case LETTERONLY:
						break;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		
		// send email
		Properties mailProps = new Properties();
		mailProps.setProperty("mail.smtp.host", Prefs.get(PrefKey.EMAIL_SMTP_HOST));
		
		Session session = Session.getInstance(mailProps);
		
		Message msgMail = new MimeMessage(session);
		
		try {
			
			msgMail.setFrom(new InternetAddress(Prefs.get(PrefKey.EMAIL_FROM), Prefs.get(PrefKey.EMAIL_FROMNAME)));
			msgMail.setRecipient(RecipientType.TO, new InternetAddress(Prefs.get(PrefKey.EMAIL_TO), Prefs.get(PrefKey.EMAIL_TONAME)));
			
			msgMail.setSubject(theSubject);
			msgMail.setText(toText(theText));
			msgMail.setSentDate(new Date());
			
			msgMail.setRecipients(RecipientType.BCC, lstRecipients.toArray(new Address[]{}));

			Transport.send(msgMail, Prefs.get(PrefKey.EMAIL_SMTP_USERNAME), Prefs.get(PrefKey.EMAIL_SMTP_PASSWORD));
			lstRecipients.stream().forEach(adr -> Constants.logger.info(String.format("sent mail to '%s'.", adr.toString())));
			
		} catch (SendFailedException e) {
			if (e.getInvalidAddresses() != null) {
				Arrays.asList(e.getInvalidAddresses()).forEach(adr -> Constants.logger.info(String.format("invalid address: '%s'.", adr.toString())));
			}
			if (e.getValidUnsentAddresses() != null) {
				Arrays.asList(e.getValidUnsentAddresses()).forEach(adr -> Constants.logger.info(String.format("valid address, but not sent: '%s'.", adr.toString())));
			}
			if (e.getValidSentAddresses() != null) {
				Arrays.asList(e.getValidSentAddresses()).forEach(adr -> Constants.logger.info(String.format("valid address, sent email: '%s'.", adr.toString())));
			}
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Converts list of strings to string.
	 * 
	 * @param theText text
	 * 
	 * @todo shorter with lambdas?
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	private static String toText(final List<String> theText) {
		StringBuilder sbReturn = new StringBuilder();
		for (String theLine : theText) {
			sbReturn.append(theLine);
			sbReturn.append(System.lineSeparator());
		}
		return sbReturn.toString();
	}
		
}

/* EOF */

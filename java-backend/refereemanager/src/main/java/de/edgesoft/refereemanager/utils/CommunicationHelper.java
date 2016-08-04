package de.edgesoft.refereemanager.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
	 * If there are multiple recipients, {@link Transport#send(Message, String, String)} sends
	 * all mails in a transaction, meaning if one fails, all fail.
	 * 
	 * Thus, every mail is sent individually.
	 * Maybe I'm doing something wrong there, in that case the code could be
	 * changes to including all recipients as BCC.
	 * 
	 * @param theSubject subject
	 * @param theText text
	 * @param theData data
	 * @param theRecipient recipient
	 * @param toTrainees send to trainees instead of referees
	 * @param theAttachments file Attachments
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void sendMail(final String theSubject, final List<String> theText, final RefereeManager theData, 
			final ArgumentCommunicationRecipient theRecipient, final boolean toTrainees, final Path... theAttachments) {
		
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
							lstRecipients.add(new InternetAddress(theEMail.getEMail(), theReferee.getFullName(), StandardCharsets.UTF_8.name()));
						}
						break;
					case LETTERONLY:
						break;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}

//		// debug addresses
//		try {
//			lstRecipients.add(new InternetAddress("wrong@ekkart.de", "Wrong"));
//			lstRecipients.add(new InternetAddress("wrong", "Wrong again"));
//			lstRecipients.add(new InternetAddress("ekleinod@edgesoft.de", "Correct"));
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		
		// send email
		Properties mailProps = new Properties();
		mailProps.setProperty("mail.smtp.host", Prefs.get(PrefKey.EMAIL_SMTP_HOST));
		mailProps.setProperty("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(mailProps, new Authenticator() {
		      @Override protected PasswordAuthentication getPasswordAuthentication() {
		          return new PasswordAuthentication(Prefs.get(PrefKey.EMAIL_SMTP_USERNAME), Prefs.get(PrefKey.EMAIL_SMTP_PASSWORD));
		        }
		      });
		
		
		try {
			Message msgMail = new MimeMessage(session);
			
			msgMail.setFrom(new InternetAddress(Prefs.get(PrefKey.EMAIL_FROM), Prefs.get(PrefKey.EMAIL_FROMNAME), StandardCharsets.UTF_8.name()));
			msgMail.setSubject(theSubject);
			msgMail.setSentDate(new Date());

			MimeMultipart msgContent = new MimeMultipart();
			
			MimeBodyPart text = new MimeBodyPart();
			text.setText(toText(theText));
			msgContent.addBodyPart(text);
			
			msgMail.setContent(msgContent);
			
			if (theAttachments != null) {
				for (Path attachment : theAttachments) {
					BodyPart bpAttachment = new MimeBodyPart();
					bpAttachment.setDataHandler(new DataHandler(new FileDataSource(attachment.toFile())));
					bpAttachment.setFileName(attachment.getFileName().toString());
					msgContent.addBodyPart(bpAttachment);
				}
			}
			
			for (Address address : lstRecipients) {
				try {
					msgMail.setRecipient(RecipientType.TO, address);
					Transport.send(msgMail);
					Constants.logger.info(String.format("sent mail to '%s'.", address.toString()));
				} catch (SendFailedException e) {
					if (e.getInvalidAddresses() != null) {
						Arrays.asList(e.getInvalidAddresses()).forEach(adr -> Constants.logger.error(String.format("invalid address, not sent: '%s'.", adr.toString())));
					}
					if (e.getValidUnsentAddresses() != null) {
						Arrays.asList(e.getValidUnsentAddresses()).forEach(adr -> Constants.logger.error(String.format("valid address, but not sent: '%s'.", adr.toString())));
					}
					if (e.getValidSentAddresses() != null) {
						Arrays.asList(e.getValidSentAddresses()).forEach(adr -> Constants.logger.error(String.format("valid address, sent email: '%s'.", adr.toString())));
					}
				}
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

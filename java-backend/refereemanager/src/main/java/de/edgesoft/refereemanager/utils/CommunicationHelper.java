package de.edgesoft.refereemanager.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.refereemanager.Prefs;
import de.edgesoft.refereemanager.jaxb.Address;
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
	 * @param theText text
	 * @param theTemplate template text
	 * @param theData data
	 * @param theRecipient recipient
	 * @param toTrainees send to trainees instead of referees
	 * @param isTest is test?
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void sendMail(final List<String> theText, final List<String> theTemplate, final RefereeManager theData, 
			final ArgumentCommunicationRecipient theRecipient, final boolean toTrainees, final boolean isTest) {
		
		Objects.requireNonNull(theText, "text must not be null");
		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		Objects.requireNonNull(theRecipient, "recipient must not be null");
		
		if (isTest) {
			Constants.logger.info("Testmode: no mails are sent!.");
		}
		Constants.logger.info(String.format("Sending mails to '%s', trainees: %s.", theRecipient.value(), toTrainees));
		
		// compute referees to send email to
		final List<? extends Referee> lstAll = (toTrainees) ? ((ContentModel) theData.getContent()).getTrainee() : ((ContentModel) theData.getContent()).getReferee();
		List<Referee> lstRecipients = new ArrayList<>();
		for (final Referee theReferee : lstAll.stream()
				.sorted(PersonModel.NAME_FIRSTNAME)
				.collect(Collectors.toList())) {
			
			EMail theEMail = theReferee.getPrimaryEMail();
			
			switch (theRecipient) {
				case ALL:
				case MAILONLY:
					if (theEMail != null) {
						lstRecipients.add(theReferee);
					}
					break;
				case ME:
					if (theReferee.getDisplayTitle().equals(Prefs.get(PrefKey.MY_NAME))) {
						lstRecipients.add(theReferee);
					}
					break;
				case LETTERONLY:
					break;
			}
			
		}

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
			Map<TemplateVariable, List<String>> mapContent = TemplateHelper.extractMessageParts(theText);

			LocalTime tmeStart = LocalTime.now();
			
			for (Referee theReferee : lstRecipients) {
				
				Map<TemplateVariable, List<String>> mapFilledContent = TemplateHelper.fillMessageParts(mapContent, theReferee, theData);

				Message msgMail = new MimeMessage(session);
				
				msgMail.setFrom(new InternetAddress(Prefs.get(PrefKey.EMAIL_FROM), Prefs.get(PrefKey.EMAIL_FROMNAME), StandardCharsets.UTF_8.name()));
				
				msgMail.setSentDate(DateTimeUtils.toDate(DateTimeUtils.fromString(mapFilledContent.get(TemplateVariable.DATE).get(0))));
				msgMail.setSubject(mapFilledContent.get(TemplateVariable.SUBJECT).get(0));

				Constants.logger.debug(String.format("Setting sent date '%s'.", msgMail.getSentDate()));
				Constants.logger.debug(String.format("Setting subject '%s'.", msgMail.getSubject()));
				
				try {
					
					EMail theEMail = theReferee.getPrimaryEMail();
					msgMail.setRecipient(RecipientType.TO, new InternetAddress(theEMail.getEMail(), theReferee.getFullName(), StandardCharsets.UTF_8.name()));
					
					MimeMultipart msgContent = new MimeMultipart();
					
					MimeBodyPart text = new MimeBodyPart();
					String sText = TemplateHelper.toText(TemplateHelper.fillText(TemplateHelper.fillDocumentTemplate(theTemplate, mapFilledContent), theReferee, theData));
					text.setText(sText);
					msgContent.addBodyPart(text);

					Constants.logger.debug(String.format("Adding body '%s'.", sText));
					
					msgMail.setContent(msgContent);
					
					if (mapFilledContent.get(TemplateVariable.ATTACHMENT) != null) {
						for (String sAttachment : mapFilledContent.get(TemplateVariable.ATTACHMENT)) {
							Path attachment = Paths.get(TemplateHelper.extractAttachmentFilename(sAttachment));
							
							BodyPart bpAttachment = new MimeBodyPart();
							bpAttachment.setDataHandler(new DataHandler(new FileDataSource(attachment.toFile())));
							bpAttachment.setFileName(attachment.getFileName().toString());
							
							msgContent.addBodyPart(bpAttachment);
							
							Constants.logger.info(String.format("Adding attachment '%s'.", bpAttachment.getFileName()));
						}
					}
					
					if (!isTest) {
						Transport.send(msgMail);
					}
					
					Arrays.asList(msgMail.getRecipients(RecipientType.TO))
							.stream()
							.forEach(adr -> Constants.logger.info(String.format("sent mail to '%s'.", adr.toString())));
					
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
			
			Duration sendingTime = Duration.between(tmeStart, LocalTime.now());
			Constants.logger.info(String.format("Sending time: %s.", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.ofSecondOfDay(sendingTime.getSeconds()))));
			
			
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Creates letters.
	 * 
	 * @param theText text
	 * @param theTemplate template text
	 * @param theData data
	 * @param theRecipient recipient
	 * @param toTrainees send to trainees instead of referees
	 * @param isTest is test?
	 * @param theOutputPath output path
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void createLetters(final List<String> theText, final List<String> theTemplate, final RefereeManager theData, 
			final ArgumentCommunicationRecipient theRecipient, final boolean toTrainees, final boolean isTest, final String theOutputPath) {
		
		Objects.requireNonNull(theText, "text must not be null");
		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		Objects.requireNonNull(theRecipient, "recipient must not be null");
		
		if (isTest) {
			Constants.logger.info("Testmode: no letters are stored!.");
		}
		Constants.logger.info(String.format("Writing letters to '%s', trainees: %s.", theRecipient.value(), toTrainees));
		
		// compute referees to send letters to
		final List<? extends Referee> lstAll = (toTrainees) ? ((ContentModel) theData.getContent()).getTrainee() : ((ContentModel) theData.getContent()).getReferee();
		List<Referee> lstRecipients = new ArrayList<>();
		for (final Referee theReferee : lstAll.stream()
				.sorted(PersonModel.NAME_FIRSTNAME)
				.collect(Collectors.toList())) {
			
			Address theAddress = theReferee.getPrimaryAddress();
			
			switch (theRecipient) {
				case ALL:
					if (theAddress != null) {
						lstRecipients.add(theReferee);
					}
					break;
				case LETTERONLY:
					if ((theAddress != null) && (theReferee.isDocsByLetter() || theReferee.getEMail().isEmpty())) {
						lstRecipients.add(theReferee);
					}
					break;
				case ME:
					if (theReferee.getFullName().equals(Prefs.get(PrefKey.MY_NAME))) {
						lstRecipients.add(theReferee);
					}
					break;
				case MAILONLY:
					break;
			}
			
		}

		// create letter
		Map<TemplateVariable, List<String>> mapContent = TemplateHelper.extractMessageParts(theText);

		LocalTime tmeStart = LocalTime.now();
		
		for (Referee theReferee : lstRecipients) {
			
			Map<TemplateVariable, List<String>> mapFilledContent = TemplateHelper.fillMessageParts(mapContent, theReferee, theData);
			
			try {

				// body is filled twice with this construct, but some variables are needed as is
				List<String> lstText = TemplateHelper.fillText(TemplateHelper.fillDocumentTemplate(theTemplate, mapFilledContent), theReferee, theData);
				
				Path pathOut = Paths.get(theOutputPath, 
						((mapFilledContent.get(TemplateVariable.FILENAME) == null) || mapFilledContent.get(TemplateVariable.FILENAME).isEmpty()) ?
								String.format(Prefs.get(PrefKey.FILENAME_PATTERN_REFEREE_DATA), theReferee.getFileName()) :
								mapFilledContent.get(TemplateVariable.FILENAME).get(0));
								
				if (!isTest) {
					FileAccess.writeFile(pathOut, lstText);
				}
				Constants.logger.info(String.format("writing letter to '%s'.", pathOut.toString()));
				
			} catch (IOException e) {
				Constants.logger.error(String.format("error while writing: '%s'.", e.getMessage()));
			}
		}
		
		Duration sendingTime = Duration.between(tmeStart, LocalTime.now());
		Constants.logger.info(String.format("Writing time: %s.", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.ofSecondOfDay(sendingTime.getSeconds()))));
		
	}
	
}

/* EOF */

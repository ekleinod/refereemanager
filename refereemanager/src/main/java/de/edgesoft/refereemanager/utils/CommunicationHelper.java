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
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.jaxb.Address;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.template.DocumentData;

/**
 * Provides methods and properties for communication.
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
	 * @param theFromRecipient start processing from which recipient (including this)
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void sendMail(final List<String> theText, final List<String> theTemplate, final de.edgesoft.refereemanager.jaxb.RefereeManager theData,
			final ArgumentCommunicationRecipient theRecipient, final boolean toTrainees, final boolean isTest, final String theFromRecipient) {

		Objects.requireNonNull(theText, "text must not be null");
		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		Objects.requireNonNull(theRecipient, "recipient must not be null");

		if (isTest) {
			RefereeManager.logger.info("Testmode: no mails are sent!.");
		}
		RefereeManager.logger.info(String.format("Sending mails to '%s', trainees: %s.", theRecipient.value(), toTrainees));

		// compute referees to send email to
		final List<? extends Referee> lstAll = (toTrainees) ? ((ContentModel) theData.getContent()).getTrainee() : ((ContentModel) theData.getContent()).getReferee();
		List<Referee> lstRecipients = new ArrayList<>();
		boolean processReferees = (theFromRecipient == null) ? true : false;
		for (final Referee theReferee : lstAll.stream()
				.sorted(PersonModel.NAME_FIRSTNAME)
				.collect(Collectors.toList())) {

			if ((theFromRecipient != null) && (theFromRecipient.equals(theReferee.getDisplayTitle()))) {
				processReferees = true;
			}

			if (processReferees) {

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

		}

		// send email
		Properties mailProps = new Properties();
		mailProps.setProperty("mail.smtp.host", Prefs.get(PrefKey.COMMUNICATION_SMTP_HOST));
		mailProps.setProperty("mail.smtp.auth", "true");

		Session session = Session.getInstance(mailProps, new Authenticator() {
					@Override protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(Prefs.get(PrefKey.COMMUNICATION_SMTP_USERNAME), Prefs.get(PrefKey.COMMUNICATION_SMTP_PASSWORD));
						}
					});

		try {

			LocalTime tmeStart = LocalTime.now();

			for (Referee theReferee : lstRecipients) {

				Message msgMail = new MimeMessage(session);
				msgMail.setFrom(new InternetAddress(Prefs.get(PrefKey.COMMUNICATION_FROM_EMAIL), Prefs.get(PrefKey.COMMUNICATION_FROM_NAME), StandardCharsets.UTF_8.name()));

				DocumentData docData = TemplateHelper.extractMessageParts(TemplateHelper.fillText(theText, theReferee, theData));

				msgMail.setSentDate(DateTimeUtils.toDate(docData.getDate()));
				msgMail.setSubject(docData.getSubject());

				try {

					EMail theEMail = theReferee.getPrimaryEMail();
					msgMail.setRecipient(RecipientType.TO, new InternetAddress(theEMail.getEMail().get(), theReferee.getFullName().get(), StandardCharsets.UTF_8.name()));

					MimeMultipart msgContent = new MimeMultipart();

					MimeBodyPart text = new MimeBodyPart();
					String sText = TemplateHelper.toText(TemplateHelper.fillText(theTemplate, docData, docData));
					text.setText(sText);
					msgContent.addBodyPart(text);

					msgMail.setContent(msgContent);

					for (Attachment theAttachment : docData.getAttachment()) {
						Path attachment = Paths.get(theAttachment.getFilename());

						BodyPart bpAttachment = new MimeBodyPart();
						bpAttachment.setDataHandler(new DataHandler(new FileDataSource(attachment.toFile())));
						bpAttachment.setFileName(attachment.getFileName().toString());

						msgContent.addBodyPart(bpAttachment);

						RefereeManager.logger.info(String.format("Adding attachment '%s'.", bpAttachment.getFileName()));
					}

					if (!isTest) {
						Transport.send(msgMail);
					}

					Arrays.asList(msgMail.getRecipients(RecipientType.TO))
							.stream()
							.forEach(adr -> RefereeManager.logger.info(String.format("sent mail to '%s'.", adr.toString())));

				} catch (SendFailedException e) {
					if (e.getInvalidAddresses() != null) {
						Arrays.asList(e.getInvalidAddresses()).forEach(adr -> RefereeManager.logger.error(String.format("invalid address, not sent: '%s'.", adr.toString())));
					}
					if (e.getValidUnsentAddresses() != null) {
						Arrays.asList(e.getValidUnsentAddresses()).forEach(adr -> RefereeManager.logger.error(String.format("valid address, but not sent: '%s'.", adr.toString())));
					}
					if (e.getValidSentAddresses() != null) {
						Arrays.asList(e.getValidSentAddresses()).forEach(adr -> RefereeManager.logger.error(String.format("valid address, sent email: '%s'.", adr.toString())));
					}
				}
			}

			Duration sendingTime = Duration.between(tmeStart, LocalTime.now());
			RefereeManager.logger.info(String.format("Sending time: %s.", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.ofSecondOfDay(sendingTime.getSeconds()))));


		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Creates letters.
	 *
	 * @param theText text
	 * @param theTemplate template text
	 * @param theAction action
	 * @param theData data
	 * @param theRecipient recipient
	 * @param toTrainees send to trainees instead of referees
	 * @param isTest is test?
	 * @param theOutputPath output path
	 * @param theFromRecipient start processing from which recipient (including this)
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void createLetters(final List<String> theText, final List<String> theTemplate, final de.edgesoft.refereemanager.jaxb.RefereeManager theData,
			final ArgumentCommunicationAction theAction, final ArgumentCommunicationRecipient theRecipient,
			final boolean toTrainees, final boolean isTest, final String theOutputPath, final String theFromRecipient) {

		Objects.requireNonNull(theText, "text must not be null");
		Objects.requireNonNull(theTemplate, "template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		Objects.requireNonNull(theRecipient, "recipient must not be null");

		if (isTest) {
			RefereeManager.logger.info("Testmode: no letters are stored!.");
		}
		RefereeManager.logger.info(String.format("Writing letters to '%s', trainees: %s.", theRecipient.value(), toTrainees));

		// merge files
		List<String> lstMergeRef = null;
		List<String> lstMergeRefs = null;
		de.edgesoft.refereemanager.jaxb.RefereeManager mgrRefs = null;
		if (theAction == ArgumentCommunicationAction.LETTER) {

			Path pathTemp = Paths.get(Prefs.get(PrefKey.PATH_TEMPLATES), Prefs.get(PrefKey.COMMUNICATION_TEMPLATE_MERGE_SINGLE));
			RefereeManager.logger.debug(String.format("read merge template from '%s'.", pathTemp.toString()));
			try {
				lstMergeRef = FileAccess.readFileInList(pathTemp);
			} catch (Exception e) {
				RefereeManager.logger.error(e);
				e.printStackTrace();
			}

			pathTemp = Paths.get(Prefs.get(PrefKey.PATH_TEMPLATES), Prefs.get(PrefKey.COMMUNICATION_TEMPLATE_MERGE_ALL));
			RefereeManager.logger.debug(String.format("read merge template from '%s'.", pathTemp.toString()));
			try {
				lstMergeRefs = FileAccess.readFileInList(pathTemp);
			} catch (Exception e) {
				RefereeManager.logger.error(e);
				e.printStackTrace();
			}

			mgrRefs = new de.edgesoft.refereemanager.jaxb.RefereeManager();
			mgrRefs.setContent(new ContentModel());
		}


		// compute referees to send letters to
		final List<? extends Referee> lstAll = (toTrainees) ? ((ContentModel) theData.getContent()).getTrainee() : ((ContentModel) theData.getContent()).getReferee();
		List<Referee> lstRecipients = new ArrayList<>();
		boolean processReferees = (theFromRecipient == null) ? true : false;
		for (final Referee theReferee : lstAll.stream()
				.sorted(PersonModel.NAME_FIRSTNAME)
				.collect(Collectors.toList())) {

			if ((theFromRecipient != null) && (theFromRecipient.equals(theReferee.getDisplayTitle()))) {
				processReferees = true;
			}

			if (processReferees) {

				Address theAddress = theReferee.getPrimaryAddress();

				switch (theRecipient) {
					case ALL:
						if ((theAction == ArgumentCommunicationAction.DOCUMENT) || (theAddress != null)) {
							lstRecipients.add(theReferee);
						}
						break;
					case LETTERONLY:
						if ((theAddress != null) && theReferee.getDocsByLetter().get()) {
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

		}

		// create letter
		LocalTime tmeStart = LocalTime.now();

		for (Referee theReferee : lstRecipients) {

			try {

				DocumentData docData = TemplateHelper.extractMessageParts(TemplateHelper.fillText(theText, theReferee, theData));

				List<String> lstText = TemplateHelper.fillText(theTemplate, docData, docData);
				lstText = TemplateHelper.fillText(lstText, theReferee, theData);

				Path pathOut = Paths.get(theOutputPath,
						((docData.getFilename() == null) || docData.getFilename().isEmpty()) ?
								String.format(Prefs.get(PrefKey.FILENAME_PATTERN_REFEREE_DATA), theReferee.getFileName()) :
									docData.getFilename());

				if (!isTest) {
					FileAccess.writeFile(pathOut, lstText);
				}
				RefereeManager.logger.info(String.format("writing letter to '%s'.", pathOut.toString()));

				if (theAction == ArgumentCommunicationAction.LETTER) {

					List<String> lstMergeFilled = TemplateHelper.fillText(lstMergeRef, docData, docData);
					lstMergeFilled = TemplateHelper.fillText(lstMergeFilled, theReferee, theData);
					pathOut = Paths.get(theOutputPath, String.format(Prefs.get(PrefKey.FILENAME_PATTERN_REFEREE_MERGE), theReferee.getFileName()));

					if (!isTest) {
						FileAccess.writeFile(pathOut, lstMergeFilled);
					}
					RefereeManager.logger.info(String.format("writing merge to '%s'.", pathOut.toString()));

					mgrRefs.getContent().getReferee().add(theReferee);

				}

			} catch (IOException e) {
				RefereeManager.logger.error(String.format("error while writing: '%s'.", e.getMessage()));
			}
		}

		if ((theAction == ArgumentCommunicationAction.LETTER) && !lstRecipients.isEmpty()) {

			try {

				List<String> lstMergeFilled = TemplateHelper.fillTemplate(lstMergeRefs, mgrRefs, null, 0, ArgumentStatusType.ALL, true);
				Path pathOut = Paths.get(theOutputPath, Prefs.get(PrefKey.FILENAME_PATTERN_REFEREES_MERGE));

				if (!isTest) {
					FileAccess.writeFile(pathOut, lstMergeFilled);
				}
				RefereeManager.logger.info(String.format("writing merge to '%s'.", pathOut.toString()));

			} catch (IOException e) {
				RefereeManager.logger.error(String.format("error while writing: '%s'.", e.getMessage()));
			}

		}

		Duration sendingTime = Duration.between(tmeStart, LocalTime.now());
		RefereeManager.logger.info(String.format("Writing time: %s.", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.ofSecondOfDay(sendingTime.getSeconds()))));

	}

}

/* EOF */

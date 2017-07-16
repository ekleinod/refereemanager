package de.edgesoft.refereemanager.utils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeMultipart;

/**
 * Utility methods for mails.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2017 Ekkart Kleinod <ekleinod@edgesoft.de>
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
 * @version 0.14.0
 * @since 0.12.0
 */
public class MailUtils {

	/**
	 * Convert mail to string.
	 *
	 * @param theMail mail
	 * @return string representation of mail
	 *
	 * @version 0.14.0
	 * @since 0.12.0
	 */
	public static String toString(final Message theMail) {
		StringBuilder sbReturn = new StringBuilder();

		sbReturn.append("-------------------\n");

		try {

			RecipientType theType = RecipientType.TO;
			if (theMail.getRecipients(theType) != null) {
				sbReturn.append(theType.toString());
				sbReturn.append("\n");
				for (Address theAddress : theMail.getRecipients(theType)) {
					sbReturn.append("\t");
					sbReturn.append(theAddress.toString());
					sbReturn.append("\n");
				}
			}

			theType = RecipientType.CC;
			if (theMail.getRecipients(theType) != null) {
				sbReturn.append(theType.toString());
				sbReturn.append("\n");
				for (Address theAddress : theMail.getRecipients(theType)) {
					sbReturn.append("\t");
					sbReturn.append(theAddress.toString());
					sbReturn.append("\n");
				}
			}

			theType = RecipientType.BCC;
			if (theMail.getRecipients(theType) != null) {
				sbReturn.append(theType.toString());
				sbReturn.append("\n");
				for (Address theAddress : theMail.getRecipients(theType)) {
					sbReturn.append("\t");
					sbReturn.append(theAddress.toString());
					sbReturn.append("\n");
				}
			}

			sbReturn.append("Subject: ");
			sbReturn.append(theMail.getSubject());
			sbReturn.append("\n");

			for (int i = 0; i < ((MimeMultipart) theMail.getContent()).getCount(); i++) {
				if (((MimeMultipart) theMail.getContent()).getBodyPart(i).getFileName() == null) {
					sbReturn.append("Body\n");
					sbReturn.append(((MimeMultipart) theMail.getContent()).getBodyPart(i).getContent());
					sbReturn.append("\n");
				} else {
					sbReturn.append("Attachment: ");
					sbReturn.append(((MimeMultipart) theMail.getContent()).getBodyPart(i).getFileName());
					sbReturn.append("\n");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		sbReturn.append("-------------------\n");

		return sbReturn.toString();
	}

}

/* EOF */

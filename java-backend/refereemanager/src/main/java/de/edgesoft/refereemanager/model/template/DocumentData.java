
package de.edgesoft.refereemanager.model.template;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.edgesoft.edgeutils.commons.ModelClass;
import de.edgesoft.refereemanager.model.RefereeModel;


/**
 * DocumentData, model class for document data, i.e. template variables.
 *
 * This class slightly breaks the model-view-controller concept,
 * as it contains view data.
 *
 * This eases the output of the data using the template language
 * without copy/paste of template resolving parts.
 *
 * Thus, the content is stored and processed here and used within the
 * {@link RefereeModel}.
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
public class DocumentData extends ModelClass {

    private String subject;
    private String subtitle;
    private String signature;
    private String opening;
    private String filename;
    private List<String> body;
    private LocalDate date;
    private List<Attachment> attachment;

    /**
     * Gets the value of the property.
     *
     * @return property value
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the property.
     *
     * @param value
     */
    public void setSubject(String value) {
        subject = value;
    }

    /**
     * Gets the value of the property.
     *
     * @return property value
     */
	public String getSubtitle() {
		return subtitle;
	}

    /**
     * Sets the value of the property.
     *
     * @param value
     */
	public void setSubtitle(String value) {
		this.subtitle = value;
	}

    /**
     * Gets the value of the property.
     *
     * @return property value
     */
	public String getSignature() {
		return signature;
	}

    /**
     * Sets the value of the property.
     *
     * @param value
     */
	public void setSignature(String value) {
		this.signature = value;
	}

    /**
     * Gets the value of the property.
     *
     * @return property value
     */
	public String getOpening() {
		return opening;
	}

    /**
     * Sets the value of the property.
     *
     * @param value
     */
	public void setOpening(String value) {
		this.opening = value;
	}

    /**
     * Gets the value of the property.
     *
     * @return property value
     */
	public String getFilename() {
		return filename;
	}

    /**
     * Sets the value of the property.
     *
     * @param value
     */
	public void setFilename(String value) {
		this.filename = value;
	}

    /**
     * Gets the value of the property.
     *
     * @return property value
     */
	public List<String> getBody() {
		return body;
	}

    /**
     * Sets the value of the property.
     *
     * @param value
     */
	public void setBody(List<String> value) {
		this.body = value;
	}

    /**
     * Gets the value of the property.
     *
     * @return property value
     */
	public LocalDate getDate() {
		return date;
	}

    /**
     * Sets the value of the property.
     *
     * @param value
     */
	public void setDate(LocalDate value) {
		this.date = value;
	}

    /**
     * Gets the value of the property.
     *
     * @return property value
     */
	public List<Attachment> getAttachment() {
        if (attachment == null) {
        	attachment = new ArrayList<>();
        }
		return attachment;
	}

}

/* EOF */

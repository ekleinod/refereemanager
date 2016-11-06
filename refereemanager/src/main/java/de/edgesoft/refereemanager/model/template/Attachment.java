
package de.edgesoft.refereemanager.model.template;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;


/**
 * Attachment class.
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
 * @version 0.10.0
 * @since 0.8.0
 */
public class Attachment {

    /**
     * Title property.
     *
     * @return property value
     *
	 * @version 0.10.0
	 * @since 0.8.0
     */
    private StringProperty title;

    /**
     * Filename property.
     *
     * @return property value
     *
	 * @version 0.10.0
	 * @since 0.8.0
     */
    private StringProperty filename;

    /**
     * Landscape property.
     *
     * @return property value
     *
	 * @version 0.10.0
	 * @since 0.8.0
     */
    private BooleanProperty landscape;

    /**
     * Gets the value of the property.
     *
     * @return property value
     *
	 * @version 0.10.0
	 * @since 0.8.0
     */
    public StringProperty getTitle() {
        return title;
    }

    /**
     * Sets the value of the property.
     *
     * @param value
     *
	 * @version 0.10.0
	 * @since 0.8.0
     */
    public void setTitle(StringProperty value) {
        title = value;
    }

    /**
     * Gets the value of the property.
     *
     * @return property value
     *
	 * @version 0.10.0
	 * @since 0.8.0
     */
	public StringProperty getFilename() {
		return filename;
	}

    /**
     * Sets the value of the property.
     *
     * @param value
     *
	 * @version 0.10.0
	 * @since 0.8.0
     */
	public void setFilename(StringProperty value) {
		filename = value;
	}

    /**
     * Gets the value of the property.
     *
     * @return property value
     *
	 * @version 0.10.0
	 * @since 0.8.0
     */
	public BooleanProperty getLandscape() {
		return landscape;
	}

    /**
     * Sets the value of the property.
     *
     * @param value
     *
	 * @version 0.10.0
	 * @since 0.8.0
     */
	public void setLandscape(BooleanProperty value) {
		landscape = value;
	}

}

/* EOF */

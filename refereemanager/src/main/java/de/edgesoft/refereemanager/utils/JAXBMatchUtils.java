package de.edgesoft.refereemanager.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement.DEFAULT;

import de.edgesoft.refereemanager.model.PersonModel;
import javafx.scene.control.Control;


/**
 * Annotation for matching FXML fields to JAXB fields.
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
 * @version 0.13.0
 * @since 0.13.0
 */
public class JAXBMatchUtils {

	/**
	 * Sets referee to be edited.
	 *
	 * @param thePerson referee
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public static boolean isMatch(final Field theFXMLField, final Field theJAXBField) {

		Objects.requireNonNull(theFXMLField);
		Objects.requireNonNull(theJAXBField);

		if (theFXMLField.getAnnotation(JAXBMatch.class) == null) {
			return false;
		}

		return (theFXMLField.getAnnotation(JAXBMatch.class).jaxbclass() == theJAXBField.getDeclaringClass()) &&
				theFXMLField.getAnnotation(JAXBMatch.class).jaxbfield().equals(theJAXBField.getName());

	}

}

/* EOF */

package de.edgesoft.refereemanager.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.ModelClass;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;


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
	 * Fill fields of fxml class with data of data classes?
	 *
	 * @param theFXMLObject calling fxml object
	 * @param theModel data model object
	 * @param theDataClasses data classes
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public static void fillFields(final Object theFXMLObject, final ModelClass theModel, final Class<?>... theDataClasses) {

		Objects.requireNonNull(theFXMLObject);
		Objects.requireNonNull(theModel);

        for (Field theFXMLField : theFXMLObject.getClass().getDeclaredFields()) {

        	for (Class<?> theClass : theDataClasses) {

        		for (Field theJAXBField : theClass.getDeclaredFields()) {

        			if (JAXBMatchUtils.isMatch(theFXMLField, theJAXBField)) {

        				try {
        					if (theFXMLField.get(theFXMLObject) instanceof TextField) {
        						StringProperty sTemp = (StringProperty) theClass
        								.getDeclaredMethod(String.format("get%s%s", theJAXBField.getName().substring(0, 1).toUpperCase(), theJAXBField.getName().substring(1)))
        								.invoke(theModel);
        						((TextField) theFXMLField.get(theFXMLObject)).setText((sTemp == null) ? null : sTemp.getValue());
        					}
        				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
        					e.printStackTrace();
        				}

        			}

        		}

			}

        }

	}

	/**
	 * Does fxml field match jaxb field?
	 *
	 * @param theFXMLField fxml field
	 * @param theJAXBField jaxb field
	 *
	 * @return does fxml field match jaxb field?
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

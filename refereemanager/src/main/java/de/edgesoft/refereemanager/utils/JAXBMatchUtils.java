package de.edgesoft.refereemanager.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

import de.edgesoft.edgeutils.commons.ModelClass;
import de.edgesoft.refereemanager.controller.RefereeEditDialogController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


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
	 * Fill field with data of data classes.
	 *
	 * The for-loop for all fields cannot be called here, because the access
	 * to the field object is forbidden for private fields.
	 * In order to maintain separation of concerns I call the
	 * loop and the get method in the calling class.
	 * {@link RefereeEditDialogController#setPerson(de.edgesoft.refereemanager.model.PersonModel)}
	 *
	 * @param theFXMLField fxml field
	 * @param theFieldObject object of fxml field
	 * @param theModel data model object
	 * @param theDataClasses data classes
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public static void fillField(final Field theFXMLField, final Object theFieldObject, final ModelClass theModel, final Class<?>... theDataClasses) {

		Objects.requireNonNull(theFXMLField);
		Objects.requireNonNull(theFieldObject);
		Objects.requireNonNull(theModel);

    	for (Class<?> theClass : theDataClasses) {

    		for (Field theJAXBField : theClass.getDeclaredFields()) {

    			if (JAXBMatchUtils.isMatch(theFXMLField, theJAXBField)) {

    				try {
    					if (theFieldObject instanceof TextField) {

    						StringProperty sTemp = (StringProperty) theClass
    								.getDeclaredMethod(getGetter(theJAXBField.getName()))
    								.invoke(theModel);
    						((TextField) theFieldObject).setText((sTemp == null) ? null : sTemp.getValue());

    					} else if (theFieldObject instanceof DatePicker) {

    						SimpleObjectProperty<LocalDate> sTemp = (SimpleObjectProperty<LocalDate>) theClass
    								.getDeclaredMethod(getGetter(theJAXBField.getName()))
    								.invoke(theModel);
    						((DatePicker) theFieldObject).setValue((sTemp == null) ? null : sTemp.getValue());

    					}

    				} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
    					e.printStackTrace();
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

	/**
	 * Getter for the field.
	 *
	 * @todo Is there a built-in Java method (jaxb) for this?
	 *
	 * @param theJAXBFieldName jaxb field
	 *
	 * @return getter name
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public static String getGetter(final String theJAXBFieldName) {

		Objects.requireNonNull(theJAXBFieldName);

		return String.format("get%s", getSuffix(theJAXBFieldName));

	}

	/**
	 * Setter for the field.
	 *
	 * @todo Is there a built-in Java method (jaxb) for this?
	 *
	 * @param theJAXBFieldName jaxb field
	 *
	 * @return setter name
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public static String getSetter(final String theJAXBFieldName) {

		Objects.requireNonNull(theJAXBFieldName);

		return String.format("set%s", getSuffix(theJAXBFieldName));

	}

	/**
	 * Access suffix for the field.
	 *
	 * @todo Is there a built-in Java method (jaxb) for this?
	 *
	 * @param theJAXBFieldName jaxb field
	 *
	 * @return access suffix
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public static String getSuffix(final String theJAXBFieldName) {

		Objects.requireNonNull(theJAXBFieldName);

		return String.format("%s%s", theJAXBFieldName.substring(0, 1).toUpperCase(), theJAXBFieldName.substring(1));

	}

	/**
	 * Mark required fields.
	 *
	 * @param theFXMLField fxml field
	 * @param theFieldObject object of fxml field
	 * @param theDataClasses data classes
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public static void markRequired(final Field theFXMLField, final Object theFieldObject, final Class<?>... theDataClasses) {

		Objects.requireNonNull(theFXMLField);

    	for (Class<?> theClass : theDataClasses) {

    		for (Field theJAXBField : theClass.getDeclaredFields()) {

    			if ((theJAXBField.getAnnotation(XmlElement.class) != null) && theJAXBField.getAnnotation(XmlElement.class).required()) {

	    			if (JAXBMatchUtils.isMatch(theFXMLField, theJAXBField)) {

	    				try {
	    					if (theFieldObject instanceof Label) {
								Font fntTemp = ((Label) theFieldObject).getFont();
								((Label) theFieldObject).setFont(Font.font(fntTemp.getFamily(), FontWeight.BOLD, fntTemp.getSize()));
	    					}
	    				} catch (IllegalArgumentException | SecurityException e) {
	    					e.printStackTrace();
	    				}

	    			}

    			}

    		}

		}

	}

}

/* EOF */

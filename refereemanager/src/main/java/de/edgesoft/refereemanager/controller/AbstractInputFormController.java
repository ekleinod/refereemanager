package de.edgesoft.refereemanager.controller;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import de.edgesoft.edgeutils.ClassUtils;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.utils.JAXBMatchUtils;

/**
 * Abstract controller for edit dialog scenes, input part.
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
 * @version 0.15.0
 * @since 0.15.0
 */
public abstract class AbstractInputFormController implements IInputFormController {

	/**
	 * Declared fields of class and abstract subclasses.
	 */
	private List<Field> lstDeclaredFields = null;

	/**
	 * Classes for introspection when setting/getting values.
	 */
	private List<Class<?>> lstClasses = null;


	/**
	 * Initializes form: sets introspection classes and marks required fields.
	 *
	 * @param theClasses list of introspection classes
	 */
	@Override
	public void initForm(final List<Class<?>> theClasses) {

		lstClasses = theClasses;

		// required fields
		for (Field theFXMLField : getDeclaredFields()) {

			try {
				Object fieldObject = theFXMLField.get(this);

				JAXBMatchUtils.markRequired(theFXMLField, fieldObject, getClasses());

			} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Fills form with data to be edited.
	 *
	 * @param theData data object
	 */
	@Override
	public <U extends ModelClassExt> void fillForm(final U theData) {

		assert (theData != null) : "data must not be null";

        // fill fields
        for (Field theFXMLField : getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.setField(theFXMLField, fieldObject, theData, getClasses());

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

    }

	/**
	 * Fills data object with form data.
	 *
	 * @param theData data object
	 */
	@Override
	public <V extends ModelClassExt> void fillData(V theData) {

        for (Field theFXMLField : getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.getField(theFXMLField, fieldObject, theData, getClasses());

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

	}

	/**
	 * Returns declared fields.
	 *
	 * @return list of declared fields
	 */
	private List<Field> getDeclaredFields() {

		if (lstDeclaredFields == null) {
			lstDeclaredFields = ClassUtils.getDeclaredFieldsFirstAbstraction(getClass());
		}

		return lstDeclaredFields;

	}

	/**
	 * Returns introspection classes.
	 *
	 * @return list of introspection classes (empty list if none are declared)
	 */
	private List<Class<?>> getClasses() {

		if (lstClasses == null) {
			lstClasses = Collections.EMPTY_LIST;
		}

		return lstClasses;

	}

}

/* EOF */

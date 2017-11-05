package de.edgesoft.refereemanager.controller;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.utils.JAXBMatchUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Abstract controller for edit dialog scenes.
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
public abstract class AbstractEditDialogController<T extends ModelClassExt> implements IEditDialogController<T> {

	/**
	 * Data item.
	 */
	private T currentData = null;

	/**
	 * Reference to dialog stage.
	 */
	private Stage dialogStage = null;

	/**
	 * OK clicked?.
	 */
	private boolean okClicked = false;

	/**
	 * Declared fields of class and abstract subclasses.
	 */
	private List<Field> lstDeclaredFields = null;

	/**
	 * Classes for introspection when setting/getting values.
	 */
	private List<Class<?>> lstClasses = null;


	/**
	 * OK button.
	 */
	@FXML
	protected Button btnOK;

	/**
	 * Cancel button.
	 */
	@FXML
	protected Button btnCancel;



	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));
	}


	/**
	 * Sets data to be edited.
	 *
	 * @param theData data
	 */
	@Override
	public void setData(T theData) {

		Objects.requireNonNull(theData);

        currentData = theData;

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
	 * Returns data to be edited.
	 *
	 * @return data
	 */
	@Override
	public T getData() {
		return currentData;
	}

	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 */
	@Override
	public void setDialogStage(final Stage theStage) {
        dialogStage = theStage;
    }

	/**
	 * Returns dialog stage.
	 *
	 * @return dialog stage
	 */
	@Override
	public Stage getDialogStage() {
		return dialogStage;
	}

	/**
	 * Sets if user clicked ok.
	 *
	 * @param isOKClicked did user click ok?
	 */
	@Override
	public void setOkClicked(final boolean isOKClicked) {
		okClicked = isOKClicked;
	}

	/**
	 * Returns if user clicked ok.
	 *
	 * @return did user click ok?
	 */
	@Override
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 */
	@FXML
	@Override
    public void handleOk() {

        for (Field theFXMLField : getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.getField(theFXMLField, fieldObject, currentData, getClasses());

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

        okClicked = true;
        dialogStage.close();

    }

	/**
	 * Stores non-ok click and closes dialog.
	 */
	@FXML
	@Override
    public void handleCancel() {
		okClicked = false;
        dialogStage.close();
    }

	/**
	 * Sets declared fields.
	 *
	 * @param theDeclaredFields list of declared fields
	 */
	@Override
	public void setDeclaredFields(final List<Field> theDeclaredFields) {
        lstDeclaredFields = theDeclaredFields;
    }

	/**
	 * Returns declared fields.
	 *
	 * @return list of declared fields (empty list if none are declared)
	 */
	@Override
	public List<Field> getDeclaredFields() {

		if (lstDeclaredFields == null) {
			return Collections.EMPTY_LIST;
		}

		return lstDeclaredFields;

	}

	/**
	 * Sets introspection classes.
	 *
	 * @param theClasses list of introspection classes
	 */
	@Override
	public void setClasses(final List<Class<?>> theClasses) {
        lstClasses = theClasses;
    }

	/**
	 * Returns introspection classes.
	 *
	 * @return list of introspection classes (empty list if none are declared)
	 */
	@Override
	public List<Class<?>> getClasses() {

		if (lstClasses == null) {
			return Collections.EMPTY_LIST;
		}

		return lstClasses;

	}

}

/* EOF */

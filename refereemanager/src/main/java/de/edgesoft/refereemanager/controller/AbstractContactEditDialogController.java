package de.edgesoft.refereemanager.controller;
import java.lang.reflect.Field;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.Contact;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContactModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.JAXBMatchUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Abstract controller for all contact edit dialog scenes.
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
 * @since 0.14.0
 */
public abstract class AbstractContactEditDialogController {

	/**
	 * Classes for introspection when setting/getting values.
	 *
	 * @version 0.14.0
	 */
	private final Class<?>[] theClasses = new Class<?>[]{IDType.class, TitledIDType.class, Contact.class, EMail.class};

	/**
	 * ID text field.
	 *
	 * @version 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "id", jaxbclass = IDType.class)
	protected TextField txtID;

	/**
	 * Checkbox for editor only.
	 *
	 * @version 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "editorOnly", jaxbclass = Contact.class)
	protected CheckBox chkEditorOnly;

	/**
	 * Combobox for contact types.
	 *
	 * @version 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "contactType", jaxbclass = Contact.class)
	protected ComboBox<ModelClassExt> cboContactType;

	/**
	 * Checkbox for primary contact.
	 *
	 * @version 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "isPrimary", jaxbclass = Contact.class)
	protected CheckBox chkIsPrimary;

	/**
	 * Text area for remark.
	 *
	 * @version 0.14.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "remark", jaxbclass = TitledIDType.class)
	protected TextArea txtRemark;


	/**
	 * OK button.
	 *
	 * @version 0.14.0
	 */
	@FXML
	protected Button btnOK;

	/**
	 * Cancel button.
	 *
	 * @version 0.14.0
	 */
	@FXML
	protected Button btnCancel;

	/**
	 * Reference to dialog stage.
	 *
	 * @version 0.14.0
	 */
	protected Stage dialogStage;

	/**
	 * Current contact.
	 *
	 * @version 0.14.0
	 */
	protected ContactModel currentContact;

	/**
	 * OK clicked?.
	 *
	 * @version 0.14.0
	 */
	protected boolean okClicked;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.14.0
	 */
	@FXML
	protected void initialize() {

		// fill sex types
        ComboBoxUtils.prepareComboBox(cboContactType, AppModel.getData().getContent().getContactType());

		// required fields
        for (Field theFXMLField : getClass().getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.markRequired(theFXMLField, fieldObject, theClasses);

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

	}

	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 *
	 * @version 0.14.0
	 */
	public void setDialogStage(final Stage theStage) {
        dialogStage = theStage;
    }

	/**
	 * Sets contact to be edited.
	 *
	 * @param theContact contact
	 *
	 * @version 0.14.0
	 */
	public void setContact(ContactModel theContact) {

		Objects.requireNonNull(theContact);

        currentContact = theContact;

        for (Field theFXMLField : getClass().getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.setField(theFXMLField, fieldObject, theContact, theClasses);

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

    }

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 *
	 * @version 0.14.0
	 */
	@FXML
    private void handleOk() {

        for (Field theFXMLField : getClass().getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.getField(theFXMLField, fieldObject, currentContact, theClasses);

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

        okClicked = true;
        dialogStage.close();

    }

	/**
	 * Returns if user clicked ok.
	 *
	 * @return did user click ok?
	 *
	 * @version 0.14.0
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Stores non-ok click and closes dialog.
	 *
	 * @version 0.14.0
	 */
	@FXML
    private void handleCancel() {
		okClicked = false;
        dialogStage.close();
    }

}

/* EOF */
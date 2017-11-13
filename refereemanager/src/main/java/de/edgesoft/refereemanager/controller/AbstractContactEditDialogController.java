package de.edgesoft.refereemanager.controller;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.Address;
import de.edgesoft.refereemanager.jaxb.Contact;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.PhoneNumber;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.jaxb.URL;
import de.edgesoft.refereemanager.model.AppModel;
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
public abstract class AbstractContactEditDialogController extends AbstractEditDialogController<Contact> {

	/**
	 * ID text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "id", jaxbclass = IDType.class)
	protected TextField txtID;

	/**
	 * Checkbox for editor only.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "editorOnly", jaxbclass = Contact.class)
	protected CheckBox chkEditorOnly;

	/**
	 * Combobox for contact types.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "contactType", jaxbclass = Contact.class)
	protected ComboBox<ModelClassExt> cboContactType;

	/**
	 * Clear contact type.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnContactTypeClear;

	/**
	 * Checkbox for primary contact.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "isPrimary", jaxbclass = Contact.class)
	protected CheckBox chkIsPrimary;

	/**
	 * Text area for remark.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "remark", jaxbclass = TitledIDType.class)
	protected TextArea txtRemark;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {

		setClasses(new ArrayList<>(Arrays.asList(new Class<?>[]{IDType.class, TitledIDType.class, Contact.class, Address.class, EMail.class, PhoneNumber.class, URL.class})));
		super.initialize();

		// fill sex types
        ComboBoxUtils.prepareComboBox(cboContactType, AppModel.getData().getContent().getContactType());

		// required fields
        for (Field theFXMLField : getDeclaredFields()) {

        	try {
        		Object fieldObject = theFXMLField.get(this);

        		JAXBMatchUtils.markRequired(theFXMLField, fieldObject, getClasses());

        	} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}

        }

		// enable buttons
		btnContactTypeClear.disableProperty().bind(
				cboContactType.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnContactTypeClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Sets data to be edited.
	 *
	 * @param theData data
	 */
	@Override
	public void setData(Contact theData) {

		super.setData(theData);

    }

	/**
	 * Clears contact type selection.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleContactTypeClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboContactType);
	}

}

/* EOF */

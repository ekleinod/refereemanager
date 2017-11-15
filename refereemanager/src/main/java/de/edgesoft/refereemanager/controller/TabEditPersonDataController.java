package de.edgesoft.refereemanager.controller;
import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Controller for the person data edit dialog tab.
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
public class TabEditPersonDataController extends AbstractInputFormController {

	/**
	 * ID text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "id", jaxbclass = IDType.class)
	protected TextField txtID;

	/**
	 * Title text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "title", jaxbclass = TitledIDType.class)
	protected TextField txtTitle;

	/**
	 * First name text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "firstName", jaxbclass = Person.class)
	protected TextField txtFirstName;

	/**
	 * Name text field label.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "name", jaxbclass = Person.class)
	protected Label lblName;

	/**
	 * Name text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "name", jaxbclass = Person.class)
	protected TextField txtName;

	/**
	 * Birthday picker.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "birthday", jaxbclass = Person.class)
	protected DatePicker pckBirthday;

	/**
	 * Day of death picker.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "dayOfDeath", jaxbclass = Person.class)
	protected DatePicker pckDayOfDeath;

	/**
	 * Combobox for sex types.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "sexType", jaxbclass = Person.class)
	protected ComboBox<ModelClassExt> cboSexType;

	/**
	 * Clear sex types.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Button btnSexTypeClear;

	/**
	 * Combobox for roles.
	 *
	 * @since 0.15.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "role", jaxbclass = Person.class)
	protected ComboBox<ModelClassExt> cboRole;

	/**
	 * Clear roles.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnRoleClear;

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
	protected void initialize() {

		// set date picker date format
		pckBirthday.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));
        pckDayOfDeath.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboSexType, AppModel.getData().getContent().getSexType());
        ComboBoxUtils.prepareComboBox(cboRole, AppModel.getData().getContent().getRoleType());

		btnSexTypeClear.disableProperty().bind(
				cboSexType.getSelectionModel().selectedItemProperty().isNull()
		);
		btnRoleClear.disableProperty().bind(
				cboRole.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnSexTypeClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnRoleClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Clears sex type selection.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private void handleSexTypeClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboSexType);
	}

	/**
	 * Clears role selection.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleRoleClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboRole);
	}

}

/* EOF */

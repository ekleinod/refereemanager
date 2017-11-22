package de.edgesoft.refereemanager.controller.inputforms;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.ButtonUtils;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;

/**
 * Controller for the referee data edit dialog tab.
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
public class InputFormRefereeDataController extends AbstractInputFormController {

	/**
	 * Combobox for member clubs.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "member", jaxbclass = Referee.class)
	protected ComboBox<ModelClassExt> cboMember;

	/**
	 * Clear member clubs.
	 */
	@FXML
	private Button btnMemberClear;

	/**
	 * Combobox for reffor clubs.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "reffor", jaxbclass = Referee.class)
	protected ComboBox<ModelClassExt> cboReffor;

	/**
	 * Clear reffor clubs.
	 */
	@FXML
	private Button btnRefforClear;

	/**
	 * Combobox for status.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "status", jaxbclass = Referee.class)
	protected ComboBox<ModelClassExt> cboStatus;

	/**
	 * Clear status.
	 */
	@FXML
	private Button btnStatusClear;

	/**
	 * Checkbox for docs by letter.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "docsByLetter", jaxbclass = Referee.class)
	protected CheckBox chkDocsByLetter;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboMember, AppModel.getData().getContent().getClub());
        ComboBoxUtils.prepareComboBox(cboReffor, AppModel.getData().getContent().getClub());
        ComboBoxUtils.prepareComboBox(cboStatus, AppModel.getData().getContent().getStatusType());

		// enable buttons
        ButtonUtils.bindDisable(btnMemberClear, cboMember);
        ButtonUtils.bindDisable(btnRefforClear, cboReffor);
        ButtonUtils.bindDisable(btnStatusClear, cboStatus);

		// icons
		btnMemberClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnRefforClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnStatusClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

	}

	/**
	 * Clears member selection.
	 */
	@FXML
	private void handleMemberClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboMember);
	}

	/**
	 * Clears reffor selection.
	 */
	@FXML
	private void handleRefforClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboReffor);
	}

	/**
	 * Clears status selection.
	 */
	@FXML
	private void handleStatusClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboStatus);
	}

}

/* EOF */

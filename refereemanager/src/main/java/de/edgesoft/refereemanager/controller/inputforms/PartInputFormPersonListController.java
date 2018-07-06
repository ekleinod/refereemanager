package de.edgesoft.refereemanager.controller.inputforms;
import java.util.ArrayList;
import java.util.Arrays;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.jaxb.PersonReference;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Controller for the input form part: person list.
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
public class PartInputFormPersonListController extends AbstractInputFormController<PersonReference> {

	/**
	 * Label for combobox for people.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "person", jaxbclass = PersonReference.class)
	protected Label lblPersonList;

	/**
	 * Combobox for people.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "person", jaxbclass = PersonReference.class)
	protected ComboBox<ModelClassExt> cboPersonList;

	/**
	 * Clear people.
	 */
	@FXML
	private Button btnPersonListClear;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboPersonList, ((ContentModel) AppModel.getData().getContent()).getAllPeople());

		// enable buttons
		btnPersonListClear.disableProperty().bind(
				cboPersonList.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnPersonListClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

		// init form
		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{PersonReference.class})));

	}

	/**
	 * Clears training level type selection.
	 */
	@FXML
	private void handlePersonListClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboPersonList);
	}

}

/* EOF */

package de.edgesoft.refereemanager.controller.inputforms;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.TrainingLevel;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 * Controller for the training level edit dialog scene.
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
public class PartInputFormTrainingLevelController extends AbstractInputFormController {

	/**
	 * Label for since picker.
	 *
	 * @since 0.15.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "since", jaxbclass = TrainingLevel.class)
	protected Label lblSince;

	/**
	 * Since picker.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "since", jaxbclass = TrainingLevel.class)
	protected DatePicker pckSince;

	/**
	 * Label for combobox for training level types.
	 *
	 * @since 0.15.0
	 */
	@FXML
	@JAXBMatch(jaxbfield = "type", jaxbclass = TrainingLevel.class)
	protected Label lblTrainingLevelType;

	/**
	 * Combobox for training level types.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "type", jaxbclass = TrainingLevel.class)
	protected ComboBox<ModelClassExt> cboTrainingLevelType;

	/**
	 * Clear training level type.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnTrainingLevelTypeClear;

//	/**
//	 * List view for updates.
//	 */
//	@FXML
//	@JAXBMatch(jaxbfield = "update", jaxbclass = TrainingLevel.class)
//	protected ListView<SimpleObjectProperty<LocalDate>> lstUpdate;
//

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// set date picker date format
		pckSince.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboTrainingLevelType, AppModel.getData().getContent().getTrainingLevelType());

        // setup list views
//        lstUpdate.setCellFactory(ComboBoxUtils.getCallbackLocalDateProperty());

		// enable buttons
		btnTrainingLevelTypeClear.disableProperty().bind(
				cboTrainingLevelType.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnTrainingLevelTypeClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

		// init form
		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{TrainingLevel.class})));

	}

	/**
	 * Clears training level type selection.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleTrainingLevelTypeClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboTrainingLevelType);
	}

}

/* EOF */

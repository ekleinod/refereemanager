package de.edgesoft.refereemanager.controller.inputforms;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.controller.crud.ListCRUDController;
import de.edgesoft.refereemanager.jaxb.TrainingLevel;
import de.edgesoft.refereemanager.jaxb.Update;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Controller for the input form part: training level.
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
public class PartInputFormTrainingLevelController extends AbstractInputFormController<TrainingLevel> {

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

	/**
	 * Label for updates display.
	 *
	 * @since 0.15.0
	 */
	@FXML
	protected Label lblUpdatesLabel;

	/**
	 * Label for displaying updates.
	 *
	 * @since 0.15.0
	 */
	@FXML
	protected Label lblUpdates;

	/**
	 * Edit updates.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnEditUpdates;

	/**
	 * Quick update picker.
	 */
	@FXML
	protected DatePicker pckQuickUpdate;

	/**
	 * Add quick update.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnAddQuickUpdate;

	/**
	 * Data storage for updates
	 */
	private List<Update> lstUpdates = null;

//	/**
//	 * CRUD buttons update.
//	 */
//	@FXML
//	private Parent embeddedCRUDUpdate;
//
//	/**
//	 * CRUD buttons update controller.
//	 */
//	@FXML
//	@JAXBMatch(jaxbfield = "update", jaxbclass = TrainingLevel.class)
//	protected ListCRUDController<Update> embeddedCRUDUpdateController;


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

		// enable buttons
		btnTrainingLevelTypeClear.disableProperty().bind(
				cboTrainingLevelType.getSelectionModel().selectedItemProperty().isNull()
		);
		btnEditUpdates.disableProperty().bind(
				cboTrainingLevelType.getSelectionModel().selectedItemProperty().isNull()
		);
		btnAddQuickUpdate.disableProperty().bind(
				cboTrainingLevelType.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnTrainingLevelTypeClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnEditUpdates.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnAddQuickUpdate.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));

		// init form
		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{TrainingLevel.class})));

		lblUpdates.setText(null);

//		Map.Entry<Parent, FXMLLoader> nodeUpdate = Resources.loadNode("inputforms/PartInputFormUpdate");
//		embeddedCRUDUpdateController.initController(
//				nodeUpdate.getValue().getController(),
//				nodeUpdate.getKey(),
//				"Fortbildungen",
//				AppModel.factory::createUpdate);

	}

	@Override
	public void fillFormFromData(
			final TrainingLevel theData
			) {

		super.fillFormFromData(theData);

		if (theData == null) {
			lstUpdates = null;
		} else {
			lstUpdates = new ArrayList<>(theData.getUpdate());
		}
		updateUpdates();

	}

	@Override
	public void fillDataFromForm(
			TrainingLevel theData
			) {

		super.fillDataFromForm(theData);

		// fill updates

	}

	/**
	 * Updates update display.
	 *
	 * @since 0.15.0
	 */
	public void updateUpdates() {

		lblUpdatesLabel.setText(MessageFormat.format(
				"{0,choice,0#Fortbildungen|1#Fortbildung|1<Fortbildungen}",
				(lstUpdates == null) ? 0 : lstUpdates.size())
				);

		if (lstUpdates == null) {

			lblUpdates.setText(null);

		} else {

			if (lstUpdates.isEmpty()) {
				lblUpdates.setText("Keine");
			} else {
				lblUpdates.setText(lstUpdates.stream().map(update -> update.getDisplayText().getValueSafe()).collect(Collectors.joining("\n")));
			}

		}

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

	/**
	 * Opens update edit form.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleEditUpdates() {
		System.out.println("todo");
	}

	/**
	 * Adds quick update date.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleAddQuickUpdate() {

		LocalDate dteValue = pckQuickUpdate.getValue();

		if (dteValue != null) {

			Update newUpdate = AppModel.factory.createUpdate();
			newUpdate.setDate(new SimpleObjectProperty<>(dteValue));
			lstUpdates.add(newUpdate);

			updateUpdates();

		}

	}

}

/* EOF */

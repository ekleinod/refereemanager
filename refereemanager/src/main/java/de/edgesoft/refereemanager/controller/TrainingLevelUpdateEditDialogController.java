package de.edgesoft.refereemanager.controller;
import java.time.LocalDate;
import java.util.Objects;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Controller for the training level update edit dialog scene.
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
public class TrainingLevelUpdateEditDialogController {

	/**
	 * Update label.
	 */
	@FXML
	private Label lblUpdate;

	/**
	 * Update picker.
	 */
	@FXML
	private DatePicker pckUpdate;


	/**
	 * OK button.
	 */
	@FXML
	private Button btnOK;

	/**
	 * Cancel button.
	 */
	@FXML
	private Button btnCancel;


	/**
	 * Reference to dialog stage.
	 */
	private Stage dialogStage;

	/**
	 * Current update.
	 */
	private SimpleObjectProperty<LocalDate> currentUpdate;

	/**
	 * OK clicked?.
	 */
	private boolean okClicked;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// set date picker date format
		pckUpdate.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

		// required fields
		Font fntTemp = lblUpdate.getFont();
		lblUpdate.setFont(Font.font(fntTemp.getFamily(), FontWeight.BOLD, fntTemp.getSize()));

		// enable ok button for valid entries only
		btnOK.disableProperty().bind(
				pckUpdate.valueProperty().isNull()
		);

		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

	}

	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 */
	public void setDialogStage(final Stage theStage) {
        dialogStage = theStage;
    }

	/**
	 * Sets update to be edited.
	 *
	 * @param theUpdate update
	 */
	public void setUpdate(SimpleObjectProperty<LocalDate> theUpdate) {

		Objects.requireNonNull(theUpdate);

        currentUpdate = theUpdate;

        // fill fields
        pckUpdate.setValue(theUpdate.getValue());

    }

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 */
	@FXML
    private void handleOk() {

		currentUpdate.setValue(pckUpdate.getValue());

        okClicked = true;
        dialogStage.close();

    }

	/**
	 * Returns if user clicked ok.
	 *
	 * @return did user click ok?
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Stores non-ok click and closes dialog.
	 */
	@FXML
    private void handleCancel() {
		okClicked = false;
        dialogStage.close();
    }


}

/* EOF */

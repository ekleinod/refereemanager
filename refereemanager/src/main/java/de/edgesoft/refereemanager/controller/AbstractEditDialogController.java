package de.edgesoft.refereemanager.controller;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
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
public abstract class AbstractEditDialogController<T extends ModelClassExt> extends AbstractEditDialogInputController<T> implements IEditDialogController<T> {

	/**
	 * Reference to dialog stage.
	 */
	private Stage dialogStage = null;

	/**
	 * OK clicked?.
	 */
	private boolean okClicked = false;


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

		storeData();

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

}

/* EOF */

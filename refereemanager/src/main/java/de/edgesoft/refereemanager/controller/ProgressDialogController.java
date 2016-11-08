package de.edgesoft.refereemanager.controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * Controller for progress dialog scene.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of refereemanager.
 *
 * refereemanager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * refereemanager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with refereemanager.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.10.0
 * @since 0.10.0
 */
public class ProgressDialogController {

	/**
	 * Title label
	 *
	 * @version 6.0.0
	 * @since 6.0.0
	 */
	@FXML
	private Label lblTitle;

	/**
	 * Message label.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblMessage;

	/**
	 * Progress bar.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private ProgressBar progBar;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void initialize() {

		progBar.setProgress(0);
		progBar.progressProperty().unbind();
		lblMessage.textProperty().unbind();

    }

	/**
	 * Initializes the controller with things, that cannot be done during {@link #initialize()}.
	 *
	 * @param theTitle title
	 * @param theProgress progress value to bind
	 * @param theMessage message text to bind
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void initController(final String theTitle, final ObservableValue<? extends Number> theProgress, final ObservableValue<? extends String> theMessage) {
		lblTitle.setText(theTitle);
		progBar.progressProperty().bind(theProgress);
		lblMessage.textProperty().bind(theMessage);
    }

}

/* EOF */

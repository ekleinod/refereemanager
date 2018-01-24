package de.edgesoft.refereemanager.controller.crud;

import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Controller for the overview part: buttons.
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
public class CRUDButtonsController {

	/**
	 * Add button.
	 */
	@FXML
	private Button btnAdd;

	/**
	 * Edit button.
	 */
	@FXML
	private Button btnEdit;

	/**
	 * Delete button.
	 */
	@FXML
	private Button btnDelete;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// icons
		btnAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

	}

	/**
	 * Returns add button.
	 *
	 * @return add button
	 */
	public Button getAddButton() {
		return btnAdd;
	}

	/**
	 * Returns edit button.
	 *
	 * @return edit button
	 */
	public Button getEditButton() {
		return btnEdit;
	}

	/**
	 * Returns delete button.
	 *
	 * @return delete button
	 */
	public Button getDeleteButton() {
		return btnDelete;
	}

}

/* EOF */

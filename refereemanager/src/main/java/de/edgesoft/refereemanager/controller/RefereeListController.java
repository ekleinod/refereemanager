package de.edgesoft.refereemanager.controller;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.RefereeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller for the referee list scene.
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
public class RefereeListController {

	/**
	 * Table view.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableView<Referee> tblReferees;

	/**
	 * Name column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<RefereeModel, String> colName;

	/**
	 * First name column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<RefereeModel, String> colFirstName;

	/**
	 * Training level column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<RefereeModel, String> colTrainingLevel;

	/**
	 * Club column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<RefereeModel, String> colClub;


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

		// set "empty data" text
		Label lblPlaceholder = new Label("Es wurden noch keine Schiedsrichter eingegeben.");
		lblPlaceholder.setWrapText(true);
		tblReferees.setPlaceholder(lblPlaceholder);

		// hook data to columns
		colName.setCellValueFactory(cellData -> cellData.getValue().getName());
		colFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
		colTrainingLevel.setCellValueFactory(cellData -> cellData.getValue().getHighestTrainingLevel().getType().getDisplayTitle());
		colClub.setCellValueFactory(cellData -> cellData.getValue().getMember().getDisplayTitle());

	}

	/**
	 * Returns table view.
	 *
	 * @return table view
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public TableView<Referee> getTableView() {
		return tblReferees;
	}

}

/* EOF */

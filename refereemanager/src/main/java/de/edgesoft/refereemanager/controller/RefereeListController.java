package de.edgesoft.refereemanager.controller;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.RefereeModel;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

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
	 * Checkbox filter active.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkActive;
	
	/**
	 * Checkbox filter inactive.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkInactive;
	
	/**
	 * Checkbox filter email.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkEMail;
	
	/**
	 * Checkbox filter letter only.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkLetterOnly;
	
	/**
	 * List of referees.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private FilteredList<Referee> lstReferees;


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
	 * @param theReferees list of referees
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void setItems(final ObservableList<Referee> theReferees) {
		
		if (theReferees == null) {
			lstReferees = null;
		} else {
			lstReferees = new FilteredList<>(theReferees, referee -> true);
		}
		
		tblReferees.setItems(lstReferees);
		handleFilterChange();
		
	}

	/**
	 * Handles filter change events.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleFilterChange() {
		
//		lstReferees.setPredicate(referee -> {
//			return referee.getDisplayTitle().get().equals("Ekkart Kleinod");
//		});
		
		System.out.println(chkActive.isSelected());
		System.out.println(chkInactive.isSelected());
		System.out.println(chkEMail.isSelected());
		System.out.println(chkLetterOnly.isSelected());
		
		tblReferees.refresh();
		
	}
	
	/**
	 * Sets selection mode.
	 * 
	 * @param theSelectionMode selection mode
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void setSelectionMode(final SelectionMode theSelectionMode) {
		tblReferees.getSelectionModel().setSelectionMode(theSelectionMode);
	}

	/**
	 * Returns selection model.
	 *
	 * @return selection model
	 * 
	 * @version 0.10.0
	 * @return 
	 * @since 0.10.0
	 */
	public TableViewSelectionModel<Referee> getSelectionModel() {
		return tblReferees.getSelectionModel();
	}

}

/* EOF */

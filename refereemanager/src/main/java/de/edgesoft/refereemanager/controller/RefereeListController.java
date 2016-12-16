package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.Trainee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
 * @version 0.12.0
 * @since 0.10.0
 */
public class RefereeListController {

	/**
	 * Text for empty table: no data.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	private static final String TABLE_NO_DATA = "Es wurden noch keine {0} eingegeben.";

	/**
	 * Text for empty table: filtered.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	private static final String TABLE_FILTERED = "Die Filterung schließt alle {0} aus.";

	/**
	 * Tab pane content.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TabPane tabPaneContent;

	/**
	 * Tab referees.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Tab tabReferees;

	/**
	 * Table view referees.
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
	private TableColumn<RefereeModel, String> colRefereesName;

	/**
	 * First name column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<RefereeModel, String> colRefereesFirstName;

	/**
	 * Training level column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<RefereeModel, String> colRefereesTrainingLevel;

	/**
	 * Club column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<RefereeModel, String> colRefereesClub;

	/**
	 * Label filter.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblRefereesFilter;

	/**
	 * Checkbox filter active.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkRefereesActive;

	/**
	 * Checkbox filter inactive.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkRefereesInactive;

	/**
	 * Checkbox filter email.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkRefereesEMail;

	/**
	 * Checkbox filter letter only.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkRefereesLetterOnly;

	/**
	 * List of referees.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private FilteredList<Referee> lstReferees;


	/**
	 * Tab trainees.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Tab tabTrainees;

	/**
	 * Table view trainees.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TableView<Trainee> tblTrainees;

	/**
	 * Name column.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TableColumn<Trainee, String> colTraineesName;

	/**
	 * First name column.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TableColumn<Trainee, String> colTraineesFirstName;

	/**
	 * Club column.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TableColumn<Trainee, String> colTraineesClub;

	/**
	 * List of trainees.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	private FilteredList<Trainee> lstTrainees;


	/**
	 * Tab people.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Tab tabPeople;

	/**
	 * Table view trainees.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TableView<Person> tblPeople;

	/**
	 * Name column.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TableColumn<PersonModel, String> colPeopleName;

	/**
	 * First name column.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TableColumn<PersonModel, String> colPeopleFirstName;

	/**
	 * List of people.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	private FilteredList<Person> lstPeople;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private void initialize() {

		// hook data to columns (referees)
		colRefereesName.setCellValueFactory(cellData -> cellData.getValue().getName());
		colRefereesFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
		colRefereesTrainingLevel.setCellValueFactory(cellData -> cellData.getValue().getHighestTrainingLevel().getType().getDisplayTitle());
		colRefereesClub.setCellValueFactory(cellData -> cellData.getValue().getMember().getDisplayTitle());

		// hook data to columns (trainees)
		colTraineesName.setCellValueFactory(cellData -> cellData.getValue().getName());
		colTraineesFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
		colTraineesClub.setCellValueFactory(cellData -> cellData.getValue().getMember().getDisplayTitle());

		// hook data to columns (people)
		colPeopleName.setCellValueFactory(cellData -> cellData.getValue().getName());
		colPeopleFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());

		// listen to tab changes
		tabPaneContent.getSelectionModel().selectedItemProperty().addListener((event, oldTab, newTab) -> {
	        handleTabChange();
	    });

		setItems();

	}

	/**
	 * Sets table items.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	public void setItems() {

		if (AppModel.getData() == null) {
			lstReferees = null;
			lstTrainees = null;
			lstPeople = null;
		} else {
			lstReferees = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableReferees(), referee -> true);
			lstTrainees = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableTrainees(), trainee -> true);
			lstPeople = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservablePeople(), person -> true);
		}

		tblReferees.setItems(lstReferees);
		tblTrainees.setItems(lstTrainees);
		tblPeople.setItems(lstPeople);

		// set "empty data" text
		Label lblPlaceholder = new Label(MessageFormat.format(((lstReferees == null) || lstReferees.isEmpty()) ? TABLE_NO_DATA : TABLE_FILTERED, "Schiedsrichter"));
		lblPlaceholder.setWrapText(true);
		tblReferees.setPlaceholder(lblPlaceholder);

		lblPlaceholder = new Label(MessageFormat.format(((lstTrainees == null) || lstTrainees.isEmpty()) ? TABLE_NO_DATA : TABLE_FILTERED, "Lehrgangsteilnehmer"));
		lblPlaceholder.setWrapText(true);
		tblTrainees.setPlaceholder(lblPlaceholder);

		lblPlaceholder = new Label(MessageFormat.format(((lstPeople == null) || lstPeople.isEmpty()) ? TABLE_NO_DATA : TABLE_FILTERED, "Personen"));
		lblPlaceholder.setWrapText(true);
		tblPeople.setPlaceholder(lblPlaceholder);

		handleFilterChange();

	}

	/**
	 * Handles filter change events.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void handleFilterChange() {

		// filter for referees
		if (lstReferees == null) {
			lblRefereesFilter.setText("Filter");
		} else {

			lstReferees.setPredicate(RefereeModel.ALL);

			if (chkRefereesActive.isSelected()) {
				lstReferees.setPredicate(((Predicate<Referee>) lstReferees.getPredicate()).and(RefereeModel.ACTIVE));
			}

			if (chkRefereesInactive.isSelected()) {
				lstReferees.setPredicate(((Predicate<Referee>) lstReferees.getPredicate()).and(RefereeModel.INACTIVE));
			}

			if (chkRefereesEMail.isSelected()) {
				lstReferees.setPredicate(((Predicate<Referee>) lstReferees.getPredicate()).and(PersonModel.HAS_EMAIL));
			}

			if (chkRefereesLetterOnly.isSelected()) {
				lstReferees.setPredicate(((Predicate<Referee>) lstReferees.getPredicate()).and(RefereeModel.LETTER_ONLY));
			}

			lblRefereesFilter.setText(MessageFormat.format("Filter ({0} ausgewählt)", lstReferees.size()));
		}

		tblReferees.refresh();
		tblTrainees.refresh();
		tblPeople.refresh();

	}

	/**
	 * Handles tab change events.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private void handleTabChange() {
		handleFilterChange();
	}

	/**
	 * Sets selection mode.
	 *
	 * @param theSelectionMode selection mode
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	public void setSelectionMode(final SelectionMode theSelectionMode) {
		tblReferees.getSelectionModel().setSelectionMode(theSelectionMode);
		tblTrainees.getSelectionModel().setSelectionMode(theSelectionMode);
		tblPeople.getSelectionModel().setSelectionMode(theSelectionMode);
	}

	/**
	 * Returns selection model of referees table.
	 *
	 * @return selection model
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	public TableViewSelectionModel<Referee> getRefereesSelectionModel() {
		return tblReferees.getSelectionModel();
	}

	/**
	 * Returns selection model of trainees table.
	 *
	 * @return selection model
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	public TableViewSelectionModel<Trainee> getTraineesSelectionModel() {
		return tblTrainees.getSelectionModel();
	}

	/**
	 * Returns selection model of people table.
	 *
	 * @return selection model
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	public TableViewSelectionModel<Person> getPeopleSelectionModel() {
		return tblPeople.getSelectionModel();
	}

	/**
	 * Returns current selection.
	 *
	 * @todo maybe this can be implemented simpler with a clever cast?
	 *
	 * @return current (visible) selection
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	public ObservableList<PersonModel> getCurrentSelection() {

		List<PersonModel> lstReturn = new ArrayList<>();

		if (tabReferees.isSelected()) {
			tblReferees.getSelectionModel().getSelectedItems().forEach(person -> lstReturn.add(person));
		}

		if (tabTrainees.isSelected()) {
			tblTrainees.getSelectionModel().getSelectedItems().forEach(person -> lstReturn.add(person));
		}

		if (tabPeople.isSelected()) {
			tblPeople.getSelectionModel().getSelectedItems().forEach(person -> lstReturn.add((PersonModel) person));
		}

		return FXCollections.observableList(lstReturn);
	}

}

/* EOF */

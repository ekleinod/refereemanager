package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.edgesoft.refereemanager.jaxb.LeagueGame;
import de.edgesoft.refereemanager.jaxb.OtherEvent;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.Tournament;
import de.edgesoft.refereemanager.jaxb.Trainee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.LeagueGameModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.model.TournamentModel;
import de.edgesoft.refereemanager.utils.TableUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
 * Controller for the event list scene.
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
public class EventListController {

	/**
	 * Text for empty table: no data.
	 */
	private static final String TABLE_NO_DATA = "Es wurden noch keine {0} eingegeben.";

	/**
	 * Text for empty table: filtered.
	 */
	private static final String TABLE_FILTERED = "Die Filterung schließt alle {0} aus.";

	/**
	 * Tab pane content.
	 */
	@FXML
	private TabPane tabPaneContent;

	/**
	 * Tab league games.
	 */
	@FXML
	private Tab tabLeagueGames;

	/**
	 * Table view league games.
	 */
	@FXML
	private TableView<LeagueGame> tblLeagueGames;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colLeagueGameID;

	/**
	 * Number column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colLeagueGameNumber;

	/**
	 * Date column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, LocalDate> colLeagueGameDate;

	/**
	 * Time column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, LocalTime> colLeagueGameTime;

	/**
	 * League column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colLeagueGameLeague;

	/**
	 * Teams column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colLeagueGameTeams;

	/**
	 * List of league games.
	 */
	private FilteredList<LeagueGame> lstLeagueGame;

	/**
	 * Label filter.
	 */
	@FXML
	private Label lblLeagueGameFilter;

	/**
	 * Checkbox filter league.
	 */
	@FXML
	private CheckBox chkLeagueGameLeague;


	/**
	 * Tab tournaments.
	 */
	@FXML
	private Tab tabTournaments;

	/**
	 * Table view tournaments.
	 */
	@FXML
	private TableView<Tournament> tblTournaments;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<TournamentModel, String> colTournamentID;

	/**
	 * Date column.
	 */
	@FXML
	private TableColumn<TournamentModel, LocalDate> colTournamentDate;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<TournamentModel, String> colTournamentTitle;

	/**
	 * List of tournaments.
	 */
	private FilteredList<Tournament> lstTournaments;


	/**
	 * Tab other events.
	 */
	@FXML
	private Tab tabOtherEvents;

	/**
	 * Table other events.
	 */
	@FXML
	private TableView<OtherEvent> tblOtherEvents;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<OtherEventModel, String> colOtherEventID;

	/**
	 * Date column.
	 */
	@FXML
	private TableColumn<OtherEventModel, String> colOtherEventDate;

	/**
	 * Time column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalTime> colOtherEventTime;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<OtherEventModel, String> colOtherEventTitle;

	/**
	 * List of other events.
	 */
	private FilteredList<OtherEvent> lstOtherEvents;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// hook data to columns (referees)
		colRefereesID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colRefereesID.setVisible(false);

		colRefereesName.setCellValueFactory(cellData -> cellData.getValue().getName());
		colRefereesFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
		colRefereesTrainingLevel.setCellValueFactory(cellData -> (cellData.getValue().getHighestTrainingLevel() == null) ? null : cellData.getValue().getHighestTrainingLevel().getType().getDisplayTitleShort());
		colRefereesClub.setCellValueFactory(cellData -> (cellData.getValue().getMember() == null) ? null : cellData.getValue().getMember().getDisplayText());

		colRefereesBirthday.setCellValueFactory(cellData -> cellData.getValue().getBirthday());
		colRefereesBirthday.setVisible(false);
		colRefereesUpdate.setCellValueFactory(cellData -> cellData.getValue().getNextTrainingUpdate());
		colRefereesUpdate.setVisible(false);

		// hook data to columns (trainees)
		colTraineesID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colTraineesID.setVisible(false);

		colTraineesName.setCellValueFactory(cellData -> cellData.getValue().getName());
		colTraineesFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
		colTraineesClub.setCellValueFactory(cellData -> (cellData.getValue().getMember() == null) ? null : cellData.getValue().getMember().getDisplayTitle());

		colTraineesBirthday.setCellValueFactory(cellData -> cellData.getValue().getBirthday());
		colTraineesBirthday.setVisible(false);

		// hook data to columns (people)
		colPeopleID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colPeopleID.setVisible(false);

		colPeopleName.setCellValueFactory(cellData -> cellData.getValue().getName());
		colPeopleFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());

		colPeopleBirthday.setCellValueFactory(cellData -> cellData.getValue().getBirthday());
		colPeopleBirthday.setVisible(false);

		// format date columns
		colRefereesBirthday.setCellFactory(column -> TableUtils.getTableCellPersonDate());
		colRefereesUpdate.setCellFactory(column -> TableUtils.getTableCellRefereeDate());
		colTraineesBirthday.setCellFactory(column -> TableUtils.getTableCellPersonDate());
		colPeopleBirthday.setCellFactory(column -> TableUtils.getTableCellPersonDate());

		// listen to tab changes
		tabPaneContent.getSelectionModel().selectedItemProperty().addListener((event, oldTab, newTab) -> {
	        handleTabChange();
	    });

		setItems();

	}

	/**
	 * Sets table items.
	 */
	public void setItems() {

		if (AppModel.getData() == null) {
			lstLeagueGame = null;
			lstTournaments = null;
			lstOtherEvents = null;
		} else {
			lstLeagueGame = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableReferees(), referee -> true);
			lstTournaments = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableTrainees(), trainee -> true);
			lstOtherEvents = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservablePeople(), person -> true);
		}

		SortedList<Referee> lstSortedRefs = new SortedList<>(lstLeagueGame);
		lstSortedRefs.comparatorProperty().bind(tblReferees.comparatorProperty());
		tblReferees.setItems(lstSortedRefs);

		SortedList<Trainee> lstSortedTrainees = new SortedList<>(lstTournaments);
		lstSortedTrainees.comparatorProperty().bind(tblTrainees.comparatorProperty());
		tblTrainees.setItems(lstSortedTrainees);

		SortedList<Person> lstSortedPeople = new SortedList<>(lstOtherEvents);
		lstSortedPeople.comparatorProperty().bind(tblPeople.comparatorProperty());
		tblPeople.setItems(lstSortedPeople);

		// set "empty data" text
		Label lblPlaceholder = new Label(MessageFormat.format(((lstLeagueGame == null) || lstLeagueGame.isEmpty()) ? TABLE_NO_DATA : TABLE_FILTERED, "Schiedsrichter"));
		lblPlaceholder.setWrapText(true);
		tblReferees.setPlaceholder(lblPlaceholder);

		lblPlaceholder = new Label(MessageFormat.format(((lstTournaments == null) || lstTournaments.isEmpty()) ? TABLE_NO_DATA : TABLE_FILTERED, "Azubis"));
		lblPlaceholder.setWrapText(true);
		tblTrainees.setPlaceholder(lblPlaceholder);

		lblPlaceholder = new Label(MessageFormat.format(((lstOtherEvents == null) || lstOtherEvents.isEmpty()) ? TABLE_NO_DATA : TABLE_FILTERED, "Personen"));
		lblPlaceholder.setWrapText(true);
		tblPeople.setPlaceholder(lblPlaceholder);

		handleFilterChange();

	}

	/**
	 * Handles filter change events.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void handleFilterChange() {

		// filter for referees
		if (lstLeagueGame == null) {
			lblRefereesFilter.setText("Filter");
		} else {

			lstLeagueGame.setPredicate(RefereeModel.ALL);

			if (chkRefereesActive.isSelected()) {
				lstLeagueGame.setPredicate(((Predicate<Referee>) lstLeagueGame.getPredicate()).and(RefereeModel.ACTIVE));
			}

			if (chkRefereesInactive.isSelected()) {
				lstLeagueGame.setPredicate(((Predicate<Referee>) lstLeagueGame.getPredicate()).and(RefereeModel.INACTIVE));
			}

			if (chkRefereesEMail.isSelected()) {
				lstLeagueGame.setPredicate(((Predicate<Referee>) lstLeagueGame.getPredicate()).and(PersonModel.HAS_EMAIL));
			}

			if (chkRefereesLetterOnly.isSelected()) {
				lstLeagueGame.setPredicate(((Predicate<Referee>) lstLeagueGame.getPredicate()).and(RefereeModel.LETTER_ONLY));
			}

			lblRefereesFilter.setText(MessageFormat.format("Filter ({0} ausgewählt)", lstLeagueGame.size()));
		}

		tblReferees.refresh();
		tblTrainees.refresh();
		tblPeople.refresh();

	}

	/**
	 * Handles tab change events.
	 *
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
	 * @since 0.12.0
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
	 * @since 0.12.0
	 */
	public TableViewSelectionModel<Referee> getRefereesSelectionModel() {
		return tblReferees.getSelectionModel();
	}

	/**
	 * Returns selection model of trainees table.
	 *
	 * @return selection model
	 *
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
	 * @since 0.12.0
	 */
	public TableViewSelectionModel<Person> getPeopleSelectionModel() {
		return tblPeople.getSelectionModel();
	}

	/**
	 * Returns tab pane.
	 *
	 * @return tab pane
	 *
	 * @since 0.13.0
	 */
	public TabPane getContentTab() {
		return tabPaneContent;
	}

	/**
	 * Returns referees tab.
	 *
	 * @return referees tab
	 *
	 * @since 0.13.0
	 */
	public Tab getTabReferees() {
		return tabReferees;
	}

	/**
	 * Returns trainees tab.
	 *
	 * @return trainees tab
	 *
	 * @since 0.13.0
	 */
	public Tab getTabTrainees() {
		return tabTrainees;
	}

	/**
	 * Returns people tab.
	 *
	 * @return people tab
	 *
	 * @since 0.13.0
	 */
	public Tab getTabPeople() {
		return tabPeople;
	}

	/**
	 * Returns selection from all tabs as sorted list.
	 *
	 * @return sorted selection from all tabs
	 *
	 * @since 0.13.0
	 */
	public ObservableList<PersonModel> getAllTabSelection() {

		List<PersonModel> lstReturn = new ArrayList<>();

		getTabRefereesSelection().forEach(person -> lstReturn.add(person));
		getTabTraineesSelection().forEach(person -> lstReturn.add(person));
		getTabPeopleSelection().forEach(person -> lstReturn.add(person));

		return FXCollections.observableList(lstReturn.stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList()));
	}

	/**
	 * Returns selection from visible tab as sorted list.
	 *
	 * @return sorted selection from visible tabs
	 *
	 * @since 0.13.0
	 */
	public ObservableList<PersonModel> getVisibleTabSelection() {

		if (tabReferees.isSelected()) {
			return getTabRefereesSelection();
		}

		if (tabTrainees.isSelected()) {
			return getTabTraineesSelection();
		}

		if (tabPeople.isSelected()) {
			return getTabPeopleSelection();
		}

		return FXCollections.observableList(new ArrayList<>());
	}

	/**
	 * Returns selection from referees tab as sorted list.
	 *
	 * @return sorted selection from referees tab
	 *
	 * @since 0.13.0
	 */
	public ObservableList<PersonModel> getTabRefereesSelection() {
		return FXCollections.observableList(tblReferees.getSelectionModel().getSelectedItems().stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList()));
	}

	/**
	 * Returns selection from trainees tab as sorted list.
	 *
	 * @return sorted selection from trainees tab
	 *
	 * @since 0.13.0
	 */
	public ObservableList<PersonModel> getTabTraineesSelection() {
		return FXCollections.observableList(tblTrainees.getSelectionModel().getSelectedItems().stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList()));
	}

	/**
	 * Returns selection from people tab as sorted list.
	 *
	 * @return sorted selection from people tab
	 *
	 * @since 0.13.0
	 */
	public ObservableList<PersonModel> getTabPeopleSelection() {
		List<PersonModel> lstReturn = new ArrayList<>();

		tblPeople.getSelectionModel().getSelectedItems().forEach(person -> lstReturn.add((PersonModel) person));

		return FXCollections.observableList(lstReturn.stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList()));
	}

}

/* EOF */

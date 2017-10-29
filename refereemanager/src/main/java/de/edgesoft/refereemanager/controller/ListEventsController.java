package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.LeagueGame;
import de.edgesoft.refereemanager.jaxb.OtherEvent;
import de.edgesoft.refereemanager.jaxb.Tournament;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.EventDateModel;
import de.edgesoft.refereemanager.model.LeagueGameModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.model.TournamentModel;
import de.edgesoft.refereemanager.utils.TableUtils;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

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
public class ListEventsController {

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
	 * League games box.
	 */
	@FXML
	private VBox boxLeagueGames;

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
	 * Referee report column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, Boolean> colLeagueGameRefereeReport;

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
	 * Filter storage.
	 */
	private Map<CheckBox, League> mapLeagueGamesLeagues;


	/**
	 * Tab tournaments.
	 */
	@FXML
	private Tab tabTournaments;

	/**
	 * Tournaments box.
	 */
	@FXML
	private VBox boxTournaments;

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
	 * Start date column.
	 */
	@FXML
	private TableColumn<TournamentModel, LocalDate> colTournamentDateStart;

	/**
	 * End date column.
	 */
	@FXML
	private TableColumn<TournamentModel, LocalDate> colTournamentDateEnd;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<TournamentModel, String> colTournamentTitle;

	/**
	 * Referee report column.
	 */
	@FXML
	private TableColumn<TournamentModel, Boolean> colTournamentRefereeReport;

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
	 * Other events box.
	 */
	@FXML
	private VBox boxOtherEvents;

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
	 * Start date column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalDate> colOtherEventDateStart;

	/**
	 * End date column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalDate> colOtherEventDateEnd;

	/**
	 * Start time column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalTime> colOtherEventTimeStart;

	/**
	 * Start time column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalTime> colOtherEventTimeEnd;

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

		// hook data to columns (league games)
		colLeagueGameID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colLeagueGameID.setVisible(false);

		colLeagueGameNumber.setCellValueFactory(cellData -> cellData.getValue().getGameNumberString());
		colLeagueGameDate.setCellValueFactory(cellData -> cellData.getValue().getFirstDay().getDate());
		colLeagueGameTime.setCellValueFactory(cellData -> cellData.getValue().getFirstDay().getStartTime());
		colLeagueGameLeague.setCellValueFactory(cellData -> cellData.getValue().getLeague().getDisplayTitleShort());
		colLeagueGameTeams.setCellValueFactory(cellData -> cellData.getValue().getTeamText());
		colLeagueGameRefereeReport.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().existsRefereeReportFile()));

		// hook data to columns (tournaments)
		colTournamentID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colTournamentID.setVisible(false);

		colTournamentDateStart.setCellValueFactory(cellData -> cellData.getValue().getFirstDay().getDate());
		colTournamentDateEnd.setCellValueFactory(cellData -> (cellData.getValue().getLastDay() == null) ? null : cellData.getValue().getLastDay().getDate());
		colTournamentTitle.setCellValueFactory(cellData -> cellData.getValue().getDisplayTitleShort());
		colTournamentRefereeReport.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().existsRefereeReportFile()));

		// hook data to columns (other events)
		colOtherEventID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colOtherEventID.setVisible(false);

		colOtherEventDateStart.setCellValueFactory(cellData -> cellData.getValue().getFirstDay().getDate());
		colOtherEventDateEnd.setCellValueFactory(cellData -> (cellData.getValue().getLastDay() == null) ? null : cellData.getValue().getLastDay().getDate());
		colOtherEventTimeStart.setCellValueFactory(cellData -> cellData.getValue().getFirstDay().getStartTime());
		colOtherEventTimeEnd.setCellValueFactory(cellData -> cellData.getValue().getFirstDay().getEndTime());
		colOtherEventTitle.setCellValueFactory(cellData -> cellData.getValue().getDisplayTitleShort());

		// format date columns
		colLeagueGameDate.setCellFactory(column -> TableUtils.getTableCellLeagueGameDate());
		colLeagueGameTime.setCellFactory(column -> TableUtils.getTableCellLeagueGameTime());

		colTournamentDateStart.setCellFactory(column -> TableUtils.getTableCellTournamentDate());
		colTournamentDateEnd.setCellFactory(column -> TableUtils.getTableCellTournamentDate());

		colOtherEventDateStart.setCellFactory(column -> TableUtils.getTableCellOtherEventDate());
		colOtherEventDateEnd.setCellFactory(column -> TableUtils.getTableCellOtherEventDate());
		colOtherEventTimeStart.setCellFactory(column -> TableUtils.getTableCellOtherEventTime());
		colOtherEventTimeEnd.setCellFactory(column -> TableUtils.getTableCellOtherEventTime());

		// format referee report columns
		colLeagueGameRefereeReport.setCellFactory(column -> TableUtils.getTableCellLeagueGameRefereeReport());
		colTournamentRefereeReport.setCellFactory(column -> TableUtils.getTableCellTournamentRefereeReport());

		// listen to tab changes
		tabPaneContent.getSelectionModel().selectedItemProperty().addListener((event, oldTab, newTab) -> {
	        handleTabChange();
	    });

		// headings
		lblLeagueGameFilter.setFont(FontUtils.getDerived(lblLeagueGameFilter.getFont(), FontWeight.BOLD));

		// setup league filter
		HBox boxLeagueFilter = new HBox(5);
		boxLeagueGames.getChildren().add(new Separator(Orientation.HORIZONTAL));
		boxLeagueGames.getChildren().add(boxLeagueFilter);

		mapLeagueGamesLeagues = new HashMap<>();
		AppModel.getData().getContent().getLeague().stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).forEach(
				league -> {
					CheckBox chkTemp = new CheckBox(league.getDisplayTitleShort().getValue());
					chkTemp.setOnAction(e -> handleFilterChange());
					boxLeagueFilter.getChildren().add(chkTemp);
					mapLeagueGamesLeagues.put(chkTemp, league);
				}
		);

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
			lstLeagueGame = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableLeagueGames(), leaguegame -> true);
			lstTournaments = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableTournaments(), tournament -> true);
			lstOtherEvents = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableOtherEvents(), otherevent -> true);
		}

		SortedList<LeagueGame> lstSortedLeagueGames = new SortedList<>(lstLeagueGame);
		lstSortedLeagueGames.comparatorProperty().bind(tblLeagueGames.comparatorProperty());
		tblLeagueGames.setItems(lstSortedLeagueGames);

		SortedList<Tournament> lstSortedTournaments = new SortedList<>(lstTournaments);
		lstSortedTournaments.comparatorProperty().bind(tblTournaments.comparatorProperty());
		tblTournaments.setItems(lstSortedTournaments);

		SortedList<OtherEvent> lstSortedOtherEvents = new SortedList<>(lstOtherEvents);
		lstSortedOtherEvents.comparatorProperty().bind(tblOtherEvents.comparatorProperty());
		tblOtherEvents.setItems(lstSortedOtherEvents);

		// set "empty data" text
		Label lblPlaceholder = new Label(MessageFormat.format(
				((lstLeagueGame == null) || lstLeagueGame.isEmpty()) ? TableUtils.TABLE_NO_DATA : TableUtils.TABLE_FILTERED,
				"Ligaspiele"));
		lblPlaceholder.setWrapText(true);
		tblLeagueGames.setPlaceholder(lblPlaceholder);

		lblPlaceholder = new Label(MessageFormat.format(((lstTournaments == null) || lstTournaments.isEmpty()) ?
				TableUtils.TABLE_NO_DATA : TableUtils.TABLE_FILTERED,
				"Turniere"));
		lblPlaceholder.setWrapText(true);
		tblTournaments.setPlaceholder(lblPlaceholder);

		lblPlaceholder = new Label(MessageFormat.format(
				((lstOtherEvents == null) || lstOtherEvents.isEmpty()) ? TableUtils.TABLE_NO_DATA : TableUtils.TABLE_FILTERED,
				"sonstigen Termine"));
		lblPlaceholder.setWrapText(true);
		tblOtherEvents.setPlaceholder(lblPlaceholder);

		handleFilterChange();

	}

	/**
	 * Handles filter change events.
	 */
	@SuppressWarnings("unchecked")
	private void handleFilterChange() {

		// filter for events
		if (lstLeagueGame == null) {
			lblLeagueGameFilter.setText("Filter");
		} else {

			lstLeagueGame.setPredicate(LeagueGameModel.ALL);

			for (Entry<CheckBox, League> entryChkLeague : mapLeagueGamesLeagues.entrySet()) {

				if (entryChkLeague.getKey().isSelected()) {
					lstLeagueGame.setPredicate(((Predicate<LeagueGame>) lstLeagueGame.getPredicate()).and(LeagueGameModel.getLeaguePredicate(entryChkLeague.getValue())));
				}

			}

			lblLeagueGameFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstLeagueGame.size()));
		}

		tblLeagueGames.refresh();
		tblTournaments.refresh();
		tblOtherEvents.refresh();

	}

	/**
	 * Handles tab change events.
	 */
	@FXML
	private void handleTabChange() {
		handleFilterChange();
	}

	/**
	 * Sets selection mode.
	 *
	 * @param theSelectionMode selection mode
	 */
	public void setSelectionMode(final SelectionMode theSelectionMode) {
		tblLeagueGames.getSelectionModel().setSelectionMode(theSelectionMode);
		tblTournaments.getSelectionModel().setSelectionMode(theSelectionMode);
		tblOtherEvents.getSelectionModel().setSelectionMode(theSelectionMode);
	}

	/**
	 * Returns selection model of league games table.
	 *
	 * @return selection model
	 */
	public TableViewSelectionModel<LeagueGame> getLeagueGamesSelectionModel() {
		return tblLeagueGames.getSelectionModel();
	}

	/**
	 * Returns selection model of tournaments table.
	 *
	 * @return selection model
	 */
	public TableViewSelectionModel<Tournament> getTournamentsSelectionModel() {
		return tblTournaments.getSelectionModel();
	}

	/**
	 * Returns selection model of other events table.
	 *
	 * @return selection model
	 */
	public TableViewSelectionModel<OtherEvent> getOtherEventsSelectionModel() {
		return tblOtherEvents.getSelectionModel();
	}

	/**
	 * Returns tab pane.
	 *
	 * @return tab pane
	 */
	public TabPane getContentTab() {
		return tabPaneContent;
	}

	/**
	 * Returns league games tab.
	 *
	 * @return league games tab
	 */
	public Tab getTabLeagueGames() {
		return tabLeagueGames;
	}

	/**
	 * Returns tournaments tab.
	 *
	 * @return tournaments tab
	 */
	public Tab getTabTournaments() {
		return tabTournaments;
	}

	/**
	 * Returns other events tab.
	 *
	 * @return other events tab
	 */
	public Tab getTabOtherEvents() {
		return tabOtherEvents;
	}

	/**
	 * Returns selection from all tabs as sorted list.
	 *
	 * @return sorted selection from all tabs
	 */
	public ObservableList<EventDateModel> getAllTabSelection() {

		List<EventDateModel> lstReturn = new ArrayList<>();

		getTabLeagueGamesSelection().forEach(event -> lstReturn.add(event));
		getTabTournamentsSelection().forEach(event -> lstReturn.add(event));
		getTabOtherEventsSelection().forEach(event -> lstReturn.add(event));

		return FXCollections.observableList(lstReturn.stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList()));
	}

	/**
	 * Returns selection from visible tab as sorted list.
	 *
	 * @return sorted selection from visible tabs
	 */
	public ObservableList<EventDateModel> getVisibleTabSelection() {

		if (tabLeagueGames.isSelected()) {
			return getTabLeagueGamesSelection();
		}

		if (tabTournaments.isSelected()) {
			return getTabTournamentsSelection();
		}

		if (tabOtherEvents.isSelected()) {
			return getTabOtherEventsSelection();
		}

		return FXCollections.observableList(new ArrayList<>());
	}

	/**
	 * Returns selection from league games tab as sorted list.
	 *
	 * @return sorted selection from league games tab
	 */
	public ObservableList<EventDateModel> getTabLeagueGamesSelection() {
		return FXCollections.observableList(tblLeagueGames.getSelectionModel().getSelectedItems().stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList()));
	}

	/**
	 * Returns selection from tournaments tab as sorted list.
	 *
	 * @return sorted selection from tournaments tab
	 */
	public ObservableList<EventDateModel> getTabTournamentsSelection() {
		return FXCollections.observableList(tblTournaments.getSelectionModel().getSelectedItems().stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList()));
	}

	/**
	 * Returns selection from other events tab as sorted list.
	 *
	 * @return sorted selection from other events tab
	 */
	public ObservableList<EventDateModel> getTabOtherEventsSelection() {
		return FXCollections.observableList(tblOtherEvents.getSelectionModel().getSelectedItems().stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList()));
	}

}

/* EOF */

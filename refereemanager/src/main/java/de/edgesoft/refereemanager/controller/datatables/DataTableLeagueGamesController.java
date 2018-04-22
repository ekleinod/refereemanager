package de.edgesoft.refereemanager.controller.datatables;

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
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.EventDateModel;
import de.edgesoft.refereemanager.model.LeagueGameModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
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
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

/**
 * Controller for the league game list scene.
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
public class DataTableLeagueGamesController extends AbstractDataTableController<LeagueGame> {

	/**
	 * Container.
	 */
	@FXML
	private VBox boxContainer;

	/**
	 * Table league games.
	 */
	@FXML
	private TableView<LeagueGame> tblData;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colID;

	/**
	 * Number column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colNumber;

	/**
	 * Date column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, LocalDate> colDate;

	/**
	 * Time column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, LocalTime> colTime;

	/**
	 * League column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colLeague;

	/**
	 * Teams column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, String> colTeams;

	/**
	 * Referee report column.
	 */
	@FXML
	private TableColumn<LeagueGameModel, Boolean> colRefereeReport;


	/**
	 * Label filter.
	 */
	@FXML
	private Label lblFilter;

	/**
	 * List of league games.
	 */
	private FilteredList<LeagueGame> lstLeagueGames;

	/**
	 * Filter storage.
	 */
	private Map<CheckBox, League> mapLeagueGamesLeagues;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// hook data to columns
		colID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colID.setVisible(false);

		colNumber.setCellValueFactory(cellData -> cellData.getValue().getGameNumberString());
		colDate.setCellValueFactory(cellData -> cellData.getValue().getFirstDay().getDate());
		colTime.setCellValueFactory(cellData -> cellData.getValue().getFirstDay().getStartTime());
		colLeague.setCellValueFactory(cellData -> cellData.getValue().getLeague().getDisplayTitleShort());
		colTeams.setCellValueFactory(cellData -> cellData.getValue().getTeamText());
		colRefereeReport.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().existsRefereeReportFile()));

		// format date columns
		colDate.setCellFactory(column -> TableUtils.getTableCellLeagueGameDate(null));
		colTime.setCellFactory(column -> TableUtils.getTableCellLeagueGameTime(null));

		// headings
		lblFilter.setFont(FontUtils.getDerived(lblFilter.getFont(), FontWeight.BOLD));

		// setup league filter
		HBox boxLeagueFilter = new HBox(5);
		boxContainer.getChildren().add(new Separator(Orientation.HORIZONTAL));
		boxContainer.getChildren().add(boxLeagueFilter);

		mapLeagueGamesLeagues = new HashMap<>();
		AppModel.getData().getContent().getLeague().stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).forEach(
				league -> {
					CheckBox chkTemp = new CheckBox(league.getDisplayTitleShort().getValue());
					chkTemp.setOnAction(e -> handleFilterChange());
					boxLeagueFilter.getChildren().add(chkTemp);
					mapLeagueGamesLeagues.put(chkTemp, league);
				}
		);

		// init items
		setDataTableItems();

	}

	/**
	 * Returns data table.
	 */
	@Override
	public TableView<LeagueGame> getDataTable() {
		return tblData;
	}

	/**
	 * Sets table items.
	 */
	@Override
	public void setDataTableItems() {

		lstLeagueGames = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableLeagueGames(), leaguegame -> true);

		SortedList<LeagueGame> lstSortedLeagueGames = new SortedList<>(lstLeagueGames);
		lstSortedLeagueGames.comparatorProperty().bind(tblData.comparatorProperty());
		tblData.setItems(lstSortedLeagueGames);

		setDataTablePlaceholderNoun("Ligaspiele");

		handleFilterChange();

	}

	/**
	 * Handles filter change events.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	@Override
	public void handleFilterChange() {

		// filter for events
		if (lstLeagueGames == null) {
			lblFilter.setText("Filter");
		} else {

			lstLeagueGames.setPredicate(TitledIDTypeModel.ALL);

			for (Entry<CheckBox, League> entryChkLeague : mapLeagueGamesLeagues.entrySet()) {

				if (entryChkLeague.getKey().isSelected()) {
					lstLeagueGames.setPredicate(((Predicate<LeagueGame>) lstLeagueGames.getPredicate()).and(LeagueGameModel.getLeaguePredicate(entryChkLeague.getValue())));
				}

			}

			lblFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstLeagueGames.size()));

		}

		tblData.refresh();

	}

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 */
	@Override
	public ObservableList<LeagueGame> getSortedSelectedItems() {
		List<LeagueGame> lstReturn = new ArrayList<>();

		getSelectionModel().getSelectedItems().forEach(data -> lstReturn.add(data));

		return FXCollections.observableList(lstReturn.stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList()));
	}

}

/* EOF */

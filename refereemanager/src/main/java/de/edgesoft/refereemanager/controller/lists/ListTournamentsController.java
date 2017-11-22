package de.edgesoft.refereemanager.controller.lists;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.refereemanager.controller.AbstractListController;
import de.edgesoft.refereemanager.jaxb.Tournament;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.EventDateModel;
import de.edgesoft.refereemanager.model.TournamentModel;
import de.edgesoft.refereemanager.utils.TableUtils;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

/**
 * Controller for the tournaments list scene.
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
public class ListTournamentsController extends AbstractListController {

	/**
	 * List.
	 */
	@FXML
	private VBox boxList;

	/**
	 * Table tournaments.
	 */
	@FXML
	private TableView<Tournament> tblData;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<TournamentModel, String> colID;

	/**
	 * Start date column.
	 */
	@FXML
	private TableColumn<TournamentModel, LocalDate> colDateStart;

	/**
	 * End date column.
	 */
	@FXML
	private TableColumn<TournamentModel, LocalDate> colDateEnd;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<TournamentModel, String> colTitle;

	/**
	 * Referee report column.
	 */
	@FXML
	private TableColumn<TournamentModel, Boolean> colRefereeReport;


	/**
	 * Label filter.
	 */
	@FXML
	private Label lblFilter;

	/**
	 * List of tournaments.
	 */
	private FilteredList<Tournament> lstTournaments;


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

		colDateStart.setCellValueFactory(cellData -> cellData.getValue().getFirstDay().getDate());
		colDateEnd.setCellValueFactory(cellData -> (cellData.getValue().getLastDay() == null) ? null : cellData.getValue().getLastDay().getDate());
		colTitle.setCellValueFactory(cellData -> cellData.getValue().getDisplayTitleShort());
		colRefereeReport.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().existsRefereeReportFile()));

		// format date columns
		colDateStart.setCellFactory(column -> TableUtils.getTableCellTournamentDate(null));
		colDateEnd.setCellFactory(column -> TableUtils.getTableCellTournamentDate(null));

		// format referee report columns
		colRefereeReport.setCellFactory(column -> TableUtils.getTableCellTournamentRefereeReport());

		// headings
		lblFilter.setFont(FontUtils.getDerived(lblFilter.getFont(), FontWeight.BOLD));

		// init items
		setDataTableItems();

	}

	/**
	 * Returns data table.
	 */
	@Override
	public TableView<? extends ModelClassExt> getDataTable() {
		return tblData;
	}

	/**
	 * Sets table items.
	 */
	@Override
	public void setDataTableItems() {

		lstTournaments = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableTournaments(), tournament -> true);

		SortedList<Tournament> lstSortedTournaments = new SortedList<>(lstTournaments);
		lstSortedTournaments.comparatorProperty().bind(tblData.comparatorProperty());
		tblData.setItems(lstSortedTournaments);

		setDataTablePlaceholderNoun("Turniere");

		handleFilterChange();

	}

	/**
	 * Handles filter change events.
	 */
	@FXML
	@Override
	public void handleFilterChange() {

		// filter for events
		if (lstTournaments == null) {
			lblFilter.setText("Filter");
		} else {
			lblFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstTournaments.size()));
		}

		tblData.refresh();

	}

	/**
	 * Sets selected item.
	 *
	 * @param theItem item to select
	 */
	@Override
	public <T extends ModelClassExt> void select(final T theItem) {
		tblData.getSelectionModel().select((Tournament) theItem);
	}

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 */
	@Override
	public ObservableList<TournamentModel> getSortedSelectedItems() {
		List<TournamentModel> lstReturn = new ArrayList<>();

		tblData.getSelectionModel().getSelectedItems().forEach(data -> lstReturn.add((TournamentModel) data));

		return FXCollections.observableList(lstReturn.stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList()));
	}

}

/* EOF */

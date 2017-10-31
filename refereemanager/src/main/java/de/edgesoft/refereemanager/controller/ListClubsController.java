package de.edgesoft.refereemanager.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ClubModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

/**
 * Controller for the club list scene.
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
public class ListClubsController implements IListController {

	/**
	 * List box.
	 */
	@FXML
	private VBox boxList;

	/**
	 * Table.
	 */
	@FXML
	private TableView<Club> tblData;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<ClubModel, String> colID;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<ClubModel, String> colTitle;

	/**
	 * Short title column.
	 */
	@FXML
	private TableColumn<ClubModel, String> colShorttitle;

	/**
	 * List of clubs.
	 */
	private FilteredList<Club> lstClubs;

	/**
	 * Label filter.
	 */
	@FXML
	private Label lblFilter;

	/**
	 * Checkbox filter local.
	 */
	@FXML
	private CheckBox chkLocal;

	/**
	 * Checkbox filter non-local.
	 */
	@FXML
	private CheckBox chkNonLocal;

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

		colTitle.setCellValueFactory(cellData -> cellData.getValue().getTitle());
		colShorttitle.setCellValueFactory(cellData -> cellData.getValue().getShorttitle());

		// headings
		lblFilter.setFont(FontUtils.getDerived(lblFilter.getFont(), FontWeight.BOLD));

		// setup local filter
		HBox boxLocalFilter = new HBox(5);
		boxList.getChildren().add(boxLocalFilter);

		setItems();

	}

	/**
	 * Sets table items.
	 */
	@Override
	public void setItems() {

		lstClubs = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableClubs(), club -> true);

		SortedList<Club> lstSortedClubs = new SortedList<>(lstClubs);
		lstSortedClubs.comparatorProperty().bind(tblData.comparatorProperty());
		tblData.setItems(lstSortedClubs);

		// set "empty data" text
		Label lblPlaceholder = new Label(MessageFormat.format(
				((lstClubs == null) || lstClubs.isEmpty()) ? TableUtils.TABLE_NO_DATA : TableUtils.TABLE_FILTERED,
				"Clubs"));
		lblPlaceholder.setWrapText(true);
		tblData.setPlaceholder(lblPlaceholder);

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
		if (lstClubs == null) {
			lblFilter.setText("Filter");
		} else {

			lstClubs.setPredicate(ClubModel.ALL);

			if (chkLocal.isSelected()) {
				lstClubs.setPredicate(((Predicate<Club>) lstClubs.getPredicate()).and(ClubModel.LOCAL));
			}

			if (chkNonLocal.isSelected()) {
				lstClubs.setPredicate(((Predicate<Club>) lstClubs.getPredicate()).and(ClubModel.NON_LOCAL));
			}

			lblFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstClubs.size()));
		}

		tblData.refresh();

	}

	/**
	 * Sets selection mode.
	 *
	 * @param theSelectionMode selection mode
	 */
	@Override
	public void setSelectionMode(final SelectionMode theSelectionMode) {
		tblData.getSelectionModel().setSelectionMode(theSelectionMode);
	}

	/**
	 * Returns selection model of data table.
	 *
	 * @return selection model
	 */
	@Override
	public TableViewSelectionModel<Club> getSelectionModel() {
		return tblData.getSelectionModel();
	}

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 */
	@Override
	public ObservableList<ClubModel> getSelection() {
		List<ClubModel> lstReturn = new ArrayList<>();

		tblData.getSelectionModel().getSelectedItems().forEach(club -> lstReturn.add((ClubModel) club));

		return FXCollections.observableList(lstReturn.stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).collect(Collectors.toList()));
	}

}

/* EOF */

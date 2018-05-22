package de.edgesoft.refereemanager.controller.datatables;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.refereemanager.jaxb.TrainingLevelType;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
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
 * Controller for the training level type list scene.
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
public class DataTableTrainingLevelTypesController extends AbstractDataTableController<TrainingLevelType> {

	/**
	 * Container.
	 */
	@FXML
	private VBox boxContainer;

	/**
	 * Table.
	 */
	@FXML
	private TableView<TrainingLevelType> tblData;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<TrainingLevelType, String> colID;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<TrainingLevelType, String> colTitle;

	/**
	 * Short title column.
	 */
	@FXML
	private TableColumn<TrainingLevelType, String> colShorttitle;

	/**
	 * Active column.
	 */
	@FXML
	private TableColumn<TrainingLevelType, Integer> colRank;

	/**
	 * List of types.
	 */
	private FilteredList<TrainingLevelType> lstTrainingLevelTypes;

	/**
	 * Label filter.
	 */
	@FXML
	private Label lblFilter;


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
		colRank.setCellValueFactory(cellData -> cellData.getValue().getRank().asObject());

		// headings
		lblFilter.setFont(FontUtils.getDerived(lblFilter.getFont(), FontWeight.BOLD));

		setDataTableItems();

	}

	/**
	 * Returns data table.
	 */
	@Override
	public TableView<TrainingLevelType> getDataTable() {
		return tblData;
	}

	/**
	 * Sets table items.
	 */
	@Override
	public void setDataTableItems() {

		lstTrainingLevelTypes = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableTrainingLevelTypes(), trainingleveltype -> true);

		SortedList<TrainingLevelType> lstSortedTrainingLevelTypes = new SortedList<>(lstTrainingLevelTypes);
		lstSortedTrainingLevelTypes.comparatorProperty().bind(tblData.comparatorProperty());
		tblData.setItems(lstSortedTrainingLevelTypes);

		setDataTablePlaceholderNoun("Ausbildungsarten");

		handleFilterChange();

	}

	/**
	 * Handles filter change events.
	 */
	@FXML
	@Override
	public void handleFilterChange() {

		if (lstTrainingLevelTypes == null) {
			lblFilter.setText("Filter");
		} else {

			lstTrainingLevelTypes.setPredicate(TitledIDTypeModel.ALL);

			lblFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstTrainingLevelTypes.size()));
		}

		tblData.refresh();

	}

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 */
	@Override
	public ObservableList<TrainingLevelType> getSortedSelectedItems() {
		List<TrainingLevelType> lstReturn = new ArrayList<>();

		getSelectionModel().getSelectedItems().forEach(data -> lstReturn.add(data));

		return FXCollections.observableList(lstReturn.stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).collect(Collectors.toList()));
	}

}

/* EOF */

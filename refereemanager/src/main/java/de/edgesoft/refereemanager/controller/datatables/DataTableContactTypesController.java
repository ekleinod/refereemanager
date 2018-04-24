package de.edgesoft.refereemanager.controller.datatables;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.refereemanager.jaxb.ContactType;
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
 * Controller for the contact type list scene.
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
public class DataTableContactTypesController extends AbstractDataTableController<ContactType> {

	/**
	 * Container.
	 */
	@FXML
	private VBox boxContainer;

	/**
	 * Table.
	 */
	@FXML
	private TableView<ContactType> tblData;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<ContactType, String> colID;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<ContactType, String> colTitle;

	/**
	 * Short title column.
	 */
	@FXML
	private TableColumn<ContactType, String> colShorttitle;

	/**
	 * List of sex types.
	 */
	private FilteredList<ContactType> lstContactTypes;

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

		// headings
		lblFilter.setFont(FontUtils.getDerived(lblFilter.getFont(), FontWeight.BOLD));

		setDataTableItems();

	}

	/**
	 * Returns data table.
	 */
	@Override
	public TableView<ContactType> getDataTable() {
		return tblData;
	}

	/**
	 * Sets table items.
	 */
	@Override
	public void setDataTableItems() {

		lstContactTypes = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableContactTypes(), contacttype -> true);

		SortedList<ContactType> lstSortedDontactTypes = new SortedList<>(lstContactTypes);
		lstSortedDontactTypes.comparatorProperty().bind(tblData.comparatorProperty());
		tblData.setItems(lstSortedDontactTypes);

		setDataTablePlaceholderNoun("Kontakttypen");

		handleFilterChange();

	}

	/**
	 * Handles filter change events.
	 */
	@FXML
	@Override
	public void handleFilterChange() {

		if (lstContactTypes == null) {
			lblFilter.setText("Filter");
		} else {

			lstContactTypes.setPredicate(TitledIDTypeModel.ALL);

			lblFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstContactTypes.size()));
		}

		tblData.refresh();

	}

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 */
	@Override
	public ObservableList<ContactType> getSortedSelectedItems() {
		List<ContactType> lstReturn = new ArrayList<>();

		getSelectionModel().getSelectedItems().forEach(data -> lstReturn.add(data));

		return FXCollections.observableList(lstReturn.stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).collect(Collectors.toList()));
	}

}

/* EOF */

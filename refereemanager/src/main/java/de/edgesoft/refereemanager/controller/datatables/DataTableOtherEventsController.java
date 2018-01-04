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
import de.edgesoft.refereemanager.jaxb.EventDateType;
import de.edgesoft.refereemanager.jaxb.OtherEvent;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.EventDateModel;
import de.edgesoft.refereemanager.model.OtherEventModel;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.utils.TableUtils;
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
 * Controller for the other event list scene.
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
public class DataTableOtherEventsController extends AbstractDataTableController<OtherEvent> {

	/**
	 * Container.
	 */
	@FXML
	private VBox boxContainer;

	/**
	 * Table other events.
	 */
	@FXML
	private TableView<OtherEvent> tblData;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<OtherEventModel, String> colID;

	/**
	 * Start date column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalDate> colDateStart;

	/**
	 * End date column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalDate> colDateEnd;

	/**
	 * Start time column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalTime> colTimeStart;

	/**
	 * Start time column.
	 */
	@FXML
	private TableColumn<OtherEventModel, LocalTime> colTimeEnd;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<OtherEventModel, String> colTitle;


	/**
	 * Label filter.
	 */
	@FXML
	private Label lblFilter;

	/**
	 * Filter storage.
	 */
	private Map<CheckBox, EventDateType> mapEventFilterTypes;


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

		// hook data to columns
		colID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colID.setVisible(false);

		colDateStart.setCellValueFactory(cellData -> (cellData.getValue().getFirstDay() == null) ? null : cellData.getValue().getFirstDay().getDate());
		colDateEnd.setCellValueFactory(cellData -> (cellData.getValue().getLastDay() == null) ? null : cellData.getValue().getLastDay().getDate());
		colTimeStart.setCellValueFactory(cellData -> (cellData.getValue().getFirstDay() == null) ? null : cellData.getValue().getFirstDay().getStartTime());
		colTimeEnd.setCellValueFactory(cellData -> (cellData.getValue().getFirstDay() == null) ? null : cellData.getValue().getFirstDay().getEndTime());
		colTitle.setCellValueFactory(cellData -> cellData.getValue().getDisplayTitleShort());

		// format date columns
		colDateStart.setCellFactory(column -> TableUtils.getTableCellTModelClassExtDate(null));
		colDateEnd.setCellFactory(column -> TableUtils.getTableCellOtherEventDate(null));
		colTimeStart.setCellFactory(column -> TableUtils.getTableCellOtherEventTime(null));
		colTimeEnd.setCellFactory(column -> TableUtils.getTableCellOtherEventTime(null));

		// headings
		lblFilter.setFont(FontUtils.getDerived(lblFilter.getFont(), FontWeight.BOLD));

		// setup type filter
		HBox boxTypeFilter = new HBox(5);
		boxContainer.getChildren().add(new Separator(Orientation.HORIZONTAL));
		boxContainer.getChildren().add(boxTypeFilter);

		mapEventFilterTypes = new HashMap<>();
		AppModel.getData().getContent().getEventDateType().stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).forEach(
				eventDateType -> {
					CheckBox chkTemp = new CheckBox(eventDateType.getDisplayTitleShort().getValueSafe());
					chkTemp.setOnAction(e -> handleFilterChange());
					boxTypeFilter.getChildren().add(chkTemp);
					mapEventFilterTypes.put(chkTemp, eventDateType);
				}
		);

		// init items
		setDataTableItems();

	}

	/**
	 * Returns data table.
	 */
	@Override
	public TableView<OtherEvent> getDataTable() {
		return tblData;
	}

	/**
	 * Sets table items.
	 */
	@Override
	public void setDataTableItems() {

		lstOtherEvents = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableOtherEvents(), otherevent -> true);

		SortedList<OtherEvent> lstSortedOtherEvents = new SortedList<>(lstOtherEvents);
		lstSortedOtherEvents.comparatorProperty().bind(tblData.comparatorProperty());
		tblData.setItems(lstSortedOtherEvents);

		setDataTablePlaceholderNoun("sonstigen Termine");

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
		if (lstOtherEvents == null) {
			lblFilter.setText("Filter");
		} else {

			lstOtherEvents.setPredicate(EventDateModel.ALL);

			for (Entry<CheckBox, EventDateType> entryChkType : mapEventFilterTypes.entrySet()) {

				if (entryChkType.getKey().isSelected()) {
					lstOtherEvents.setPredicate(((Predicate<OtherEvent>) lstOtherEvents.getPredicate()).and(EventDateModel.getTypePredicate(entryChkType.getValue())));
				}

			}

			lblFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstOtherEvents.size()));

		}

		tblData.refresh();

	}

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 */
	@Override
	public ObservableList<OtherEvent> getSortedSelectedItems() {
		List<OtherEvent> lstReturn = new ArrayList<>();

		getSelectionModel().getSelectedItems().forEach(data -> lstReturn.add(data));

		return FXCollections.observableList(lstReturn.stream().sorted(EventDateModel.DATE_FIRST).collect(Collectors.toList()));
	}

}

/* EOF */

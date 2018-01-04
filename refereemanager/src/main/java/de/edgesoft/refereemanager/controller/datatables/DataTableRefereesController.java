package de.edgesoft.refereemanager.controller.datatables;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.StatusType;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;
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
 * Controller for the referee list scene.
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
 * @version 0.14.0
 * @since 0.10.0
 */
public class DataTableRefereesController extends AbstractDataTableController<Referee> {

	/**
	 * Container.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private VBox boxContainer;

	/**
	 * Table.
	 */
	@FXML
	private TableView<Referee> tblData;

	/**
	 * ID column.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private TableColumn<RefereeModel, String> colID;

	/**
	 * Name column.
	 */
	@FXML
	private TableColumn<RefereeModel, String> colName;

	/**
	 * First name column.
	 */
	@FXML
	private TableColumn<RefereeModel, String> colFirstName;

	/**
	 * Training level column.
	 */
	@FXML
	private TableColumn<RefereeModel, String> colTrainingLevel;

	/**
	 * Club column.
	 */
	@FXML
	private TableColumn<RefereeModel, String> colClub;

	/**
	 * Birthday column.
	 */
	@FXML
	private TableColumn<RefereeModel, LocalDate> colBirthday;

	/**
	 * Next update column.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TableColumn<RefereeModel, LocalDate> colUpdate;

	/**
	 * Label filter.
	 */
	@FXML
	private Label lblFilter;

	/**
	 * Checkbox filter active.
	 */
	@FXML
	private CheckBox chkActive;

	/**
	 * Checkbox filter inactive.
	 */
	@FXML
	private CheckBox chkInactive;

	/**
	 * Checkbox filter email.
	 */
	@FXML
	private CheckBox chkEMail;

	/**
	 * Checkbox filter letter only.
	 */
	@FXML
	private CheckBox chkLetterOnly;

	/**
	 * Filter storage.
	 *
	 * @since 0.15.0
	 */
	private Map<CheckBox, StatusType> mapRefereesFilterStatus;


	/**
	 * List of referees.
	 */
	private FilteredList<Referee> lstReferees;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// hook data to columns (referees)
		colID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
		colID.setVisible(false);

		colName.setCellValueFactory(cellData -> cellData.getValue().getName());
		colFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
		colTrainingLevel.setCellValueFactory(cellData -> (cellData.getValue().getHighestTrainingLevel() == null) ? null : cellData.getValue().getHighestTrainingLevel().getType().getDisplayTitleShort());
		colClub.setCellValueFactory(cellData -> (cellData.getValue().getMember() == null) ? null : cellData.getValue().getMember().getDisplayText());

		colBirthday.setCellValueFactory(cellData -> cellData.getValue().getBirthday());
		colBirthday.setVisible(false);
		colBirthday.setCellFactory(column -> TableUtils.getTableCellRefereeDate(null));
		colUpdate.setCellValueFactory(cellData -> cellData.getValue().getNextTrainingUpdate());
		colUpdate.setVisible(false);
		colUpdate.setCellFactory(column -> TableUtils.getTableCellRefereeDate("yyyy"));

		// headings
		lblFilter.setFont(FontUtils.getDerived(lblFilter.getFont(), FontWeight.BOLD));

		// setup status filter
		HBox boxStatusFilter = new HBox(5);
		boxContainer.getChildren().add(new Separator(Orientation.HORIZONTAL));
		boxContainer.getChildren().add(boxStatusFilter);

		mapRefereesFilterStatus = new HashMap<>();
		AppModel.getData().getContent().getStatusType().stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).forEach(
				statusType -> {
					CheckBox chkTemp = new CheckBox(statusType.getDisplayTitleShort().getValueSafe());
					chkTemp.setOnAction(e -> handleFilterChange());
					boxStatusFilter.getChildren().add(chkTemp);
					mapRefereesFilterStatus.put(chkTemp, statusType);
				}
		);

		// init items
		setDataTableItems();

	}

	/**
	 * Returns data table.
	 */
	@Override
	public TableView<Referee> getDataTable() {
		return tblData;
	}

	/**
	 * Sets table items.
	 */
	@Override
	public void setDataTableItems() {

		lstReferees = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservableReferees(), referee -> true);

		SortedList<Referee> lstSortedRefs = new SortedList<>(lstReferees);
		lstSortedRefs.comparatorProperty().bind(tblData.comparatorProperty());
		tblData.setItems(lstSortedRefs);

		setDataTablePlaceholderNoun("Schiedsrichter");

		handleFilterChange();

	}

	/**
	 * Handles filter change events.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	@Override
	public void handleFilterChange() {

		if (lstReferees == null) {
			lblFilter.setText("Filter");
		} else {

			lstReferees.setPredicate(PersonModel.ALL);

			if (chkActive.isSelected()) {
				lstReferees.setPredicate(((Predicate<Referee>) lstReferees.getPredicate()).and(RefereeModel.ACTIVE));
			}

			if (chkInactive.isSelected()) {
				lstReferees.setPredicate(((Predicate<Referee>) lstReferees.getPredicate()).and(RefereeModel.INACTIVE));
			}

			if (chkEMail.isSelected()) {
				lstReferees.setPredicate(((Predicate<Referee>) lstReferees.getPredicate()).and(PersonModel.HAS_EMAIL));
			}

			if (chkLetterOnly.isSelected()) {
				lstReferees.setPredicate(((Predicate<Referee>) lstReferees.getPredicate()).and(RefereeModel.LETTER_ONLY));
			}

			for (Entry<CheckBox, StatusType> entryChkStatus : mapRefereesFilterStatus.entrySet()) {

				if (entryChkStatus.getKey().isSelected()) {
					lstReferees.setPredicate(((Predicate<Referee>) lstReferees.getPredicate()).and(RefereeModel.getStatusPredicate(entryChkStatus.getValue())));
				}

			}

			lblFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstReferees.size()));
		}

		tblData.refresh();

	}

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 *
	 * @since 0.12.0
	 */
	@Override
	public ObservableList<Referee> getSortedSelectedItems() {
		List<Referee> lstReturn = new ArrayList<>();

		getSelectionModel().getSelectedItems().forEach(data -> lstReturn.add(data));

		return FXCollections.observableList(lstReturn.stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList()));
	}

}

/* EOF */

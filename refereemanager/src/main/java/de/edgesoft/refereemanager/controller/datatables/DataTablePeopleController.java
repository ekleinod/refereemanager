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
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.PersonRoleType;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;
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
 * Controller for the person list scene.
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
public class DataTablePeopleController extends AbstractDataTableController<Person> {

	/**
	 * Container.
	 */
	@FXML
	private VBox boxContainer;

	/**
	 * Table.
	 */
	@FXML
	private TableView<Person> tblData;

	/**
	 * ID column.
	 */
	@FXML
	private TableColumn<PersonModel, String> colID;

	/**
	 * Name column.
	 */
	@FXML
	private TableColumn<PersonModel, String> colName;

	/**
	 * First name column.
	 */
	@FXML
	private TableColumn<PersonModel, String> colFirstName;

	/**
	 * Birthday column.
	 */
	@FXML
	private TableColumn<PersonModel, LocalDate> colBirthday;

	/**
	 * Role column.
	 */
	@FXML
	private TableColumn<PersonModel, String> colRole;


	/**
	 * Label filter.
	 */
	@FXML
	private Label lblFilter;

	/**
	 * Filter storage.
	 */
	private Map<CheckBox, PersonRoleType> mapPeopleFilterRoles;


	/**
	 * List of people.
	 */
	private FilteredList<Person> lstPeople;

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

		colName.setCellValueFactory(cellData -> cellData.getValue().getName());
		colFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());

		colBirthday.setCellValueFactory(cellData -> cellData.getValue().getBirthday());
		colBirthday.setVisible(false);
		colBirthday.setCellFactory(column -> TableUtils.getTableCellPersonDate(null));

		colRole.setCellValueFactory(cellData -> (cellData.getValue().getRole() == null) ? null : cellData.getValue().getRole().getDisplayTitleShort());

		// headings
		lblFilter.setFont(FontUtils.getDerived(lblFilter.getFont(), FontWeight.BOLD));

		// setup role filter
		HBox boxRoleFilter = new HBox(5);
		boxContainer.getChildren().add(new Separator(Orientation.HORIZONTAL));
		boxContainer.getChildren().add(boxRoleFilter);

		mapPeopleFilterRoles = new HashMap<>();
		AppModel.getData().getContent().getRoleType().stream().sorted(TitledIDTypeModel.SHORTTITLE_TITLE).forEach(
				roleType -> {
					CheckBox chkTemp = new CheckBox(roleType.getDisplayTitleShort().getValueSafe());
					chkTemp.setOnAction(e -> handleFilterChange());
					boxRoleFilter.getChildren().add(chkTemp);
					mapPeopleFilterRoles.put(chkTemp, roleType);
				}
		);

		// init items
		setDataTableItems();

	}

	/**
	 * Returns data table.
	 */
	@Override
	public TableView<Person> getDataTable() {
		return tblData;
	}

	/**
	 * Sets table items.
	 */
	@Override
	public void setDataTableItems() {

		lstPeople = new FilteredList<>(((ContentModel) AppModel.getData().getContent()).getObservablePeople(), person -> true);

		SortedList<Person> lstSortedPeople = new SortedList<>(lstPeople);
		lstSortedPeople.comparatorProperty().bind(tblData.comparatorProperty());
		tblData.setItems(lstSortedPeople);

		setDataTablePlaceholderNoun("Personen");

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
		if (lstPeople == null) {
			lblFilter.setText("Filter");
		} else {

			lstPeople.setPredicate(PersonModel.ALL);

			for (Entry<CheckBox, PersonRoleType> entryChkRole : mapPeopleFilterRoles.entrySet()) {

				if (entryChkRole.getKey().isSelected()) {
					lstPeople.setPredicate(((Predicate<Person>) lstPeople.getPredicate()).and(PersonModel.getRolePredicate(entryChkRole.getValue())));
				}

			}

			lblFilter.setText(MessageFormat.format("Filter ({0} angezeigt)", lstPeople.size()));
		}

		tblData.refresh();

	}

	/**
	 * Returns selection from table as sorted list.
	 *
	 * @return sorted selection from table
	 */
	@Override
	public ObservableList<Person> getSortedSelectedItems() {
		List<Person> lstReturn = new ArrayList<>();

		getSelectionModel().getSelectedItems().forEach(data -> lstReturn.add(data));

		return FXCollections.observableList(lstReturn.stream().sorted(PersonModel.NAME_FIRSTNAME).collect(Collectors.toList()));
	}

}

/* EOF */

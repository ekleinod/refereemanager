package de.edgesoft.refereemanager.controller.overview;

import java.util.Map;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.controller.crud.AbstractEmbedCRUDButtonsController;
import de.edgesoft.refereemanager.controller.datatables.IDataTableController;
import de.edgesoft.refereemanager.controller.details.DetailsTitledIDController;
import de.edgesoft.refereemanager.controller.details.IDetailsController;
import de.edgesoft.refereemanager.model.TitledIDTypeModel;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.FontWeight;

/**
 * General controller for overview scenes, cannot be declared abstract, because it is
 * instantiated via fxml, it is not used directly though, but through the Overview...Controllers.
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
public class OverviewDetailsController<T extends TitledIDTypeModel> extends AbstractEmbedCRUDButtonsController implements IDetailsController<T> {

	/**
	 * Heading.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * Split pane.
	 */
	@FXML
	private SplitPane pneSplit;

	/**
	 * Details pane.
	 */
	@FXML
	private BorderPane pneDetails;


	/**
	 * Details controller.
	 */
	@FXML
	private IDetailsController<T> detailsController;

	/**
	 * Data table controller.
	 */
	@FXML
	private IDataTableController<T> dataTableController;


	/**
	 * Titled ID details view part.
	 */
	@FXML
	private Parent embeddedDetailsTitledID;

	/**
	 * Titled ID details view part controller.
	 */
	@FXML
	private DetailsTitledIDController<T> embeddedDetailsTitledIDController;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// headings
		lblHeading.setFont(FontUtils.getDerived(lblHeading.getFont(), FontWeight.BOLD, 2));

	}

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theOverviewController overview controller
	 * @param theDividerPosition divider position preference key
	 * @param theListNode name of list node view
	 * @param theDetailsNode name of details node view
	 */
	public void initController(
			final AbstractOverviewController<T> theOverviewController,
			final PrefKey theDividerPosition,
			final String theListNode,
			final String theDetailsNode
			) {

		// add nodes to overview scene
		Map.Entry<Parent, FXMLLoader> pneListNode = Resources.loadNode(theListNode);
		dataTableController = pneListNode.getValue().getController();
		pneSplit.getItems().add(0, pneListNode.getKey());

		Map.Entry<Parent, FXMLLoader> pneDetailsNode = Resources.loadNode(theDetailsNode);
		detailsController = pneDetailsNode.getValue().getController();
		pneDetails.setCenter(pneDetailsNode.getKey());

		// set divider position
		getSplitPane().setDividerPositions(Double.parseDouble(Prefs.get(theDividerPosition)));

		// if changed, save divider position to preferences
		getSplitPane().getDividers().get(0).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(theDividerPosition, Double.toString(newValue.doubleValue()));
		});

		// listen to selection changes, show details
		getDataTableController().selectedItemProperty().addListener((observable, oldValue, newValue) -> theOverviewController.showDetails(newValue));
		getDataTableController().getDataTable().setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && (event.getClickCount() == 2)) {
		            theOverviewController.handleEdit(new ActionEvent(event.getSource(), event.getTarget()));
		        }
		    }
		});
		getDataTableController().getDataTable().setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        if (event.getCode() == KeyCode.ENTER) {
		            theOverviewController.handleEdit(new ActionEvent(event.getSource(), event.getTarget()));
		        }
		    }
		});

		// clear event details
		theOverviewController.showDetails(null);

		// set list items
		getDataTableController().setDataTableItems();

	}

	@Override
	public void showDetails(
			final T theDetailData
			) {

		embeddedDetailsTitledIDController.showDetails(theDetailData);

		detailsController.showDetails(theDetailData);

	}


	/**
	 * Returns data table controller.
	 *
	 * @return data table controller
	 */
	protected IDataTableController<T> getDataTableController() {
		return dataTableController;
	}

	/**
	 * Returns details controller.
	 *
	 * @return details controller
	 */
	protected IDetailsController<T> getDetailsController() {
		return detailsController;
	}

	/**
	 * Returns split pane.
	 *
	 * @return split pane
	 */
	protected SplitPane getSplitPane() {
		return pneSplit;
	}

	/**
	 * Sets heading.
	 *
	 * @param theHeading heading text
	 */
	public void setHeading(
			final StringProperty theHeading
			) {

		LabelUtils.setText(lblHeading, theHeading);

	}

}

/* EOF */
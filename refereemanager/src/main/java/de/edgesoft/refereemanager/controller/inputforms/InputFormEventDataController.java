package de.edgesoft.refereemanager.controller.inputforms;
import java.util.Map;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.refereemanager.controller.crud.ListCRUDController;
import de.edgesoft.refereemanager.jaxb.EventDate;
import de.edgesoft.refereemanager.jaxb.EventDay;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Controller for the event data edit dialog tab.
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
public class InputFormEventDataController extends AbstractInputFormController {

	/**
	 * ID text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "id", jaxbclass = IDType.class)
	protected TextField txtID;

	/**
	 * Title text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "title", jaxbclass = TitledIDType.class)
	protected TextField txtTitle;

	/**
	 * Short title text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "shorttitle", jaxbclass = TitledIDType.class)
	protected TextField txtShorttitle;

	/**
	 * Combobox for venue.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "venue", jaxbclass = EventDate.class)
	protected ComboBox<ModelClassExt> cboVenue;

	/**
	 * Clear venue.
	 */
	@FXML
	private Button btnVenueClear;

	/**
	 * Combobox for type.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "type", jaxbclass = EventDate.class)
	protected ComboBox<ModelClassExt> cboType;

	/**
	 * Clear type.
	 */
	@FXML
	private Button btnTypeClear;

	/**
	 * CRUD buttons event day.
	 */
	@FXML
	private Parent embeddedCRUDEventDay;

	/**
	 * CRUD buttons address controller.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "day", jaxbclass = EventDate.class)
	protected ListCRUDController<EventDay> embeddedCRUDEventDayController;

	/**
	 * Text area for remark.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "remark", jaxbclass = TitledIDType.class)
	protected TextArea txtRemark;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// fill combo boxes
        ComboBoxUtils.prepareComboBox(cboVenue, AppModel.getData().getContent().getVenue());
        ComboBoxUtils.prepareComboBox(cboType, AppModel.getData().getContent().getEventDateType());

		// enable buttons
		btnVenueClear.disableProperty().bind(
				cboVenue.getSelectionModel().selectedItemProperty().isNull()
		);
		btnTypeClear.disableProperty().bind(
				cboType.getSelectionModel().selectedItemProperty().isNull()
		);

		// icons
		btnVenueClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));
		btnTypeClear.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit-clear.png")));

		Map.Entry<Parent, FXMLLoader> nodeEventDay = Resources.loadNode("inputforms/PartInputFormEventDayNoAssignment");
		embeddedCRUDEventDayController.initController(
				nodeEventDay.getValue().getController(),
				nodeEventDay.getKey(),
				null,
				AppModel.factory::createEventDay);

	}

	/**
	 * Clears venue selection.
	 */
	@FXML
	private void handleVenueClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboVenue);
	}

	/**
	 * Clears type selection.
	 */
	@FXML
	private void handleTypeClear() {
		de.edgesoft.edgeutils.javafx.ComboBoxUtils.clearSelection(cboType);
	}

}

/* EOF */

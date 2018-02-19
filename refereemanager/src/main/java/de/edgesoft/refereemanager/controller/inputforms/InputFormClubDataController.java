package de.edgesoft.refereemanager.controller.inputforms;
import java.text.MessageFormat;
import java.util.Map;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.edgeutils.files.FileUtils;
import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.refereemanager.controller.crud.ListCRUDController;
import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.jaxb.URL;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.FontPosture;

/**
 * Controller for the club data edit dialog tab.
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
public class InputFormClubDataController extends AbstractInputFormController<Club> {

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
	 * Checkbox for local clubs.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "isLocal", jaxbclass = Club.class)
	protected CheckBox chkIsLocal;

	/**
	 * Filename text field.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "filename", jaxbclass = Club.class)
	protected TextField txtFilename;

	/**
	 * Generated filename label.
	 */
	@FXML
	protected Label lblGeneratedFilename;

	/**
	 * CRUD URL.
	 */
	@FXML
	private Parent embeddedCRUDURL;

	/**
	 * CRUD buttons address controller.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "URL", jaxbclass = Club.class)
	protected ListCRUDController<URL> embeddedCRUDURLController;

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

		lblGeneratedFilename.setFont(FontUtils.getDerived(lblGeneratedFilename.getFont(), FontPosture.ITALIC, -1));

		Map.Entry<Parent, FXMLLoader> nodeURL = Resources.loadNode("inputforms/PartInputFormURL");
		embeddedCRUDURLController.initController(
				nodeURL.getValue().getController(),
				nodeURL.getKey(),
				null,
				AppModel.factory::createURL);

		txtTitle.textProperty().addListener((obs, oldText, newText) -> showGeneratedFilename());
		txtShorttitle.textProperty().addListener((obs, oldText, newText) -> showGeneratedFilename());

	}

	/**
	 * Shows generated filename as remark.
	 */
	private void showGeneratedFilename() {

		String sGeneratedFilename = "";

		// check for null: catch cases in which GUI is not initialized properly
		if (txtTitle.getText() != null) {
			sGeneratedFilename = txtTitle.getText();
		}

		if (txtShorttitle.getText() != null) {
			if (!txtShorttitle.getText().isEmpty()) {
				sGeneratedFilename = txtShorttitle.getText();
			}
		}

		if (lblGeneratedFilename != null) {
			lblGeneratedFilename.setText(MessageFormat.format("Generierter Dateiname: {0}", FileUtils.cleanFilename(sGeneratedFilename, false)));
		}

	}

}

/* EOF */

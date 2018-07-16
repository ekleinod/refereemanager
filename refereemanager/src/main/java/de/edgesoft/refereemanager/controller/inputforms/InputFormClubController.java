package de.edgesoft.refereemanager.controller.inputforms;
import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
public class InputFormClubController extends AbstractInputFormController<Club> {

	/**
	 * Checkbox for local clubs.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "isLocal", jaxbclass = Club.class)
	protected CheckBox chkIsLocal;

	/**
	 * Filename.
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
	 * Homepage.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "homepage", jaxbclass = Club.class)
	protected TextField txtHomepage;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		lblGeneratedFilename.setFont(FontUtils.getDerived(lblGeneratedFilename.getFont(), FontPosture.ITALIC, -1));

//		txtTitle.textProperty().addListener((obs, oldText, newText) -> showGeneratedFilename());
//		txtShorttitle.textProperty().addListener((obs, oldText, newText) -> showGeneratedFilename());
		showGeneratedFilename();

	}

	/**
	 * Shows generated filename as remark.
	 */
	private void showGeneratedFilename() {

		lblGeneratedFilename.setText("todo");

//		String sGeneratedFilename = "";
//
//		// check for null: catch cases in which GUI is not initialized properly
//		if (txtTitle.getText() != null) {
//			sGeneratedFilename = txtTitle.getText();
//		}
//
//		if (txtShorttitle.getText() != null) {
//			if (!txtShorttitle.getText().isEmpty()) {
//				sGeneratedFilename = txtShorttitle.getText();
//			}
//		}
//
//		if (lblGeneratedFilename != null) {
//			lblGeneratedFilename.setText(MessageFormat.format("Generierter Dateiname: {0}", FileUtils.cleanFilename(sGeneratedFilename, false)));
//		}

	}

}

/* EOF */

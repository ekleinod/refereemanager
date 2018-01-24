package de.edgesoft.refereemanager.controller.details;

import java.io.File;
import java.nio.file.Paths;

import de.edgesoft.edgeutils.javafx.FontUtils;
import de.edgesoft.edgeutils.javafx.LabelUtils;
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.FontWeight;

/**
 * Controller for the person details scene.
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
public class DetailsPersonController<T extends PersonModel> implements IDetailsController<T> {

	/**
	 * Heading.
	 */
	@FXML
	private Label lblHeading;

	/**
	 * Name.
	 */
	@FXML
	private Label lblName;

	/**
	 * First name.
	 */
	@FXML
	private Label lblFirstName;

	/**
	 * Birthday.
	 */
	@FXML
	private Label lblBirthday;

	/**
	 * Role.
	 */
	@FXML
	private Label lblRole;

	/**
	 * Image.
	 */
	@FXML
	private ImageView imgView;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// headings
		lblHeading.setFont(FontUtils.getDerived(lblHeading.getFont(), FontWeight.BOLD));

	}

	/**
	 * Shows detail data.
	 *
	 * @param theDetailData (null if no data to show)
	 */
	@Override
	public void showDetails(final T theDetailData) {

		if (theDetailData == null) {

			LabelUtils.setText(lblName, null);
			LabelUtils.setText(lblFirstName, null);
			LabelUtils.setText(lblBirthday, null);
			LabelUtils.setText(lblRole, null);

			imgView.setImage(null);

		} else {

			LabelUtils.setText(lblName, theDetailData.getName());
			LabelUtils.setText(lblFirstName, theDetailData.getFirstName());
			LabelUtils.setText(lblBirthday, theDetailData.getBirthday(), null);

			lblRole.setText(
					(theDetailData.getRole() == null) ?
							null :
							theDetailData.getRole().getDisplayText().getValue());

			try {
				if (theDetailData.existsImageFile(Prefs.get(PrefKey.PATHS_IMAGE))) {
					File fleImage = Paths.get(Prefs.get(PrefKey.PATHS_IMAGE), String.format("%s.jpg", theDetailData.getFileName().getValue())).toFile();
					imgView.setImage(new Image(fleImage.toURI().toURL().toString()));
				} else {
					File fleImage = Paths.get(Prefs.get(PrefKey.PATHS_IMAGE), "missing.jpg").toFile();
					if (fleImage.exists()) {
						imgView.setImage(new Image(fleImage.toURI().toURL().toString()));
					} else {
						imgView.setImage(null);
					}
				}
			} catch (Exception e) {
				RefereeManager.logger.throwing(e);
				imgView.setImage(null);
			}


		}

	}

}

/* EOF */

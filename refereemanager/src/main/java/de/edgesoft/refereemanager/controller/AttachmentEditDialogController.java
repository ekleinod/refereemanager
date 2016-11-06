package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import de.edgesoft.refereemanager.utils.Attachment;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller for attachment edit dialog scene.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of refereemanager.
 *
 * refereemanager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * refereemanager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with refereemanager.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.10.0
 * @since 0.10.0
 */
public class AttachmentEditDialogController {

	/**
	 * Filename text field.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtFilename;

	/**
	 * Filename button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnFilename;

	/**
	 * Title text field.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtTitle;

	/**
	 * Paper format portrait radio button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private RadioButton radPortrait;

	/**
	 * Paper format landscape radio button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private RadioButton radLandscape;

	/**
	 * OK button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnOK;

	/**
	 * Cancel button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnCancel;

	/**
	 * Reference to dialog stage.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private Stage dialogStage;

	/**
	 * Current attachment.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private Attachment currentAttachment;

	/**
	 * OK clicked?.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private boolean okClicked;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void initialize() {

		// enable ok button for valid entries only
		btnOK.disableProperty().bind(
				txtFilename.textProperty().isEmpty()
				);

		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));
		btnFilename.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/folder-open.png")));
		
	}

	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void setDialogStage(final Stage theStage) {
        dialogStage = theStage;
    }

	/**
	 * Sets attachment to be edited.
	 *
	 * @param theAttachment edit attachment
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void setAttachment(Attachment theAttachment) {

		Objects.requireNonNull(theAttachment);

        txtFilename.setText(
        		(theAttachment.getFilename() == null) ?
        				null :
        				theAttachment.getFilename().get());

        txtTitle.setText(
        		(theAttachment.getTitle() == null) ?
        				null :
        				theAttachment.getTitle().get());

        radPortrait.setSelected(
        		(theAttachment.getLandscape() == null) ?
        				true :
        				!theAttachment.getLandscape().get());

        radLandscape.setSelected(!radPortrait.isSelected());

        currentAttachment = theAttachment;

    }

	/**
	 * Returns if user clicked ok.
	 *
	 * @return did user click ok?
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public boolean isOkClicked() {
        return okClicked;
    }

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
    private void handleOk() {
        okClicked = false;

    	if (currentAttachment.getFilename() == null) {
    		currentAttachment.setFilename(new SimpleStringProperty());
    	}
        currentAttachment.getFilename().setValue(txtFilename.getText());

    	if (currentAttachment.getTitle() == null) {
    		currentAttachment.setTitle(new SimpleStringProperty());
    	}
        currentAttachment.getTitle().setValue(txtTitle.getText());

    	if (currentAttachment.getLandscape() == null) {
    		currentAttachment.setLandscape(new SimpleBooleanProperty());
    	}
        currentAttachment.getLandscape().setValue(radLandscape.isSelected());

        okClicked = true;
        dialogStage.close();

    }

	/**
	 * Stores non-ok click and closes dialog.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
    private void handleCancel() {
		okClicked = false;
        dialogStage.close();
    }

	/**
	 * Selects file, stores it to text field.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
    private void handleFileSelect() {
		
		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Attachment auswählen");
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("Alle Dateien (*.*)", "*.*")
        		);
        if (!Prefs.get(PrefKey.REFEREE_COMMUNICATION_LAST_ATTACHMENT_PATH).isEmpty()) {
        	Path pthFile = Paths.get(Prefs.get(PrefKey.REFEREE_COMMUNICATION_LAST_ATTACHMENT_PATH));
        	if (pthFile != null) {
        		fileChooser.setInitialDirectory(pthFile.toFile());
        	}
        }

        File file = fileChooser.showOpenDialog(dialogStage);

        if (file != null) {
            txtFilename.setText(file.getPath());
        }

    }

}

/* EOF */

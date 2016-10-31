package de.edgesoft.refereemanager.controller;

import java.util.Map;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the referee communication scene.
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
public class RefereeCommunicationController {

	/**
	 * Textfield title.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtTitle;

	/**
	 * Textfield opening.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtOpening;

	/**
	 * Textarea body.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextArea txtBody;

	/**
	 * Textfield closing.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtClosing;

	/**
	 * Textfield signature.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtSignature;

	/**
	 * Textfield message file.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtMessageFile;

	/**
	 * Button message file select.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnMessageFileSelect;

	/**
	 * Button message file load.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnMessageFileLoad;

	/**
	 * Button message file save.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnMessageFileSave;

	/**
	 * Checkbox email.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkEMail;

	/**
	 * Checkbox letter.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkLetter;

	/**
	 * Send button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnSend;

	/**
	 * Prefs button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnPrefs;

	@FXML
	private TextField txtAttachment1;
	@FXML
	private Button btnAttachmentSelect1;
	@FXML
	private Button btnAttachmentAdd1;
	@FXML
	private Button btnAttachmentDelete1;

	/**
	 * Attachment pane.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private GridPane pneAttachments;
	
	/**
	 * Split pane.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private SplitPane pneSplit;


	/**
	 * Main app controller.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private AppLayoutController appController = null;


	/**
	 * Table view.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private TableView<Referee> tblReferees;

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

		// list
    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("RefereeList");
    	AnchorPane refList = (AnchorPane) pneLoad.getKey();

        // add referee list to split pane
        pneSplit.getItems().add(refList);

        // store referee table reference
        RefereeListController ctlRefList = pneLoad.getValue().getController();
        tblReferees = ctlRefList.getTableView();

		// set divider position
		pneSplit.setDividerPositions(Double.parseDouble(Prefs.get(PrefKey.REFEREE_COMMUNICATION_SPLIT_0)), Double.parseDouble(Prefs.get(PrefKey.REFEREE_COMMUNICATION_SPLIT_1)));

		// if changed, save divider position to preferences
		pneSplit.getDividers().get(0).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(PrefKey.REFEREE_COMMUNICATION_SPLIT_0, Double.toString(newValue.doubleValue()));
		});
		pneSplit.getDividers().get(1).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(PrefKey.REFEREE_COMMUNICATION_SPLIT_1, Double.toString(newValue.doubleValue()));
		});

		// icons
		btnSend.setGraphic(new ImageView(Resources.loadImage("icons/actions/mail-send.png")));
		btnPrefs.setGraphic(new ImageView(Resources.loadImage("icons/actions/configure.png")));
		btnMessageFileSelect.setGraphic(new ImageView(Resources.loadImage("icons/actions/folder-open-16.png")));
		btnMessageFileLoad.setGraphic(new ImageView(Resources.loadImage("icons/actions/document-open.png")));
		btnMessageFileSave.setGraphic(new ImageView(Resources.loadImage("icons/actions/document-save.png")));

		// enabling buttons
		btnSend.disableProperty().bind(tblReferees.getSelectionModel().selectedItemProperty().isNull());
		
	}

	/**
	 * Initializes the controller with things, that cannot be done during {@link #initialize()}.
	 *
	 * @param theAppController app controller
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void initController(final AppLayoutController theAppController) {

		appController = theAppController;

		if (AppModel.getData() != null) {
			tblReferees.setItems(((ContentModel) AppModel.getData().getContent()).getObservableReferees());
			tblReferees.refresh();
		} else {
			tblReferees.setItems(null);
			tblReferees.refresh();
		}
    }

	/**
	 * Send email.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleSend() {
		System.out.println("#handleSend");
	}
	
	/**
	 * Open preferences.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handlePrefs() {
		
    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("PreferencesDialog");
    	AnchorPane preferencesDialog = (AnchorPane) pneLoad.getKey();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Einstellungen");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(appController.getPrimaryStage());

        Scene scene = new Scene(preferencesDialog);
        dialogStage.setScene(scene);

        // initialize controller
        PreferencesDialogController controller = pneLoad.getValue().getController();
        controller.initController(appController, dialogStage, "tabCommunication");

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        appController.setAppTitle();

	}
	
	/**
	 * Message file selection.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleMessageFileSelect() {
		System.out.println("#handleMessageFileSelect");
	}

	/**
	 * Message file load.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleMessageFileLoad() {
		System.out.println("#handleMessageFileLoad");
	}

	/**
	 * Message file save.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleMessageFileSave() {
		System.out.println("#handleMessageFileSave");
	}

	/**
	 * Attachment selection.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleAttachmentSelect() {
		System.out.println("#handleAttachmentSelect");
	}

	/**
	 * Attachment addition.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleAttachmentAdd() {
		System.out.println("#handleAttachmentAdd");
	}

	/**
	 * Attachment deletion.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleAttachmentDelete() {
		System.out.println("#handleAttachmentDelete");
	}

}

/* EOF */

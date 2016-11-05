package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import de.edgesoft.refereemanager.utils.TemplateHelper;
import de.edgesoft.refereemanager.utils.TemplateVariable;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
	 * Textfield subtitle.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtSubtitle;

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
	 * Date picker.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private DatePicker pckDate;

	/**
	 * Textfield message file.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtCommunicationFile;

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
	 * Referee list controller.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private RefereeListController ctlRefList;

	/**
	 * A message variable token.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static final String MESSAGE_VARIABLE_TOKEN = "%s:";


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

        // store referee table controller
        ctlRefList = pneLoad.getValue().getController();
        ctlRefList.setSelectionMode(SelectionMode.MULTIPLE);

		// set divider position
		pneSplit.setDividerPositions(Double.parseDouble(Prefs.get(PrefKey.REFEREE_COMMUNICATION_SPLIT_0)), Double.parseDouble(Prefs.get(PrefKey.REFEREE_COMMUNICATION_SPLIT_1)));

		// if changed, save divider position to preferences
		pneSplit.getDividers().get(0).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(PrefKey.REFEREE_COMMUNICATION_SPLIT_0, Double.toString(newValue.doubleValue()));
		});
		pneSplit.getDividers().get(1).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(PrefKey.REFEREE_COMMUNICATION_SPLIT_1, Double.toString(newValue.doubleValue()));
		});

		// communication file
		txtCommunicationFile.setText(Prefs.get(PrefKey.REFEREE_COMMUNICATION_FILE));
		txtCommunicationFile.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			Prefs.put(PrefKey.REFEREE_COMMUNICATION_FILE, newValue);
		});

		// set date picker date format
		pckDate.setConverter(new StringConverter<LocalDate>() {

			@Override
			public String toString(LocalDate date) {
				if (date == null) {
					return "";
				}
				return DateTimeUtils.formatDate(date);
			}

			@Override
			public LocalDate fromString(String string) {
				String sPattern = DateTimeUtils.DATE_PATTERN;
				DateTimeUtils.setDatePattern("d.M.yyyy");
				LocalDate dteParsed = DateTimeUtils.parseDate(string);
				DateTimeUtils.setDatePattern(sPattern);
				return dteParsed;
			}
		});

		// icons
		btnSend.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/mail-send.png")));
		btnPrefs.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/configure.png")));
		btnMessageFileSelect.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/folder-open.png")));
		btnMessageFileLoad.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/document-open.png")));
		btnMessageFileSave.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/document-save.png")));

		// enabling buttons
		btnSend.disableProperty().bind(
				ctlRefList.getSelectionModel().selectedItemProperty().isNull()
				.or(
						chkEMail.selectedProperty().not()
						.and(chkLetter.selectedProperty().not())
						)
				);
		btnMessageFileLoad.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());
		btnMessageFileSave.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());

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

		if (AppModel.getData() == null) {
			ctlRefList.setItems(null);
		} else {
			ctlRefList.setItems(((ContentModel) AppModel.getData().getContent()).getObservableReferees());
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

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Nachrichtendatei öffnen");
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("Nachrichten-Dateien (*.mmd)", "*.mmd"),
        		new FileChooser.ExtensionFilter("Alle Dateien (*.*)", "*.*")
        		);
        if (!Prefs.get(PrefKey.REFEREE_COMMUNICATION_FILE).isEmpty()) {
        	Path pthFile = Paths.get(Prefs.get(PrefKey.REFEREE_COMMUNICATION_FILE));
        	if ((pthFile != null) && (pthFile.getParent() != null)) {
        		fileChooser.setInitialDirectory(pthFile.getParent().toFile());
        	}
        }

        File file = fileChooser.showOpenDialog(appController.getPrimaryStage());

        if (file != null) {
            txtCommunicationFile.setText(file.getPath());
        }

	}

	/**
	 * Message file load.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleMessageFileLoad() {

		try {

			final List<String> lstText = FileAccess.readFileInList(Paths.get(txtCommunicationFile.getText()));

			List<String> lstBody = new ArrayList<>();
			boolean isBody = false;
			for (String theLine : lstText) {

				if (isBody) {

					lstBody.add(theLine.trim());

				} else {

					for (TemplateVariable theTemplateVariable : TemplateVariable.values()) {

						String sVarToken = String.format(MESSAGE_VARIABLE_TOKEN, theTemplateVariable.value());
						if (theLine.trim().startsWith(sVarToken)) {

							String theLineContent = theLine.trim().substring(sVarToken.length()).trim();

							switch (theTemplateVariable) {
								case ATTACHMENT:
									System.out.println("todo: attachment");
									break;
								case CLOSING:
									txtClosing.setText(theLineContent);
									break;
								case DATE:
									pckDate.setValue(DateTimeUtils.parseDate(theLineContent));
									break;
								case FILENAME:
									System.out.println("todo filename");
									break;
								case OPENING:
									txtOpening.setText(theLineContent);
									break;
								case SIGNATURE:
									txtSignature.setText(theLineContent);
									break;
								case SUBJECT:
									txtTitle.setText(theLineContent);
									break;
								case SUBTITLE:
									txtSubtitle.setText(theLineContent);
									break;

							}
						}

					}

				}

				if (theLine.isEmpty()) {
					isBody = true;
				}
			}

			txtBody.setText(TemplateHelper.toText(lstBody));

		} catch (Exception e) {

	        AlertUtils.createAlert(AlertType.ERROR, appController.getPrimaryStage(),
	        		"Dateifehler",
	        		"Ein Fehler ist beim Laden der Nachricht aufgetreten.",
	        		MessageFormat.format("{0}\nDie Daten wurden nicht geladen.", e.getMessage()))
	        .showAndWait();

		}

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

package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.Attachment;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import de.edgesoft.refereemanager.utils.TemplateHelper;
import de.edgesoft.refereemanager.utils.DocumentDataVariable;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
	 * A message variable token.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static final String MESSAGE_VARIABLE_TOKEN = "%s:";

	/**
	 * Attachment separator.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static final String ATTACHMENT_SEPARATOR = "::";

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
	 * Textfield generated filename.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtFilename;

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
	 * Radio button email.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private RadioButton radEMail;

	/**
	 * Radio button letter.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private RadioButton radLetter;

	/**
	 * Radio button document.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private RadioButton radDocument;

	/**
	 * Toggle group communication kind.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private ToggleGroup grpCommKind;

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

	/**
	 * Attachment add button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnAttachmentAdd;

	/**
	 * Attachment edit button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnAttachmentEdit;

	/**
	 * Attachment delete button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnAttachmentDelete;

	/**
	 * Attachment table view.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableView<Attachment> tblAttachments;

	/**
	 * Split pane.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private SplitPane pneSplit;

	/**
	 * Filename column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<Attachment, String> colFilename;

	/**
	 * Title column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<Attachment, String> colTitle;

	/**
	 * Landscape paper format column.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TableColumn<Attachment, Boolean> colLandscape;


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

		// icons
		btnSend.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/mail-send.png")));
		btnPrefs.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/configure.png")));
		btnMessageFileSelect.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/folder-open.png")));
		btnMessageFileLoad.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/document-open.png")));
		btnMessageFileSave.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/document-save.png")));
		btnAttachmentAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnAttachmentEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnAttachmentDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

		// enabling buttons
		btnSend.disableProperty().bind(
				ctlRefList.getSelectionModel().selectedItemProperty().isNull()
				.or(txtTitle.textProperty().isEmpty())
				.or(txtOpening.textProperty().isEmpty())
				.or(txtBody.textProperty().isEmpty())
				.or(txtClosing.textProperty().isEmpty())
				.or(txtSignature.textProperty().isEmpty())
				);
		btnMessageFileLoad.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());
		btnMessageFileSave.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());
		btnAttachmentEdit.disableProperty().bind(tblAttachments.getSelectionModel().selectedItemProperty().isNull());
		btnAttachmentDelete.disableProperty().bind(tblAttachments.getSelectionModel().selectedItemProperty().isNull());

		// text on send button
		grpCommKind.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
			btnSend.setText((newValue == radEMail) ? "Senden" : "Erzeugen");
		});

		// attachment list
		colFilename.setCellValueFactory(cellData -> cellData.getValue().getFilename());
		colTitle.setCellValueFactory(cellData -> cellData.getValue().getTitle());
		colLandscape.setCellValueFactory(cellData -> cellData.getValue().getLandscape());
		colLandscape.setCellFactory(col -> new CheckBoxTableCell<>());

		Label lblPlaceholder = new Label("Keine Attachments.");
		lblPlaceholder.setWrapText(true);
		tblAttachments.setPlaceholder(lblPlaceholder);

		tblAttachments.setItems(FXCollections.observableList(new ArrayList<>()));

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
	 * Send email or create letter/document.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleSend() {

		Map<String, Object> mapDocData = new HashMap<>();
		for (DocumentDataVariable theTemplateVariable : DocumentDataVariable.values()) {

			switch (theTemplateVariable) {
				case ATTACHMENT:
					tblAttachments.getItems().forEach(attachment -> {
						mapDocData.putIfAbsent(theTemplateVariable.value(), new ArrayList<>());
						((List<Attachment>) mapDocData.get(theTemplateVariable.value())).add(attachment);
					});
					break;
				case BODY:
					mapDocData.put(theTemplateVariable.value(), txtBody.getText());
					break;
				case CLOSING:
					mapDocData.put(theTemplateVariable.value(), txtClosing.getText());
					break;
				case DATE:
					mapDocData.put(theTemplateVariable.value(), LocalDateTime.now());
					break;
				case FILENAME:
					mapDocData.put(theTemplateVariable.value(), txtFilename.getText());
					break;
				case OPENING:
					mapDocData.put(theTemplateVariable.value(), txtOpening.getText());
					break;
				case SIGNATURE:
					mapDocData.put(theTemplateVariable.value(), txtSignature.getText());
					break;
				case SUBTITLE:
					mapDocData.put(theTemplateVariable.value(), txtSubtitle.getText());
					break;
				case SUBJECT:
					mapDocData.put(theTemplateVariable.value(), txtTitle.getText());
					break;
			}
		}

		if (grpCommKind.getSelectedToggle() == radEMail) {

	    	Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, appController.getPrimaryStage(),
	    			"Bestätigung E-Mail senden",
	    			"E-Mails versenden?",
	    			"Je nach Empfängeranzahl kann das etwas dauern.");

	        alert.showAndWait()
	        		.filter(response -> response == ButtonType.OK)
	        		.ifPresent(response -> {
	        			sendEMail(mapDocData);
	        		});
		} else {
			System.out.println("#handleCreate");
		}

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

    	Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, appController.getPrimaryStage(),
    			"Bestätigung Nachrichtendatei laden",
    			"Soll die Nachrichtendatei geladen werden?",
    			"Alle Eingaben werden dabei gelöscht bzw. überschrieben.");

        alert.showAndWait()
        		.filter(response -> response == ButtonType.OK)
        		.ifPresent(response -> {

        			try {

        				final List<String> lstText = FileAccess.readFileInList(Paths.get(txtCommunicationFile.getText()));

        				tblAttachments.getItems().clear();
        				txtBody.setText(null);
        				txtClosing.setText(null);
        				txtFilename.setText(null);
        				txtOpening.setText(null);
        				txtSignature.setText(null);
        				txtTitle.setText(null);
        				txtSubtitle.setText(null);

        				List<String> lstBody = new ArrayList<>();
        				boolean isBody = false;
        				for (String theLine : lstText) {

        					if (isBody) {

        						lstBody.add(theLine.trim());

        					} else {

        						for (DocumentDataVariable theTemplateVariable : DocumentDataVariable.values()) {

        							String sVarToken = String.format(MESSAGE_VARIABLE_TOKEN, theTemplateVariable.value());
        							if (theLine.trim().startsWith(sVarToken)) {

        								String theLineContent = theLine.trim().substring(sVarToken.length()).trim();

        								switch (theTemplateVariable) {
	        								case ATTACHMENT:
	        									String[] arrAttachmentParts = theLineContent.split(ATTACHMENT_SEPARATOR);

	        									Attachment attNew = new Attachment();

	        									attNew.setFilename(new SimpleStringProperty(arrAttachmentParts[0].trim()));

	        									if (arrAttachmentParts.length > 1) {
	        										attNew.setTitle(new SimpleStringProperty(arrAttachmentParts[1].trim()));
	        									}

	        									if (arrAttachmentParts.length > 2) {
	        										attNew.setLandscape(new SimpleBooleanProperty(Boolean.valueOf(arrAttachmentParts[2].trim())));
	        									}

	        									tblAttachments.getItems().add(attNew);
	        									break;
	        								case BODY:
	        									// multiple lines without explicit token
	        									break;
	        								case CLOSING:
	        									txtClosing.setText(theLineContent);
	        									break;
	        								case DATE:
	        									// not used for now, always "now"
	        									break;
	        								case FILENAME:
	        									txtFilename.setText(theLineContent);
	        									break;
	        								case OPENING:
	        									txtOpening.setText(theLineContent);
	        									break;
	        								case SIGNATURE:
	        									txtSignature.setText(theLineContent);
	        									break;
	        								case SUBTITLE:
	        									txtSubtitle.setText(theLineContent);
	        									break;
	        								case SUBJECT:
	        									txtTitle.setText(theLineContent);
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

        				e.printStackTrace();

        				AlertUtils.createAlert(AlertType.ERROR, appController.getPrimaryStage(),
        						"Dateifehler",
        						"Ein Fehler ist beim Laden der Nachricht aufgetreten.",
        						MessageFormat.format("{0}\nDie Daten wurden nicht geladen.", e.getMessage()))
        				.showAndWait();

        			}

        		});

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
	 * Attachment addition.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleAttachmentAdd() {

		Attachment newAttachment = new Attachment();
		if (showAttachmentEditDialog(newAttachment)) {
			tblAttachments.getItems().add(newAttachment);
			tblAttachments.getSelectionModel().select(newAttachment);
		}

	}

	/**
	 * Attachment edit.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleAttachmentEdit() {

		Attachment editAttachment = tblAttachments.getSelectionModel().getSelectedItem();

	    if (editAttachment != null) {
			showAttachmentEditDialog(editAttachment);
	    }

	}

	/**
	 * Attachment deletion.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleAttachmentDelete() {

		Attachment selectedAttachment = tblAttachments.getSelectionModel().getSelectedItem();

	    if (selectedAttachment != null) {

	    	Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, appController.getPrimaryStage(),
	    			"Bestätigung Attachment löschen",
	    			"Soll das ausgewählte Attachment gelöscht werden?",
	    			null);

	        alert.showAndWait()
	        		.filter(response -> response == ButtonType.OK)
	        		.ifPresent(response -> {
	        			tblAttachments.getItems().remove(selectedAttachment);
	        			});

	    }

	}

	/**
	 * Opens the attachment edit dialog.
	 *
	 * @param theAttachment the attachment to be edited
	 * @return true if the user clicked OK, false otherwise.
	 *
	 * @version 6.0.0
	 * @since 6.0.0
	 */
	private boolean showAttachmentEditDialog(Attachment theAttachment) {

    	Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("AttachmentEditDialog");
    	AnchorPane editDialog = (AnchorPane) pneLoad.getKey();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(appController.getPrimaryStage());
        dialogStage.setTitle("Dateianhang editieren");

        Scene scene = new Scene(editDialog);
        dialogStage.setScene(scene);

        // Set the event into the controller.
        AttachmentEditDialogController editController = pneLoad.getValue().getController();
        editController.setDialogStage(dialogStage);
        editController.setAttachment(theAttachment);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return editController.isOkClicked();

	}

	/**
	 * Sends mails.
	 *
	 * If there are multiple recipients, {@link Transport#send(Message, String, String)} sends
	 * all mails in a transaction, meaning if one fails, all fail.
	 *
	 * Thus, every mail is sent individually.
	 * Maybe I'm doing something wrong there, in that case the code could be
	 * changes to including all recipients as BCC.
	 *
	 * @todo log in string
	 *
	 * @param theDocData document data
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private void sendEMail(final Map<String, Object> theDocData) {

		StringBuilder sbProtocol = new StringBuilder();

		LocalTime tmeStart = LocalTime.now();
		sbProtocol.append(MessageFormat.format("Start: {0}\n", DateTimeUtils.formatAsTime(LocalDateTime.now())));

		try {

			// load email template
			Path pathTemplateFile = Paths.get(Prefs.get(PrefKey.PATH_TEMPLATES), Prefs.get(PrefKey.COMMUNICATION_TEMPLATE_EMAIL));

			Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
			cfg.setDirectoryForTemplateLoading(pathTemplateFile.getParent().toFile());
			cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);

			Template tplEMail = cfg.getTemplate(pathTemplateFile.getFileName().toString());

			// send email
			Properties mailProps = new Properties();
			mailProps.setProperty("mail.smtp.host", Prefs.get(PrefKey.COMMUNICATION_SMTP_HOST));
			mailProps.setProperty("mail.smtp.auth", "true");

			Session session = Session.getInstance(mailProps, new Authenticator() {
			      @Override protected PasswordAuthentication getPasswordAuthentication() {
			          return new PasswordAuthentication(Prefs.get(PrefKey.COMMUNICATION_SMTP_USERNAME), Prefs.get(PrefKey.COMMUNICATION_SMTP_PASSWORD));
			        }
			      });

			for (Referee referee : new FilteredList<>(ctlRefList.getSelectionModel().getSelectedItems(), PersonModel.HAS_EMAIL)) {

				sbProtocol.append(MessageFormat.format("Mail an: {0}\n", referee.getDisplayTitle().get()));

				// fill variables in generated content (todo)
				Map<String, Object> mapFilled = theDocData;

				try {

					Message msgMail = new MimeMessage(session);
					msgMail.setFrom(new InternetAddress(Prefs.get(PrefKey.COMMUNICATION_FROM_EMAIL), Prefs.get(PrefKey.COMMUNICATION_FROM_NAME), StandardCharsets.UTF_8.name()));

					msgMail.setSentDate(DateTimeUtils.toDate((LocalDateTime) mapFilled.get(DocumentDataVariable.DATE.value())));
					msgMail.setSubject((String) mapFilled.get(DocumentDataVariable.SUBJECT.value()));

					EMail theEMail = referee.getPrimaryEMail();
					msgMail.setRecipient(RecipientType.TO, new InternetAddress(theEMail.getEMail().get(), referee.getFullName().get(), StandardCharsets.UTF_8.name()));

					MimeMultipart msgContent = new MimeMultipart();

					try (StringWriter wrtContent = new StringWriter()) {
						tplEMail.process(mapFilled, wrtContent);
						MimeBodyPart text = new MimeBodyPart();
						text.setText(wrtContent.toString());
						msgContent.addBodyPart(text);
					}

					msgMail.setContent(msgContent);

					for (Attachment theAttachment : (List<Attachment>) mapFilled.get(DocumentDataVariable.ATTACHMENT.value())) {
						Path attachment = Paths.get(theAttachment.getFilename().get());

						BodyPart bpAttachment = new MimeBodyPart();
						bpAttachment.setDataHandler(new DataHandler(new FileDataSource(attachment.toFile())));
						bpAttachment.setFileName(attachment.getFileName().toString());

						msgContent.addBodyPart(bpAttachment);

						sbProtocol.append(MessageFormat.format("\tAttachment: {0}\n", bpAttachment.getFileName()));
					}

//					Transport.send(msgMail);
					sbProtocol.append(MessageFormat.format("\terfolgreich gesendet. ({0})\n", LocalDateTime.now()));

				} catch (SendFailedException e) {
					if (e.getInvalidAddresses() != null) {
						Arrays.asList(e.getInvalidAddresses()).forEach(adr -> sbProtocol.append(MessageFormat.format("\tnicht gesendet, fehlerhafte Adresse: {0} ({1}, {2})\n", adr.toString(), LocalDateTime.now(), e.getMessage())));
					}
					if (e.getValidUnsentAddresses() != null) {
						Arrays.asList(e.getValidUnsentAddresses()).forEach(adr -> sbProtocol.append(MessageFormat.format("\tnicht gesendet, valide Adresse: {0} ({1}, {2})\n", adr.toString(), LocalDateTime.now(), e.getMessage())));
					}
					if (e.getValidSentAddresses() != null) {
						Arrays.asList(e.getValidSentAddresses()).forEach(adr -> sbProtocol.append(MessageFormat.format("\tgesendet, valide Adresse: {0} ({1}, {2})\n", adr.toString(), LocalDateTime.now(), e.getMessage())));
					}
				}

			}

		} catch (MessagingException | IOException | TemplateException e) {
			e.printStackTrace();
		}

		sbProtocol.append(MessageFormat.format("Ende: {0}\n", DateTimeUtils.formatAsTime(LocalDateTime.now())));
		Duration sendingTime = Duration.between(tmeStart, LocalTime.now());
		sbProtocol.append(MessageFormat.format("Dauer: {0}\n", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.ofSecondOfDay(sendingTime.getSeconds()))));

		RefereeManager.logger.info(sbProtocol.toString());

	}

}

/* EOF */

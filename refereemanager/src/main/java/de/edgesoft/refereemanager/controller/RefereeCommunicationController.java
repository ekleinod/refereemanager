package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
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
import java.util.Objects;
import java.util.Optional;
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
import de.edgesoft.refereemanager.utils.DocumentDataVariable;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import de.edgesoft.refereemanager.utils.TemplateHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
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
import javafx.stage.StageStyle;

/**
 * Controller for the referee communication scene.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
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
	 * Label opening.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Label lblOpening;

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
	 * Label closing.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Label lblClosing;

	/**
	 * Textfield closing.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtClosing;

	/**
	 * Label signature.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Label lblSignature;

	/**
	 * Textfield signature.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtSignature;

	/**
	 * Label generated filename.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Label lblFilename;

	/**
	 * Textfield generated filename.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtFilename;

	/**
	 * Textfield date.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtDate;

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
	 * Label attachments.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Label lblAttachments;

	/**
	 * Buttonbar attachments.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private ButtonBar barAttachments;

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
	 * Label output.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Label lblCommunicationOutput;

	/**
	 * Separator output.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Separator sepCommunicationOutput;

	/**
	 * Label output path.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Label lblCommunicationOutputPath;

	/**
	 * Text field output path.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtCommunicationOutputPath;

	/**
	 * Label options.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Label lblOptions;

	/**
	 * Text field options.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtOptions;

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
	 * @version 0.12.0
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

		// output path
		txtCommunicationOutputPath.setText(Prefs.get(PrefKey.REFEREE_COMMUNICATION_OUTPUT_PATH));
		txtCommunicationOutputPath.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			Prefs.put(PrefKey.REFEREE_COMMUNICATION_OUTPUT_PATH, newValue);
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
				.or(txtBody.textProperty().isEmpty())
				.or(
						radDocument.selectedProperty().not()
						.and(
								txtOpening.textProperty().isEmpty()
								.or(txtClosing.textProperty().isEmpty())
								.or(txtSignature.textProperty().isEmpty())
								)
						)
				);
		btnMessageFileLoad.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());
		btnMessageFileSave.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());
		btnAttachmentEdit.disableProperty().bind(tblAttachments.getSelectionModel().selectedItemProperty().isNull());
		btnAttachmentDelete.disableProperty().bind(tblAttachments.getSelectionModel().selectedItemProperty().isNull());

		// text on send button
		grpCommKind.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
			btnSend.setText((newValue == radEMail) ? "Senden" : "Erzeugen");
		});

		// visible for emails and letters
		lblOpening.visibleProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		lblOpening.managedProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		txtOpening.visibleProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		txtOpening.managedProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		lblClosing.visibleProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		lblClosing.managedProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		txtClosing.visibleProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		txtClosing.managedProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		lblSignature.visibleProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		lblSignature.managedProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		txtSignature.visibleProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		txtSignature.managedProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));

		lblAttachments.visibleProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		barAttachments.visibleProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));
		tblAttachments.visibleProperty().bind(radEMail.selectedProperty().or(radLetter.selectedProperty()));

		// visible for letters and documents
		lblFilename.visibleProperty().bind(radLetter.selectedProperty().or(radDocument.selectedProperty()));
		lblFilename.managedProperty().bind(radLetter.selectedProperty().or(radDocument.selectedProperty()));
		txtFilename.visibleProperty().bind(radLetter.selectedProperty().or(radDocument.selectedProperty()));
		txtFilename.managedProperty().bind(radLetter.selectedProperty().or(radDocument.selectedProperty()));
		lblOptions.visibleProperty().bind(radLetter.selectedProperty().or(radDocument.selectedProperty()));
		lblOptions.managedProperty().bind(radLetter.selectedProperty().or(radDocument.selectedProperty()));
		txtOptions.visibleProperty().bind(radLetter.selectedProperty().or(radDocument.selectedProperty()));
		txtOptions.managedProperty().bind(radLetter.selectedProperty().or(radDocument.selectedProperty()));

		// visible for documents
		lblCommunicationOutputPath.visibleProperty().bind(radDocument.selectedProperty());
		lblCommunicationOutputPath.managedProperty().bind(radDocument.selectedProperty());
		txtCommunicationOutputPath.visibleProperty().bind(radDocument.selectedProperty());
		txtCommunicationOutputPath.managedProperty().bind(radDocument.selectedProperty());

		// visible for specific fields
		lblCommunicationOutput.visibleProperty().bind(lblCommunicationOutputPath.visibleProperty().or(lblOptions.visibleProperty()));
		lblCommunicationOutput.managedProperty().bind(lblCommunicationOutputPath.managedProperty().or(lblOptions.managedProperty()));
		sepCommunicationOutput.visibleProperty().bind(lblCommunicationOutputPath.visibleProperty().or(lblOptions.visibleProperty()));
		sepCommunicationOutput.managedProperty().bind(lblCommunicationOutputPath.managedProperty().or(lblOptions.managedProperty()));

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
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleSendCreate() {

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
					if ((txtBody.getText() != null) && !txtBody.getText().trim().isEmpty()) {
						mapDocData.put(theTemplateVariable.value(), txtBody.getText().trim());
					}
					break;
				case CLOSING:
					if ((txtClosing.getText() != null) && !txtClosing.getText().trim().isEmpty()) {
						mapDocData.put(theTemplateVariable.value(), txtClosing.getText().trim());
					}
					break;
				case DATE:
					if ((txtDate.getText() != null) && !txtDate.getText().trim().isEmpty()) {
						mapDocData.put(theTemplateVariable.value(), txtDate.getText().trim());
					} else {
						mapDocData.put(theTemplateVariable.value(), LocalDateTime.now());
					}
					break;
				case FILENAME:
					if ((txtFilename.getText() != null) && !txtFilename.getText().trim().isEmpty()) {
						mapDocData.put(theTemplateVariable.value(), txtFilename.getText().trim());
					}
					break;
				case OPENING:
					if ((txtOpening.getText() != null) && !txtOpening.getText().trim().isEmpty()) {
						mapDocData.put(theTemplateVariable.value(), txtOpening.getText().trim());
					}
					break;
				case OPTIONS:
					if ((txtOptions.getText() != null) && !txtOptions.getText().trim().isEmpty()) {
						mapDocData.put(theTemplateVariable.value(), txtOptions.getText().trim());
					}
					break;
				case SIGNATURE:
					if ((txtSignature.getText() != null) && !txtSignature.getText().trim().isEmpty()) {
						mapDocData.put(theTemplateVariable.value(), txtSignature.getText().trim());
					}
					break;
				case SUBTITLE:
					if ((txtSubtitle.getText() != null) && !txtSubtitle.getText().trim().isEmpty()) {
						mapDocData.put(theTemplateVariable.value(), txtSubtitle.getText().trim());
					}
					break;
				case SUBJECT:
					if ((txtTitle.getText() != null) && !txtTitle.getText().trim().isEmpty()) {
						mapDocData.put(theTemplateVariable.value(), txtTitle.getText().trim());
					}
					break;
			}
		}

		Configuration tplConfig = new Configuration(Configuration.VERSION_2_3_25);
		tplConfig.setDefaultEncoding(StandardCharsets.UTF_8.name());
		tplConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		tplConfig.setLogTemplateExceptions(false);

		if (grpCommKind.getSelectedToggle() == radEMail) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, appController.getPrimaryStage(),
					"Bestätigung E-Mail senden",
					"E-Mails versenden?",
					"Je nach Empfängeranzahl kann das etwas dauern.");

			final BooleanProperty doSend = new SimpleBooleanProperty(false);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						doSend.set(true);
					});

			if (doSend.get()) {
				sendEMail(mapDocData, tplConfig);
			}

		} else if (grpCommKind.getSelectedToggle() == radDocument) {
			createDocument(mapDocData, tplConfig);
		} else {
			AlertUtils.createAlert(AlertType.ERROR, appController.getPrimaryStage(),
					"Unbekannte Aktion",
					MessageFormat.format("Unbekannte Aktion ''{0}''.", ((RadioButton) grpCommKind.getSelectedToggle()).getText()),
					null)
			.showAndWait();
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
		controller.initController(appController, dialogStage,
				(radEMail.isSelected()) ? "tabEMail" :
					(radLetter.isSelected()) ? "tabEMail" : "tabDocuments");

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
	 * @version 0.12.0
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
				txtDate.setText(null);
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
										txtDate.setText(theLineContent);
										break;
									case FILENAME:
										txtFilename.setText(theLineContent);
										break;
									case OPENING:
										txtOpening.setText(theLineContent);
										break;
									case OPTIONS:
										txtOptions.setText(theLineContent);
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
	 * @param theDocData document data
	 * @param theConfig template configuration
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	private void sendEMail(final Map<String, Object> theDocData, Configuration theConfig) {

		Objects.requireNonNull(theDocData, "document data must not be null");
		Objects.requireNonNull(theConfig, "template configuration must not be null");

		try (StringWriter wrtProtocol = new StringWriter()) {

			RefereeManager.addAppender(wrtProtocol, "sendEMail");

			LocalTime tmeStart = LocalTime.now();
			RefereeManager.logger.info("Start Mailsenden.");

			Task<Map<String, Integer>> taskSend = new Task<Map<String,Integer>>() {

				/** Main execution method. */
				@Override
				protected Map<String, Integer> call() throws InterruptedException {

					int iSuccess = 0;
					int iError = 0;

					try {

						// load email template
						updateMessage("Lade Mail-Template.");
						Path pathTemplateFile = Paths.get(Prefs.get(PrefKey.TEMPLATE_PATH), Prefs.get(PrefKey.EMAIL_TEMPLATE_EMAIL));

						theConfig.setDirectoryForTemplateLoading(pathTemplateFile.getParent().toFile());
						Template tplEMail = theConfig.getTemplate(pathTemplateFile.getFileName().toString());

						// send email
						Properties mailProps = new Properties();
						mailProps.setProperty("mail.smtp.host", Prefs.get(PrefKey.EMAIL_SMTP_HOST));
						mailProps.setProperty("mail.smtp.auth", "true");

						Session session = Session.getInstance(mailProps, new Authenticator() {
									@Override
									protected PasswordAuthentication getPasswordAuthentication() {
											return new PasswordAuthentication(Prefs.get(PrefKey.EMAIL_SMTP_USERNAME), Prefs.get(PrefKey.EMAIL_SMTP_PASSWORD));
									}
						});

						FilteredList<Referee> lstReferees = new FilteredList<>(ctlRefList.getSelectionModel().getSelectedItems(), PersonModel.HAS_EMAIL);
						int iCount = lstReferees.size();
						for (Referee referee : lstReferees) {

							RefereeManager.logger.info(MessageFormat.format("Mail an ''{0}''.", referee.getDisplayTitle().get()));
							updateMessage(MessageFormat.format("Mail an ''{0}''.", referee.getDisplayTitle().get()));

							// fill variables in generated content (todo)
							Map<String, Object> mapFilled = fillDocumentData(theDocData, theConfig);

							try {

								Message msgMail = new MimeMessage(session);
								msgMail.setFrom(new InternetAddress(Prefs.get(PrefKey.EMAIL_FROM_EMAIL), Prefs.get(PrefKey.EMAIL_FROM_NAME), StandardCharsets.UTF_8.name()));

								if (mapFilled.get(DocumentDataVariable.DATE.value()) instanceof LocalDateTime) {
									msgMail.setSentDate(DateTimeUtils.toDate((LocalDateTime) mapFilled.get(DocumentDataVariable.DATE.value())));
								} else {
									msgMail.setSentDate(DateTimeUtils.toDate(DateTimeUtils.fromString((String) mapFilled.get(DocumentDataVariable.DATE.value()))));
								}

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

								if (mapFilled.containsKey(DocumentDataVariable.ATTACHMENT.value())) {
									for (Attachment theAttachment : (List<Attachment>) mapFilled.get(DocumentDataVariable.ATTACHMENT.value())) {
										Path attachment = Paths.get(theAttachment.getFilename().get());

										BodyPart bpAttachment = new MimeBodyPart();
										bpAttachment.setDataHandler(new DataHandler(new FileDataSource(attachment.toFile())));
										bpAttachment.setFileName(attachment.getFileName().toString());

										msgContent.addBodyPart(bpAttachment);

										RefereeManager.logger.info(MessageFormat.format("Attachment: {0}", bpAttachment.getFileName()));
									}
								}

//								Transport.send(msgMail);
								Thread.sleep(500);
								RefereeManager.logger.info("gesendet.");
								iSuccess++;

							} catch (SendFailedException e) {
								if (e.getInvalidAddresses() != null) {
									Arrays.asList(e.getInvalidAddresses()).forEach(adr ->
											RefereeManager.logger.error(MessageFormat.format("nicht gesendet, fehlerhafte Adresse: {0} ({1})", adr.toString(), e.getMessage())));
								}
								if (e.getValidUnsentAddresses() != null) {
									Arrays.asList(e.getValidUnsentAddresses()).forEach(adr ->
											RefereeManager.logger.error(MessageFormat.format("nicht gesendet, valide Adresse: {0} ({1})", adr.toString(), e.getMessage())));
								}
								if (e.getValidSentAddresses() != null) {
									Arrays.asList(e.getValidSentAddresses()).forEach(adr ->
											RefereeManager.logger.error(MessageFormat.format("gesendet, valide Adresse: {0} ({1})", adr.toString(), e.getMessage())));
								}
								iError++;
							}

							updateProgress(iError + iSuccess, iCount);

						}

					} catch (MessagingException | IOException | TemplateException e) {
						RefereeManager.logger.error(e);
						e.printStackTrace();
						iError++;
					}

					Map<String, Integer> mapReturn = new HashMap<>();
					mapReturn.put("success", iSuccess);
					mapReturn.put("error", iError);

					return mapReturn;
				}
			};

            // progress dialog
			Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("ProgressDialog");

			Stage dialogStage = new Stage(StageStyle.UTILITY);
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.setResizable(false);
			dialogStage.setScene(new Scene(pneLoad.getKey()));

			ProgressDialogController controller = pneLoad.getValue().getController();
			controller.initController("Mail senden", taskSend);

            // task succeeded - show results
            taskSend.setOnSucceeded(event -> {
                dialogStage.close();

    			Map<String, Integer> mapResult = taskSend.getValue();

    			RefereeManager.logger.info("Ende Mailsenden.");
    			Duration sendingTime = Duration.between(tmeStart, LocalTime.now());
    			RefereeManager.logger.info(MessageFormat.format("Dauer: {0}", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.ofSecondOfDay(sendingTime.getSeconds()))));

    			Alert alert = AlertUtils.createExpandableAlert((mapResult.get("error") > 0) ? AlertType.ERROR : AlertType.INFORMATION, appController.getPrimaryStage(),
    					"E-Mails versenden",
    					MessageFormat.format("Versand {0,choice,0#erfolgreich|1#fehlerhaft|1<fehlerhaft}", mapResult.get("error")),
    					MessageFormat.format("{0,choice,0#Keine E-Mails|1#Eine E-Mail|1<{0,number,integer} E-Mails} wurden versendet, es {1,choice,0#traten keine|1#trat ein|1<traten {1,number,integer}} Fehler auf.",
    							mapResult.get("success"), mapResult.get("error")),
    					"Details:",
    					wrtProtocol.toString());

    			alert.showAndWait();
            });

            dialogStage.show();

            Thread thread = new Thread(taskSend);
            thread.start();

		} catch (IOException e) {
			RefereeManager.logger.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * Creates document.
	 *
	 * @param theDocData document data
	 * @param theConfig template configuration
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	private void createDocument(final Map<String, Object> theDocData, Configuration theConfig) {

		Objects.requireNonNull(theDocData, "document data must not be null");
		Objects.requireNonNull(theConfig, "template configuration must not be null");


		try (StringWriter wrtProtocol = new StringWriter()) {

			RefereeManager.addAppender(wrtProtocol, "createDocument");

			LocalTime tmeStart = LocalTime.now();
			RefereeManager.logger.info("Start Dokumenterzeugung.");

			Optional<String> sCreated = Optional.empty();

			try {

				// fill variables in generated content (todo)
				Map<String, Object> mapFilled = fillDocumentData(theDocData, theConfig);

				// load document template
				Path pathTemplateFile = Paths.get(Prefs.get(PrefKey.TEMPLATE_PATH), Prefs.get(PrefKey.DOCUMENTS_TEMPLATE_DOCUMENT));
				RefereeManager.logger.info(MessageFormat.format("Lade Dokument-Template ''{0}''.", pathTemplateFile.toAbsolutePath().toString()));
				theConfig.setDirectoryForTemplateLoading(pathTemplateFile.getParent().toFile());
				Template tplDocument = theConfig.getTemplate(pathTemplateFile.getFileName().toString());

				// fill document template
				Path pathOutFile = Paths.get(Prefs.get(PrefKey.REFEREE_COMMUNICATION_OUTPUT_PATH), (String) mapFilled.get(DocumentDataVariable.FILENAME.value()));
				try (StringWriter wrtContent = new StringWriter()) {
					tplDocument.process(mapFilled, wrtContent);
					FileAccess.writeFile(pathOutFile, wrtContent.toString());
				}
				sCreated = Optional.of(pathOutFile.toAbsolutePath().toString());

			} catch (IOException | TemplateException e) {
				RefereeManager.logger.error(e);
				e.printStackTrace();
			}

			RefereeManager.logger.info("Ende Dokumenterzeugung.");
			Duration sendingTime = Duration.between(tmeStart, LocalTime.now());
			RefereeManager.logger.info(MessageFormat.format("Dauer: {0}", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.ofSecondOfDay(sendingTime.getSeconds()))));

			Alert alert = AlertUtils.createExpandableAlert(AlertType.INFORMATION, appController.getPrimaryStage(),
					"Dokument erzeugen",
					sCreated.isPresent() ? MessageFormat.format("Dokument ''{0}'' erzeugt", sCreated.get()) : "Kein Dokument erzeugt",
							null,
							"Details:",
							wrtProtocol.toString());

			alert.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Fills document data.
	 *
	 * @param theDocData document data
	 * @return filled document data
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	private Map<String, Object> fillDocumentData(final Map<String, Object> theDocData, Configuration theConfig) {
		Map<String, Object> mapReturn = new HashMap<>();

		Map<String, Object> mapData = new HashMap<>();
		mapData.put("today", DateTimeUtils.toDate(LocalDateTime.now()));
		mapData.put("documentdata", theDocData);
		mapData.put("refdata", AppModel.getData());
		mapData.put("refs", ctlRefList.getSelectionModel().getSelectedItems());

		theDocData.forEach((key, value) -> {
			if (value instanceof String) {
				String sFilled = (String) value;
				try {
					Template tplTemp = new Template(key, new StringReader(sFilled), theConfig);
					try (StringWriter wrtContent = new StringWriter()) {
						tplTemp.process(mapData, wrtContent);
						sFilled = wrtContent.toString();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				mapReturn.put(key, sFilled);
			} else {
				mapReturn.put(key, value);
			}
		});

		return mapReturn;
	}

}

/* EOF */

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
import java.util.stream.Collectors;

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
import de.edgesoft.refereemanager.controller.datatables.DataTableRefereesController;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.Attachment;
import de.edgesoft.refereemanager.utils.DocumentDataVariable;
import de.edgesoft.refereemanager.utils.MailUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controller for the referee communication scene.
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
 * @version 0.14.0
 * @since 0.10.0
 */
public class RefereeCommunicationController {

	/**
	 * A message variable token.
	 */
	public static final String MESSAGE_VARIABLE_TOKEN = "%s:";

	/**
	 * Attachment separator.
	 */
	public static final String ATTACHMENT_SEPARATOR = "::";


	/**
	 * Split pane.
	 */
	@FXML
	private SplitPane pneSplit;


	/**
	 * Send button.
	 */
	@FXML
	private Button btnSend;

	/**
	 * Prefs button.
	 */
	@FXML
	private Button btnPrefs;

	/**
	 * Label title.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblTitle;

	/**
	 * Textfield title.
	 */
	@FXML
	private TextField txtTitle;

	/**
	 * Label subtitle.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblSubtitle;

	/**
	 * Textfield subtitle.
	 */
	@FXML
	private TextField txtSubtitle;

	/**
	 * Label opening.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblOpening;

	/**
	 * Textfield opening.
	 */
	@FXML
	private TextField txtOpening;

	/**
	 * Textarea body.
	 */
	@FXML
	private TextArea txtBody;

	/**
	 * Label closing.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblClosing;

	/**
	 * Textfield closing.
	 */
	@FXML
	private TextField txtClosing;

	/**
	 * Label signature.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblSignature;

	/**
	 * Textfield signature.
	 */
	@FXML
	private TextField txtSignature;

	/**
	 * Label date.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblDate;

	/**
	 * Textfield date.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtDate;

	/**
	 * Label filename.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblFilename;

	/**
	 * Textfield filename.
	 */
	@FXML
	private TextField txtFilename;


	/**
	 * Label attachments.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblAttachments;

	/**
	 * Buttonbar attachments.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private ButtonBar barAttachments;

	/**
	 * Attachment add button.
	 */
	@FXML
	private Button btnAttachmentAdd;

	/**
	 * Attachment edit button.
	 */
	@FXML
	private Button btnAttachmentEdit;

	/**
	 * Attachment delete button.
	 */
	@FXML
	private Button btnAttachmentDelete;

	/**
	 * Attachment table view.
	 */
	@FXML
	private TableView<Attachment> tblAttachments;

	/**
	 * Filename column.
	 */
	@FXML
	private TableColumn<Attachment, String> colFilename;

	/**
	 * Title column.
	 */
	@FXML
	private TableColumn<Attachment, String> colTitle;

	/**
	 * Landscape paper format column.
	 */
	@FXML
	private TableColumn<Attachment, Boolean> colLandscape;


	/**
	 * Toggle group communication kind.
	 */
	@FXML
	private ToggleGroup grpCommKind;

	/**
	 * Radio button email.
	 */
	@FXML
	private RadioButton radEMails;

	/**
	 * Radio button letter.
	 */
	@FXML
	private RadioButton radLetters;

	/**
	 * Radio button texts.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private RadioButton radDocuments;

	/**
	 * Radio button texts.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private RadioButton radTexts;

	/**
	 * Radio button document.
	 */
	@FXML
	private RadioButton radDocument;

	/**
	 * Radio button text.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private RadioButton radText;


	/**
	 * Textfield message file.
	 */
	@FXML
	private TextField txtCommunicationFile;

	/**
	 * Button message file select.
	 */
	@FXML
	private Button btnMessageFileSelect;

	/**
	 * Button message file load.
	 */
	@FXML
	private Button btnMessageFileLoad;

	/**
	 * Button message file save.
	 */
	@FXML
	private Button btnMessageFileSave;


	/**
	 * Label output.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblCommunicationOutput;

	/**
	 * Separator output.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Separator sepCommunicationOutput;

	/**
	 * Label output path.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblCommunicationOutputPath;

	/**
	 * Text field output path.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtCommunicationOutputPath;

	/**
	 * Label options.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblOptions;

	/**
	 * Text field options.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtOptions;


	/**
	 * Label other.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblOther;

	/**
	 * Separator other.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Separator sepOther;

	/**
	 * Label test mail.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Label lblTestMail;

	/**
	 * Checkbox test mail.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private CheckBox chkTestMail;


	/**
	 * Main app controller.
	 */
	private AppLayoutController appController = null;


	/**
	 * Referee list controller.
	 */
	private DataTableRefereesController ctlRefList;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// list
		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("datatables/DataTableReferees");

		// add referee list to split pane
		pneSplit.getItems().add(pneLoad.getKey());

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
				ctlRefList.selectedItemProperty().isNull()
				.or(txtBody.textProperty().isEmpty())
				.or(
						radEMails.selectedProperty().or(radLetters.selectedProperty())
						.and(
								txtOpening.textProperty().isEmpty()
								.or(txtClosing.textProperty().isEmpty())
								.or(txtSignature.textProperty().isEmpty())
						)
				)
				.or(
						radEMails.selectedProperty().or(radLetters.selectedProperty()).or(radDocuments.selectedProperty()).or(radDocument.selectedProperty())
						.and(
								txtTitle.textProperty().isEmpty()
						)
				)
				.or(
						radTexts.selectedProperty().or(radDocument.selectedProperty()).or(radText.selectedProperty())
						.and(
								txtFilename.textProperty().isEmpty()
						)
				)
		);
		btnMessageFileLoad.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());
		btnMessageFileSave.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());
		btnAttachmentEdit.disableProperty().bind(tblAttachments.getSelectionModel().selectedItemProperty().isNull());
		btnAttachmentDelete.disableProperty().bind(tblAttachments.getSelectionModel().selectedItemProperty().isNull());

		// text on send button
		grpCommKind.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
			btnSend.setText((newValue == radEMails) ? "Senden" : "Erzeugen");
		});

		// visible for emails
		ObservableBooleanValue isEmail = radEMails.selectedProperty();
		lblOther.visibleProperty().bind(isEmail);
		lblOther.managedProperty().bind(isEmail);
		sepOther.visibleProperty().bind(isEmail);
		sepOther.managedProperty().bind(isEmail);
		lblTestMail.visibleProperty().bind(isEmail);
		lblTestMail.managedProperty().bind(isEmail);
		chkTestMail.visibleProperty().bind(isEmail);
		chkTestMail.managedProperty().bind(isEmail);

		// visible for emails, letters, and documents
		ObservableBooleanValue isEmailOrLetterOrDocument = radEMails.selectedProperty().or(radLetters.selectedProperty()).or(radDocuments.selectedProperty()).or(radDocument.selectedProperty());
		txtTitle.visibleProperty().bind(isEmailOrLetterOrDocument);
		txtTitle.managedProperty().bind(isEmailOrLetterOrDocument);
		lblTitle.visibleProperty().bind(isEmailOrLetterOrDocument);
		lblTitle.managedProperty().bind(isEmailOrLetterOrDocument);
		txtSubtitle.visibleProperty().bind(isEmailOrLetterOrDocument);
		txtSubtitle.managedProperty().bind(isEmailOrLetterOrDocument);
		lblSubtitle.visibleProperty().bind(isEmailOrLetterOrDocument);
		lblSubtitle.managedProperty().bind(isEmailOrLetterOrDocument);
		txtDate.visibleProperty().bind(isEmailOrLetterOrDocument);
		txtDate.managedProperty().bind(isEmailOrLetterOrDocument);
		lblDate.visibleProperty().bind(isEmailOrLetterOrDocument);
		lblDate.managedProperty().bind(isEmailOrLetterOrDocument);

		// visible for emails and letters
		ObservableBooleanValue isEmailOrLetter = radEMails.selectedProperty().or(radLetters.selectedProperty());
		txtOpening.visibleProperty().bind(isEmailOrLetter);
		txtOpening.managedProperty().bind(isEmailOrLetter);
		lblOpening.visibleProperty().bind(isEmailOrLetter);
		lblOpening.managedProperty().bind(isEmailOrLetter);
		txtClosing.visibleProperty().bind(isEmailOrLetter);
		txtClosing.managedProperty().bind(isEmailOrLetter);
		lblClosing.visibleProperty().bind(isEmailOrLetter);
		lblClosing.managedProperty().bind(isEmailOrLetter);
		txtSignature.visibleProperty().bind(isEmailOrLetter);
		txtSignature.managedProperty().bind(isEmailOrLetter);
		lblSignature.visibleProperty().bind(isEmailOrLetter);
		lblSignature.managedProperty().bind(isEmailOrLetter);

		tblAttachments.visibleProperty().bind(isEmailOrLetter);
		lblAttachments.visibleProperty().bind(isEmailOrLetter);
		barAttachments.visibleProperty().bind(isEmailOrLetter);

		// visible for letters and documents
		ObservableBooleanValue isLetterOrDocument = radLetters.selectedProperty().or(radDocuments.selectedProperty()).or(radDocument.selectedProperty());
		txtOptions.visibleProperty().bind(isLetterOrDocument);
		txtOptions.managedProperty().bind(isLetterOrDocument);
		lblOptions.visibleProperty().bind(isLetterOrDocument);
		lblOptions.managedProperty().bind(isLetterOrDocument);

		// visible for letters, texts, documents, and text
		ObservableBooleanValue isLetterOrDocumentOrText = radLetters.selectedProperty().or(radDocuments.selectedProperty()).or(radTexts.selectedProperty()).or(radDocument.selectedProperty()).or(radText.selectedProperty());
		txtCommunicationOutputPath.visibleProperty().bind(isLetterOrDocumentOrText);
		txtCommunicationOutputPath.managedProperty().bind(isLetterOrDocumentOrText);
		lblCommunicationOutputPath.visibleProperty().bind(isLetterOrDocumentOrText);
		lblCommunicationOutputPath.managedProperty().bind(isLetterOrDocumentOrText);
		txtFilename.visibleProperty().bind(isLetterOrDocumentOrText);
		txtFilename.managedProperty().bind(isLetterOrDocumentOrText);
		lblFilename.visibleProperty().bind(isLetterOrDocumentOrText);
		lblFilename.managedProperty().bind(isLetterOrDocumentOrText);

		// visible for specific fields
		lblCommunicationOutput.visibleProperty().bind(lblCommunicationOutputPath.visibleProperty().or(lblOptions.visibleProperty()));
		lblCommunicationOutput.managedProperty().bind(lblCommunicationOutput.visibleProperty());
		sepCommunicationOutput.visibleProperty().bind(lblCommunicationOutput.visibleProperty());
		sepCommunicationOutput.managedProperty().bind(lblCommunicationOutput.visibleProperty());

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
	 */
	public void initController(final AppLayoutController theAppController) {

		appController = theAppController;

		ctlRefList.setDataTableItems();

	}

	/**
	 * Send email or create letter/document.
	 */
	@FXML
	private void handleSendCreate() {

		Map<String, Object> mapDocData = new HashMap<>();
		for (DocumentDataVariable theTemplateVariable : DocumentDataVariable.values()) {

			switch (theTemplateVariable) {
				case ATTACHMENT:
					mapDocData.putIfAbsent(theTemplateVariable.value(), new ArrayList<>());
					tblAttachments.getItems().forEach(attachment -> {
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

		Configuration tplConfig = new Configuration(Configuration.VERSION_2_3_26);
		tplConfig.setDefaultEncoding(StandardCharsets.UTF_8.name());
		tplConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		tplConfig.setLogTemplateExceptions(false);

		if (grpCommKind.getSelectedToggle() == radEMails) {

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

			if (doSend.getValue()) {
				sendEMails(mapDocData, tplConfig);
			}

		} else if (grpCommKind.getSelectedToggle() == radLetters) {
			createLetters(mapDocData, tplConfig);
		} else if (grpCommKind.getSelectedToggle() == radDocuments) {
			createDocumentsOrTexts(mapDocData, tplConfig, Prefs.get(PrefKey.DOCUMENTS_TEMPLATE_DOCUMENT));
		} else if (grpCommKind.getSelectedToggle() == radTexts) {
			createDocumentsOrTexts(mapDocData, tplConfig, Prefs.get(PrefKey.TEXTS_TEMPLATE_TEXT));
		} else if (grpCommKind.getSelectedToggle() == radDocument) {
			createDocumentOrText(mapDocData, tplConfig, Prefs.get(PrefKey.DOCUMENTS_TEMPLATE_DOCUMENT));
		} else if (grpCommKind.getSelectedToggle() == radText) {
			createDocumentOrText(mapDocData, tplConfig, Prefs.get(PrefKey.TEXTS_TEMPLATE_TEXT));
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
	 */
	@FXML
	private void handlePrefs() {

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("PreferencesDialog");

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Einstellungen");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(appController.getPrimaryStage());

		dialogStage.setScene(new Scene(pneLoad.getKey()));

		// initialize controller
		PreferencesDialogController controller = pneLoad.getValue().getController();
		controller.initController(appController, dialogStage,
				(radEMails.isSelected()) ? "tabEMail" :
					(radLetters.isSelected()) ? "tabLetters" :
						(radDocument.isSelected()) ? "tabDocuments" : "tabTexts");

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

		appController.setAppTitle();

	}

	/**
	 * Message file selection.
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

						// only right trim
						int i = theLine.length() - 1;
				        while (i >= 0 && Character.isWhitespace(theLine.charAt(i))) {
				            i--;
				        }
				        lstBody.add(theLine.substring(0, i+1));

					} else {

						for (DocumentDataVariable theTemplateVariable : DocumentDataVariable.values()) {

							String sVarToken = String.format(MESSAGE_VARIABLE_TOKEN, theTemplateVariable.value());
							if (theLine.trim().startsWith(sVarToken)) {

								String theLineContent = theLine.trim().substring(sVarToken.length()).trim();

								switch (theTemplateVariable) {
									case ATTACHMENT:
										String[] arrAttachmentParts = theLineContent.split(ATTACHMENT_SEPARATOR);

										Attachment attNew = new Attachment();
										attNew.setLandscape(new SimpleBooleanProperty(false));

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

				txtBody.setText(lstBody.stream().collect(Collectors.joining(System.lineSeparator())));

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
	 */
	@FXML
	private void handleMessageFileSave() {
		System.out.println("#handleMessageFileSave");
	}

	/**
	 * Attachment addition.
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
	 */
	private boolean showAttachmentEditDialog(Attachment theAttachment) {

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("AttachmentEditDialog");

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(appController.getPrimaryStage());
		dialogStage.setTitle("Dateianhang editieren");

		dialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set the attachment
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
	 * Every mail is sent individually for two reasons:
	 *
	 * 1. If there are multiple recipients, {@link Transport#send(Message, String, String)} sends
	 * all mails in a transaction, meaning if one fails, all fail.
	 * This is bad, because mails frequently fail, meaning one has to resend all Mails.
	 *
	 * Maybe I'm doing something wrong there, in that case the code could be
	 * changes to including all recipients as BCC.
	 *
	 * 2. I have to use individualized mails (per template), this only works
	 * with individual mails.
	 *
	 * @param theDocData document data
	 * @param theConfig template configuration
	 */
	private void sendEMails(final Map<String, Object> theDocData, Configuration theConfig) {

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
						Path pathTemplateFile = Paths.get(Prefs.get(PrefKey.PATHS_TEMPLATE), Prefs.get(PrefKey.EMAIL_TEMPLATE_EMAIL));

						theConfig.setDirectoryForTemplateLoading(pathTemplateFile.getParent().toFile());
						Template tplEMail = theConfig.getTemplate(pathTemplateFile.getFileName().toString());

						// prepare email
						Properties mailProps = new Properties();
						mailProps.setProperty("mail.smtp.host", Prefs.get(PrefKey.EMAIL_SMTP_HOST));
						mailProps.setProperty("mail.smtp.auth", "true");

						Session session = Session.getInstance(mailProps, new Authenticator() {
									@Override
									protected PasswordAuthentication getPasswordAuthentication() {
											return new PasswordAuthentication(Prefs.get(PrefKey.EMAIL_SMTP_USERNAME), Prefs.get(PrefKey.EMAIL_SMTP_PASSWORD));
									}
						});

						// send email for every person individually (see remark in method doc)
						FilteredList<? extends PersonModel> lstPeople = new FilteredList<>(ctlRefList.getSortedSelectedItems(), PersonModel.HAS_EMAIL);
						int iCount = lstPeople.size();
						for (PersonModel person : lstPeople) {

							RefereeManager.logger.info(MessageFormat.format("Mail an ''{0}''.", person.getDisplayTitle().getValue()));
							updateMessage(MessageFormat.format("Mail an ''{0}''.", person.getDisplayTitle().getValue()));

							// fill variables in generated content
							Map<String, Object> mapFilled = fillDocumentData(theDocData, theConfig, person);
							RefereeManager.logger.info(MessageFormat.format("Subject: ''{0}''.", (String) mapFilled.get(DocumentDataVariable.SUBJECT.value())));

							try {

								Message msgMail = new MimeMessage(session);

								// from
								msgMail.setFrom(new InternetAddress(Prefs.get(PrefKey.EMAIL_FROM_EMAIL), Prefs.get(PrefKey.EMAIL_FROM_NAME), StandardCharsets.UTF_8.name()));

								// reply to
								List<InternetAddress> lstReplyTo = new ArrayList<>();
								lstReplyTo.add(new InternetAddress(Prefs.get(PrefKey.EMAIL_FROM_EMAIL), Prefs.get(PrefKey.EMAIL_FROM_NAME), StandardCharsets.UTF_8.name()));
								if (!Prefs.get(PrefKey.EMAIL_REPLY_TO_EMAIL1).isEmpty()) {
									lstReplyTo.add(new InternetAddress(Prefs.get(PrefKey.EMAIL_REPLY_TO_EMAIL1), Prefs.get(PrefKey.EMAIL_REPLY_TO_NAME1), StandardCharsets.UTF_8.name()));
								}
								if (!Prefs.get(PrefKey.EMAIL_REPLY_TO_EMAIL2).isEmpty()) {
									lstReplyTo.add(new InternetAddress(Prefs.get(PrefKey.EMAIL_REPLY_TO_EMAIL2), Prefs.get(PrefKey.EMAIL_REPLY_TO_NAME2), StandardCharsets.UTF_8.name()));
								}
								msgMail.setReplyTo(lstReplyTo.toArray(new InternetAddress[lstReplyTo.size()]));

								// date
								if (mapFilled.get(DocumentDataVariable.DATE.value()) instanceof LocalDateTime) {
									msgMail.setSentDate(DateTimeUtils.toDate((LocalDateTime) mapFilled.get(DocumentDataVariable.DATE.value())));
								} else {
									msgMail.setSentDate(DateTimeUtils.toDate(DateTimeUtils.parseDateTime((String) mapFilled.get(DocumentDataVariable.DATE.value()))));
								}

								// subject
								msgMail.setSubject((String) mapFilled.get(DocumentDataVariable.SUBJECT.value()));

								// recipient
								EMail theEMail = person.getPrimaryEMail();
								msgMail.setRecipient(RecipientType.TO, new InternetAddress(theEMail.getEMail().getValue(), person.getFullName().getValue(), StandardCharsets.UTF_8.name()));

								// content
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
										Path attachment = Paths.get(theAttachment.getFilename().getValue());

										BodyPart bpAttachment = new MimeBodyPart();
										bpAttachment.setDataHandler(new DataHandler(new FileDataSource(attachment.toFile())));
										bpAttachment.setFileName(attachment.getFileName().toString());

										msgContent.addBodyPart(bpAttachment);

										RefereeManager.logger.info(MessageFormat.format("Attachment: {0}", bpAttachment.getFileName()));
									}
								}

								if (chkTestMail.isSelected()) {
									RefereeManager.logger.info(MailUtils.toString(msgMail));
									RefereeManager.logger.info("Test! Nicht gesendet.");
								} else {
									Transport.send(msgMail);
									RefereeManager.logger.info("gesendet.");
								}
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
			Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("ProgressDialog");

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
    					MessageFormat.format("{0,choice,0#Keine E-Mails wurden|1#Eine E-Mail wurde|1<{0,number,integer} E-Mails wurden} versendet, es {1,choice,0#traten keine|1#trat ein|1<traten {1,number,integer}} Fehler auf.",
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
	 * Create letters.
	 *
	 * @param theDocData document data
	 * @param theConfig template configuration
	 *
	 * @since 0.12.0
	 */
	private void createLetters(final Map<String, Object> theDocData, Configuration theConfig) {

		Objects.requireNonNull(theDocData, "document data must not be null");
		Objects.requireNonNull(theConfig, "template configuration must not be null");

		try (StringWriter wrtProtocol = new StringWriter()) {

			RefereeManager.addAppender(wrtProtocol, "createLetters");

			LocalTime tmeStart = LocalTime.now();
			RefereeManager.logger.info("Start Brieferzeugung.");

			Task<Map<String, Integer>> taskLetter = new Task<Map<String,Integer>>() {

				/** Main execution method. */
				@Override
				protected Map<String, Integer> call() throws InterruptedException {

					int iSuccess = 0;
					int iError = 0;

					try {

						// load document template
						updateMessage("Lade Brief-Template.");
						Path pathTemplateFile = Paths.get(Prefs.get(PrefKey.PATHS_TEMPLATE), Prefs.get(PrefKey.LETTERS_TEMPLATE_LETTER));

						theConfig.setDirectoryForTemplateLoading(pathTemplateFile.getParent().toFile());
						Template tplLetter = theConfig.getTemplate(pathTemplateFile.getFileName().toString());

						// load single merge template
						updateMessage("Lade Single-Merge-Template.");
						pathTemplateFile = Paths.get(Prefs.get(PrefKey.PATHS_TEMPLATE), Prefs.get(PrefKey.LETTERS_TEMPLATE_MERGE_SINGLE));

						theConfig.setDirectoryForTemplateLoading(pathTemplateFile.getParent().toFile());
						Template tplMergeSingle = theConfig.getTemplate(pathTemplateFile.getFileName().toString());

						// load all merge template
						updateMessage("Lade All-Merge-Template.");
						pathTemplateFile = Paths.get(Prefs.get(PrefKey.PATHS_TEMPLATE), Prefs.get(PrefKey.LETTERS_TEMPLATE_MERGE_ALL));

						theConfig.setDirectoryForTemplateLoading(pathTemplateFile.getParent().toFile());
						Template tplMergeAll = theConfig.getTemplate(pathTemplateFile.getFileName().toString());


						// create docs
						List<? extends PersonModel> lstPeople = ctlRefList.getSortedSelectedItems();
						int iCount = lstPeople.size();

						Map<String, List<String>> mapFilenames = new HashMap<>();
						mapFilenames.put("merges", new ArrayList<>());

						for (PersonModel person : lstPeople) {

							RefereeManager.logger.info(MessageFormat.format("Brief an ''{0}''.", person.getDisplayTitle().getValue()));
							updateMessage(MessageFormat.format("Brief an ''{0}''.", person.getDisplayTitle().getValue()));

							// fill variables in generated content
							Map<String, Object> mapFilled = fillDocumentData(theDocData, theConfig, person);

							try {

								// fill document template, write document
								Path pathOutFile = Paths.get(Prefs.get(PrefKey.REFEREE_COMMUNICATION_OUTPUT_PATH), (String) mapFilled.get(DocumentDataVariable.FILENAME.value()));
								try (StringWriter wrtContent = new StringWriter()) {
									tplLetter.process(mapFilled, wrtContent);
									FileAccess.writeFile(pathOutFile, wrtContent.toString());
								}

								RefereeManager.logger.info(MessageFormat.format("Dokument ''{0}'' erzeugt", pathOutFile.toAbsolutePath().toString()));

								iSuccess++;

								// fill single merge template, write document
								pathOutFile = Paths.get(Prefs.get(PrefKey.REFEREE_COMMUNICATION_OUTPUT_PATH),
										String.format("merge_%s", mapFilled.get(DocumentDataVariable.FILENAME.value())).replace(".mmd", ".tex"));
								try (StringWriter wrtContent = new StringWriter()) {
									tplMergeSingle.process(mapFilled, wrtContent);
									FileAccess.writeFile(pathOutFile, wrtContent.toString());
								}
								mapFilenames.get("merges").add(pathOutFile.getFileName().toString().replace(".tex", ".pdf"));

								RefereeManager.logger.info(MessageFormat.format("Dokument ''{0}'' erzeugt", pathOutFile.toAbsolutePath().toString()));

								iSuccess++;

							} catch (IOException | TemplateException e) {
								RefereeManager.logger.error(e);
								iError++;
							}

							updateProgress(iError + iSuccess, iCount);

						}

						try {

							// fill all merge template, write document
							Path pathOutFile = Paths.get(Prefs.get(PrefKey.REFEREE_COMMUNICATION_OUTPUT_PATH), "merge_all.tex");
							try (StringWriter wrtContent = new StringWriter()) {
								tplMergeAll.process(mapFilenames, wrtContent);
								FileAccess.writeFile(pathOutFile, wrtContent.toString());
							}

							RefereeManager.logger.info(MessageFormat.format("Dokument ''{0}'' erzeugt", pathOutFile.toAbsolutePath().toString()));

							iSuccess++;

						} catch (IOException | TemplateException e) {
							RefereeManager.logger.error(e);
						}

					} catch (IOException e) {
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
			Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("ProgressDialog");

			Stage dialogStage = new Stage(StageStyle.UTILITY);
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.setResizable(false);
			dialogStage.setScene(new Scene(pneLoad.getKey()));

			ProgressDialogController controller = pneLoad.getValue().getController();
			controller.initController("Briefe erzeugen", taskLetter);

            // task succeeded - show results
            taskLetter.setOnSucceeded(event -> {
                dialogStage.close();

    			Map<String, Integer> mapResult = taskLetter.getValue();

    			RefereeManager.logger.info("Ende Brieferzeugung.");
    			Duration sendingTime = Duration.between(tmeStart, LocalTime.now());
    			RefereeManager.logger.info(MessageFormat.format("Dauer: {0}", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.ofSecondOfDay(sendingTime.getSeconds()))));

    			Alert alert = AlertUtils.createExpandableAlert((mapResult.get("error") > 0) ? AlertType.ERROR : AlertType.INFORMATION, appController.getPrimaryStage(),
    					"Brieferzeugung",
    					MessageFormat.format("Erzeugung {0,choice,0#erfolgreich|1#fehlerhaft|1<fehlerhaft}", mapResult.get("error")),
    					MessageFormat.format("{0,choice,0#Keine Dateien wurden|1#Eine Datei wurde|1<{0,number,integer} Dateien wurden} erzeugt, es {1,choice,0#traten keine|1#trat ein|1<traten {1,number,integer}} Fehler auf.",
    							mapResult.get("success"), mapResult.get("error")),
    					"Details:",
    					wrtProtocol.toString());

    			alert.showAndWait();
            });

            dialogStage.show();

            Thread thread = new Thread(taskLetter);
            thread.start();

		} catch (IOException e) {
			RefereeManager.logger.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * Create documents or texts.
	 *
	 * @param theDocData document data
	 * @param theConfig template configuration
	 * @param theTemplate template name
	 *
	 * @since 0.12.0
	 */
	private void createDocumentsOrTexts(final Map<String, Object> theDocData, Configuration theConfig, final String theTemplate) {

		Objects.requireNonNull(theDocData, "document data must not be null");
		Objects.requireNonNull(theConfig, "template configuration must not be null");

		try (StringWriter wrtProtocol = new StringWriter()) {

			RefereeManager.addAppender(wrtProtocol, "createTexts");

			LocalTime tmeStart = LocalTime.now();
			RefereeManager.logger.info("Start Texterzeugung.");

			Task<Map<String, Integer>> taskLetter = new Task<Map<String,Integer>>() {

				/** Main execution method. */
				@Override
				protected Map<String, Integer> call() throws InterruptedException {

					int iSuccess = 0;
					int iError = 0;

					try {

						// load text template
						Path pathTemplateFile = Paths.get(Prefs.get(PrefKey.PATHS_TEMPLATE), theTemplate);
						updateMessage(MessageFormat.format("Lade Template ''{0}''.", pathTemplateFile.toAbsolutePath().toString()));
						theConfig.setDirectoryForTemplateLoading(pathTemplateFile.getParent().toFile());
						Template tplDocument = theConfig.getTemplate(pathTemplateFile.getFileName().toString());


						// create texts
						List<? extends PersonModel> lstPeople = ctlRefList.getSortedSelectedItems();
						int iCount = lstPeople.size();

						for (PersonModel person : lstPeople) {

							RefereeManager.logger.info(MessageFormat.format("Text für ''{0}''.", person.getDisplayTitle().getValue()));
							updateMessage(MessageFormat.format("Text für ''{0}''.", person.getDisplayTitle().getValue()));

							// fill variables in generated content
							Map<String, Object> mapFilled = fillDocumentData(theDocData, theConfig, person);

							try {

								// fill document template, write document
								Path pathOutFile = Paths.get(Prefs.get(PrefKey.REFEREE_COMMUNICATION_OUTPUT_PATH), (String) mapFilled.get(DocumentDataVariable.FILENAME.value()));
								try (StringWriter wrtContent = new StringWriter()) {
									tplDocument.process(mapFilled, wrtContent);
									FileAccess.writeFile(pathOutFile, wrtContent.toString());
								}

								RefereeManager.logger.info(MessageFormat.format("Dokument ''{0}'' erzeugt", pathOutFile.toAbsolutePath().toString()));

								iSuccess++;

							} catch (IOException | TemplateException e) {
								RefereeManager.logger.error(e);
								iError++;
							}

							updateProgress(iError + iSuccess, iCount);

						}

					} catch (IOException e) {
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
			Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("ProgressDialog");

			Stage dialogStage = new Stage(StageStyle.UTILITY);
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.setResizable(false);
			dialogStage.setScene(new Scene(pneLoad.getKey()));

			ProgressDialogController controller = pneLoad.getValue().getController();
			controller.initController("Texte erzeugen", taskLetter);

            // task succeeded - show results
            taskLetter.setOnSucceeded(event -> {
                dialogStage.close();

    			Map<String, Integer> mapResult = taskLetter.getValue();

    			RefereeManager.logger.info("Ende Texterzeugung.");
    			Duration sendingTime = Duration.between(tmeStart, LocalTime.now());
    			RefereeManager.logger.info(MessageFormat.format("Dauer: {0}", DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.ofSecondOfDay(sendingTime.getSeconds()))));

    			Alert alert = AlertUtils.createExpandableAlert((mapResult.get("error") > 0) ? AlertType.ERROR : AlertType.INFORMATION, appController.getPrimaryStage(),
    					"Texterzeugung",
    					MessageFormat.format("Erzeugung {0,choice,0#erfolgreich|1#fehlerhaft|1<fehlerhaft}", mapResult.get("error")),
    					MessageFormat.format("{0,choice,0#Keine Texte wurden|1#Ein Text wurde|1<{0,number,integer} Texte wurden} erzeugt, es {1,choice,0#traten keine|1#trat ein|1<traten {1,number,integer}} Fehler auf.",
    							mapResult.get("success"), mapResult.get("error")),
    					"Details:",
    					wrtProtocol.toString());

    			alert.showAndWait();
            });

            dialogStage.show();

            Thread thread = new Thread(taskLetter);
            thread.start();

		} catch (IOException e) {
			RefereeManager.logger.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * Creates document or text.
	 *
	 * @param theDocData document data
	 * @param theConfig template configuration
	 * @param theTemplate template name
	 *
	 * @since 0.12.0
	 */
	private void createDocumentOrText(final Map<String, Object> theDocData, Configuration theConfig, final String theTemplate) {

		Objects.requireNonNull(theDocData, "document data must not be null");
		Objects.requireNonNull(theConfig, "template configuration must not be null");


		try (StringWriter wrtProtocol = new StringWriter()) {

			RefereeManager.addAppender(wrtProtocol, "createDocument");

			LocalTime tmeStart = LocalTime.now();
			RefereeManager.logger.info("Start Dokumenterzeugung.");

			Optional<String> sCreated = Optional.empty();

			try {

				// fill variables in generated content (todo)
				Map<String, Object> mapFilled = fillDocumentData(theDocData, theConfig, null);

				// load document template
				Path pathTemplateFile = Paths.get(Prefs.get(PrefKey.PATHS_TEMPLATE), theTemplate);
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
	 * @param theConfig template configuration
	 * @param thePerson currently processed person, null if there is none
	 *
	 * @return filled document data
	 *
	 * @since 0.12.0
	 */
	private Map<String, Object> fillDocumentData(final Map<String, Object> theDocData, final Configuration theConfig, final Person thePerson) {
		Map<String, Object> mapReturn = new HashMap<>();

		Map<String, Object> mapData = new HashMap<>();
		mapData.put("today", LocalDateTime.now());
		mapData.put("documentdata", theDocData);
		mapData.put("refdata", AppModel.getData());
		mapData.put("selection", ctlRefList.getSortedSelectedItems());
		mapData.put("current", thePerson);
		mapData.put("prefs", Prefs.getPrefMap());

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

			} else if (value instanceof List<?>) {

				List<Attachment> lstAtts = new ArrayList<>();

				for (Attachment theAttachment : (List<Attachment>) value) {

					Attachment attTemp = new Attachment();

					if ((theAttachment.getFilename() != null) && !theAttachment.getFilename().getValue().isEmpty()) {
						String sFilled = theAttachment.getFilename().getValue();
						try {
							Template tplTemp = new Template(key, new StringReader(sFilled), theConfig);
							try (StringWriter wrtContent = new StringWriter()) {
								tplTemp.process(mapData, wrtContent);
								sFilled = wrtContent.toString();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						attTemp.setFilename(new SimpleStringProperty(sFilled));
					}

					if ((theAttachment.getTitle() != null) && !theAttachment.getTitle().getValue().isEmpty()) {
						String sFilled = theAttachment.getTitle().getValue();
						try {
							Template tplTemp = new Template(key, new StringReader(sFilled), theConfig);
							try (StringWriter wrtContent = new StringWriter()) {
								tplTemp.process(mapData, wrtContent);
								sFilled = wrtContent.toString();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						attTemp.setTitle(new SimpleStringProperty(sFilled));
					}

					attTemp.setLandscape(theAttachment.getLandscape());

					lstAtts.add(attTemp);

				}

				mapReturn.put(key, lstAtts);


			} else {
				mapReturn.put(key, value);
			}

		});

		// for templates that need the current person
		mapReturn.put("current", thePerson);

		return mapReturn;
	}

}

/* EOF */

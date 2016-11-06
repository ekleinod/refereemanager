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
import de.edgesoft.refereemanager.model.template.Attachment;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import de.edgesoft.refereemanager.utils.TemplateHelper;
import de.edgesoft.refereemanager.utils.TemplateVariable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
	 * Date picker.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private DatePicker pckDate;

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
	 * Checkbox document.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private CheckBox chkDocument;

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
		btnAttachmentAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnAttachmentEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnAttachmentDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

		// enabling buttons
		btnSend.disableProperty().bind(
				ctlRefList.getSelectionModel().selectedItemProperty().isNull()
				.or(
						chkEMail.selectedProperty().not()
						.and(chkLetter.selectedProperty().not())
						.and(chkDocument.selectedProperty().not())
						)
				);
		btnMessageFileLoad.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());
		btnMessageFileSave.disableProperty().bind(txtCommunicationFile.textProperty().isEmpty());
		btnAttachmentEdit.disableProperty().bind(tblAttachments.getSelectionModel().selectedItemProperty().isNull());
		btnAttachmentDelete.disableProperty().bind(tblAttachments.getSelectionModel().selectedItemProperty().isNull());


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
        				txtClosing.setText(null);
        				pckDate.setValue(null);
        				txtFilename.setText(null);
        				txtOpening.setText(null);
        				txtSignature.setText(null);
        				txtTitle.setText(null);
        				txtSubtitle.setText(null);
        				txtBody.setText(null);
        				
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
        								case CLOSING:
        									txtClosing.setText(theLineContent);
        									break;
        								case DATE:
        									pckDate.setValue(DateTimeUtils.parseDate(theLineContent));
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
        								case TITLE:
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

}

/* EOF */

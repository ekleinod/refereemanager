package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;

import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller for preferences edit dialog scene.
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
 * @version 0.12.0
 * @since 0.10.0
 */
public class PreferencesDialogController {

	/**
	 * Checkbox: full path in title.
	 *
	 * @version 6.0.0
	 * @since 6.0.0
	 */
	@FXML
	private CheckBox chkTitleFullpath;

	/**
	 * Template path.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtTemplatePath;

	/**
	 * Template path button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnTemplatePath;

	/**
	 * Image path.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtImagePath;

	/**
	 * Image path button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnImagePath;

	/**
	 * EMail - SMTP host.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailSMTPHost;

	/**
	 * EMail - SMTP username.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailSMTPUsername;

	/**
	 * EMail - SMTP password.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailSMTPPassword;

	/**
	 * EMail - From name.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailFromName;

	/**
	 * EMail - From ameil.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailFromEMail;

	/**
	 * EMail - To name.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailToName;

	/**
	 * EMail - To email.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailToEMail;

	/**
	 * EMail - Templates - EMail.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailTemplateEMail;

	/**
	 * EMail - Templates - Letter.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailTemplateLetter;

	/**
	 * EMail - Templates - single merge.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailTemplateMergeSingle;

	/**
	 * EMail - Templates - all merge.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private TextField txtEMailTemplateMergeAll;

	/**
	 * Documents - Templates - Document.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtDocumentsTemplateDocument;

	/**
	 * Texts - Templates - Text.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtTextsTemplateText;

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
	 * Import button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnImport;

	/**
	 * Export button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnExport;

	/**
	 * Tab display.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Tab tabDisplay;

	/**
	 * Tab paths.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Tab tabPaths;

	/**
	 * Tab EMail.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Tab tabEMail;

	/**
	 * Tab documents.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Tab tabDocuments;

	/**
	 * Tab texts.
	 *
	 * @version 0.12.0
	 * @since 0.12.0
	 */
	@FXML
	private Tab tabTexts;

	/**
	 * Tab pane.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private TabPane pneTabs;


	/**
	 * Reference to dialog stage.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private Stage dialogStage;

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
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private void initialize() {

		// icons
		btnTemplatePath.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/folder-open.png")));
		btnImagePath.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/folder-open.png")));

		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

		tabDisplay.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));
		tabPaths.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));
		tabEMail.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));
		tabDocuments.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));
		tabTexts.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));

		fillValues();

	}

	/**
	 * Initializes the controller with things, that cannot be done during {@link #initialize()}.
	 *
	 * @param theAppController app controller
	 * @param theStage dialog stage
	 * @param theTabID tab to open
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void initController(final AppLayoutController theAppController, final Stage theStage, final String theTabID) {
		dialogStage = theStage;
		if (theTabID != null) {
			pneTabs.getTabs().forEach(tab -> {
				if (tab.getId().equals(theTabID)) {
					pneTabs.getSelectionModel().select(tab);
				}
			});
		}
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
	 * Fill preference values.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	private void fillValues() {

		// tab display
		chkTitleFullpath.setSelected(Boolean.parseBoolean(Prefs.get(PrefKey.TITLE_FULLPATH)));

		// tab templates
		txtTemplatePath.setText(Prefs.get(PrefKey.TEMPLATE_PATH));
		txtImagePath.setText(Prefs.get(PrefKey.IMAGE_PATH));

		// tab email
		txtEMailSMTPHost.setText(Prefs.get(PrefKey.EMAIL_SMTP_HOST));
		txtEMailSMTPUsername.setText(Prefs.get(PrefKey.EMAIL_SMTP_USERNAME));
		txtEMailSMTPPassword.setText(Prefs.get(PrefKey.EMAIL_SMTP_PASSWORD));
		txtEMailFromName.setText(Prefs.get(PrefKey.EMAIL_FROM_NAME));
		txtEMailFromEMail.setText(Prefs.get(PrefKey.EMAIL_FROM_EMAIL));
		txtEMailToName.setText(Prefs.get(PrefKey.EMAIL_TO_NAME));
		txtEMailToEMail.setText(Prefs.get(PrefKey.EMAIL_TO_EMAIL));
		txtEMailTemplateEMail.setText(Prefs.get(PrefKey.EMAIL_TEMPLATE_EMAIL));
		txtEMailTemplateLetter.setText(Prefs.get(PrefKey.EMAIL_TEMPLATE_LETTER));
		txtEMailTemplateMergeSingle.setText(Prefs.get(PrefKey.EMAIL_TEMPLATE_MERGE_SINGLE));
		txtEMailTemplateMergeAll.setText(Prefs.get(PrefKey.EMAIL_TEMPLATE_MERGE_ALL));

		// tab documents
		txtDocumentsTemplateDocument.setText(Prefs.get(PrefKey.DOCUMENTS_TEMPLATE_DOCUMENT));

		// tab texts
		txtTextsTemplateText.setText(Prefs.get(PrefKey.TEXTS_TEMPLATE_TEXT));

	}

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleOk() {
		okClicked = false;
		if (isInputValid()) {

			// tab display
			Prefs.put(PrefKey.TITLE_FULLPATH, Boolean.toString(chkTitleFullpath.isSelected()));

			// tab templates
			Prefs.put(PrefKey.TEMPLATE_PATH, txtTemplatePath.getText());
			Prefs.put(PrefKey.IMAGE_PATH, txtImagePath.getText());

			// tab email
			Prefs.put(PrefKey.EMAIL_SMTP_HOST, txtEMailSMTPHost.getText());
			Prefs.put(PrefKey.EMAIL_SMTP_USERNAME, txtEMailSMTPUsername.getText());
			Prefs.put(PrefKey.EMAIL_SMTP_PASSWORD, txtEMailSMTPPassword.getText());
			Prefs.put(PrefKey.EMAIL_FROM_NAME, txtEMailFromName.getText());
			Prefs.put(PrefKey.EMAIL_FROM_EMAIL, txtEMailFromEMail.getText());
			Prefs.put(PrefKey.EMAIL_TO_NAME, txtEMailToName.getText());
			Prefs.put(PrefKey.EMAIL_TO_EMAIL, txtEMailToEMail.getText());
			Prefs.put(PrefKey.EMAIL_TEMPLATE_EMAIL, txtEMailTemplateEMail.getText());
			Prefs.put(PrefKey.EMAIL_TEMPLATE_LETTER, txtEMailTemplateLetter.getText());
			Prefs.put(PrefKey.EMAIL_TEMPLATE_MERGE_SINGLE, txtEMailTemplateMergeSingle.getText());
			Prefs.put(PrefKey.EMAIL_TEMPLATE_MERGE_ALL, txtEMailTemplateMergeAll.getText());

			// tab documents
			Prefs.put(PrefKey.DOCUMENTS_TEMPLATE_DOCUMENT, txtDocumentsTemplateDocument.getText());

			// tab documents
			Prefs.put(PrefKey.TEXTS_TEMPLATE_TEXT, txtTextsTemplateText.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Validates input, shows error message for invalid input.
	 *
	 * @return is input valid?
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private boolean isInputValid() {

		StringBuilder sbErrorMessage = new StringBuilder();

		if (!txtTemplatePath.getText().isEmpty()) {
			File flePath = Paths.get(txtTemplatePath.getText()).toFile();
			if (!flePath.exists() || !flePath.isDirectory()) {
				sbErrorMessage.append("Der Template-Pfad existiert nicht oder ist kein Verzeichnis.\n");
			}
		}

		if (!txtImagePath.getText().isEmpty()) {
			File flePath = Paths.get(txtImagePath.getText()).toFile();
			if (!flePath.exists() || !flePath.isDirectory()) {
				sbErrorMessage.append("Der Bilder-Pfad existiert nicht oder ist kein Verzeichnis.\n");
			}
		}

		if (sbErrorMessage.length() == 0) {
				return true;
		}

		// Show the error message.
		AlertUtils.createAlert(AlertType.ERROR, dialogStage,
				"Ungültige Eingaben",
				"Bitte korrigieren Sie die fehlerhaften Eingaben.",
				sbErrorMessage.toString())
		.showAndWait();

		return false;

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
	 * Set template path.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleTemplatePath() {

		DirectoryChooser dirChooser = new DirectoryChooser();

		dirChooser.setTitle("Template-Pfad auswählen");
		if (!Prefs.get(PrefKey.TEMPLATE_PATH).isEmpty()) {
			dirChooser.setInitialDirectory(new File(Prefs.get(PrefKey.TEMPLATE_PATH)));
		}

		File dir = dirChooser.showDialog(dialogStage);

		if (dir != null) {
			txtTemplatePath.setText(dir.getPath());
		}

	}

	/**
	 * Set image path.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleImagePath() {

		DirectoryChooser dirChooser = new DirectoryChooser();

		dirChooser.setTitle("Bilder-Pfad auswählen");
		if (!Prefs.get(PrefKey.IMAGE_PATH).isEmpty()) {
			dirChooser.setInitialDirectory(new File(Prefs.get(PrefKey.IMAGE_PATH)));
		}

		File dir = dirChooser.showDialog(dialogStage);

		if (dir != null) {
			txtImagePath.setText(dir.getPath());
		}

	}

	/**
	 * Imports preferences.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleImport() {

		Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, dialogStage,
				"Einstellungsimport",
				"Überschreiben von Einstellungen.",
				"Wenn Sie Einstellungen importieren, werden die bisherigen Einstellungen überschrieben, wenn Einstellungen dafür vorhanden sind.\nWollen Sie fortfahren?");

		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent()) {
			if (result.get() == ButtonType.YES) {
				FileChooser fileChooser = new FileChooser();

				fileChooser.setTitle("Einstellungen importieren");
				fileChooser.getExtensionFilters().addAll(
						new FileChooser.ExtensionFilter("Referee-Manager-Einstellungen (*.prefs)", "*.prefs"),
						new FileChooser.ExtensionFilter("Alle Dateien (*.*)", "*.*")
						);
				if (!Prefs.get(PrefKey.PREFERENCES_FILE).isEmpty()) {
					Path pathPrefs = Paths.get(Prefs.get(PrefKey.PREFERENCES_FILE));
					fileChooser.setInitialDirectory(pathPrefs.getParent().toFile());
					fileChooser.setInitialFileName(pathPrefs.getFileName().toString());
				}

				File flePrefs = fileChooser.showOpenDialog(dialogStage);

				if (flePrefs != null) {
					Prefs.put(PrefKey.PREFERENCES_FILE, flePrefs.getAbsolutePath());

					try (InputStream stmIn = new FileInputStream(flePrefs)) {
						Prefs.importPrefs(stmIn);
						fillValues();
					} catch (IOException | InvalidPreferencesFormatException | BackingStoreException e) {
						AlertUtils.createAlert(AlertType.ERROR, dialogStage,
								"Importfehler",
								"Beim Import der Einstellungen ist ein Fehler aufgetreten.",
								MessageFormat.format("{0}\nDie Daten wurden nicht importiert.", e.getMessage()))
						.showAndWait();
					}
				}

			}
		}

	}

	/**
	 * Exports preferences.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleExport() {

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Einstellungen exportieren");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Referee-Manager-Einstellungen (*.prefs)", "*.prefs"),
				new FileChooser.ExtensionFilter("Alle Dateien (*.*)", "*.*")
				);
		if (!Prefs.get(PrefKey.PREFERENCES_FILE).isEmpty()) {
			Path pathPrefs = Paths.get(Prefs.get(PrefKey.PREFERENCES_FILE));
			fileChooser.setInitialDirectory(pathPrefs.getParent().toFile());
			fileChooser.setInitialFileName(pathPrefs.getFileName().toString());
		}

		File flePrefs = fileChooser.showSaveDialog(dialogStage);

		if (flePrefs != null) {
			if (!flePrefs.getName().contains(".")) {
				flePrefs = new File(String.format("%s.prefs", flePrefs.getPath()));
			}
			Prefs.put(PrefKey.PREFERENCES_FILE, flePrefs.getAbsolutePath());

			try (OutputStream stmOut = new FileOutputStream(flePrefs)) {
				Prefs.exportPrefs(stmOut);
			} catch (IOException | BackingStoreException e) {
				AlertUtils.createAlert(AlertType.ERROR, dialogStage,
						"Exportfehler",
						"Beim Export der Einstellungen ist ein Fehler aufgetreten.",
						MessageFormat.format("{0}\nDie Daten wurden nicht exportiert.", e.getMessage()))
				.showAndWait();
			}
		}

	}

}

/* EOF */

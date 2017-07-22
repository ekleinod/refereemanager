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
public class PreferencesDialogController {

	/**
	 * OK button.
	 */
	@FXML
	private Button btnOK;

	/**
	 * Cancel button.
	 */
	@FXML
	private Button btnCancel;

	/**
	 * Import button.
	 */
	@FXML
	private Button btnImport;

	/**
	 * Export button.
	 */
	@FXML
	private Button btnExport;


	/**
	 * Tab pane.
	 */
	@FXML
	private TabPane pneTabs;


	/**
	 * Tab other.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Tab tabOther;

	/**
	 * Checkbox: full path in title.
	 */
	@FXML
	private CheckBox chkTitleFullpath;

	/**
	 * Checkbox: sort data after loading.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private CheckBox chkDataSortLoading;


	/**
	 * Tab paths.
	 */
	@FXML
	private Tab tabPaths;

	/**
	 * Template path.
	 */
	@FXML
	private TextField txtPathsTemplate;

	/**
	 * Template path button.
	 */
	@FXML
	private Button btnPathsTemplate;

	/**
	 * Image path.
	 */
	@FXML
	private TextField txtPathsImage;

	/**
	 * Image path button.
	 */
	@FXML
	private Button btnPathsImage;

	/**
	 * Schema path.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtPathsXSD;

	/**
	 * Schema path button.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Button btnPathsXSD;


	/**
	 * Tab referee report.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Tab tabRefereeReports;

	/**
	 * Report path.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private TextField txtRefereeReportPath;

	/**
	 * Report path button.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnRefereeReportPath;

	/**
	 * Report filename league games.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private TextField txtRefereeReportLeagueGame;

	/**
	 * Report filename tournaments.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private TextField txtRefereeReportTournament;


	/**
	 * Tab EMail.
	 */
	@FXML
	private Tab tabEMail;

	/**
	 * EMail - SMTP host.
	 */
	@FXML
	private TextField txtEMailSMTPHost;

	/**
	 * EMail - SMTP username.
	 */
	@FXML
	private TextField txtEMailSMTPUsername;

	/**
	 * EMail - SMTP password.
	 */
	@FXML
	private TextField txtEMailSMTPPassword;

	/**
	 * EMail - From name.
	 */
	@FXML
	private TextField txtEMailFromName;

	/**
	 * EMail - From ameil.
	 */
	@FXML
	private TextField txtEMailFromEMail;

	/**
	 * EMail - To name.
	 */
	@FXML
	private TextField txtEMailToName;

	/**
	 * EMail - To email.
	 */
	@FXML
	private TextField txtEMailToEMail;

	/**
	 * EMail - Templates - EMail.
	 */
	@FXML
	private TextField txtEMailTemplateEMail;


	/**
	 * Tab letters.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Tab tabLetters;

	/**
	 * Letters - Templates - Letter.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtLettersTemplateLetter;

	/**
	 * Letters - Templates - single merge.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtLettersTemplateMergeSingle;

	/**
	 * Letters - Templates - all merge.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtLettersTemplateMergeAll;


	/**
	 * Tab documents.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Tab tabDocuments;

	/**
	 * Documents - Templates - Document.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtDocumentsTemplateDocument;


	/**
	 * Tab texts.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Tab tabTexts;

	/**
	 * Texts - Templates - Text.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private TextField txtTextsTemplateText;


	/**
	 * Tab statistics.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private Tab tabStatistics;

	/**
	 * Statistics - Templates - Overview.
	 *
	 * @since 0.14.0
	 */
	@FXML
	private TextField txtStatisticsTemplateOverview;


	/**
	 * Reference to dialog stage.
	 */
	private Stage dialogStage;

	/**
	 * OK clicked?.
	 */
	private boolean okClicked;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {

		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

		tabOther.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));
		btnPathsTemplate.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/folder-open.png")));
		btnPathsImage.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/folder-open.png")));
		btnPathsXSD.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/document-open.png")));
		btnRefereeReportPath.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/document-open.png")));

		tabPaths.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));

		tabEMail.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));

		tabLetters.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));

		tabDocuments.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));

		tabTexts.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));

		tabStatistics.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-list-details.png")));

		// fill with existing values
		fillValues();

	}

	/**
	 * Initializes the controller with things, that cannot be done during {@link #initialize()}.
	 *
	 * @param theAppController app controller
	 * @param theStage dialog stage
	 * @param theTabID tab to open
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
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Fill preference values.
	 */
	private void fillValues() {

		// tab other
		chkTitleFullpath.setSelected(Boolean.parseBoolean(Prefs.get(PrefKey.OTHER_TITLE_FULLPATH)));
		chkDataSortLoading.setSelected(Boolean.parseBoolean(Prefs.get(PrefKey.OTHER_DATA_SORT_LOADING)));

		// tab templates
		txtRefereeReportPath.setText(Prefs.get(PrefKey.REFEREE_REPORT_PATH));
		txtRefereeReportLeagueGame.setText(Prefs.get(PrefKey.REFEREE_REPORT_LEAGUE_GAMES));
		txtRefereeReportTournament.setText(Prefs.get(PrefKey.REFEREE_REPORT_TOURNAMENTS));

		// tab paths
		txtPathsImage.setText(Prefs.get(PrefKey.PATHS_IMAGE));
		txtPathsTemplate.setText(Prefs.get(PrefKey.PATHS_TEMPLATE));
		txtPathsXSD.setText(Prefs.get(PrefKey.PATHS_XSD));

		// tab email
		txtEMailSMTPHost.setText(Prefs.get(PrefKey.EMAIL_SMTP_HOST));
		txtEMailSMTPUsername.setText(Prefs.get(PrefKey.EMAIL_SMTP_USERNAME));
		txtEMailSMTPPassword.setText(Prefs.get(PrefKey.EMAIL_SMTP_PASSWORD));
		txtEMailFromName.setText(Prefs.get(PrefKey.EMAIL_FROM_NAME));
		txtEMailFromEMail.setText(Prefs.get(PrefKey.EMAIL_FROM_EMAIL));
		txtEMailToName.setText(Prefs.get(PrefKey.EMAIL_TO_NAME));
		txtEMailToEMail.setText(Prefs.get(PrefKey.EMAIL_TO_EMAIL));
		txtEMailTemplateEMail.setText(Prefs.get(PrefKey.EMAIL_TEMPLATE_EMAIL));

		// tab letters
		txtLettersTemplateLetter.setText(Prefs.get(PrefKey.LETTERS_TEMPLATE_LETTER));
		txtLettersTemplateMergeSingle.setText(Prefs.get(PrefKey.LETTERS_TEMPLATE_MERGE_SINGLE));
		txtLettersTemplateMergeAll.setText(Prefs.get(PrefKey.LETTERS_TEMPLATE_MERGE_ALL));

		// tab documents
		txtDocumentsTemplateDocument.setText(Prefs.get(PrefKey.DOCUMENTS_TEMPLATE_DOCUMENT));

		// tab texts
		txtTextsTemplateText.setText(Prefs.get(PrefKey.TEXTS_TEMPLATE_TEXT));

		// tab statistics
		txtStatisticsTemplateOverview.setText(Prefs.get(PrefKey.STATISTICS_TEMPLATE_OVERVIEW));

	}

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 */
	@FXML
	private void handleOk() {
		okClicked = false;
		if (isInputValid()) {

			// tab other
			Prefs.put(PrefKey.OTHER_TITLE_FULLPATH, Boolean.toString(chkTitleFullpath.isSelected()));
			Prefs.put(PrefKey.OTHER_DATA_SORT_LOADING, Boolean.toString(chkDataSortLoading.isSelected()));

			// tab referee report
			Prefs.put(PrefKey.REFEREE_REPORT_PATH, txtRefereeReportPath.getText());
			Prefs.put(PrefKey.REFEREE_REPORT_LEAGUE_GAMES, txtRefereeReportLeagueGame.getText());
			Prefs.put(PrefKey.REFEREE_REPORT_TOURNAMENTS, txtRefereeReportTournament.getText());

			// tab paths
			Prefs.put(PrefKey.PATHS_IMAGE, txtPathsImage.getText());
			Prefs.put(PrefKey.PATHS_TEMPLATE, txtPathsTemplate.getText());
			Prefs.put(PrefKey.PATHS_XSD, txtPathsXSD.getText());

			// tab email
			Prefs.put(PrefKey.EMAIL_SMTP_HOST, txtEMailSMTPHost.getText());
			Prefs.put(PrefKey.EMAIL_SMTP_USERNAME, txtEMailSMTPUsername.getText());
			Prefs.put(PrefKey.EMAIL_SMTP_PASSWORD, txtEMailSMTPPassword.getText());
			Prefs.put(PrefKey.EMAIL_FROM_NAME, txtEMailFromName.getText());
			Prefs.put(PrefKey.EMAIL_FROM_EMAIL, txtEMailFromEMail.getText());
			Prefs.put(PrefKey.EMAIL_TO_NAME, txtEMailToName.getText());
			Prefs.put(PrefKey.EMAIL_TO_EMAIL, txtEMailToEMail.getText());
			Prefs.put(PrefKey.EMAIL_TEMPLATE_EMAIL, txtEMailTemplateEMail.getText());

			// tab letters
			Prefs.put(PrefKey.LETTERS_TEMPLATE_LETTER, txtLettersTemplateLetter.getText());
			Prefs.put(PrefKey.LETTERS_TEMPLATE_MERGE_SINGLE, txtLettersTemplateMergeSingle.getText());
			Prefs.put(PrefKey.LETTERS_TEMPLATE_MERGE_ALL, txtLettersTemplateMergeAll.getText());

			// tab documents
			Prefs.put(PrefKey.DOCUMENTS_TEMPLATE_DOCUMENT, txtDocumentsTemplateDocument.getText());

			// tab documents
			Prefs.put(PrefKey.TEXTS_TEMPLATE_TEXT, txtTextsTemplateText.getText());

			// tab statistics
			Prefs.put(PrefKey.STATISTICS_TEMPLATE_OVERVIEW, txtStatisticsTemplateOverview.getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Validates input, shows error message for invalid input.
	 *
	 * @return is input valid?
	 */
	private boolean isInputValid() {

		StringBuilder sbErrorMessage = new StringBuilder();

		if (!txtPathsTemplate.getText().isEmpty()) {
			File flePath = Paths.get(txtPathsTemplate.getText()).toFile();
			if (!flePath.exists() || !flePath.isDirectory()) {
				sbErrorMessage.append("Der Template-Pfad existiert nicht oder ist kein Verzeichnis.\n");
			}
		}

		if (!txtPathsImage.getText().isEmpty()) {
			File flePath = Paths.get(txtPathsImage.getText()).toFile();
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
	 */
	@FXML
	private void handleCancel() {
		okClicked = false;
		dialogStage.close();
	}

	/**
	 * Set template path.
	 */
	@FXML
	private void handlePathsTemplate() {
		selectDirectory("Template-Pfad auswählen", PrefKey.PATHS_TEMPLATE, txtPathsTemplate);
	}

	/**
	 * Set image path.
	 *
	 * @since 0.10.0
	 */
	@FXML
	private void handlePathsImage() {
		selectDirectory("Bilder-Pfad auswählen", PrefKey.PATHS_IMAGE, txtPathsImage);
	}

	/**
	 * Set referee report path.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleRefereeReportPath() {
		selectDirectory("OSR-Bericht-Pfad auswählen", PrefKey.REFEREE_REPORT_PATH, txtRefereeReportPath);
	}

	/**
	 * Get directory, set text field.
	 *
	 * @since 0.15.0
	 */
	private void selectDirectory(final String theTitle, final PrefKey thePrefKey, final TextField theField) {

		DirectoryChooser dirChooser = new DirectoryChooser();

		dirChooser.setTitle(theTitle);

		if (!theField.getText().trim().isEmpty()) {
			dirChooser.setInitialDirectory(new File(theField.getText().trim()));
		} else if (!Prefs.get(thePrefKey).isEmpty()) {
			dirChooser.setInitialDirectory(new File(Prefs.get(thePrefKey)));
		}

		File dir = dirChooser.showDialog(dialogStage);

		if (dir != null) {
			theField.setText(dir.getPath());
		}

	}

	/**
	 * Set XSD path.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private void handlePathsXSD() {

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("XML-Schema auswählen");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("XML-Schema (*.xsd)", "*.xsd"),
				new FileChooser.ExtensionFilter("Alle Dateien (*.*)", "*.*")
				);
		if (!Prefs.get(PrefKey.PATHS_XSD).isEmpty()) {
			Path pathPrefs = Paths.get(Prefs.get(PrefKey.PATHS_XSD));
			fileChooser.setInitialDirectory(pathPrefs.getParent().toFile());
			fileChooser.setInitialFileName(pathPrefs.getFileName().toString());
		}

		File flePrefs = fileChooser.showOpenDialog(dialogStage);

		if (flePrefs != null) {
			txtPathsXSD.setText(flePrefs.getAbsolutePath());
		}

	}

	/**
	 * Imports preferences.
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

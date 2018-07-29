package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import de.edgesoft.edgeutils.EdgeUtilsException;
import de.edgesoft.edgeutils.commons.Info;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.edgeutils.javafx.ButtonUtils;
import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.controller.overview.IOverviewController;
import de.edgesoft.refereemanager.controller.overview.OverviewClubsController;
import de.edgesoft.refereemanager.controller.overview.OverviewContactTypesController;
import de.edgesoft.refereemanager.controller.overview.OverviewLeagueGamesController;
import de.edgesoft.refereemanager.controller.overview.OverviewLeaguesController;
import de.edgesoft.refereemanager.controller.overview.OverviewOtherEventsController;
import de.edgesoft.refereemanager.controller.overview.OverviewPeopleController;
import de.edgesoft.refereemanager.controller.overview.OverviewRefereesController;
import de.edgesoft.refereemanager.controller.overview.OverviewRolesController;
import de.edgesoft.refereemanager.controller.overview.OverviewSexTypesController;
import de.edgesoft.refereemanager.controller.overview.OverviewStatusTypesController;
import de.edgesoft.refereemanager.controller.overview.OverviewTournamentsController;
import de.edgesoft.refereemanager.controller.overview.OverviewTraineesController;
import de.edgesoft.refereemanager.controller.overview.OverviewTrainingLevelTypesController;
import de.edgesoft.refereemanager.controller.overview.OverviewVenuesController;
import de.edgesoft.refereemanager.jaxb.Content;
import de.edgesoft.refereemanager.jaxb.ObjectFactory;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for application layout.
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
public class AppLayoutController {

	/**
	 * Application icon.
	 */
	public static final Image ICON = Resources.loadImage("images/icon-32.png");

	/**
	 * App border pane.
	 */
	@FXML
	private BorderPane appPane;

	/**
	 * Menu item program -> preferences.
	 */
	@FXML
	private MenuItem mnuProgramPreferences;

	/**
	 * Menu item program -> quit.
	 */
	@FXML
	private MenuItem mnuProgramQuit;

	/**
	 * Menu item file -> new.
	 */
	@FXML
	private MenuItem mnuFileNew;

	/**
	 * Menu item file -> open.
	 */
	@FXML
	private MenuItem mnuFileOpen;

	/**
	 * Menu item file -> save.
	 */
	@FXML
	private MenuItem mnuFileSave;

	/**
	 * Menu item file -> save as.
	 */
	@FXML
	private MenuItem mnuFileSaveAs;

	/**
	 * Menu item referee -> overview.
	 */
	@FXML
	private MenuItem mnuOverviewReferees;

	/**
	 * Menu item overview -> people.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewPeople;

	/**
	 * Menu item trainee -> overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewTrainees;

	/**
	 * Menu item referee -> communication.
	 */
	@FXML
	private MenuItem mnuRefereeCommunication;

	/**
	 * Menu league game overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewLeagueGames;

	/**
	 * Menu item tournament overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewTournaments;

	/**
	 * Menu item other event overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewOtherEvents;

	/**
	 * Menu item club overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewClubs;

	/**
	 * Menu item leagues overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewLeagues;

	/**
	 * Menu item venues overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewVenues;

	/**
	 * Menu item sex types overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewSexTypes;

	/**
	 * Menu item roles overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewRoles;

	/**
	 * Menu item contact types overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewContactTypes;

	/**
	 * Menu item status types overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewStatusTypes;

	/**
	 * Menu item training level types overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private MenuItem mnuOverviewTrainingLevelTypes;

	/**
	 * Menu item statistics -> data.
	 */
	@FXML
	private MenuItem mnuStatisticsData;

	/**
	 * Menu item help -> about.
	 */
	@FXML
	private MenuItem mnuHelpAbout;

	/**
	 * Main toolbar.
	 */
	@FXML
	private ToolBar barMain;

	/**
	 * Button program -> quit.
	 */
	@FXML
	private Button btnProgramQuit;

	/**
	 * Button file -> open.
	 */
	@FXML
	private Button btnFileOpen;

	/**
	 * Button file -> save.
	 */
	@FXML
	private Button btnFileSave;

	/**
	 * Button referee -> overview.
	 */
	@FXML
	private Button btnOverviewReferees;

	/**
	 * Button overview -> people.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnOverviewPeople;

	/**
	 * Button overview -> trainees.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnOverviewTrainees;

	/**
	 * Button referee -> communication.
	 */
	@FXML
	private Button btnRefereeCommunication;

	/**
	 * Button league games overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnOverviewLeagueGames;

	/**
	 * Button tournament overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnOverviewTournaments;

	/**
	 * Button other event overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnOverviewOtherEvents;

	/**
	 * Button club overview.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private Button btnOverviewClubs;

	/**
	 * Button statistics -> data.
	 */
	@FXML
	private Button btnStatisticsData;

	/**
	 * Button program -> preferences.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private Button btnProgramPreferences;


	/**
	 * Primary stage.
	 */
	private Stage primaryStage = null;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @since 0.12.0
	 */
	@FXML
	private void initialize() {

		// icons
		mnuProgramQuit.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/application-exit.png")));
		ButtonUtils.adaptButton(btnProgramQuit, mnuProgramQuit);
		mnuProgramPreferences.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/configure.png")));
		ButtonUtils.adaptButton(btnProgramPreferences, mnuProgramPreferences);

		mnuFileNew.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/document-new.png")));
		mnuFileOpen.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/document-open.png")));
		ButtonUtils.adaptButton(btnFileOpen, mnuFileOpen);
		mnuFileSave.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/document-save.png")));
		ButtonUtils.adaptButton(btnFileSave, mnuFileSave);
		mnuFileSaveAs.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/document-save-as.png")));

		mnuOverviewReferees.setGraphic(new ImageView(Resources.loadImage("icons/24x24/own/view-referees.png")));
		ButtonUtils.adaptButton(btnOverviewReferees, mnuOverviewReferees);
		mnuOverviewPeople.setGraphic(new ImageView(Resources.loadImage("icons/24x24/own/view-people.png")));
		ButtonUtils.adaptButton(btnOverviewPeople, mnuOverviewPeople);
		mnuOverviewTrainees.setGraphic(new ImageView(Resources.loadImage("icons/24x24/own/view-trainees.png")));
		ButtonUtils.adaptButton(btnOverviewTrainees, mnuOverviewTrainees);

		mnuRefereeCommunication.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/dialog-messages.png")));
		ButtonUtils.adaptButton(btnRefereeCommunication, mnuRefereeCommunication);

		mnuOverviewLeagueGames.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-calendar-week.png")));
		ButtonUtils.adaptButton(btnOverviewLeagueGames, mnuOverviewLeagueGames);
		mnuOverviewTournaments.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-calendar-week.png")));
		ButtonUtils.adaptButton(btnOverviewTournaments, mnuOverviewTournaments);
		mnuOverviewOtherEvents.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-calendar-week.png")));
		ButtonUtils.adaptButton(btnOverviewOtherEvents, mnuOverviewOtherEvents);

		mnuOverviewClubs.setGraphic(new ImageView(Resources.loadImage("icons/24x24/own/view-clubs.png")));
		ButtonUtils.adaptButton(btnOverviewClubs, mnuOverviewClubs);

		mnuOverviewLeagues.setGraphic(new ImageView(Resources.loadImage("icons/24x24/own/view-leagues.png")));
		mnuOverviewVenues.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/atmosphere.png")));
		mnuOverviewSexTypes.setGraphic(new ImageView(Resources.loadImage("icons/24x24/own/view-sextypes.png")));
		mnuOverviewRoles.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-calendar-week.png")));
		mnuOverviewContactTypes.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/call-start.png")));
		mnuOverviewStatusTypes.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/view-calendar-week.png")));
		mnuOverviewTrainingLevelTypes.setGraphic(new ImageView(Resources.loadImage("icons/24x24/own/view-trainingleveltypes.png")));

		mnuStatisticsData.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/office-chart-bar.png")));
		ButtonUtils.adaptButton(btnStatisticsData, mnuStatisticsData);

		mnuHelpAbout.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/help-about.png")));

	}

	/**
	 * Initializes the controller with things, that cannot be done during {@link #initialize()}.
	 *
	 * @param thePrimaryStage primary stage
	 */
	public void initController(final Stage thePrimaryStage) {

		primaryStage = thePrimaryStage;

		// set icon
		primaryStage.getIcons().add(ICON);

		// Show the scene containing the root layout.
		primaryStage.setScene(new Scene(appPane));
		primaryStage.show();

		// resize to last dimensions
		primaryStage.setX(Double.parseDouble(Prefs.get(PrefKey.STAGE_X)));
		primaryStage.setY(Double.parseDouble(Prefs.get(PrefKey.STAGE_Y)));

		primaryStage.setWidth(Double.parseDouble(Prefs.get(PrefKey.STAGE_WIDTH)));
		primaryStage.setHeight(Double.parseDouble(Prefs.get(PrefKey.STAGE_HEIGHT)));

		primaryStage.setMaximized(Boolean.parseBoolean(Prefs.get(PrefKey.STAGE_MAXIMIZED)));

		// if changed, save bounds to preferences
		primaryStage.xProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (!primaryStage.isMaximized()) {
				Prefs.put(PrefKey.STAGE_X, Double.toString(newValue.doubleValue()));
			}
		});
		primaryStage.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (!primaryStage.isMaximized()) {
				Prefs.put(PrefKey.STAGE_WIDTH, Double.toString(newValue.doubleValue()));
			}
		});
		primaryStage.yProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (!primaryStage.isMaximized()) {
				Prefs.put(PrefKey.STAGE_Y, Double.toString(newValue.doubleValue()));
			}
		});
		primaryStage.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (!primaryStage.isMaximized()) {
				Prefs.put(PrefKey.STAGE_HEIGHT, Double.toString(newValue.doubleValue()));
			}
		});

		primaryStage.maximizedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			Prefs.put(PrefKey.STAGE_MAXIMIZED, Boolean.toString(newValue.booleanValue()));
		});

		// set handler for close requests (x-button of window)
		primaryStage.setOnCloseRequest(event -> {
			event.consume();
			handleProgramExit();
		});

		// finally, we can initialize the data
		initData();

	}

	/**
	 * Initializes the data.
	 */
	private void initData() {

		if (AppModel.getFilename().isEmpty()) {
			newData();
		} else {
			openData(Prefs.get(PrefKey.FILE));
		}

	}

	/**
	 * Creates new data.
	 */
	private void newData() {

		de.edgesoft.refereemanager.jaxb.RefereeManager dtaRefMan = new ObjectFactory().createRefereeManager();

		Info info = new de.edgesoft.edgeutils.commons.ObjectFactory().createInfo();

		info.setCreated(LocalDateTime.now());
		info.setModified(LocalDateTime.now());
		info.setAppversion(RefereeManager.getVersion());
		info.setDocversion(RefereeManager.getVersion());
		info.setCreator(RefereeManager.class.getCanonicalName());

		dtaRefMan.setInfo(info);

		Content content = new ObjectFactory().createContent();
		dtaRefMan.setContent(content);

		AppModel.setData(dtaRefMan);
		AppModel.setFilename(null);
		AppModel.setModified(false);

		setAppTitle();

		}

	/**
	 * Sets the app title.
	 */
	public void setAppTitle() {

		String sFile = Prefs.get(PrefKey.FILE).isEmpty() ?
				"" :
				String.format(" - %s", Boolean.parseBoolean(Prefs.get(PrefKey.OTHER_TITLE_FULLPATH)) ?
						Paths.get(Prefs.get(PrefKey.FILE)).toAbsolutePath().toString() :
						Paths.get(Prefs.get(PrefKey.FILE)).getFileName().toString());

		primaryStage.setTitle(String.format("Referee Manager%s%s",
				sFile,
				AppModel.isModified() ? " *" : ""
				));

	}

	/**
	 * Loads data from the given file.
	 *
	 * @param theFilename filename
	 *
	 * @since 0.12.0
	 */
	private void openData(final String theFilename) {

		try {

			de.edgesoft.refereemanager.jaxb.RefereeManager dtaRefMan = JAXBFiles.unmarshal(theFilename, de.edgesoft.refereemanager.jaxb.RefereeManager.class);

			AppModel.setData(dtaRefMan);
			AppModel.setFilename(theFilename);
			AppModel.setModified(false);

			if (Boolean.parseBoolean(Prefs.get(PrefKey.OTHER_DATA_SORT_LOADING))) {
				((ContentModel) AppModel.getData().getContent()).sortData();
			}

			setAppTitle();

		} catch (EdgeUtilsException e) {

			AlertUtils.createAlert(AlertType.ERROR, primaryStage,
					"Datenfehler",
					"Ein Fehler ist beim Laden der Referee-Manager-Daten aufgetreten.",
					MessageFormat.format("{0}\nDas Programm wird ohne Daten fortgeführt.", e.getMessage()))
			.showAndWait();

			newData();

		}

	}

	/**
	 * Program menu preferences.
	 *
	 * Opens the preferences edit dialog.
	 */
	@FXML
	private void handleProgramPreferences() {

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("PreferencesDialog");

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Einstellungen");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);

		dialogStage.setScene(new Scene(pneLoad.getKey()));

		// initialize controller
		PreferencesDialogController controller = pneLoad.getValue().getController();
		controller.initController(dialogStage, null);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

		setAppTitle();

	}

	/**
	 * Program menu exit.
	 */
	@FXML
	public void handleProgramExit() {
		if (checkModified()) {
			Platform.exit();
		}
	}

	/**
	 * File menu new.
	 */
	@FXML
	private void handleFileNew() {
		if (checkModified()) {
			newData();
		}
	}

	/**
	 * File menu open.
	 */
	@FXML
	private void handleFileOpen() {

		if (checkModified()) {

			FileChooser fileChooser = new FileChooser();

			fileChooser.setTitle("Referee-Manager-Datei öffnen");
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("Referee-Manager-Dateien (*.refman, *.xml)", "*.refman", "*.xml"),
					new FileChooser.ExtensionFilter("Alle Dateien (*.*)", "*.*")
					);
			if (!Prefs.get(PrefKey.PATH).isEmpty()) {
				Path flePath = Paths.get(Prefs.get(PrefKey.PATH));
				if (flePath.toFile().exists()) {
					fileChooser.setInitialDirectory(flePath.toFile());
				}
			}

			File file = fileChooser.showOpenDialog(primaryStage);

			if (file != null) {
				openData(file.getPath());
			}

		}

	}

	/**
	 * File menu save.
	 */
	@FXML
	public void handleFileSave() {
		if (Prefs.get(PrefKey.FILE).isEmpty()) {
			handleFileSaveAs();
		} else {
			saveData(Prefs.get(PrefKey.FILE));
		}
	}

	/**
	 * File menu save as.
	 */
	@FXML
	private void handleFileSaveAs() {

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Referee-Manager-Datei speichern");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Referee-Manager-Dateien (*.refman, *.xml)", "*.refman", "*.xml"),
				new FileChooser.ExtensionFilter("Alle Dateien (*.*)", "*.*")
				);
		if (!Prefs.get(PrefKey.PATH).isEmpty()) {
			fileChooser.setInitialDirectory(new File(Prefs.get(PrefKey.PATH)));
		}

		File file = fileChooser.showSaveDialog(primaryStage);

		if (file != null) {
			if (!file.getName().contains(".")) {
				file = new File(String.format("%s.refman", file.getPath()));
			}
			saveData(file.getPath());
		}

	}

	/**
	 * Help menu about.
	 */
	@FXML
	private void handleHelpAbout() {

		Alert alert = AlertUtils.createAlert(AlertType.INFORMATION, primaryStage,
				"Über \"Referee-Manager\"",
				MessageFormat.format("Referee-Manager Version {0}", RefereeManager.getVersion()),
				null
				);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("AboutText");
		alert.getDialogPane().contentProperty().set(pneLoad.getKey());

		alert.setGraphic(new ImageView(Resources.loadImage("images/icon-64.png")));
		alert.showAndWait();

	}

	/**
	 * Menu referee -> overview.
	 */
	@FXML
	private void handleOverviewReferees() {
		handleOverview(new OverviewRefereesController());
	}

	/**
	 * Menu overview -> people.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewPeople() {
		handleOverview(new OverviewPeopleController());
	}

	/**
	 * Menu overview -> trainees.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewTrainees() {
		handleOverview(new OverviewTraineesController());
	}

	/**
	 * Load overview view.
	 *
	 * @param theOverviewController overview controller
	 *
	 * @since 0.15.0
	 */
	private void handleOverview(
			final IOverviewController<? extends ModelClassExt> theOverviewController
			) {

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("overview/OverviewDetails");

		appPane.setCenter(pneLoad.getKey());

		theOverviewController.initController(pneLoad.getValue().getController());

	}

	/**
	 * Menu referee -> communication.
	 */
	@FXML
	private void handleRefereeCommunication() {

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("RefereeCommunication");
		BorderPane pneCommunication = (BorderPane) pneLoad.getKey();

		// Set event overview into the center of root layout.
		appPane.setCenter(pneCommunication);

		// Give the controller access to the app.
		RefereeCommunicationController ctlRefCommunication = pneLoad.getValue().getController();
		ctlRefCommunication.initController(this);

	}

	/**
	 * Menu overview -> league games.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewLeagueGames() {
		handleOverview(new OverviewLeagueGamesController());
	}

	/**
	 * Menu overview -> tournaments.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewTournaments() {
		handleOverview(new OverviewTournamentsController());
	}

	/**
	 * Menu overview -> other events.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewOtherEvents() {
		handleOverview(new OverviewOtherEventsController());
	}

	/**
	 * Menu things -> clubs.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewClubs() {
		handleOverview(new OverviewClubsController());
	}

	/**
	 * Menu things -> leagues.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewLeagues() {
		handleOverview(new OverviewLeaguesController());
	}

	/**
	 * Menu things -> venues.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewVenues() {
		handleOverview(new OverviewVenuesController());
	}

	/**
	 * Menu things -> sex types.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewSexTypes() {
		handleOverview(new OverviewSexTypesController());
	}

	/**
	 * Menu things -> roles.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewRoles() {
		handleOverview(new OverviewRolesController());
	}

	/**
	 * Menu things -> contact types.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewContactTypes() {
		handleOverview(new OverviewContactTypesController());
	}

	/**
	 * Menu things -> status types.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewStatusTypes() {
		handleOverview(new OverviewStatusTypesController());
	}

	/**
	 * Menu things -> training level types.
	 *
	 * @since 0.15.0
	 */
	@FXML
	private void handleOverviewTrainingLevelTypes() {
		handleOverview(new OverviewTrainingLevelTypesController());
	}

	/**
	 * Menu statistics -> data.
	 *
	 * Shows the data statistics.
	 */
	@FXML
	private void handleStatisticsData() {

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("Statistics");

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Statistik");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);

		dialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set the events into the controller.
		StatisticsController controller = pneLoad.getValue().getController();
		controller.fillStatistics();
		controller.setDialogStage(dialogStage);

		// Show the dialog and wait until the user closes it
		dialogStage.show();

	}

	/**
	 * Check if data is modified, show corresponding dialog, save data if needed.
	 *
	 * @return did user select continue (true) or cancel (false)?
	 */
	private boolean checkModified() {

		boolean doContinue = true;

		if (AppModel.isModified()) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, primaryStage,
					"Nicht gespeicherte Änderungen",
					"Sie haben Änderungen durchgeführt, die noch nicht gespeichert wurden.",
					"Wollen Sie die geänderten Daten speichern, nicht speichern oder wollen Sie den gesamten Vorgang abbrechen?");

			alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent()) {
				if (result.get() == ButtonType.YES) {
					handleFileSave();
					doContinue = true;
				}
				if (result.get() == ButtonType.NO) {
					doContinue = true;
				}
				if (result.get() == ButtonType.CANCEL) {
					doContinue = false;
				}
			}

		}

		return doContinue;

	}

	/**
	 * Saves the data.
	 *
	 * @param theFilename filename
	 */
	private void saveData(final String theFilename) {

		try {

			AppModel.getData().getInfo().setModified(LocalDateTime.now());
			AppModel.getData().getInfo().setAppversion(RefereeManager.getVersion());
			AppModel.getData().getInfo().setDocversion(RefereeManager.getVersion());
			AppModel.getData().getInfo().setCreator(RefereeManager.class.getCanonicalName());

			JAXBFiles.marshal(new ObjectFactory().createRefereemanager(AppModel.getData()), theFilename,
					(Prefs.get(PrefKey.PATHS_XSD).isEmpty() ? null : Prefs.get(PrefKey.PATHS_XSD)));

			AppModel.setFilename(theFilename);
			AppModel.setModified(false);

		} catch (EdgeUtilsException e) {


			AlertUtils.createAlert(AlertType.ERROR, primaryStage,
					"Datenfehler",
					"Ein Fehler ist beim Speichern der Referee-Manager-Daten aufgetreten.",
					MessageFormat.format("{0}\nDie Daten wurden nicht gespeichert.", e.getMessage()))
			.showAndWait();

		}

		setAppTitle();

	}

	/**
	 * Returns primary stage.
	 *
	 * @return primary stage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}

/* EOF */

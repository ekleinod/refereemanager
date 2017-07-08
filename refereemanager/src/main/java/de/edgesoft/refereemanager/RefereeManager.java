package de.edgesoft.refereemanager;

import java.io.Writer;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.WriterAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;

import de.edgesoft.edgeutils.commons.Version;
import de.edgesoft.edgeutils.commons.ext.VersionExt;
import de.edgesoft.refereemanager.controller.AppLayoutController;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * RefereeManager application.
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
public class RefereeManager extends Application {

	/**
	 * Central logger for all classes.
	 */
	public static final Logger logger = LogManager.getLogger(RefereeManager.class.getPackage().getName());

	/**
	 * Host services delegate.
	 *
	 * Needed for opening links in browser etc.
	 */
	public static HostServicesDelegate hostServices = null;

	/**
	 * Starts the application.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * The main entry point for all JavaFX applications.
	 * The start method is called after the init method has returned,
	 * and after the system is ready for the application to begin running.
	 *
	 * @param primaryStage primary stage
	 */
	@Override
	public void start(Stage primaryStage) {

		showSplashScreen();

		// load app layout and controller, then delegate control to controller
		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("AppLayout");
		((AppLayoutController) pneLoad.getValue().getController()).initController(primaryStage);

		// host services
		hostServices = HostServicesFactory.getInstance(this);

	}

	/**
	 * Shows splash screen.
	 *
	 * Inspired by https://gist.github.com/jewelsea/2305098
	 */
	public void showSplashScreen() {

		// Load splash screen.
		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("SplashScreen");
		final AnchorPane pane = (AnchorPane) pneLoad.getKey();

		// Create and fill splash screen stage.
		Stage stage = new Stage();
		stage.initModality(Modality.NONE);
		stage.setAlwaysOnTop(true);
		stage.initStyle(StageStyle.TRANSPARENT);

		Scene scene = new Scene(pane, new Color(1, 1, 1, .5));
		stage.setScene(scene);
		stage.sizeToScene();
		stage.centerOnScreen();

		// define task, that waits 4 seconds, then returns null, i.e. succeeds
		// if needed, some progress bar output could be defined here
		final Task<Void> splashTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Thread.sleep(4000);
				return null;
			}
		};

		// add listener to succeed state of task, then fade out
		splashTask.stateProperty().addListener((observableValue, oldState, newState) -> {
				if (newState == Worker.State.SUCCEEDED) {
						FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), pane);
						fadeSplash.setFromValue(1.0);
						fadeSplash.setToValue(0.0);
						fadeSplash.setOnFinished(actionEvent -> stage.hide());
						fadeSplash.play();
				}
		});

		// show splash screen, then start fading task
		stage.show();
		new Thread(splashTask).start();

	}

	/**
	 * Adds writer appender.
	 *
	 * @param theWriter writer
	 * @param theWriterName name of writer
	 */
	public static void addAppender(final Writer theWriter, final String theWriterName) {
		final LoggerContext context = LoggerContext.getContext(false);
		final Configuration config = context.getConfiguration();
		final PatternLayout layout = PatternLayout.createLayout("%d %-5p: %m%n", null, config, null, null, true, false, null, null);
		final Appender appender = WriterAppender.createAppender(layout, null, theWriter, theWriterName, false, true);
		appender.start();
		config.addAppender(appender);
		updateLoggers(appender, config);
	}

	/**
	 * Updates all loggers with new appender.
	 *
	 * @param theAppender new appender
	 * @param theConfig logger configuration
	 */
	private static void updateLoggers(final Appender theAppender, final Configuration theConfig) {
		final Level level = null;
		final Filter filter = null;
		for (final LoggerConfig loggerConfig : theConfig.getLoggers().values()) {
				loggerConfig.addAppender(theAppender, level, filter);
		}
		theConfig.getRootLogger().addAppender(theAppender, level, filter);
	}

	/**
	 * Program and doc version.
	 *
	 * @since 0.14.0
	 */
	public static Version getVersion() {
		return new VersionExt(Resources.getProjectProperties().getProperty("longversion"));
	}

}

/* EOF */

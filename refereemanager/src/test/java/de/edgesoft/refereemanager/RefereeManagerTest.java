package de.edgesoft.refereemanager;


import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeoutException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.application.Application;
import javafx.scene.input.KeyCode;

/**
 * RefereeManager application test.
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
 * @version 0.15.0
 * @since 0.15.0
 */
@ExtendWith(ApplicationExtension.class)
public class RefereeManagerTest {

	/**
	 * Sleep time in ms.
	 */
	public static final int SLEEP = 1000;

	/**
	 * File name for temporary preferences file.
	 */
	public static final String FILE_PREFS = "savedPreferences.preferences";

	/**
	 * Application.
	 */
	public static Application appRefMan = null;

	/**
	 * Init tests - call application.
	 *
	 * - save current preferences
	 * - start app
	 *
	 * @throws TimeoutException
	 */
	@BeforeAll
    public static void initAll() throws TimeoutException {

		try (OutputStream osPrefs = new FileOutputStream(FILE_PREFS)) {
			Preferences.userNodeForPackage(RefereeManager.class).exportNode(osPrefs);
		} catch (IOException | BackingStoreException e) {
			e.printStackTrace();
		}

        FxToolkit.registerPrimaryStage();
        appRefMan = FxToolkit.setupApplication(RefereeManager.class);
    }

	/**
	 * The big test.
	 *
	 * JUnit 5 does not provide ordered tests (2018-02-25).
	 * The GUI tests have to be performed in a certain order, therefore
	 * all tests are executed in this one test method.
	 *
	 * @param robot FX robot
	 */
    @Test
    public void performeGUITest(
    		FxRobot robot
    		) {

    	// app visible
    	verifyThat("#appPane", isVisible());

    	// preferences
    	robot.clickOn("#mnuProgram").clickOn("#mnuProgramPreferences");
    	robot.sleep(SLEEP);
    	robot.clickOn("#btnCancel");
    	robot.sleep(SLEEP);

    	// new file
    	robot.clickOn("#mnuFile").clickOn("#mnuFileNew");
    	robot.sleep(SLEEP);
    	robot.clickOn("#btnFileSave");
    	robot.sleep(SLEEP);
    	robot.push(KeyCode.ESCAPE);
    	robot.sleep(SLEEP);

    }

	/**
	 * Tear down tests.
	 *
	 * - restore preferences
	 * - clean up app
	 *
	 * @throws TimeoutException
	 */
	@AfterAll
	public static void tearDownAll() throws TimeoutException {

		try (InputStream isPrefs = new FileInputStream(FILE_PREFS)) {
			Preferences.userNodeForPackage(RefereeManager.class).clear();
			Preferences.importPreferences(isPrefs);
		} catch (IOException | BackingStoreException | InvalidPreferencesFormatException e) {
			e.printStackTrace();
		}

		FxToolkit.cleanupApplication(appRefMan);
		FxToolkit.cleanupStages();

	}

}

/* EOF */

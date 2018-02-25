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

    @Test
    public void isAppVisible() {

    	verifyThat("#appPane", isVisible());

    }

    @Test
    public void showPreferences(FxRobot robot) {

    	robot.clickOn("#mnuProgram");
//    	robot.sleep(500);
    	robot.clickOn("#mnuProgramPreferences");
//    	robot.sleep(500);
    	robot.clickOn("#btnCancel");
//    	robot.sleep(500);

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

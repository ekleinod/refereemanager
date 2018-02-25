package de.edgesoft.refereemanager;


import static org.junit.jupiter.api.Assertions.fail;
import static org.testfx.api.FxAssert.verifyThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
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
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.ButtonMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import de.edgesoft.edgeutils.testfx.CheckBoxMatcher;
import de.edgesoft.edgeutils.testfx.RobotHelper;
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
	 * Easing debugging of test with flags: no export of preferences at start of test.
	 */
	private static final boolean DEBUG_EXPORT_PREFS = false;

	/**
	 * Easing debugging of test with flags: no import of preferences at end of test.
	 */
	private static final boolean DEBUG_IMPORT_PREFS = false;

	/**
	 * Sleep time in ms.
	 */
	private static final int SLEEP = 1000;

	/**
	 * File name for temporary preferences file.
	 */
	private static final String FILE_PREFS = "savedPreferences.preferences";

	/**
	 * File name for temporary content file.
	 */
	private static final String FILE_CONTENT = "RefManContent.xml";

	/**
	 * Application.
	 */
	public static Application appRefMan = null;

	/**
	 * Init tests - call application.
	 *
	 * - save current preferences
	 * - if exists - remove test file
	 * - start app
	 *
	 * @throws TimeoutException
	 */
	@BeforeAll
    public static void initAll() throws TimeoutException {

		if (DEBUG_EXPORT_PREFS) {
			try (OutputStream osPrefs = new FileOutputStream(FILE_PREFS)) {
				Preferences.userNodeForPackage(RefereeManager.class).exportNode(osPrefs);
			} catch (IOException | BackingStoreException e) {
				e.printStackTrace();
			}
		}

		File fleTemp = Paths.get(FILE_CONTENT).toFile();
		if (fleTemp.exists()) {
			fleTemp.delete();
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
    	verifyThat("#appPane", NodeMatchers.isVisible());

    	// preferences
    	robot.clickOn("#mnuProgram").clickOn("#mnuProgramPreferences");
    	robot.sleep(SLEEP);

    	verifyThat("#pnePreferences", NodeMatchers.isVisible());
    	verifyThat("#btnOK", ButtonMatchers.isDefaultButton());
    	verifyThat("#btnCancel", ButtonMatchers.isCancelButton());

    	robot.clickOn("#btnCancel");
    	robot.sleep(SLEEP);

    	// new file, save dialog should open as "save as"
    	robot.clickOn("#mnuFile").clickOn("#mnuFileNew");
    	robot.sleep(SLEEP);
    	robot.clickOn("#btnFileSave");
    	robot.sleep(SLEEP);
    	robot.push(KeyCode.ESCAPE);
    	robot.sleep(SLEEP);

    	// clear preferences
		try {
			Preferences.userNodeForPackage(RefereeManager.class).clear();
		} catch (BackingStoreException e) {
			fail(e);
		}

    	// preferences
    	robot.clickOn("#btnProgramPreferences");
    	robot.sleep(SLEEP);

    	verifyThat("#chkTitleFullpath", CheckBoxMatcher.isNotSelected());
    	verifyThat("#chkDataSortLoading", CheckBoxMatcher.isNotSelected());
    	verifyThat("#txtPathsTemplate", TextInputControlMatchers.hasText(""));
    	verifyThat("#txtRefereeReportPath", TextInputControlMatchers.hasText(""));
    	verifyThat("#txtRefereeReportTournament", TextInputControlMatchers.hasText("OSR_%1$s_%2$s.pdf"));
    	verifyThat("#txtEMailTemplateEMail", TextInputControlMatchers.hasText("email/email.mmd"));
    	verifyThat("#txtLettersTemplateMergeSingle", TextInputControlMatchers.hasText("letter/merge_referee.tex"));
    	verifyThat("#txtDocumentsTemplateDocument", TextInputControlMatchers.hasText("document/document.mmd"));
    	verifyThat("#txtTextsTemplateText", TextInputControlMatchers.hasText("text/text.mmd"));
    	verifyThat("#txtStatisticsTemplateOverview", TextInputControlMatchers.hasText("statistics/overview.html"));

    	robot.clickOn("#btnCancel");
    	robot.sleep(SLEEP);

		// save empty file
    	robot.clickOn("#btnFileSave");
    	robot.sleep(SLEEP);
    	RobotHelper.write(robot, FILE_CONTENT);
    	robot.sleep(SLEEP);
    	robot.push(KeyCode.ENTER);
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

		if (DEBUG_IMPORT_PREFS) {
			try (InputStream isPrefs = new FileInputStream(FILE_PREFS)) {
				Preferences.userNodeForPackage(RefereeManager.class).clear();
				Preferences.importPreferences(isPrefs);
			} catch (IOException | BackingStoreException | InvalidPreferencesFormatException e) {
				e.printStackTrace();
			}
		}

		FxToolkit.cleanupApplication(appRefMan);
		FxToolkit.cleanupStages();

	}

}

/* EOF */

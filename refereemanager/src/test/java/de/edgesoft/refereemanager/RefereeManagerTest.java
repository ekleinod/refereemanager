package de.edgesoft.refereemanager;


import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import java.util.concurrent.TimeoutException;

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
	 * Application.
	 */
	public static Application appRefMan = null;

	/**
	 * Init tests - call application.
	 *
	 * @throws TimeoutException
	 */
	@BeforeAll
    public static void initAll() throws TimeoutException {
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

//	/**
//	 * Tear down tests - close application.
//	 * @throws TimeoutException
//	 */
//	@AfterAll
//    public static void tearDownAll(FxRobot robot) throws TimeoutException {
//
//		robot.press(KeyCode.CONTROL, KeyCode.Q);
//		robot.release(KeyCode.CONTROL, KeyCode.Q);
//
//		FxToolkit.cleanupApplication(appRefMan);
//		FxToolkit.cleanupStages();
//
//    }
//
//    @Test
//    public void should_click_on_button(FxRobot robot) {
//        // when:
//        robot.clickOn(button);
//
//        // then:
//        verifyThat(button, hasText("clicked!"));
//    }

}

/* EOF */

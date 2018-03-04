package de.edgesoft.refereemanager;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.testfx.api.FxAssert.verifyThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
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
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.ListViewMatchers;
import org.testfx.matcher.control.TableViewMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.edgeutils.testfx.CheckBoxMatcher;
import de.edgesoft.edgeutils.testfx.ComboBoxMatcher;
import de.edgesoft.edgeutils.testfx.DatePickerMatcher;
import de.edgesoft.edgeutils.testfx.RobotHelper;
import de.edgesoft.edgeutils.testfx.TextInputControlMatcher;
import de.edgesoft.refereemanager.jaxb.ContactType;
import de.edgesoft.refereemanager.jaxb.EMail;
import de.edgesoft.refereemanager.jaxb.ObjectFactory;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.PersonRoleType;
import de.edgesoft.refereemanager.jaxb.PhoneNumber;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.SexType;
import de.edgesoft.refereemanager.model.AppModel;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.VerticalDirection;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
	private static final int SLEEP = 200;

	/**
	 * Default path for saving temporary files.
	 */
	private static final String PATH_TEMP = Paths.get("", "target").toAbsolutePath().toString();

	/**
	 * File name for temporary preferences file.
	 */
	private static final String FILE_PREFS = "savedPreferences.preferences";

	/**
	 * File name for temporary content file.
	 */
	private static final String FILE_CONTENT = "RefManTestContent.xml";

	/**
	 * Object factory.
	 */
	private static ObjectFactory factory = new ObjectFactory();

	/**
	 * Application.
	 */
	private static Application appRefMan = null;

	/**
	 * Fx robot.
	 */
	private static FxRobot robot = null;

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
			try (OutputStream osPrefs = new FileOutputStream(Paths.get(PATH_TEMP, FILE_PREFS).toFile())) {
				Preferences.userNodeForPackage(RefereeManager.class).exportNode(osPrefs);
			} catch (IOException | BackingStoreException e) {
				e.printStackTrace();
			}
		}

		File fleTemp = Paths.get(PATH_TEMP, FILE_CONTENT).toFile();
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
    public void performGUITest(
    		FxRobot theRobot
    		) {

    	robot = theRobot;

    	// app visible
    	verifyThat("#appPane", NodeMatchers.isVisible());

    	// clear preferences
    	try {
    		Preferences.userNodeForPackage(RefereeManager.class).clear();
    	} catch (BackingStoreException e) {
    		fail(e);
    	}

    	testPreferences();
    	testNewFile();
    	testRefereeOverview();


    	robot.clickOn("#btnFileSave");
    	robot.sleep(SLEEP);

    }

	/**
	 * Preferences
	 */
    private static void testPreferences() {

    	robot.clickOn("#mnuProgram").clickOn("#mnuProgramPreferences");
    	robot.sleep(SLEEP);

    	verifyThat("#pnePreferences", NodeMatchers.isVisible());
    	verifyThat("#btnOK", ButtonMatchers.isDefaultButton());
    	verifyThat("#btnCancel", ButtonMatchers.isCancelButton());

    	robot.clickOn("#btnCancel");
    	robot.sleep(SLEEP);

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

    }

	/**
	 * New file.
	 */
    private static void testNewFile() {

    	// new file, save dialog should open as "save as"
    	robot.clickOn("#mnuFile").clickOn("#mnuFileNew");
    	robot.sleep(SLEEP);
    	robot.clickOn("#btnFileSave");
    	robot.sleep(SLEEP);
    	robot.push(KeyCode.ESCAPE);
    	robot.sleep(SLEEP);

		// save empty file
    	Preferences.userNodeForPackage(RefereeManager.class).put("path", PATH_TEMP);
    	robot.clickOn("#btnFileSave");
    	robot.sleep(SLEEP);
    	RobotHelper.write(robot, FILE_CONTENT);
    	robot.sleep(SLEEP);
    	robot.push(KeyCode.ENTER);
    	robot.sleep(1000); // wait a little for the title setting to take effect (don't know why)

    	// check title
    	assertEquals(String.format("Referee Manager - %s", FILE_CONTENT), FxToolkit.toolkitContext().getRegisteredStage().getTitle());

    	robot.clickOn("#btnProgramPreferences");
    	robot.sleep(SLEEP);
    	robot.clickOn("#chkTitleFullpath");
    	robot.sleep(SLEEP);
    	robot.clickOn("#btnOK");
    	robot.sleep(SLEEP);

    	assertEquals(
    			String.format("Referee Manager - %s", Paths.get(PATH_TEMP, FILE_CONTENT).toAbsolutePath().toString()),
    			FxToolkit.toolkitContext().getRegisteredStage().getTitle()
    			);

    }

	/**
	 * Referee overview
	 */
    private static void testRefereeOverview() {

    	robot.clickOn("#btnOverviewReferees");
    	robot.sleep(SLEEP);

    	// verify empty list
    	verifyThat("#tblData", NodeMatchers.isVisible());
    	verifyThat("#tblData", TableViewMatchers.hasNumRows(0));
    	verifyThat("#tblData", (TableView<Referee> tblView) -> ((Label) tblView.getPlaceholder()).getText().equals("Es wurden noch keine Schiedsrichter eingegeben."));
    	verifyThat("#lblHeading", LabeledMatchers.hasText("Details"));
    	verifyThat("#lblFilter", LabeledMatchers.hasText("Filter (0 angezeigt)"));

    	// app model
    	robot.clickOn("#btnAdd");
    	robot.sleep(SLEEP);
    	verifyThat("#cboSexType", ComboBoxMatchers.hasItems(0));
    	verifyThat("#cboRole", ComboBoxMatchers.hasItems(0));
    	robot.clickOn("#btnCancel");
    	robot.sleep(SLEEP);

    	fillAppModel();

    	robot.clickOn("#btnAdd");
    	robot.sleep(SLEEP);
    	verifyThat("#cboSexType", ComboBoxMatchers.hasItems(AppModel.getData().getContent().getSexType().size()));
    	verifyThat("#cboRole", ComboBoxMatchers.hasItems(AppModel.getData().getContent().getRoleType().size()));
    	robot.push(KeyCode.ESCAPE);
    	robot.sleep(SLEEP);

    	// fill in some referees
    	robot.clickOn("#btnAdd");
    	robot.sleep(SLEEP);
    	fillPersonForm(getReferee1());
    	fillContactForm(getReferee1());
    	robot.clickOn("#btnOK");
    	robot.sleep(SLEEP);

    	// verify list entry and details entry (visible, selected automatically)
    	verifyThat("#tblData", TableViewMatchers.hasNumRows(1));
//    	verifyThat("#tblData", TableViewMatchers.containsRow("Name Schiedsrichter 1", "Vorname Schiedsrichter 1", "", "", "", "", "")); // does not work yet (NullPointerException)
    	verifyThat("#lblHeading", LabeledMatchers.hasText(String.format("%s %s", getReferee1().getFirstName().getValueSafe(), getReferee1().getName().getValueSafe())));
    	verifyThat("#lblFilter", LabeledMatchers.hasText("Filter (1 angezeigt)"));

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

		try {
			Preferences.userNodeForPackage(RefereeManager.class).clear();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}

		if (DEBUG_IMPORT_PREFS) {
			try (InputStream isPrefs = new FileInputStream(Paths.get(PATH_TEMP, FILE_PREFS).toFile())) {
				Preferences.importPreferences(isPrefs);
			} catch (IOException | InvalidPreferencesFormatException e) {
				e.printStackTrace();
			}
		}

		FxToolkit.cleanupApplication(appRefMan);
		FxToolkit.cleanupStages();

	}

	/**
	 * Fills person data in input form.
	 *
	 * @param person person data
	 */
    private static void fillPersonForm(
    		Person person
    		) {

    	robot.clickOn("#tabPerson");
    	robot.sleep(SLEEP);

    	verifyThat("#txtID", NodeMatchers.isVisible());
    	robot.clickOn("#txtID");

    	if (person.getId() != null) {
    		RobotHelper.write(robot, person.getId());
    		robot.sleep(SLEEP);
    	}
    	robot.push(KeyCode.TAB);

    	writeText("#txtTitle", person.getTitle());
    	writeText("#txtFirstName", person.getFirstName());
    	writeText("#txtName", person.getName());
    	writeDate("#pckBirthday", person.getBirthday());
    	writeDate("#pckDayOfDeath", person.getDayOfDeath());
    	selectComboBox("#cboSexType", "#btnSexTypeClear", person.getSexType(), AppModel.getData().getContent().getSexType());
    	selectComboBox("#cboRole", "#btnRoleClear", person.getRole(), AppModel.getData().getContent().getRoleType());
    	writeText("#txtRemark", person.getRemark());

    }

	/**
	 * Fills contact data in input form.
	 *
	 * @param person person data
	 */
    private static void fillContactForm(
    		Person person
    		) {

    	robot.clickOn("#tabContact");
    	robot.sleep(SLEEP);

    	verifyThat(".EMail #lstData", ListViewMatchers.isEmpty());
    	verifyThat(".EMail #txtID", NodeMatchers.isInvisible());

    	for (EMail theEMail : person.getEMail()) {

        	writeText(".EMail #txtEMail", theEMail.getEMail());
        	checkCheckBox(".EMail #chkIsPrimary", theEMail.getIsPrimary());
        	checkCheckBox(".EMail #chkEditorOnly", theEMail.getEditorOnly());
        	selectComboBox(".EMail #cboContactType", ".EMail #btnContactTypeClear", theEMail.getContactType(), AppModel.getData().getContent().getContactType());
        	writeText(".EMail #txtRemark", theEMail.getRemark());

			robot.clickOn(".EMail #btnAdd");
			robot.sleep(SLEEP);

			robot.clickOn(".EMail #btnClearList");
			robot.sleep(SLEEP);

		}

    	verifyThat(".EMail #lstData", ListViewMatchers.hasItems(person.getEMail().size()));

    	robot.scroll(30, VerticalDirection.DOWN);

    	verifyThat(".PhoneNumber #lstData", ListViewMatchers.isEmpty());
    	verifyThat(".PhoneNumber #txtID", NodeMatchers.isInvisible());

    	for (PhoneNumber thePhoneNumber : person.getPhoneNumber()) {

        	writeText(".PhoneNumber #txtCountryCode", thePhoneNumber.getCountryCode());
        	writeText(".PhoneNumber #txtAreaCode", thePhoneNumber.getAreaCode());
        	writeText(".PhoneNumber #txtNumber", thePhoneNumber.getNumber());
        	checkCheckBox(".PhoneNumber #chkIsCell", thePhoneNumber.getIsCell());
        	checkCheckBox(".PhoneNumber #chkIsPrimary", thePhoneNumber.getIsPrimary());
        	checkCheckBox(".PhoneNumber #chkEditorOnly", thePhoneNumber.getEditorOnly());
        	selectComboBox(".PhoneNumber #cboContactType", ".PhoneNumber #btnContactTypeClear", thePhoneNumber.getContactType(), AppModel.getData().getContent().getContactType());
        	writeText(".PhoneNumber #txtRemark", thePhoneNumber.getRemark());

			robot.clickOn(".PhoneNumber #btnAdd");
			robot.sleep(SLEEP);

			robot.clickOn(".PhoneNumber #btnClearList");
			robot.sleep(SLEEP);

		}

    	verifyThat(".PhoneNumber #lstData", ListViewMatchers.hasItems(person.getPhoneNumber().size()));

    }

	/**
	 * Returns test data.
	 * @return test data
	 */
	private static Referee getReferee1() {
		Referee dtaReturn = factory.createReferee();

		dtaReturn.setId("Referee.1");
		dtaReturn.setFirstName(new SimpleStringProperty("Vorname Schiedsrichter 1"));
		dtaReturn.setName(new SimpleStringProperty("Name Schiedsrichter 1"));
		dtaReturn.setBirthday(new SimpleObjectProperty<>(LocalDate.of(1970, 9, 21)));
		dtaReturn.setSexType(AppModel.getData().getContent().getSexType().stream().filter(st -> st.getDisplayText().getValueSafe().equals("männlich")).findFirst().get());
		dtaReturn.setRemark(new SimpleStringProperty("Testdaten Schiedsrichter 1"));

		EMail theMail = factory.createEMail();
		theMail.setEMail(new SimpleStringProperty("schiri1 at test-schiri.de")); // @todo @ does not work so far (KeyCombination)
		theMail.setIsPrimary(new SimpleBooleanProperty(true));
		theMail.setContactType(AppModel.getData().getContent().getContactType().stream().filter(st -> st.getDisplayText().getValueSafe().equals("persönlich")).findFirst().get());
		theMail.setRemark(new SimpleStringProperty("Testmail 1 Schiedsrichter 1"));
		dtaReturn.getEMail().add(theMail);

		theMail = factory.createEMail();
		theMail.setEMail(new SimpleStringProperty("schiri2 at test-schiri.de")); // @todo @ does not work so far (KeyCombination)
		theMail.setContactType(AppModel.getData().getContent().getContactType().stream().filter(st -> st.getDisplayText().getValueSafe().equals("dienstlich")).findFirst().get());
		dtaReturn.getEMail().add(theMail);

		PhoneNumber thePhone = factory.createPhoneNumber();
		thePhone.setNumber(new SimpleStringProperty("1234567"));
		thePhone.setIsPrimary(new SimpleBooleanProperty(true));
		thePhone.setContactType(AppModel.getData().getContent().getContactType().stream().filter(st -> st.getDisplayText().getValueSafe().equals("persönlich")).findFirst().get());
		thePhone.setRemark(new SimpleStringProperty("Testtelefon 1 Schiedsrichter 1"));
		dtaReturn.getPhoneNumber().add(thePhone);

		thePhone = factory.createPhoneNumber();
		thePhone.setCountryCode(new SimpleStringProperty("49"));
		thePhone.setAreaCode(new SimpleStringProperty("176"));
		thePhone.setNumber(new SimpleStringProperty("6666 666"));
		thePhone.setIsCell(new SimpleBooleanProperty(true));
		thePhone.setEditorOnly(new SimpleBooleanProperty(true));
		dtaReturn.getPhoneNumber().add(thePhone);

		thePhone = factory.createPhoneNumber();
		thePhone.setAreaCode(new SimpleStringProperty("345"));
		thePhone.setNumber(new SimpleStringProperty("88 88 888-8"));
		dtaReturn.getPhoneNumber().add(thePhone);

		return dtaReturn;
	}

	/**
	 * Fills app model with static data: sex types etc.
	 */
    private static void fillAppModel() {

    	// sex types
    	SexType stNew = factory.createSexType();
    	stNew.setId("SexType.female");
    	stNew.setTitle(new SimpleStringProperty("weiblich"));
    	AppModel.getData().getContent().getSexType().add(stNew);

    	stNew = factory.createSexType();
    	stNew.setId("SexType.male");
    	stNew.setTitle(new SimpleStringProperty("männlich"));
    	stNew.setShorttitle(new SimpleStringProperty("m"));
    	AppModel.getData().getContent().getSexType().add(stNew);

    	stNew = factory.createSexType();
    	stNew.setId("SexType.other");
    	stNew.setTitle(new SimpleStringProperty("andere"));
    	AppModel.getData().getContent().getSexType().add(stNew);

    	// roles
    	PersonRoleType rtNew = factory.createPersonRoleType();
    	rtNew.setId("RoleType.1");
    	rtNew.setTitle(new SimpleStringProperty("VSRO"));
    	AppModel.getData().getContent().getRoleType().add(rtNew);

    	rtNew = factory.createPersonRoleType();
    	rtNew.setId("RoleType.2");
    	rtNew.setTitle(new SimpleStringProperty("Kapitän"));
    	AppModel.getData().getContent().getRoleType().add(rtNew);

    	// contact kinds
    	ContactType ctNew = factory.createContactType();
    	ctNew.setId("ContactType.p");
    	ctNew.setTitle(new SimpleStringProperty("persönlich"));
    	AppModel.getData().getContent().getContactType().add(ctNew);

    	ctNew = factory.createContactType();
    	ctNew.setId("ContactType.d");
    	ctNew.setTitle(new SimpleStringProperty("dienstlich"));
    	ctNew.setShorttitle(new SimpleStringProperty("d"));
    	AppModel.getData().getContent().getContactType().add(ctNew);

    }

	/**
	 * Writes text in form element if needed.
	 *
	 * @param theField input form field
	 * @param theContent content
	 */
    private static void writeText(
    		final String theField,
    		final StringProperty theContent
    		) {

    	verifyThat(theField, NodeMatchers.isVisible());
		verifyThat(theField, TextInputControlMatcher.isEmpty());

		if (theContent != null) {
			robot.clickOn(theField);
			RobotHelper.write(robot, theContent.getValueSafe());
			robot.sleep(SLEEP);

			// text areas' getText returns empty text, therefore no assertion here, same goes for test for focus
			if (!(robot.lookup(theField).query() instanceof TextArea)) {
				verifyThat(theField, TextInputControlMatchers.hasText(theContent.getValueSafe()));
				verifyThat(theField, NodeMatchers.isFocused());
			}

		}

    }

	/**
	 * Writes date in form element if needed.
	 *
	 * @param theField input form field
	 * @param theContent content
	 */
    private static void writeDate(
    		final String theField,
    		final ObjectProperty<LocalDate> theContent
    		) {

    	verifyThat(theField, NodeMatchers.isVisible());
		verifyThat(theField, DatePickerMatcher.isEmpty());

		if (theContent != null) {
			robot.clickOn(theField);
	    	verifyThat(theField, NodeMatchers.isFocused());
			RobotHelper.write(robot, DateTimeUtils.formatDate(theContent.getValue()));
			robot.push(KeyCode.TAB);
			robot.sleep(SLEEP);
			verifyThat(theField, DatePickerMatcher.hasDate(theContent.getValue()));
		}


    }

	/**
	 * Checks CheckBox if needed.
	 *
	 * @param theField input form field
	 * @param theContent content
	 */
    private static void checkCheckBox(
    		final String theField,
    		final BooleanProperty theContent
    		) {

    	verifyThat(theField, NodeMatchers.isVisible());
		verifyThat(theField, CheckBoxMatcher.isNotSelected());

		if ((theContent != null) && (theContent.getValue())) {
			robot.clickOn(theField);
	    	verifyThat(theField, NodeMatchers.isFocused());
			robot.sleep(SLEEP);
			verifyThat(theField, (theContent.getValue()) ? CheckBoxMatcher.isSelected() : CheckBoxMatcher.isNotSelected());
		}


    }

	/**
	 * Selects ComboBox if needed.
	 *
	 * @param theField input form field
	 * @param theFieldClear input form field clear button
	 * @param theContent content
	 * @param theComboContent content of combo box
	 */
    private static void selectComboBox(
    		final String theField,
    		final String theFieldClear,
    		final ModelClassExt theContent,
    		final List<? extends ModelClassExt> theComboContent
    		) {

    	verifyThat(theField, NodeMatchers.isVisible());
		verifyThat(theField, ComboBoxMatcher.isNotSelected());
    	verifyThat(theField, ComboBoxMatchers.hasItems(theComboContent.size()));
    	verifyThat(theField, ComboBoxMatchers.containsItems(theComboContent.toArray()));

		if (theContent != null) {
    		robot.clickOn(theField).clickOn(theContent.getDisplayText().getValue());

    		if (theFieldClear != null) {
	    		robot.clickOn(theFieldClear);
	    		verifyThat(theField, ComboBoxMatcher.isNotSelected());
	    		robot.clickOn(theField).clickOn(theContent.getDisplayText().getValue());
    		}

    		// cannot test for focus, not sure, why
	    	//verifyThat(theField, NodeMatchers.isFocused());

			robot.sleep(SLEEP);

			// ComboBox#getSelectionModel().getSelectedItem() returns null, therefore no check possible
			//verifyThat(theField, ComboBoxMatchers.hasSelectedItem(theContent));

		}


    }

}

/* EOF */

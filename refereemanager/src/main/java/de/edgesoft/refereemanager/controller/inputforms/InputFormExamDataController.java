package de.edgesoft.refereemanager.controller.inputforms;
import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.Trainee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.SpinnerUtils;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

/**
 * Controller for the exam data edit dialog tab.
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
public class InputFormExamDataController extends AbstractInputFormController {

	/**
	 * Checkbox for withdrawn.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "withdrawn", jaxbclass = Trainee.class)
	protected CheckBox chkWithdrawn;

	/**
	 * Checkbox for did not start.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "didNotStart", jaxbclass = Trainee.class)
	protected CheckBox chkDidNotStart;

	/**
	 * Picker for exam date.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "examDate", jaxbclass = Trainee.class)
	protected DatePicker pckExamDate;

	/**
	 * Spinner for points written A.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "pointsWrittenA", jaxbclass = Trainee.class)
	protected Spinner<Integer> spnPointsWrittenA;

	/**
	 * Spinner for points written B.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "pointsWrittenB", jaxbclass = Trainee.class)
	protected Spinner<Integer> spnPointsWrittenB;

	/**
	 * Checkbox for written passed.
	 */
	@FXML
	protected CheckBox chkPassedWritten;

	/**
	 * Spinner for points practical.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "pointsPractical", jaxbclass = Trainee.class)
	protected Spinner<Integer> spnPointsPractical;

	/**
	 * Checkbox for practical passed.
	 */
	@FXML
	protected CheckBox chkPassedPractical;

	/**
	 * Spinner for points oral.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "pointsOral", jaxbclass = Trainee.class)
	protected Spinner<Integer> spnPointsOral;

	/**
	 * Checkbox for oral passed.
	 */
	@FXML
	protected CheckBox chkPassedOral;

	/**
	 * Checkbox for passed.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "passed", jaxbclass = Trainee.class)
	protected CheckBox chkPassed;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

		// set date picker date format
        pckExamDate.setConverter(DateTimeUtils.getDateConverter("d.M.yyyy"));

        // setup spinners
        SpinnerUtils.prepareIntegerSpinner(spnPointsWrittenA, 0, AppModel.getData().getContent().getExam().getMaxPointsWrittenA().getValue());
        spnPointsWrittenA.valueProperty().addListener((observable, oldValue, newValue) -> computeExam());
        SpinnerUtils.prepareIntegerSpinner(spnPointsWrittenB, 0, AppModel.getData().getContent().getExam().getMaxPointsWrittenB().getValue());
        spnPointsWrittenB.valueProperty().addListener((observable, oldValue, newValue) -> computeExam());
        SpinnerUtils.prepareIntegerSpinner(spnPointsPractical, 0, AppModel.getData().getContent().getExam().getMaxPointsPractical().getValue());
        spnPointsPractical.valueProperty().addListener((observable, oldValue, newValue) -> computeExam());
        SpinnerUtils.prepareIntegerSpinner(spnPointsOral, 0, AppModel.getData().getContent().getExam().getMaxPointsOral().getValue());
        spnPointsOral.valueProperty().addListener((observable, oldValue, newValue) -> computeExam());

        spnPointsWrittenA.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spnPointsWrittenB.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spnPointsPractical.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spnPointsOral.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

		// enable spinners
		spnPointsWrittenA.disableProperty().bind(
				pckExamDate.valueProperty().isNull()
		);
		spnPointsWrittenB.disableProperty().bind(
				pckExamDate.valueProperty().isNull()
		);
		spnPointsPractical.disableProperty().bind(
				pckExamDate.valueProperty().isNull()
		);
		spnPointsOral.disableProperty().bind(
				pckExamDate.valueProperty().isNull()
		);

	}

	/**
	 * Fills form with data to be edited.
	 *
	 * @param theData data object
	 */
	@Override
	public <U extends ModelClassExt> void fillForm(final U theData) {

		super.fillForm(theData);
		computeExam();

    }

	/**
	 * Computes if exam is passed.
	 *
	 * @since 0.14.0
	 */
	private void computeExam() {

		int iPointsWritten = spnPointsWrittenA.getValue() + spnPointsWrittenB.getValue();
		int iPointsPractical = spnPointsPractical.getValue();
		int iPointsOral = spnPointsOral.getValue();
		int iPointsSum = iPointsWritten + iPointsPractical + iPointsOral;

		int iNeededWritten = AppModel.getData().getContent().getExam().getNeededPointsWritten().getValue();
		int iNeededPractical = AppModel.getData().getContent().getExam().getNeededPointsPractical().getValue();
		int iNeededOral = AppModel.getData().getContent().getExam().getNeededPointsOral().getValue();
		int iNeededSum = AppModel.getData().getContent().getExam().getNeededPoints().getValue();

		chkPassedWritten.setSelected(
				(iPointsWritten >= iNeededWritten)
		);

		chkPassedPractical.setSelected(
				(iPointsPractical >= iNeededPractical)
		);

		chkPassedOral.setSelected(
				(iPointsOral >= iNeededOral)
		);

		chkPassed.setSelected(
				chkPassedWritten.isSelected() &&
				chkPassedPractical.isSelected() &&
				chkPassedOral.isSelected() &&
				(iPointsSum >= iNeededSum)
		);

	}

}

/* EOF */

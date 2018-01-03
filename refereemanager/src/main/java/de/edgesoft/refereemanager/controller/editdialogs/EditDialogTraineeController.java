package de.edgesoft.refereemanager.controller.editdialogs;
import java.util.ArrayList;
import java.util.Arrays;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.refereemanager.controller.inputforms.IInputFormController;
import de.edgesoft.refereemanager.jaxb.Person;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.jaxb.Trainee;
import javafx.fxml.FXML;
import javafx.scene.Parent;

/**
 * Controller for the referee edit dialog scene.
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
 * @since 0.13.0
 */
public class EditDialogTraineeController extends AbstractTabbedEditDialogController {

	/**
	 * Person data.
	 */
	@FXML
	private Parent embeddedInputFormPersonData;

	/**
	 * Person data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormPersonDataController;

	/**
	 * Contact data.
	 */
	@FXML
	private Parent embeddedInputFormContactData;

	/**
	 * Contact data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormContactDataController;

	/**
	 * Address data.
	 */
	@FXML
	private Parent embeddedInputFormAddressData;

	/**
	 * Address data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormAddressDataController;

	/**
	 * Referee data.
	 */
	@FXML
	private Parent embeddedInputFormRefereeData;

	/**
	 * Referee data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormRefereeDataController;

	/**
	 * Wish data.
	 */
	@FXML
	private Parent embeddedInputFormWishData;

	/**
	 * Wish data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormWishDataController;

	/**
	 * Training level data.
	 */
	@FXML
	private Parent embeddedInputFormTrainingLevelData;

	/**
	 * Training level data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormTrainingLevelDataController;

	/**
	 * Exam data.
	 */
	@FXML
	private Parent embeddedInputFormExamData;

	/**
	 * Exam data controller.
	 */
	@FXML
	private IInputFormController embeddedInputFormExamDataController;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {

		addInputFormController(embeddedInputFormPersonDataController);
		addInputFormController(embeddedInputFormContactDataController);
		addInputFormController(embeddedInputFormAddressDataController);
		addInputFormController(embeddedInputFormRefereeDataController);
		addInputFormController(embeddedInputFormWishDataController);
		addInputFormController(embeddedInputFormTrainingLevelDataController);
		addInputFormController(embeddedInputFormExamDataController);

		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{IDType.class, TitledIDType.class, Person.class, Referee.class, Trainee.class})));

		super.initialize();

	}

}

/* EOF */

package de.edgesoft.refereemanager.controller;
import java.time.LocalDate;
import java.util.Objects;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
 * @version 0.13.0
 * @since 0.13.0
 */
public class RefereeEditDialogController {

	/**
	 * Title text field.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private TextField txtTitle;

	/**
	 * Date picker.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private DatePicker pickDate;

	/**
	 * Eventtype combobox.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private ComboBox<String> cboEventtype;

	/**
	 * Category combobox.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private ComboBox<String> cboCategory;

	/**
	 * OK button.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Button btnOK;

	/**
	 * Cancel button.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Button btnCancel;

	/**
	 * Reference to dialog stage.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	private Stage dialogStage;

	/**
	 * Current event.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	private Referee currentReferee;

	/**
	 * OK clicked?.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	private boolean okClicked;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private void initialize() {

		// set date picker date format
		pickDate.setConverter(new StringConverter<LocalDate>() {

			@Override
			public String toString(LocalDate date) {
				if (date == null) {
					return "";
				}
				return DateTimeUtils.formatDate(date);
			}

			@Override
			public LocalDate fromString(String string) {
				return DateTimeUtils.parseDate(string, "d.M.yyyy");
			}
		});

		// enable ok button for valid entries only
		btnOK.disableProperty().bind(
				pickDate.valueProperty().isNull()
				.or(txtTitle.textProperty().isEmpty())
				.or(cboEventtype.valueProperty().isNull())
				.or(cboEventtype.valueProperty().asString().isEmpty())
				);

		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));
		btnCancel.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-cancel.png")));

	}

	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public void setDialogStage(final Stage theStage) {
        dialogStage = theStage;
    }

	/**
	 * Sets referee to be edited.
	 *
	 * @param thePerson referee
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public void setPerson(PersonModel thePerson) {

		Objects.requireNonNull(thePerson);

        currentReferee = (Referee) thePerson;

        txtTitle.setText(
        		(thePerson.getTitle() == null) ?
        				null :
        				thePerson.getTitle().getValue());
//        pickDate.setValue(
//        		(theReferee.getDate() == null) ?
//        				null :
//        				(LocalDate) theReferee.getDate().getValue());
//        cboEventtype.setValue(
//        		(theReferee.getEventtype() == null) ?
//        				null :
//        				theReferee.getEventtype().getValue());
//        cboCategory.setValue(
//        		(theReferee.getCategory() == null) ?
//        				null :
//        				theReferee.getCategory().getValue());

		// fill event type and category boxes
//		cboEventtype.setItems(FXCollections.observableArrayList(((ContentModel) AppModel.getData().getContent()).getEventtypes()));
//		cboCategory.setItems(FXCollections.observableArrayList(((ContentModel) AppModel.getData().getContent()).getCategories()));

    }

	/**
	 * Returns if user clicked ok.
	 *
	 * @return did user click ok?
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	public boolean isOkClicked() {
        return okClicked;
    }

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
    private void handleOk() {
        okClicked = false;

    	if (currentReferee.getTitle() == null) {
    		currentReferee.setTitle(new SimpleStringProperty());
    	}
        currentReferee.getTitle().setValue(txtTitle.getText());

//        if (currentReferee.getDate() == null) {
//    		currentReferee.setDate(new SimpleObjectProperty<>());
//    	}
//        currentReferee.getDate().setValue(pickDate.getValue());
//
//    	if (currentReferee.getEventtype() == null) {
//    		currentReferee.setEventtype(new SimpleStringProperty());
//    	}
//        currentReferee.getEventtype().setValue(cboEventtype.getValue());
//
//        if (currentReferee.getCategory() == null) {
//    		currentReferee.setCategory(new SimpleStringProperty());
//    	}
//        currentReferee.getCategory().setValue(cboCategory.getValue());

        okClicked = true;
        dialogStage.close();

    }

	/**
	 * Stores non-ok click and closes dialog.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
    private void handleCancel() {
		okClicked = false;
        dialogStage.close();
    }

}

/* EOF */

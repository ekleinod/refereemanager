package de.edgesoft.refereemanager.controller.inputforms;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

import de.edgesoft.edgeutils.commons.ext.ModelClassExt;
import de.edgesoft.edgeutils.javafx.ButtonUtils;
import de.edgesoft.refereemanager.controller.crud.ListCRUDController;
import de.edgesoft.refereemanager.controller.inputformparts.InputFormPartWishController;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.Wish;
import de.edgesoft.refereemanager.model.WishModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.ComboBoxUtils;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the wish data edit dialog tab.
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
public class InputFormWishDataController extends AbstractInputFormController {

	/**
	 * List view for prefers.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "prefer", jaxbclass = Referee.class)
	protected ListView<ModelClassExt> lstPrefer;

	/**
	 * CRUD buttons prefer.
	 */
	@FXML
	private Parent embeddedCRUDPrefer;

	/**
	 * CRUD buttons prefer controller.
	 */
	@FXML
	private ListCRUDController<Wish> embeddedCRUDPreferController;

	/**
	 * Add prefer.
	 */
	@FXML
	private Button btnPreferAdd;

	/**
	 * Edit prefer.
	 */
	@FXML
	private Button btnPreferEdit;

	/**
	 * Delete prefer.
	 */
	@FXML
	private Button btnPreferDelete;


	/**
	 * List view for avoids.
	 */
	@FXML
	@JAXBMatch(jaxbfield = "avoid", jaxbclass = Referee.class)
	protected ListView<ModelClassExt> lstAvoid;

	/**
	 * Add avoid.
	 */
	@FXML
	private Button btnAvoidAdd;

	/**
	 * Edit avoid.
	 */
	@FXML
	private Button btnAvoidEdit;

	/**
	 * Delete avoid.
	 */
	@FXML
	private Button btnAvoidDelete;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {

        // setup list views
        lstPrefer.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());
        lstAvoid.setCellFactory(ComboBoxUtils.getCallbackModelClassExt());

		// enable buttons
        ButtonUtils.bindDisable(btnPreferEdit, lstPrefer);
        ButtonUtils.bindDisable(btnPreferDelete, lstPrefer);

        ButtonUtils.bindDisable(btnAvoidEdit, lstAvoid);
        ButtonUtils.bindDisable(btnAvoidDelete, lstAvoid);

		// icons
		btnPreferAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnAvoidAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));

		btnPreferEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnAvoidEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));

		btnPreferDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));
		btnAvoidDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

		// init embedded views
		embeddedCRUDPreferController.initCRUDController(Wish.class, "Bevorzugt schiedsen", "Wunsch");

		// add nodes to overview scene
		Map.Entry<Parent, FXMLLoader> pneInputFormPartNode = Resources.loadNode("inputformparts/InputFormPartWish");
		embeddedCRUDPreferController.addInputFormPart(pneInputFormPartNode.getKey());

	}

	/**
	 * Opens edit dialog for new data.
	 */
	@FXML
	private void handlePreferAdd() {
		addWish(lstPrefer, new WishModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@FXML
	private void handlePreferEdit() {
		editWish(lstPrefer);
	}

	/**
	 * Deletes selected data from list.
	 */
	@FXML
	private void handlePreferDelete() {
		deleteWish(lstPrefer);
	}

	/**
	 * Opens edit dialog for new data.
	 */
	@FXML
	private void handleAvoidAdd() {
		addWish(lstAvoid, new WishModel());
	}

	/**
	 * Opens edit dialog for editing selected data.
	 */
	@FXML
	private void handleAvoidEdit() {
		editWish(lstAvoid);
	}

	/**
	 * Deletes selected data from list.
	 */
	@FXML
	private void handleAvoidDelete() {
		deleteWish(lstAvoid);
	}

	/**
	 * Opens edit dialog for new data.
	 *
	 * @param theListView list view
	 * @param newWish new wish
	 */
	private void addWish(ListView<ModelClassExt> theListView, WishModel newWish) {

		if (showWishEditDialog(newWish)) {
			theListView.getItems().add(newWish);
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @param theListView list view
	 */
	private void editWish(ListView<ModelClassExt> theListView) {

		if (!theListView.getSelectionModel().isEmpty()) {
			showWishEditDialog((WishModel) theListView.getSelectionModel().getSelectedItem());
			theListView.refresh();
		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @param theListView list view
	 */
	private void deleteWish(ListView<ModelClassExt> theListView) {

		if (!theListView.getSelectionModel().isEmpty()) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, getDialogStage(),
					"Löschbestätigung",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", theListView.getSelectionModel().getSelectedItem().getDisplayText().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						theListView.getItems().remove(theListView.getSelectionModel().getSelectedItem());
			});

		}

	}

	/**
	 * Opens the wish edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theWish the wish to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	private boolean showWishEditDialog(WishModel theWish) {

		Objects.requireNonNull(theWish);

		Map.Entry<Parent, FXMLLoader> pneLoad = Resources.loadNode("WishEditDialog");

		// Create the dialog Stage.
		Stage editDialogStage = new Stage();
		editDialogStage.initModality(Modality.WINDOW_MODAL);
		editDialogStage.initOwner(getDialogStage());
		editDialogStage.setTitle("Wunsch editieren");

		editDialogStage.setScene(new Scene(pneLoad.getKey()));

		// Set the referee
		InputFormPartWishController editController = pneLoad.getValue().getController();
		editController.setDialogStage(editDialogStage);
		editController.setData(theWish);

		// Show the dialog and wait until the user closes it
		editDialogStage.showAndWait();

		return editController.isOkClicked();

	}

}

/* EOF */

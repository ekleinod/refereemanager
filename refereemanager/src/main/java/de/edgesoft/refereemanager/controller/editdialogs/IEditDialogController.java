package de.edgesoft.refereemanager.controller.editdialogs;

import de.edgesoft.refereemanager.controller.inputforms.IInputFormController;
import javafx.stage.Stage;

/**
 * Interface for the edit dialog controller.
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
public interface IEditDialogController extends IInputFormController {

	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 */
	public void setDialogStage(final Stage theStage);

	/**
	 * Returns dialog stage.
	 *
	 * @return dialog stage
	 */
	public Stage getDialogStage();

	/**
	 * Returns if user clicked ok.
	 *
	 * @return did user click ok?
	 */
	public boolean isOkClicked();

	/**
	 * Sets if user clicked ok.
	 *
	 * @param isOKClicked did user click ok?
	 */
	public void setOkClicked(final boolean isOKClicked);

	/**
	 * Validates input, stores ok click, and closes dialog; does nothing for invalid input.
	 */
    public void handleOk();

	/**
	 * Stores non-ok click and closes dialog.
	 */
    public void handleCancel();

}

/* EOF */

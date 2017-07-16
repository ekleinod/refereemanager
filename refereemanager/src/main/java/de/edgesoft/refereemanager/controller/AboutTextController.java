package de.edgesoft.refereemanager.controller;

import de.edgesoft.refereemanager.RefereeManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

/**
 * Controller for about text.
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
 * @since 0.10.0
 */
public class AboutTextController {

	/**
	 * Email link.
	 *
	 * @param theEvent action event
	 *
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	@SuppressWarnings("static-method")
	@FXML
	private void handleEmailLinkAction(final ActionEvent theEvent) {
		RefereeManager.hostServices.showDocument(String.format("mailto:%s", ((Hyperlink) theEvent.getTarget()).getText()));
	}

	/**
	 * Web link.
	 *
	 * @param theEvent action event
	 *
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	@SuppressWarnings("static-method")
	@FXML
	private void handleWebLinkAction(final ActionEvent theEvent) {
		RefereeManager.hostServices.showDocument(((Hyperlink) theEvent.getTarget()).getText());
	}

}

/* EOF */

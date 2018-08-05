package de.edgesoft.refereemanager.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Labeled;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * Abstract controller with hyperlink abilities.
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
public abstract class AbstractHyperlinkController {

	/**
	 * Email link.
	 *
	 * @param theEvent action event
	 */
	@FXML
	protected void handleEmailLinkAction(final ActionEvent theEvent) {
		try {
			Desktop.getDesktop().mail(new URI(String.format("mailto:%s", ((Hyperlink) theEvent.getTarget()).getText())));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Web link.
	 *
	 * @param theEvent action event
	 */
	@FXML
	protected void handleWebLinkAction(final ActionEvent theEvent) {
		try {
			Desktop.getDesktop().browse(new URI(((Hyperlink) theEvent.getTarget()).getText()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Copy url.
	 */
	protected void copyURL(
			final Labeled theURLHolder
			) {

		ClipboardContent clpContent = new ClipboardContent();
		clpContent.putString(theURLHolder.getText());
		Clipboard.getSystemClipboard().setContent(clpContent);

	}

}

/* EOF */

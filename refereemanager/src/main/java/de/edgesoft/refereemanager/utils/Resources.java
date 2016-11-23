package de.edgesoft.refereemanager.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Map;

import de.edgesoft.refereemanager.RefereeManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Resources helper.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 * 
 * This file is part of refereemanager.
 * 
 * refereemanager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * refereemanager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with refereemanager.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Ekkart Kleinod
 * @version 0.10.0
 * @since 0.10.0
 */
public class Resources {

	/**
	 * Loads image from resources.
	 *
	 * @param theImagePath image path
	 * @return loaded image
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static Image loadImage(final String theImagePath) {
		return new Image(RefereeManager.class.getClassLoader().getResourceAsStream(theImagePath));
	}

	/**
	 * Loads fxml pane from resources.
	 *
	 * @param thePaneName pane name
	 * @return loaded pane
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static Map.Entry<Pane, FXMLLoader> loadPane(final String thePaneName) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RefereeManager.class.getResource(String.format("view/%s.fxml", thePaneName)));
            return new AbstractMap.SimpleImmutableEntry<>((Pane) loader.load(), loader);

        } catch (IOException e) {
            RefereeManager.logger.catching(e);
            return null;
        }
    }

	/**
	 * Loads file from resources.
	 *
	 * @param theFileName pane name
	 * @return loaded file as string
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static String loadFile(final String theFileName) {

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(RefereeManager.class.getClassLoader().getResourceAsStream(theFileName)))) {

			StringBuilder sbReturn = new StringBuilder();
			String sLine = null;

			while ((sLine = reader.readLine()) != null) {
			    sbReturn.append(sLine);
			}

			return sbReturn.toString();

		} catch (Exception e) {
            RefereeManager.logger.catching(e);
			return "";
		}

    }

}

/* EOF */

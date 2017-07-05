package de.edgesoft.refereemanager.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Properties;

import de.edgesoft.refereemanager.RefereeManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * Resources helper.
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
public class Resources {

	/**
	 * Project properties singleton.
	 *
	 * @since 0.14.0
	 */
	private static Properties prpProject = null;

	/**
	 * Loads image from resources.
	 *
	 * @param theImagePath image path
	 * @return loaded image
	 */
	public static Image loadImage(final String theImagePath) {
		return new Image(RefereeManager.class.getClassLoader().getResourceAsStream(theImagePath));
	}

	/**
	 * Loads fxml pane from resources.
	 *
	 * @param thePaneName pane name
	 * @return loaded pane
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

	/**
	 * Loads project properties from resources.
	 *
	 * @return project properties
	 *
	 * @since 0.14.0
	 */
	public static Properties getProjectProperties() {
		if (prpProject == null) {
			prpProject = loadProperties("project.properties");
		}
		return prpProject;
    }

	/**
	 * Loads properties from resources.
	 *
	 * @param theFileName pane name
	 * @return loaded file as properties
	 *
	 * @since 0.14.0
	 */
	public static Properties loadProperties(final String theFileName) {

		Properties prpReturn = new Properties();

		try {

			prpReturn.load(RefereeManager.class.getClassLoader().getResourceAsStream(theFileName));

		} catch (Exception e) {
			RefereeManager.logger.catching(e);
		}

		return prpReturn;

    }

}

/* EOF */

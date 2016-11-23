package de.edgesoft.refereemanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.WebsiteHelper;

/**
 * Website operations.
 *
 * This is just hacked, because of lack of time.
 * Thus, the code is cluttered with view information.
 * Beware :)
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
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
 * @version 0.9.0
 * @since 0.9.0
 */
public class Website extends AbstractMainClass {

	/**
	 * Command line entry point.
	 *
	 * @param args command line arguments
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public static void main(String[] args) {
		new Website().executeOperation(args);
	}

	/**
	 * Programmatic entry point, initializing and executing main functionality.
	 *
	 * - set description setDescription("...");
	 * - add options addOption("short", "long", "description", argument?, required?);
	 * - call init(args);
	 * - call operation execution with arguments
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	@Override
	public void executeOperation(final String[] args) {

		setDescription("Assignments operations.");

		addOption("p", "path", MessageFormat.format("input path of data (default: {0}).", Prefs.get(PrefKey.PATH_DATABASE)), true, false);
		addOption("d", "database", MessageFormat.format("database file name pattern (default: {0}).", Prefs.get(PrefKey.FILENAME_PATTERN_DATABASE)), true, false);
		addOption("s", "season", "season (empty for current season).", true, false);
		addOption("t", "template", "template path.", true, true);
		addOption("o", "output", "output path.", true, true);

		init(args);

		websiteOperation(getOptionValue("p"), getOptionValue("d"), getOptionValue("s"), getOptionValue("t"), getOptionValue("o"));

	}

	/**
	 * Executes website operation.
	 *
	 * @param theDBPath input path
	 * @param theDBFile db filename (null = {@link RefereeManager#DATAFILENAMEPATTERN})
	 * @param theSeason season (null = current season)
	 * @param theTemplatepath template path
	 * @param theOutputpath output path
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public void websiteOperation(final String theDBPath, final String theDBFile, final String theSeason, final String theTemplatepath, final String theOutputpath) {

		RefereeManager.logger.debug("start.");

		Objects.requireNonNull(theTemplatepath, "template path must not be null");
		Objects.requireNonNull(theOutputpath, "output path must not be null");

		Integer iSeason = (theSeason == null) ? SeasonModel.getCurrentStartYear() : Integer.valueOf(theSeason);

		Path pathDBFile = Paths.get((theDBPath == null) ? Prefs.get(PrefKey.PATH_DATABASE) : theDBPath,
				String.format(((theDBFile == null) ? Prefs.get(PrefKey.FILENAME_PATTERN_DATABASE) : theDBFile), iSeason));

		Path pathTemplate = Paths.get(Prefs.get(PrefKey.PATH_TEMPLATES), theTemplatepath);

		Path pathOut = Paths.get(theOutputpath);

		websiteOperation(pathDBFile, pathTemplate, pathOut);

		RefereeManager.logger.debug("stop");

	}

	/**
	 * Executes website operation.
	 *
	 * @param theDBPath database filename with path
	 * @param theTemplatePath template path
	 * @param theOutputPath output path
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public void websiteOperation(final Path theDBPath, final Path theTemplatePath, final Path theOutputPath) {

		Objects.requireNonNull(theDBPath, "database file path must not be null");
		Objects.requireNonNull(theTemplatePath, "template path must not be null");
		Objects.requireNonNull(theOutputPath, "output file path must not be null");

		try {

			RefereeManager.logger.debug(String.format("read database '%s'.", theDBPath.toString()));

			final de.edgesoft.refereemanager.jaxb.RefereeManager mgrData = JAXBFiles.unmarshal(theDBPath.toString(), de.edgesoft.refereemanager.jaxb.RefereeManager.class);

			String sFile = "osr-recipients.yml";
			Path pathTemplate = Paths.get(theTemplatePath.toString(), sFile);
			RefereeManager.logger.debug(String.format("read template '%s'.", pathTemplate.toString()));
			List<String> lstTemplate = FileAccess.readFileInList(pathTemplate);
			RefereeManager.logger.debug("fill template.");
			List<String> lstFilled = WebsiteHelper.fillOSRRecipients(lstTemplate, mgrData);
			Path pathOut = Paths.get(theOutputPath.toString(), sFile);
			RefereeManager.logger.debug(String.format("write output to '%s'.", pathOut.toString()));
			FileAccess.writeFile(pathOut, lstFilled);

			sFile = "venues.yml";
			pathTemplate = Paths.get(theTemplatePath.toString(), sFile);
			RefereeManager.logger.debug(String.format("read template '%s'.", pathTemplate.toString()));
			lstTemplate = FileAccess.readFileInList(pathTemplate);
			RefereeManager.logger.debug("fill template.");
			lstFilled = WebsiteHelper.fillVenues(lstTemplate, mgrData, true);
			pathOut = Paths.get(theOutputPath.toString(), sFile);
			RefereeManager.logger.debug(String.format("write output to '%s'.", pathOut.toString()));
			FileAccess.writeFile(pathOut, lstFilled);

			sFile = "venues_pages.yml";
			pathTemplate = Paths.get(theTemplatePath.toString(), sFile);
			RefereeManager.logger.debug(String.format("read template '%s'.", pathTemplate.toString()));
			lstTemplate = FileAccess.readFileInList(pathTemplate);
			RefereeManager.logger.debug("fill template.");
			lstFilled = WebsiteHelper.fillVenues(lstTemplate, mgrData, false);
			pathOut = Paths.get(theOutputPath.toString(), sFile);
			RefereeManager.logger.debug(String.format("write output to '%s'.", pathOut.toString()));
			FileAccess.writeFile(pathOut, lstFilled);

			sFile = "events.yml";
			pathTemplate = Paths.get(theTemplatePath.toString(), sFile);
			RefereeManager.logger.debug(String.format("read template '%s'.", pathTemplate.toString()));
			lstTemplate = FileAccess.readFileInList(pathTemplate);
			RefereeManager.logger.debug("fill template.");
			lstFilled = WebsiteHelper.fillEvents(lstTemplate, mgrData);
			pathOut = Paths.get(theOutputPath.toString(), sFile);
			RefereeManager.logger.debug(String.format("write output to '%s'.", pathOut.toString()));
			FileAccess.writeFile(pathOut, lstFilled);

		} catch (Exception e) {
			RefereeManager.logger.error(e);
			e.printStackTrace();
		}

	}

}

/* EOF */

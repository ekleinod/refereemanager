package de.edgesoft.refereemanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.model.ContactModel;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.utils.ArgumentStatusType;
import de.edgesoft.refereemanager.utils.Constants;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.TemplateHelper;

/**
 * Fill the referee list.
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
 * @version 0.8.0
 * @since 0.5.0
 */
public class RefereeList extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static void main(String[] args) {
		new RefereeList().executeOperation(args);
	}

	/**
	 * Programmatic entry point, initializing and executing main functionality.
	 * 
	 * - set description setDescription("...");
	 * - add options addOption("short", "long", "description", argument?, required?);
	 * - call init(args);
	 * - call operation execution with arguments
	 * 
	 * @version 0.8.0
	 * @since 0.5.0
	 */
	@Override
	public void executeOperation(final String[] args) {
		
		setDescription("Generates referee lists.");
		
		addOption("p", "path", MessageFormat.format("input path of data (default: {0}).", Prefs.get(PrefKey.PATH_DATABASE)), true, false);
		addOption("d", "database", MessageFormat.format("database file name pattern (default: {0}).", Prefs.get(PrefKey.FILENAME_PATTERN_DATABASE)), true, false);
		addOption("s", "season", "season (empty for current season).", true, false);
		addOption("t", "template", "template to fill.", true, true);
		addOption("o", "output", "output file (empty for template + '.out').", true, false);
		addOption("a", "status", "status (all (default), active, inactive).", true, false);
		addOption("e", "editor", "also use data that is editor only.", false, false);
		addOption("i", "privateonly", "use only private contacts.", false, false);
		
		init(args);
		
		listReferees(getOptionValue("p"), getOptionValue("d"), getOptionValue("s"), 
				getOptionValue("t"), getOptionValue("o"), getOptionValue("a"), hasOption("e"), hasOption("i"));
		
	}

	/**
	 * Fills list of referees.
	 * 
	 * @param theDBPath input path
	 * @param theDBFile db filename (null = {@link Constants#DATAFILENAMEPATTERN})
	 * @param theSeason season (null = current season)
	 * @param theTemplatefile template file
	 * @param theOutputfile output file (null = template + ".out")
	 * @param theStatus status (all (default), activeonly)
	 * @param isEditor use editor only data?
	 * @param isPrivateOnly use only private contacts?
	 * 
	 * @version 0.8.0
	 * @since 0.5.0
	 */
	public void listReferees(final String theDBPath, final String theDBFile, final String theSeason, 
			final String theTemplatefile, final String theOutputfile, final String theStatus, final boolean isEditor, final boolean isPrivateOnly) {
		
		Constants.logger.debug("start.");
		
		Objects.requireNonNull(theTemplatefile, "template file must not be null");
		
		Integer iSeason = (theSeason == null) ? SeasonModel.getCurrentStartYear() : Integer.valueOf(theSeason);
		
		Path pathDBFile = Paths.get((theDBPath == null) ? Prefs.get(PrefKey.PATH_DATABASE) : theDBPath,
				String.format(((theDBFile == null) ? Prefs.get(PrefKey.FILENAME_PATTERN_DATABASE) : theDBFile), iSeason));
		
		String sOutFile = (theOutputfile == null) ? String.format("%s.out", theTemplatefile) : theOutputfile;
		
		ArgumentStatusType argStatus = ArgumentStatusType.ALL;
		try {
			argStatus = ArgumentStatusType.fromValue(theStatus);
		} catch (IllegalArgumentException e) {
			// do nothing, remains "all"
		}
		
		listReferees(pathDBFile, Paths.get(Prefs.get(PrefKey.PATH_TEMPLATES), theTemplatefile), Paths.get(sOutFile), argStatus, isEditor, isPrivateOnly);
		
		Constants.logger.debug("stop");
		
	}
	
	/**
	 * Fills list of referees.
	 * 
	 * @param theDBPath database filename with path
	 * @param theTemplatePath template file
	 * @param theOutputPath output file
	 * @param theStatus status
	 * @param isEditor use editor only data?
	 * @param isPrivateOnly use only private contacts?
	 * 
	 * @version 0.8.0
	 * @since 0.5.0
	 */
	public void listReferees(final Path theDBPath, final Path theTemplatePath, final Path theOutputPath, 
			final ArgumentStatusType theStatus, final boolean isEditor, final boolean isPrivateOnly) {
		
		Objects.requireNonNull(theDBPath, "database file path must not be null");
		Objects.requireNonNull(theTemplatePath, "template file path must not be null");
		Objects.requireNonNull(theOutputPath, "output file path must not be null");
		Objects.requireNonNull(theStatus, "status must not be null");
		
		try {
			
			Constants.logger.debug(String.format("read template '%s'.", theTemplatePath.toString()));
			
			final List<String> lstTemplate = FileAccess.readFileInList(theTemplatePath);
			
			Constants.logger.debug(String.format("read database '%s'.", theDBPath.toString()));
			
			final RefereeManager mgrData = JAXBFiles.unmarshal(theDBPath.toString(), RefereeManager.class);
			
			Constants.logger.debug("fill template.");
			
			ContactModel.isPrivateOnly = isPrivateOnly;
			List<String> lstFilled = TemplateHelper.fillTemplate(lstTemplate, mgrData, null, 0, theStatus, isEditor);
			
			Constants.logger.debug(String.format("write referee list to '%s'.", theOutputPath.toString()));
			
			FileAccess.writeFile(theOutputPath, lstFilled);
			
		} catch (Exception e) {
			Constants.logger.error(e);
			e.printStackTrace();
		}
		
	}
	
}

/* EOF */

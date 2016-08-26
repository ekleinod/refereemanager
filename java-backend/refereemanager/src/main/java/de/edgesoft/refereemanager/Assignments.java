package de.edgesoft.refereemanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.jaxb.Team;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.utils.Constants;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.TemplateHelper;

/**
 * Assignment operations.
 * 
 * This is just hacked, because of lack of time.
 * Thus, the code is cluttered with view information.
 * Beware :)
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
 * @version 0.9.0
 * @since 0.9.0
 */
public class Assignments extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public static void main(String[] args) {
		new Assignments().executeOperation(args);
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
		addOption("t", "template", "template to fill.", true, true);
		addOption("o", "output", "output file or path.", true, true);
		
		init(args);
		
		assignmentOperation(getOptionValue("p"), getOptionValue("d"), getOptionValue("s"), getOptionValue("t"), getOptionValue("o"));
		
	}

	/**
	 * Executes assignment operation.
	 * 
	 * @param theDBPath input path
	 * @param theDBFile db filename (null = {@link Constants#DATAFILENAMEPATTERN})
	 * @param theSeason season (null = current season)
	 * @param theTemplatefile template file
	 * @param theOutputfile output file
	 * 
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public void assignmentOperation(final String theDBPath, final String theDBFile, final String theSeason, final String theTemplatefile, final String theOutputfile) {
		
		Constants.logger.debug("start.");
		
		Objects.requireNonNull(theTemplatefile, "template file must not be null");
		Objects.requireNonNull(theOutputfile, "output file/path must not be null");
		
		Integer iSeason = (theSeason == null) ? SeasonModel.getCurrentStartYear() : Integer.valueOf(theSeason);

		Path pathDBFile = Paths.get((theDBPath == null) ? Prefs.get(PrefKey.PATH_DATABASE) : theDBPath,
				String.format(((theDBFile == null) ? Prefs.get(PrefKey.FILENAME_PATTERN_DATABASE) : theDBFile), iSeason));
		
		Path pathOut = Paths.get(theOutputfile);
		
		dbOperation(pathDBFile, Paths.get(Prefs.get(PrefKey.PATH_TEMPLATES), theTemplatefile), pathOut);
		
		Constants.logger.debug("stop");
		
	}
	
	/**
	 * Executes assignment operation.
	 * 
	 * @param theDBPath database filename with path
	 * @param theTemplatePath template file
	 * @param theOutputPath output file
	 * @param theDBOperation database operation
	 * 
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public void dbOperation(final Path theDBPath, final Path theTemplatePath, final Path theOutputPath) {
		
		Objects.requireNonNull(theDBPath, "database file path must not be null");
		Objects.requireNonNull(theTemplatePath, "template file path must not be null");
		Objects.requireNonNull(theOutputPath, "output file path must not be null");
		
		try {
			
			Constants.logger.debug(String.format("read template '%s'.", theTemplatePath.toString()));
			
			final List<String> lstTemplate = FileAccess.readFileInList(theTemplatePath);
			
			Constants.logger.debug(String.format("read database '%s'.", theDBPath.toString()));
			
			final RefereeManager mgrData = JAXBFiles.unmarshal(theDBPath.toString(), RefereeManager.class);
			
			Constants.logger.debug("fill template.");

			// start hack
			List<String> lstContent = new ArrayList<>();
			
			for (League league : ((ContentModel) mgrData.getContent()).getUsedLeagues()) {
				lstContent.add(TemplateHelper.replaceTextAndConditions("<!--\\league{-->**generated league:displaytitle**<!--}-->", "league:displaytitle", league.getDisplayTitle()));
				lstContent.add("");
				
				for (Team team : ((ContentModel) mgrData.getContent()).getLocalHomeTeams(league)) {
					lstContent.add(TemplateHelper.replaceTextAndConditions("<!--\\club{-->**generated team:displaytitle**<!--}-->", "team:displaytitle", team.getDisplayTitle()));
					lstContent.add("");
				}
			}
			
			List<String> lstFilled = new ArrayList<>();
			
			for (String theLine : lstTemplate) {
				lstFilled.add(TemplateHelper.replaceTextAndConditions(theLine, "content", TemplateHelper.toText(lstContent)));
			}
//			lstFilled = TemplateHelper.fillTemplate(lstFilled, mgrData, null, 0, ArgumentStatusType.ALL, true);
			
			// end hack
			Constants.logger.debug(String.format("write assignment output to '%s'.", theOutputPath.toString()));
			
			FileAccess.writeFile(theOutputPath, lstFilled);
			
		} catch (Exception e) {
			Constants.logger.error(e);
			e.printStackTrace();
		}
		
	}
	
}

/* EOF */

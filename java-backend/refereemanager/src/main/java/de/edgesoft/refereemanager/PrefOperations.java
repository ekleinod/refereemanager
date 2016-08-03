package de.edgesoft.refereemanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.refereemanager.utils.Constants;

/**
 * Preferences operations.
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
 * @since 0.8.0
 */
public class PrefOperations extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public static void main(String[] args) {
		new PrefOperations().executeOperation(args);
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
	 * @since 0.8.0
	 */
	@Override
	public void executeOperation(final String[] args) {
		
		setDescription("Preferences operations.");
		
		addOption("f", "file", "filename.", true, true);
		addOption("i", "import", "import preferences.", false, false);
		addOption("x", "export", "export preferences.", false, false);
		
		init(args);
		
		prefOperation(getOptionValue("f"), hasOption("i"), hasOption("x"));
		
	}

	/**
	 * Executes preferences operation.
	 * 
	 * @param theFile filename
	 * @param isImport import?
	 * @param isExport export?
	 * 
	 * @version 0.8.0
	 * @since 0.8.0
	 */
	public void prefOperation(final String theFile, final boolean isImport, final boolean isExport) {
		
		Constants.logger.info("start.");
		
		Objects.requireNonNull(theFile, "filename must not be null");
		
		if (isImport) {
			Constants.logger.info(String.format("importing from '%s'.", theFile));
			
			try (InputStream stmIn = new FileInputStream(theFile)) {
				Prefs.importPrefs(stmIn);
			} catch (IOException | InvalidPreferencesFormatException e) {
				e.printStackTrace();
			}
		}
		
		if (isExport) {
			Constants.logger.info(String.format("exporting to '%s'.", theFile));
			
			try (OutputStream stmOut = new FileOutputStream(theFile)) {
				Prefs.exportPrefs(stmOut);
			} catch (IOException | BackingStoreException e) {
				e.printStackTrace();
			}
		}
		
		Constants.logger.info("stop");
		
	}
	
}

/* EOF */

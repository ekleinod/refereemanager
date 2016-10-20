package de.edgesoft.refereemanager.utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.model.PersonModel;

/**
 * Provides methods and properties for IDs.
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
public class IDHelper {

	/**
	 * Fills ID template.
	 *
	 * @param theTemplate template
	 * @param theData data
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public static List<List<String>> fillIDs(final List<String> theSingleTemplate, final List<String> theA4Template, 
			final List<String> theValidTemplate, final List<String> theValidA4Template, 
			final RefereeManager theData, final Path theImagePath) {

		Objects.requireNonNull(theSingleTemplate, "single template must not be null");
		Objects.requireNonNull(theA4Template, "a4 template must not be null");
		Objects.requireNonNull(theValidTemplate, "valid template must not be null");
		Objects.requireNonNull(theValidA4Template, "valid a4 template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		
		List<List<String>> lstSingleSVGs = new ArrayList<>();
		
		// loop through referees
		for (final Referee theReferee : theData.getContent().getReferee().stream()
				.sorted(PersonModel.NAME_FIRSTNAME)
				.collect(Collectors.toList())) {
			
			if (!theReferee.getFirstName().equals("Metin")) continue;
			
			Constants.logger.debug(theReferee.getDisplayTitle());
			
			List<String> lstSingle = TemplateHelper.fillText(theSingleTemplate, theReferee, theData);
			lstSingle = TemplateHelper.fillTextAndConditions(lstSingle, "imagepath", theImagePath.toAbsolutePath().normalize().toString());
			
			lstSingleSVGs.add(lstSingle);
			
			break;
			
//			List<String> lstSingle = new ArrayList<>();
//			for (String theLine : theSingleTemplate) {
//				
//				String sFilledLine = TemplateHelper.replaceTextAndConditions(theLine, "VSR-filename", 
//						Paths.get(theImagePath.toString(), String.format("%s.jpg", theReferee.getFileName())).toAbsolutePath().toString());
//				
//				sFilledLine = TemplateHelper.replaceTextAndConditions(sFilledLine, "VSR-since", theReferee.getId());
//				sFilledLine = TemplateHelper.replaceTextAndConditions(sFilledLine, "VSR-Name", theReferee.getFullName());
//				
//				lstSingle.add(sFilledLine);
//			}
			
		}

		List<List<String>> lstReturn = new ArrayList<>(lstSingleSVGs);
		
//		lstReturn.add(lstSingleSVGs);

		return lstReturn;

	}

}

/* EOF */

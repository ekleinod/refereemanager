package de.edgesoft.refereemanager.utils;

import java.io.File;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import de.edgesoft.refereemanager.RefereeManager;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.TrainingLevel;
import de.edgesoft.refereemanager.model.PersonModel;
import de.edgesoft.refereemanager.model.RefereeModel;

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
	 * @todo filling needs work, see comment
	 *
	 * @param theTemplate template
	 * @param theData data
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public static List<List<String>> fillIDs(final List<String> theSingleTemplate, final List<String> theA4Template, 
			final List<String> theValidTemplate, final List<String> theValidA4Template, 
			final de.edgesoft.refereemanager.jaxb.RefereeManager theData, final Path theImagePath) {

		Objects.requireNonNull(theSingleTemplate, "single template must not be null");
		Objects.requireNonNull(theA4Template, "a4 template must not be null");
		Objects.requireNonNull(theValidTemplate, "valid template must not be null");
		Objects.requireNonNull(theValidA4Template, "valid a4 template must not be null");
		Objects.requireNonNull(theData, "data must not be null");
		
		RefereeManager.logger.debug(MessageFormat.format("{0} referees", theData.getContent().getReferee().size()));
		
		// fill all single svg templates
		List<List<String>> lstSingleSVGs = new ArrayList<>();
		for (final Referee theReferee : theData.getContent().getReferee().stream()
				.sorted(PersonModel.NAME_FIRSTNAME)
				.collect(Collectors.toList())) {
			
			File fleImage = new File(theImagePath.toString(), String.format("%s.jpg", theReferee.getFileName()));
			if (fleImage.exists()) {
				
				RefereeManager.logger.debug(theReferee.getDisplayTitle());
				
				List<String> lstSingle = theSingleTemplate;
				
				// originally this line was
				// lstSingle = TemplateHelper.fillText(lstSingle, theReferee, theData);
				// but this is extremely slow, thus I fill data directly, change this back when filling is optimized
				lstSingle = TemplateHelper.fillTextAndConditions(lstSingle, "referee:fullname", theReferee.getFullName());
				lstSingle = TemplateHelper.fillTextAndConditions(lstSingle, "referee:filename", theReferee.getFileName());
				
				TrainingLevel lvlLocal = ((RefereeModel) theReferee).getLocalTrainingLevel();
				boolean missesDate = (lvlLocal == null) || (lvlLocal.getSince() == null);
				lstSingle = TemplateHelper.fillTextAndConditions(lstSingle, "referee:localtraininglevel:since:datemedium",
						missesDate ? "---" :
							lvlLocal.getSince().format(DateTimeFormatter.ofPattern(Prefs.get(DateTimeFormat.DATEMEDIUM), Locale.forLanguageTag(Prefs.get(PrefKey.LOCALE)))));
				if (missesDate) {
					RefereeManager.logger.error(MessageFormat.format("Missing date for {0}", theReferee.getDisplayTitle()));
				}
				
				// this is correct
				lstSingle = TemplateHelper.fillTextAndConditions(lstSingle, "imagepath", theImagePath.toAbsolutePath().normalize().toString());
				
				lstSingleSVGs.add(lstSingle);
				
			}
			
		}
		
		RefereeManager.logger.debug(MessageFormat.format("{0} referee ids", lstSingleSVGs.size()));
		
		// fill A4 templates with 5 single svgs each
		List<List<String>> lstReturn = new ArrayList<>();
		List<String> lstA4 = null;
		int i = 0;
		for (List<String> lstSingle : lstSingleSVGs) {
			if (i == 0) {
				lstA4 = theA4Template;
			}
			
			lstA4 = TemplateHelper.fillTextAndConditions(lstA4, String.format("id-single-%d", i), TemplateHelper.toText(lstSingle));
			
			if (i == 4) {
				lstReturn.add(lstA4);
			}
			i = (i + 1) % 5;
		}
		
		// fill missing slots if needed
		if (i > 0) {
			for (int j = i; j < 5; j++) {
				lstA4 = TemplateHelper.fillTextAndConditions(lstA4, String.format("id-single-%d", j), "");
			}
			lstReturn.add(lstA4);
		}

		RefereeManager.logger.debug(MessageFormat.format("{0} sheets", lstReturn.size()));
		
		return lstReturn;

	}

}

/* EOF */

package de.edgesoft.refereemanager.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBElement;

import de.edgesoft.refereemanager.jaxb.League;
import de.edgesoft.refereemanager.jaxb.Person;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * League model, additional methods for jaxb model class.
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
 * @since 0.9.0
 */
public class LeagueModel extends League {

	/**
	 * Comparator rank.
	 */
	public static final Comparator<League> RANK = Comparator.comparingInt(league -> league.getRank().get());

	/**
	 * Comparator rank and display title.
	 *
	 * @since 0.10.0
	 */
	public static final Comparator<League> RANK_DISPLAYTITLE = RANK.thenComparing(TitledIDTypeModel.DISPLAYTITLE);

	/**
	 * Returns if league is national league.
	 *
	 * @return is league national league
	 */
	@Override
	public SimpleBooleanProperty getNational() {
		return (super.getNational() == null) ? new SimpleBooleanProperty(Boolean.FALSE) : super.getNational();
	}

	/**
	 * Gets the value of the refereeReportRecipient property.
	 *
	 * For some reason, the generated code is correct but there is a list of {@link JAXBElement} in
	 * the refereeReportRecipient property.
	 *
	 * Until the issue is solved, this method converts this to the correct list of {@link Person}
	 *
	 */
	@Override
	public List<Person> getRefereeReportRecipient() {
		List<Person> lstReturn = new ArrayList<>();

		for (Object personObject : super.getRefereeReportRecipient()) {
			lstReturn.add(((JAXBElement<Person>) personObject).getValue());
		}

		return lstReturn;
	}

}

/* EOF */

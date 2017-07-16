package de.edgesoft.refereemanager.model;

import java.text.Collator;
import java.util.Comparator;

import de.edgesoft.refereemanager.jaxb.TitledIDType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * TitledIDType model, additional methods for jaxb model class.
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
 * @since 0.5.0
 */
public class TitledIDTypeModel extends TitledIDType {

	/**
	 * Comparator title.
	 *
	 * @version 0.10.0
	 */
	public static final Comparator<TitledIDType> TITLE = Comparator.comparing(titled -> titled.getTitle().get(), Collator.getInstance());

	/**
	 * Comparator shorttitle.
	 *
	 * @todo not sorted with collator
	 *
	 * @version 0.10.0
	 */
	public static final Comparator<TitledIDType> SHORTTITLE = Comparator.comparing(titled -> titled.getShorttitle().get(), Comparator.nullsFirst(String::compareTo));

	/**
	 * Comparator shorttitle then title.
	 *
	 * @todo does not work with shorttitle == null
	 *
	 * @version 0.9.0
	 */
	public static final Comparator<TitledIDType> SHORTTITLE_TITLE = SHORTTITLE.thenComparing(TITLE);

	/**
	 * Comparator displaytitle.
	 *
	 * @version 0.10.0
	 */
	public static final Comparator<TitledIDTypeModel> DISPLAYTITLE = Comparator.comparing(titled -> titled.getDisplayTitleShort().get(), Collator.getInstance());

	/**
	 * Display title.
	 *
	 * @return display title
	 *
	 * @version 0.10.0
	 */
	public SimpleStringProperty getDisplayTitleShort() {
		return (getShorttitle() == null) ? getDisplayTitle() : getShorttitle();
	}

	/**
	 * Display title.
	 *
	 * @return display title
	 *
	 * @version 0.10.0
	 */
	public SimpleStringProperty getDisplayTitle() {
		return (getTitle() == null) ? new SimpleStringProperty(getId()) : getTitle();
	}

	/**
	 * Returns display text.
	 *
	 * @version 0.14.0
	 */
	@Override
	public StringProperty getDisplayText() {
		return getDisplayTitle();
	}

}

/* EOF */

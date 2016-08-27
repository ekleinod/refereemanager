package de.edgesoft.refereemanager.model;

import java.util.Comparator;

import de.edgesoft.refereemanager.jaxb.TrainingLevelType;

/**
 * TrainingLevelType model, additional methods for jaxb model class.
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
 * @since 0.7.0
 */
public class TrainingLevelTypeModel extends TrainingLevelType {
	
	/** 
	 * Comparator rank. 
	 * 
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public static final Comparator<TrainingLevelType> RANK = Comparator.comparingInt(TrainingLevelType::getRank);
	
}

/* EOF */

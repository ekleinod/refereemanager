package de.edgesoft.refereemanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.TrainingLevel;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Referee model, additional methods for jaxb model class.
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
 * @version 0.10.0
 * @since 0.5.0
 */
public class RefereeModel extends Referee {

	/**
	 * Filter predicate for all status types.
	 *
	 * @version 0.6.0
	 * @since 0.5.0
	 */
	public static Predicate<Referee> ALL = referee -> true;

	/**
	 * Filter predicate for active status types.
	 *
	 * @version 0.10.0
	 * @since 0.5.0
	 */
	public static Predicate<Referee> ACTIVE = referee -> referee.getStatus().getActive().get();

	/**
	 * Filter predicate for inactive status types.
	 *
	 * @version 0.10.0
	 * @since 0.5.0
	 */
	public static Predicate<Referee> INACTIVE = referee -> !referee.getStatus().getActive().get();

	/**
	 * Filter predicate for letter only (docs by letter and no email) referees.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public static Predicate<Referee> LETTER_ONLY = referee -> referee.getDocsByLetter().get();

	/**
	 * Highest training level.
	 *
	 * @return highest training level
	 *
	 * @version 0.6.0
	 * @since 0.5.0
	 */
		public TrainingLevel getHighestTrainingLevel() {
			return getTrainingLevel()
					.stream()
					.sorted(TrainingLevelModel.RANK.reversed())
					.findFirst()
					.orElse(null);
		}

	/**
	 * Local training level.
	 *
	 * @return local training level
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
		public TrainingLevel getLocalTrainingLevel() {
			TrainingLevel firstLevel = getTrainingLevel()
					.stream()
					.sorted(TrainingLevelModel.RANK)
					.findFirst()
					.orElse(null);

			if ((firstLevel == null) || !firstLevel.getType().getId().equals(Prefs.get(PrefKey.LOCAL_TRAININGLEVEL))) {
				return null;
			}

			return firstLevel;
		}

	/**
	 * Last training update.
	 *
	 * @return last training update
	 *
	 * @version 0.10.0
	 * @since 0.8.0
	 */
		public LocalDate getLastTrainingUpdate() {

			TrainingLevel highestTrainingLevel = getHighestTrainingLevel();

			if (highestTrainingLevel == null) {
				return null;
			}

			List<LocalDate> lstUpdate = new ArrayList<>();
			highestTrainingLevel.getUpdate().forEach(update -> lstUpdate.add((LocalDate) update.get()));

			LocalDate dteReturn = lstUpdate
					.stream()
					.sorted(Comparator.reverseOrder())
					.findFirst()
					.orElse(null);

			if (dteReturn == null) {
				return (LocalDate) highestTrainingLevel.getSince().get();
			}

			return dteReturn;
		}

	/**
	 * Next training update.
	 *
	 * @return next training update
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
		public LocalDate getNextTrainingUpdate() {

			LocalDate lastTrainingUpdate = getLastTrainingUpdate();

			if (lastTrainingUpdate == null) {
				return null;
			}

			return lastTrainingUpdate.plusYears(getHighestTrainingLevel().getType().getUpdateInterval().get());
		}

	/**
	 * Does referee receive docs by letter?.
	 *
	 * Considers property and lack of email address.
	 *
	 * @return receive docs by letter?
	 *
	 * @version 0.10.0
	 * @since 0.8.0
	 */
	@Override
	public SimpleBooleanProperty getDocsByLetter() {
			return new SimpleBooleanProperty(docsByLetter.get() || getEMail().isEmpty());
	}

}

/* EOF */

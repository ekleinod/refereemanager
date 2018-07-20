package de.edgesoft.refereemanager.utils;

import java.text.DecimalFormat;
import java.text.ParseException;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

/**
 * Utility methods for {@link Spinner}.
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
 * @since 0.14.0
 */
public class SpinnerUtils {

	/**
	 * Prepares integer spinner with min and max values.
	 *
	 * Editable spinners do not change their value on exiting with tab,
	 * thus the complicated initialization.
	 *
	 * Code from: <https://stackoverflow.com/questions/32340476/manually-typing-in-text-in-javafx-spinner-is-not-updating-the-value-unless-user>
	 *
	 * @param theSpinner spinner to prepare
	 * @param theMin min value
	 * @param theMax max value
	 */
	public static final void prepareIntegerSpinner(Spinner<Integer> theSpinner, final int theMin, final int theMax) {
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(theMin, theMax);
        theSpinner.setValueFactory(factory);
        TextFormatter<Integer> formatter = new TextFormatter<>(factory.getConverter(), factory.getValue());
        theSpinner.getEditor().setTextFormatter(formatter);
        factory.valueProperty().bindBidirectional(formatter.valueProperty());
	}

	/**
	 * Prepares double spinner with min and max values.
	 *
	 * Editable spinners do not change their value on exiting with tab,
	 * thus the complicated initialization.
	 *
	 * Code from: <https://stackoverflow.com/questions/32340476/manually-typing-in-text-in-javafx-spinner-is-not-updating-the-value-unless-user>
	 *
	 * @param theSpinner spinner to prepare
	 * @param theMin min value
	 * @param theMax max value
	 *
	 * @since 0.15.0
	 */
	public static final void prepareDoubleSpinner(Spinner<Double> theSpinner, final double theMin, final double theMax) {

		// value factory with more decimal places than standard implementation (converter - taken from Java documentation)
        SpinnerValueFactory<Double> factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(theMin, theMax);
        factory.setConverter(new StringConverter<Double>() {

            private final DecimalFormat df = new DecimalFormat("#.#####");

            @Override public String toString(Double value) {
                // If the specified value is null, return a zero-length String
                if (value == null) {
                    return "";
                }

                return df.format(value);
            }

            @Override public Double fromString(String value) {
                try {

                    // If the specified value is null or zero-length, return null
                    if ((value == null) || value.trim().isEmpty()) {
                        return null;
                    }

                    // Perform the requested parsing
                    return df.parse(value.trim()).doubleValue();

                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        theSpinner.setValueFactory(factory);

        // text formatter for output/editor binding
        TextFormatter<Double> formatter = new TextFormatter<>(factory.getConverter(), factory.getValue());
        theSpinner.getEditor().setTextFormatter(formatter);
        factory.valueProperty().bindBidirectional(formatter.valueProperty());

	}

}

/* EOF */

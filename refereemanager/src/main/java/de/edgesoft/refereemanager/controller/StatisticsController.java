package de.edgesoft.refereemanager.controller;

import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controller for statistics scene.
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
 * @since 0.10.0
 */
public class StatisticsController {

	/**
	 * Bar chart overview.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private BarChart<String, Integer> chartOverview;

	/**
	 * Pie chart gender.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private PieChart chartGender;

	/**
	 * Pie chart training levels.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private PieChart chartTrainingLevel;

	/**
	 * Tab overview.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Tab tabOverview;

	/**
	 * Tab gender.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Tab tabGender;

	/**
	 * Tab training levels.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Tab tabTrainingLevel;

	/**
	 * OK button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnOK;

	/**
	 * Reference to dialog stage.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private Stage dialogStage;


	/**
	 * Sets dialog stage.
	 *
	 * @param theStage dialog stage
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void setDialogStage(final Stage theStage) {
				dialogStage = theStage;
		}

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void initialize() {

		// icons
		btnOK.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/dialog-ok.png")));

		tabOverview.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/office-chart-bar.png")));
		tabGender.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/office-chart-pie.png")));
		tabTrainingLevel.setGraphic(new ImageView(Resources.loadImage("icons/24x24/actions/office-chart-pie.png")));

	}

	/**
	 * Fills statistics.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	public void fillStatistics() {

//		// compute statistics data
//		Map<String, Map<Month, AtomicInteger>> mapCounts = new HashMap<>();
//
//		theData.forEach(event -> {
//			mapCounts.computeIfAbsent(event.getEventtype().getValue(), eventtype -> {
//				Map<Month, AtomicInteger> mapMonthCounts = new HashMap<>();
//				for (Month month : Month.values()) {
//					mapMonthCounts.put(month, new AtomicInteger());
//				}
//				return mapMonthCounts;
//			});
//			mapCounts.get(event.getEventtype().getValue()).get(((LocalDate) event.getDate().getValue()).getMonth()).incrementAndGet();
//		});
//
//		AtomicInteger iWholeCount = new AtomicInteger();
//
//		// output data
//		mapCounts.entrySet().stream()
//				.sorted(Comparator.comparing(typemap -> typemap.getKey(), Collator.getInstance()))
//				.forEach(typemap -> {
//
//					AtomicInteger iEventCount = new AtomicInteger();
//
//					XYChart.Series<String, Integer> seriesOverview = new XYChart.Series<>();
//					seriesOverview.setName(typemap.getKey());
//
//					XYChart.Series<String, Integer> seriesStacked = new XYChart.Series<>();
//					seriesStacked.setName(typemap.getKey());
//
//					typemap.getValue().entrySet().stream()
//							.sorted(Comparator.comparing(monthcount -> monthcount.getKey()))
//									.forEach(monthcount -> {
//										seriesOverview.getData().add(new XYChart.Data<>(monthcount.getKey().getDisplayName(TextStyle.FULL, Locale.getDefault()), monthcount.getValue().get()));
//										seriesStacked.getData().add(new XYChart.Data<>(monthcount.getKey().getDisplayName(TextStyle.FULL, Locale.getDefault()), monthcount.getValue().get()));
//										iEventCount.addAndGet(monthcount.getValue().get());
//									});
//
//					chartOverview.getData().add(seriesOverview);
//					chartGender.getData().add(seriesStacked);
//					chartTrainingLevel.getData().add(new PieChart.Data(String.format("%s (%d)", typemap.getKey(), iEventCount.get()), iEventCount.get()));
//
//					iWholeCount.addAndGet(iEventCount.get());
//
//				});

		}

	/**
	 * Closes dialog.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
		private void handleOk() {
				dialogStage.close();
		}

}

/* EOF */

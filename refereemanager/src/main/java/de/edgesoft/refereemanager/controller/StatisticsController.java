package de.edgesoft.refereemanager.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategorySeries.CategorySeriesRenderStyle;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieSeries.PieSeriesRenderStyle;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.PieStyler.AnnotationType;

import de.edgesoft.edgeutils.xchart.ChartFactory;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for statistics scene.
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
 * @since 0.10.0
 */
public class StatisticsController {

	/**
	 * Tab pane.
	 *
	 * @version 0.14.0
	 * @since 0.14.0
	 */
	@FXML
	private TabPane pneTabs;

	/**
	 * Chart overview.
	 *
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	@FXML
	private SwingNode nodeOverview;

	/**
	 * Chart gender.
	 *
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	@FXML
	private SwingNode nodeGender;

	/**
	 * Chart training levels.
	 *
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	@FXML
	private SwingNode nodeTrainingLevel;

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
	 * Export button.
	 *
	 * @version 0.14.0
	 * @since 0.14.0
	 */
	@FXML
	private Button btnExport;


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
	 * @version 0.14.0
	 * @since 0.10.0
	 */
	public void fillStatistics() {

		ContentModel theContent = (ContentModel) AppModel.getData().getContent();

		// overview
	    CategoryChart chartOverview = ChartFactory.createCategoryChart(tabOverview.getText(), OptionalInt.empty(), OptionalInt.empty(),
	    		Optional.of(CategorySeriesRenderStyle.Bar), Optional.empty());

	    chartOverview.getStyler().setXAxisTicksVisible(true);

	    chartOverview.addSeries("test",
	    		Arrays.asList(new String[] {"Schiedsrichter", "Auszubildende", "Personen", "Clubs", "Teams", "Ligen"}),
	    		Arrays.asList(new Number[] {theContent.getReferee().size(), theContent.getTrainee().size(), theContent.getPerson().size(), theContent.getClub().size(), theContent.getTeam().size(), theContent.getLeague().size()})
				);

	    nodeOverview.setContent(new XChartPanel<>(chartOverview));

		// gender
	    PieChart chartGender = ChartFactory.createPieChart(tabGender.getText(), OptionalInt.empty(), OptionalInt.empty(), Optional.empty(), Optional.empty());

	    chartGender.getStyler().setAnnotationType(AnnotationType.Label);
	    chartGender.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);

	    theContent.getSexType().forEach(sextype -> {

	    	int iSize = theContent.getReferee().stream()
	    			.filter(referee -> referee.getSexType().equals(sextype))
	    			.collect(Collectors.toList())
	    			.size();

	    	if (iSize > 0) {
	    		chartGender.addSeries(String.format("%s (%d)", sextype.getDisplayText().getValue(), iSize), iSize);
	    	}

	    });

	    nodeGender.setContent(new XChartPanel<>(chartGender));

		// level
	    PieChart chartTrainingLevel = ChartFactory.createPieChart(tabTrainingLevel.getText(), OptionalInt.empty(), OptionalInt.empty(), Optional.empty(), Optional.empty());

	    chartTrainingLevel.getStyler().setAnnotationType(AnnotationType.Label);
	    chartTrainingLevel.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);

	    theContent.getTrainingLevelType().forEach(trainingleveltype -> {

	    	int iSize = theContent.getReferee().stream()
	    			.filter(referee -> ((RefereeModel) referee).getHighestTrainingLevel().getType().equals(trainingleveltype))
	    			.collect(Collectors.toList())
	    			.size();

	    	if (iSize > 0) {
	    		chartTrainingLevel.addSeries(trainingleveltype.getDisplayText().getValue(), iSize);
	    	}

	    });

	    nodeTrainingLevel.setContent(new XChartPanel<>(chartTrainingLevel));

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

	/**
	 * Exports chart.
	 *
	 * @version 0.14.0
	 * @since 0.14.0
	 */
	@FXML
	private void handleExport() {

		Chart<?, ?> activeChart = ((XChartPanel<?>) ((SwingNode) ((AnchorPane) pneTabs.getSelectionModel().getSelectedItem().getContent()).getChildren().get(0)).getContent()).getChart();

		try {
			VectorGraphicsEncoder.saveVectorGraphic(activeChart, "/home/ekleinod/working/rani/schiri/refereemanager/generiert/test", VectorGraphicsFormat.PDF);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

/* EOF */

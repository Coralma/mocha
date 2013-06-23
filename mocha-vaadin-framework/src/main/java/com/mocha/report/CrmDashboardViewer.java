/**
 * 
 */
package com.mocha.report;

import java.util.Arrays;
import java.util.LinkedHashSet;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.invient.vaadin.charts.InvientCharts;
import com.invient.vaadin.charts.InvientChartsConfig;
import com.invient.vaadin.charts.Color.RGB;
import com.invient.vaadin.charts.InvientCharts.DecimalPoint;
import com.invient.vaadin.charts.InvientCharts.Series;
import com.invient.vaadin.charts.InvientCharts.SeriesType;
import com.invient.vaadin.charts.InvientCharts.XYSeries;
import com.invient.vaadin.charts.InvientChartsConfig.CategoryAxis;
import com.invient.vaadin.charts.InvientChartsConfig.ColumnConfig;
import com.invient.vaadin.charts.InvientChartsConfig.DataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.HorzAlign;
import com.invient.vaadin.charts.InvientChartsConfig.Legend;
import com.invient.vaadin.charts.InvientChartsConfig.NumberYAxis;
import com.invient.vaadin.charts.InvientChartsConfig.PieConfig;
import com.invient.vaadin.charts.InvientChartsConfig.PieDataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.PointConfig;
import com.invient.vaadin.charts.InvientChartsConfig.XAxis;
import com.invient.vaadin.charts.InvientChartsConfig.XAxisDataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.YAxis;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisTitle;
import com.invient.vaadin.charts.InvientChartsConfig.GeneralChartConfig.Margin;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Coral
 * 
 */
public class CrmDashboardViewer extends CommonViewer implements Viewer {

	String width = "765px";

	public CrmDashboardViewer() {

	}

	public void attach() {
		this.setWidth(width);
		this.setSpacing(true);
		Layout portalLayout = buildPortalLayout();
		Panel columnPanel = buildPanel("Customer distribution");
		VerticalLayout columnLayout = new VerticalLayout();
		columnLayout.setSizeFull();
		columnLayout.setMargin(false);
		InvientCharts columnChart = showColumn();
		columnChart.setWidth("100%");
		columnChart.setHeight("250px");
		columnLayout.addComponent(columnChart);
		columnPanel.setContent(columnLayout);
		portalLayout.addComponent(columnPanel);
		this.addComponent(portalLayout);

		portalLayout = buildPortalLayout();
		Panel piePanel = buildPanel("Customer Status Chart");
		VerticalLayout pieLayout = new VerticalLayout();
		pieLayout.setMargin(false);
		pieLayout.setSizeFull();
		InvientCharts chart = showPie();
		chart.setWidth("100%");
		chart.setHeight("250px");
		chart.setStyleName("v-chart-min-width");
		pieLayout.addComponent(chart);

		piePanel.setContent(pieLayout);
		portalLayout.addComponent(piePanel);
		this.addComponent(portalLayout);
	}

	public Layout buildPortalLayout() {
		HorizontalLayout portalLayout = new HorizontalLayout();
		portalLayout.addStyleName("app-dashboard");
		portalLayout.setSpacing(true);
		portalLayout.setWidth(width);
		return portalLayout;
	}

	public Panel buildPanel(String caption) {
		Panel dash = new Panel();
		dash.setWidth("100%");
		dash.setCaption(caption);
		dash.setHeight("300px");
		return dash;
	}

	@Override
	public String getViewerTitle() {
		return "Home";
	}

	private InvientCharts showColumn() {
		InvientChartsConfig chartConfig = new InvientChartsConfig();
		chartConfig.getGeneralChartConfig().setType(SeriesType.COLUMN);
		chartConfig.getGeneralChartConfig().setMargin(new Margin());
		chartConfig.getGeneralChartConfig().getMargin().setTop(50);
		chartConfig.getGeneralChartConfig().getMargin().setRight(50);
		chartConfig.getGeneralChartConfig().getMargin().setBottom(100);
		chartConfig.getGeneralChartConfig().getMargin().setLeft(80);

		chartConfig.getTitle().setText("");

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setCategories(Arrays.asList("Tokyo", "Jakarta", "New York",
				"Seoul", "Manila", "Mumbai"));
		xAxis.setLabel(new XAxisDataLabel());
		xAxis.getLabel().setRotation(-45);
		xAxis.getLabel().setAlign(HorzAlign.RIGHT);
		xAxis.getLabel()
				.setStyle("{ font: 'normal 13px Verdana, sans-serif' }");
		LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
		xAxesSet.add(xAxis);
		chartConfig.setXAxes(xAxesSet);

		NumberYAxis yAxis = new NumberYAxis();
		yAxis.setMin(0.0);
		yAxis.setTitle(new AxisTitle("Customer Number"));
		LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
		yAxesSet.add(yAxis);
		chartConfig.setYAxes(yAxesSet);

		chartConfig.setLegend(new Legend(false));

		chartConfig
				.getTooltip()
				.setFormatterJsFunc(
						"function() {"
								+ " return '<b>'+ this.x +'</b><br/>'+ 'Number of Customer: '+ $wnd.Highcharts.numberFormat(this.y, 1) + "
								+ " ' millions' " + "}");

		InvientCharts chart = new InvientCharts(chartConfig);

		ColumnConfig colCfg = new ColumnConfig();
		colCfg.setDataLabel(new DataLabel());
//		colCfg.getDataLabel().setRotation(-90);
		colCfg.getDataLabel().setAlign(HorzAlign.RIGHT);
		colCfg.getDataLabel().setX(-3);
		colCfg.getDataLabel().setY(10);
		colCfg.getDataLabel().setColor(new RGB(255, 255, 255));
		colCfg.getDataLabel().setFormatterJsFunc(
				"function() {" + " return this.y; " + "}");
		colCfg.getDataLabel().setStyle(
				" { font: 'normal 13px Verdana, sans-serif' } ");
		XYSeries seriesData = new XYSeries("Population", colCfg);
		seriesData.setSeriesPoints(getPoints(seriesData, 34, 21, 41, 20,
				19, 5));

		chart.addSeries(seriesData);

		return chart;
	}
	
	private static LinkedHashSet<DecimalPoint> getPoints(Series series,
            double... values) {
        LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
        for (double value : values) {
            points.add(new DecimalPoint(series, value));
        }
        return points;
    }

	private InvientCharts showPie() {
		InvientChartsConfig chartConfig = new InvientChartsConfig();
		chartConfig.getGeneralChartConfig().setType(SeriesType.PIE);

		chartConfig.getTitle().setText("");

		chartConfig
				.getTooltip()
				.setFormatterJsFunc(
						"function() {"
								+ " return '<b>'+ this.point.name +'</b>: '+ this.y +' %'; "
								+ "}");

		PieConfig pie = new PieConfig();
		pie.setAllowPointSelect(true);
		pie.setCursor("pointer");
		pie.setDataLabel(new PieDataLabel(false));
		pie.setShowInLegend(true);
		chartConfig.addSeriesConfig(pie);

		InvientCharts chart = new InvientCharts(chartConfig);

		XYSeries series = new XYSeries("Browser Share");
		LinkedHashSet<DecimalPoint> points = new LinkedHashSet<DecimalPoint>();
		PointConfig config = new PointConfig(true);
		points.add(new DecimalPoint(series, "Achieved", 55.0, config));
		points.add(new DecimalPoint(series, "Potential", 25.0));
		points.add(new DecimalPoint(series, "Failure", 15.0));
		points.add(new DecimalPoint(series, "Close", 5.0));

		series.setSeriesPoints(points);
		chart.addSeries(series);

		return chart;
	}
}

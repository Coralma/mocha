package com.mocha.ib.presenter;

import java.util.Arrays;
import java.util.LinkedHashSet;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.invient.vaadin.charts.InvientCharts;
import com.invient.vaadin.charts.InvientChartsConfig;
import com.invient.vaadin.charts.Color.RGB;
import com.invient.vaadin.charts.InvientCharts.ChartClickEvent;
import com.invient.vaadin.charts.InvientCharts.ChartClickListener;
import com.invient.vaadin.charts.InvientCharts.DecimalPoint;
import com.invient.vaadin.charts.InvientCharts.PointClickEvent;
import com.invient.vaadin.charts.InvientCharts.PointClickListener;
import com.invient.vaadin.charts.InvientCharts.Series;
import com.invient.vaadin.charts.InvientCharts.SeriesType;
import com.invient.vaadin.charts.InvientCharts.XYSeries;
import com.invient.vaadin.charts.InvientChartsConfig.CategoryAxis;
import com.invient.vaadin.charts.InvientChartsConfig.DataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.LineConfig;
import com.invient.vaadin.charts.InvientChartsConfig.NumberXAxis;
import com.invient.vaadin.charts.InvientChartsConfig.NumberYAxis;
import com.invient.vaadin.charts.InvientChartsConfig.PieConfig;
import com.invient.vaadin.charts.InvientChartsConfig.PieDataLabel;
import com.invient.vaadin.charts.InvientChartsConfig.PointConfig;
import com.invient.vaadin.charts.InvientChartsConfig.ScatterConfig;
import com.invient.vaadin.charts.InvientChartsConfig.XAxis;
import com.invient.vaadin.charts.InvientChartsConfig.YAxis;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.AxisTitle;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotLine;
import com.invient.vaadin.charts.InvientChartsConfig.AxisBase.NumberPlotLine.NumberValue;
import com.invient.vaadin.charts.InvientChartsConfig.GeneralChartConfig.Margin;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class IBDashboardView extends CommonViewer implements Viewer {

	String width = "765px";
	private BasicUser user;
	
	public IBDashboardView() {

	}
	
	public IBDashboardView(BasicUser user){
		this.user=user;
	}

	public void attach() {
		this.setWidth(width);
		this.setSpacing(true);
		Layout portalLayout = buildPortalLayout();
		Panel columnPanel = buildPanel("Commission Monthly Distribution");
		VerticalLayout columnLayout = new VerticalLayout();
		columnLayout.setSizeFull();
		columnLayout.setMargin(false);
		InvientCharts columnChart = showLine();
		columnChart.setWidth("100%");
		columnChart.setHeight("250px");
		columnLayout.addComponent(columnChart);
		columnPanel.setContent(columnLayout);
		portalLayout.addComponent(columnPanel);
		this.addComponent(portalLayout);

		portalLayout = buildPortalLayout();
		Panel piePanel = buildPanel("Sales Product Category");
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

	private InvientCharts showLine() {
		InvientChartsConfig chartConfig = new InvientChartsConfig();
		chartConfig.getGeneralChartConfig().setMargin(new Margin());

		chartConfig.getTitle().setText("Monthly Commission Amount");
//		chartConfig.getSubtitle().setText("Source: WorldClimate.com");

		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.setCategories(Arrays.asList("Jan", "Feb", "Mar", "Apr",
				"May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
		LinkedHashSet<XAxis> xAxesSet = new LinkedHashSet<InvientChartsConfig.XAxis>();
		xAxesSet.add(categoryAxis);
		chartConfig.setXAxes(xAxesSet);

		NumberYAxis numberYAxis = new NumberYAxis();
		numberYAxis.setTitle(new AxisTitle("Million Dollar (USD)"));
		LinkedHashSet<YAxis> yAxesSet = new LinkedHashSet<InvientChartsConfig.YAxis>();
		yAxesSet.add(numberYAxis);
		chartConfig.setYAxes(yAxesSet);

		chartConfig.getTooltip().setEnabled(false);

		// Series data label formatter
		LineConfig lineCfg = new LineConfig();
		lineCfg.setDataLabel(new DataLabel());
		lineCfg.getDataLabel().setEnabled(true);
		lineCfg.setEnableMouseTracking(false);
		chartConfig.addSeriesConfig(lineCfg);

		InvientCharts chart = new InvientCharts(chartConfig);

		XYSeries seriesData = new XYSeries("Commission");
		seriesData.setSeriesPoints(getPoints(seriesData, 17.0, 16.9, 19.5, 14.5,
				18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 19.6));
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
		points.add(new DecimalPoint(series, "Vehicle", 35.0));
		points.add(new DecimalPoint(series, "Accident", 18.0));
		points.add(new DecimalPoint(series, "Life", 12.0));
		points.add(new DecimalPoint(series, "Property", 25.0));
		points.add(new DecimalPoint(series, "Liability", 7.0));
		points.add(new DecimalPoint(series, "Contract", 3.0));
		

		series.setSeriesPoints(points);
		chart.addSeries(series);

		return chart;
	}
}

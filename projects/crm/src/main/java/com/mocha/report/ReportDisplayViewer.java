/**
 * 
 */
package com.mocha.report;

import java.util.ArrayList;
import java.util.List;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.view.CommonViewer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class ReportDisplayViewer extends CommonViewer implements Viewer {

	private String reportTitle = "Achieved Campaign Report";
	
	public ReportDisplayViewer() {
		
	}

	public void attach() {
		super.attach();
		
		VerticalLayout filterLayout = new VerticalLayout();
		HorizontalLayout filterFieldLayout = new HorizontalLayout();
		filterFieldLayout.addComponent(getAvailableColumnField());
		filterFieldLayout.addComponent(WidgetFactory.createDateField("Start Date"));
		filterFieldLayout.addComponent(WidgetFactory.createDateField("End Date"));
		filterLayout.addComponent(filterFieldLayout);
		
		this.addComponent(filterFieldLayout);
	}
	
	public ComboBox getAvailableColumnField() {
		List<String> datas = new ArrayList<String>();
		datas.add("Start Date");
		datas.add("End Date");
		datas.add("Create Date");
		ComboBox box =new ComboBox("Columns",datas);
		return box;
	}
	
	@Override
	public String getViewerTitle() {
		return reportTitle;
	}

}

package com.mocha.report;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.ReportTable;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.view.CommonViewer;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CrmReportViewer extends CommonViewer implements Viewer {

	private static final long serialVersionUID = 8828931259387636566L;
	public ReportCategoryListener listener;
	public Button createButton;
	private List<AppReport> customizedAppReports=new ArrayList<AppReport>(); 
	
	public CrmReportViewer(List<AppReport> customizedAppReports){
		this.customizedAppReports=customizedAppReports;
	}
	
	@Override
	public void attach() {
		super.attach();

		ToolbarAdvance toolbar = new ToolbarAdvance();
		toolbar.setToolbarWidth(RuntimeConstant.APP_CONTENT_WIDTH);
		toolbar.addLeftComponent(WidgetFactory.createLink("All Report"));
		toolbar.addLeftComponent(WidgetFactory.createLink("Customized Report"));
		createButton = WidgetFactory.createButton("Create Report");
		toolbar.addRightComponent(createButton);
		this.addComponent(toolbar);
		
		ReportCardGroup systemReport = new ReportCardGroup("System Reports", getAppReports());
		this.addComponent(systemReport);
		
		ReportCardGroup customizedReport = new ReportCardGroup("Customized Reports", getCustomizedAppReports());
		this.addComponent(customizedReport);
		
		bind();
	}
	
	public void bind() {
		createButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				listener.createReport();
			}
		});
	}
	
	public class ReportCardGroup extends VerticalLayout {
		
		public String category;
		public List<AppReport> appReports;
		
		public ReportCardGroup(String category, List<AppReport> appReports) {
			this.category = category;
			this.appReports = appReports;
			this.setWidth("100%");
			this.addStyleName("report-card-group");
		}
		
		public void attach() {
			Label categoryLabel = new Label();
			categoryLabel.setIcon(new ThemeResource("icons/report_icon.png"));
			categoryLabel.setCaption(category);
			categoryLabel.addStyleName("report-category");
			this.addComponent(categoryLabel);

			GridLayout gridLayout  = new GridLayout(3, 1);
			gridLayout.setSizeFull();
		    if(appReports==null || appReports.size()==0){
				gridLayout.setCaption("Empty Customered Report View");
			}else{
				for(AppReport appReport : appReports) {
					ReportCard reportCard = new ReportCard(appReport);
					gridLayout.addComponent(reportCard);
				    gridLayout.setComponentAlignment(reportCard, Alignment.MIDDLE_LEFT);
				}
				// add the empty
				int appSize = appReports.size();
				int c = appSize % 3;
				if(c != 0) {
					c = 3 - c;
				}
				for(int i=0; i<c;i++) {
					VerticalLayout layout = new VerticalLayout();
					layout.setWidth("248px");
					layout.addComponent(WidgetFactory.createLabel(" "));
					gridLayout.addComponent(layout);
				}
			}
			this.addComponent(gridLayout);
		}
	}

	public class ReportCard extends VerticalLayout implements LayoutClickListener {
		private String name;
		private String desc;
		private String width = "230px";
		private Long reportId ;
		public ReportCard(AppReport appReport) {
			this.name= appReport.getName();
			this.desc = appReport.getDescription();
			this.addStyleName("report-card");
			this.setWidth(width);
			this.setHeight("100px");
			this.reportId=appReport.getAppReportId();
			this.addListener(this);
		}
		
		public void attach() {
			Label nameLabel = new Label(name, Label.CONTENT_XHTML);
			nameLabel.setWidth(width);
			nameLabel.addStyleName("report-name");
			Label descLabel = new Label(desc, Label.CONTENT_XHTML);
			descLabel.setWidth(width);
			this.addComponent(nameLabel);
			this.setExpandRatio(nameLabel, 0.2f);
			this.addComponent(descLabel);
			this.setExpandRatio(descLabel, 0.8f);
		}

		@Override
		public void layoutClick(LayoutClickEvent event) {
			listener.showReport(getReportId());			
		}

		public Long getReportId() {
			return reportId;
		}

		public void setReportId(Long reportId) {
			this.reportId = reportId;
		}
	}
	
	public List<AppReport> getAppReports() {
		List<AppReport> reports = new ArrayList<AppReport>();
		AppReport appReport = new AppReport();
		appReport.setName("Achieved Custome Report");
		appReport.setDescription("This statistics report will display all achieved custome data.");
		reports.add(appReport);
		
		appReport = new AppReport();
		appReport.setName("Failure Custome Report");
		appReport.setDescription("This statistics report will display all failure custome and the failure reason.");
		reports.add(appReport);
		
		appReport = new AppReport();
		appReport.setName("Achieved Campaign Report");
		appReport.setDescription("This statistics report will display all completed campaign data.");
		reports.add(appReport);
		
		appReport = new AppReport();
		appReport.setName("Visite serve Report");
		appReport.setDescription("This statistics report will display all visite serve detail information.");
		reports.add(appReport);
		
		AppReport userAccountReport = new AppReport();
		userAccountReport = new AppReport();
		userAccountReport.setName("Default Report for User And Account Mapping");
		userAccountReport.setDescription("This statistics report will display the users under account");
		userAccountReport.setAppReportId(7L);
		
		reports.add(userAccountReport);
		return reports;
	}
	
//	public List<AppReport> getCustomizedAppReports() {
//		List<AppReport> reports = new ArrayList<AppReport>();
////		AppReport appReport = new AppReport();
////		appReport.setName("Call Serve of Custome Report");
////		appReport.setDescription("This statistics report will display all call serve detail of custome.");
////		reports.add(appReport);
////		
////		appReport = new AppReport();
////		appReport.setName("Potential Custome Report all potential custome");
////		appReport.setDescription("This statistics report will display all potential custome.");
////		reports.add(appReport);
//		
//		return reports;
//	}
	
	@Override
	public String getViewerTitle() {
		return "Report";
	}

	/**
	 * @return the listener
	 */
	public ReportCategoryListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(ReportCategoryListener listener) {
		this.listener = listener;
	}

	public List<AppReport> getCustomizedAppReports() {
		return customizedAppReports;
	}

	public void setCustomizedAppReports(List<AppReport> customizedAppReports) {
		this.customizedAppReports = customizedAppReports;
	}
}

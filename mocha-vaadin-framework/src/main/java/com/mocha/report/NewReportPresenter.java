/**
 * 
 */
package com.mocha.report;

import java.util.Map;

import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.ReportTable;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.view.CommonPresenter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.coral.foundation.report.AbstrctAppRawData;
import com.coral.foundation.report.AppCusteomReportService;
import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.report.ReportModel;
import com.coral.foundation.report.ReportModelPool;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportFilter;
import com.coral.foundation.security.model.ReportJoinTable;

/**
 * @author Coral
 *
 */
public class NewReportPresenter extends CommonPresenter implements Presenter {
	
	final AbstrctAppRawData appCustomReprotRowData;
	
	public NewReportPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
//		ReportModelPool.clearReportModelByUser(eventBus.getUser());
		ReportModel reportModel=new ReportModel("","","");
		appCustomReprotRowData=(AbstrctAppRawData) eventBus.getContext().get("appCustomReprotRowData");
		if(appCustomReprotRowData!=null){
			reportModel.setAppRawRata(appCustomReprotRowData);
			if(ReportModelPool.findReportModelByCurrentUser(eventBus.getUser())==null){
				ReportModelPool.initInstance(eventBus.getUser(),reportModel);
			}
		}else{
			new Throwable("Error occurs when init Report View on NewReportPresenter");
		}
		System.out.println("appCustomReprotRowData.getReportTables().size() is: "+appCustomReprotRowData.getReportTables().size());
		this.viewer = new NewReportViewer(eventBus.getUser(),appCustomReprotRowData);
	}

	@Override
	public void bind() {
		NewReportViewer newReportViewer=(NewReportViewer) viewer;
		newReportViewer.setListener(new ReportWizardProgressListener(){
			@Override
			public void activeStepChanged(WizardStepActivationEvent event) {
				// TODO Auto-generated method stub
			}

			@Override
			public void stepSetChanged(WizardStepSetChangedEvent event) {
				// TODO Auto-generated method stub
			}

			@Override
			public void wizardCompleted(WizardCompletedEvent event) {
				ReportModel reportModel=ReportModelPool.findReportModelByCurrentUser(eventBus.getUser());
				AppReport appReport = reportModel.getAppReport();
				BasicUser creator=getEventBus().getUser();
				AppCusteomReportService appCustomReportService = new AppCusteomReportService(appReport,creator);
				//Support one main table with one sub tables
				ReportTable subTable = new ReportTable();
				ReportTable mainTable = new ReportTable();
				mainTable.setType(ReportConfiguration.ReportType.MainTable.toString());
				subTable.setType(ReportConfiguration.ReportType.SubTable.toString());
				List<ReportColumn> subTableReportColumns = new ArrayList<ReportColumn>();
				List<ReportColumn> mainTableReportColumns =new ArrayList<ReportColumn>(); 
				
				for (Iterator<ReportTable> it = reportModel.getReportTables().iterator(); it.hasNext();) {
					ReportTable reportTable=(ReportTable) it.next();
					//build subTable relate info
					if(reportTable.getType().equals(ReportConfiguration.ReportType.SubTable.toString())){
						//build subTable name
						subTable.setTableName(reportTable.getTableName());
						//build subTable output columns
						for(ReportColumn subReportColumn: reportModel.getSubTableSelectedColumns()){
//							ReportColumn subReportColumn = reportModel.getSubTableSelectedColumns().get(tableKey);
							subTableReportColumns.add(subReportColumn);
						}
						
					}
					
				}
				
				subTable.setReportColumns(subTableReportColumns);
				
				for (Iterator<ReportTable> it = reportModel.getReportTables().iterator(); it.hasNext();) {
					ReportTable reportTable=(ReportTable) it.next();
					// build mainTable infor
					String mainTableType=ReportConfiguration.ReportType.MainTable.toString();
					if(reportTable.getType().toString().equals(mainTableType)){
						//build mainTable name and join type
						mainTable.setTableName(reportTable.getTableName());
						mainTable.setJoinType("inner join");
						//build maintable output
						for(ReportColumn mainTableSelectReportColumn : reportModel.getMainTableSelectedColumns()){
//							ReportColumn mainTableSelectReportColumn = reportModel.getMainTableSelectedColumns().get(tableKey);
							mainTableSelectReportColumn.setColumnUseMode(ReportConfiguration.ReportColumnType.OutputColumn.toString());
							mainTableReportColumns.add(mainTableSelectReportColumn);
						}
						//build mainTable join columns
						for(ReportColumn reportColumn:reportTable.getReportColumns()){
							if (reportColumn.getColumnUseMode() != null
									&& reportColumn.getColumnUseMode().equals(ReportConfiguration.ReportColumnType.ForeignKeyRefernceColumn.toString())) {
								if(reportColumn.getReferenceTableName().equals(subTable.getTableName())){
									// maintable join column
									ReportColumn mainReportJoinColumn = new ReportColumn();
									mainReportJoinColumn.setColumnName(reportColumn.getColumnName());
									mainReportJoinColumn.setColumnUseMode(ReportConfiguration.ReportColumnType.JoinColumn.toString());
									mainTableReportColumns.add(mainReportJoinColumn);
									
									// subTable join column
									ReportColumn subTableReportJoinColumn = new ReportColumn();
									subTableReportJoinColumn.setColumnName(reportColumn.getReferenceColumnName());
									subTableReportJoinColumn.setColumnUseMode(ReportConfiguration.ReportColumnType.JoinColumn.toString());
									subTable.getReportColumns().add(subTableReportJoinColumn);
									
									// maintable join table
									ReportJoinTable mainReportJoinTable = new ReportJoinTable();
									mainReportJoinTable.setReportTable(subTable);
									List<ReportJoinTable> mainTableReportJoinTables = new ArrayList<ReportJoinTable>();
									mainTableReportJoinTables.add(mainReportJoinTable);
									mainTable.getReportJoinReportTableId().add(mainReportJoinTable);
									
								}
							}
						}
						mainTable.setReportColumns(mainTableReportColumns);
					
					}
				}
				
				ReportFilter reportFilter=new ReportFilter();
				if(reportModel.getReportQueryFilterCondition().size()>0){
					com.coral.foundation.report.ReportQueryFilterCondition rqfc=reportModel.getReportQueryFilterCondition().get(0);					
					reportFilter.setFilterBuildString(rqfc.getQueryStrings());
					appReport.getReportFilters().add(reportFilter);				
				}
				
				appReport.getReportTables().add(mainTable);
				appCustomReportService.setAppReport(appReport);
				appCustomReportService.saveMainReportTable();
				appCustomReportService.buildReport();
				AppContentEvent appContentEvent = new AppContentEvent();
				appContentEvent.setCustomizeClass("com.mocha.report.CrmReportPresenter");
				eventBus.post(appContentEvent);
				//Keep the report row data for this user
				ReportModelPool.clearReportModelByUser(eventBus.getUser());
				
			}

			@Override
			public void wizardCancelled(WizardCancelledEvent event) {
				AppContentEvent appContentEvent = new AppContentEvent();
				appContentEvent.setCustomizeClass("com.mocha.report.CrmReportPresenter");
				eventBus.post(appContentEvent);
				ReportModelPool.clearReportModelByUser(eventBus.getUser());
			}
		});
		
		
	}

	@Override
	public String getPresenterName() {
		return null;
	}

}

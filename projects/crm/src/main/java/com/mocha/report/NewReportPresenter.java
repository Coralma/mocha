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
import com.coral.foundation.report.AppCusteomReportService;
import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportJoinTable;

/**
 * @author Coral
 *
 */
public class NewReportPresenter extends CommonPresenter implements Presenter {

	public NewReportPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new NewReportViewer();
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
				ReportModel reportModel=AbstarctReportWizardStep.getUserSelectReport().get();
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
						//build subTable report columns
						for(ReportColumn subReportColumn: reportModel.getSubTableSelectedColumns()){
//							ReportColumn subReportColumn = reportModel.getSubTableSelectedColumns().get(tableKey);
							subTableReportColumns.add(subReportColumn);
						}	
					}
				}	
				subTable.setReportColumns(subTableReportColumns);
				for (Iterator<ReportTable> it = reportModel.getReportTables().iterator(); it.hasNext();) {
					ReportTable reportTable=(ReportTable) it.next();
					// build mainTable relate infor
					String mainTableType=ReportConfiguration.ReportType.MainTable.toString();
					if(reportTable.getType().toString().contains(mainTableType)){
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
				
				appReport.getReportTables().add(mainTable);
				appCustomReportService.setAppReport(appReport);
				appCustomReportService.saveMainReportTable();
				appCustomReportService.buildReport();
				
				AppContentEvent appContentEvent = new AppContentEvent();
				appContentEvent.setCustomizeClass("com.mocha.report.CrmReportPresenter");
				eventBus.post(appContentEvent);
			}

			@Override
			public void wizardCancelled(WizardCancelledEvent event) {
				// TODO Auto-generated method stub

			}
		});
		
		
	}

	@Override
	public String getPresenterName() {
		return null;
	}

}

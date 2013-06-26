package com.coral.vaadin.template.common

import java.util.List
import com.coral.foundation.md.model.Mocha
import com.coral.foundation.md.model.ReportDef
import com.coral.foundation.constant.SystemConstant

class ReportDefinition {
	
	List<Mocha> mochas
	ReportDef reportDef;
	String reportName;
	
	def init(List<Mocha> mochas, ReportDef reportDef) {
  		this.mochas = mochas;
  		this.reportDef = reportDef;
  		this.reportName = reportDef.name;
	}
	
	def generate()'''
		«GENPackageImport»
		
		«GENClassHead»
			«GENConstructor»
		
			«GENBuildMethod»
		
		«GENClassEnd»
	'''
	
	def GENPackageImport() '''
		package «SystemConstant::GENERATED_PAGE»;
		
		import java.util.List;
		import java.util.ArrayList;
		import com.coral.foundation.security.model.*;
		import com.coral.foundation.report.AbstrctAppRawData;
		
	'''
	def GENClassHead() '''
		public class «reportName» extends AbstrctAppRawData {
			private static String appName="«reportName»";
			
	'''
	
	def GENConstructor() '''
		public «reportName»() {
			super(appName);
			
		}
	'''
	
	def GENBuildMethod() '''
		public List<ReportTable> getReportTables() {
			List<ReportTable> reportTables = new ArrayList<ReportTable>();
			«FOR reportTableDef : reportDef.reportTables»
				«val reportVariable = reportTableDef.getName + "Report"»
				ReportTable «reportVariable» = new ReportTable();
				reportTables.add(«reportVariable»);
				«reportVariable».setTableName("«reportTableDef.getTableName»");
				«reportVariable».setTableLabel("«reportTableDef.getLabel»");
				«FOR columnDef : reportTableDef.getColumns»
					«val columnVariable = reportVariable + columnDef.getName»
					ReportColumn «columnVariable» = new ReportColumn();
					«columnVariable».setColumnName("«columnDef.getColumnName»");
					«columnVariable».setColumnLabel("«columnDef.getLabel»");
					«reportVariable».getReportColumns().add(«columnVariable»);
				«ENDFOR»
				
			«ENDFOR»
			
			«FOR reportTableDef : reportDef.reportTables»
				«val reportVariable = reportTableDef.getName + "Report"»
				«FOR joinTableDef : reportTableDef.getJoinDefs»
				«val joinTableRef = joinTableDef.getName + "Report"»
				«val joinTableVariable = reportVariable + joinTableDef.getName + "Join"»
				ReportJoinTable «joinTableVariable» = new ReportJoinTable();
				«joinTableVariable».setReportTable(«joinTableRef»);
				«reportVariable».getReportJoinReportTableId().add(«joinTableVariable»);
				«ENDFOR»
			«ENDFOR»
			return reportTables;
		}
	'''
	
	def GENClassEnd() '''
	}
	'''
}
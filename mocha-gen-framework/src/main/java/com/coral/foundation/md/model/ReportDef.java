/**
 * 
 */
package com.coral.foundation.md.model;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Coral
 *
 */
public class ReportDef {

	private String name;
	private List<ReportTableDef> reportTables = Lists.newArrayList();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the reportTables
	 */
	public List<ReportTableDef> getReportTables() {
		return reportTables;
	}

	/**
	 * @param reportTables the reportTables to set
	 */
	public void setReportTables(List<ReportTableDef> reportTables) {
		this.reportTables = reportTables;
	}
	
	public void addReportTable(ReportTableDef reportTableDef) {
		this.reportTables.add(reportTableDef);
	}
}

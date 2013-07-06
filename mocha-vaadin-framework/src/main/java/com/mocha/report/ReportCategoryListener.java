/**
 * 
 */
package com.mocha.report;

/**
 * @author Coral
 *
 */
public interface ReportCategoryListener {

	public void createReport();
	public void showReport(Long reportID);
	public void editReport(Long reportID);
}

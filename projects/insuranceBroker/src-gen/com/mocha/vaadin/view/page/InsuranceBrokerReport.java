package com.mocha.vaadin.view.page;

import java.util.List;
import java.util.ArrayList;

import com.coral.foundation.report.AbstrctAppRawData;
import com.coral.foundation.security.model.*;


public class InsuranceBrokerReport extends AbstrctAppRawData{

	private static String appName="InsuranceBrokerReport";
	
	InsuranceBrokerReport() {
		super(appName);
	}

	@Override
	public List<ReportTable> getReportTables() {
		List<ReportTable> reportTables = new ArrayList<ReportTable>();
		ReportTable insuranceCustomerReport = new ReportTable();
		reportTables.add(insuranceCustomerReport);
		insuranceCustomerReport.setTableName("T_INSURANCE_CUSTOMER");
		insuranceCustomerReport.setTableLabel("Insurance Customer");
		ReportColumn insuranceCustomerReportname = new ReportColumn();
		insuranceCustomerReportname.setColumnName("name");
		insuranceCustomerReportname.setColumnLabel("Name");
		insuranceCustomerReport.getReportColumns().add(insuranceCustomerReportname);
		ReportColumn insuranceCustomerReportdistrict = new ReportColumn();
		insuranceCustomerReportdistrict.setColumnName("district");
		insuranceCustomerReportdistrict.setColumnLabel("District");
		insuranceCustomerReport.getReportColumns().add(insuranceCustomerReportdistrict);
		ReportColumn insuranceCustomerReportpostcode = new ReportColumn();
		insuranceCustomerReportpostcode.setColumnName("postcode");
		insuranceCustomerReportpostcode.setColumnLabel("Postcode");
		insuranceCustomerReport.getReportColumns().add(insuranceCustomerReportpostcode);
		ReportColumn insuranceCustomerReportaddress = new ReportColumn();
		insuranceCustomerReportaddress.setColumnName("address");
		insuranceCustomerReportaddress.setColumnLabel("Address");
		insuranceCustomerReport.getReportColumns().add(insuranceCustomerReportaddress);
		ReportColumn insuranceCustomerReportcontectPerson = new ReportColumn();
		insuranceCustomerReportcontectPerson.setColumnName("contect_Person");
		insuranceCustomerReportcontectPerson.setColumnLabel("Contect Person");
		insuranceCustomerReport.getReportColumns().add(insuranceCustomerReportcontectPerson);
		
		
		
		ReportTable policyReport = new ReportTable();
		reportTables.add(policyReport);
		policyReport.setTableName("T_POLICY");
		policyReport.setTableLabel("Policy");
		ReportColumn policyReportpolicyNo = new ReportColumn();
		policyReportpolicyNo.setColumnName("policy_No");
		policyReportpolicyNo.setColumnLabel("Policy No");
		policyReport.getReportColumns().add(policyReportpolicyNo);
		ReportColumn policyReporteffectiveDate = new ReportColumn();
		policyReporteffectiveDate.setColumnName("effective_Date");
		policyReporteffectiveDate.setColumnLabel("Effective Date");
		policyReport.getReportColumns().add(policyReporteffectiveDate);
		ReportColumn policyReportexpiryDate = new ReportColumn();
		policyReportexpiryDate.setColumnName("expiry_Date");
		policyReportexpiryDate.setColumnLabel("Expiry Date");
		policyReport.getReportColumns().add(policyReportexpiryDate);
		ReportColumn policyReportpremium = new ReportColumn();
		policyReportpremium.setColumnName("premium");
		policyReportpremium.setColumnLabel("Premium");
		policyReport.getReportColumns().add(policyReportpremium);
		ReportColumn policyReportcommission = new ReportColumn();
		policyReportcommission.setColumnName("commission");
		policyReportcommission.setColumnLabel("Commission");
		policyReport.getReportColumns().add(policyReportcommission);

		
		
		ReportTable claimReport = new ReportTable();
		reportTables.add(claimReport);
		claimReport.setTableName("T_CLAIM");
		claimReport.setTableLabel("Claim");
		ReportColumn claimReportstatus = new ReportColumn();
		claimReportstatus.setColumnName("status");
		claimReportstatus.setColumnLabel("Status");
		claimReport.getReportColumns().add(claimReportstatus);
		ReportColumn claimReportclaimReason = new ReportColumn();
		claimReportclaimReason.setColumnName("claimReason");
		claimReportclaimReason.setColumnLabel("Claim Reason");
		claimReport.getReportColumns().add(claimReportclaimReason);
		ReportColumn claimReportclaimAmount = new ReportColumn();
		claimReportclaimAmount.setColumnName("claimAmount");
		claimReportclaimAmount.setColumnLabel("Claim Amount");
		claimReport.getReportColumns().add(claimReportclaimAmount);
		
		
		ReportJoinTable insuranceCustomerReportpolicyJoin = new ReportJoinTable();
		insuranceCustomerReportpolicyJoin.setReportTable(policyReport);
		insuranceCustomerReport.getReportJoinReportTableId().add(insuranceCustomerReportpolicyJoin);
		ReportJoinTable policyReportinsuranceCustomerJoin = new ReportJoinTable();
		policyReportinsuranceCustomerJoin.setReportTable(insuranceCustomerReport);
		policyReport.getReportJoinReportTableId().add(policyReportinsuranceCustomerJoin);
		ReportJoinTable claimReportpolicyJoin = new ReportJoinTable();
		claimReportpolicyJoin.setReportTable(policyReport);
		claimReport.getReportJoinReportTableId().add(claimReportpolicyJoin);
		return reportTables;
	}

}


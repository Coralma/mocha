package com.mocha.cooperate.service.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coral.foundation.report.AppCusteomReportService;
import com.coral.foundation.security.basic.dao.ReportTableDao;
import com.coral.foundation.security.model.Account;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportJoinTable;
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.security.service.BasicUserService;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.UUIDGenerater;
import com.mocha.cooperate.model.File;
import com.mocha.cooperate.service.UserFileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext.xml"})
public class AppReportServiceTest {

//	private ReportTableDao reportTableDao = SpringContextUtils
//			.getBean(ReportTableDao.class);

	@Test
	public void simpleJoinQueryTest() {
		AppReport appReport = new AppReport();
		AppCusteomReportService appCustomReportService = new AppCusteomReportService(
				appReport);

		/*
		 * 
		 * select * from t_account t_account inner join t_user t_user on
		 * t_user.`ACCOUNT`= t_account.`ACCOUNT_ID`;
		 */

		// subJoinTable basic info
		ReportTable subTable = new ReportTable();
		subTable.setID(1L);
		subTable.setTableName("t_user");
		subTable.setType("2");

		// subJoinTable output query columns
		List<ReportColumn> subTableReportColumns = new ArrayList<ReportColumn>();
		ReportColumn subReportColumn = new ReportColumn();
		subReportColumn.setColumnName("name");
		subReportColumn.setColumnUseMode("1");
		subTableReportColumns.add(subReportColumn);

		// subTable join column
		ReportColumn subTableReportJoinColumn = new ReportColumn();
		subTableReportJoinColumn.setColumnName("account");
		subTableReportJoinColumn.setColumnUseMode("2");
		subTableReportColumns.add(subTableReportJoinColumn);
		subTable.setReportColumns(subTableReportColumns);

		// main account table
		Account account = new Account();
		// main table basic info
		ReportTable mainTable = new ReportTable();
		mainTable.setTableName("t_account");
		mainTable.setType("1");
		mainTable.setJoinType("inner join");

		// main table ouput columns info
		List<ReportColumn> mainTableReportColumns = new ArrayList<ReportColumn>();
		ReportColumn mainReportColumn = new ReportColumn();
		mainReportColumn.setColumnName("name");
		mainReportColumn.setColumnUseMode("1");
		// mainReportColumn.setReportTable(mainTable);
		mainTableReportColumns.add(mainReportColumn);
		mainTable.setReportColumns(mainTableReportColumns);

		// maintable join column
		ReportColumn mainReportJoinColumn = new ReportColumn();
		mainReportJoinColumn.setColumnName("Account_ID");
		mainReportJoinColumn.setColumnUseMode("2");
		// mainReportJoinColumn.setReportTable(mainTable);
		mainTableReportColumns.add(mainReportJoinColumn);

		// maintable join type
		mainTable.setJoinType("inner join");
		
//		appReport.getReportTables().add(subTable);
//		appCustomReportService.saveReferenceJoinTables();

		// maintable join table
		ReportJoinTable mainReportJoinTable = new ReportJoinTable();
		mainReportJoinTable.setReportTable(subTable);
		List<ReportJoinTable> mainTableReportJoinTables = new ArrayList<ReportJoinTable>();
		mainTableReportJoinTables.add(mainReportJoinTable);
		mainTable.setReportJoinReportTableId(mainTableReportJoinTables);

//		// subtable join table
//		ReportJoinTable subTableReportJoinTable = new ReportJoinTable();
//		subTableReportJoinTable.setReportJoinTableId(mainTable.getID());
//		List<ReportJoinTable> subTableReportJoinTables = new ArrayList<ReportJoinTable>();
//		subTableReportJoinTables.add(mainReportJoinTable);
//		subTable.setReportJoinReportTableId(subTableReportJoinTables);
		
		appReport.getReportTables().add(mainTable);
		appCustomReportService.saveMainReportTable();		
		appCustomReportService.buildReport();
	}

	public BasicUser loadTestUser() {
		BasicUserService service = new BasicUserService();
		return service.loadUserById(new Long(1));
	}
}
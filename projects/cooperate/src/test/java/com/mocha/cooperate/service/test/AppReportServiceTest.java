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
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.security.service.BasicUserService;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.UUIDGenerater;
import com.mocha.cooperate.model.File;
import com.mocha.cooperate.service.UserFileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AppReportServiceTest {

	// private ReportTableDao
	// reportDao=SpringContextUtils.getBean(ReportTableDao.class);

	@Test
	public void simpleJoinQueryTest() {
		// String fileName = "testFile.java";
		// UserFileService fileService = new UserFileService(loadTestUser());
		// File file = createFile(fileName);
		// fileService.createFile(file);
		//
		// List<File> files = fileService.loadFiles();
		// boolean isTrue = false;
		// for(File existFile : files) {
		// if(fileName.equals(existFile.getName())) {
		// isTrue = true;
		// }
		// }

		/*
		 * 
		 * select * from t_account t_account inner join t_user t_user on
		 * t_user.`ACCOUNT`= t_account.`ACCOUNT_ID`;
		 */

		// Prepare one mainTable and one subTable
		// Method[] methods = Account.class.getDeclaredMethods();

		ReportTable subTable = new ReportTable();
		subTable.setID(1L);
		subTable.setTableName("t_user");
		subTable.setType("2");
		// subTable output columns
		// methods = BasicUser.class.getDeclaredMethods();
		List<ReportColumn> subTableReportColumns = new ArrayList<ReportColumn>();
		ReportColumn subReportColumn = new ReportColumn();
		subReportColumn.setColumnName("name");
		subReportColumn.setColumnUseMode("1");
		subTableReportColumns.add(subReportColumn);
		subTable.setReportColumns(subTableReportColumns);
		// subTable join column
		ReportColumn subTableReportJoinColumn = new ReportColumn();
		subTableReportJoinColumn.setColumnName("account");
		subTableReportJoinColumn.setColumnUseMode("2");

		// reportDao.saveReportTable(subTable);

		// main account table
		Account account = new Account();

		ReportTable mainTable = new ReportTable();
		mainTable.setTableName("t_account");
		mainTable.setType("1");
		mainTable.setJoinType("inner join");
		List<Long> joniReportTableId = new ArrayList<Long>();
		joniReportTableId.add(subTable.getID());
		mainTable.setJoniReportTableId(joniReportTableId);
		List<ReportColumn> mainTableReportColumns = new ArrayList<ReportColumn>();
		ReportColumn mainReportColumn = new ReportColumn();
		mainReportColumn.setColumnName("user_name");
		mainReportColumn.setColumnUseMode("1");
		mainTableReportColumns.add(mainReportColumn);

		mainTable.setReportColumns(mainTableReportColumns);
		// maintable join column
		ReportColumn mainReportJoinColumn = new ReportColumn();
		mainReportJoinColumn.setColumnName("Account_ID");
		mainReportJoinColumn.setColumnUseMode("2");
		mainTable.setReportJoinColumn(mainReportJoinColumn);
		// join type
		mainTable.setJoinType("inner join");

		// reportDao.saveReportTable(mainTable);

		AppReport appReport = new AppReport();
		appReport.getReportTables().add(mainTable);
		appReport.getReportTables().add(subTable);

		AppCusteomReportService appCustomReportService = new AppCusteomReportService(
				appReport);
		appCustomReportService.saveAllReportTable();
		appCustomReportService.buildReport();

	}

	public BasicUser loadTestUser() {
		BasicUserService service = new BasicUserService();
		return service.loadUserById(new Long(1));
	}
}
package com.mocha.cooperate.service.test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.report.AppCusteomReportService;
import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.security.model.Account;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportJoinTable;
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.security.service.BasicUserService;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.thoughtworks.xstream.XStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext.xml"})
public class AppReportServiceTest {

	// private ReportTableDao reportTableDao = SpringContextUtils
	// .getBean(ReportTableDao.class);

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
		subTable.setReportTableId(1L);
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

		// appReport.getReportTables().add(subTable);
		// appCustomReportService.saveReferenceJoinTables();

		// maintable join table
		ReportJoinTable mainReportJoinTable = new ReportJoinTable();
		mainReportJoinTable.setReportTable(subTable);
		List<ReportJoinTable> mainTableReportJoinTables = new ArrayList<ReportJoinTable>();
		mainTableReportJoinTables.add(mainReportJoinTable);
		mainTable.setReportJoinReportTableId(mainTableReportJoinTables);

		appReport.getReportTables().add(mainTable);
		appCustomReportService.saveMainReportTable();
		appCustomReportService.buildReport();

		ArrayList appCustomReportResult = appCustomReportService
				.executeMochaReportQuery();

	}

	public BasicUser loadTestUser() {
		BasicUserService service = new BasicUserService();
		return service.loadUserById(new Long(1));
	}

	public <T> void loadEntityTest() {
		ArrayList<Mocha> entities = load("foundationModel.xml");

		TreeMap<String, ReportTable> allModels = new TreeMap<String, ReportTable>();

		for (Mocha mocha : entities) {
			for (Entity entity : mocha.getEntityList()) {

				ReportTable reportTable = new ReportTable();
				List<ReportColumn> reportColumns = new ArrayList<ReportColumn>();

				Class<?> c = null;
				try {
					c = Class.forName(entity.getEntityClass());
					reportTable.setTableName(entity.getEntityName());

					for (Annotation ann : c.getAnnotations()) {
						System.out.println(ann.getClass().getName());
					}

					for (Field field : c.getDeclaredFields()) {
						ReportColumn reportColumn = new ReportColumn();
						if (field.getAnnotations().length > 0) {
							for (Annotation ann : field.getAnnotations()) {
								System.out.println(ann.annotationType()
										.toString());
								reportColumn.setColumnName(field.getName());
								if (ann.annotationType().toString()
										.contains("OneToMany")
										|| ann.annotationType().toString()
												.contains("ManyToOne")) {
									reportColumn
											.setColumnUseMode(ReportConfiguration.ReportColumnType.JoinColumn
													.toString());
									System.out.println("found!");
								} else if (ann.annotationType().toString()
										.contains("ManyToMany")) {
								} else {
									reportColumn
											.setColumnUseMode(ReportConfiguration.ReportColumnType.OutputColumn
													.toString());
								}
							}
						}
						reportColumns.add(reportColumn);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				reportTable.setReportColumns(reportColumns);
				allModels.put(reportTable.getTableName(), reportTable);
			}
		}
		System.out.println("Done");
	}
	public static <T> T load(String fileName) {
		T entity = null;
		try {
			Resource resource = SpringContextUtils.getApplicationContext()
					.getResource("classpath:" + fileName);
			if (resource != null) {
				XStream stream = new XStream();
				entity = (T) stream.fromXML(resource.getInputStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Test
	public void loadDBSchemaTest() {
//		DBToolUtil dbToolUtil = new DBToolUtil();
//		Map<String,ReportTable> reportTables=dbToolUtil.loadBasicTableInfo();
		
//		dbToolUtil.loadAdvancedTableInfo(reportTables);
	}

}
package com.coral.foundation.jdbc.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.coral.foundation.security.basic.dao.MochaReportDao;
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.spring.bean.SpringContextUtils;

public class DBToolUtil {

	public MochaReportDao mochaReportDao = SpringContextUtils
			.getBean(MochaReportDao.class);

	public Map<String, ReportTable> loadBasicTableInfo() {
		return mochaReportDao.loadDBBasicInfo();
	}

	public Map<String, ReportTable> loadAdvancedTableInfo(
			Map<String, ReportTable> reportTables) {
		return mochaReportDao.loadDBAdvanceInfo(reportTables);
	}
}

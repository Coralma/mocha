package com.coral.foundation.security.basic.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.*;

/**
 * MochaReportDao is a auto Generated class. Please don't modify it.
 * 
 * @author Coral
 */
public interface MochaReportDao extends Dao<MochaReport> {

	public void saveMochaReport(MochaReport mochaReport);

	public ArrayList<String[]> executeReport(MochaReport mochaReport);

	//load db table and columns
	public  Map<String,ReportTable> loadDBBasicInfo();
	//load db foreign keys
	public Map<String,ReportTable> loadDBAdvanceInfo(Map<String,ReportTable> reportTable);
	
	
}

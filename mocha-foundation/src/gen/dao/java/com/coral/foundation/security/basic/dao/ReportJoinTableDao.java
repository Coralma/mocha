package com.coral.foundation.security.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.*;

/**
  * ReportJoinTableDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface ReportJoinTableDao extends Dao<ReportJoinTable> {
	List<ReportTable> findReportTableById(Long id);
}


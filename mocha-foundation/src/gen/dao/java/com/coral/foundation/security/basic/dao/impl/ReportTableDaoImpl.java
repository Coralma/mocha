package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ReportTableDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ReportTableDaoImpl extends JpaDao<ReportTable> implements ReportTableDao {
	
	Logger log=LoggerFactory.getLogger(ReportTableDaoImpl.class);
	public ReportTableDaoImpl() {
		super();
		log.debug(""+ReportTableDaoImpl.class);
	}
}


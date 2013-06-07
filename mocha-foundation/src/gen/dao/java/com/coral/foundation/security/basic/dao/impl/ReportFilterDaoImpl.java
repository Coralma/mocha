package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ReportFilterDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ReportFilterDaoImpl extends JpaDao<ReportFilter> implements ReportFilterDao {
	
	Logger log=LoggerFactory.getLogger(ReportFilterDaoImpl.class);
	public ReportFilterDaoImpl() {
		super();
		log.debug(""+ReportFilterDaoImpl.class);
	}
}


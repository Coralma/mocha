package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ReportColumnDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ReportColumnDaoImpl extends JpaDao<ReportColumn> implements ReportColumnDao {
	
	Logger log=LoggerFactory.getLogger(ReportColumnDaoImpl.class);
	public ReportColumnDaoImpl() {
		super();
		log.debug(""+ReportColumnDaoImpl.class);
	}
}


package com.coral.foundation.security.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * AppReportDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class AppReportDaoImpl extends JpaDao<AppReport> implements AppReportDao {
	
	Logger log=LoggerFactory.getLogger(AppReportDaoImpl.class);
	public AppReportDaoImpl() {
		super();
		log.debug(""+AppReportDaoImpl.class);
	}
	
}


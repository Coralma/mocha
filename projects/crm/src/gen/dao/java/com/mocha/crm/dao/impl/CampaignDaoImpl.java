package com.mocha.crm.dao.impl;
import com.mocha.crm.dao.*;
import com.mocha.crm.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * CampaignDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class CampaignDaoImpl extends JpaDao<Campaign> implements CampaignDao {
	
	Logger log=LoggerFactory.getLogger(CampaignDaoImpl.class);
	public CampaignDaoImpl() {
		super();
		log.debug(""+CampaignDaoImpl.class);
	}
}


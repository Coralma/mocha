package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * MenuPermissionDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class MenuPermissionDaoImpl extends JpaDao<MenuPermission> implements MenuPermissionDao {
	
	Logger log=LoggerFactory.getLogger(MenuPermissionDaoImpl.class);
	public MenuPermissionDaoImpl() {
		super();
		log.debug(""+MenuPermissionDaoImpl.class);
	}
}


package com.mocha.cooperate.basic.dao.impl;
import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * SubToDoItemDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class SubToDoItemDaoImpl extends JpaDao<SubToDoItem> implements SubToDoItemDao {
	
	Logger log=LoggerFactory.getLogger(SubToDoItemDaoImpl.class);
	public SubToDoItemDaoImpl() {
		super();
		log.debug(""+SubToDoItemDaoImpl.class);
	}
}


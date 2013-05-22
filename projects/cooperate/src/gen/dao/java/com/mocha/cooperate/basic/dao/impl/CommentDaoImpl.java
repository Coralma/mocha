package com.mocha.cooperate.basic.dao.impl;
import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * CommentDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class CommentDaoImpl extends JpaDao<Comment> implements CommentDao {
	
	Logger log=LoggerFactory.getLogger(CommentDaoImpl.class);
	public CommentDaoImpl() {
		super();
		log.debug(""+CommentDaoImpl.class);
	}
}


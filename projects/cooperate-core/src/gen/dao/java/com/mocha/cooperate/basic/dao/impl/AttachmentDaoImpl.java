package com.mocha.cooperate.basic.dao.impl;
import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * AttachmentDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class AttachmentDaoImpl extends JpaDao<Attachment> implements AttachmentDao {
	
	Logger log=LoggerFactory.getLogger(AttachmentDaoImpl.class);
	public AttachmentDaoImpl() {
		super();
		log.debug(""+AttachmentDaoImpl.class);
	}
}


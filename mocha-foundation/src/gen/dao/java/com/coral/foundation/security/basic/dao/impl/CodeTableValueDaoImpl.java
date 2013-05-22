package com.coral.foundation.security.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * CodeTableValueDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class CodeTableValueDaoImpl extends JpaDao<CodeTableValue> implements CodeTableValueDao {
	
	Logger log=LoggerFactory.getLogger(CodeTableValueDaoImpl.class);
	public CodeTableValueDaoImpl() {
		super();
		log.debug(""+CodeTableValueDaoImpl.class);
	}

	@Override
	public List<CodeTableValue> findByName(String codeTableName) {
		Query query = entityManager.createQuery("from CodeTableValue where codeTableName = :name", CodeTableValue.class);
		query.setParameter("name", codeTableName);
		List<CodeTableValue> rs = query.getResultList();
		return rs;
	}
}


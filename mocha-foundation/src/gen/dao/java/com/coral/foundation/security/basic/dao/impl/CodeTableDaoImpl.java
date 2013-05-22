package com.coral.foundation.security.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.security.basic.dao.CodeTableDao;
import com.coral.foundation.security.model.CodeTable;

/**
  * CodeTableDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class CodeTableDaoImpl extends JpaDao<CodeTable> implements CodeTableDao {
	
	Logger log=LoggerFactory.getLogger(CodeTableDaoImpl.class);
	public CodeTableDaoImpl() {
		super();
		log.debug(""+CodeTableDaoImpl.class);
	}
	
	@Override
	public CodeTable findByName(String codeTableName) {
		Query query = entityManager.createQuery("from CodeTable where name = :name",CodeTable.class);
		query.setParameter("name", codeTableName);
		List<CodeTable> files = query.getResultList();
		if(files.size() > 0) {
			return files.get(0);
		}
		return null;
	}
}


package com.coral.foundation.security.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.CodeTable;

/**
  * CodeTableDao is a auto Generated class. Please don't modify it.
  */
public class CodeTableDao extends BaseDao<CodeTable> {
	
	@Override
	public Class<CodeTable> getEntityClass() {
		return CodeTable.class;
	}
	
	public CodeTable findByName(String codeTableName) {
		Query query = getEntityManager().createQuery("from CodeTable c where c.name = :name",CodeTable.class);
		query.setParameter("name", codeTableName);
		List<CodeTable> files = query.getResultList();
		if(files.size() > 0) {
			return files.get(0);
		}
		return null;
	}
}


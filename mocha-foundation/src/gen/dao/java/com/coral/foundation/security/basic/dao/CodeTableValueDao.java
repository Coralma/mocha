package com.coral.foundation.security.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.CodeTableValue;

/**
  * CodeTableValueDao is a auto Generated class. Please don't modify it.
  */
public class CodeTableValueDao extends BaseDao<CodeTableValue> {
	
	@Override
	public Class<CodeTableValue> getEntityClass() {
		return CodeTableValue.class;
	}
	
	public List<CodeTableValue> findByName(String codeTableName) {
		Query query = getEntityManager().createQuery("from CodeTableValue c where c.codeTableName = :name", CodeTableValue.class);
		query.setParameter("name", codeTableName);
		List<CodeTableValue> rs = query.getResultList();
		return rs;
	}
}


package com.coral.foundation.security.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ReportJoinTableDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ReportJoinTableDaoImpl extends JpaDao<ReportJoinTable> implements ReportJoinTableDao {
	
	Logger log=LoggerFactory.getLogger(ReportJoinTableDaoImpl.class);
	public ReportJoinTableDaoImpl() {
		super();
		log.debug(""+ReportJoinTableDaoImpl.class);
	}
	@Override
	public List<ReportTable> findReportTableById(Long id) {
		
		
//		String queryLang = "from ReportJoinTable Inner Join  where accountName = :accountName and parentID = :parentID";
//		if(parentId == null) {
//			queryLang = "from File where accountName = :accountName and parentID is null";
//		}
//		Query query = entityManager.createQuery(queryLang,ReportTable.class);
//		query.setParameter("accountName", accountName);
//		if(parentId != null) {
//			query.setParameter("parentID", parentId);
//		}
//		List<File> files = query.getResultList();
//	
//		entityManager.find(entityClass,);
		return null;
	}
}


package com.coral.foundation.security.basic.dao.impl;
import java.util.Collection;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MochaReportDaoImpl is a auto Generated class. Please don't modify it.
 * 
 * @author Coral
 */
public class MochaReportDaoImpl extends JpaDao<MochaReport>
		implements
			MochaReportDao {

	Logger log = LoggerFactory.getLogger(MochaReportDaoImpl.class);
	public MochaReportDaoImpl() {
		super();
		log.debug("" + MochaReportDaoImpl.class);
	}

	@Override
	public void saveMochaReport(MochaReport mochaReport) {
		entityManager.persist(mochaReport);
	}

	@Override
	public Collection executeReport(MochaReport mochaReport) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final Query query = entityManager.createNativeQuery(mochaReport
				.getReportPureQuery());
		Collection<Object> resultList = query.getResultList();
		return resultList;
	}
}
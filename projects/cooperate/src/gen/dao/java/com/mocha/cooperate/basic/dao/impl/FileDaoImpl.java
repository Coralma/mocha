package com.mocha.cooperate.basic.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.StrUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FileDaoImpl is a auto Generated class. Please don't modify it.
 * 
 * @author Coral
 */
public class FileDaoImpl extends JpaDao<File> implements FileDao {

	Logger log = LoggerFactory.getLogger(FileDaoImpl.class);

	public FileDaoImpl() {
		super();
		log.debug("" + FileDaoImpl.class);
	}

	@Override
	public List<File> loadFiles(BasicUser user, Long parentID) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<File> criteria = builder.createQuery(File.class);
		Root<File> fileRoot = criteria.from(File.class);
		criteria.select(fileRoot);
		Predicate predicate = null;
		if(parentID == null) {
			predicate = builder.and(builder.equal(fileRoot.get("creator"), user), builder.isNull(fileRoot.get("parentID")),builder.isNull(fileRoot.get("accountName")));
		} else {
			predicate = builder.and(builder.equal(fileRoot.get("creator"), user), builder.equal(fileRoot.get("parentID"), parentID),builder.isNull(fileRoot.get("accountName")));
		}
		criteria.where(predicate);
		List<File> files = entityManager.createQuery(criteria).getResultList();
		return files;
	}
	
	@Override
	public List<File> loadCompanyFiles(String accountName, Long parentId) {
		String queryLang = "from File where accountName = :accountName and parentID = :parentID";
		if(parentId == null) {
			queryLang = "from File where accountName = :accountName and parentID is null";
		}
		Query query = entityManager.createQuery(queryLang,File.class);
		query.setParameter("accountName", accountName);
		if(parentId != null) {
			query.setParameter("parentID", parentId);
		}
		List<File> files = query.getResultList();
		return files;
	}
	
	public List<File> fuzzySearchFile(BasicUser user, String condition) {
		// using criteria
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<File> criteria = builder.createQuery(File.class);
//		Root<File> fileRoot = criteria.from(File.class);
//		criteria.select(fileRoot);
//		Expression<String> fileName = fileRoot.get("name");
//		criteria.where(builder.and(builder.equal(fileRoot.get("creator"), user), builder.like(fileName, likeCondition)));
//		List<File> files = entityManager.createQuery(criteria).getResultList();
		
		// using query language
		Query query = entityManager.createQuery("from File where name like :name",File.class);
		query.setParameter("name", StrUtils.like(condition));
		List<File> files = query.getResultList();
		return files;
	}

	@Override
	public File findFileByShareKey(String shareKey) {
		Query query = entityManager.createQuery("from File where shareKey like :shareKey",File.class);
		query.setParameter("shareKey", shareKey);
		List<File> files = query.getResultList();
		if(files.size() > 0) {
			return files.get(0);
		}
		return null;
	}

	@Override
	public List<File> fuzzySearchFile(String accountName, String condition) {
		Query query = entityManager.createQuery("from File where name like :name and accountName = :accountName",File.class);
		query.setParameter("name", StrUtils.like(condition));
		query.setParameter("accountName", accountName);
		List<File> files = query.getResultList();
		return files;
	}
}

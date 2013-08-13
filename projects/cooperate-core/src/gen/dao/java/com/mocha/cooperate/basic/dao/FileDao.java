package com.mocha.cooperate.basic.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.StrUtils;
import com.mocha.cooperate.model.File;

/**
  * FileDao is a auto Generated class. Please don't modify it.
  */
public class FileDao extends BaseDao<File> {
	
	@Override
	public Class<File> getEntityClass() {
		return File.class;
	}
	
	public List<File> loadFiles(BasicUser user, Long parentID) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
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
		List<File> files = getEntityManager().createQuery(criteria).getResultList();
		return files;
	}
	
	
	public List<File> loadCompanyFiles(String accountName, Long parentId) {
		String queryLang = "from File f where f.accountName = :accountName and f.parentID = :parentID";
		if(parentId == null) {
			queryLang = "from File f where f.accountName = :accountName and f.parentID is null";
		}
		Query query = getEntityManager().createQuery(queryLang,File.class);
		query.setParameter("accountName", accountName);
		if(parentId != null) {
			query.setParameter("parentID", parentId);
		}
		List<File> files = query.getResultList();
		return files;
	}
	
	public List<File> fuzzySearchFile(BasicUser user, String condition) {
		// using query language
		Query query = getEntityManager().createQuery("from File f where f.name like :name",File.class);
		query.setParameter("name", StrUtils.like(condition));
		List<File> files = query.getResultList();
		return files;
	}

	
	public File findFileByShareKey(String shareKey) {
		Query query = getEntityManager().createQuery("from File f where f.shareKey like :shareKey",File.class);
		query.setParameter("shareKey", shareKey);
		List<File> files = query.getResultList();
		if(files.size() > 0) {
			return files.get(0);
		}
		return null;
	}

	
	public List<File> fuzzySearchFile(String accountName, String condition) {
		Query query = getEntityManager().createQuery("from File f where f.name like :name and f.accountName = :accountName",File.class);
		query.setParameter("name", StrUtils.like(condition));
		query.setParameter("accountName", accountName);
		List<File> files = query.getResultList();
		return files;
	}
}


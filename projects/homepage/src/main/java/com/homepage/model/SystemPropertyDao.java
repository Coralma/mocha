package com.homepage.model;

import javax.persistence.Query;

public class SystemPropertyDao extends abstractDao {
	
	public SystemPropertyDao(){
		super();
	}

	public <T> T findSystemValueByKey(String key){
		entityMfg.getTransaction().begin();
		Query query=entityMfg.createNamedQuery("findSystemPropertyByKey");
		query.setParameter("key", key);
		String value=(String) query.getSingleResult();
		return (T) value;
	}
	
	
}

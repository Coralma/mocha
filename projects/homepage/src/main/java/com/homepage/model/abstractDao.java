package com.homepage.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.internal.jpa.EntityManagerImpl;

public class abstractDao {

	private static final String PERSISTENCE_UNIT_NAME = "homepage";
	private static EntityManagerFactory factory;

	protected EntityManager entityMfg;

	public abstractDao() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityMfg = (EntityManagerImpl) factory.createEntityManager();
	}

	public void closeEntityMfg() {
		entityMfg.close();
	}

}

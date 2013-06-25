package com.mocha.ib.dao.impl;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.jpa.impl.JpaDao;
import com.mocha.ib.dao.InsuranceProductDao;
import com.mocha.ib.model.InsuranceCompany;
import com.mocha.ib.model.InsuranceProduct;

/**
  * InsuranceProductDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class InsuranceProductDaoImpl extends JpaDao<InsuranceProduct> implements InsuranceProductDao {
	
	Logger log=LoggerFactory.getLogger(InsuranceProductDaoImpl.class);
	public InsuranceProductDaoImpl() {
		super();
		log.debug(""+InsuranceProductDaoImpl.class);
	}

	@Override
	public List<InsuranceProduct> findProductByCompany(InsuranceCompany company) {
		Query query = entityManager.createQuery("from InsuranceProduct t where t.insuranceCompany=:insuranceCompany",InsuranceProduct.class);
		query.setParameter("insuranceCompany", company);
		List<InsuranceProduct> products = query.getResultList();
		return products;
	}
}


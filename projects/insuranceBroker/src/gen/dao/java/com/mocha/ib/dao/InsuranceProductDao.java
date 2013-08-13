package com.mocha.ib.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.mocha.ib.model.InsuranceCompany;
import com.mocha.ib.model.InsuranceProduct;

/**
  * InsuranceProductDao is a auto Generated class. Please don't modify it.
  */
public class InsuranceProductDao extends BaseDao<InsuranceProduct> {
	
	@Override
	public Class<InsuranceProduct> getEntityClass() {
		return InsuranceProduct.class;
	}
	
	public List<InsuranceProduct> findProductByCompany(InsuranceCompany company) {
		Query query = getEntityManager().createQuery("from InsuranceProduct t where t.insuranceCompany=:insuranceCompany",InsuranceProduct.class);
		query.setParameter("insuranceCompany", company);
		List<InsuranceProduct> products = query.getResultList();
		return products;
	}
}


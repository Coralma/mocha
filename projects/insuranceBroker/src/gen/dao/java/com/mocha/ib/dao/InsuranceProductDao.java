package com.mocha.ib.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.mocha.ib.model.*;

/**
  * InsuranceProductDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface InsuranceProductDao extends Dao<InsuranceProduct> {
	
	public List<InsuranceProduct> findProductByCompany(InsuranceCompany company);
}


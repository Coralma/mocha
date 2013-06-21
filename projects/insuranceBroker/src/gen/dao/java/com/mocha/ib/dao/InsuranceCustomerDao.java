package com.mocha.ib.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.ib.model.*;

/**
  * InsuranceCustomerDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface InsuranceCustomerDao extends Dao<InsuranceCustomer> {
	
	public InsuranceCustomer findCustomerByUser(BasicUser customerUser);
}


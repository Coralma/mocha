package com.mocha.co.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.co.model.*;

/**
  * CommerceCustomerDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface CommerceCustomerDao extends Dao<CommerceCustomer> {

	CommerceCustomer findCCByUser(BasicUser basicUser);
	
}


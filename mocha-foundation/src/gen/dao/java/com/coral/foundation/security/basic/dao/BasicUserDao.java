package com.coral.foundation.security.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.*;

/**
  * BasicUserDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface BasicUserDao extends Dao<BasicUser> {
	
	public List<BasicUser> findActiveUsers(int page);
	public List<BasicUser> findInActiveUsers(int page);
	public List<BasicUser> fuzzyUserSearch(String condition);
	
}


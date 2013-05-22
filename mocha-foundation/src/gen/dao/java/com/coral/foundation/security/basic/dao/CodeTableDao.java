package com.coral.foundation.security.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.*;

/**
  * CodeTableDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface CodeTableDao extends Dao<CodeTable> {
	
	public CodeTable findByName(String codeTableName);
}


package com.coral.foundation.security.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.*;

/**
  * CodeTableValueDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface CodeTableValueDao extends Dao<CodeTableValue> {

	public List<CodeTableValue> findByName(String codeTableName);
}


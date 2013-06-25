package com.mocha.cooperate.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.*;

/**
  * FileDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface FileDao extends Dao<File> {
	
	public List<File> loadFiles(BasicUser user, Long parentId);
	public List<File> loadCompanyFiles(String accountName, Long parentId);
	public List<File> fuzzySearchFile(BasicUser user, String condition);
	public List<File> fuzzySearchFile(String accountName, String condition);
	public File findFileByShareKey(String shareKey);
}


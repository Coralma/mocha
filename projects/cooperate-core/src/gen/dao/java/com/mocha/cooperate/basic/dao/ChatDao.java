package com.mocha.cooperate.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.*;

/**
  * ChatDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface ChatDao extends Dao<Chat> {
	
	public List<Chat> loadChats(BasicUser currentUser);
}


package com.mocha.cooperate.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.*;

/**
  * NotifyLineDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface NotifyLineDao extends Dao<NotifyLine> {
	
	public int loadNotifyNumber(BasicUser basicUser);
	
	public void readAllNotify(BasicUser basicUser);
}


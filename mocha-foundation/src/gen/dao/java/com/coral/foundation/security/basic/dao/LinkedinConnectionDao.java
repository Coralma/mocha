package com.coral.foundation.security.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.*;

/**
  * LinkedinConnectionDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface LinkedinConnectionDao extends Dao<LinkedinConnection> {
	List<LinkedinConnection> findFollowedConnectionByPerson(LinkedinPersonProfile profile);

	List<LinkedinConnection> findEntireConnectionByConn(LinkedinConnection connUser);
}


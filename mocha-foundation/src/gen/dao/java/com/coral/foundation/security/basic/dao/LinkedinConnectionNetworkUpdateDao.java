package com.coral.foundation.security.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.*;

/**
  * LinkedinConnectionNetworkUpdateDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface LinkedinConnectionNetworkUpdateDao extends Dao<LinkedinConnectionNetworkUpdate> {

	List<LinkedinConnectionNetworkUpdate> findUpdateByConnection(LinkedinConnection conn);

	boolean findDuplicateUpdate(LinkedinConnection con, LinkedinConnectionNetworkUpdate update);

	
}


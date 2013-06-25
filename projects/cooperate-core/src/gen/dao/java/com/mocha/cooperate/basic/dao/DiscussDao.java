package com.mocha.cooperate.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.mocha.cooperate.model.*;

/**
  * DiscussDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface DiscussDao extends Dao<Discuss> {
	
	public List<Discuss> queryLastThreeNotification();
	
	public List<Discuss> queryLastThreeQuestion();
	
	public List<Discuss> queryLastThreeDiscuss();
	
	public List<Discuss> queryTopic(String type, int page);
}


package com.mocha.cooperate.basic.dao;
import java.util.Date;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.*;

/**
  * TimeLineDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface TimeLineDao extends Dao<TimeLine> {
	
	public List<TimeLine> loadTimeLine(BasicUser basicUser, int page);
	public List<TimeLine> loadTimeLineByUser(BasicUser basicUser, Date date, int page);
	public List<TimeLine> loadStatus(BasicUser basicUser, int page);
	public List<TimeLine> loadStatusByUser(BasicUser basicUser, int page);
	public List<TimeLine> loadDiscuss(BasicUser basicUser, int page);
	public List<TimeLine> loadDiscussByUser(BasicUser basicUser, int page);
	public List<TimeLine> loadTodo(BasicUser basicUser, int page);
	public List<TimeLine> loadTodoByUser(BasicUser basicUser, int page);

	public TimeLine queryTimelineByTodo(ToDo todo);
	public TimeLine queryTimelineByDiscuss(Discuss discuss);
	public TimeLine queryOldestData(BasicUser user);
}


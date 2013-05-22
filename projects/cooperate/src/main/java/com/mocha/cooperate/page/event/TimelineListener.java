package com.mocha.cooperate.page.event;

import java.util.List;

import com.mocha.cooperate.model.TimeLine;

public interface TimelineListener {

	public List<TimeLine> loadMessages(String type);
//	public List<TimeLine> queryAll();
//	public List<TimeLine> queryStatusList();
//	public List<TimeLine> queryDiscussList();
//	public List<TimeLine> queryTodoList();
}

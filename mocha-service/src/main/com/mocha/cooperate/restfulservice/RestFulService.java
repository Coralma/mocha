package com.mocha.cooperate.restfulservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import com.mocha.service.model.MobileFeeds;

public interface RestFulService {

	public List<ToDo> getFullToDos(@PathParam("uuid") String uuid);

	public List<ToDo> loadActivityTodo(BasicUser basicUser);

	List<ToDo> loadCompletedTodo(BasicUser basicUser);

	public MobileFeeds getMobileFeeds();
}

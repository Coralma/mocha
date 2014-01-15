package com.mocha.cooperate.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.ToDo;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("v1")
public interface ToDoService {
	@GET
//	@Path("todos/{uuid}")
	@Path("/getToDo/{uuid}")
	@Consumes({ "application/x-javascript", "application/json", "application/xml" })
	@Produces({ "application/x-javascript", "application/json", "application/xml" })
	public JSONWithPadding getFullToDos(@PathParam("uuid") String uuid, @QueryParam("callback") String callback,
			@QueryParam("_") String emptyParam);

	public List<ToDo> loadActivityTodo(BasicUser basicUser);

	List<ToDo> loadCompletedTodo(BasicUser basicUser);
}
